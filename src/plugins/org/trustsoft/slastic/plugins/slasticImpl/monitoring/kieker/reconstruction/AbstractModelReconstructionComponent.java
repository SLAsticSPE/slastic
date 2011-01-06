package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractTransformationComponent;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractModelReconstructionComponent extends
		AbstractTransformationComponent {

	public AbstractModelReconstructionComponent(final ModelManager modelManager) {
		super(modelManager);
	}

	/**
	 * 
	 * @param hostName
	 * @return
	 */
	protected ExecutionContainer lookupOrCreateExecutionContainerByName(
			final String hostName) {
		ExecutionContainer executionContainer;
		{
			/*
			 * Lookup execution container
			 */
			executionContainer =
					this.getExecutionEnvModelManager()
							.lookupExecutionContainer(hostName);
			if (executionContainer == null) {
				/* We need to create the execution container */
				executionContainer =
						ModelEntityFactory.createExecutionContainer(
								this.getModelManager(), hostName);
			}
		}
		return executionContainer;
	}
	
	/**
	 * 
	 * @param resourceName
	 * @param executionContainer
	 * @return
	 */
	protected Resource lookupOrCreateResource(
			final String resourceName,
			final ExecutionContainer executionContainer) {
		Resource resource;
		{
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
		return resource;
	}
}
