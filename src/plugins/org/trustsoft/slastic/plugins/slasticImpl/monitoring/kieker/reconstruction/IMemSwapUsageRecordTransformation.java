package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.MemSwapUsageRecord;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IMemSwapUsageRecordTransformation {

	/**
	 * Transforms a Kieker memory/swap utilization record of type $
	 * {@link MemSwapUsageRecord} into a SLAstic execution record of type $
	 * {@link MemSwapUsage}.
	 * 
	 * @param memSwapUsageRecord
	 * @return
	 */
	public MemSwapUsage transformMemSwapUsageRecord(
			MemSwapUsageRecord memSwapUsageRecord);
}
