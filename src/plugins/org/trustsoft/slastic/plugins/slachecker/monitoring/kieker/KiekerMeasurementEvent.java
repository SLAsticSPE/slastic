package org.trustsoft.slastic.plugins.slachecker.monitoring.kieker;

import kieker.common.record.IMonitoringRecord;
import org.trustsoft.slastic.common.event.IObservationEvent;

/**
 *
 * @author Andre van Hoorn
 */
public class KiekerMeasurementEvent implements IObservationEvent {
    private final IMonitoringRecord kiekerRecord;

    public final IMonitoringRecord getKiekerRecord() {
        return kiekerRecord;
    }

    public KiekerMeasurementEvent (final IMonitoringRecord kiekerRecord){
        this.kiekerRecord = kiekerRecord;
    }
}
