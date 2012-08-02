package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.system.ResourceUtilizationRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.ISynchronousTransformationFilter;

import de.cau.se.slastic.metamodel.core.IEvent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;

/**
 * 
 * @author Andre van Hoorn
 */
public class ResourceUtilizationRecordTransformationFilter extends
		AbstractModelReconstructionComponent implements
		ISynchronousTransformationFilter,
		IResourceUtilizationRecordTransformation {

	private static final Log log = LogFactory
			.getLog(ResourceUtilizationRecordTransformationFilter.class);

	/**
	 * 
	 * @param modelManager
	 */
	public ResourceUtilizationRecordTransformationFilter(
			final ModelManager modelManager) {
		super(modelManager);
	}

	@Override
	public ResourceUtilization transformResourceUtilizationRecord(
			final ResourceUtilizationRecord resourceUtilizationRecord) {

		/* Will become the return value. */
		final ResourceUtilization newUtilization =
				MonitoringFactory.eINSTANCE.createResourceUtilization();

		final ExecutionContainer executionContainer =
				this.lookupOrCreateExecutionContainerByName(resourceUtilizationRecord
						.getHostname());

		final Resource resource =
				this.lookupOrCreateGenericResource(
						AbstractModelReconstructionComponent
								.createGenericResourceSpecName(resourceUtilizationRecord
										.getResourceName()),
						AbstractModelReconstructionComponent.DEFAULT_GENERIC_RESOURCE_TYPE_NAME,
						executionContainer);

		// And finally, the simple part:
		newUtilization.setTimestamp(resourceUtilizationRecord.getTimestamp());
		newUtilization.setUtilization(resourceUtilizationRecord
				.getUtilization());
		newUtilization.setResource(resource);

		return newUtilization;
	}

	@Override
	public IEvent transform(final IMonitoringRecord record) {
		if (!(record instanceof ResourceUtilizationRecord)) {
			return null;
		}

		final ResourceUtilizationRecord utilizationRecord =
				(ResourceUtilizationRecord) record;

		return this.transformResourceUtilizationRecord(utilizationRecord);
	}
}
