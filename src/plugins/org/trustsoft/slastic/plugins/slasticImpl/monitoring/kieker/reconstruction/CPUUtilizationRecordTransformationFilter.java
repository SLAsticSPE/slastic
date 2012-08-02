package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.IMonitoringRecord;

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
		ISynchronousTransformationFilter, ICPUUtilizationRecordTransformation {

//	private static final Log LOG = LogFactory
//			.getLog(CPUUtilizationRecordTransformationFilter.class);

	/**
	 * 
	 * @param modelManager
	 */
	public CPUUtilizationRecordTransformationFilter(
			final ModelManager modelManager) {
		super(modelManager);
	}

	@Override
	public CPUUtilization transformCPUUtilizationRecord(
			final kieker.common.record.system.CPUUtilizationRecord cpuUtilizationRecord) {

		/* Will become the return value. */
		final CPUUtilization newUtilization =
				MonitoringFactory.eINSTANCE.createCPUUtilization();

		final ExecutionContainer executionContainer =
				this.lookupOrCreateExecutionContainerByName(cpuUtilizationRecord
						.getHostname());

		final Resource resource =
				this.lookupOrCreateCPUResource(
						AbstractModelReconstructionComponent
								.createCPUResourceSpecName(cpuUtilizationRecord
										.getCpuID()),
						AbstractModelReconstructionComponent.DEFAULT_CPU_RESOURCE_TYPE_NAME, executionContainer);

		// And finally, the simple part:
		newUtilization.setTimestamp(cpuUtilizationRecord.getTimestamp());
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
		if (!(record instanceof kieker.common.record.system.CPUUtilizationRecord)) {
			return null;
		}

		final kieker.common.record.system.CPUUtilizationRecord utilizationRecord =
				(kieker.common.record.system.CPUUtilizationRecord) record;

		return this.transformCPUUtilizationRecord(utilizationRecord);
	}
}
