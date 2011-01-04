package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.executionEnvironment.Resource;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public interface IExecutionContainerResourceUtilizationReceiver {

	public void update(final long currentTimestampMillis,
			final Resource resource, final Double utilization);
}
