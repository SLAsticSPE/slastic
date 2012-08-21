package org.trustsoft.slastic.simulation.model.interfaces;

import org.trustsoft.slastic.simulation.listeners.IReconfigurationEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IReconfigurationPlanReceiver {

	public void reconfigure(SLAsticReconfigurationPlan plan);

	public void addReconfigurationEventListener(IReconfigurationEventListener listener);

	public void removeReconfigurationEventListener(IReconfigurationEventListener listener);
}
