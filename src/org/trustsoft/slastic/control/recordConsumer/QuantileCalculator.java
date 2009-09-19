/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.systemModel.ModelUpdater;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 * 
 */
public class QuantileCalculator {

    private static final Log log = LogFactory.getLog(QuantileCalculator.class);
    //private final TreeMap<Integer, ConcurrentSkipListSet<SLOMonitoringRecord>> map;

    public QuantileCalculator(int[] serviceIDs) {
        log.info("QuantileCalculatorThread created!");
    }

    public long[] getResponseTimeForQuantiles(Float[] quantiles, int serviceID) {
        long[] responseTime;

        System.out.println("Quantile Request for ServiceID: " + serviceID);
        responseTime = new long[quantiles.length];
        //log.info(quantiles.length);
        SLOMonitoringRecord[] rtSet= new SLOMonitoringRecord[ModelUpdater.getResponseTimes(serviceID).size()];
        rtSet = ModelUpdater.getResponseTimes(serviceID).toArray(rtSet);
        
        if (rtSet == null) {
            log.error("Not yet any serviced with ID: "+serviceID+" available");
            return null;
        }else if(rtSet.length == 0){
        	log.error("Not yet any responseTime for ID: "+serviceID+" measured");
        } else {
            for (int i = 0; i < responseTime.length; i++) {
                if ((rtSet.length % (1 / (quantiles[i]))) != 0.0f) {
                	 int index = (int) ((rtSet.length)*(quantiles[i]));
                	 responseTime[i] = rtSet[index].rtNseconds;
                    log.info("UPDATING............." + responseTime[i] + "......................");
                } else {
                	try {
                		int index = (int) ((rtSet.length)*(quantiles[i]));
                		log.info(rtSet.length);
                   	 	responseTime[i] =(long) (0.5* (rtSet[index].rtNseconds + rtSet[index+1].rtNseconds));
                   	 } catch (Exception e) {
						e.printStackTrace();
					}
                    log.info("UPDATING.............." + responseTime[i] + ".....................");
                }
            }
        }
        return responseTime;
    }
}
