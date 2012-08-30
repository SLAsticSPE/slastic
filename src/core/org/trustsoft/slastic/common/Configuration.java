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

package org.trustsoft.slastic.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

/**
 * 
 * @author Andre van Hoorn
 */
public class Configuration {
	private static final Log LOG = LogFactory.getLog(FrameworkInstance.class);

	public AbstractMonitoringManagerComponent monitoringManagerComponent;
	public AbstractControlComponent controlComponent;
	public AbstractModelManagerComponent modelManagerComponent;
	public AbstractModelUpdaterComponent modelUpdaterComponent;
	public AbstractAnalysisComponent analysisComponent;
	public AbstractPerformanceEvaluatorComponent performanceEvaluatorComponent;
	public AbstractWorkloadForecasterComponent workloadForecasterComponent;
	public AbstractPerformancePredictorComponent performancePredictorComponent;
	public AbstractAdaptationPlannerComponent adaptationPlannerComponent;
	public AbstractReconfigurationManagerComponent reconfigurationManagerComponent;

	public final Properties monitoringManagerComponentProperties = new Properties();
	public final Properties controlComponentProperties = new Properties();
	public final Properties modelManagerComponentProperties = new Properties();
	public final Properties modelUpdaterComponentProperties = new Properties();
	public final Properties analysisComponentProperties = new Properties();
	public final Properties performanceEvaluatorComponentProperties = new Properties();
	public final Properties workloadForecasterProperties = new Properties();
	public final Properties performancePredictorComponentProperties = new Properties();
	public final Properties adaptationPlannerComponentProperties = new Properties();
	public final Properties reconfigurationManagerComponentProperties = new Properties();

	private volatile IComponentContext rootContext; // will be set in #createAndSetComponentContexts

	/**
	 * @return the rootContext
	 */
	public IComponentContext getRootContext() {
		return this.rootContext;
	}

	/**
	 * Tests if the value of component is non-null and logs and additionally
	 * logs and error that the component of type componentType is null.
	 * 
	 * @param component
	 * @param identifier
	 * @return true if and only if component is not null, false otherwise
	 */
	private boolean isComponentInitialized(final AbstractSLAsticComponent component, final String componentType) {
		if (component == null) {
			LOG.error(componentType + " not initialized");
			return false;
		}
		return true;
	}

	/**
	 * Checks if components have a value different than null.
	 * 
	 * @return true iff ad only if all components have been initialized
	 */
	public boolean allComponentsInitialized() {
		return this.isComponentInitialized(this.monitoringManagerComponent, "MonitoringManagerComponent")
				&& this.isComponentInitialized(this.controlComponent, "ControlComponent")
				&& this.isComponentInitialized(this.modelManagerComponent, "ModelManagerComponent")
				&& this.isComponentInitialized(this.modelUpdaterComponent, "ModelUpdaterComponent")
				&& this.isComponentInitialized(this.analysisComponent, "AnalysisComponent")
				&& this.isComponentInitialized(this.performanceEvaluatorComponent, "PerformanceEvaluatorComponent")
				&& this.isComponentInitialized(this.workloadForecasterComponent, "WorkloadForecasterComponent")
				&& this.isComponentInitialized(this.performancePredictorComponent, "PerformancePredictorComponent")
				&& this.isComponentInitialized(this.adaptationPlannerComponent, "AdaptationPlannerComponent")
				&& this.isComponentInitialized(this.reconfigurationManagerComponent, "ReconfigurationManagerComponent");
	}

	public boolean wireComponents() {
		if (!this.allComponentsInitialized()) {
			LOG.error("At least one component is null");
		}

		this.wireMonitoringManager();
		this.wireControlComponent();
		this.wireModelManagerComponent();
		this.wireModelUpdaterComponent();
		this.wireAnalysisComponent();
		this.wireAnalysisSubcomponents();
		this.wireReconfigurationComponent();

		return true;
	}

	private final String COMPONENT_CLASSINFO_FN = "component-classname.txt";

	/**
	 * Creates a {@link ComponentContext} including a corresponding
	 * sub-directory for the given component which realizes the given SLAstic
	 * component type. The directory will
	 * 
	 * @param component
	 * @param componentType
	 * @return
	 */
	private boolean createAndsetComponentContext(final AbstractSLAsticComponent component, final String componentType) {
		final String contextName = componentType; // too long: + "-" + component.getClass().getName();
		final IComponentContext context = this.rootContext.createSubcontext(contextName);

		if (context == null) {
			LOG.error("Failed to create component context " + contextName);
			return false;
		}

		{ /*
		 * Writing file COMPONENT_CLASSINFO_FN which contains the
		 * class name of the component type implementation
		 */
			final File classInfoFile =
					context.createFileInContextDir(this.COMPONENT_CLASSINFO_FN);
			if (classInfoFile == null) {
				LOG.error("Failed to create file '" + this.COMPONENT_CLASSINFO_FN + "' in context " + context);
				return false;
			}

			PrintStream pos = null;
			try {
				pos = new PrintStream(classInfoFile);
				pos.print(componentType);
				pos.print(":");
				pos.println(component.getClass().getName());
			} catch (final IOException e) {
				LOG.error("Failed to create FileWriter for " + classInfoFile + ":" + e.getMessage(), e);
				return false;
			} finally {
				if (pos != null) {
					pos.close();
				}
			}
		}

		component.setComponentContext(context);

		return true;
	}

	public boolean createAndSetComponentContexts(final String fulldirname) {
		this.rootContext = ComponentContext.createRootContext("", fulldirname);

		if (this.rootContext == null) {
			return false;
		}

		return this.createAndsetComponentContext(this.monitoringManagerComponent, "MonitoringManagerComponent")
				&& this.createAndsetComponentContext(this.controlComponent, "Control")
				&& this.createAndsetComponentContext(this.modelManagerComponent, "ModelManager")
				&& this.createAndsetComponentContext(this.modelUpdaterComponent, "ModelUpdater")
				&& this.createAndsetComponentContext(this.analysisComponent, "Analysis")
				&& this.createAndsetComponentContext(this.performanceEvaluatorComponent, "PerformanceEvaluator")
				&& this.createAndsetComponentContext(this.workloadForecasterComponent, "WorkloadForecaster")
				&& this.createAndsetComponentContext(this.performancePredictorComponent, "PerformancePredictor")
				&& this.createAndsetComponentContext(this.adaptationPlannerComponent, "AdaptationPlanner")
				&& this.createAndsetComponentContext(this.reconfigurationManagerComponent, "ReconfigurationManager");
	}

	private void wireMonitoringManager() {
		this.monitoringManagerComponent.setController(this.controlComponent);
	}

	private void wireControlComponent() {
		this.controlComponent.setReconfigurationManager(this.reconfigurationManagerComponent);
		this.controlComponent.setAnalysis(this.analysisComponent);
		this.controlComponent.setModelManager(this.modelManagerComponent);
		this.controlComponent.setModelUpdater(this.modelUpdaterComponent);

		this.controlComponent.addListener(this.modelManagerComponent);
		this.controlComponent.addListener(this.modelUpdaterComponent);
		this.controlComponent.addListener(this.performanceEvaluatorComponent);
		this.performanceEvaluatorComponent.setSimpleSLAsticEventService(this.controlComponent);
		this.controlComponent.addListener(this.workloadForecasterComponent);
		this.workloadForecasterComponent.setSimpleSLAsticEventService(this.controlComponent);
		this.controlComponent.addListener(this.performancePredictorComponent);
		this.performancePredictorComponent.setSimpleSLAsticEventService(this.controlComponent);
		this.controlComponent.addListener(this.adaptationPlannerComponent);
		this.adaptationPlannerComponent.setSimpleSLAsticEventService(this.controlComponent);
	}

	private void wireModelManagerComponent() {
		this.modelManagerComponent.setParentControlComponent(this.controlComponent);
	}

	private void wireModelUpdaterComponent() {
		this.modelUpdaterComponent.setParentControlComponent(this.controlComponent);
		this.modelUpdaterComponent.setModelManager(this.modelManagerComponent);
	}

	private void wireAnalysisComponent() {
		this.analysisComponent.setParentControlComponent(this.controlComponent);
		this.analysisComponent.setPerformanceEvaluator(this.performanceEvaluatorComponent);
		this.analysisComponent.setWorkloadForecaster(this.workloadForecasterComponent);
		this.analysisComponent.setPerformancePredictor(this.performancePredictorComponent);
		this.analysisComponent.setAdaptationPlanner(this.adaptationPlannerComponent);
	}

	private void wireAnalysisSubcomponents() {
		this.performanceEvaluatorComponent.setParentAnalysisComponent(this.analysisComponent);
		this.workloadForecasterComponent.setParentAnalysisComponent(this.analysisComponent);
		this.performancePredictorComponent.setParentAnalysisComponent(this.analysisComponent);
		this.adaptationPlannerComponent.setParentAnalysisComponent(this.analysisComponent);
	}

	private void wireReconfigurationComponent() {
		this.reconfigurationManagerComponent.setControlComponent(this.controlComponent);
	}
}
