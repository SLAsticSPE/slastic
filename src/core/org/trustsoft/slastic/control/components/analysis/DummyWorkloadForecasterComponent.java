package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * A workload forecaster that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyWorkloadForecasterComponent extends AbstractWorkloadForecasterComponent {

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

    @Override
    public boolean init() {
        return true;
    }
}
