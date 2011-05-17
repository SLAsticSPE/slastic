package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractTransformationComponent;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
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
	 * TODO: Really DIRTY HACK: This information should be stored somewhere
	 * central, e.g., in the model manager.
	 * 
	 * Maps a technical hostname to the corresponding architectural container
	 * name;
	 */
	public static final ConcurrentHashMap<String, ExecutionContainer> containerNameMapping =
			new ConcurrentHashMap<String, ExecutionContainer>();

	boolean reportedMatch = false;

	/**
	 * 
	 * @param hostName
	 * @return
	 */
	protected ExecutionContainer lookupOrCreateExecutionContainerByName(
			final String hostName) {

		ExecutionContainer executionContainer;

		{ /* HACK! */
			executionContainer =
					AbstractModelReconstructionComponent.containerNameMapping
							.get(hostName);
			if (executionContainer != null) {
				if (!this.reportedMatch) {
					AbstractModelReconstructionComponent.log
							.info("Found match: " + hostName + "->"
									+ executionContainer);
					this.reportedMatch = true;
				}
				return executionContainer;
			}
		}

		{
			/*
			 * Lookup execution container
			 */
			executionContainer =
					this.getExecutionEnvModelManager()
							.lookupExecutionContainer(hostName);
			if (executionContainer == null) {
				/* We need to create the execution container */
				executionContainer = this.createExecutionContainer(hostName);
			}
			// TODO: remove
			AbstractModelReconstructionComponent.containerNameMapping.put(
					hostName, executionContainer);
		}
		return executionContainer;
	}

	/**
	 * 
	 * @param resourceName
	 * @param memCapacityBytes
	 * @param swapCapacityBytes
	 * @param resourceTypeName
	 * @param executionContainer
	 * @return
	 */
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
				// Lookup resource type which may exist already

				final ResourceType resourceType =
						this.getTypeModelManager().lookupResourceType(
								resourceTypeName);
				if ((resourceType != null)
						&& !(resourceType instanceof MemSwapType)) {
					AbstractModelReconstructionComponent.log
							.error("Resource type with name '"
									+ resourceTypeName
									+ "' exists already but is of type "
									+ resourceType.getClass() + " instead of "
									+ MemSwapType.class);
					return null;
				}

				final MemSwapType memSwapResourceType;

				if (resourceType == null) {

					memSwapResourceType =
							this.getTypeModelManager()
									.createAndRegisterMemSwapResourceType(
											resourceTypeName);

				} else {
					memSwapResourceType = (MemSwapType) resourceType;
				}

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager()
								.createAndAddMemSwapResourceSpecification(
										executionContainer
												.getExecutionContainerType(),
										memCapacityBytes, swapCapacityBytes,
										memSwapResourceType, resourceName);

				resource =
						this.createResource(executionContainer,
								resourceSpecification);
			}
		}
		return resource;
	}

	/**
	 * 
	 * @param resourceName
	 * @param resourceTypeName
	 * @param executionContainer
	 * @return
	 */
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
				ResourceType resourceType =
						this.getTypeModelManager().lookupResourceType(
								resourceTypeName);

				if (resourceType == null) {
					resourceType =
							this.getTypeModelManager()
									.createAndRegisterCPUResourceType(
											resourceTypeName);
				}

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager()
								.createAndAddResourceSpecification(
										executionContainer
												.getExecutionContainerType(),
										resourceType, resourceName);

				resource =
						this.createResource(executionContainer,
								resourceSpecification);
			}
		}
		return resource;
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
				ResourceType resourceType =
						this.getTypeModelManager().lookupResourceType(
								resourceTypeName);

				if (resourceType == null) {
					resourceType =
							this.getTypeModelManager()
									.createAndRegisterGenericResourceType(
											resourceTypeName);
				}

				// Create resource specification and add it to the container
				// type
				final ResourceSpecification resourceSpecification =
						this.getTypeModelManager()
								.createAndAddResourceSpecification(
										executionContainer
												.getExecutionContainerType(),
										resourceType, resourceName);

				resource =
						this.createResource(executionContainer,
								resourceSpecification);
			}
		}
		return resource;
	}

	/**
	 * 
	 * @param resourceName
	 * @param expected
	 * @param found
	 */
	private void logConflictingTypeForResource(final String resourceName,
			final Class<? extends ResourceType> expected,
			final Class<? extends ResourceType> found) {
		AbstractModelReconstructionComponent.log
				.fatal("Conflicting resource with name '" + resourceName
						+ "' exists. Expecting type '" + expected.getName()
						+ "' but found '" + found.getName() + "'");
	}

	/**
	 * Appended to instance names, to form a type name in the type repository.
	 */
	public static final String DEFAULT_TYPE_POSTFIX = "__T";

	/**
	 * <p>
	 * Creates a new {@link AssemblyComponent} with the given componentName. The
	 * associated {@link ComponentType} is selected by using a (existing or
	 * newly created by the method) {@link ComponentType} with the same name.
	 * </p>
	 * 
	 * <p>
	 * An assembly component with the name componentName must not be registered
	 * prior to the call.
	 * </p>
	 * 
	 * @param componentName
	 * @return
	 */
	public AssemblyComponent createAssemblyComponent(final String componentName) {

		ComponentType componentType =
				this.getTypeModelManager()
						.lookupComponentType(
								componentName
										+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		if (componentType == null) {
			componentType =
					this.getTypeModelManager()
							.createAndRegisterComponentType(
									componentName
											+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		}

		return this.getAssemblyModelManager()
				.createAndRegisterAssemblyComponent(componentName,
						componentType);
	}

	/**
	 * 
	 * @param containerName
	 * @return
	 */
	public ExecutionContainer createExecutionContainer(
			final String containerName) {
		ExecutionContainerType containerType =
				this.getTypeModelManager()
						.lookupExecutionContainerType(
								containerName
										+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		if (containerType == null) {
			containerType =
					this.getTypeModelManager()
							.createAndRegisterExecutionContainerType(
									containerName
											+ AbstractModelReconstructionComponent.DEFAULT_TYPE_POSTFIX);
		}

		return this.getExecutionEnvModelManager()
				.createAndRegisterExecutionContainer(containerName,
						containerType);
	}

	public static final String DEFAULT_GENERIC_RESOURCE_TYPE_NAME =
			"DEFAULT.GENERIC_RESOURCE_TYPE";

	public static final String DEFAULT_CPU_RESOURCE_TYPE_NAME =
			"DEFAULT.CPU_RESOURCE_TYPE";

	public static final String DEFAULT_MEMSWAP_RESOURCE_TYPE_NAME =
			"DEFAULT.MEMSWAP_RESOURCE_TYPE";

	/**
	 * 
	 * @param executionContainer
	 * @param resourceSpecification
	 * @return
	 */
	private Resource createResource(
			final ExecutionContainer executionContainer,
			final ResourceSpecification resourceSpecification) {
		final Resource resource =
				ExecutionEnvironmentFactory.eINSTANCE.createResource();
		resource.setExecutionContainer(executionContainer);
		resource.setResourceSpecification(resourceSpecification);
		return resource;
	}
}
