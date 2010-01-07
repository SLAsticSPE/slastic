package org.trustsoft.slastic.control.components;

import java.util.ArrayList;
import kieker.common.logReader.RecordConsumerExecutionException;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventListener;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventService;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicSLAsticControl extends AbstractSLAsticControl implements ISimpleSLAsticEventService{

    private static final Log log = LogFactory.getLog(BasicSLAsticControl.class);

    private ArrayList<ISimpleSLAsticEventListener> listeners = new ArrayList<ISimpleSLAsticEventListener>();

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    public void update(AbstractKiekerMonitoringRecord record) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[] getRecordTypeSubscriptionList() {
        return null; // receive records of all types
    }

    @Override
    public void consumeMonitoringRecord(
            AbstractKiekerMonitoringRecord monitoringRecord)
            throws RecordConsumerExecutionException {
        this.getModelUpdater().handleEvent(monitoringRecord);

    }

    public void sendEvent(ISLAsticEvent ev) {
        for (ISimpleSLAsticEventListener l : this.listeners){
            l.handleSLAsticEvent(ev);
        }
    }

    public void addListener(ISimpleSLAsticEventListener l) {
        this.listeners.add(l);
    }
}
