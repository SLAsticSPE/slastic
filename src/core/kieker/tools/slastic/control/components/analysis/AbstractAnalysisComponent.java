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

package kieker.tools.slastic.control.components.analysis;

import kieker.tools.slastic.common.AbstractSLAsticComponent;
import kieker.tools.slastic.control.AbstractControlComponent;
import kieker.tools.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractAnalysisComponent extends AbstractSLAsticComponent {

	// private static final Log LOG = LogFactory.getLog(AbstractControlComponent.class);

	public static final String PROP_PREFIX = "slastic.control.analysis";
	private AbstractControlComponent parentControlComponent;
	private AbstractPerformanceEvaluatorComponent performanceEvaluator;
	private AbstractWorkloadForecasterComponent workloadForecaster;
	private AbstractPerformancePredictorComponent performancePredictor;
	private AbstractAdaptationPlannerComponent adaptationPlanner;

	public final AbstractAdaptationPlannerComponent getAdaptationPlanner() {
		return this.adaptationPlanner;
	}

	public final void setAdaptationPlanner(final AbstractAdaptationPlannerComponent adaptationPlanner) {
		this.adaptationPlanner = adaptationPlanner;
	}

	public final AbstractPerformanceEvaluatorComponent getPerformanceEvaluator() {
		return this.performanceEvaluator;
	}

	public final void setPerformanceEvaluator(final AbstractPerformanceEvaluatorComponent performanceEvaluator) {
		this.performanceEvaluator = performanceEvaluator;
	}

	public final AbstractPerformancePredictorComponent getPerformancePredictor() {
		return this.performancePredictor;
	}

	public final void setPerformancePredictor(final AbstractPerformancePredictorComponent performancePredictor) {
		this.performancePredictor = performancePredictor;
	}

	public final AbstractReconfigurationManagerComponent getReconfigurationManager() {
		return this.getParentControlComponent().getReconfigurationManager();
	}

	public final AbstractControlComponent getParentControlComponent() {
		return this.parentControlComponent;
	}

	public final void setParentControlComponent(final AbstractControlComponent parentControlComponent) {
		this.parentControlComponent = parentControlComponent;
	}

	public final AbstractWorkloadForecasterComponent getWorkloadForecaster() {
		return this.workloadForecaster;
	}

	public final void setWorkloadForecaster(final AbstractWorkloadForecasterComponent workloadForecaster) {
		this.workloadForecaster = workloadForecaster;
	}
}
