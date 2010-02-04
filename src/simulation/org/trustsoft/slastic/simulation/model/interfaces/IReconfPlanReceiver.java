package org.trustsoft.slastic.simulation.model.interfaces;

import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

public interface IReconfPlanReceiver {

	public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener);

        // TODO (von Andre): remove?
	public void reconfigure(SLAsticReconfigurationPlan plan);

        // TODO (von Andre): remove?
	public void addReconfigurationEventListener(ReconfEventListener listener);

        // TODO (von Andre): remove?
	public void removeReconfigurationEventListener(ReconfEventListener listener);

}
