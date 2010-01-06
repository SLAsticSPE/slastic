package org.trustsoft.slastic.control.components;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.ISLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.ISLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicSLAsticControl extends AbstractSLAsticControl {

    public BasicSLAsticControl(){
        super(null,null,null,null);
    }

    public BasicSLAsticControl(
            ISLAsticReconfigurationManager reconfigurationManager,
            ISLAsticModelManager modelManager,
            ISLAsticModelUpdater modelUpdater,
            ISLAsticAnalysis analysis) {
        super(reconfigurationManager,
                modelManager, modelUpdater,
                analysis);
    }

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
