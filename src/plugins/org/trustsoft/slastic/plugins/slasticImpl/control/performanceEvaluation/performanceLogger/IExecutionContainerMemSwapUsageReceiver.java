package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.executionEnvironment.Resource;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IExecutionContainerMemSwapUsageReceiver {

	public void update(final long currentTimestampMillis,
			final Resource resource, final Long memUsedBytes,
			Long memFreeBytes, Long swapUsedBytes, Long swapFreeBytes);
}
