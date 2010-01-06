package org.trustsoft.slastic.control.components;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.trustsoft.slastic.control.components.analysis.ISLAsticAnalysis;
import org.trustsoft.slastic.control.components.modelManager.ISLAsticModelManager;
import org.trustsoft.slastic.control.components.modelUpdater.ISLAsticModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;

public interface ISLAsticControl extends IKiekerRecordConsumer {

	/**
	 * This method gives the record to the Model Updater 
	 * @param record current monitoring record
	 */
	public void update(AbstractKiekerMonitoringRecord record);
	
	public void setAnalysis(ISLAsticAnalysis analysis);
	public void setModelManager(ISLAsticModelManager mng);
	public void setModelUpdater(ISLAsticModelUpdater updater);
	public void setReconfigurationManager(ISLAsticReconfigurationManager reconfManager);
	
}
