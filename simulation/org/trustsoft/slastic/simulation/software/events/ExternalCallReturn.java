package org.trustsoft.slastic.simulation.software.events;

import org.trustsoft.slastic.simulation.user.model.CallNode;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Model;

public class ExternalCallReturn extends ControlFlowEvent {

	private final CallNode callNode;

	public ExternalCallReturn(final Model owner, final String name,
			final CallNode call) {
		super(owner, name);
		callNode = call;
	}

	@Override
	public void eventRoutine(final Entity arg0) {
		callNode.callReturned();
	}

}
