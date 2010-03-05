package org.trustsoft.slastic.control;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.common.ISLAsticComponent;

public interface ISLAsticEventReceiver extends ISLAsticComponent {

    public void newEvent(AbstractKiekerMonitoringRecord record);
}
