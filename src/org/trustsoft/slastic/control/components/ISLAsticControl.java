package org.trustsoft.slastic.control.components;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public interface ISLAsticControl extends ISLAsticComponent {

    // TODO: what is this method good for?
    /**
     * This method gives the record to the Model Updater
     * @param record current monitoring record
     */
    public void update(AbstractKiekerMonitoringRecord record);
}
