package org.trustsoft.slastic.control.analysis;

/**
 * This interface represents the Workload Analyzer component of the
 * SLAstic.CONTROL framework. It is responsible for the analysis and prediction
 * of workload data.
 * 
 * @author Lena Stoever
 * 
 */
public interface IWorkloadAnalyzer {
	public void execute();

	public void terminate();

	public void handle(ISLAsticAnalysisEvent event);

	public void setAnalysis(IAnalysis ana);

}
