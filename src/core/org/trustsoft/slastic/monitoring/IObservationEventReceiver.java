package org.trustsoft.slastic.monitoring;

import org.trustsoft.slastic.common.event.IObservationEvent;

/**
 *
 * @author Andre van Hoorn
 */
public interface IObservationEventReceiver {
    public void newObservation (IObservationEvent event);
}
