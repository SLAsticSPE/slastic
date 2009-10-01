package org.trustsoft.slastic.control.recordConsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.systemModel.ModelManager;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

import kieker.common.logReader.IKiekerRecordConsumer;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

/**
 * This class is a RecordConsumer which gets the MonitoringRecords of Kieker and
 * collects specific MonitoringRecords to forward them to the ModelManager.
 * 
 * @author Lena Stöver
 * 
 */
public class ModelUpdater implements IKiekerRecordConsumer {
	private static final Log log = LogFactory.getLog(ModelUpdater.class);
	private final BlockingQueue<SLOMonitoringRecord> responseTimes;
	boolean finished;

	/**
	 * The Only constructor of this class.
	 * 
	 * @param defaultCapacity
	 *            Capacity of the List of SLOMonitoringRecords that should be
	 *            collected.
	 */
	public ModelUpdater(int defaultCapacity) {
		this.responseTimes = new ArrayBlockingQueue<SLOMonitoringRecord>(
				defaultCapacity);
	}

	@Override
	public void consumeMonitoringRecord(
			AbstractKiekerMonitoringRecord newMonitoringRecord)
			throws RecordConsumerExecutionException {
		if (newMonitoringRecord instanceof SLOMonitoringRecord) {
			SLOMonitoringRecord oldSLORecord = null;
			SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newMonitoringRecord;
			while (!this.responseTimes.offer(newSLORecord)) {
				oldSLORecord = this.responseTimes.poll();
			}
			ModelManager.getInstance().update(newSLORecord, oldSLORecord);
			log
					.info("UPDATE F†R __________________________________________________________: "
							+ newSLORecord.serviceId);
		}

	}

	@Override
	public boolean execute() throws RecordConsumerExecutionException {
		return true;
	}

	@Override
	public String[] getRecordTypeSubscriptionList() {
		String[] vec = { ModelUpdater.class.getCanonicalName() };
		return null;
	}

	@Override
	public void terminate() {
		finished = true;

	}

}
