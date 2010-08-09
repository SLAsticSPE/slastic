package org.trustsoft.slastic.tests.junit.framework.testComponentStates;

import org.trustsoft.slastic.control.components.analysis.BasicAnalysisComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class StateTrackingAnalysisComponent extends BasicAnalysisComponent implements ITracksComponentStates {

    private final ComponentStateTracker stateTracker = new ComponentStateTracker();

    public StateTrackingAnalysisComponent() {
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
