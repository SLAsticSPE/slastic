package org.trustsoft.slastic.simulation.software.controller.cfframes;

import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallEnterEvent;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class CallFrame extends CFFrame {

	private final ExternalCallEnterEvent ece;

	public CallFrame(final ResourceDemandingBehaviour seff, final AbstractAction headAction, final String asmContext, final ExternalCallEnterEvent ece) {
		super(seff, headAction, asmContext);
		this.ece = ece;
	}

	/**
	 * @return the ece
	 */
	public final ExternalCallEnterEvent getEce() {
		return this.ece;
	}
}
