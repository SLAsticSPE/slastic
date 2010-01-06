package org.trustsoft.slastic.control.components.modelManager;

import reconfMM.ReconfigurationModel;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * Interface that has to be implemented by any class that should manage the ReconfigurationModel of SLAsticControl.
 * @author Lena Stoever
 *
 */
public interface ISLAsticModelManager {

	/**
	 * Synchronized updating of the ReconfigurationModel via MonitoringRecords
	 * @param newRecord Record that updates the model
	 * @param oldRecord Record that represents an older value that can be deleted, because of the update of the model
	 */
	public void update(AbstractKiekerMonitoringRecord newRecord);

	
	/**
	 * This method is responsible for the executing of the reconfiguration plan
	 * @param plan
	 */
	public void doReconfiguration(SLAsticReconfigurationPlan plan);
	
	public void setModel(ReconfigurationModel model);

	void setMaxResponseTime(int capacity);
}