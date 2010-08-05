package org.trustsoft.slastic.control.components.modelManager;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

/**
 * A model manager that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyModelManagerComponent extends AbstractModelManagerComponent {

    @Override
    public void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException {
        // do nothing
    }

    @Override
    public void newObservation(IObservationEvent event) {
        // do nothing
    }

    @Override
    public void handleEvent(IEvent ev) {
        // do nothing
    }

}
