/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractSLAsticControl;
import org.trustsoft.slastic.reconfiguration.AbstractSLAsticReconfigurationManager;
import slal.Model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticAnalysis extends AbstractSLAsticComponent implements ISLAsticAnalysis {

    private static final Log log = LogFactory.getLog(AbstractSLAsticControl.class);
    private AbstractSLAsticControl parentControlComponent;
    private AbstractPerformanceEvaluator performanceEvaluator;
    private AbstractWorkloadForecaster workloadForecaster;
    private AbstractPerformancePredictor performancePredictor;
    private AbstractAdaptationPlanner adaptationPlanner;

    // TODO: remove
    public void setSLAs(Model slas) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute() {
        boolean success = true;

        if (this.performanceEvaluator != null) {
            if (!this.performanceEvaluator.execute()) {
                log.error("Failed to execute performanceEvaluator (" + this.performanceEvaluator + ")");
                success = false;
            }
        }
        if (success && this.workloadForecaster != null) {
            if (!this.workloadForecaster.execute()) {
                log.error("Failed to execute workloadForecaster (" + this.workloadForecaster + ")");
                success = false;
            }
        }
       if (success && this.performancePredictor != null) {
            if (!this.performancePredictor.execute()) {
                log.error("Failed to execute performancePredictor (" + this.performancePredictor + ")");
                success = false;
            }
        }
       if (success && this.adaptationPlanner != null) {
            if (!this.adaptationPlanner.execute()) {
                log.error("Failed to execute adaptationPlanner (" + this.adaptationPlanner + ")");
                success = false;
            }
        }

        if (!success){ // terminate all components
            if (this.performanceEvaluator != null) this.performanceEvaluator.terminate();
            if (this.workloadForecaster != null) this.workloadForecaster.terminate();
            if (this.performancePredictor != null) this.performancePredictor.terminate();
            if (this.adaptationPlanner != null) this.adaptationPlanner.terminate();
        }

        return success;
    }

    public void terminate() {
        if (this.performanceEvaluator != null) {
            this.performanceEvaluator.terminate();
        }
        if (this.workloadForecaster != null) {
            this.workloadForecaster.terminate();
        }
        if (this.performancePredictor != null) {
            this.performancePredictor.terminate();
        }
        if (this.adaptationPlanner != null) {
            this.adaptationPlanner.terminate();
        }
    }

    public final AbstractAdaptationPlanner getAdaptationPlanner() {
        return adaptationPlanner;
    }

    public final void setAdaptationPlanner(AbstractAdaptationPlanner adaptationPlanner) {
        this.adaptationPlanner = adaptationPlanner;
    }

    public final AbstractPerformanceEvaluator getPerformanceEvaluator() {
        return performanceEvaluator;
    }

    public final void setPerformanceEvaluator(AbstractPerformanceEvaluator performanceEvaluator) {
        this.performanceEvaluator = performanceEvaluator;
    }

    public final AbstractPerformancePredictor getPerformancePredictor() {
        return performancePredictor;
    }

    public final void setPerformancePredictor(AbstractPerformancePredictor performancePredictor) {
        this.performancePredictor = performancePredictor;
    }

    public final AbstractSLAsticReconfigurationManager getReconfigurationManager() {
        return getParentControlComponent().getReconfigurationManager();
    }

    public final AbstractSLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(AbstractSLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }

    public final AbstractWorkloadForecaster getWorkloadForecaster() {
        return workloadForecaster;
    }

    public final void setWorkloadForecaster(AbstractWorkloadForecaster workloadForecaster) {
        this.workloadForecaster = workloadForecaster;
    }
}
