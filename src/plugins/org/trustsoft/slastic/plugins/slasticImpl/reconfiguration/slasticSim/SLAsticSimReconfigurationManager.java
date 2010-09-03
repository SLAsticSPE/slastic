package org.trustsoft.slastic.plugins.slasticImpl.reconfiguration.slasticSim;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipe;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipeBroker;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipeException;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

/**
 *
 * @author voorn
 */
public class SLAsticSimReconfigurationManager extends AbstractReconfigurationManagerComponent implements ReconfEventListener {

    private ReconfigurationPipe reconfigurationPipe;
    private static final String PROPERTY_PIPE_NAME = "pipeName";
    private String pipeName;
    private static final Log log = LogFactory.getLog(SLAsticSimReconfigurationManager.class);

    @Override
    public boolean init() {
        this.pipeName = super.getInitProperty(SLAsticSimReconfigurationManager.PROPERTY_PIPE_NAME);
        if (this.pipeName == null || this.pipeName.length() == 0) {
            SLAsticSimReconfigurationManager.log.error("Invalid or missing pipeName value for property '"
                    + SLAsticSimReconfigurationManager.PROPERTY_PIPE_NAME + "'");
            throw new IllegalArgumentException(
                    "Invalid or missing pipeName value:" + this.pipeName);
        }
        this.reconfigurationPipe = ReconfigurationPipeBroker.getInstance().acquirePipe(this.pipeName);
        if (this.reconfigurationPipe == null) {
            SLAsticSimReconfigurationManager.log.error("Failed to get pipe with name:"
                    + this.pipeName);
            throw new IllegalArgumentException("Failed to get pipe with name:"
                    + this.pipeName);
        }
        SLAsticSimReconfigurationManager.log.info("Connected to pipe '" + this.pipeName
                + "'" + " (" + this.reconfigurationPipe + ")");
        return true;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(final boolean error) {
    }

    @Override
    public void doReconfiguration(final SLAsticReconfigurationPlan plan)
            throws ReconfigurationException {
        try {
            this.reconfigurationPipe.reconfigure(plan, this);
        } catch (final ReconfigurationPipeException ex) {
            SLAsticSimReconfigurationManager.log.error("reconfiguration failed", ex);
            throw new ReconfigurationException("reconfiguration failed", ex);
        }
    }

    @Override
    public void notifyPlanDone(final SLAsticReconfigurationPlan plan) {
        SLAsticSimReconfigurationManager.log.info("notifyPlanDone received");
    }

    @Override
    public void notifyOpFailed(final SLAsticReconfigurationPlan plan,
            final SLAsticReconfigurationOpType reconfOp) {
        SLAsticSimReconfigurationManager.log.warn("notifyOpFailed received");
    }

    @Override
    public void notifyPlanFailed(final SLAsticReconfigurationPlan plan) {
        SLAsticSimReconfigurationManager.log.warn("notifyOpFailed received");
    }
}
