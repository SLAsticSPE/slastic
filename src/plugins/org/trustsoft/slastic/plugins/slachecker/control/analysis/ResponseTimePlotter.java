/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.plugins.slachecker.control.analysis;

import java.util.ArrayList;
import java.util.Collection;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.common.record.IMonitoringRecord;
import kieker.common.record.MonitoringRecordReceiverException;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class ResponseTimePlotter implements IMonitoringRecordConsumerPlugin {

    private static final Log log = LogFactory.getLog(ResponseTimePlotter.class);

    private final static Collection<Class<? extends IMonitoringRecord>> recordTypeSubscriptionList =
            new ArrayList<Class<? extends IMonitoringRecord>>();
    static {
        recordTypeSubscriptionList.add(SLOMonitoringRecord.class);
    }

    public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean newMonitoringRecord(IMonitoringRecord record) {
        if (record instanceof SLOMonitoringRecord) {
            SLOMonitoringRecord rec = (SLOMonitoringRecord) record;
            log.info(rec.componentName + "." + rec.operationName + ":"
                    + rec.rtNseconds + "ns = " + rec.rtNseconds / (1000 * 1000)
                    + "ms" + " @ timestamp " + rec.timestamp);
        } else {
            log.error("Can only consume records of type KiekerExecutionRecord"
                    + " but passed record is of type " + record.getClass().getName());
        }
        return true;
    }

    public boolean execute() {
        /* We don't need to prepare */
        return true;
    }

    public void terminate(final boolean error) {
        /* No actions required */
    }
}
