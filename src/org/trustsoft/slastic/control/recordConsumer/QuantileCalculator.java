/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 * 
 */
public class QuantileCalculator {

    private static final Log log = LogFactory.getLog(QuantileCalculator.class);
    private final TreeMap<Integer, ConcurrentSkipListSet<SLOMonitoringRecord>> map;

    public QuantileCalculator(int[] serviceIDs) {
        log.info("QuantileCalculatorThread created!");
        this.map = new TreeMap<Integer, ConcurrentSkipListSet<SLOMonitoringRecord>>();
        for(int i = 0; i< serviceIDs.length; i++){
        	this.map.put(serviceIDs[i], new ConcurrentSkipListSet<SLOMonitoringRecord>());
        	System.out.println("ID mit der Nummer: "+serviceIDs[i]+" hinzugefuegt.");
        }
    }

    public void updateSample(SLOMonitoringRecord newRecord,
            SLOMonitoringRecord oldRecord) {
        Integer newID = new Integer(newRecord.serviceId);
        if (this.map.containsKey(newID)) {
            this.map.get(newID).add(newRecord);
            if (oldRecord != null) {
                this.map.get(new Integer(oldRecord.serviceId)).remove(oldRecord);
            }
        } else {
//            ConcurrentSkipListSet<SLOMonitoringRecord> set = new ConcurrentSkipListSet<SLOMonitoringRecord>();
//            set.add(newRecord);
//            this.map.put(newID, set);
        	log.error("SLO for ID: "+newRecord.serviceId+" does not exist!");
        }
        
//		log.info("record added with ID: "+newRecord.serviceId);
    }

    public long[] getResponseTimeForQuantiles(Float[] quantiles, int serviceID) {
        long[] responseTime;

        System.out.println("Quantile Request for ServiceID: " + serviceID);
        responseTime = new long[quantiles.length];
        //log.info(quantiles.length);
        SLOMonitoringRecord[] rtSet= new SLOMonitoringRecord[this.map.get(serviceID).size()];
        rtSet = this.map.get(serviceID).toArray(rtSet);
        
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
