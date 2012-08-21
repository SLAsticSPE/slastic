package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.simulation.listeners.IReconfigurationEventListener;

/**
 *
 * @author Andre van Hoorn
 */
public interface IReconfigurationPipePlanReceiver {
    public void reconfigure(SLAsticReconfigurationPlan plan, IReconfigurationEventListener listener);
}
