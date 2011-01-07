package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.MemSwapUsageRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.ISynchronousTransformationFilter;

import de.cau.se.slastic.metamodel.core.IEvent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class MemSwapUsageRecordTransformationFilter extends
		AbstractModelReconstructionComponent implements
		ISynchronousTransformationFilter,
		IMemSwapUsageRecordTransformation {

	private static final Log log = LogFactory
			.getLog(MemSwapUsageRecordTransformationFilter.class);

	/**
	 * 
	 * @param modelManager
	 */
	public MemSwapUsageRecordTransformationFilter(
			final ModelManager modelManager) {
		super(modelManager);
	}

	private final static String MEM_SWAP_RESOURCE_NAME = "memSwap";

	@Override
	public MemSwapUsage transformMemSwapUsageRecord(
			final MemSwapUsageRecord memSwapUsageRecord) {

		/* Will become the return value. */
		final MemSwapUsage newUsage =
				MonitoringFactory.eINSTANCE.createMemSwapUsage();

		final ExecutionContainer executionContainer =
				this.lookupOrCreateExecutionContainerByName(memSwapUsageRecord
						.getHostName());

		// TODO: consider resource type

		final Resource resource =
				this.lookupOrCreateResource(
						MemSwapUsageRecordTransformationFilter.MEM_SWAP_RESOURCE_NAME,
						executionContainer);

		// And finally, the simple part:
		newUsage.setTimestamp(memSwapUsageRecord
					.getTimestamp());
		newUsage.setResource(resource);
		
		newUsage.setMemFreeBytes(memSwapUsageRecord.getMemFree());
		newUsage.setMemUsedBytes(memSwapUsageRecord.getMemUsed());
		newUsage.setSwapFreeBytes(memSwapUsageRecord.getSwapFree());
		newUsage.setSwapUsedBytes(memSwapUsageRecord.getSwapUsed());
		
		return newUsage;
	}

	@Override
	public IEvent transform(final IMonitoringRecord record) {
		if (!(record instanceof MemSwapUsageRecord)) {
			return null;
		}

		final MemSwapUsageRecord usageRecord =
				(MemSwapUsageRecord) record;

		return this.transformMemSwapUsageRecord(usageRecord);
	}
}
