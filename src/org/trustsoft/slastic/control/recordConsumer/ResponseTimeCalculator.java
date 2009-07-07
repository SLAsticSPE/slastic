/**
 * @author Lena
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.swing.event.EventListenerList;

import kieker.common.logReader.IKiekerRecordConsumer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;



import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public class ResponseTimeCalculator implements IKiekerRecordConsumer {
	
	private static final Log log = LogFactory.getLog(ResponseTimeCalculator.class);
	private static final int defaultCapacity = 200;
	private final BlockingQueue<SLOMonitoringRecord> responseTimes;
	AverageCalculatorThread averageCalcThread;
	QuantilCalculatorThread quantilCalcThread;
	private int anzahlConsumes=0;
	
	public ResponseTimeCalculator(){
		this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);
	}
	
	@Override
	public void consumeMonitoringRecord(
			AbstractKiekerMonitoringRecord monitoringRecord) {
		this.anzahlConsumes ++;
		if(monitoringRecord instanceof SLOMonitoringRecord){
			//System.out.println("TIME: "+(((SLOMonitoringRecord)monitoringRecord).rtNseconds)/(1000*1000));
			//synchronized(responseTimes){
				while(!this.responseTimes.offer((SLOMonitoringRecord)monitoringRecord)){
                    this.responseTimes.poll();
                }
			//}
//			System.out.println("average nach feuern "+this.consumerThread.getAverage());			
		}
	}
	
	public long getAverageResponseTime(){
		return this.averageCalcThread.getAverage();
	}
	
	public long getMedianResponseTime(){
		return this.quantilCalcThread.getQuantil(0.5f);
	}
	public long getQuantilResponseTime(float quantil){
		return this.quantilCalcThread.getQuantil(quantil);
	}

	@Override
	public String[] getRecordTypeSubscriptionList() {
		String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
	}

	@Override
	public boolean execute() {
		 this.averageCalcThread = new AverageCalculatorThread(this.responseTimes);
		 this.quantilCalcThread = new QuantilCalculatorThread(this.responseTimes);
		 averageCalcThread.start();
		 quantilCalcThread.start();
        return true;
	}

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        averageCalcThread.terminate();
    }

}
