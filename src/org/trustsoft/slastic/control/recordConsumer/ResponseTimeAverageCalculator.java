/**
 * @author Lena
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.SLAsticControl;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import kieker.loganalysis.recordConsumer.IMonitoringRecordConsumer;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public class ResponseTimeAverageCalculator implements IMonitoringRecordConsumer {
	
	private static final Log log = LogFactory.getLog(SLAsticControl.class);
	private static final int defaultCapacity = 5;
	private ArrayBlockingQueue<SLOMonitoringRecord> responseTimes;
	private long averageResponseTime;
	
	public ResponseTimeAverageCalculator(){
		this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);
		this.averageResponseTime = 0; 
	}
	
	@Override
	public synchronized void  consumeMonitoringRecord(
			AbstractKiekerMonitoringRecord monitoringRecord) {
		if(monitoringRecord instanceof SLOMonitoringRecord){
			while(!this.responseTimes.offer((SLOMonitoringRecord)monitoringRecord)){
				this.responseTimes.poll();
			}
			//log.info("Record Added: "+((SLOMonitoringRecord)monitoringRecord).rtNseconds);
			updateAverage();
		}

	}

	private synchronized void updateAverage() {
		long summedTimes = 0; 
		for(SLOMonitoringRecord slo:responseTimes){
			summedTimes += (slo.rtNseconds);
		}
		//log.info("summed times:"+summedTimes);
		this.averageResponseTime = (summedTimes/responseTimes.size());
		log.info("Average rtNseconds NOW: "+this.averageResponseTime);
	}
	
	public long getAverageResponseTime(){
		return this.averageResponseTime;
	}

	@Override
	public String[] getRecordTypeSubscriptionList() {
		String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
	}

	@Override
	public void run() {
		 /* We don't need to prepare */

	}

}
