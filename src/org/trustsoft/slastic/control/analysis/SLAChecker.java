/**
 * @author Lena
 */
package org.trustsoft.slastic.control.analysis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.control.recordConsumer.AverageCalculatorThread;
import org.trustsoft.slastic.control.recordConsumer.QuantileCalculator;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import slal.SLO;

public class SLAChecker extends Thread implements IPerformanceAnalyzer {

    private static final Log log = LogFactory.getLog(SLAChecker.class);
    private static final int defaultCapacity = 200;
    //private final BlockingQueue<SLOMonitoringRecord> responseTimes;
    AverageCalculatorThread averageCalcThread;
    QuantileCalculator quantileCalc;
    final slal.Model slas;
    SLACheckerGUI[] guis;
    int[] serviceIDs;
    

    public SLAChecker(slal.Model SLAmodel) {
    	slas = SLAmodel;
        //this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(defaultCapacity);        
        this.serviceIDs = new int[SLAmodel.getObligations().getSlo().size()];
        for(int i = 0; i<SLAmodel.getObligations().getSlo().size(); i++){
        	this.serviceIDs[i]= SLAmodel.getObligations().getSlo().get(i).getServiceID();
        	
        }
        this.quantileCalc = new QuantileCalculator(this.serviceIDs);
        guis = new SLACheckerGUI[slas.getObligations().getSlo().size()];
        for(int i = 0; i < slas.getObligations().getSlo().size(); i++){
        	long[] quantiles = new long[slas.getObligations().getSlo().get(i).getValue().getPair().size()];
        	log.info("quantilesSize: "+quantiles.length);
        	for(int k = 0; k < slas.getObligations().getSlo().get(i).getValue().getPair().size(); k++){
        		quantiles[k] = slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getResponseTime();
        	}
        	guis[i] = new SLACheckerGUI("ServiceID: "+slas.getObligations().getSlo().get(i).getServiceID(), 30000, quantiles[0], quantiles[1], quantiles[2]);
        	guis[i].paint();
        }
    }

    private long getAverageResponseTime() {
        return this.averageCalcThread.getAverage();
    }

    private long[] getQuantilResponseTime(Float[] quantile, int id) {
    	long[] rt = this.quantileCalc.getResponseTimeForQuantiles(quantile,id);
    	for(int i = 0; i < this.serviceIDs.length; i++){
    		if(id == this.serviceIDs[i]){
    			guis[i].addResponseTime(rt);
    			log.info("GUI fŸr ID "+this.serviceIDs[i]+" geupdatet");
    			return rt;
    		}
    	}
        return rt;
    }


    
    public void run() {
        //this.averageCalcThread = new AverageCalculatorThread(this.responseTimes);
        //averageCalcThread.start();
        EList<SLO> slaslo = slas.getObligations().getSlo();
		ScheduledThreadPoolExecutor ex = new ScheduledThreadPoolExecutor(slaslo.size());
        //final DateFormat m_ISO8601Local = new SimpleDateFormat("yyyyMMdd'-'HHmmss");    
        for(int i = 0; i< slaslo.size(); i++){
        	final int ID = slaslo.get(i).getServiceID();
        	if(slaslo.get(i).getType() == slal.Type.RT_QUANTILE_TYPE){
	        	int size = slaslo.get(i).getValue().getPair().size();
				final Float[] quantile = new Float[size];
	        	final int[] responseTimes = new int[size];
	        	for(int k = 0; k<size; k++){
	        		float q = ((float)slaslo.get(i).getValue().getPair().get(k).getQuantile())/100;
	        		log.info("quantile:  "+q);
	        		quantile[k] = q;
	            	
	            		int responseTime = slaslo.get(i).getValue().getPair().get(k).getResponseTime();
						responseTimes[k] = responseTime;
	            		log.info("current responsetime:"+responseTime);
	            		log.info("SLA-Type"+slaslo.get(i).getType().getName());
	        	}
	        	final SLO slo = slaslo.get(i);
	        	ex.scheduleAtFixedRate(new Runnable() {
	                public void run() {
	                	try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	                	long[] responseTimes = getQuantilResponseTime(quantile, ID);
	                	for(int j = 0; j<responseTimes.length; j++){
	                		int responseTime2 = slo.getValue().getPair().get(j).getResponseTime();
							if(responseTimes[j]> responseTime2){
	                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" NOT satisfied : "+responseTimes[j]+" > "+responseTime2);
	                		}else
	                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" SATISFIED: "+responseTimes[j]+" <= "+responseTime2);
	                			
	                	}
	                    //System.out.println(m_ISO8601Local.format(new java.util.Date()) + ": QUANTIL:::::::::" + rtac.getQuantilResponseTime(quantile, ID)[0]);
	                }
	            }, (1000/(slaslo.size()))+i*1000, 1000, TimeUnit.MILLISECONDS);
        	}else{
        		log.error("No handling for this SLA-Type available");
        		
        	}
        }
    }

    public void terminate() {
        /* In case we spawned a thread in execute(),
         * we get the chance to kill it here. */
        averageCalcThread.terminate();
    }
}
