package org.trustsoft.slastic.plugins.starter;

import java.lang.reflect.Method;
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
import org.trustsoft.slastic.plugins.util.PropertiesFileReader;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

/**
 * @author Andre van Hoorn, Lena Stoever
 */
public class SLAsticAdaptationFrameworkInstance {

    private static final Log log = LogFactory.getLog(SLAsticAdaptationFrameworkInstance.class);
    private SLAsticAdaptationFrameworkConfiguration configuration =
            new SLAsticAdaptationFrameworkConfiguration();
    public static final String COMPONENT_CLASSNAME_PROPNAME = "classname";

    /* Avoid construction via default constructor */
    private SLAsticAdaptationFrameworkInstance() {
    }

    public SLAsticAdaptationFrameworkInstance(final String configurationFilename) {
        this(PropertiesFileReader.loadPropertiesFile(configurationFilename));
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
            Method m = cl.getMethod("init", Properties.class);
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
