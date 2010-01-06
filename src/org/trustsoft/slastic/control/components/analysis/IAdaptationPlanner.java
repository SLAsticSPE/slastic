package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.reconfigurationManager.IReconfigurationManager;

/**
 * Interface for the Adaptation Analyzer component of the
 * SLAstic.CONTROL-Framework. It is responsible for producing reconfiguration
 * plans and forwarding them to the Reconfiguration Manager component.
 * 
 * @author Lena Stoever
 * 
 */
public interface IAdaptationPlanner {
	public void execute();

	public void terminate();

	public void setReconfigurationManager(IReconfigurationManager manager);

	public void setAnalysis(ISLAsticAnalysis ana);

	/**
	 * This method is called by the Analysis component, if a SLAViolationEvent
	 * has been recognized.
	 * 
	 * @param event
	 */
	public void handle(ISLAsticAnalysisEvent event);
}