package org.trustsoft.slastic.simulation.software.controller;

import org.trustsoft.slastic.simulation.config.Constants;

import de.uka.ipd.sdq.pcm.seff.AbstractAction;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

public class ControlFlowAction<T extends AbstractAction> extends ExternalEvent {

	private final boolean resourceDemanding;
	private final T action;

	public ControlFlowAction(final Model model, final String name,
			final T action, final boolean resourceDemanding) {
		super(model, name, Constants.DEBUG);
		this.resourceDemanding = resourceDemanding;
		this.action = action;
	}

	public boolean isResourceDemanding() {
		return resourceDemanding;
	}

	public T getAction() {
		return action;
	}

	@Override
	public void eventRoutine() {
		// TODO Auto-generated method stub

	}

}
