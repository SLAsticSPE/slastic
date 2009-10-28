package org.trustsoft.slastic.control.recordConsumer;

import org.trustsoft.slastic.control.analysis.IAnalysis;
import org.trustsoft.slastic.control.systemModel.IModelManager;
import org.trustsoft.slastic.control.systemModel.IModelUpdater;
import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public interface IControl extends IKiekerRecordConsumer {

	public void update(AbstractKiekerMonitoringRecord record);
	public void setAnalysis(IAnalysis analysis);
	public void setModelManager(IModelManager mng);
	public void setModelUpdater(IModelUpdater updater);
	public void setReconfigurationManager(IReconfigurationManager reconfManager);
	
}
