package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.NodeDeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public final class DelocationEvent extends AbstractReconfigurationEvent {

	public DelocationEvent(final Model owner, final String name, final boolean showInTrace, final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean concreteEventRoutine() {
		return ModelManager.getInstance().getHardwareController().delocate(((NodeDeAllocationOP) this.getReconfOp()).getNode().getId());
	}
}
