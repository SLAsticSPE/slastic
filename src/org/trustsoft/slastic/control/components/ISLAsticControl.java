package org.trustsoft.slastic.control.components;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.ISLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.ISLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;

public interface ISLAsticControl extends ISLAsticComponent {

    // TODO: what is this method good for?
    /**
     * This method gives the record to the Model Updater
     * @param record current monitoring record
     */
    public void update(AbstractKiekerMonitoringRecord record);

    public ISLAsticAnalysis getAnalysis();

    public ISLAsticModelManager getModelManager();

    public ISLAsticModelUpdater getModelUpdater();

    public ISLAsticReconfigurationManager getReconfigurationManager();

    public void setAnalysis(ISLAsticAnalysis analysis);

    public void setModelManager(ISLAsticModelManager modelManager);

    public void setModelUpdater(ISLAsticModelUpdater modelUpdater);

    public void setReconfigurationManager(ISLAsticReconfigurationManager reconfigurationManager);
}
