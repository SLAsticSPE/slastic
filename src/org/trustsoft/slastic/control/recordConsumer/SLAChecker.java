/**
 * @author Lena
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;



import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

public class SLAChecker implements IKiekerRecordConsumer {

    private static final Log log = LogFactory.getLog(SLAChecker.class);
    private static final int defaultCapacity = 200;
    private final BlockingQueue<SLOMonitoringRecord> responseTimes;
    AverageCalculatorThread averageCalcThread;
    QuantileCalculator quantileCalc;
    final TreeMap<Integer,TreeMap<Float,Long>> map;

    public SLAChecker() {
        this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);        
        this.map = new TreeMap<Integer, TreeMap<Float,Long>>();
        TreeMap<Float, Long> slo = new TreeMap<Float, Long>();
        slo.put(new Float(0.90f),new Long(1850000000));
        slo.put(new Float(0.95), new Long(1950000000));
        slo.put(new Float(0.99), new Long(1970000000));
        map.put(new Integer(77), slo);
        map.put(new Integer(12), slo);
        this.quantileCalc = new QuantileCalculator(this.map.keySet().toArray(new Integer[this.map.size()]));
    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord newMonitoringRecord) {
        if (newMonitoringRecord instanceof SLOMonitoringRecord) {
            SLOMonitoringRecord oldSLORecord = null;
            SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newMonitoringRecord;
            while (!this.responseTimes.offer(newSLORecord)) {
                oldSLORecord = this.responseTimes.poll();
            }
            this.quantileCalc.updateSample(newSLORecord, oldSLORecord);
            
        }
    }

    public long getAverageResponseTime() {
        return this.averageCalcThread.getAverage();
    }

    private long[] getQuantilResponseTime(Float[] quantile, int id) {
        return this.quantileCalc.getQuantile(quantile,id);
    }

    @Override
    public String[] getRecordTypeSubscriptionList() {
        String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
    }

    @Override
    public boolean execute() throws RecordConsumerExecutionException {
        this.averageCalcThread = new AverageCalculatorThread(this.responseTimes);
        averageCalcThread.start();
        System.out.println("Komm ich hier denn an?");
        ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(map.size());
        final DateFormat m_ISO8601Local = new SimpleDateFormat("yyyyMMdd'-'HHmmss");        
        for(int i = 0; i< map.size(); i++){
        	final int ID = map.keySet().toArray(new Integer[map.size()])[i];
        	final Float[] quantile = map.get(ID).keySet().toArray(new Float[map.get(ID).size()]);
        	ex.scheduleAtFixedRate(new Runnable() {
                public void run() {
                	long[] responseTimes = getQuantilResponseTime(quantile, ID);
                	for(int j = 0; j<responseTimes.length; j++){
                		if(responseTimes[j]> map.get(ID).get(quantile[j])){
                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" NOT satisfied : "+responseTimes[j]+" > "+map.get(ID).get(quantile[j]));
                		}else
                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" SATISFIED: "+responseTimes[j]+" <= "+map.get(ID).get(quantile[j]));
                			
                	}
                    //System.out.println(m_ISO8601Local.format(new java.util.Date()) + ": QUANTIL:::::::::" + rtac.getQuantilResponseTime(quantile, ID)[0]);
                }
            }, (1000/(this.map.size()))+i*1000, 1000, TimeUnit.MILLISECONDS);
        }
        return true;
    }

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        averageCalcThread.terminate();
    }
}
