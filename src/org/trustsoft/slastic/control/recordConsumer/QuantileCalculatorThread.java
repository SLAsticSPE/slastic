/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 *
 */
public class QuantileCalculatorThread extends Thread {
	
	private static final Log log = LogFactory.getLog(AverageCalculatorThread.class);
    private final BlockingQueue<SLOMonitoringRecord> q;
    private final AtomicBoolean terminateRequestPending = new AtomicBoolean(false);
    private final ConcurrentSkipListSet<SLOMonitoringRecord>treeSet;
    float which = 0.0f;
    long quantil = -1;
    
    
    public QuantileCalculatorThread(BlockingQueue<SLOMonitoringRecord> q){
    	log.info("QuantilCalculatorThread created!");
    	this.q = q;
    	this.treeSet = new ConcurrentSkipListSet<SLOMonitoringRecord>(this.q);
    }

    public void updateSample (SLOMonitoringRecord newRecord, SLOMonitoringRecord oldRecord){
        this.treeSet.add(newRecord);
        this.treeSet.remove(oldRecord);
    }

    public void run(){
    	while(true){
    		try{
	    		synchronized(this){
	    			this.wait();
	    		}
	    		Object[] a = this.treeSet.toArray();
	    		if(a.length%(1/this.which) != 0){
	    			this.quantil = ((SLOMonitoringRecord)a[(int) ((a.length+1)/(1/this.which))]).rtNseconds; 
	    			System.out.println("UPDATING...................................");
	    		}else{
	    			this.quantil = (long) (0.5*(((SLOMonitoringRecord)(a[(int) (a.length/(1/this.which))])).rtNseconds)+((SLOMonitoringRecord)(a[(int) ((a.length/(1/this.which))+1)])).rtNseconds);
	    			System.out.println("UPDATING...................................");
	    		}
	    		
    		}catch (InterruptedException ex) {
    			 if (this.terminateRequestPending.get()) {
                     log.info("Shutting down");
                 } else {
                     log.error("Interrupted", ex);
                 }
			}
    	}
    }
    
    public void terminate(){
        this.terminateRequestPending.set(true);
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    public long getQuantile(float quantile){
    	if(quantile > 1.0 || quantile < 0.0){
    		throw new IllegalArgumentException();
    	}
    	this.which = quantile;
    	synchronized(this){
    		this.notify();
    	}
    	return this.quantil;
    }

}
