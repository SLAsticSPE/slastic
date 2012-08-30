package kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.utilizationBased;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.cloud.slastic.control.adaptationPlanning.ConfigurationManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * @author Florian Fittkau
 * 
 */
class OverallCPUUtilizationEventWorker implements Runnable {
	private static final Log log = LogFactory.getLog(OverallCPUUtilizationEventWorker.class);

	private final OverallCPUUtilizationRuleEngine ruleEngine;
	private final ConfigurationManager configurationManager;

	private final ExecutionContainerType executionContainerType;

	private final AtomicReference<OverallCPUUtilizationEvent> overallCPUUtilizationEventRef;

	private final AssemblyComponent assemblyComponent;

	public OverallCPUUtilizationEventWorker(final AssemblyComponent assemblyComponent, final ExecutionContainerType executionContainerType, final OverallCPUUtilizationRuleEngine ruleEngine,
			final ConfigurationManager configurationManager,
			final AtomicReference<OverallCPUUtilizationEvent> overallCPUUtilizationEventEventRef) {
		this.assemblyComponent = assemblyComponent;
		this.executionContainerType = executionContainerType;
		this.ruleEngine = ruleEngine;
		this.configurationManager = configurationManager;
		this.overallCPUUtilizationEventRef = overallCPUUtilizationEventEventRef;
	}

	/**
	 * Note that this method doesn't need to be thread-safe, as there's only one
	 * worker thread executing the {@link OverallCPUUtilizationEventWorker}s.
	 * Also the {@link ConfigurationManager#reconfigure(int)} is only called by
	 * this method.
	 */
	@Override
	public void run() {
		// consume (get and reset) most recent cpu utilization event
		final OverallCPUUtilizationEvent curOverallCPUUtilization = this.overallCPUUtilizationEventRef.getAndSet(null);

		if (curOverallCPUUtilization == null) {
			return;
		}

		final List<CPUUtilizationRule> matchedRules = this.ruleEngine.evaluate(curOverallCPUUtilization);

		for (final CPUUtilizationRule cpuUtilizationRule : matchedRules) {
			
			final boolean success =
					this.configurationManager.reconfigureWithRelativeNumNodes(this.assemblyComponent, this.executionContainerType,
							cpuUtilizationRule.getRelativeNumNodes());
			if (!success) {
				OverallCPUUtilizationEventWorker.log
						.error(String
								.format(
										"Reconfiguration failed for execution contain type %s, and cpu rule %s ", this.executionContainerType, cpuUtilizationRule));
			}
		}
	}
}