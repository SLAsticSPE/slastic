package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimPlanSender extends AbstractReconfigurationManagerComponent implements ReconfEventListener {

    private static final Log log = LogFactory.getLog(SLAsticSimPlanSender.class);
    private ReconfigurationPipe reconfigurationPipe;
    private static final String PROPERTY_PIPE_NAME = "pipeName";
    private String pipeName;

    @Override
    public boolean init() {
        this.pipeName = super.getInitProperty(PROPERTY_PIPE_NAME);
        if (this.pipeName == null || this.pipeName.length() == 0) {
            log.error("Invalid or missing pipeName value for property '" + PROPERTY_PIPE_NAME + "'");
            throw new IllegalArgumentException("Invalid or missing pipeName value:" + this.pipeName);
        }
        this.reconfigurationPipe = ReconfigurationPipeBroker.getInstance().acquirePipe(pipeName);
        if (this.reconfigurationPipe == null) {
            log.error("Failed to get pipe with name:" + this.pipeName);
            throw new IllegalArgumentException("Failed to get pipe with name:" + this.pipeName);
        }
        log.info("Connected to pipe '" + this.pipeName + "'" + " (" + this.reconfigurationPipe + ")");
        return true;
    }

    public boolean execute() {
        return true;
    }

    public void terminate(final boolean error) {
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException {
        try {
            this.reconfigurationPipe.reconfigure(plan, this);
        } catch (ReconfigurationPipeException ex) {
            log.error("reconfiguration failed", ex);
            throw new ReconfigurationException("reconfiguration failed", ex);
        }
    }

    public void notifyPlanDone(SLAsticReconfigurationPlan plan) {
        log.info("notifyPlanDone received");
    }

    public void notifyOpFailed(SLAsticReconfigurationPlan plan, SLAsticReconfigurationOpType reconfOp) {
        log.info("notifyOpFailed received");
    }
}
