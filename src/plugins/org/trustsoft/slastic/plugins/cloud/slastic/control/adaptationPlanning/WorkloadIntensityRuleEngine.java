package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicReference;

import kieker.tools.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.IAssemblyComponentInvocationCountReceiver;

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

	// TODO: HashMap AssemblyComponent (name) x
	// LinkedList<WorkloadIntensityEvent>
	private final AtomicReference<WorkloadIntensityEvent> pendingWorkloadIntensityEvent =
			new AtomicReference<WorkloadIntensityEvent>();

	// TODO: HashMap AssemblyComponent (name) x NumNodesRuleSet2
	private final NumNodesRuleSet2 ruleSet;

	// TODO: HashMap AssemblyComponent (name) x Baseline
	private volatile Baseline prevBaseline;

	// TODO: HashMap ExecutionContainerType x int (max num instances per node)

	/**
	 * 
	 * @param ruleSet
	 * @param configurationManager
	 */
	// TODO: Pass HashMap AssemblyComponent x NumNodesRuleSet2
	public WorkloadIntensityRuleEngine(final NumNodesRuleSet2 ruleSet, final ConfigurationManager configurationManager) {
		this.reconfigurationWorkerExecutor = this.createReconfigurationWorkerExecutor();
		this.ruleSet = ruleSet;
		this.prevBaseline = this.ruleSet.getInitialBaseline();
		this.configurationManager = configurationManager;
	}

	@Override
	public synchronized void update(final long currentTimestampMillis, final AssemblyComponent assemblyComponent,
			final Long count) {

		// TODO: distinctions for AssemblyComponents

		// 1. Check whether this is the right component
		{ // TODO: Remove HACK
			if (!assemblyComponent.getName().equals("OperationExecutionRegistrationAndLoggingFilter")) {
				return;
			}
		}

		// TODO: lookup execution container type based on container type name in
		// ruleSet

		final ExecutionContainerType executionContainerType;

		{ // TODO: Remove HACK
			this.configurationManager.setAssemblyComponent(assemblyComponent);
			executionContainerType =
					this.configurationManager.getModelManager().getTypeRepositoryManager().getModel()
							.getExecutionContainerTypes().get(0);
			this.configurationManager.setExecutionContainerType(executionContainerType);
		}

		WorkloadIntensityRuleEngine.log.info("Incoming intensity: "
				+ LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000))
				+ ": " + count);

		// 2. Every update spawns a worker job
		final WorkloadIntensityEvent oldEvent =
				this.pendingWorkloadIntensityEvent.getAndSet(new WorkloadIntensityEvent(currentTimestampMillis, count));
		if (oldEvent != null) {
			WorkloadIntensityRuleEngine.log.info("Dropping " + oldEvent);
		}

		final WorkloadIntensityEventWorker w =
				new WorkloadIntensityEventWorker(assemblyComponent, executionContainerType, this,
						this.configurationManager, null);
		this.reconfigurationWorkerExecutor.submit(w);
	}

	/**
	 * 
	 * @return
	 */
	// TODO: pass assembly component
	public synchronized Baseline nextBaseline(final WorkloadIntensityEvent nextEvent) {
		return this.ruleSet.getNextBaseline(this.prevBaseline, nextEvent.getWorkloadIntensity());
	}

	/**
	 * Must be called after the successful execution of a reconfiguration to
	 * commit the current state.
	 * 
	 * @param assemblyComponent
	 * @param baseline
	 */
	public synchronized void commitBaseline(final AssemblyComponent assemblyComponent, final Baseline baseline) {
		this.prevBaseline = baseline;
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
		 * Handler for failed sensor executions that simply logs notifications.
		 */
		new RejectedExecutionHandler() {

			@Override
			public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
				WorkloadIntensityRuleEngine.log.error("Exception caught by RejectedExecutionHandler for Runnable " + r
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