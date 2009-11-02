package org.trustsoft.slastic.control.systemModel;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * This class is a RecordConsumer which gets the MonitoringRecords of Kieker and
 * collects specific MonitoringRecords to forward them to the ModelManager.
 * 
 * @author Lena Stoever
 * 
 */
public class ModelUpdater implements IModelUpdater {
	private static final Log log = LogFactory.getLog(ModelUpdater.class);
	
	/**
	 * The Only constructor of this class.
	 * 
	 * @param defaultCapacity
	 *            Capacity of the List of SLOMonitoringRecords that should be
	 *            collected.
	 */
	public ModelUpdater() {
		
	}
	@Override
	public void update(
			AbstractKiekerMonitoringRecord newMonitoringRecord) {
		if (newMonitoringRecord instanceof SLOMonitoringRecord) {
			ModelManager.getInstance().update(newMonitoringRecord);
		}

	}
	
	@Override
	public void execute(){
		
	}

	@Override
	public void terminate() {
	}



}
