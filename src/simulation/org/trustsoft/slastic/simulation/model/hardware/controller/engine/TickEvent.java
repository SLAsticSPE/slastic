package org.trustsoft.slastic.simulation.model.hardware.controller.engine;

import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class TickEvent extends ExternalEvent {

	private final TickEventGenerator teg;

	public TickEvent(final Model owner, final String name, final boolean showInTrace, final TickEventGenerator teg) {
		super(owner, name, showInTrace);
		this.teg = teg;
	}

	@Override
	public void eventRoutine() {
		this.teg.tick();
	}

}
