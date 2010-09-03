package org.trustsoft.slastic.simulation.events.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public class ComponentInstanceUnused extends ReconfigurationEvent {

	public ComponentInstanceUnused(final Model owner, final String name,
			final boolean showInTrace, final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean eventRoutine2() {
		// TODO Auto-generated method stub
		return false;
	}

}
