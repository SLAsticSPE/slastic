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

package kieker.tools.slastic.common;

import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.common.util.PropertiesFileUtils;
import kieker.tools.slastic.control.AbstractControlComponent;
import kieker.tools.slastic.control.BasicControlComponent;
import kieker.tools.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import kieker.tools.slastic.control.components.analysis.AbstractAnalysisComponent;
import kieker.tools.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import kieker.tools.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import kieker.tools.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import kieker.tools.slastic.control.components.analysis.BasicAnalysisComponent;
import kieker.tools.slastic.control.components.analysis.DummyAdaptationPlannerComponent;
import kieker.tools.slastic.control.components.analysis.DummyPerformanceEvaluatorComponent;
import kieker.tools.slastic.control.components.analysis.DummyPerformancePredictorComponent;
import kieker.tools.slastic.control.components.analysis.DummyWorkloadForecasterComponent;
import kieker.tools.slastic.control.components.modelManager.AbstractModelManagerComponent;
import kieker.tools.slastic.control.components.modelManager.DummyModelManagerComponent;
import kieker.tools.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import kieker.tools.slastic.control.components.modelUpdater.DummyModelUpdaterComponent;
import kieker.tools.slastic.monitoring.AbstractMonitoringManagerComponent;
import kieker.tools.slastic.monitoring.DummyMonitoringManagerComponent;
import kieker.tools.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import kieker.tools.slastic.reconfiguration.DummyReconfigurationManagerComponent;

/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class FrameworkInstance {

	private static final Log LOG = LogFactory.getLog(FrameworkInstance.class);

	private final Configuration configuration = new Configuration();

	private final AtomicBoolean terminated = new AtomicBoolean(false);

	public static final String COMPONENT_CLASSNAME_PROPNAME = "classname";
	public static final String DIRECTORYTOWRITE_PROPNAME = "directoryToWrite";

	/**
	 * Avoid construction via default constructor
	 */
	@SuppressWarnings("unused")
	private FrameworkInstance() {}

	public FrameworkInstance(final String configurationFilename) {
		this(PropertiesFileUtils.loadPropertiesFile(configurationFilename));
	}

	public FrameworkInstance(final Properties prop) {
		this.initConfiguration(prop);
	}

	/**
	 * Returns the framework configuration.
	 * 
	 * @return the framework configuration
	 */
	public Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * Splits the given {@link Properties} into {@link Properties} for each {@link AbstractSLAsticComponent} and stores them in the {@link #configuration} of this
	 * instance.
	 * 
	 * @param prop
	 * @throws IllegalArgumentException
	 */
	private void initComponentProperties(final Properties prop) throws IllegalArgumentException {
		for (final String curPropName : prop.stringPropertyNames()) {
			boolean storedProp = false;
			if (curPropName.startsWith(AbstractPerformanceEvaluatorComponent.PROP_PREFIX + ".")) {
				storedProp = true;
				this.configuration.performanceEvaluatorComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractPerformanceEvaluatorComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractWorkloadForecasterComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.workloadForecasterProperties.setProperty(
						curPropName.replaceFirst(AbstractWorkloadForecasterComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractPerformancePredictorComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.performancePredictorComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractPerformancePredictorComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractAdaptationPlannerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.adaptationPlannerComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractAdaptationPlannerComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractAnalysisComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.analysisComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractAnalysisComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractModelManagerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.modelManagerComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractModelManagerComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractModelUpdaterComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.modelUpdaterComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractModelUpdaterComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractControlComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.controlComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractControlComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractMonitoringManagerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.monitoringManagerComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractMonitoringManagerComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractReconfigurationManagerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.reconfigurationManagerComponentProperties.setProperty(
						curPropName.replaceFirst(AbstractReconfigurationManagerComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
			}
			if (!storedProp) {
				LOG.warn("Unknown property name '" + curPropName + "'");
			}
		}
	}

	/**
	 * Instantiates a {@link AbstractSLAsticComponent} based on the name of the {@link Properties} value {@link #COMPONENT_CLASSNAME_PROPNAME}.
	 * 
	 * @return the instance; null in case an error occurred.
	 */
	private AbstractSLAsticComponent loadComponent(final String componentPropertiesPrefix, final Properties componentProperties) {
		final String classname = componentProperties.getProperty(COMPONENT_CLASSNAME_PROPNAME);
		if ((classname == null) || (classname.length() <= 0)) {
			LOG.error("Missing configuration property value for '" + componentPropertiesPrefix + "." + COMPONENT_CLASSNAME_PROPNAME + "'");
			return null;
		}

		AbstractSLAsticComponent inst = null;
		try {
			final Class<?> cl = Class.forName(classname);
			if (cl == null) {
				throw new NullPointerException("Class.forName returned null");
			}
			// FIXME: assert cl instanceof AbstractSLAsticComponent?
			inst = (AbstractSLAsticComponent) cl.newInstance();
			final Method m = cl.getMethod("setProperties", Properties.class);
			m.invoke(inst, componentProperties);

		} catch (final Exception ex) {
			inst = null;
			LOG.fatal("Failed to instantiate component of class '" + classname + "'", ex);
		}
		return inst;
	}

	/**
	 * Loads the {@link AbstractSLAsticComponent}s, wires them and assigns the {@link IComponentContext}s.
	 * 
	 * @param prop
	 * @throws IllegalArgumentException
	 */
	private void initConfiguration(final Properties prop) throws IllegalArgumentException {
		try {
			this.initComponentProperties(prop);

			/* Load all components */
			this.configuration.monitoringManagerComponent = (AbstractMonitoringManagerComponent)
					this.loadComponent(AbstractMonitoringManagerComponent.PROP_PREFIX, this.configuration.monitoringManagerComponentProperties);

			this.configuration.controlComponent = (AbstractControlComponent)
					this.loadComponent(AbstractControlComponent.PROP_PREFIX, this.configuration.controlComponentProperties);

			this.configuration.modelManagerComponent = (AbstractModelManagerComponent)
					this.loadComponent(AbstractModelManagerComponent.PROP_PREFIX, this.configuration.modelManagerComponentProperties);

			this.configuration.modelUpdaterComponent = (AbstractModelUpdaterComponent)
					this.loadComponent(AbstractModelUpdaterComponent.PROP_PREFIX, this.configuration.modelUpdaterComponentProperties);

			this.configuration.analysisComponent = (AbstractAnalysisComponent)
					this.loadComponent(AbstractAnalysisComponent.PROP_PREFIX, this.configuration.analysisComponentProperties);

			this.configuration.performanceEvaluatorComponent = (AbstractPerformanceEvaluatorComponent)
					this.loadComponent(AbstractPerformanceEvaluatorComponent.PROP_PREFIX, this.configuration.performanceEvaluatorComponentProperties);

			this.configuration.workloadForecasterComponent = (AbstractWorkloadForecasterComponent)
					this.loadComponent(AbstractWorkloadForecasterComponent.PROP_PREFIX, this.configuration.workloadForecasterProperties);

			this.configuration.performancePredictorComponent = (AbstractPerformancePredictorComponent)
					this.loadComponent(AbstractPerformancePredictorComponent.PROP_PREFIX, this.configuration.performancePredictorComponentProperties);

			this.configuration.adaptationPlannerComponent = (AbstractAdaptationPlannerComponent)
					this.loadComponent(AbstractAdaptationPlannerComponent.PROP_PREFIX, this.configuration.adaptationPlannerComponentProperties);

			this.configuration.reconfigurationManagerComponent = (AbstractReconfigurationManagerComponent)
					this.loadComponent(AbstractReconfigurationManagerComponent.PROP_PREFIX, this.configuration.reconfigurationManagerComponentProperties);

			if (!this.configuration.allComponentsInitialized()) {
				throw new IllegalArgumentException("At least one component could not be initialized");
			}

			if (!this.configuration.wireComponents()) {
				throw new IllegalArgumentException("Failed to wire the components. See log for details");
			}

			if (!this.configuration
					.createAndSetComponentContexts(prop.getProperty(FrameworkInstance.DIRECTORYTOWRITE_PROPNAME, System.getProperty("java.io.tmpdir")))) {
				throw new IllegalArgumentException(
						"Failed to set component contexts. See log for details");
			}

		} catch (final Exception exc) {
			LOG.error("An error occured", exc);
			throw new IllegalArgumentException("An error occured: " + exc.getMessage() + " (see log for details)", exc);
		}
	}

	private boolean initComponent(final AbstractSLAsticComponent component, final String componentType) {
		if ((component == null) || !component.init()) {
			LOG.error(componentType + " failed on init");
			return false;
		}
		return true;
	}

	private boolean initAllComponents() {
		return this.initComponent(
				this.configuration.monitoringManagerComponent, "MonitoringManagerComponent")
				&& this.initComponent(this.configuration.controlComponent, "ControlComponent")
				&& this.initComponent(this.configuration.modelManagerComponent, "ModelManagerComponent")
				&& this.initComponent(this.configuration.modelUpdaterComponent, "ModelUpdaterComponent")
				&& this.initComponent(this.configuration.analysisComponent, "AnalysisComponent")
				&& this.initComponent(this.configuration.performanceEvaluatorComponent, "PerformanceEvaluatorComponent")
				&& this.initComponent(this.configuration.workloadForecasterComponent, "WorkloadForecasterComponent")
				&& this.initComponent(this.configuration.performancePredictorComponent, "PerformancePredictorComponent")
				&& this.initComponent(this.configuration.adaptationPlannerComponent, "AdaptationPlannerComponent")
				&& this.initComponent(this.configuration.reconfigurationManagerComponent, "ReconfigurationManagerComponent");
	}

	private void terminateComponent(final AbstractSLAsticComponent component,
			final String componentType, final boolean error) {
		if (component != null) {
			component.terminate(error);
		}
	}

	// TODO: Terminate in reverse order of initialization?
	private void terminateAllComponents(final boolean error) {
		this.terminateComponent(this.configuration.monitoringManagerComponent, "MonitoringManagerComponent", error);
		this.terminateComponent(this.configuration.modelUpdaterComponent, "ModelUpdaterComponent", error);
		this.terminateComponent(this.configuration.modelManagerComponent, "ModelManagerComponent", error);
		this.terminateComponent(this.configuration.analysisComponent, "AnalysisComponent", error);
		this.terminateComponent(this.configuration.performanceEvaluatorComponent, "PerformanceEvaluatorComponent", error);
		this.terminateComponent(this.configuration.workloadForecasterComponent, "WorkloadForecasterComponent", error);
		this.terminateComponent(this.configuration.performancePredictorComponent, "PerformancePredictorComponent", error);
		this.terminateComponent(this.configuration.adaptationPlannerComponent, "AdaptationPlannerComponent", error);
		this.terminateComponent(this.configuration.reconfigurationManagerComponent, "ReconfigurationManagerComponent", error);
		this.terminateComponent(this.configuration.controlComponent, "ControlComponent", error);
	}

	private boolean executeComponent(final AbstractSLAsticComponent component, final String componentType) {
		if ((component == null) || !component.execute()) {
			LOG.error(componentType + " failed to execute");
			return false;
		}
		return true;
	}

	private boolean executeAllComponents() {
		return this.executeComponent(
				this.configuration.monitoringManagerComponent, "MonitoringManagerComponent")
				&& this.executeComponent(this.configuration.controlComponent, "ControlComponent")
				&& this.executeComponent(this.configuration.modelManagerComponent, "ModelManagerComponent")
				&& this.executeComponent(this.configuration.modelUpdaterComponent, "ModelUpdaterComponent")
				&& this.executeComponent(this.configuration.analysisComponent, "AnalysisComponent")
				&& this.executeComponent(this.configuration.performanceEvaluatorComponent, "PerformanceEvaluatorComponent")
				&& this.executeComponent(this.configuration.workloadForecasterComponent, "WorkloadForecasterComponent")
				&& this.executeComponent(this.configuration.performancePredictorComponent, "PerformancePredictorComponent")
				&& this.executeComponent(this.configuration.adaptationPlannerComponent, "AdaptationPlannerComponent")
				&& this.executeComponent(this.configuration.reconfigurationManagerComponent, "ReconfigurationManagerComponent");
	}

	/**
	 * Start instance. The method returns immediately.
	 * 
	 * @return true on success; false otherwise.
	 */
	public boolean run() {

		/**
		 * Register shutdown hook to make sure, that the framework will be shut
		 * down
		 */
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				LOG.info("ShutdownHook notifies SLAstic framework to terminate");
				FrameworkInstance.this.terminate(false); // not setting error flag
			}
		}));

		if (!this.initAllComponents()) {
			LOG.error("init of at least one component failed");
			return false;
		}
		if (this.executeAllComponents()) {
			return true;
		}

		LOG.error("execute of at least one component failed. Will terminare all components.");
		this.terminateAllComponents(true); // terminate error
		return false;
	}

	/**
	 * Initiates a termination of the instance. The value of the parameter error
	 * indicates whether an error occurred.
	 * 
	 * @param error
	 *            true iff an error occurred.
	 */
	public void terminate(final boolean error) {
		if (!this.terminated.getAndSet(true)) {
			this.terminateAllComponents(error);
		}
	}

	/**
	 * Creates a SLAstic framework configuration containing only dummy components.
	 * 
	 * @return
	 */
	public static Properties genDummyConfiguration() {
		final Properties configuration = new Properties();
		/* set classname of monitoring manager component */
		configuration.setProperty(
				AbstractMonitoringManagerComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyMonitoringManagerComponent.class.getName());
		/* set classname of control component */
		configuration.setProperty(
				AbstractControlComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, BasicControlComponent.class.getName());
		/* set classname of model management component */
		configuration.setProperty(
				AbstractModelManagerComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyModelManagerComponent.class.getName());
		/* set classname of model updating component */
		configuration.setProperty(
				AbstractModelUpdaterComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME,
				DummyModelUpdaterComponent.class.getName());
		/* set classname of analysis component */
		configuration.setProperty(
				AbstractAnalysisComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME,
				BasicAnalysisComponent.class.getName());
		/* set classname of performance evaluation component */
		configuration.setProperty(
				AbstractPerformanceEvaluatorComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyPerformanceEvaluatorComponent.class.getName());
		/* set classname of workload forecaster component */
		configuration.setProperty(
				AbstractWorkloadForecasterComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyWorkloadForecasterComponent.class.getName());
		/* set classname of performance predictor component */
		configuration.setProperty(
				AbstractPerformancePredictorComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyPerformancePredictorComponent.class.getName());
		/* set classname of adaptation planning component */
		configuration.setProperty(
				AbstractAdaptationPlannerComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyAdaptationPlannerComponent.class.getName());
		/* set classname of reconfiguration component */
		configuration.setProperty(
				AbstractReconfigurationManagerComponent.PROP_PREFIX + "." + COMPONENT_CLASSNAME_PROPNAME, DummyReconfigurationManagerComponent.class.getName());

		return configuration;
	}
}
