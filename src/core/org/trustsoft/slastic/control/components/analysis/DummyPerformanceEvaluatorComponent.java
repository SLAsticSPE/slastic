package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * A performance evaluator that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyPerformanceEvaluatorComponent extends AbstractPerformanceEvaluatorComponent {

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
