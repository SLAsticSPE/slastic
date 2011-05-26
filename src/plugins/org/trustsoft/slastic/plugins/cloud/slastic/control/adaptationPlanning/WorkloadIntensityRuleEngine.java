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

public class WorkloadIntensityRuleEngine implements
		IAssemblyComponentInvocationCountReceiver {
	private static final Log log = LogFactory
			.getLog(WorkloadIntensityRuleEngine.class);

	private final ScheduledThreadPoolExecutor reconfigurationWorkerExecutor;

	private final LinkedList<WorkloadIntensityEvent> pendingWorkloadIntensityEvents =
			new LinkedList<WorkloadIntensityEvent>();

	private final ConfigurationManager configurationManager;

	private final NumNodesRuleSet2 ruleSet;

	private volatile Baseline prevBaseline;

	/**
	 * 
	 * @param ruleSet
	 * @param configurationManager
	 */
	public WorkloadIntensityRuleEngine(final NumNodesRuleSet2 ruleSet,
			final ConfigurationManager configurationManager) {
		this.reconfigurationWorkerExecutor =
				this.createReconfigurationWorkerExecutor();
		this.ruleSet = ruleSet;
		this.prevBaseline = this.ruleSet.getInitialBaseline();
		this.configurationManager = configurationManager;
	}

	/**
	 * 
	 * @return
	 */
	private final ScheduledThreadPoolExecutor createReconfigurationWorkerExecutor() {
		final ScheduledThreadPoolExecutor executor =
				new ScheduledThreadPoolExecutor(1, // the only thread that
													// executes the
													// reconfigurations
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
												+ r
												+ " and ThreadPoolExecutor "
												+ executor);

							}
						});
		executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);
		executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		return executor;
	}

	@Override
	public synchronized void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Long count) {

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

		// 2. Every update spawns a worker
		this.pendingWorkloadIntensityEvents.add(new WorkloadIntensityEvent(
				currentTimestampMillis, count));
		final WorkloadIntensityEventWorker w =
				new WorkloadIntensityEventWorker(this,
						this.configurationManager);
		this.reconfigurationWorkerExecutor.submit(w);
	}

	/**
	 * 
	 * @return
	 */
	public synchronized int nextNumNodes() {
		final int newNumNodes;
		WorkloadIntensityEvent nextEvent = null;

		while (!this.pendingWorkloadIntensityEvents.isEmpty()) {
			nextEvent = this.pendingWorkloadIntensityEvents.removeFirst();
			if (!this.pendingWorkloadIntensityEvents.isEmpty()) {
				WorkloadIntensityRuleEngine.log.info("Dropping " + nextEvent);
			}
		}

		if (nextEvent == null) {
			newNumNodes = -1;
		} else {
			this.prevBaseline =
					this.ruleSet.getNextBaseline(this.prevBaseline,
							nextEvent.getWorkloadIntensity());
			newNumNodes = this.prevBaseline.getNumNodes();
		}
		return newNumNodes;
	}

	public void terminate() {
		this.reconfigurationWorkerExecutor.shutdown();
	}
}