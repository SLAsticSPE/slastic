package org.trustsoft.slastic.simulation;

import desmoj.core.simulator.Experiment;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class DynamicSimExpirement extends Experiment {

	private final StopCondition stopCondition;

	public DynamicSimExpirement(final String name, final String referenceTime, final int referenceUnit, final StopCondition stopCondition) {
		super(name, referenceTime, referenceUnit);
		this.stopCondition = stopCondition;
	}

	public boolean stopRun() {
		return this.stopCondition.setStopped(true);
	}
}
