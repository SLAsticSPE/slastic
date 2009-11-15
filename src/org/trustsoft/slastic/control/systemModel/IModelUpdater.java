package org.trustsoft.slastic.control.systemModel;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public interface IModelUpdater {

	/**
	 * This method identifies the type of the given record and forwards it to the Model Manager
	 * @param record given monitoring record
	 */
	public void update(AbstractKiekerMonitoringRecord record);
	public void execute();
	public void terminate();
}
