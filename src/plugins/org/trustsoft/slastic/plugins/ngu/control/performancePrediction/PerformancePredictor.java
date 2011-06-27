package org.trustsoft.slastic.plugins.ngu.control.performancePrediction;

import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * 
 * @author Nicolas Günther
 * 
 */
public class PerformancePredictor extends AbstractPerformancePredictorComponent {
	@Override
	public void handleEvent(final IEvent ev) {

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

	}
}
