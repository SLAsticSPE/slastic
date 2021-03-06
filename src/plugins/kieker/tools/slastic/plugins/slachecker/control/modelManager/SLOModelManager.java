/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.plugins.slachecker.control.modelManager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import reconfMM.Service;
import slal.Model;

import kieker.common.record.IMonitoringRecord;
import kieker.tools.slastic.common.event.IObservationEvent;
import kieker.tools.slastic.plugins.pcm.control.modelManager.ModelManager;
import kieker.tools.slastic.plugins.slachecker.control.ServiceIDDoesNotExistException;
import kieker.tools.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;
import kieker.tools.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;
import kieker.tools.slastic.plugins.slasticImpl.ModelIOUtils;

/**
 * 
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLOModelManager extends ModelManager {

	private static final String PROP_NAME_SLAMODEL_FN = "slamodel_fn";

	private static final Log LOG = LogFactory.getLog(SLOModelManager.class);

	// map with the serviceID and the belonging queue of response times. This is necessary for deleting the oldest values when the maximum number is reached.
	private ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>> responseTimeQueues;
	private int capacity = 0;
	private slal.Model slas = null;

	@Override
	public boolean execute() {
		final boolean success = super.execute();
		// reading the SLA-reconfigurationModel
		this.slas = ModelIOUtils.readSLAModel(this.getInitProperty(PROP_NAME_SLAMODEL_FN));

		// intialize Model Manager object
		this.setMaxResponseTime(super.getReconfigurationModel().getMaxResponseTimes());

		this.initQueues();

		return success;
	}

	public Model getSlas() {
		return this.slas;
	}

	/**
	 * runs through the reconfigurationModel and returns the set of responsetimes that belongs
	 * to the given service
	 * 
	 * @param serviceID
	 *            identifies the service
	 * @return
	 * @throws ServiceIDDoesNotExistException
	 */
	@SuppressWarnings("unchecked")
	public ConcurrentSkipListSet<SLOMonitoringRecord> getResponseTimes(
			final int serviceID) throws ServiceIDDoesNotExistException {
		synchronized (this.reconfigurationModel) {
			for (int i = 0; i < this.reconfigurationModel.getComponents().size(); i++) {
				for (int k = 0; k < this.reconfigurationModel.getComponents().get(i).getServices().size(); k++) {
					if (this.reconfigurationModel.getComponents().get(i).getServices().get(k).getServiceID() == serviceID) {
						ConcurrentSkipListSet<SLOMonitoringRecord> rtList = (ConcurrentSkipListSet<SLOMonitoringRecord>) this.reconfigurationModel.getComponents()
								.get(i).getServices().get(k).getResponseTimes();
						if (rtList == null) {
							rtList = new ConcurrentSkipListSet<SLOMonitoringRecord>();
						}
						return rtList;
					}
				}
			}
		}
		throw new ServiceIDDoesNotExistException();
	}

	@Override
	public void newObservation(final IObservationEvent ev) {
		super.newObservation(ev);

		if (!(ev instanceof KiekerMeasurementEvent)) {
			LOG.error("Can only handle records of type" + KiekerMeasurementEvent.class.getName());
			return;
		}

		final IMonitoringRecord newRecord = ((KiekerMeasurementEvent) ev).getKiekerRecord();
		final SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newRecord;
		final int serviceID = newSLORecord.serviceId;
		synchronized (this.reconfigurationModel) {
			for (int i = 0; i < this.reconfigurationModel.getComponents().size(); i++) {
				for (int k = 0; k < this.reconfigurationModel.getComponents().get(i).getServices().size(); k++) {
					final Service service = this.reconfigurationModel.getComponents().get(i).getServices().get(k);
					if (service.getServiceID() == serviceID) {
						final ConcurrentSkipListSet<SLOMonitoringRecord> list = (ConcurrentSkipListSet<SLOMonitoringRecord>) service.getResponseTimes();
						final BlockingQueue<SLOMonitoringRecord> queue = this.responseTimeQueues.get(serviceID);
						synchronized (list) {
							if (list.size() < this.capacity) {
								list.add(newSLORecord);
								queue.add(newSLORecord);
							} else {
								list.remove(queue.poll());
								list.add(newSLORecord);
								queue.add(newSLORecord);
							}
							// log.info("ListSize: "+list.size());
						}
					}

				}
			}
		}
	}

	/**
	 * Initializing the HashMap with the response time queues
	 */
	private void initQueues() {
		this.responseTimeQueues = new ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>>();
		for (int i = 0; i < this.reconfigurationModel.getComponents().size(); i++) {
			for (int k = 0; k < this.reconfigurationModel.getComponents().get(i).getServices().size(); k++) {
				// It is important not to use a Set of Longs, because of the
				// possibility of equal values of response times
				final ConcurrentSkipListSet<SLOMonitoringRecord> list = new ConcurrentSkipListSet<SLOMonitoringRecord>();
				this.responseTimeQueues.put(this.reconfigurationModel.getComponents().get(i).getServices().get(k).getServiceID(),
						new ArrayBlockingQueue<SLOMonitoringRecord>(this.capacity));
				this.reconfigurationModel.getComponents().get(i).getServices().get(k).setResponseTimes(list);
			}
		}

	}

	public void setMaxResponseTime(final int capacity) {
		this.capacity = capacity;

	}
}
