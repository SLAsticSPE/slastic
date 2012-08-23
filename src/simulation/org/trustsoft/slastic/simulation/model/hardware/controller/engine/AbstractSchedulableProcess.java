package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionEvent;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public abstract class AbstractSchedulableProcess extends Entity {
	private static Log LOG = LogFactory.getLog(AbstractSchedulableProcess.class);

	private volatile long ticksRemaining;
	private final InternalActionEvent belongs;

	public AbstractSchedulableProcess(final Model owner, final String name, final boolean showInTrace, final long ticks, final InternalActionEvent belongs) {
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
			if (this.ticksRemaining <= 0) {
				LOG.info("Done Processing " + this + " at sim time " + this.currentTime());
				this.getBelongs().returned(this.currentTime(), this);
			}
		}
	}

	public final InternalActionEvent getBelongs() {
		return this.belongs;
	}

}
