package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

/**
 *
 * @author Andre van Hoorn
 */
public class ReconfigurationPipe {

    private static final Log log = LogFactory.getLog(ReconfigurationPipe.class);
    private final String name;
    private IReconfigurationPipePlanReceiver planReceiver;
    private boolean closed;

    /** No construction employing default constructor */
    private ReconfigurationPipe() {
        name = null;
    }

    public ReconfigurationPipe(final String name) {
        this.name = name;
    }

    public void setPlanReceiver(final IReconfigurationPipePlanReceiver planReceiver) {
        this.planReceiver = planReceiver;
        log.info("PipeReader initialized");
    }

    public void reconfigure(SLAsticReconfigurationPlan plan, ReconfEventListener listener) throws ReconfigurationPipeException {
        if (this.closed) {
            log.error("trying to write to closed pipe");
            throw new ReconfigurationPipeException("trying to write to closed pipe");
        }
        this.planReceiver.reconfigure(plan, listener);
    }

    public String getName() {
        return this.name;
    }

    public void close() {
        this.closed = true;
    }
}
