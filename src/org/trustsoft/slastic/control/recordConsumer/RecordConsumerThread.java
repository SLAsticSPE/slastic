/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 *
 */
public class RecordConsumerThread extends Thread implements ICalculateAverageListener {
	
	private static final Log log = LogFactory.getLog(RecordConsumerThread.class);
	private BlockingQueue<SLOMonitoringRecord> q = null; 
	private int update = 0;
	private long averageResponseTime = 0;
	
	public RecordConsumerThread(BlockingQueue<SLOMonitoringRecord> q){
		log.info("New RecordConsumerThread created");
		this.q = q;
	}
	
	public void run(){
		System.out.println("START vom RecordConsumerThread");
		while(true){
			synchronized(q){
				int count=0;
				while(this.update > 0 && (q.size()!=0)){
					this.update--;
					count++;
					System.out.println("updating.............................");
					long summedTimes = 0;
						for(SLOMonitoringRecord slo: q){
							summedTimes += (slo.rtNseconds);
						}
						this.averageResponseTime = (summedTimes/q.size());
				}
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public long getAverage(){
		return this.averageResponseTime;
	}
	
	@Override
	public synchronized void calculateAverageEventOccured(CalculateAverageEvent evt) {
			this.update++;			
	}

}
