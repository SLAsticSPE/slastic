package org.trustsoft.slastic.simulation.software.controller.cfframes;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingBehaviour;

public class LoopFrame extends CFFrame {

	private final int max;
	private int i = 0;

	public LoopFrame(final ResourceDemandingBehaviour seff,
			final AbstractAction headAction, final String asmContext,
			final int iterations) {
		super(seff, headAction, asmContext);
		// TODO Auto-generated constructor stub
		this.max = iterations;
	}

	/**
	 * @return the iterations
	 */
	public final int getIterations() {
		return this.max;
	}

	public int inc() {
		return ++this.i;
	}
}
