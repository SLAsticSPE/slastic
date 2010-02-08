package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionNode;

import desmoj.core.simulator.Model;

public class IOOperation extends AbstractSchedulableProcess {

	public IOOperation(final Model owner, final String name,
			final boolean showInTrace, final long ticks,
			final InternalActionNode belongs) {
		super(owner, name, showInTrace, ticks, belongs);
	}

}
