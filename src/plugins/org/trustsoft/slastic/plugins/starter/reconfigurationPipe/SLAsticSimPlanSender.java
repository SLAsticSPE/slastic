package org.trustsoft.slastic.plugins.starter.reconfigurationPipe;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import java.util.Properties;
import org.trustsoft.slastic.reconfiguration.AbstractSLAsticReconfigurationManager;
import org.trustsoft.slastic.reconfiguration.SLAsticReconfigurationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimPlanSender extends AbstractSLAsticReconfigurationManager implements ReconfEventListener {

    private static final Log log = LogFactory.getLog(SLAsticSimPlanSender.class);
    private ReconfigurationPipe reconfigurationPipe;
    private static final String PROPERTY_PIPE_NAME = "pipeName";
    private String pipeName;

    @Override
    public void init(Properties properties) {
        super.init(properties);
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
    }

    public boolean execute() {
        return true;
    }

    public void terminate() {
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) throws SLAsticReconfigurationException {
        try {
            this.reconfigurationPipe.reconfigure(plan, this);
        } catch (ReconfigurationPipeException ex) {
            log.error("reconfiguration failed", ex);
            throw new SLAsticReconfigurationException("reconfiguration failed", ex);
        }
    }

    public void notifyPlanDone(SLAsticReconfigurationPlan plan) {
        log.info("notifyPlanDone received");
    }

    public void notifyOpFailed(SLAsticReconfigurationPlan plan, SLAsticReconfigurationOpType reconfOp) {
        log.info("notifyOpFailed received");
    }
}
