/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.AbstractSLAsticControl;
import org.trustsoft.slastic.reconfigurationManager.AbstractSLAsticReconfigurationManager;
import slal.Model;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticAnalysis extends AbstractSLAsticComponent implements ISLAsticAnalysis {

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
        if (this.performanceEvaluator != null) {
            this.performanceEvaluator.execute();
        }
        if (this.workloadForecaster != null) {
            this.workloadForecaster.execute();
        }
        if (this.performancePredictor != null) {
            this.performancePredictor.execute();
        }
        if (this.adaptationPlanner != null) {
            this.adaptationPlanner.execute();
        }

        // TODO: consider return values of delegated execution calls
        return true;
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
