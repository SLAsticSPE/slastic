package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

/**
 * This Interface represents the Analysis component of the SLAstic.CONTROL framework. It holds different componente that are responsible for different analysis'.
 * @author Lena Stoever
 *
 */
public interface IAnalysis {
	
	public void setReconfigurationManager(IReconfigurationManager manager);
	public void setAdaptationAnalyzer(IAdaptationPlanner adaptationAnalyzer);
	public void setWorkloadAnalyzer(IWorkloadForecaster workloadAnalyzer);
	public void setPerformanceAnalyzer(IPerformanceEvaluator performanceAnalyzer);
	public void setPerformancePredictor(IPerformancePredictor performancePredictor);
	
	/**
	 * method for delegating ISlAsticAnalysisEvents to the belonging analysis objects.s
	 * @param evt
	 */
	public void handleInternalEvent(ISLAsticAnalysisEvent evt);
	
	public void setSLAs(slal.Model slas);
	
	public void execute();
	public void terminate();
}
