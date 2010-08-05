package org.trustsoft.slastic.plugins.starter;

import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
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
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticAdaptationFrameworkInstance {

    private static final Log log = LogFactory.getLog(SLAsticAdaptationFrameworkInstance.class);
    private SLAsticAdaptationFrameworkConfiguration configuration =
            new SLAsticAdaptationFrameworkConfiguration();
    
    private final static String COMPONENT_CLASSNAME_PROPNAME = "classname";

    /* Avoid construction via default constructor */
    private SLAsticAdaptationFrameworkInstance() { }

    public SLAsticAdaptationFrameworkInstance(Properties prop) {
        loadProperties(prop);
    }

    private void initProperties(final Properties prop)
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
                this.configuration.monitoringManagerComponentProperties.setProperty(
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

    private void loadProperties(Properties prop)
            throws IllegalArgumentException {
        try {
            this.initProperties(prop);

            /* Load all components */
            String monitoringComponentClassnameProperty = this.configuration.monitoringManagerComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (monitoringComponentClassnameProperty == null
                    || monitoringComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractMonitoringManagerComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            }
            this.configuration.monitoringManagerComponent = (AbstractMonitoringManagerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                    monitoringComponentClassnameProperty, this.configuration.monitoringManagerComponentProperties);

            String controlComponentClassnameProperty = this.configuration.controlComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (controlComponentClassnameProperty == null
                    || controlComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractControlComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                this.configuration.controlComponent = (AbstractControlComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        controlComponentClassnameProperty, this.configuration.controlComponentProperties);
            }

            String modelManagerComponentClassnameProperty = this.configuration.modelManagerComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (modelManagerComponentClassnameProperty == null
                    || modelManagerComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractModelManagerComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                this.configuration.modelManagerComponent = (AbstractModelManagerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        modelManagerComponentClassnameProperty,
                        this.configuration.modelManagerComponentProperties);
            }

            String modelUpdaterComponentClassnameProperty = this.configuration.modelUpdaterComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (modelUpdaterComponentClassnameProperty == null
                    || modelUpdaterComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractModelUpdaterComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                this.configuration.modelUpdaterComponent = (AbstractModelUpdaterComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        modelUpdaterComponentClassnameProperty,
                        this.configuration.modelUpdaterComponentProperties);
            }

            String analysisComponentClassnameProperty = this.configuration.analysisComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (analysisComponentClassnameProperty == null
                    || analysisComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for '"
                        + AbstractAnalysisComponent.PROP_PREFIX + "."
                        + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME + "'");
            } else {
                this.configuration.analysisComponent = (AbstractAnalysisComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        analysisComponentClassnameProperty, this.configuration.analysisComponentProperties);
            }

            String performanceEvaluatorComponentClassnameProperty = this.configuration.performanceEvaluatorComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            // Note: a performance evaluator component is not mandatory
            if (performanceEvaluatorComponentClassnameProperty != null
                    && performanceEvaluatorComponentClassnameProperty.length() > 0) {
                this.configuration.performanceEvaluatorComponent = (AbstractPerformanceEvaluatorComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        performanceEvaluatorComponentClassnameProperty,
                        this.configuration.performanceEvaluatorComponentProperties);
            }

            String workloadForecasterComponentClassnameProperty = this.configuration.workloadForecasterProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            // Note: a workload forecaster component is not mandatory
            if (workloadForecasterComponentClassnameProperty != null
                    && workloadForecasterComponentClassnameProperty.length() > 0) {
                this.configuration.workloadForecasterComponent = (AbstractWorkloadForecasterComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        workloadForecasterComponentClassnameProperty,
                        this.configuration.workloadForecasterProperties);
            }

            String performancePredictorComponentClassnameProperty = this.configuration.performancePredictorComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            // Note: a performance predictor component is not mandatory
            if (performancePredictorComponentClassnameProperty != null
                    && performancePredictorComponentClassnameProperty.length() > 0) {
                this.configuration.performancePredictorComponent = (AbstractPerformancePredictorComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        performancePredictorComponentClassnameProperty,
                        this.configuration.performancePredictorComponentProperties);
            }

            String adaptationPlannerComponentClassnameProperty = this.configuration.adaptationPlannerComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            // Note: a performance predictor component is not mandatory
            if (adaptationPlannerComponentClassnameProperty != null
                    && adaptationPlannerComponentClassnameProperty.length() > 0) {
                this.configuration.adaptationPlannerComponent = (AbstractAdaptationPlannerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                        adaptationPlannerComponentClassnameProperty,
                        this.configuration.adaptationPlannerComponentProperties);
            }

            String reconfigurationManagerComponentClassnameProperty = this.configuration.reconfigurationManagerComponentProperties.getProperty(SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME);
            if (reconfigurationManagerComponentClassnameProperty == null
                    || reconfigurationManagerComponentClassnameProperty.length() <= 0) {
                log.error("Missing configuration property value for "
                        + AbstractReconfigurationManagerComponent.PROP_PREFIX
                        + "." + SLAsticAdaptationFrameworkInstance.COMPONENT_CLASSNAME_PROPNAME
                        + "'");
            }
            this.configuration.reconfigurationManagerComponent = (AbstractReconfigurationManagerComponent) AbstractSLAsticComponent.loadAndInitSLAsticComponentFromClassname(
                    reconfigurationManagerComponentClassnameProperty,
                    this.configuration.reconfigurationManagerComponentProperties);

            /* Assert that ALL components are available */
            boolean success = true;
            if (this.configuration.reconfigurationManagerComponent == null) {
                log.error("reconfigurationManagerComponent is null");
                success = false;
            }
            if (this.configuration.controlComponent == null) {
                log.error("slasticCtrlComponent is null");
                success = false;
            }
            if (this.configuration.modelManagerComponent == null) {
                log.error("modelManagerComponent is null");
                success = false;
            }
            if (this.configuration.modelUpdaterComponent == null) {
                log.error("modelUpdaterComponent is null");
                success = false;
            }
            if (this.configuration.analysisComponent == null) {
                log.error("analysisComponent is null");
                success = false;
            }
            if (!success) {
                throw new IllegalArgumentException(
                        "Failed to load at least one component");
            }

            /* "wire" the components */
            this.configuration.monitoringManagerComponent.setController(this.configuration.controlComponent);
            this.configuration.reconfigurationManagerComponent.setControlComponent(this.configuration.controlComponent);

            this.configuration.controlComponent.setReconfigurationManager(this.configuration.reconfigurationManagerComponent); // TODO:
            // fix!
            this.configuration.controlComponent.setAnalysis(this.configuration.analysisComponent);
            this.configuration.controlComponent.setModelManager(this.configuration.modelManagerComponent);
            this.configuration.controlComponent.setModelUpdater(this.configuration.modelUpdaterComponent);

            this.configuration.modelManagerComponent.setParentControlComponent(this.configuration.controlComponent);
            this.configuration.modelUpdaterComponent.setParentControlComponent(this.configuration.controlComponent);
            this.configuration.modelUpdaterComponent.setModelManager(this.configuration.modelManagerComponent);

            this.configuration.analysisComponent.setParentControlComponent(this.configuration.controlComponent);
            this.configuration.analysisComponent.setPerformanceEvaluator(this.configuration.performanceEvaluatorComponent);
            this.configuration.analysisComponent.setWorkloadForecaster(this.configuration.workloadForecasterComponent);
            this.configuration.analysisComponent.setPerformancePredictor(this.configuration.performancePredictorComponent);
            this.configuration.analysisComponent.setAdaptationPlanner(this.configuration.adaptationPlannerComponent);

            if (this.configuration.performanceEvaluatorComponent != null) {
                this.configuration.performanceEvaluatorComponent.setParentAnalysisComponent(this.configuration.analysisComponent);
            }
            if (this.configuration.workloadForecasterComponent != null) {
                this.configuration.workloadForecasterComponent.setParentAnalysisComponent(this.configuration.analysisComponent);
            }
            if (this.configuration.performancePredictorComponent != null) {
                this.configuration.performancePredictorComponent.setParentAnalysisComponent(this.configuration.analysisComponent);
            }
            if (this.configuration.adaptationPlannerComponent != null) {
                this.configuration.adaptationPlannerComponent.setParentAnalysisComponent(this.configuration.analysisComponent);
            }

            /* Initialize event handling */
            this.configuration.controlComponent.addListener(this.configuration.modelManagerComponent);
            this.configuration.controlComponent.addListener(this.configuration.modelUpdaterComponent);
            if (this.configuration.performanceEvaluatorComponent != null) {
                this.configuration.controlComponent.addListener(this.configuration.performanceEvaluatorComponent);
                this.configuration.performanceEvaluatorComponent.setSimpleSLAsticEventService(this.configuration.controlComponent);
            }
            if (this.configuration.workloadForecasterComponent != null) {
                this.configuration.controlComponent.addListener(this.configuration.workloadForecasterComponent);
                this.configuration.workloadForecasterComponent.setSimpleSLAsticEventService(this.configuration.controlComponent);
            }
            if (this.configuration.performancePredictorComponent != null) {
                this.configuration.controlComponent.addListener(this.configuration.performancePredictorComponent);
                this.configuration.performancePredictorComponent.setSimpleSLAsticEventService(this.configuration.controlComponent);
            }
            if (this.configuration.adaptationPlannerComponent != null) {
                this.configuration.controlComponent.addListener(this.configuration.adaptationPlannerComponent);
                this.configuration.adaptationPlannerComponent.setSimpleSLAsticEventService(this.configuration.controlComponent);
            }

        } catch (Exception exc) {
            log.error("An error occured", exc);
            throw new IllegalArgumentException("An error occured", exc);
        }
    }

    /** Start instance. The method returns immediately. */
    public void run() {
        this.configuration.controlComponent.execute();
        this.configuration.reconfigurationManagerComponent.execute();
        this.configuration.monitoringManagerComponent.execute();
    }

    /** Terminate instance */
    public void terminate(final boolean error) {
        this.configuration.controlComponent.terminate(error);
        this.configuration.monitoringManagerComponent.terminate(error);
        this.configuration.reconfigurationManagerComponent.terminate(error);
    }
}
