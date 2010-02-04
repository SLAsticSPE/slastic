package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public class AllocationEvent extends ReconfigurationEvent {

	private final String serverId;

	public AllocationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp, final String serverId) {
		super(owner, name, showInTrace, reconfOp);
		this.serverId = serverId;
	}

	@Override
	public void eventRoutine() {
		ModelManager.getInstance().getHwCont().allocate(serverId);
		ModelManager.getInstance().getReconfController().operationFinished(
				getReconfOp());
	}

}
