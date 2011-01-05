package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

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
    public void terminate(final boolean error) {
        // do nothing
    }

    @Override
    public void doReconfiguration(final SLAsticReconfigurationPlan plan) throws ReconfigurationException {
        // do nothing
    }
    
	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		// do nothing
	}

    @Override
    public boolean init() {
        return true;
    }
}
