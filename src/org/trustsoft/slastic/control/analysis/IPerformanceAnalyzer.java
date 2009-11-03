package org.trustsoft.slastic.control.analysis;

public interface IPerformanceAnalyzer {
	public void execute();
	public void terminate();
	public void handle(ISLAsticAnalysisEvent event);
	
	public void setSLAs(slal.Model slas);
	public void setAnalysis(IAnalysis ana);
}
