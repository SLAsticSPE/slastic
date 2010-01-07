package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.ISLAsticEvent;

/**
 * This interface represents the Performance Predictor component of the
 * SLAstic.CONTROL framework. It is responsible for predicting the performance
 * of the dynamic reconfigurable system.
 * 
 * @author Lena Stoever
 * 
 */
public interface IPerformancePredictor {
	public void execute();

	public void terminate();

	public void handle(ISLAsticEvent event);

	public void setAnalysis(ISLAsticAnalysis ana);

}
