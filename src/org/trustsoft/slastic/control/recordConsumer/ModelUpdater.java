package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public class ModelUpdater implements IKiekerRecordConsumer {
	private final BlockingQueue<SLOMonitoringRecord> responseTimes;

	public ModelUpdater(int defaultCapacity){
		this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);
	}
	  
	@Override
	public void consumeMonitoringRecord(
			AbstractKiekerMonitoringRecord newMonitoringRecord)
			throws RecordConsumerExecutionException {
		 if (newMonitoringRecord instanceof SLOMonitoringRecord) {
	            SLOMonitoringRecord oldSLORecord = null;
	            SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newMonitoringRecord;
	            while (!this.responseTimes.offer(newSLORecord)) {
	                oldSLORecord = this.responseTimes.poll();
	            }
	            ModelManager.getInstance().update(newSLORecord, oldSLORecord);
	            
	        }
		
	}

	@Override
	public boolean execute() throws RecordConsumerExecutionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getRecordTypeSubscriptionList() {
		String[] vec = {ModelUpdater.class.getCanonicalName()};
		return vec;
	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub
		
	}

}
