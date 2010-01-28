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
    // TODO: rename init to init(..)
    public void init(Properties properties) {
        super.init(properties);
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
