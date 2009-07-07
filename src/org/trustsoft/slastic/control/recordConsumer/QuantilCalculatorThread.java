/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 *
 */
public class QuantilCalculatorThread extends Thread {
	
	private static final Log log = LogFactory.getLog(AverageCalculatorThread.class);
    private final BlockingQueue<SLOMonitoringRecord> q;
    private AtomicBoolean terminateRequestPending = new AtomicBoolean(false);
    private TreeSet<SLOMonitoringRecord>treeSet;
    float which = 0.0f;
    long quantil = -1;
    
    
    public QuantilCalculatorThread(BlockingQueue<SLOMonitoringRecord> q){
    	log.info("QuantilCalculatorThread created!");
    	this.q = q;
    	this.treeSet = new TreeSet<SLOMonitoringRecord>(this.q);
    }
    
    public void run(){
    	while(true){
    		try{
	    		synchronized(this){
	    			this.wait();
	    		}
	    		this.treeSet = new TreeSet<SLOMonitoringRecord>(q);
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
    
    public long getQuantil(float quantil){
    	if(quantil > 1.0 || quantil < 0.0){
    		throw new IllegalArgumentException();
    	}
    	this.which = quantil;
    	synchronized(this){
    		this.notify();
    	}
    	return this.quantil;
    }

}
