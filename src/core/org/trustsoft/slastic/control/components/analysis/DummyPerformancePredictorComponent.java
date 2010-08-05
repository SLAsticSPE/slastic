package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * A performance predictor that doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyPerformancePredictorComponent extends AbstractPerformancePredictorComponent {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(boolean error) {
        // do nothing
    }

    @Override
    public void handleEvent(IEvent ev) {
        // do nothing
    }
}
