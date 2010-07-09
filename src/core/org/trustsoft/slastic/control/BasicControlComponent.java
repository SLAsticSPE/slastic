package org.trustsoft.slastic.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.monitoring.IObservationEventReceiver;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicControlComponent extends AbstractControlComponent {

    private static final Log log = LogFactory.getLog(BasicControlComponent.class);

    private final IObservationEventReceiver monitoringClientPort = new IObservationEventReceiver() {

            public void newObservation(final IObservationEvent event) {
                getModelUpdater().newObservation(event);
            }
        };

    @Override
    public IObservationEventReceiver getMonitoringClientPort() {
        return this.monitoringClientPort;
    }
}
