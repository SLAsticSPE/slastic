package org.trustsoft.slastic.control.components;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicSLAsticControl extends AbstractSLAsticControl {

    public void update(AbstractKiekerMonitoringRecord record) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getRecordTypeSubscriptionList() {
        // TODO: delegate to ModelManager/Updater
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord monitoringRecord)
            throws RecordConsumerExecutionException {
        this.getModelUpdater().update(monitoringRecord);

    }
}
