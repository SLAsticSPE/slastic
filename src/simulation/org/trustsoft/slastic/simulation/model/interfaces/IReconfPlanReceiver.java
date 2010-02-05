package org.trustsoft.slastic.simulation.model.interfaces;

import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

public interface IReconfPlanReceiver {

	public void reconfigure(SLAsticReconfigurationPlan plan);

	public void addReconfigurationEventListener(ReconfEventListener listener);

	public void removeReconfigurationEventListener(ReconfEventListener listener);
}
