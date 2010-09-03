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
		try {
			ModelManager.getInstance().getAllocCont().del(
					((ComponentDeReplicationOP) super.getReconfOp())
							.getComponent());
		} catch (final Exception e) {
			return false;
		}
		return true;
	}

}
