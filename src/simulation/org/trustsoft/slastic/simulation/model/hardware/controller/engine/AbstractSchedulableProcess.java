package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public abstract class AbstractSchedulableProcess extends Entity {

	private volatile long ticksRemaining;

	public AbstractSchedulableProcess(final Model owner, final String name,
			final boolean showInTrace, final long ticks) {
		super(owner, name, showInTrace);
		ticksRemaining = ticks;
	}

	public long getCyclesRemaining() {
		return ticksRemaining;
	}

	public void substractFromRemaining(final long ticksRemaining) {
		if (ticksRemaining > 0) {
			this.ticksRemaining -= ticksRemaining;
		}
	}

}
