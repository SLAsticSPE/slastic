package org.trustsoft.slastic.simulation.events.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public final class ReplicationEvent extends ReconfigurationEvent {

	public ReplicationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public void eventRoutine() {
		// TODO Auto-generated method stub

	}

}
