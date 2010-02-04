package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;

import desmoj.core.simulator.Model;

public class IOOperation extends AbstractSchedulableProcess {

	public IOOperation(final Model owner, final String name,
			final boolean showInTrace, final long ticks) {
		super(owner, name, showInTrace, ticks);
	}

}
