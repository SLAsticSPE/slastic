package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public final class DelocationEvent extends ReconfigurationEvent {

	public DelocationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean eventRoutine2() {
		return ModelManager
				.getInstance()
				.getHwCont()
				.delocate(
						((NodeDeAllocationOP) this.getReconfOp()).getNode()
								.getId());
	}

}
