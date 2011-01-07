package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.CPUUtilizationRecord;
import kieker.common.record.IMonitoringRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.ISynchronousTransformationFilter;

import de.cau.se.slastic.metamodel.core.IEvent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class CPUUtilizationRecordTransformationFilter extends
		AbstractModelReconstructionComponent implements
		ISynchronousTransformationFilter,
		ICPUUtilizationRecordTransformation {

	private static final Log log = LogFactory
			.getLog(CPUUtilizationRecordTransformationFilter.class);

	/**
	 * 
	 * @param modelManager
	 */
	public CPUUtilizationRecordTransformationFilter(
			final ModelManager modelManager) {
		super(modelManager);
	}

	private final static String CPU_RESOURCE_NAME_PREFX = "cpu";

	@Override
	public CPUUtilization transformCPUUtilizationRecord(
			final CPUUtilizationRecord cpuUtilizationRecord) {

		/* Will become the return value. */
		final CPUUtilization newUtilization =
				MonitoringFactory.eINSTANCE.createCPUUtilization();

		final ExecutionContainer executionContainer =
				this.lookupOrCreateExecutionContainerByName(cpuUtilizationRecord
						.getHostName());

		// TODO: consider resource type

		final Resource resource =
				this.lookupOrCreateResource(
						CPUUtilizationRecordTransformationFilter.CPU_RESOURCE_NAME_PREFX
								+ cpuUtilizationRecord.getCpuID(),
						executionContainer);

		// And finally, the simple part:
		newUtilization.setTimestamp(cpuUtilizationRecord
					.getTimestamp());
		newUtilization.setResource(resource);

		newUtilization.setCombined(cpuUtilizationRecord.getTotalUtilization());
		newUtilization.setIdle(cpuUtilizationRecord.getIdle());
		newUtilization.setIrq(cpuUtilizationRecord.getIrq());
		newUtilization.setNice(cpuUtilizationRecord.getNice());
		newUtilization.setSystem(cpuUtilizationRecord.getSystem());
		newUtilization.setUser(cpuUtilizationRecord.getUser());
		newUtilization.setWait(cpuUtilizationRecord.getWait());

		return newUtilization;
	}

	@Override
	public IEvent transform(final IMonitoringRecord record) {
		if (!(record instanceof CPUUtilizationRecord)) {
			return null;
		}

		final CPUUtilizationRecord utilizationRecord =
				(CPUUtilizationRecord) record;

		return this.transformCPUUtilizationRecord(utilizationRecord);
	}
}
