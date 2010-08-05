package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * An adaptation planner that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyAdaptationPlannerComponent extends AbstractAdaptationPlannerComponent {

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
