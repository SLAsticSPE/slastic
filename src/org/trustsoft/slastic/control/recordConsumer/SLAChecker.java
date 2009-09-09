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
    	slas = m;
        this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);        
        int[] serviceIDs = new int[m.getObligations().getSlo().size()];
        for(int i = 0; i<m.getObligations().getSlo().size(); i++){
        	serviceIDs[i]= m.getObligations().getSlo().get(i).getServiceID();
        	
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
        return this.quantileCalc.getResponseTimeForQuantiles(quantile,id);
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
        ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(slas.getObligations().getSlo().size());
        final DateFormat m_ISO8601Local = new SimpleDateFormat("yyyyMMdd'-'HHmmss");    
        for(int i = 0; i< slas.getObligations().getSlo().size(); i++){
        	final int ID = slas.getObligations().getSlo().get(i).getServiceID();
        	if(slas.getObligations().getSlo().get(i).getType() == slal.Type.RT_QUANTILE_TYPE){
	        	final Float[] quantile = new Float[slas.getObligations().getSlo().get(i).getValue().getPair().size()];
	        	final int[] responseTimes = new int[slas.getObligations().getSlo().get(i).getValue().getPair().size()];
	        	for(int k = 0; k<slas.getObligations().getSlo().get(i).getValue().getPair().size(); k++){
	        		float q = ((float)slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getQuantile())/100;
	        		log.info("quantile:  "+q);
	        		quantile[k] = q;
	            	
	            		responseTimes[k] = slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getResponseTime();
	            		log.info("current responsetime:"+slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getResponseTime());
	            		log.info("SLA-Type"+slas.getObligations().getSlo().get(i).getType().getName());
	        	}
	        	final SLO slo = slas.getObligations().getSlo().get(i);
	        	ex.scheduleAtFixedRate(new Runnable() {
	                public void run() {
	                	long[] responseTimes = getQuantilResponseTime(quantile, ID);
	                	for(int j = 0; j<responseTimes.length; j++){
	                		if(responseTimes[j]> slo.getValue().getPair().get(j).getResponseTime()){
	                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" NOT satisfied : "+responseTimes[j]+" > "+slo.getValue().getPair().get(j).getResponseTime());
	                		}else
	                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" SATISFIED: "+responseTimes[j]+" <= "+slo.getValue().getPair().get(j).getResponseTime());
	                			
	                	}
	                    //System.out.println(m_ISO8601Local.format(new java.util.Date()) + ": QUANTIL:::::::::" + rtac.getQuantilResponseTime(quantile, ID)[0]);
	                }
	            }, (1000/(slas.getObligations().getSlo().size()))+i*1000, 1000, TimeUnit.MILLISECONDS);
        	}else{
        		log.error("No handling for this SLA-Type available");
        	}
        }
        return true;
    }

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        averageCalcThread.terminate();
    }
}
