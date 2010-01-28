package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

/**
 *
 * @author Andre van Hoorn
 */
public interface IReconfigurationPipePlanReceiver {
    public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener);
}
