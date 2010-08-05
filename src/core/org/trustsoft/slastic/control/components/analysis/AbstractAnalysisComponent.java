package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractAnalysisComponent extends AbstractSLAsticComponent {

    private static final Log log = LogFactory.getLog(AbstractControlComponent.class);
    public static final String PROP_PREFIX = "slastic.control.analysis";
    private AbstractControlComponent parentControlComponent;
    private AbstractPerformanceEvaluatorComponent performanceEvaluator;
    private AbstractWorkloadForecasterComponent workloadForecaster;
    private AbstractPerformancePredictorComponent performancePredictor;
    private AbstractAdaptationPlannerComponent adaptationPlanner;

    public final AbstractAdaptationPlannerComponent getAdaptationPlanner() {
        return adaptationPlanner;
    }

    public final void setAdaptationPlanner(final AbstractAdaptationPlannerComponent adaptationPlanner) {
        this.adaptationPlanner = adaptationPlanner;
    }

    public final AbstractPerformanceEvaluatorComponent getPerformanceEvaluator() {
        return performanceEvaluator;
    }

    public final void setPerformanceEvaluator(final AbstractPerformanceEvaluatorComponent performanceEvaluator) {
        this.performanceEvaluator = performanceEvaluator;
    }

    public final AbstractPerformancePredictorComponent getPerformancePredictor() {
        return performancePredictor;
    }

    public final void setPerformancePredictor(final AbstractPerformancePredictorComponent performancePredictor) {
        this.performancePredictor = performancePredictor;
    }

    public final AbstractReconfigurationManagerComponent getReconfigurationManager() {
        return getParentControlComponent().getReconfigurationManager();
    }

    public final AbstractControlComponent getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(final AbstractControlComponent parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }

    public final AbstractWorkloadForecasterComponent getWorkloadForecaster() {
        return workloadForecaster;
    }

    public final void setWorkloadForecaster(final AbstractWorkloadForecasterComponent workloadForecaster) {
        this.workloadForecaster = workloadForecaster;
    }
}
