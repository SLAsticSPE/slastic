package org.trustsoft.slastic.plugins.ngu.control.performancePrediction;

import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.ngu.transformation.SlasticToPcmTranformation;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.core.SystemModel;

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

		
//		transformation.slastic2pcm(new File("resources/bookstore.slastic"));
		return true;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		final ModelManager modelManager = (ModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();
		final SlasticToPcmTranformation transformation = new SlasticToPcmTranformation();
		final SystemModel systemModel = modelManager.getSystemModel();
		transformation.transform(systemModel);
		transformation.extractPcmModel(this.getComponentContext(), "output");
	}
}
