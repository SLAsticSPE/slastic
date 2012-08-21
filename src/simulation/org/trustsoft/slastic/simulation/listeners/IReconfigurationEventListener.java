package org.trustsoft.slastic.simulation.listeners;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * 
 * @author Robert von Massow
 * 
 */
public interface IReconfigurationEventListener {

	public void notifyPlanDone(SLAsticReconfigurationPlan plan);

	public void notifyOpFailed(SLAsticReconfigurationPlan plan, SLAsticReconfigurationOpType reconfOp);

	public void notifyPlanFailed(SLAsticReconfigurationPlan plan);

}
