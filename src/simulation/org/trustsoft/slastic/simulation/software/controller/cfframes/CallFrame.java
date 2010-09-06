package org.trustsoft.slastic.simulation.software.controller.cfframes;

import org.trustsoft.slastic.simulation.software.controller.controlflow.ExternalCallEnterNode;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;

public class CallFrame extends CFFrame {

	private final ExternalCallEnterNode ece;

	public CallFrame(final ResourceDemandingBehaviour seff,
			final AbstractAction headAction, final String asmContext,
			final ExternalCallEnterNode ece) {
		super(seff, headAction, asmContext);
		this.ece = ece;
	}

	/**
	 * @return the ece
	 */
	public final ExternalCallEnterNode getEce() {
		return this.ece;
	}
}
