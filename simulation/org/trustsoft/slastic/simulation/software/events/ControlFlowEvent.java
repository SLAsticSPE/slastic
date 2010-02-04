package org.trustsoft.slastic.simulation.software.events;

import org.trustsoft.slastic.simulation.config.Constants;

import desmoj.core.simulator.Entity;
import desmoj.core.simulator.Event;
import desmoj.core.simulator.Model;

public abstract class ControlFlowEvent<T extends Entity> extends Event<T> {

	private boolean resourceDemanding;

	public ControlFlowEvent(final Model owner, final String name) {
		super(owner, name, Constants.DEBUG);
	}

	@Override
	public abstract void eventRoutine(final Entity arg0);

	public boolean isResourceDemanding() {
		return resourceDemanding;
	}

	public void setResourceDemanding(final boolean resourceDemanding) {
		this.resourceDemanding = resourceDemanding;
	}

}
