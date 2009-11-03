package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;


public interface IAnalysis {
	
	public void setReconfigurationManager(IReconfigurationManager manager);
	public void setAdaptationAnalyzer(IAdaptationAnalyzer adaptationAnalyzer);
	public void setWorkloadAnalyzer(IWorkloadAnalyzer workloadAnalyzer);
	public void setPerformanceAnalyzer(IPerformanceAnalyzer performanceAnalyzer);
	public void setPerformancePredictor(IPerformancePredictor performancePredictor);
	
	public void handleInternalEvent(ISLAsticAnalysisEvent evt);
	
	public void setSLAs(slal.Model slas);
	
	public void execute();
	public void terminate();
}
