package org.trustsoft.slastic.simulation.events.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.ExternalEvent;
import desmoj.core.simulator.Model;

public abstract class ReconfigurationEvent extends ExternalEvent {
	private final SLAsticReconfigurationOpType reconfOp;

	public ReconfigurationEvent(final Model owner, final String name,
			final boolean showInTrace,
			final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace);
		this.reconfOp = reconfOp;
	}

	@Override
	abstract public void eventRoutine();

	public final SLAsticReconfigurationOpType getReconfOp() {
		return reconfOp;
	}

}
