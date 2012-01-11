package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.workloadIntensityBased;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

import kieker.tools.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.ConfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.IAssemblyComponentInvocationCountReceiver;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * Triggers the {@link ConfigurationManager} to perform reconfigurations based
 * on the configured rule sets for {@link AssemblyComponent}s, and the incomcing
 * workload intensity data received via its
 * {@link #update(long, AssemblyComponent, Long)} method.
 * 
 * @author Andre van Hoorn
 * 
 */
public class WorkloadIntensityRuleEngine implements IAssemblyComponentInvocationCountReceiver {
	private static final Log log = LogFactory.getLog(WorkloadIntensityRuleEngine.class);

	private final ModelManager modelManager;

	/**
	 * Performs the actual reconfigurations.
	 */
	private final ConfigurationManager configurationManager;

	/**
	 * Queue for workload intensity values to be processed. The processing of
	 * workload intensity value may or may not trigger the execution of a
	 * reconfiguration emplying the {@link #configurationManager}.
	 */
	private final ScheduledThreadPoolExecutor reconfigurationWorkerExecutor;

	/**
	 * AssemblyComponent (name) x most recent (pending, if such) workload
	 * intensity value
	 */
	private final Map<String, AtomicReference<WorkloadIntensityEvent>> pendingWorkloadIntensityEvents =
			new HashMap<String, AtomicReference<WorkloadIntensityEvent>>();

	/** AssemblyComponent (name) x rule set */
	private final Map<String, NumDeploymentsForAssemblyComponentRuleSet> ruleSets;

	/** AssemblyComponent (name) x Baseline */
	private volatile Map<String, Baseline> prevBaselines =
			new HashMap<String, Baseline>();

	// TODO: HashMap ExecutionContainerType x int (max num instances per node)

	/**
	 * 
	 */
	public WorkloadIntensityRuleEngine(final ModelManager modelManager,
			final Map<String, NumDeploymentsForAssemblyComponentRuleSet> ruleSets,
			final ConfigurationManager configurationManager) {
		this.modelManager = modelManager;
		this.reconfigurationWorkerExecutor = this.createReconfigurationWorkerExecutor();
		this.ruleSets = ruleSets;

		/* Initialize previous baselines with initial baselines */
		for (final NumDeploymentsForAssemblyComponentRuleSet rs : this.ruleSets.values()) {
			final String fqAssemblyComponentName = rs.getFQAssemblyComponentName();
			this.prevBaselines.put(fqAssemblyComponentName, rs.getInitialBaseline());
			this.pendingWorkloadIntensityEvents.put(fqAssemblyComponentName,
					/* null: no workload intensity value so far */
					new AtomicReference<WorkloadIntensityEvent>(null));
		}

		// TODO: max num instances

		this.configurationManager = configurationManager;
	}

	@Override
	public synchronized void update(final long currentTimestampMillis, final AssemblyComponent assemblyComponent,
			final Long count) {

		final String fqAssemblyComponentName =
				NameUtils.createFQName(assemblyComponent.getPackageName(), assemblyComponent.getName());

		final NumDeploymentsForAssemblyComponentRuleSet rs = this.ruleSets.get(fqAssemblyComponentName);

		if (rs == null) {
			WorkloadIntensityRuleEngine.log.error("Received workload event for unregistered assembly component: "
					+ assemblyComponent);
			return;
		}

		WorkloadIntensityRuleEngine.log.info("Incoming intensity: "
				+ LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000))
				+ ": " + count);
		
		final String fqExecutionContainerTypeName = rs.getFQExecutionContainerTypeName();
		final ExecutionContainerType executionContainerType =
				this.modelManager.getTypeRepositoryManager().lookupExecutionContainerType(fqExecutionContainerTypeName);
		if (executionContainerType == null) {
			WorkloadIntensityRuleEngine.log.error("Failed to lookup execution container type with name '"
					+ fqExecutionContainerTypeName + "'");
			return;
		}

		// 2. Every update spawns a worker job
		final AtomicReference<WorkloadIntensityEvent> pendingEventRef =
				this.pendingWorkloadIntensityEvents.get(fqAssemblyComponentName);

		final WorkloadIntensityEvent oldEvent =
				pendingEventRef.getAndSet(new WorkloadIntensityEvent(currentTimestampMillis, count));
		if (oldEvent != null) {
			WorkloadIntensityRuleEngine.log.info("Dropping " + oldEvent);
		}

		final WorkloadIntensityEventWorker w =
				new WorkloadIntensityEventWorker(assemblyComponent, executionContainerType, this,
						this.configurationManager, pendingEventRef);
		this.reconfigurationWorkerExecutor.submit(w);
	}

	/**
	 * 
	 * @return
	 */
	public synchronized Baseline nextBaseline(final AssemblyComponent assemblyComponent,
			final WorkloadIntensityEvent nextEvent) {
		final String fqAssemblyComponentName =
				NameUtils.createFQName(assemblyComponent.getPackageName(), assemblyComponent.getName());

		final NumDeploymentsForAssemblyComponentRuleSet rs =
				this.ruleSets.get(fqAssemblyComponentName);
		if (rs == null) {
			WorkloadIntensityRuleEngine.log.error("Failed to lookup rule set for assembly component: "
					+ assemblyComponent);
			return null;
		}

		final Baseline prevBaseline = this.prevBaselines.get(fqAssemblyComponentName);
		if (prevBaseline == null) {
			WorkloadIntensityRuleEngine.log.error("Failed to lookup previous baseline for assembly component: " +
					assemblyComponent);
			return null;
		}

		return rs.getNextBaseline(prevBaseline, nextEvent.getWorkloadIntensity());
	}

	/**
	 * Must be called after the successful execution of a reconfiguration to
	 * commit the current state.
	 * 
	 * @param assemblyComponent
	 * @param baseline
	 */
	public synchronized void commitBaseline(final AssemblyComponent assemblyComponent, final Baseline baseline) {
		final String fqAssemblyComponentName =
				NameUtils.createFQName(assemblyComponent.getPackageName(), assemblyComponent.getName());

		final Baseline prevBaseline = this.prevBaselines.get(fqAssemblyComponentName);
		if (prevBaseline == null) {
			WorkloadIntensityRuleEngine.log.warn("Committing baseline '" + baseline
					+ "' for assembly component without previous baseline: "
					+ assemblyComponent);
		}

		this.prevBaselines.put(fqAssemblyComponentName, baseline);
	}

	/**
	 * 
	 * @return
	 */
	private final ScheduledThreadPoolExecutor createReconfigurationWorkerExecutor() {
		final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
				/* the only thread that executes the reconfigurations: */
				1,
				/*
				 * Handler for failed sensor executions that simply logs
				 * notifications.
				 */
				new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
						WorkloadIntensityRuleEngine.log
								.error("Exception caught by RejectedExecutionHandler for Runnable " + r
										+ " and ThreadPoolExecutor " + executor);

					}
				});
		executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
		executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		return executor;
	}

	public void terminate() {
		this.reconfigurationWorkerExecutor.shutdown();
	}
}