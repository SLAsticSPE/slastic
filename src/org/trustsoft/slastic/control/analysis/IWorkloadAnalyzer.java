package org.trustsoft.slastic.control.analysis;

public interface IWorkloadAnalyzer {
	public void execute();
	public void terminate();
	public void handle(ISLAsticAnalysisEvent event);

}
