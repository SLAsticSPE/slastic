package org.trustsoft.slastic.tests.junit.testComponentStates;

import org.trustsoft.slastic.monitoring.DummyMonitoringManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class StateTrackingMonitoringManagerComponent extends DummyMonitoringManagerComponent implements ITracksComponentStates {

    private final ComponentStateTracker stateTracker = new ComponentStateTracker();

    public StateTrackingMonitoringManagerComponent() {
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
