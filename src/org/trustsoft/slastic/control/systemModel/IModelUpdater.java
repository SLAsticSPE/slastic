package org.trustsoft.slastic.control.systemModel;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public interface IModelUpdater {

	public void update(AbstractKiekerMonitoringRecord record);
	public void execute();
	public void terminate();
}
