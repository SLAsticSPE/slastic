package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.ResourceUtilizationRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractSynchronousTransformationFilter;

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
		AbstractSynchronousTransformationFilter implements
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

		ExecutionContainer executionContainer;
		{
			/*
			 * Lookup execution container
			 */
			executionContainer =
					this.getExecutionEnvModelManager()
							.lookupExecutionContainer(
									resourceUtilizationRecord.getHostName());
			if (executionContainer == null) {
				/* We need to create the execution container */
				executionContainer =
						ModelEntityFactory.createExecutionContainer(
								this.getModelManager(),
								resourceUtilizationRecord.getHostName());
			}
		}

		Resource resource;
		{
			final String resourceName =
					resourceUtilizationRecord.getResourceName();
			/*
			 * Lookup resource -- which may exist already
			 */
			resource =
					this.getExecutionEnvModelManager()
							.lookupExecutionContainerResource(
									executionContainer, resourceName);

			if (resource == null) {
				/* We need to create the resource (type / specification) */
				resource =
						ModelEntityFactory
								.createResourceSpecificationAndResource(
										this.getModelManager(),
										executionContainer, resourceName);
			}
		}

		{
			// And finally, the simple part:
			newUtilization.setTimestamp(resourceUtilizationRecord
					.getTimestamp());
			newUtilization.setUtilization(resourceUtilizationRecord
					.getUtilization());
			newUtilization.setResource(resource);
		}
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
