/**
 * 
 */
package org.trustsoft.slastic.plugins.daLena.analysis;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monitoring.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 *
 */
public class AverageCalculatorThread extends Thread {

    private static final Log log = LogFactory.getLog(AverageCalculatorThread.class);
    private final BlockingQueue<SLOMonitoringRecord> q;
    private long averageResponseTime = -1;
    private AtomicBoolean terminateRequestPending = new AtomicBoolean(false);

    public AverageCalculatorThread(BlockingQueue<SLOMonitoringRecord> q) {
        log.info("New AverageCalculatorThread created");
        this.q = q;
    }

    public void run() {
        System.out.println("START vom RecordConsumerThread");
        while (true) {
            try {
                synchronized (this) {
                    this.wait();
                }
                if (!(q.size() > 0)) {
                    this.averageResponseTime = -1;
                    continue;
                }
                System.out.println("updating......................."+this.q.size()+"......");
                long summedTimes = 0;
                synchronized (q) {
                    for (SLOMonitoringRecord slo : q) {
                        summedTimes += (slo.rtNseconds);
                    }
                    this.averageResponseTime = (summedTimes / q.size());
                }
            } catch (InterruptedException ex) {
                if (this.terminateRequestPending.get()) {
                    log.info("Shutting down");
                } else {
                    log.error("Interrupted", ex);
                }
            } 
        }
    }

    public long getAverage() {
    	 synchronized (this) {
             this.notify();
         }
        return this.averageResponseTime;
    }

    public void terminate() {
        this.terminateRequestPending.set(true);
        synchronized (this) {
            this.notifyAll();
        }
    }
}
