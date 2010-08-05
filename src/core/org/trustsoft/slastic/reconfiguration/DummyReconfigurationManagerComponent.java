package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;

/**
 * A reconfiguration manager that simply doesn't do anything.
 *
 * @author Andre van Hoorn
 */
public class DummyReconfigurationManagerComponent extends AbstractReconfigurationManagerComponent {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(boolean error) {
        // do nothing
    }

    @Override
    public void doReconfiguration(SLAsticReconfigurationPlan plan) throws ReconfigurationException {
        // do nothing
    }

    @Override
    public boolean init() {
        return true;
    }

}