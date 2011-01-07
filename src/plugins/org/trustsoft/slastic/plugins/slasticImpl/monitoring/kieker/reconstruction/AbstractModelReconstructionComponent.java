package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractTransformationComponent;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractModelReconstructionComponent extends
		AbstractTransformationComponent {

	private static final Log log = LogFactory
			.getLog(AbstractModelReconstructionComponent.class);

	public AbstractModelReconstructionComponent(final ModelManager modelManager) {
		super(modelManager);
	}

	private final static String CPU_RESOURCE_NAME_PREFX = "cpu";
	private final static String MEM_SWAP_RESOURCE_NAME = "memSwap";

	public static String createGenericResourceSpecName(final String name) {
		return name;
	}

	public static String createCPUResourceSpecName(final String cpuId) {
		return AbstractModelReconstructionComponent.CPU_RESOURCE_NAME_PREFX
				+ cpuId;
	}

	public static String createMemSwapResourceSpecName() {
		return AbstractModelReconstructionComponent.MEM_SWAP_RESOURCE_NAME;
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
	 * @param resourceTypeName
	 * @param executionContainer
	 * @return
	 */
	protected Resource lookupOrCreateGenericResource(final String resourceName,
			final String resourceTypeName,
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

			if ((resource != null)
					&& !(resource.getResourceSpecification().getResourceType() instanceof GenericResourceType)) {
				this.logConflictingTypeForResource(resourceName,
						GenericResourceType.class, resource
								.getResourceSpecification().getResourceType()
								.getClass());
				return null;
			}

			if (resource == null) {
				/* We need to create the resource (type / specification) */
				final GenericResourceType resourceType =
						ModelEntityFactory.createGenericResourceType(
								this.getModelManager(), resourceTypeName);
				resource =
						ModelEntityFactory
								.createResourceSpecificationAndResource(
										this.getModelManager(), resourceType,
										executionContainer, resourceName);
			}
		}
		return resource;
	}

	protected Resource lookupOrCreateMemSwapResource(final String resourceName,
			final long memCapacityBytes, final long swapCapacityBytes,
			final String resourceTypeName,
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

			if ((resource != null)
					&& !(resource.getResourceSpecification().getResourceType() instanceof MemSwapType)) {
				this.logConflictingTypeForResource(resourceName,
						MemSwapType.class, resource.getResourceSpecification()
								.getResourceType().getClass());
				return null;
			}

			if ((resource != null)
					&& !(resource.getResourceSpecification() instanceof MemSwapResourceSpecification)) {
				AbstractModelReconstructionComponent.log
						.fatal("Expecting resource specification to be an instance of "
								+ MemSwapResourceSpecification.class
								+ "' but found '"
								+ resource.getResourceSpecification()
										.getClass() + "'");
				return null;
			}

			if (resource == null) {
				/* We need to create the resource (type / specification) */
				final MemSwapType resourceType =
						ModelEntityFactory.createMemSwapResourceType(
								this.getModelManager(), resourceTypeName);
				resource =
						ModelEntityFactory
								.createResourceSpecificationAndResource(
										this.getModelManager(), resourceType,
										executionContainer, resourceName);
			}
		}
		return resource;
	}

	protected Resource lookupOrCreateCPUResource(final String resourceName,
			final String resourceTypeName,
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

			if ((resource != null)
					&& !(resource.getResourceSpecification().getResourceType() instanceof CPUType)) {
				this.logConflictingTypeForResource(resourceName, CPUType.class,
						resource.getResourceSpecification().getResourceType()
								.getClass());
				return null;
			}

			if (resource == null) {
				/* We need to create the resource (type / specification) */
				final CPUType resourceType =
						ModelEntityFactory.createCPUResourceType(
								this.getModelManager(), resourceTypeName);
				resource =
						ModelEntityFactory
								.createResourceSpecificationAndResource(
										this.getModelManager(), resourceType,
										executionContainer, resourceName);
			}
		}
		return resource;
	}

	private void logConflictingTypeForResource(final String resourceName,
			final Class<? extends ResourceType> expected,
			final Class<? extends ResourceType> found) {
		AbstractModelReconstructionComponent.log
				.fatal("Conflicting resource with name '" + resourceName
						+ "' exists. Expecting type '" + expected.getName()
						+ "' but found '" + found.getName() + "'");
	}
}
