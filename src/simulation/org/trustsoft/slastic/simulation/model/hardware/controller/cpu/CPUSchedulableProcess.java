package org.trustsoft.slastic.simulation.model.hardware.controller.cpu;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionEvent;

import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CPUSchedulableProcess extends AbstractSchedulableProcess {

	public CPUSchedulableProcess(final Model owner, final boolean showInTrace, final long ticks, final InternalActionEvent belongs) {
		super(owner, "CPU", showInTrace, ticks, belongs);
	}

}
