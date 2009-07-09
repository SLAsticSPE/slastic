/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.TreeMap;
import java.util.concurrent.BlockingQueue;
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

    public QuantileCalculator(BlockingQueue<SLOMonitoringRecord> q) {
        log.info("QuantileCalculatorThread created!");
        this.map = new TreeMap<Integer, ConcurrentSkipListSet<SLOMonitoringRecord>>();
    }

    public void updateSample(SLOMonitoringRecord newRecord,
            SLOMonitoringRecord oldRecord) {
        Integer newID = new Integer(newRecord.serviceId);
        if (this.map.containsKey(newID)) {
            this.map.get(newID).add(newRecord);
        } else {
            ConcurrentSkipListSet<SLOMonitoringRecord> set = new ConcurrentSkipListSet<SLOMonitoringRecord>();
            set.add(newRecord);
            this.map.put(newID, set);
        }
        if (oldRecord != null) {
            this.map.get(new Integer(oldRecord.serviceId)).remove(oldRecord);
        }
//		log.info("record added with ID: "+newRecord.serviceId);
    }

    public long[] getQuantile(float[] quantiles, int serviceID) {
        long[] quantile;

        System.out.println("Quantile Request for ServiceID: " + serviceID);
        synchronized (this) {
            quantile = new long[quantiles.length];
            if (this.map.get(serviceID) == null) {
                log.error("Not yet any serviced with this ID available");
                return null;
            } else {
                Object[] a = this.map.get(serviceID).toArray();
                for (int i = 0; i < quantiles.length; i++) {
                    if (a.length % (1 / quantiles[i]) != 0) {
                        quantile[i]=((SLOMonitoringRecord) a[(int) ((a.length) / (1 / quantiles[i]))]).rtNseconds;
                        log.info("UPDATING............." + quantile[i] + "......................");
                    } else {
                        quantile[i] =(long) (0.5 * (((SLOMonitoringRecord) (a[(int) (a.length / (1 / quantiles[i]))])).rtNseconds) + ((SLOMonitoringRecord) (a[(int) ((a.length / (1 / quantiles[i])) + 1)])).rtNseconds);
                        log.info("UPDATING.............." + quantile[i] + ".....................");
                    }
                }
            }
            System.out.println("Die Quantile sehen so aus: " + quantile.length);
            for (int i = 0; i < quantile.length; i++) {
                System.out.println(quantile[i]);
            }
        }

        return quantile;
    }

//	class IntegerValueComparator implements Comparator<Integer> {
//
//		@Override
//		public int compare(Integer arg0, Integer arg1) {
//			if (arg0.intValue() < arg1.intValue()) {
//				return -1;
//			} else if (arg0.intValue() > arg1.intValue()) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}
//	}
}
