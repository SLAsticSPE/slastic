package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionNode;

import desmoj.core.simulator.Model;

public class CPUSchedulableProcess extends AbstractSchedulableProcess {

	private final InternalActionNode belongs;

	public CPUSchedulableProcess(final Model owner, final boolean showInTrace,
			final long ticks, final InternalActionNode belongs) {
		super(owner, "CPU", showInTrace, ticks);
		this.belongs = belongs;
	}

	public InternalActionNode getBelongs() {
		return belongs;
	}

}
