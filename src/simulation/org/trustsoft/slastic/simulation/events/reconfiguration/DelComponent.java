package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.ComponentDeReplicationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public class DelComponent extends ReconfigurationEvent {

	public DelComponent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean eventRoutine2() {
		// FIXME check that no users are left
		ModelManager.getInstance().getAllocCont()
				.del(
						((ComponentDeReplicationOP) super.getReconfOp())
								.getComponent());
		return true;
	}

}
