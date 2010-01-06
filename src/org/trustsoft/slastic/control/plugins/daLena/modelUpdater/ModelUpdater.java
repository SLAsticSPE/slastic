package org.trustsoft.slastic.control.plugins.daLena.modelUpdater;

import org.trustsoft.slastic.control.components.modelUpdater.IModelUpdater;

import org.trustsoft.slastic.control.plugins.daLena.modelManager.ModelManager;
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
	long count = 0;
	
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
		count++;
		if (newMonitoringRecord instanceof SLOMonitoringRecord) {
			ModelManager.getInstance().update(newMonitoringRecord);
		}
		//Logging the number of Records that have been processed
		if(count%500 ==0){
			log.info("Number of Records: "+count);
		}

	}
	
	@Override
	public void execute(){
		
	}

	@Override
	public void terminate() {
	}



}
