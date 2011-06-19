package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

/**
 * 
 * 
 * @author Andre van Hoorn
 *
 */
class WorkloadIntensityEventWorker implements Runnable {
	private final WorkloadIntensityRuleEngine ruleEngine;
	private final ConfigurationManager configurationManager;

	// TODO: Field AssemblyComponent

	public WorkloadIntensityEventWorker(
			final WorkloadIntensityRuleEngine ruleEngine,
			final ConfigurationManager configurationManager) {
		this.ruleEngine = ruleEngine;
		this.configurationManager = configurationManager;
	}

	/**
	 * Note that this method doesn't need to be thread-safe, as there's only one
	 * worker thread executing the {@link WorkloadIntensityEventWorker}s. Also
	 * the {@link ConfigurationManager#reconfigure(int)} is only called by this
	 * method.
	 */
	@Override
	public void run() {
		// TODO: pass AssemblyComponent
		// TODO: receive Baseline object
		final int requestedNumNodes = this.ruleEngine.nextNumNodes();

		// TODO: This logic should be handled by the configurationManager
		if ((requestedNumNodes == -1)
				|| (requestedNumNodes == this.configurationManager
						.getCurrentNumNodes())) {
			return; // nothing to do
		}

		// TODO: evaluate return value (to be implemented)
		this.configurationManager.reconfigure(requestedNumNodes);
		// TODO: commit Baseline to ruleEngine on success
	}
}