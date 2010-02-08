package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class TickEventGenerator extends ExternalEvent {

	private final AbstractScheduler<?, ? extends AbstractSchedulableProcess> scheduler;
	static private Log log = LogFactory.getLog(TickEventGenerator.class);

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final ProcessingResource<? extends AbstractSchedulableProcess> ressource) {
		super(owner, name, showInTrace);
		this.scheduler = ressource.getScheduler();
	}

	/**
	 * Schedule next tick event
	 * 
	 * @see desmoj.core.simulator.ExternalEvent#eventRoutine()
	 */
	@Override
	public void eventRoutine() {
		this.scheduler.tick();
		TickEventGenerator.log.info("CPU ticked");
		if (this.scheduler.isIdle()) {
			return;
		} else {
			final SimTime nextTick = this.scheduler.tick();
			if (nextTick != null) {
				this.schedule(nextTick);
			}
		}
	}

	public void resume(final SimTime tick) {
		// TODO make some noise for "here comes the time" versus simtime
		this.schedule(tick);
	}

}
