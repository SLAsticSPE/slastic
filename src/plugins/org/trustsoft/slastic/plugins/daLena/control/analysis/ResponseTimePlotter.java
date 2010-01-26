/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.plugins.daLena.control.analysis;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.common.SLAsticInstance;
import org.trustsoft.slastic.plugins.daLena.monitoring.kieker.monitoringRecord.SLA.SLOMonitoringRecord;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 *
 * @author Andre van Hoorn
 */
public class ResponseTimePlotter implements IKiekerRecordConsumer {

    private static final Log log = LogFactory.getLog(SLAsticInstance.class);
     

   private final static String[] recordTypeSubscriptionList = {
        SLOMonitoringRecord.class.getCanonicalName()
    };

    public String[] getRecordTypeSubscriptionList() {
        return recordTypeSubscriptionList;
    }

    public void consumeMonitoringRecord(AbstractKiekerMonitoringRecord monitoringRecord) {
        if (monitoringRecord instanceof SLOMonitoringRecord) {
            SLOMonitoringRecord rec = (SLOMonitoringRecord) monitoringRecord;
            log.info(rec.componentName + "." + rec.operationName + ":"
					+ rec.rtNseconds + "ns = " + rec.rtNseconds / (1000 * 1000)
					+ "ms" + " @ timestamp " + rec.timestamp);
		} else {
            log.error("Can only consume records of type KiekerExecutionRecord" +
                    " but passed record is of type " + monitoringRecord.getClass().getName());
        }
    }



    public boolean execute() {
        /* We don't need to prepare */
    	return true;
    }

    public void terminate() {
        /* No actions required */
    }
}
