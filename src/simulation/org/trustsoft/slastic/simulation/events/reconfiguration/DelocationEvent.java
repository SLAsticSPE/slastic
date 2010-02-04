package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

public final class DelocationEvent extends ReconfigurationEvent {

	private final String serverId;

	public DelocationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp, final String serverId) {
		super(owner, name, showInTrace, reconfOp);
		this.serverId = serverId;

	}

	@Override
	public void eventRoutine() {
		if (ModelManager.getInstance().getHwCont().delocate(serverId)) {
			ModelManager.getInstance().getReconfController().operationFinished(
					getReconfOp());
		} else {
			ModelManager.getInstance().getReconfController().operationFailed(
					getReconfOp());
		}
	}

}
