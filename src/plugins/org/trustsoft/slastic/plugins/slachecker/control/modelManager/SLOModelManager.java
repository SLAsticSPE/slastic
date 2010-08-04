package org.trustsoft.slastic.plugins.slachecker.control.modelManager;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import kieker.common.record.IMonitoringRecord;
import org.trustsoft.slastic.plugins.slachecker.control.ServiceIDDoesNotExistException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ModelManager;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;
import org.trustsoft.slastic.plugins.slasticImpl.ModelReader;

import reconfMM.Service;
import slal.Model;

/**
 *
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLOModelManager extends ModelManager {

    private static final String PROP_NAME_SLAMODEL_FN = "slamodel_fn";

    private static final Log log = LogFactory.getLog(SLOModelManager.class);
    //map with the serviceID and the belonging queue of response times. This is necessary for deleting the oldest values when the maximum number is reached.
    private ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>> responseTimeQueues;
    private int capacity = 0;
    private slal.Model slas = null;

    @Override
    public boolean execute() {
        boolean success = super.execute();
        //reading the SLA-reconfigurationModel
        this.slas = ModelReader.readSLAModel(this.getInitProperty(PROP_NAME_SLAMODEL_FN));

        //intialize Model Manager object
        this.setMaxResponseTime(super.getReconfigurationModel().getMaxResponseTimes());

        this.initQueues();

        return success;
    }

    public Model getSlas() {
        return slas;
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
            int serviceID) throws ServiceIDDoesNotExistException {
        synchronized (this.reconfigurationModel) {
            for (int i = 0; i < this.reconfigurationModel.getComponents().size(); i++) {
                for (int k = 0; k < this.reconfigurationModel.getComponents().get(i).getServices().size(); k++) {
                    if (this.reconfigurationModel.getComponents().get(i).getServices().get(k).getServiceID() == serviceID) {
                        ConcurrentSkipListSet<SLOMonitoringRecord> rtList = (ConcurrentSkipListSet<SLOMonitoringRecord>)this.reconfigurationModel.getComponents().get(i).getServices().get(k).getResponseTimes();
                        if (rtList == null) { rtList = new ConcurrentSkipListSet<SLOMonitoringRecord>(); }
                        return rtList;
                    }
                }
            }
        }
        throw new ServiceIDDoesNotExistException();
    }

    @Override
    public void newObservation(IObservationEvent ev) {
        super.newObservation(ev);

        if (! (ev instanceof KiekerMeasurementEvent)){
            log.error("Can only handle records of type" + KiekerMeasurementEvent.class.getName());
            return;
        }

        IMonitoringRecord newRecord = ((KiekerMeasurementEvent)ev).getKiekerRecord();
        SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newRecord;
        int serviceID = newSLORecord.serviceId;
        synchronized (this.reconfigurationModel) {
            for (int i = 0; i < this.reconfigurationModel.getComponents().size(); i++) {
                for (int k = 0; k < this.reconfigurationModel.getComponents().get(i).getServices().size(); k++) {
                    Service service = this.reconfigurationModel.getComponents().get(i).getServices().get(k);
                    if (service.getServiceID() == serviceID) {
                        ConcurrentSkipListSet<SLOMonitoringRecord> list = (ConcurrentSkipListSet<SLOMonitoringRecord>) service.getResponseTimes();
                        BlockingQueue<SLOMonitoringRecord> queue = this.responseTimeQueues.get(serviceID);
                        synchronized (list) {
                            if (list.size() < this.capacity) {
                                list.add(newSLORecord);
                                queue.add(newSLORecord);
                            } else {
                                list.remove(queue.poll());
                                list.add(newSLORecord);
                                queue.add(newSLORecord);
                            }
                            //log.info("ListSize: "+list.size());
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
                ConcurrentSkipListSet<SLOMonitoringRecord> list = new ConcurrentSkipListSet<SLOMonitoringRecord>();
                this.responseTimeQueues.put(this.reconfigurationModel.getComponents().get(i).getServices().get(k).getServiceID(), new ArrayBlockingQueue<SLOMonitoringRecord>(this.capacity));
                this.reconfigurationModel.getComponents().get(i).getServices().get(k).setResponseTimes(list);
            }
        }

    }

    public void setMaxResponseTime(int capacity) {
        this.capacity = capacity;

    }
}
