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

    private static final Log log = LogFactory.getLog(SLAsticAdaptationFrameworkInstance.class);
    private SLAsticAdaptationFrameworkConfiguration configuration =
            new SLAsticAdaptationFrameworkConfiguration();

    /**
     * Returns the framework configuration.
     * 
     * @return the framework configuration
     */
    public SLAsticAdaptationFrameworkConfiguration getConfiguration() {
        return configuration;
    }
    public static final String COMPONENT_CLASSNAME_PROPNAME = "classname";

    /**
     * Avoid construction via default constructor
     */
    private SLAsticAdaptationFrameworkInstance() {
    }

    public SLAsticAdaptationFrameworkInstance(final String configurationFilename) {
        this(PropertiesFileUtils.loadPropertiesFile(configurationFilename));
    }

    public SLAsticAdaptationFrameworkInstance(final Properties prop) {
        initConfiguration(prop);
    }

    private void initComponentProperties(final Properties prop)
            throws IllegalArgumentException {
        for (String curPropName : prop.stringPropertyNames()) {
            boolean storedProp = false;
            if (curPropName.startsWith(AbstractPerformanceEvaluatorComponent.PROP_PREFIX
                    + ".")) {
                storedProp = true;
                this.configuration.performanceEvaluatorComponentProperties.setProperty(curPropName.replaceFirst(AbstractPerformanceEvaluatorComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractWorkloadForecasterComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.workloadForecasterProperties.setProperty(curPropName.replaceFirst(AbstractWorkloadForecasterComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractPerformancePredictorComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.performancePredictorComponentProperties.setProperty(curPropName.replaceFirst(AbstractPerformancePredictorComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractAdaptationPlannerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.adaptationPlannerComponentProperties.setProperty(curPropName.replaceFirst(AbstractAdaptationPlannerComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractAnalysisComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.analysisComponentProperties.setProperty(curPropName.replaceFirst(
                        AbstractAnalysisComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractModelManagerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.modelManagerComponentProperties.setProperty(curPropName.replaceFirst(
                        AbstractModelManagerComponent.PROP_PREFIX + ".", ""),
                        prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractModelUpdaterComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.modelUpdaterComponentProperties.setProperty(curPropName.replaceFirst(
                        AbstractModelUpdaterComponent.PROP_PREFIX + ".", ""),
                        prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractControlComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.controlComponentProperties.setProperty(curPropName.replaceFirst(
                        AbstractControlComponent.PROP_PREFIX + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractMonitoringManagerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.monitoringComponentProperties.setProperty(
                        curPropName.replaceFirst(
                        AbstractMonitoringManagerComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (curPropName.startsWith(AbstractReconfigurationManagerComponent.PROP_PREFIX)) {
                storedProp = true;
                this.configuration.reconfigurationManagerComponentProperties.setProperty(
                        curPropName.replaceFirst(
                        AbstractReconfigurationManagerComponent.PROP_PREFIX
                        + ".", ""), prop.getProperty(curPropName));
            }
            if (!storedProp) {
                log.warn("Unknown property name '" + curPropName + "");
            }
        }
    }

    /**
     *
     * @return the instance; null in case an error occured.
     */
    private AbstractSLAsticComponent loadComponent(final String componentPropertiesPrefix, final Properties componentProperties) {
        String classname = componentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
        if (classname == null
                || classname.length() <= 0) {
            log.error("Missing configuration property value for '"
                    + componentPropertiesPrefix + "."
                    + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            return null;
        }

        AbstractSLAsticComponent inst = null;
        try {
            Class cl = Class.forName(classname);
            // FIXME: assert cl instanceof AbstractSLAsticComponent?
            inst = (AbstractSLAsticComponent) cl.newInstance();
            Method m = cl.getMethod("setProperties", Properties.class);
            m.invoke(inst, componentProperties);
            log.info("Loaded and instantiated component ('" + classname
                    + "') with init string '" + componentProperties + "'");
        } catch (Exception ex) {
            inst = null;
            log.fatal("Failed to instantiate component of class '" + classname
                    + "'", ex);
        }
        return inst;
    }

    private void initConfiguration(Properties prop)
            throws IllegalArgumentException {
        try {
            this.initComponentProperties(prop);

            /* Load all components */
            this.configuration.monitoringManagerComponent =
                    (AbstractMonitoringManagerComponent) loadComponent(AbstractMonitoringManagerComponent.PROP_PREFIX, this.configuration.monitoringComponentProperties);

            this.configuration.controlComponent =
                    (AbstractControlComponent) loadComponent(AbstractControlComponent.PROP_PREFIX, this.configuration.controlComponentProperties);

            this.configuration.modelManagerComponent =
                    (AbstractModelManagerComponent) loadComponent(AbstractModelManagerComponent.PROP_PREFIX, this.configuration.modelManagerComponentProperties);

            this.configuration.modelUpdaterComponent =
                    (AbstractModelUpdaterComponent) loadComponent(AbstractModelUpdaterComponent.PROP_PREFIX, this.configuration.modelUpdaterComponentProperties);

            this.configuration.analysisComponent =
                    (AbstractAnalysisComponent) loadComponent(AbstractAnalysisComponent.PROP_PREFIX, this.configuration.analysisComponentProperties);

            this.configuration.performanceEvaluatorComponent =
                    (AbstractPerformanceEvaluatorComponent) loadComponent(AbstractPerformanceEvaluatorComponent.PROP_PREFIX, this.configuration.performanceEvaluatorComponentProperties);

            this.configuration.workloadForecasterComponent =
                    (AbstractWorkloadForecasterComponent) loadComponent(AbstractWorkloadForecasterComponent.PROP_PREFIX, this.configuration.workloadForecasterProperties);

            this.configuration.performancePredictorComponent =
                    (AbstractPerformancePredictorComponent) loadComponent(AbstractPerformancePredictorComponent.PROP_PREFIX, this.configuration.performancePredictorComponentProperties);

            this.configuration.adaptationPlannerComponent =
                    (AbstractAdaptationPlannerComponent) loadComponent(AbstractAdaptationPlannerComponent.PROP_PREFIX, this.configuration.adaptationPlannerComponentProperties);

            this.configuration.reconfigurationManagerComponent =
                    (AbstractReconfigurationManagerComponent) loadComponent(AbstractReconfigurationManagerComponent.PROP_PREFIX, this.configuration.reconfigurationManagerComponentProperties);


            if (!this.configuration.allComponentsInitialized()) {
                throw new IllegalArgumentException(
                        "At least one component could not be initialized");
            }

            if (!this.configuration.wireComponents()) {
                throw new IllegalArgumentException(
                        "Failed to wire the components. See log for details");
            }

        } catch (Exception exc) {
            log.error("An error occured", exc);
            throw new IllegalArgumentException("An error occured: " + exc.getMessage() + " (see log for details)", exc);
        }
    }

   private boolean initComponent(AbstractSLAsticComponent component, String componentType) {
        if (component == null || !component.init()) {
            log.error(componentType + " failed on init");
            return false;
        }
        return true;
    }

    private boolean initAllComponents(){
        return  initComponent(this.configuration.monitoringManagerComponent, "MonitoringManagerComponent")
                && initComponent(this.configuration.controlComponent, "ControlComponent")
                && initComponent(this.configuration.modelManagerComponent, "ModelManagerComponent")
                && initComponent(this.configuration.modelUpdaterComponent, "ModelUpdaterComponent")
                && initComponent(this.configuration.analysisComponent, "AnalysisComponent")
                && initComponent(this.configuration.performanceEvaluatorComponent, "PerformanceEvaluatorComponent")
                && initComponent(this.configuration.workloadForecasterComponent, "WorkloadForecasterComponent")
                && initComponent(this.configuration.performancePredictorComponent, "PerformancePredictorComponent")
                && initComponent(this.configuration.adaptationPlannerComponent, "AdaptationPlannerComponent")
                && initComponent(this.configuration.reconfigurationManagerComponent, "ReconfigurationManagerComponent");
    }

    private void terminateComponent(AbstractSLAsticComponent component, String componentType, boolean error) {
        if (component != null)
            component.terminate(error);
    }

    private void terminateAllComponents(final boolean error){
            terminateComponent(this.configuration.monitoringManagerComponent, "MonitoringManagerComponent", error);
            terminateComponent(this.configuration.controlComponent, "ControlComponent", error);
            terminateComponent(this.configuration.modelManagerComponent, "ModelManagerComponent", error);
            terminateComponent(this.configuration.modelUpdaterComponent, "ModelUpdaterComponent", error);
            terminateComponent(this.configuration.analysisComponent, "AnalysisComponent", error);
            terminateComponent(this.configuration.performanceEvaluatorComponent, "PerformanceEvaluatorComponent", error);
            terminateComponent(this.configuration.workloadForecasterComponent, "WorkloadForecasterComponent", error);
            terminateComponent(this.configuration.performancePredictorComponent, "PerformancePredictorComponent", error);
            terminateComponent(this.configuration.adaptationPlannerComponent, "AdaptationPlannerComponent", error);
            terminateComponent(this.configuration.reconfigurationManagerComponent, "ReconfigurationManagerComponent", error);
    }

    private boolean executeComponent(AbstractSLAsticComponent component, String componentType) {
        if (component == null || !component.execute()) {
            log.error(componentType + " failed to execute");
            return false;
        }
        return true;
    }

    private boolean executeAllComponents(){
        return  executeComponent(this.configuration.monitoringManagerComponent, "MonitoringManagerComponent")
                && executeComponent(this.configuration.controlComponent, "ControlComponent")
                && executeComponent(this.configuration.modelManagerComponent, "ModelManagerComponent")
                && executeComponent(this.configuration.modelUpdaterComponent, "ModelUpdaterComponent")
                && executeComponent(this.configuration.analysisComponent, "AnalysisComponent")
                && executeComponent(this.configuration.performanceEvaluatorComponent, "PerformanceEvaluatorComponent")
                && executeComponent(this.configuration.workloadForecasterComponent, "WorkloadForecasterComponent")
                && executeComponent(this.configuration.performancePredictorComponent, "PerformancePredictorComponent")
                && executeComponent(this.configuration.adaptationPlannerComponent, "AdaptationPlannerComponent")
                && executeComponent(this.configuration.reconfigurationManagerComponent, "ReconfigurationManagerComponent");
    }

    /**
     * Start instance. The method returns immediately.
     * 
     * @return true on success; false otherwise.
     */
    public boolean run() {
        if (!initAllComponents()){
            log.error("init of at least one component failed");
            return false;
        }
        if (executeAllComponents()){
            return true;
        }
        
        log.error("execute of at least one component failed. Will terminare all components.");
        terminateAllComponents(true); // terminate error
        return false;
    }

    /**
     * Initiates a termination of the instance. The value of the parameter
     * error indicates whether an error occured.
     *
     * @param error true iff an error occured.
     */
    public void terminate(final boolean error) {
        this.terminateAllComponents(error);
    }

   /**
     * Creates a SLAstic framework configuration containing only dummy commponents.
     *
     * @return
     */
    public static Properties genDummyConfiguration(){
        Properties configuration =
                new Properties();
        /* set classname of monitoring manager component */
        configuration.setProperty(
                AbstractMonitoringManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyMonitoringManagerComponent.class.getName());
        /* set classname of control component */
        configuration.setProperty(
                AbstractControlComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                BasicControlComponent.class.getName());
        /* set classname of model management component */
        configuration.setProperty(
                AbstractModelManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyModelManagerComponent.class.getName());
        /* set classname of model updating component */
        configuration.setProperty(
                AbstractModelUpdaterComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyModelUpdaterComponent.class.getName());
        /* set classname of analysis component */
        configuration.setProperty(
                AbstractAnalysisComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                BasicAnalysisComponent.class.getName());
        /* set classname of performance evaluation component */
        configuration.setProperty(
                AbstractPerformanceEvaluatorComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyPerformanceEvaluatorComponent.class.getName());
        /* set classname of workload forecaster component */
        configuration.setProperty(
                AbstractWorkloadForecasterComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyWorkloadForecasterComponent.class.getName());
        /* set classname of performance predictor component */
        configuration.setProperty(
                AbstractPerformancePredictorComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyPerformancePredictorComponent.class.getName());
        /* set classname of adaptation planning component */
        configuration.setProperty(
                AbstractAdaptationPlannerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyAdaptationPlannerComponent.class.getName());
        /* set classname of reconfiguration component */
        configuration.setProperty(
                AbstractReconfigurationManagerComponent.PROP_PREFIX+"."+SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME,
                DummyReconfigurationManagerComponent.class.getName());

        return configuration;
    }
}
