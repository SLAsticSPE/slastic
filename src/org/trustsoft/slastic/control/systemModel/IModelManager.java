package org.trustsoft.slastic.control.systemModel;

import reconfMM.ReconfigurationModel;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * Interface that has to be implemented by any class that should manage the ReconfigurationModel of SLAsticControl.
 * @author Lena Stoever
 *
 */
public interface IModelManager {

	/**
	 * Synchronized updating of the ReconfigurationModel via MonitoringRecords
	 * @param newRecord Record that updates the model
	 * @param oldRecord Record that represents an older value that can be deleted, because of the update of the model
	 */
	public void update(AbstractKiekerMonitoringRecord newRecord,
			AbstractKiekerMonitoringRecord oldRecord)throws RecordConsumerExecutionException;

	public void doReconfiguration(SLAsticReconfigurationPlan plan);
	
	public void setModel(ReconfigurationModel model);
}
