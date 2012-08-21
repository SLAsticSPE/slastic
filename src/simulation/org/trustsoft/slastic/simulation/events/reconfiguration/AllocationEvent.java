package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public class AllocationEvent extends ReconfigurationEvent {

	public AllocationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean eventRoutine2() {
		return ModelManager.getInstance().getHardwareController().allocate(
				((NodeAllocationOP) this.getReconfOp()).getNode().getId());
	}

}
