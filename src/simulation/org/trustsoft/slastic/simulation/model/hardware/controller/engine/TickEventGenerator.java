package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class TickEventGenerator {

	private final AbstractScheduler<?, ? extends AbstractSchedulableProcess> scheduler;
	private final String name;
	private final Model owner;
	static private Log log = LogFactory.getLog(TickEventGenerator.class);

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final ProcessingResource<? extends AbstractSchedulableProcess> ressource) {
		this.owner = owner;
		this.name = name;
		this.scheduler = ressource.getScheduler();
	}

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final AbstractScheduler<?, ? extends AbstractSchedulableProcess> sched) {
		this.owner = owner;
		this.name = name;
		this.scheduler = sched;
	}

	/**
	 * Schedule next tick event
	 * 
	 * @see desmoj.core.simulator.ExternalEvent#eventRoutine()
	 */
	public void tick() {
		TickEventGenerator.log.info("CPU ticks at " + this.owner.currentTime());
		// this.scheduler.tick();
		if (this.scheduler.isIdle()) {
			return;
		} else {
			final SimTime nextTick = this.scheduler.tick();
			if (nextTick != null) {
				final TickEvent te = new TickEvent(this.owner, this.name,
						Constants.DEBUG, this);
				te.schedule(nextTick);
			}
		}
	}

	public void resume() {
		final TickEvent te = new TickEvent(this.owner, this.name,
				Constants.DEBUG, this);
		te.schedule(SimTime.NOW);
	}

}
