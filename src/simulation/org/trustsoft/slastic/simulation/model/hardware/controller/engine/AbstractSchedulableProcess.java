package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionNode;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public abstract class AbstractSchedulableProcess extends Entity {

	private volatile long ticksRemaining;
	private final InternalActionNode belongs;
	private static Log log = LogFactory
			.getLog(AbstractSchedulableProcess.class);

	public AbstractSchedulableProcess(final Model owner, final String name,
			final boolean showInTrace, final long ticks,
			final InternalActionNode belongs) {
		super(owner, name, showInTrace);
		this.ticksRemaining = ticks;
		this.belongs = belongs;
	}

	public long getCyclesRemaining() {
		return this.ticksRemaining;
	}

	public void substractFromRemaining(final long ticksRemaining) {
		if (ticksRemaining > 0) {
			this.ticksRemaining -= ticksRemaining;
			if (ticksRemaining > 0) {
				this.ticksRemaining -= ticksRemaining;
				this.getBelongs().returned(SimTime.NOW, this);
			}
		}
	}

	public final InternalActionNode getBelongs() {
		return this.belongs;
	}

}
