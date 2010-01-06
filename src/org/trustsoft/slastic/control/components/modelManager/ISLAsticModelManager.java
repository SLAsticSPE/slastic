package org.trustsoft.slastic.control.components.modelManager;

import reconfMM.ReconfigurationModel;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.control.components.ISLAsticComponent;

/**
 * Interface that has to be implemented by any class that should manage the ReconfigurationModel of SLAsticControl.
 * @author Lena Stoever
 *
 */
public interface ISLAsticModelManager extends ISLAsticComponent {

    // TODO: should at least be renamed
    /**
     * Synchronized updating of the ReconfigurationModel via MonitoringRecords
     * @param newRecord Record that updates the model
     * @param oldRecord Record that represents an older value that can be deleted, because of the update of the model
     */
    public void update(AbstractKiekerMonitoringRecord newRecord);

    // TODO: should at least be renamed
    /**
     * This method is responsible for the executing of the reconfiguration plan
     * @param plan
     */
    public void doReconfiguration(SLAsticReconfigurationPlan plan);

    // TODO: remove
    public void setModel(ReconfigurationModel model);

    // TODO: remove
    void setMaxResponseTime(int capacity);
}
