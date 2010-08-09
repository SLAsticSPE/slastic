package org.trustsoft.slastic.tests.junit.framework.testComponentStates;

import java.util.ArrayList;
import java.util.List;
import org.trustsoft.slastic.common.ISLAsticComponent;

/**
 * Tracker which can be used by components to monitor the sequence of 
 * calls to the framework method init, execute, and terminate.
 *
 * @see ISLAsticComponent
 *
 * @author Andre van Hoorn
 */
public class ComponentStateTracker {

    public enum States {

        INIT, EXECUTE, TERMINATE_NO_ERROR, TERMINATE_ERROR
    };
    private final List<States> stateSequence = new ArrayList<States>();

    /**
     * Monitors call to method init.
     *
     * @see ISLAsticComponent
     */
    public void initCalled() {
        this.stateSequence.add(States.INIT);
    }

    /**
     * Monitors call to method execute.
     *
     * @see ISLAsticComponent
     */
    public void executeCalled() {
        this.stateSequence.add(States.EXECUTE);
    }

    /**
     * Monitors call to method execute.
     *
     * @see ISLAsticComponent
     */
    public void terminateCalled(final boolean error) {
        if (error) {
            this.stateSequence.add(States.TERMINATE_ERROR);
        } else {
            this.stateSequence.add(States.TERMINATE_NO_ERROR);
        }
    }

    /**
     * Returns the sequence of monitoring framework method calls.
     *
     * @return the sequence of method calls
     */
    public List<States> getStateSequence(){
        return this.stateSequence;
    }
}
