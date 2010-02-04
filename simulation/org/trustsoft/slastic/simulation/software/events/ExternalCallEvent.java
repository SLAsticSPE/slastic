package org.trustsoft.slastic.simulation.software.events;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class ExternalCallEvent extends ControlFlowEvent {

	public ExternalCallEvent(final Model owner, final String name) {
		super(owner, name);
	}

	@Override
	public void eventRoutine(final Entity arg0) {
		// TODO Auto-generated method stub

	}

}
