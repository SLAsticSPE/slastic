/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.ISLAsticControl;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;
import slal.Model;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticAnalysis extends AbstractSLAsticComponent implements ISLAsticAnalysis {

    private ISLAsticControl parentControlComponent;

    private IPerformanceEvaluator performanceEvaluator;
    private IWorkloadForecaster workloadForecaster;
    private IPerformancePredictor performancePredictor;
    private IAdaptationPlanner adaptationPlanner;

    public void handleInternalEvent(ISLAsticAnalysisEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

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

   public final IAdaptationPlanner getAdaptationPlanner() {
        return adaptationPlanner;
    }

    public final void setAdaptationPlanner(IAdaptationPlanner adaptationPlanner) {
        this.adaptationPlanner = adaptationPlanner;
    }

    public final IPerformanceEvaluator getPerformanceEvaluator() {
        return performanceEvaluator;
    }

    public final void setPerformanceEvaluator(IPerformanceEvaluator performanceEvaluator) {
        this.performanceEvaluator = performanceEvaluator;
    }

    public final IPerformancePredictor getPerformancePredictor() {
        return performancePredictor;
    }

    public final void setPerformancePredictor(IPerformancePredictor performancePredictor) {
        this.performancePredictor = performancePredictor;
    }

    public final ISLAsticReconfigurationManager getReconfigurationManager() {
        return getParentControlComponent().getReconfigurationManager();
    }

   public final ISLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(ISLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }

    public final IWorkloadForecaster getWorkloadForecaster() {
        return workloadForecaster;
    }

    public final void setWorkloadForecaster(IWorkloadForecaster workloadForecaster) {
        this.workloadForecaster = workloadForecaster;
    }
}
