package org.trustsoft.slastic.tests.junit.framework.testComponentStates;

import org.trustsoft.slastic.control.components.analysis.DummyPerformanceEvaluatorComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class StateTrackingPerformanceEvaluatorComponent extends DummyPerformanceEvaluatorComponent implements ITracksComponentStates {

    private final ComponentStateTracker stateTracker = new ComponentStateTracker();

    public StateTrackingPerformanceEvaluatorComponent() {
    }

    @Override
    public boolean init() {
        this.stateTracker.initCalled();
        return super.init();
    }

    @Override
    public boolean execute() {
        this.stateTracker.executeCalled();
        return super.execute();
    }

    @Override
    public void terminate(boolean error) {
         this.stateTracker.terminateCalled(error);
        super.terminate(error);
    }

    @Override
    public ComponentStateTracker getComponentStateTracker() {
        return this.stateTracker;
    }
}
