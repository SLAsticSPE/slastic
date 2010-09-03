package org.trustsoft.slastic.simulation.listeners;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

public interface ReconfEventListener {

	abstract public void notifyPlanDone(SLAsticReconfigurationPlan plan);

	abstract public void notifyOpFailed(SLAsticReconfigurationPlan plan,
			SLAsticReconfigurationOpType reconfOp);

	abstract public void notifyPlanFailed(SLAsticReconfigurationPlan plan);

}
