package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;
import desmoj.core.simulator.SimTime;

public class TickEventGenerator extends ExternalEvent {

	private final AbstractScheduler<?, ? extends AbstractSchedulableProcess> scheduler;

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final ProcessingResource<? extends AbstractSchedulableProcess> ressource) {
		super(owner, name, showInTrace);
		scheduler = ressource.getScheduler();
	}

	/**
	 * Schedule next tick event
	 * 
	 * @see desmoj.core.simulator.ExternalEvent#eventRoutine()
	 */
	@Override
	public void eventRoutine() {
		scheduler.tick();
		if (scheduler.isIdle()) {
			return;
		} else {
			final SimTime nextTick = scheduler.tick();
			if (nextTick != null) {
				schedule(nextTick);
			}
		}
	}

	public void resume(final SimTime tick) {
		// TODO make some noise for "here comes the time" versus simtime
		schedule(tick);
	}

}
