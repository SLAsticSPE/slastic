package org.trustsoft.slastic.simulation.software.controller.cfframes;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;

public class CFFrame {
	private final ResourceDemandingBehaviour seff;

	private AbstractAction action;

	private final String asmContextCurrent;

	public CFFrame(final ResourceDemandingBehaviour seff,
			final AbstractAction headAction, final String asmContext) {
		this.seff = seff;
		this.action = headAction;
		this.asmContextCurrent = asmContext;
	}

	/**
	 * @return the seff
	 */
	public final ResourceDemandingBehaviour getSeff() {
		return this.seff;
	}

	/**
	 * @return the action
	 */
	public final AbstractAction getAction() {
		return this.action;
	}

	/**
	 * @return the asmContextCurrent
	 */
	public final String getAsmContextCurrent() {
		return this.asmContextCurrent;
	}

	public final void setAction(final AbstractAction action) {
		this.action = action;
	}
}
