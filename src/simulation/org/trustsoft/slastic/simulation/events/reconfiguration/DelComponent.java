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
	public void eventRoutine() {
		ModelManager.getInstance().getAllocCont()
				.del(
						((ComponentDeReplicationOP) super.getReconfOp())
								.getComponent());
	}

}
