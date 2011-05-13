package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

class WorkloadIntensityEventWorker implements Runnable {
	private final WorkloadIntensityRuleEngine ruleEngine;
	private final ConfigurationManager configurationManager;

	public WorkloadIntensityEventWorker(
			final WorkloadIntensityRuleEngine ruleEngine,
			final ConfigurationManager configurationManager) {
		this.ruleEngine = ruleEngine;
		this.configurationManager = configurationManager;
	}

	@Override
	public void run() {
		final int requestedNumNodes = this.ruleEngine.nextNumNodes();

		if ((requestedNumNodes == -1)
				|| (requestedNumNodes == this.configurationManager
						.getCurrentNumNodes())) {
			return; // nothing to do
		}

		this.configurationManager.reconfigure(requestedNumNodes);
	}
}