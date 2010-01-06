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

    protected final ISLAsticReconfigurationManager reconfigurationManager;

    public IWorkloadForecaster getWorkloadForecaster() {
        return workloadForecaster;
    }
    protected final IPerformanceEvaluator performanceEvaluator;
    protected final IWorkloadForecaster workloadForecaster;
    protected final IPerformancePredictor performancePredictor;
    protected final IAdaptationPlanner adaptationPlanner;

    public AbstractSLAsticAnalysis() {
        this(null, null, null, null, null, null);
    }

    public AbstractSLAsticAnalysis(
            ISLAsticControl control,
            ISLAsticReconfigurationManager reconfigurationManager,
            IPerformanceEvaluator performanceEvaluator,
            IWorkloadForecaster workloadForecaster,
            IPerformancePredictor performancePredictor,
            IAdaptationPlanner adaptationPlanner) {
        this.reconfigurationManager = reconfigurationManager;
        this.performanceEvaluator = performanceEvaluator;
        this.workloadForecaster = workloadForecaster;
        this.performancePredictor = performancePredictor;
        this.adaptationPlanner = adaptationPlanner;
    }

//    public final ISLAsticReconfigurationManager getReconfigurationManager() {
//        return reconfigurationManager;
//    }
//
//    public final IPerformanceEvaluator getPerformanceEvaluator() {
//        return performanceEvaluator;
//    }
//
//    public final IPerformancePredictor getPerformancePredictor() {
//        return performancePredictor;
//    }
//
//    public final IAdaptationPlanner getAdaptationPlanner() {
//        return adaptationPlanner;
//    }

//    public void setReconfigurationManager(ISLAsticReconfigurationManager manager) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void setAdaptationPlanner(IAdaptationPlanner adaptationPlanner) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void setWorkloadForecaster(IWorkloadForecaster workloadForecaster) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void setPerformanceEvaluator(IPerformanceEvaluator performanceEvaluator) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    public void setPerformancePredictor(IPerformancePredictor performancePredictor) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
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
}
