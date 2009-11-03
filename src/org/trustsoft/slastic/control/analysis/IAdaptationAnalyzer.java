package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

public interface IAdaptationAnalyzer {
	public void execute();
	public void terminate();
	public void setReconfigurationManager(IReconfigurationManager manager);
	public void setAnalysis(IAnalysis ana);
	public void handle(ISLAsticAnalysisEvent event);
}
