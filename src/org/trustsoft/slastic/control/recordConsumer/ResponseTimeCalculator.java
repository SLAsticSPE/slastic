/**
 * @author Lena
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLongArray;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
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

    public AtomicLongArray getMedianResponseTime() {
        return this.quantileCalcThread.getQuantile(new float[]{0.5f});
    }

    public AtomicLongArray getQuantilResponseTime(float[] quantile, int id) {
        return this.quantileCalcThread.getQuantile(quantile,id);
    }

    @Override
    public String[] getRecordTypeSubscriptionList() {
        String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
    }

    @Override
    public boolean execute() throws RecordConsumerExecutionException {
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
