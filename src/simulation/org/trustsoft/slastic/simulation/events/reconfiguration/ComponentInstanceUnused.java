package org.trustsoft.slastic.simulation.events.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import desmoj.core.simulator.Model;

/**
 * 
 * @author Robert von Massow
 * 
 */
// TODO: It seems that this class is not used at all
public class ComponentInstanceUnused extends AbstractReconfigurationEvent {

	public ComponentInstanceUnused(final Model owner, final String name, final boolean showInTrace, final SLAsticReconfigurationOpType reconfOp) {
		super(owner, name, showInTrace, reconfOp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean concreteEventRoutine() {
		// TODO Auto-generated method stub
		return false;
	}

}
