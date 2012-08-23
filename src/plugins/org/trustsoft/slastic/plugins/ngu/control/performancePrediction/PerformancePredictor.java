/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.plugins.ngu.control.performancePrediction;

import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.plugins.ngu.transformation.SlasticToPcmTranformation;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.usage.UsageModel;

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
		final ModelManager modelManager = (ModelManager) this.getParentAnalysisComponent().getParentControlComponent().getModelManager();
		final SlasticToPcmTranformation transformation = new SlasticToPcmTranformation();
		final SystemModel systemModel = modelManager.getSystemModel();
		final UsageModel usageModel = modelManager.getUsageModel();
		transformation.transform(systemModel, usageModel);
		transformation.extractPcmModel(this.getComponentContext(), "output");
	}
}
