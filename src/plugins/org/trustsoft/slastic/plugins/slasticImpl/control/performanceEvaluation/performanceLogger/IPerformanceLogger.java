package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IPerformanceLogger {

	/**
	 * 
	 */
	public abstract void closeLogs();

	/**
	 * 
	 * @param logger
	 */
	public abstract void addAndRegisterLoggerAsSubscriber(final AbstractPerformanceMeasureLogger<?> logger);

}
