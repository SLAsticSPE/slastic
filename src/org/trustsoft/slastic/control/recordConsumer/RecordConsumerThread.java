/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 *
 */
public class RecordConsumerThread extends Thread implements ICalculateAverageListener {

    private static final Log log = LogFactory.getLog(RecordConsumerThread.class);
    private final BlockingQueue<SLOMonitoringRecord> q;
    private long averageResponseTime = -1;
    private AtomicBoolean terminateRequestPending = new AtomicBoolean(false);

    public RecordConsumerThread(BlockingQueue<SLOMonitoringRecord> q) {
        log.info("New RecordConsumerThread created");
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
                System.out.println("updating.............................");
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
        return this.averageResponseTime;
    }

    @Override
    public synchronized void calculateAverageEventOccured(CalculateAverageEvent evt) {
        synchronized (this) {
            this.notify();
        }
    }

    public void terminate() {
        this.terminateRequestPending.set(true);
        synchronized (this) {
            this.notifyAll();
        }
    }
}
