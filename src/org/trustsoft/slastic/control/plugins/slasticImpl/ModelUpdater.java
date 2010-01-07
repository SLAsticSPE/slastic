package org.trustsoft.slastic.control.plugins.slasticImpl;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ModelUpdater extends AbstractSLAsticModelUpdater {

    private static final Log log = LogFactory.getLog(ModelUpdater.class);

   public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    public void consumeMonitoringRecord(AbstractKiekerMonitoringRecord record) {

    }

    public void handleSLAsticEvent(ISLAsticEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
