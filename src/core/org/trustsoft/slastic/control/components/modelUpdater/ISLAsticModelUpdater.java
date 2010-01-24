package org.trustsoft.slastic.control.components.modelUpdater;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.common.ISLAsticComponent;

public interface ISLAsticModelUpdater extends ISLAsticComponent {

    /**
     * Handler for incoming events.
     * @param record given monitoring record
     */
    public void consumeMonitoringRecord(AbstractKiekerMonitoringRecord record);
}