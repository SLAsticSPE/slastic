package org.trustsoft.slastic.simulation.events.reconfiguration;

import org.trustsoft.slastic.simulation.model.ModelManager;

import ReconfigurationPlanModel.NodeAllocationOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
public class AllocationEvent extends AbstractReconfigurationEvent {

	public AllocationEvent(final Model owner, final String name, final boolean showInTrace, final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
	}

	@Override
	public boolean concreteEventRoutine() {
		return ModelManager.getInstance().getHardwareController().allocate(((NodeAllocationOP) this.getReconfOp()).getNode().getId());
	}

}
