package org.trustsoft.slastic.plugins.ngu.control.performancePrediction;

import java.io.File;

import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.ngu.transformation.Transformation;

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
		final Transformation transformation = Transformation.getInstance();
		transformation.slastic2pcm(new File("resources/bookstore.slastic"));
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
