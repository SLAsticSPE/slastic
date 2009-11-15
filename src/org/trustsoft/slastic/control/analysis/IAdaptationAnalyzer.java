package org.trustsoft.slastic.control.analysis;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

/**
 * Interface for the Adaptation Analyzer component of the
 * SLAstic.CONTROL-Framework. It is responsible for producing reconfiguration
 * plans and forwarding them to die Reconfiguration Manager component.
 * 
 * @author Lena Stoever
 * 
 */
public interface IAdaptationAnalyzer {
	public void execute();

	public void terminate();

	public void setReconfigurationManager(IReconfigurationManager manager);

	public void setAnalysis(IAnalysis ana);

	/**
	 * This method is called by the Analysis component, if a SLAViolationEvent
	 * has been recognized.
	 * 
	 * @param event
	 */
	public void handle(ISLAsticAnalysisEvent event);
}
