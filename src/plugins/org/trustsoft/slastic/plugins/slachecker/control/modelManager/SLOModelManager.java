package org.trustsoft.slastic.plugins.slachecker.control.modelManager;

import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;
import org.trustsoft.slastic.plugins.slachecker.control.ServiceIDDoesNotExistException;
import reconfMM.ReconfigurationModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.SLA.SLOMonitoringRecord;

import reconfMM.Service;
import slal.Model;

/**
 *
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLOModelManager extends org.trustsoft.slastic.plugins.pcmreconfiguration.control.modelManager.ModelManager {

    private static final Log log = LogFactory.getLog(SLOModelManager.class);
    //map with the serviceID and the belonging queue of response times. This is necessary for deleting the oldest values when the maximum number is reached.
    private ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>> responseTimeQueues;
    private int capacity = 0;
    private slal.Model slas = null;

    @Override
    public boolean execute() {
        boolean success = super.execute();

        //reading the SLA-model
        this.slas = (slal.Model) runner.getContext().get("theModel");

        //intialize Model Manager object
        this.setMaxResponseTime(super.getReconfigurationModel().getMaxResponseTimes());

        //initialize Analysis object
        //this.getAnalysis().setSLAs(slas);
        //this.analysis.setReconfigurationManager(this.reconfigurationManager);

        return success;
    }

    public Model getSlas() {
        return slas;
    }

    /**
     * runs through the model and returns the set of responsetimes that belongs
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
        synchronized (this.model) {
            for (int i = 0; i < this.model.getComponents().size(); i++) {
                for (int k = 0; k < this.model.getComponents().get(i).getServices().size(); k++) {
                    if (this.model.getComponents().get(i).getServices().get(k).getServiceID() == serviceID) {
                        ConcurrentSkipListSet<SLOMonitoringRecord> rtList = (ConcurrentSkipListSet<SLOMonitoringRecord>)this.model.getComponents().get(i).getServices().get(k).getResponseTimes();
                        if (rtList == null) { rtList = new ConcurrentSkipListSet<SLOMonitoringRecord>(); }
                        return rtList;
                    }
                }
            }
        }
        throw new ServiceIDDoesNotExistException();
    }

    @Override
    public void update(AbstractKiekerMonitoringRecord newRecord) {
        super.update(newRecord);
        SLOMonitoringRecord newSLORecord = (SLOMonitoringRecord) newRecord;
        int serviceID = newSLORecord.serviceId;
        synchronized (this.model) {
            for (int i = 0; i < this.model.getComponents().size(); i++) {
                for (int k = 0; k < this.model.getComponents().get(i).getServices().size(); k++) {
                    Service service = this.model.getComponents().get(i).getServices().get(k);
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

    @Override
    public void setModel(ReconfigurationModel m) {
        super.setModel(m);
        this.initQueues();
    }

    /**
     * Initializing the HashMap with the response time queues
     */
    private void initQueues() {
        this.responseTimeQueues = new ConcurrentHashMap<Integer, BlockingQueue<SLOMonitoringRecord>>();
        for (int i = 0; i < this.model.getComponents().size(); i++) {
            for (int k = 0; k < this.model.getComponents().get(i).getServices().size(); k++) {
                // It is important not to use a Set of Longs, because of the
                // possibility of equal values of response times
                ConcurrentSkipListSet<SLOMonitoringRecord> list = new ConcurrentSkipListSet<SLOMonitoringRecord>();
                this.responseTimeQueues.put(this.model.getComponents().get(i).getServices().get(k).getServiceID(), new ArrayBlockingQueue<SLOMonitoringRecord>(this.capacity));
                this.model.getComponents().get(i).getServices().get(k).setResponseTimes(list);
            }
        }

    }

    public void setMaxResponseTime(int capacity) {
        this.capacity = capacity;

    }
}
