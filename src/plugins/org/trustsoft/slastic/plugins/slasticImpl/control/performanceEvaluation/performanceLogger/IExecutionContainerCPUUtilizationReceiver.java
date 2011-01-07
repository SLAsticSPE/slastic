package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.executionEnvironment.Resource;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IExecutionContainerCPUUtilizationReceiver {

	public void update(final long currentTimestampMillis,
			final Resource resource, final Double user, final Double system,
			Double wait, Double nice, Double irq, Double combined, Double idle);
}
