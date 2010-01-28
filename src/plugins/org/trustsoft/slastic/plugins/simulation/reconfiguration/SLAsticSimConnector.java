package org.trustsoft.slastic.plugins.simulation.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import java.util.Properties;
import org.trustsoft.slastic.reconfiguration.AbstractSLAsticReconfigurationManager;
import org.trustsoft.slastic.reconfiguration.SLAsticReconfigurationException;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticSimConnector extends AbstractSLAsticReconfigurationManager {

    private ReconfigurationPipe reconfigurationPipe;

    @Override
    // TODO: rename setProperties to init(..)
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        // TODO: acquire pipe
    }

    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void terminate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) throws SLAsticReconfigurationException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
