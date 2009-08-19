/**
 * @author Lena
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import slal.SLO;

public class SLAChecker implements IKiekerRecordConsumer {

    private static final Log log = LogFactory.getLog(SLAChecker.class);
    private static final int defaultCapacity = 200;
    private final BlockingQueue<SLOMonitoringRecord> responseTimes;
    AverageCalculatorThread averageCalcThread;
    QuantileCalculator quantileCalc;
//    final TreeMap<Integer,TreeMap<Float,Long>> map;
    final slal.Model slas;

    public SLAChecker(slal.Model m) {
        log.info("constr SLAChecker");
        slas = m;
        this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);
//        this.map = new TreeMap<Integer, TreeMap<Float,Long>>();
//        TreeMap<Float, Long> slo = new TreeMap<Float, Long>();
//        slo.put(new Float(0.90f),new Long(1850000000));
//        slo.put(new Float(0.95), new Long(1950000000));
//        slo.put(new Float(0.99), new Long(1970000000));
//        map.put(new Integer(77), slo);
//        map.put(new Integer(12), slo);
//        this.quantileCalc = new QuantileCalculator(this.map.keySet().toArray(new Integer[this.map.size()]));

        int[] serviceIDs = new int[m.getSlos().size()];
        for (int i = 0; i < m.getSlos().size(); i++) {
            serviceIDs[i] = m.getSlos().get(i).getServiceID();

        }
        this.quantileCalc = new QuantileCalculator(serviceIDs);
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
        return this.quantileCalc.getQuantile(quantile, id);
    }

    @Override
    public String[] getRecordTypeSubscriptionList() {
        String[] vec = {SLOMonitoringRecord.class.getCanonicalName()};
        return vec;
    }
    ScheduledThreadPoolExecutor ex;

    @Override
    public boolean execute() throws RecordConsumerExecutionException {
        //this.averageCalcThread = new AverageCalculatorThread(this.responseTimes);
        //averageCalcThread.start();
        ex = new ScheduledThreadPoolExecutor(1);
        ex.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
        final DateFormat m_ISO8601Local = new SimpleDateFormat("yyyyMMdd'-'HHmmss");
        for (int i = 0; i < slas.getSlos().size(); i++) {
            final int ID = slas.getSlos().get(i).getServiceID();
            final Float[] quantile = new Float[slas.getSlos().get(i).getPairList().getPair().size()];
            final int[] responseTimes = new int[slas.getSlos().get(i).getPairList().getPair().size()];
            for (int k = 0; k < slas.getSlos().get(i).getPairList().getPair().size(); k++) {
                int pre = slas.getSlos().get(i).getPairList().getPair().get(k).getQuantile().getPre();
                int post = slas.getSlos().get(i).getPairList().getPair().get(k).getQuantile().getPost();
                if (!(pre >= 0 && pre <= 1)) {
                    log.error("Quantile needs to be less than or equals 1!");
                    return false;
                } else {
                    float fpre = (float) pre;
                    float buff = (float) post;
                    int c = ((Integer) post).toString().length();
                    float fpost = (buff / (10 * c));
                    fpre += fpost;
                    quantile[k] = fpre;
                    responseTimes[k] = slas.getSlos().get(i).getPairList().getPair().get(k).getResponseTime();
                }

            }
            final SLO slo = slas.getSlos().get(i);
            ex.scheduleAtFixedRate(new Runnable() {

                public void run() {
                    log.info("Hallo");
                    long[] responseTimes = getQuantilResponseTime(quantile, ID);
                    log.info("Tschüss");
                    for (int j = 0; j < responseTimes.length; j++) {
                        if (responseTimes[j] > slo.getPairList().getPair().get(j).getResponseTime()) {
                            System.out.println("SLA for service " + ID + " for quantile: " + quantile[j] + " NOT satisfied : " + responseTimes[j] + " > " + slo.getPairList().getPair().get(j).getResponseTime());
                        } else {
                            System.out.println("SLA for service " + ID + " for quantile: " + quantile[j] + " SATISFIED: " + responseTimes[j] + " <= " + slo.getPairList().getPair().get(j).getResponseTime());
                        }

                    }
                    //System.out.println(m_ISO8601Local.format(new java.util.Date()) + ": QUANTIL:::::::::" + rtac.getQuantilResponseTime(quantile, ID)[0]);
                }
            }, 1000, 5000, TimeUnit.MILLISECONDS); //(1000/(slas.getSlos().size()))+i*1000
        }
        return true;
    }

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        //averageCalcThread.terminate();
        ex.shutdown();
        log.info("terminated");
    }
}
