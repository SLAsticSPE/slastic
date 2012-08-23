package org.trustsoft.slastic.simulation;

import desmoj.core.simulator.Condition;
import desmoj.core.simulator.Entity;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class StopCondition extends Condition {

	private boolean stopped;

	public boolean isStopped() {
		return this.stopped;
	}

	public boolean setStopped(final boolean stopped) {
		return this.stopped = stopped;
	}

	public StopCondition(final DynamicSimulationModel owner, final String name,
			final boolean showInTrace) {
		super(owner, name, showInTrace);
	}

	@Override
	public boolean check() {
		return this.stopped;
	}

	@Override
	public boolean check(final Entity arg0) {
		return this.stopped;
	}
}
