package org.trustsoft.slastic.control.components.modelManager;

import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;

/**
 * A model manager that simply doesn't do anything.
 * 
 * @author Andre van Hoorn
 */
public class DummyModelManagerComponent extends AbstractModelManagerComponent {

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		// do nothing
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		// do nothing
	}

	@Override
	public void newObservation(final IObservationEvent event) {
		// do nothing
	}

	@Override
	public void handleEvent(final IEvent ev) {
		// do nothing
	}

	@Override
	public boolean init() {
		return true;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		// do nothing
	}
}
