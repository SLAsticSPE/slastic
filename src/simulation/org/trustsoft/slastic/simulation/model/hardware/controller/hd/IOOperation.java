package org.trustsoft.slastic.simulation.model.hardware.controller.hd;

import org.trustsoft.slastic.simulation.model.hardware.controller.engine.AbstractSchedulableProcess;
import org.trustsoft.slastic.simulation.software.controller.controlflow.InternalActionEvent;

import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class IOOperation extends AbstractSchedulableProcess {

	public IOOperation(final Model owner, final String name, final boolean showInTrace, final long ticks, final InternalActionEvent belongs) {
		super(owner, name, showInTrace, ticks, belongs);
	}

}
