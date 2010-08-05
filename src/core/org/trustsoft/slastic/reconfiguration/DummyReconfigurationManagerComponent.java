package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * A reconfiguration manager that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyReconfigurationManagerComponent extends AbstractReconfigurationManagerComponent {

    public boolean execute() {
        return true;
    }

    public void terminate(boolean error) {
        // do nothing
    }

    public void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException {
        // do nothing
    }

}
