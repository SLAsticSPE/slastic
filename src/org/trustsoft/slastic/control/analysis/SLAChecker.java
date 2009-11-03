/**
 * @author Lena
 */
package org.trustsoft.slastic.control.analysis;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.control.exceptions.ServiceIDDoesNotExistException;

import slal.Model;
import slal.SLO;

public class SLAChecker extends Thread implements IPerformanceAnalyzer {

    private static final Log log = LogFactory.getLog(SLAChecker.class);
    private AverageCalculatorThread averageCalcThread;
    private QuantileCalculator quantileCalc;
    private  slal.Model slas = null;
    private SLACheckerGUI[] guis;
    private int[] serviceIDs;
    private IAnalysis ana;
    private ScheduledThreadPoolExecutor ex;
    

    public SLAChecker() {
        this.quantileCalc = new QuantileCalculator();
    }

    private long getAverageResponseTime() {
        return this.averageCalcThread.getAverage();
    }

    private long[] getQuantilResponseTime(float[] quantile, int id) throws ServiceIDDoesNotExistException {
    	long[] rt = this.quantileCalc.getResponseTimeForQuantiles(quantile,id);
    	try {
    	} catch(Exception e) {e.printStackTrace();}
    	for(int i = 0; i < this.serviceIDs.length; i++){
    		if(id == this.serviceIDs[i]){
    			guis[i].addResponseTime(rt);
    			return rt;
    		}
    	}
        return rt;
    }


    
    public void run() {
        //this.averageCalcThread = new AverageCalculatorThread(this.responseTimes);
        //averageCalcThread.start();
        EList<SLO> slaslo = slas.getObligations().getSlo();
        ex = new ScheduledThreadPoolExecutor(slas.getObligations().getSlo().size());
        //final DateFormat m_ISO8601Local = new SimpleDateFormat("yyyyMMdd'-'HHmmss");    
        for(int i = 0; i< slaslo.size(); i++){
        	final int ID = slaslo.get(i).getServiceID();
        	if(slaslo.get(i).getType() == slal.Type.RT_QUANTILE_TYPE){
	        	int size = slaslo.get(i).getValue().getPair().size();
				final float[] quantile = new float[size];
	        	final int[] responseTimes = new int[size];
	        	for(int k = 0; k<size; k++){
	        		float q = (slaslo.get(i).getValue().getPair().get(k).getQuantile()/100.0f);
	        		quantile[k] = q;
	            	
	            		int responseTime = slaslo.get(i).getValue().getPair().get(k).getResponseTime();
						responseTimes[k] = responseTime;
	        	}
	        	final SLO slo = slaslo.get(i);
	        	ex.scheduleAtFixedRate(new Runnable() {
	                public void run() {
	                	long[] responseTimes = null;
						try {
							responseTimes = getQuantilResponseTime(quantile, ID);
						} catch (ServiceIDDoesNotExistException e) {
							e.printStackTrace();
						}
	                	for(int j = 0; j<responseTimes.length; j++){
	                		int responseTime2 = slo.getValue().getPair().get(j).getResponseTime();
							if(responseTimes[j]> responseTime2){
	                			System.out.println("SLA for service "+ID+" for quantile: "+quantile[j]+" NOT satisfied : "+responseTimes[j]+" > "+responseTime2);
	                			log.info("try to send SLAViolation");
	                			SLAViolationEvent evt = new SLAViolationEvent(ID);
	                			ana.handleInternalEvent(evt);
	                			log.info("SLAViolation sent");
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
        this.quantileCalc.terminate();
        if(ex != null){
        	ex.shutdownNow();
        }
        
    }

	@Override
	public void execute() {
        guis = new SLACheckerGUI[this.slas.getObligations().getSlo().size()];
        serviceIDs = new int[this.slas.getObligations().getSlo().size()];
        for(int i = 0; i < this.slas.getObligations().getSlo().size(); i++){
        	long[] quantiles = new long[this.slas.getObligations().getSlo().get(i).getValue().getPair().size()];
        	log.info("quantilesSize: "+quantiles.length);
        	for(int k = 0; k <this. slas.getObligations().getSlo().get(i).getValue().getPair().size(); k++){
        		quantiles[k] = this.slas.getObligations().getSlo().get(i).getValue().getPair().get(k).getResponseTime();
        	}
        	guis[i] = new SLACheckerGUI("ServiceID: "+this.slas.getObligations().getSlo().get(i).getServiceID(), 30000, quantiles[0], quantiles[1], quantiles[2]);
        	guis[i].paint();
        	serviceIDs[i] = this.slas.getObligations().getSlo().get(i).getServiceID();
        }
		this.run();
	}

	@Override
	public void handle(ISLAsticAnalysisEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSLAs(Model slas) {
		this.slas = slas;
		
	}

	@Override
	public void setAnalysis(IAnalysis ana) {
		this.ana=ana;
		
	}
}
