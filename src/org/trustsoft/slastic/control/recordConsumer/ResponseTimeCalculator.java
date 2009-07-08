/**
 * @author Lena
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

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
    QuantileCalculatorThread quantileCalcThread;
    private int anzahlConsumes = 0;

    public ResponseTimeCalculator() {
        this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);
    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord newMonitoringRecord) {
        this.anzahlConsumes++;
        if (newMonitoringRecord instanceof SLOMonitoringRecord) {
            SLOMonitoringRecord oldSLORecord = null;
            SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newMonitoringRecord;
            while (!this.responseTimes.offer(newSLORecord)) {
                oldSLORecord = this.responseTimes.poll();
            }
            this.quantileCalcThread.updateSample(newSLORecord, oldSLORecord);
        }
    }

    public long getAverageResponseTime() {
        return this.averageCalcThread.getAverage();
    }

    public long getMedianResponseTime() {
        return this.quantileCalcThread.getQuantile(0.5f);
    }

    public long getQuantilResponseTime(float quantil) {
        return this.quantileCalcThread.getQuantile(quantil);
    }

    @Override
    public String[] getRecordTypeSubscriptionList() {
        String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
    }

    @Override
    public boolean execute() {
        this.averageCalcThread = new AverageCalculatorThread(this.responseTimes);
        this.quantileCalcThread = new QuantileCalculatorThread(this.responseTimes);
        averageCalcThread.start();
        quantileCalcThread.start();
        return true;
    }

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        averageCalcThread.terminate();
    }
}
