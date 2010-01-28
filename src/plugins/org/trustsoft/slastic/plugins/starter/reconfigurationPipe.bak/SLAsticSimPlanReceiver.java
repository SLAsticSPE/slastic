package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;

/**
 * TODO: there should be an AbstractReconfPlanReceiver including the
 *       listener management.
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimPlanReceiver implements IReconfPlanReceiver {

    public void reconfigure(SLAsticReconfigurationPlan plan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addReconfigurationEventListener(ReconfEventListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeReconfigurationEventListener(ReconfEventListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
