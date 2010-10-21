package org.trustsoft.slastic.plugins.starter;

import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.BasicControlComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.AbstractWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.analysis.BasicAnalysisComponent;
import org.trustsoft.slastic.control.components.analysis.DummyAdaptationPlannerComponent;
import org.trustsoft.slastic.control.components.analysis.DummyPerformanceEvaluatorComponent;
import org.trustsoft.slastic.control.components.analysis.DummyPerformancePredictorComponent;
import org.trustsoft.slastic.control.components.analysis.DummyWorkloadForecasterComponent;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.control.components.modelManager.DummyModelManagerComponent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.control.components.modelUpdater.DummyModelUpdaterComponent;
import org.trustsoft.slastic.monitoring.AbstractMonitoringManagerComponent;
import org.trustsoft.slastic.monitoring.DummyMonitoringManagerComponent;
import org.trustsoft.slastic.plugins.util.PropertiesFileUtils;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.DummyReconfigurationManagerComponent;

/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticAdaptationFrameworkInstance {

	private static final Log log = LogFactory
			.getLog(SLAsticAdaptationFrameworkInstance.class);
	private final SLAsticAdaptationFrameworkConfiguration configuration =
			new SLAsticAdaptationFrameworkConfiguration();

	/**
	 * Returns the framework configuration.
	 * 
	 * @return the framework configuration
	 */
	public SLAsticAdaptationFrameworkConfiguration getConfiguration() {
		return this.configuration;
	}

	public static final String COMPONENT_CLASSNAME_PROPNAME = "classname";

	/**
	 * Avoid construction via default constructor
	 */
	@SuppressWarnings("unused")
	private SLAsticAdaptationFrameworkInstance() {
	}

	public SLAsticAdaptationFrameworkInstance(final String configurationFilename) {
		this(PropertiesFileUtils.loadPropertiesFile(configurationFilename));
	}

	public SLAsticAdaptationFrameworkInstance(final Properties prop) {
		this.initConfiguration(prop);
	}

	private void initComponentProperties(final Properties prop)
			throws IllegalArgumentException {
		for (final String curPropName : prop.stringPropertyNames()) {
			boolean storedProp = false;
			if (curPropName
					.startsWith(AbstractPerformanceEvaluatorComponent.PROP_PREFIX
							+ ".")) {
				storedProp = true;
				this.configuration.performanceEvaluatorComponentProperties
						.setProperty(
								curPropName
										.replaceFirst(
												AbstractPerformanceEvaluatorComponent.PROP_PREFIX
														+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractWorkloadForecasterComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.workloadForecasterProperties.setProperty(
						curPropName.replaceFirst(
								AbstractWorkloadForecasterComponent.PROP_PREFIX
										+ ".", ""), prop
								.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractPerformancePredictorComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.performancePredictorComponentProperties
						.setProperty(
								curPropName
										.replaceFirst(
												AbstractPerformancePredictorComponent.PROP_PREFIX
														+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractAdaptationPlannerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.adaptationPlannerComponentProperties
						.setProperty(curPropName.replaceFirst(
								AbstractAdaptationPlannerComponent.PROP_PREFIX
										+ ".", ""), prop
								.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractAnalysisComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.analysisComponentProperties.setProperty(
						curPropName
								.replaceFirst(
										AbstractAnalysisComponent.PROP_PREFIX
												+ ".", ""), prop
								.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractModelManagerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.modelManagerComponentProperties
						.setProperty(
								curPropName
										.replaceFirst(
												AbstractModelManagerComponent.PROP_PREFIX
														+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractModelUpdaterComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.modelUpdaterComponentProperties
						.setProperty(
								curPropName
										.replaceFirst(
												AbstractModelUpdaterComponent.PROP_PREFIX
														+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (curPropName.startsWith(AbstractControlComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.controlComponentProperties
						.setProperty(
								curPropName.replaceFirst(
										AbstractControlComponent.PROP_PREFIX
												+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractMonitoringManagerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.monitoringComponentProperties.setProperty(
						curPropName.replaceFirst(
								AbstractMonitoringManagerComponent.PROP_PREFIX
										+ ".", ""), prop
								.getProperty(curPropName));
			}
			if (curPropName
					.startsWith(AbstractReconfigurationManagerComponent.PROP_PREFIX)) {
				storedProp = true;
				this.configuration.reconfigurationManagerComponentProperties
						.setProperty(
								curPropName
										.replaceFirst(
												AbstractReconfigurationManagerComponent.PROP_PREFIX
														+ ".", ""), prop
										.getProperty(curPropName));
			}
			if (!storedProp) {
				SLAsticAdaptationFrameworkInstance.log
						.warn("Unknown property name '" + curPropName + "");
			}
		}
	}

	/**
	 * 
	 * @return the instance; null in case an error occurred.
	 */
	private AbstractSLAsticComponent loadComponent(
			final String componentPropertiesPrefix,
			final Properties componentProperties) {
		final String classname =
				componentProperties
						.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
		if ((classname == null) || (classname.length() <= 0)) {
			SLAsticAdaptationFrameworkInstance.log
					.error("Missing configuration property value for '"
							+ componentPropertiesPrefix
							+ "."
							+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME
							+ "'");
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
			SLAsticAdaptationFrameworkInstance.log
					.info("Loaded and instantiated component ('" + classname
							+ "') with init string '" + componentProperties
							+ "'");
		} catch (final Exception ex) {
			inst = null;
			SLAsticAdaptationFrameworkInstance.log.fatal(
					"Failed to instantiate component of class '" + classname
							+ "'", ex);
		}
		return inst;
	}

	private void initConfiguration(final Properties prop)
			throws IllegalArgumentException {
		try {
			this.initComponentProperties(prop);

			/* Load all components */
			this.configuration.monitoringManagerComponent =
					(AbstractMonitoringManagerComponent) this.loadComponent(
							AbstractMonitoringManagerComponent.PROP_PREFIX,
							this.configuration.monitoringComponentProperties);

			this.configuration.controlComponent =
					(AbstractControlComponent) this.loadComponent(
							AbstractControlComponent.PROP_PREFIX,
							this.configuration.controlComponentProperties);

			this.configuration.modelManagerComponent =
					(AbstractModelManagerComponent) this.loadComponent(
							AbstractModelManagerComponent.PROP_PREFIX,
							this.configuration.modelManagerComponentProperties);

			this.configuration.modelUpdaterComponent =
					(AbstractModelUpdaterComponent) this.loadComponent(
							AbstractModelUpdaterComponent.PROP_PREFIX,
							this.configuration.modelUpdaterComponentProperties);

			this.configuration.analysisComponent =
					(AbstractAnalysisComponent) this.loadComponent(
							AbstractAnalysisComponent.PROP_PREFIX,
							this.configuration.analysisComponentProperties);

			this.configuration.performanceEvaluatorComponent =
					(AbstractPerformanceEvaluatorComponent) this
							.loadComponent(
									AbstractPerformanceEvaluatorComponent.PROP_PREFIX,
									this.configuration.performanceEvaluatorComponentProperties);

			this.configuration.workloadForecasterComponent =
					(AbstractWorkloadForecasterComponent) this.loadComponent(
							AbstractWorkloadForecasterComponent.PROP_PREFIX,
							this.configuration.workloadForecasterProperties);

			this.configuration.performancePredictorComponent =
					(AbstractPerformancePredictorComponent) this
							.loadComponent(
									AbstractPerformancePredictorComponent.PROP_PREFIX,
									this.configuration.performancePredictorComponentProperties);

			this.configuration.adaptationPlannerComponent =
					(AbstractAdaptationPlannerComponent) this
							.loadComponent(
									AbstractAdaptationPlannerComponent.PROP_PREFIX,
									this.configuration.adaptationPlannerComponentProperties);

			this.configuration.reconfigurationManagerComponent =
					(AbstractReconfigurationManagerComponent) this
							.loadComponent(
									AbstractReconfigurationManagerComponent.PROP_PREFIX,
									this.configuration.reconfigurationManagerComponentProperties);

			if (!this.configuration.allComponentsInitialized()) {
				throw new IllegalArgumentException(
						"At least one component could not be initialized");
			}

			if (!this.configuration.wireComponents()) {
				throw new IllegalArgumentException(
						"Failed to wire the components. See log for details");
			}

		} catch (final Exception exc) {
			SLAsticAdaptationFrameworkInstance.log.error("An error occured",
					exc);
			throw new IllegalArgumentException("An error occured: "
					+ exc.getMessage() + " (see log for details)", exc);
		}
	}

	private boolean initComponent(final AbstractSLAsticComponent component,
			final String componentType) {
		if ((component == null) || !component.init()) {
			SLAsticAdaptationFrameworkInstance.log.error(componentType
					+ " failed on init");
			return false;
		}
		return true;
	}

	private boolean initAllComponents() {
		return this.initComponent(
				this.configuration.monitoringManagerComponent,
				"MonitoringManagerComponent")
				&& this.initComponent(this.configuration.controlComponent,
						"ControlComponent")
				&& this.initComponent(this.configuration.modelManagerComponent,
						"ModelManagerComponent")
				&& this.initComponent(this.configuration.modelUpdaterComponent,
						"ModelUpdaterComponent")
				&& this.initComponent(this.configuration.analysisComponent,
						"AnalysisComponent")
				&& this.initComponent(
						this.configuration.performanceEvaluatorComponent,
						"PerformanceEvaluatorComponent")
				&& this.initComponent(
						this.configuration.workloadForecasterComponent,
						"WorkloadForecasterComponent")
				&& this.initComponent(
						this.configuration.performancePredictorComponent,
						"PerformancePredictorComponent")
				&& this.initComponent(
						this.configuration.adaptationPlannerComponent,
						"AdaptationPlannerComponent")
				&& this.initComponent(
						this.configuration.reconfigurationManagerComponent,
						"ReconfigurationManagerComponent");
	}

	private void terminateComponent(final AbstractSLAsticComponent component,
			final String componentType, final boolean error) {
		if (component != null) {
			component.terminate(error);
		}
	}

	private void terminateAllComponents(final boolean error) {
		this.terminateComponent(this.configuration.monitoringManagerComponent,
				"MonitoringManagerComponent", error);
		this.terminateComponent(this.configuration.controlComponent,
				"ControlComponent", error);
		this.terminateComponent(this.configuration.modelManagerComponent,
				"ModelManagerComponent", error);
		this.terminateComponent(this.configuration.modelUpdaterComponent,
				"ModelUpdaterComponent", error);
		this.terminateComponent(this.configuration.analysisComponent,
				"AnalysisComponent", error);
		this.terminateComponent(
				this.configuration.performanceEvaluatorComponent,
				"PerformanceEvaluatorComponent", error);
		this.terminateComponent(this.configuration.workloadForecasterComponent,
				"WorkloadForecasterComponent", error);
		this.terminateComponent(
				this.configuration.performancePredictorComponent,
				"PerformancePredictorComponent", error);
		this.terminateComponent(this.configuration.adaptationPlannerComponent,
				"AdaptationPlannerComponent", error);
		this.terminateComponent(
				this.configuration.reconfigurationManagerComponent,
				"ReconfigurationManagerComponent", error);
	}

	private boolean executeComponent(final AbstractSLAsticComponent component,
			final String componentType) {
		if ((component == null) || !component.execute()) {
			SLAsticAdaptationFrameworkInstance.log.error(componentType
					+ " failed to execute");
			return false;
		}
		return true;
	}

	private boolean executeAllComponents() {
		return this.executeComponent(
				this.configuration.monitoringManagerComponent,
				"MonitoringManagerComponent")
				&& this.executeComponent(this.configuration.controlComponent,
						"ControlComponent")
				&& this.executeComponent(
						this.configuration.modelManagerComponent,
						"ModelManagerComponent")
				&& this.executeComponent(
						this.configuration.modelUpdaterComponent,
						"ModelUpdaterComponent")
				&& this.executeComponent(this.configuration.analysisComponent,
						"AnalysisComponent")
				&& this.executeComponent(
						this.configuration.performanceEvaluatorComponent,
						"PerformanceEvaluatorComponent")
				&& this.executeComponent(
						this.configuration.workloadForecasterComponent,
						"WorkloadForecasterComponent")
				&& this.executeComponent(
						this.configuration.performancePredictorComponent,
						"PerformancePredictorComponent")
				&& this.executeComponent(
						this.configuration.adaptationPlannerComponent,
						"AdaptationPlannerComponent")
				&& this.executeComponent(
						this.configuration.reconfigurationManagerComponent,
						"ReconfigurationManagerComponent");
	}

	/**
	 * Start instance. The method returns immediately.
	 * 
	 * @return true on success; false otherwise.
	 */
	public boolean run() {
		if (!this.initAllComponents()) {
			SLAsticAdaptationFrameworkInstance.log
					.error("init of at least one component failed");
			return false;
		}
		if (this.executeAllComponents()) {
			return true;
		}

		SLAsticAdaptationFrameworkInstance.log
				.error("execute of at least one component failed. Will terminare all components.");
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
		this.terminateAllComponents(error);
	}

	/**
	 * Creates a SLAstic framework configuration containing only dummy
	 * commponents.
	 * 
	 * @return
	 */
	public static Properties genDummyConfiguration() {
		final Properties configuration = new Properties();
		/* set classname of monitoring manager component */
		configuration
				.setProperty(
						AbstractMonitoringManagerComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyMonitoringManagerComponent.class.getName());
		/* set classname of control component */
		configuration
				.setProperty(
						AbstractControlComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						BasicControlComponent.class.getName());
		/* set classname of model management component */
		configuration
				.setProperty(
						AbstractModelManagerComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyModelManagerComponent.class.getName());
		/* set classname of model updating component */
		configuration
				.setProperty(
						AbstractModelUpdaterComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyModelUpdaterComponent.class.getName());
		/* set classname of analysis component */
		configuration
				.setProperty(
						AbstractAnalysisComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						BasicAnalysisComponent.class.getName());
		/* set classname of performance evaluation component */
		configuration
				.setProperty(
						AbstractPerformanceEvaluatorComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyPerformanceEvaluatorComponent.class.getName());
		/* set classname of workload forecaster component */
		configuration
				.setProperty(
						AbstractWorkloadForecasterComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyWorkloadForecasterComponent.class.getName());
		/* set classname of performance predictor component */
		configuration
				.setProperty(
						AbstractPerformancePredictorComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyPerformancePredictorComponent.class.getName());
		/* set classname of adaptation planning component */
		configuration
				.setProperty(
						AbstractAdaptationPlannerComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyAdaptationPlannerComponent.class.getName());
		/* set classname of reconfiguration component */
		configuration
				.setProperty(
						AbstractReconfigurationManagerComponent.PROP_PREFIX
								+ "."
								+ SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
						DummyReconfigurationManagerComponent.class.getName());

		return configuration;
	}
}
