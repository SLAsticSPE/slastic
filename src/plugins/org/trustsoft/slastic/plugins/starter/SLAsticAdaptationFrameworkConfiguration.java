package org.trustsoft.slastic.plugins.starter;

import java.util.Properties;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;

/**
 *
 * @author Andre van Hoorn
 */
public class SLAsticAdaptationFrameworkConfiguration {

    private static final Log log = LogFactory.getLog(SLAsticAdaptationFrameworkInstance.class);
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
    public final Properties monitoringComponentProperties = new Properties();
    public final Properties controlComponentProperties = new Properties();
    public final Properties modelManagerComponentProperties = new Properties();
    public final Properties modelUpdaterComponentProperties = new Properties();
    public final Properties analysisComponentProperties = new Properties();
    public final Properties performanceEvaluatorComponentProperties = new Properties();
    public final Properties workloadForecasterProperties = new Properties();
    public final Properties performancePredictorComponentProperties = new Properties();
    public final Properties adaptationPlannerComponentProperties = new Properties();
    public final Properties reconfigurationManagerComponentProperties = new Properties();

    /**
     * Tests if the value of component is non-null and logs and additionally
     * logs and error that the component of type componentType is null.
     *
     * @param component
     * @param identifier
     * @return true if and only if component is not null, false otherwise
     */
    private boolean isComponentInitialized(AbstractSLAsticComponent component, String componentType) {
        if (component == null) {
            log.error(componentType + " not initialized");
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
        return isComponentInitialized(monitoringManagerComponent, "MonitoringManagerComponent")
                && isComponentInitialized(controlComponent, "ControlComponent")
                && isComponentInitialized(modelManagerComponent, "ModelManagerComponent")
                && isComponentInitialized(modelUpdaterComponent, "ModelUpdaterComponent")
                && isComponentInitialized(analysisComponent, "AnalysisComponent")
                && isComponentInitialized(performanceEvaluatorComponent, "PerformanceEvaluatorComponent")
                && isComponentInitialized(workloadForecasterComponent, "WorkloadForecasterComponent")
                && isComponentInitialized(performancePredictorComponent, "PerformancePredictorComponent")
                && isComponentInitialized(adaptationPlannerComponent, "AdaptationPlannerComponent")
                && isComponentInitialized(reconfigurationManagerComponent, "ReconfigurationManagerComponent");
    }



    public boolean wireComponents() {
        if (!allComponentsInitialized()){
            log.error("At least one component is null");
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

    private void wireReconfigurationComponent(){
        this.reconfigurationManagerComponent.setControlComponent(this.controlComponent);
    }
}
