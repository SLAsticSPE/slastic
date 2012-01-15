package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.utilizationBased;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

import kieker.tools.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning.ConfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

public class OverallCPUUtilizationRuleEngine {
	private static final Log log = LogFactory.getLog(OverallCPUUtilizationRuleEngine.class);

	private final static int DefaultStartOfVmInSec = 90;

	private final ModelManager modelManager;
	private final ConfigurationManager configurationManager;

	private final ScheduledThreadPoolExecutor reconfigurationWorkerExecutor;

	private final AtomicReference<OverallCPUUtilizationEvent> pendingOverallCPUUtilizationEvent;

	private final OverallCPUUtilizationRuleSet ruleSet;

	private final Map<CPUUtilizationRule, Long> provisioningStartedInSecMap = new HashMap<CPUUtilizationRule, Long>();
	private final Map<CPUUtilizationRule, Integer> waitForVMStartInSecMap = new HashMap<CPUUtilizationRule, Integer>();

	public OverallCPUUtilizationRuleEngine(final ModelManager modelManager,
			final OverallCPUUtilizationRuleSet ruleSet,
			final ConfigurationManager configurationManager) {
		this.modelManager = modelManager;
		this.reconfigurationWorkerExecutor = this.createReconfigurationWorkerExecutor();
		this.ruleSet = ruleSet;
		this.configurationManager = configurationManager;

		this.pendingOverallCPUUtilizationEvent = new AtomicReference<OverallCPUUtilizationEvent>(null);
	}

	public synchronized void update(final Long currentTimestampMillis,
			final Double cpuUtil) {

		if (cpuUtil == null) {
			return;
		}

		OverallCPUUtilizationRuleEngine.log.info("Incoming CPU Utilization: "
				+ LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000))
				+ " (" + currentTimestampMillis + ") " + ": " + cpuUtil);

		final String fqExecutionContainerTypeName = this.ruleSet.getFQExecutionContainerTypeName();
		final ExecutionContainerType executionContainerType =
				this.modelManager.getTypeRepositoryManager().lookupExecutionContainerType(fqExecutionContainerTypeName);
		if (executionContainerType == null) {
			OverallCPUUtilizationRuleEngine.log.error("Failed to lookup execution container type with name '"
					+ fqExecutionContainerTypeName + "'");
			return;
		}

		final String assemblyComponentName = this.ruleSet.getAssemblyComponentName();
		final AssemblyComponent assemblyComponent =
				this.modelManager.getComponentAssemblyModelManager().lookupAssemblyComponent(assemblyComponentName);
		if (assemblyComponent == null) {
			OverallCPUUtilizationRuleEngine.log.error("Failed to lookup assembly component with name '"
					+ assemblyComponentName + "'");
			return;
		}

		this.spawnAWorkerJob(currentTimestampMillis, cpuUtil, executionContainerType, assemblyComponent);
	}

	private void spawnAWorkerJob(final Long currentTimestampMillis, final Double cpuUtil,
			final ExecutionContainerType executionContainerType, final AssemblyComponent assemblyComponent) {
		final AtomicReference<OverallCPUUtilizationEvent> pendingEventRef =
				this.pendingOverallCPUUtilizationEvent;

		if (pendingEventRef == null) {
			OverallCPUUtilizationRuleEngine.log.info("pendingEventRef is null!");
		}

		final OverallCPUUtilizationEvent oldEvent =
				pendingEventRef.getAndSet(new OverallCPUUtilizationEvent(currentTimestampMillis, cpuUtil));
		if (oldEvent != null) {
			OverallCPUUtilizationRuleEngine.log.info("Dropping " + oldEvent);
		}

		final OverallCPUUtilizationEventWorker w =
				new OverallCPUUtilizationEventWorker(assemblyComponent, executionContainerType, this,
						this.configurationManager, pendingEventRef);
		this.reconfigurationWorkerExecutor.submit(w);
	}

	private final ScheduledThreadPoolExecutor createReconfigurationWorkerExecutor() {
		final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(
				1,
				new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(final Runnable r, final ThreadPoolExecutor executor) {
						OverallCPUUtilizationRuleEngine.log
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

	public List<CPUUtilizationRule> evaluate(final OverallCPUUtilizationEvent curOverallCPUUtilizationEvent) {
		final List<CPUUtilizationRule> rules = this.ruleSet.getRules();
		final List<CPUUtilizationRule> result = new ArrayList<CPUUtilizationRule>();

		for (final CPUUtilizationRule rule : rules) {
			Integer waitForVm = this.waitForVMStartInSecMap.get(rule);
			if (waitForVm != null) {
				waitForVm--;
				if (waitForVm == 0) {
					this.waitForVMStartInSecMap.put(rule, null);
				} else {
					this.waitForVMStartInSecMap.put(rule, waitForVm);
					continue;
				}
			}

			final Long provisioningStartedInSec = this.provisioningStartedInSecMap.get(rule);
			if (provisioningStartedInSec == null) {
				this.setProvisionStarted(rule, 0L);
			}

			if (this.evalConitionRelation(rule.getUtilizationBorder(), rule.getRelation(),
					curOverallCPUUtilizationEvent.getOverallCPUUtilization())) {
				final long timestampInSec = (curOverallCPUUtilizationEvent.getTimestampMillis() / 1000);

				if (provisioningStartedInSec == 0L) {
					this.setProvisionStarted(rule, timestampInSec);
				} else {
					if ((timestampInSec - provisioningStartedInSec) > rule.getDurationInSec()) {
						result.add(rule);
						this.setProvisionStarted(rule, 0L);
						this.waitForVMStartInSecMap.put(rule, OverallCPUUtilizationRuleEngine.DefaultStartOfVmInSec);
						break;
					}
				}
			} else {
				this.setProvisionStarted(rule, 0L);
			}

		}

		return result;
	}

	private void setProvisionStarted(final CPUUtilizationRule rule, final long value) {
		this.provisioningStartedInSecMap.put(rule, value);
	}

	private boolean evalConitionRelation(final double utilBorder,
			final Relation relation, final double currentUtil) {

		if (relation == Relation.Greater) {
			return currentUtil > utilBorder;
		}

		if (relation == Relation.GreaterOrEqual) {
			return currentUtil >= utilBorder;
		}

		if (relation == Relation.Equal) {
			if (Double.compare(currentUtil, utilBorder) == 0) {
				return true;
			}
			return Math.abs(currentUtil - utilBorder) <= 0.01;
		}

		if (relation == Relation.Less) {
			return currentUtil < utilBorder;
		}

		if (relation == Relation.LessOrEqual) {
			return currentUtil <= utilBorder;
		}

		return false;
	}
}