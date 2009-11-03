package org.trustsoft.slastic.control.analysis;

public interface IWorkloadAnalyzer {
	public void execute();
	public void terminate();
	public void handle(ISLAsticAnalysisEvent event);
	public void setAnalysis(IAnalysis ana);

}
