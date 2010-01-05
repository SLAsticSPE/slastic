package org.trustsoft.slastic.control.recordConsumer;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.trustsoft.slastic.control.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.systemModel.IModelManager;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

public interface ISLAsticControl extends IKiekerRecordConsumer {

	/**
	 * This method gives the record to the Model Updater 
	 * @param record current monitoring record
	 */
	public void update(AbstractKiekerMonitoringRecord record);
	
	public void setAnalysis(ISLAsticAnalysis analysis);
	public void setModelManager(IModelManager mng);
	public void setModelUpdater(IModelUpdater updater);
	public void setReconfigurationManager(IReconfigurationManager reconfManager);
	
}
