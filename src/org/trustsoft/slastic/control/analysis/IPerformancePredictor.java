package org.trustsoft.slastic.control.analysis;

public interface IPerformancePredictor {
	public void execute();
	public void terminate();
	public void handle(ISLAsticAnalysisEvent event);

}
