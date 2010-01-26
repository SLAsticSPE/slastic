/**
 * 
 */
package org.trustsoft.slastic.plugins.daLena.control.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.daLena.control.modelManager.ModelManager;
import org.trustsoft.slastic.control.exceptions.ServiceIDDoesNotExistException;
import org.trustsoft.slastic.monitoring.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * Class for calculating the given quantiles for a given service which is identified via serviceID.
 * @author Lena Stoever
 * 
 */
public class QuantileCalculator {

    private final Log log = LogFactory.getLog(QuantileCalculator.class);
    private final ModelManager mng;
    

    /**
     * Simple constructor of the QuantileCalculator.
     */
    public QuantileCalculator(final ModelManager mng) {
        log.info("QuantileCalculatorThread created!");
        this.mng = mng;
    }

    /**
     * The main method of this class, which is responsible for the calculation of the quantiles.
     * @param quantiles array of that identifies the quantiles in percent
     * @param serviceID ID that identified the services for which the quantiles should be calculated
     * @return array of results
     * @throws ServiceIDDoesNotExistException 
     */
    public long[] getResponseTimeForQuantiles(float[] quantiles, int serviceID) throws ServiceIDDoesNotExistException {
        long[] responseTime;
        //System.out.println("Quantile Request for ServiceID: " + serviceID);
        responseTime = new long[quantiles.length];
        SLOMonitoringRecord[] rtSet;
        rtSet= new SLOMonitoringRecord[this.mng.getResponseTimes(serviceID).size()];
                rtSet = mng.getResponseTimes(serviceID).toArray(rtSet);
       
        if (rtSet == null) {
            //log.error("Not yet any service with ID: "+serviceID+" available");
            return null;
        }else if(rtSet.length == 0){
        	//log.error("Not yet any responseTime for ID: "+serviceID+" measured"); 	
        } else {
        	//Calculating quantiles, if there are any response times available
            for (int i = 0; i < responseTime.length; i++) {
            	try{
            	// for the calculation check out the thesis ;)
                if ((rtSet.length % (1.0f / (quantiles[i])) != 0.0f)) {
                	 int index = (int) ((rtSet.length+1)*(quantiles[i]))-1;
                	 //log.info("rtsetSize"+rtSet.length+" und der Index: "+index);
                	 responseTime[i] = rtSet[index].rtNseconds;
                    //log.info("NEW Quantiles calculated............." + responseTime[i] + "......................");

                } else {
                	int index = (int) ((rtSet.length)*(quantiles[i]))-1;
                	//log.info(rtSet.length);
                   	responseTime[i] =(long) (0.5* (rtSet[index].rtNseconds + rtSet[index+1].rtNseconds));
                    //log.info("NEW Quantile calculated.............." + responseTime[i] + ".....................");
                }
                }catch(Exception e){
                	e.printStackTrace();
                }
            }
        }
        return responseTime;
    }
    public void terminate(){
    	
    }
}
