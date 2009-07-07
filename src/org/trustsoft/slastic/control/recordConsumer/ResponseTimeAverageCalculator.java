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

public class ResponseTimeAverageCalculator implements IKiekerRecordConsumer {
	
	private static final Log log = LogFactory.getLog(ResponseTimeAverageCalculator.class);
	private static final int defaultCapacity = 5;
	private final BlockingQueue<SLOMonitoringRecord> responseTimes;
	RecordConsumerThread consumerThread;
	private javax.swing.event.EventListenerList listenerList;
	private int anzahlConsumes=0;
	
	public ResponseTimeAverageCalculator(){
		this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);
		listenerList = new EventListenerList();
	}
	
	@Override
	public void consumeMonitoringRecord(
			AbstractKiekerMonitoringRecord monitoringRecord) {
		this.anzahlConsumes ++;
		if(monitoringRecord instanceof SLOMonitoringRecord){
			//System.out.println("TIME: "+((SLOMonitoringRecord)monitoringRecord).rtNseconds);
			//synchronized(responseTimes){
				while(!this.responseTimes.offer((SLOMonitoringRecord)monitoringRecord)){
                    this.responseTimes.poll();
                }
			//}
//			System.out.println("average nach feuern "+this.consumerThread.getAverage());			
		}
	}
	
	private void fireCalculateAverageEvent(CalculateAverageEvent evt){
		Object[] listeners = this.listenerList.getListenerList();
		for(int i = 0; i<listeners.length; i+=2){
			if(listeners[i] == ICalculateAverageListener.class){
				((ICalculateAverageListener)listeners[i+1]).calculateAverageEventOccured(evt);
			}
		}
	}
	
	public synchronized void addCalculateAverageEventListener(ICalculateAverageListener listener){
		listenerList.add(ICalculateAverageListener.class, listener);
	}
	
	public long getAverageResponseTime(){
		this.fireCalculateAverageEvent(new CalculateAverageEvent(this));
		return this.consumerThread.getAverage();
	}

	@Override
	public String[] getRecordTypeSubscriptionList() {
		String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
	}

	@Override
	public boolean execute() {
		 this.consumerThread = new RecordConsumerThread(this.responseTimes);
		 this.addCalculateAverageEventListener(this.consumerThread);
		 consumerThread.start();
//		 TestAskThread thread = new TestAskThread(this);
//		 thread.start();
        return true;
	}

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        consumerThread.terminate();
    }

}
