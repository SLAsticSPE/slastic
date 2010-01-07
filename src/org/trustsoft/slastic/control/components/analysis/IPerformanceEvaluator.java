package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.ISLAsticEvent;

/**
 * This interface represents the Performance Analyzer of the SLAstic.CONTROL
 * framework, that is responsible for analyzing the performance of the dynamic
 * reconfigurable system.
 * 
 * @author Lena Stoever
 * 
 */
public interface IPerformanceEvaluator {
	public void execute();

	public void terminate();

	public void handle(ISLAsticEvent event);

	public void setSLAs(slal.Model slas);

	public void setAnalysis(ISLAsticAnalysis ana);
}
