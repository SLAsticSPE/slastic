package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.LinkedList;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

import kieker.tools.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.IAssemblyComponentInvocationCountReceiver;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * Triggers the {@link ConfigurationManager} to perform reconfigurations based on 
 * the configured rule sets for {@link AssemblyComponent}s, and the incomcing workload 
 * intensity data received via its {@link #update(long, AssemblyComponent, Long)}
 * method.  
 * 
 * @author Andre van Hoorn
 * 
 */
public class WorkloadIntensityRuleEngine implements
		IAssemblyComponentInvocationCountReceiver {
	private static final Log log = LogFactory
			.getLog(WorkloadIntensityRuleEngine.class);

	/**
	 * Performs the actual reconfigurations.
	 */
	private final ConfigurationManager configurationManager;
	
	/**
	 * Queue for workload intensity values to be processed. 
	 * The processing of workload intensity value may or may not 
	 * trigger the execution of a reconfiguration emplying the
	 * {@link #configurationManager}.
	 */
	private final ScheduledThreadPoolExecutor reconfigurationWorkerExecutor;

	// TODO: HashMap AssemblyComponent x LinkedList<WorkloadIntensityEvent>
	// TODO: Also: Why aren't we using an AtomicReference as we are considering
	// only the most recent value anyway (and drop outdated ones)
	private final LinkedList<WorkloadIntensityEvent> pendingWorkloadIntensityEvents =
			new LinkedList<WorkloadIntensityEvent>();

	// TODO: HashMap AssemblyComponent x NumNodesRuleSet2
	private final NumNodesRuleSet2 ruleSet;

	// TODO: HashMap AssemblyComponent x Baseline
	private volatile Baseline prevBaseline;

	// TODO: HashMap ExecutionContainerType x int (max num instances per node)

	/**
	 * 
	 * @param ruleSet
	 * @param configurationManager
	 */
	// TODO: Pass HashMap AssemblyComponent x NumNodesRuleSet2
	public WorkloadIntensityRuleEngine(final NumNodesRuleSet2 ruleSet,
			final ConfigurationManager configurationManager) {
		this.reconfigurationWorkerExecutor =
				this.createReconfigurationWorkerExecutor();
		this.ruleSet = ruleSet;
		this.prevBaseline = this.ruleSet.getInitialBaseline();
		this.configurationManager = configurationManager;
	}

	@Override
	public synchronized void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Long count) {

		// TODO: distinctions for AssemblyComponents

		// 1. Check whether this is the right component
		{ // TODO: Remove HACK
			if (!assemblyComponent.getName().equals(
					"OperationExecutionRegistrationAndLoggingFilter")) {
				return;
			}
		}

		{ // TODO: Remove HACK
			this.configurationManager.setAssemblyComponent(assemblyComponent);
			final ExecutionContainerType executionContainerType =
					this.configurationManager.getModelManager()
							.getTypeRepositoryManager().getModel()
							.getExecutionContainerTypes().get(0);
			this.configurationManager
					.setExecutionContainerType(executionContainerType);
		}

		WorkloadIntensityRuleEngine.log
				.info("Incoming intensity: "
						+ LoggingTimestampConverter
								.convertLoggingTimestampToUTCString(currentTimestampMillis
										* (1000 * 1000)) + ": " + count);

		// 2. Every update spawns a worker job
		this.pendingWorkloadIntensityEvents.add(new WorkloadIntensityEvent(currentTimestampMillis, count));
		final WorkloadIntensityEventWorker w = new WorkloadIntensityEventWorker(this, this.configurationManager);
		this.reconfigurationWorkerExecutor.submit(w);
	}

	/**
	 * 
	 * @return
	 */
	// TODO: pass AssemblyComponent
	// TODO: return Baseline object
	public synchronized int nextNumNodes() {
		final int newNumNodes;
		WorkloadIntensityEvent nextEvent = null;

		// TODO: lookup from HashMap using (to be) passed AssemblyComponent

		while (!this.pendingWorkloadIntensityEvents.isEmpty()) {
			nextEvent = this.pendingWorkloadIntensityEvents.removeFirst();
			if (!this.pendingWorkloadIntensityEvents.isEmpty()) {
				WorkloadIntensityRuleEngine.log.info("Dropping " + nextEvent);
			}
		}

		if (nextEvent == null) {
			// TODO: under which circumstances could this happen?
			newNumNodes = -1;
		} else {
			// TODO: baseline should be committed/canceled from "outside" after
			// successful/failed reconfiguration
			this.prevBaseline = this.ruleSet.getNextBaseline(this.prevBaseline, nextEvent.getWorkloadIntensity());
			newNumNodes = this.prevBaseline.getNumNodes();
		}
		return newNumNodes;
	}

	/**
	 * 
	 * @return
	 */
	private final ScheduledThreadPoolExecutor createReconfigurationWorkerExecutor() {
		final ScheduledThreadPoolExecutor executor =
				new ScheduledThreadPoolExecutor(
						/* the only thread that executes the reconfigurations: */
						1,
						/*
						 * Handler for failed sensor executions that simply logs
						 * notifications.
						 */
						new RejectedExecutionHandler() {

							@Override
							public void rejectedExecution(final Runnable r,
									final ThreadPoolExecutor executor) {
								WorkloadIntensityRuleEngine.log
										.error("Exception caught by RejectedExecutionHandler for Runnable "
												+ r + " and ThreadPoolExecutor " + executor);

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