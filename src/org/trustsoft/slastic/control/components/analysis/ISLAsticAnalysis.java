package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.ISLAsticComponent;
import org.trustsoft.slastic.control.components.ISLAsticControl;
import org.trustsoft.slastic.reconfigurationManager.ISLAsticReconfigurationManager;

/**
 * This Interface represents the Analysis component of the SLAstic.CONTROL framework. It holds different componente that are responsible for different analysis'.
 * @author Lena Stoever
 *
 */
public interface ISLAsticAnalysis extends ISLAsticComponent {

    /**
     * method for delegating ISlAsticAnalysisEvents to the belonging analysis
     * objects
     *
     * @param evt
     */
    public void handleInternalEvent(ISLAsticAnalysisEvent evt);

    // TODO: remove
    public void setSLAs(slal.Model slas);

    public IAdaptationPlanner getAdaptationPlanner();

    public void setAdaptationPlanner(IAdaptationPlanner adaptationPlanner);

    public IPerformanceEvaluator getPerformanceEvaluator();

    public void setPerformanceEvaluator(IPerformanceEvaluator performanceEvaluator);

    public IPerformancePredictor getPerformancePredictor();

    public void setPerformancePredictor(IPerformancePredictor performancePredictor);

    public ISLAsticReconfigurationManager getReconfigurationManager();

    public ISLAsticControl getParentControlComponent();

    public void setParentControlComponent(ISLAsticControl parentControlComponent);

    public IWorkloadForecaster getWorkloadForecaster();

    public void setWorkloadForecaster(IWorkloadForecaster workloadForecaster);
}
