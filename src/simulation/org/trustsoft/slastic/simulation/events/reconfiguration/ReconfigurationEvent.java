package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

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
	final public void eventRoutine(){
		if(this.eventRoutine2()) {
			// TODO if dereplication wait for component empty event
			ModelManager.getInstance().getReconfigurationController().operationFinished(this.reconfOp);
		} else {
			ModelManager.getInstance().getReconfigurationController().operationFailed(this.reconfOp);
		}
	}

	abstract public boolean eventRoutine2();

	public final SLAsticReconfigurationOpType getReconfOp() {
		return this.reconfOp;
	}

}
