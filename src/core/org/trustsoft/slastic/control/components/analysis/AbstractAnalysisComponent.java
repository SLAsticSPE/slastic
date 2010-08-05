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

    @Override
    public boolean init() {
        boolean success = true;

        if (this.performanceEvaluator != null || !this.performanceEvaluator.init()) {
            log.error("Failed to init performanceEvaluator (" + this.performanceEvaluator + ")");
            success = false;
        }
        if (success && (this.workloadForecaster != null  || !this.workloadForecaster.init())) {
                log.error("Failed to init workloadForecaster (" + this.workloadForecaster + ")");
                success = false;
        }
        if (success && (this.performancePredictor != null || !this.performancePredictor.init())) {
                log.error("Failed to init performancePredictor (" + this.performancePredictor + ")");
                success = false;
        }
        if (success && (this.adaptationPlanner != null || !this.adaptationPlanner.init())) {
                log.error("Failed to init adaptationPlanner (" + this.adaptationPlanner + ")");
                success = false;
        }
        return success;
    }

    @Override
    public boolean execute() {
        boolean success = true;

        if (this.performanceEvaluator != null || !this.performanceEvaluator.execute()) {
            log.error("Failed to execute performanceEvaluator (" + this.performanceEvaluator + ")");
            success = false;
        }
        if (success && (this.workloadForecaster != null  || !this.workloadForecaster.execute())) {
                log.error("Failed to execute workloadForecaster (" + this.workloadForecaster + ")");
                success = false;
        }
        if (success && (this.performancePredictor != null || !this.performancePredictor.execute())) {
                log.error("Failed to execute performancePredictor (" + this.performancePredictor + ")");
                success = false;
        }
        if (success && (this.adaptationPlanner != null || !this.adaptationPlanner.execute())) {
                log.error("Failed to execute adaptationPlanner (" + this.adaptationPlanner + ")");
                success = false;
        }

        if (!success) { // terminate all components
            if (this.performanceEvaluator != null) {
                this.performanceEvaluator.terminate(false);
            }
            if (this.workloadForecaster != null) {
                this.workloadForecaster.terminate(false);
            }
            if (this.performancePredictor != null) {
                this.performancePredictor.terminate(false);
            }
            if (this.adaptationPlanner != null) {
                this.adaptationPlanner.terminate(false);
            }
        }

        return success;
    }

    @Override
    public void terminate(final boolean error) {
        if (this.performanceEvaluator != null) {
            this.performanceEvaluator.terminate(error);
        }
        if (this.workloadForecaster != null) {
            this.workloadForecaster.terminate(error);
        }
        if (this.performancePredictor != null) {
            this.performancePredictor.terminate(error);
        }
        if (this.adaptationPlanner != null) {
            this.adaptationPlanner.terminate(error);
        }
    }

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
