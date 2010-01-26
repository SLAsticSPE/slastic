package org.trustsoft.slastic.control;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventService;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicSLAsticControl extends AbstractSLAsticControl implements ISimpleSLAsticEventService{

    private static final Log log = LogFactory.getLog(BasicSLAsticControl.class);

//    public void init(String initString) throws IllegalArgumentException {
//        super.initVarsFromInitString(initString);
//        // we don't expect init properties so far, so just return.
//    }

    public void update(AbstractKiekerMonitoringRecord record) {
        try {
            this.consumeMonitoringRecord(record);
        } catch (RecordConsumerExecutionException ex) {
            log.error(ex);
        }
    }

    public String[] getRecordTypeSubscriptionList() {
        return null; // receive records of all types
    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord monitoringRecord)
            throws RecordConsumerExecutionException {
        this.getModelUpdater().consumeMonitoringRecord(monitoringRecord);
    }
}
