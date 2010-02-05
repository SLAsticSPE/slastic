package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import java.util.HashSet;
import java.util.Set;
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
    private Set<ReconfEventListener> registeredListeners = new HashSet<ReconfEventListener>();

    public SLAsticSimPlanReceiver (final String pipeName, final IReconfPlanReceiver delegate){
        this.pipeName = pipeName;
        this.delegate = delegate;
    }

    public void execute(){
        this.connect();
    }

    private void connect() throws IllegalArgumentException{
        this.pipe = ReconfigurationPipeBroker.getInstance().acquirePipe(pipeName);
        if (pipe == null){
            log.error("Failed to get Pipe with name " + this.pipeName);
            throw new IllegalArgumentException("Failed to get Pipe with name " + this.pipeName);
        }
        pipe.setPlanReceiver(this);
    }

    public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener) {
        log.info("Delegating reconfiguration plan" + plan);
        /* Register only once */
        if (!registeredListeners.contains(listener)){
            this.registeredListeners.add(listener);
            this.delegate.addReconfigurationEventListener(listener);
        }
        this.delegate.reconfigure(plan, listener);
    }
}
