package org.trustsoft.slastic.control.components.analysis;

/**
 * This interface represents the Workload Analyzer component of the
 * SLAstic.CONTROL framework. It is responsible for the analysis and prediction
 * of workload data.
 * 
 * @author Lena Stoever
 * 
 */
public interface IWorkloadForecaster {
	public void execute();

	public void terminate();

	public void handle(ISLAsticAnalysisEvent event);

	public void setAnalysis(ISLAsticAnalysis ana);

}