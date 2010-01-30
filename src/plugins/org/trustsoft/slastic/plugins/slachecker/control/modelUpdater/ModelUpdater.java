package org.trustsoft.slastic.plugins.slachecker.control.modelUpdater;

import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ModelManager;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractSLAsticModelUpdater;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * This class is a RecordConsumer which gets the MonitoringRecords of Kieker and
 * collects specific MonitoringRecords to forward them to the ModelManager.
 * 
 * @author Lena Stoever
 * 
 */
public class ModelUpdater extends AbstractSLAsticModelUpdater {

    private static final Log log = LogFactory.getLog(ModelUpdater.class);
    long count = 0;

    /**
     * The Only constructor of this class.
     *
     * @param defaultCapacity
     *            Capacity of the List of SLOMonitoringRecords that should be
     *            collected.
     */
    public ModelUpdater() {
    }

   public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord newMonitoringRecord) {

        count++;
        if (newMonitoringRecord instanceof SLOMonitoringRecord) {
            ((ModelManager)this.getModelManager()).update(newMonitoringRecord);
        }
        //Logging the number of Records that have been processed
        if (count % 500 == 0) {
            log.info("Number of Records: " + count);
        }

    }

    public void handleSLAsticEvent(ISLAsticEvent ev) {  }
}
