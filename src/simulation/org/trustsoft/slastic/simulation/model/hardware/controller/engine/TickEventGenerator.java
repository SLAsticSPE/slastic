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

	public TickEventGenerator(
			final Model owner,
			final String name,
			final boolean showInTrace,
			final AbstractScheduler<?, ? extends AbstractSchedulableProcess> sched) {
		super(owner, name, showInTrace);
		this.scheduler = sched;
	}

	/**
	 * Schedule next tick event
	 * 
	 * @see desmoj.core.simulator.ExternalEvent#eventRoutine()
	 */
	@Override
	public void eventRoutine() {
		TickEventGenerator.log.info("CPU ticks at " + this.currentTime());
		this.scheduler.tick();
		if (this.scheduler.isIdle()) {
			return;
		} else {
			final SimTime nextTick = this.scheduler.tick();
			if (nextTick != null) {
				final TickEventGenerator teg = new TickEventGenerator(this
						.getModel(), this.getName(), super
						.currentlySendTraceNotes(), this.scheduler);
				teg.schedule(nextTick);
			}
		}
	}

	public void resume(final SimTime tick) {
		// TODO make some noise for "here comes the time" versus simtime
		final TickEventGenerator teg = new TickEventGenerator(this.getModel(),
				this.getName(), super.currentlySendTraceNotes(), this.scheduler);
		teg.schedule(tick);
	}

}
