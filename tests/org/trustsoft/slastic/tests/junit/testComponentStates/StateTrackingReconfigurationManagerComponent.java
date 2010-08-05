package org.trustsoft.slastic.tests.junit.testComponentStates;

import org.trustsoft.slastic.reconfiguration.DummyReconfigurationManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class StateTrackingReconfigurationManagerComponent extends DummyReconfigurationManagerComponent implements ITracksComponentStates {

    private final ComponentStateTracker stateTracker = new ComponentStateTracker();

    public StateTrackingReconfigurationManagerComponent() {
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
