package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;
import org.trustsoft.slastic.simulation.model.interfaces.IReconfPlanReceiver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * TODO: there should be an AbstractReconfPlanReceiver including the
 *       listener management.
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimPlanReceiver implements IReconfigurationPipePlanReceiver {
    private static final Log log = LogFactory.getLog(SLAsticSimPlanReceiver.class);

    private IReconfPlanReceiver delegate;
    private ReconfigurationPipe pipe;
    private String pipeName;

    public SLAsticSimPlanReceiver (final String pipeName, final IReconfPlanReceiver delegate){
        this.initPipe(pipeName);
        this.delegate = delegate;
    }

    private void initPipe(String pipeName) throws IllegalArgumentException{
        this.pipeName = pipeName;
        this.pipe = ReconfigurationPipeBroker.getInstance().acquirePipe(pipeName);
        if (pipe == null){
            log.error("Failed to get Pipe with name " + this.pipeName);
            throw new IllegalArgumentException("Failed to get Pipe with name " + this.pipeName);
        }
        pipe.setPlanReceiver(this);
    }

    public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener) {
        log.info("Delegating reconfiguration plan" + plan);
        this.delegate.reconfigure(plan, listener);
    }

    public void addReconfigurationEventListener(ReconfEventListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeReconfigurationEventListener(ReconfEventListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reconfigure(SLAsticReconfigurationPlan plan) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
