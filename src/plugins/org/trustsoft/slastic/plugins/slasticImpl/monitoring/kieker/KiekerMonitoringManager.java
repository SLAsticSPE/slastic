package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker;

import java.util.Collection;
import kieker.analysis.plugin.IMonitoringRecordConsumerPlugin;
import kieker.common.record.IMonitoringRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;

/**
 * 
 * @author Andre van Hoorn
 */
public class KiekerMonitoringManager extends AbstractKiekerMonitoringManager {

    private static final Log log = LogFactory.getLog(KiekerMonitoringManager.class);

    @Override
    public void terminate(final boolean error) {
        log.info("KiekerMonitoringManager now terminating");
    }

    @Override
    protected IMonitoringRecordConsumerPlugin getMonitoringRecordConsumer() {
        return new IMonitoringRecordConsumerPlugin() {

            @Override
            public boolean newMonitoringRecord(IMonitoringRecord record) {
                try {
                    // simply forward (wrapped) records to controller
                    getController().getMonitoringClientPort().newObservation(new KiekerMeasurementEvent(record));
                } catch (Exception exc) {
                    log.error("Failed to forward record", exc);
                    return false;
                }
                return true;
            }

            @Override
            public Collection<Class<? extends IMonitoringRecord>> getRecordTypeSubscriptionList() {
                // TODO: needs to be refined
                return null; // receive events of any type
            }

            @Override
            public boolean execute() {
                // TODO: needs to be refined
                return true;
            }

            @Override
            public void terminate(boolean error) {
            }
        };
    }

    @Override
    protected boolean concreteExecute() {
        return true; // do nothing here 
    }
}
