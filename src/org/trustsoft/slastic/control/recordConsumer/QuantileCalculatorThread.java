/**
 * 
 */
package org.trustsoft.slastic.control.recordConsumer;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/**
 * @author Lena
 * 
 */
public class QuantileCalculatorThread extends Thread {

	private static final Log log = LogFactory
			.getLog(QuantileCalculatorThread.class);
	private final TreeMap<Integer, TreeSet<SLOMonitoringRecord>> map;
	private final AtomicBoolean terminateRequestPending = new AtomicBoolean(
			false);
	private final ConcurrentSkipListSet<SLOMonitoringRecord> treeSet;
	private float[] which;
	private AtomicLongArray quantile;
	private int serviceID;

	public QuantileCalculatorThread(BlockingQueue<SLOMonitoringRecord> q) {
		log.info("QuantileCalculatorThread created!");
		this.treeSet = new ConcurrentSkipListSet<SLOMonitoringRecord>();
		this.map = new TreeMap<Integer, TreeSet<SLOMonitoringRecord>>(
				new IntegerValueComparator());
		this.quantile = new AtomicLongArray(1); 
	}

	public void updateSample(SLOMonitoringRecord newRecord,
			SLOMonitoringRecord oldRecord) {
		Integer newID = new Integer(newRecord.serviceId);
		if (this.map.containsKey(newID)) {
			this.map.get(newID).add(newRecord);
		} else {
			TreeSet<SLOMonitoringRecord> set = new TreeSet<SLOMonitoringRecord>();
			set.add(newRecord);
			this.map.put(newID, set);
		}
		this.treeSet.add(newRecord);
		if (oldRecord != null) {
			this.map.get(new Integer(oldRecord.serviceId)).remove(oldRecord);
			this.treeSet.remove(oldRecord);
		}
//		log.info("record added with ID: "+newRecord.serviceId);
	}

	public void run() {
		while (true) {
			try {
				synchronized (this) {
					this.wait();
					//this.quantile = new AtomicLongArray(this.which.length);
				}
				if (this.serviceID == -1) {
					Object[] a = this.treeSet.toArray();
					AtomicLongArray ar = new AtomicLongArray(this.which.length);
					for (int i = 0; i < this.which.length; i++) {
						if (a.length % (1 / this.which[i]) != 0) {
							ar.set(i, ((SLOMonitoringRecord) a[(int) ((a.length) / (1 / this.which[i]))]).rtNseconds);
							log.info("UPDATING..........."+ar.get(i)+"........................");
						} else {
							ar.set(i,(long) (0.5 * (((SLOMonitoringRecord) (a[(int) (a.length / (1 / this.which[i]))])).rtNseconds) + ((SLOMonitoringRecord) (a[(int) ((a.length / (1 / this.which[i])) + 1)])).rtNseconds));
							log.info("UPDATING..........."+ar.get(i)+"........................");
						}
					}
				} else {
					AtomicLongArray ar = new AtomicLongArray(this.which.length);
					
					if(this.map.get(serviceID)== null){
						log.error("Not yet any serviced with this ID available");
						continue;
					}else{
						Object[] a = this.map.get(serviceID).toArray();
						for (int i = 0; i < this.which.length; i++) {
							if (a.length % (1 / this.which[i]) != 0) {
								ar.set(i,((SLOMonitoringRecord) a[(int) ((a.length) / (1 / this.which[i]))]).rtNseconds);
								log.info("UPDATING............."+ar.get(i)+"......................");
							} else {
								ar.set(i,(long) (0.5 * (((SLOMonitoringRecord) (a[(int) (a.length / (1 / this.which[i]))])).rtNseconds) + ((SLOMonitoringRecord) (a[(int) ((a.length / (1 / this.which[i])) + 1)])).rtNseconds));
								log.info("UPDATING.............."+ar.get(i)+".....................");
							}
						}
						this.quantile = ar;
					}
				}
			} catch (InterruptedException ex) {
				if (this.terminateRequestPending.get()) {
					log.info("Shutting down");
				} else {
					log.error("Interrupted", ex);
				}
			}
		}
	}

	public void terminate() {
		this.terminateRequestPending.set(true);
		synchronized (this) {
			this.notifyAll();
		}
	}

	public AtomicLongArray getQuantile(float[] quantiles) {
		this.serviceID = -1;
		this.which = quantiles;
		synchronized (this) {
			this.notify();
		}
		return this.quantile;
	}

	public AtomicLongArray getQuantile(float[] quantiles, int serviceIDs) {
		System.out.println("Quantile Request for ServiceID: "+serviceIDs);
		this.which = quantiles;
		this.serviceID=serviceIDs;
		synchronized (this) {
			this.notify();
			System.out.println("Die Quantile sehen so aus: "+this.quantile.length());
			for(int i = 0; i < this.quantile.length(); i++){
				System.out.println(this.quantile.get(i));
			}
		}
		
		return this.quantile;
	}

	class IntegerValueComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer arg0, Integer arg1) {
			if (arg0.intValue() < arg1.intValue()) {
				return -1;
			} else if (arg0.intValue() > arg1.intValue()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
