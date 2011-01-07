package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
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
public class ModelEntityFactory {

	private static final Log log = LogFactory.getLog(ModelEntityFactory.class);

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
	public static AssemblyComponent createAssemblyComponent(
			final ModelManager modelManager, final String componentName) {

		ComponentType componentType =
				modelManager
						.getTypeRepositoryManager()
						.lookupComponentType(
								componentName
										+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX);
		if (componentType == null) {
			componentType =
					modelManager
							.getTypeRepositoryManager()
							.createAndRegisterComponentType(
									componentName
											+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX);
		}

		return modelManager.getComponentAssemblyModelManager()
				.createAndRegisterAssemblyComponent(componentName,
						componentType);
	}

	/**
	 * 
	 * @param containerName
	 * @return
	 */
	public static ExecutionContainer createExecutionContainer(
			final ModelManager modelManager, final String containerName) {
		ExecutionContainerType containerType =
				modelManager
						.getTypeRepositoryManager()
						.lookupExecutionContainerType(
								containerName
										+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX);
		if (containerType == null) {
			containerType =
					modelManager
							.getTypeRepositoryManager()
							.createAndRegisterExecutionContainerType(
									containerName
											+ ModelEntityFactory.DEFAULT_TYPE_POSTFIX);
		}

		return modelManager.getExecutionEnvironmentModelManager()
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
	 * Looks up an existing {@link ResourceType} with the given name.
	 * 
	 * @param modelManager
	 * @param resourceName
	 * @return the {@link ResourceType} or null if no {@link ResourceType} with
	 *         this name
	 */
	public static final ResourceType lookupResourceType(
			final ModelManager modelManager, final String resourceTypeName) {
		final ResourceType resourceType =
				modelManager.getTypeRepositoryManager().lookupResourceType(
						resourceTypeName);
		return resourceType;
	}

	/**
	 * 
	 * @param modelManager
	 * @param resourceTypeName
	 * @return
	 */
	public static final GenericResourceType createGenericResourceType(
			final ModelManager modelManager, final String resourceTypeName) {
		if (ModelEntityFactory.lookupResourceType(modelManager,
				resourceTypeName) != null) {
			ModelEntityFactory.log.error("Resource type with name '"
					+ resourceTypeName + "' exists already");
			return null;
		}

		final GenericResourceType res =
				modelManager.getTypeRepositoryManager()
						.createAndRegisterGenericResourceType(resourceTypeName);

		return res;
	}

	/**
	 * 
	 * @param modelManager
	 * @param resourceTypeName
	 * @return
	 */
	public static final MemSwapType createMemSwapResourceType(
			final ModelManager modelManager, final String resourceTypeName) {
		if (ModelEntityFactory.lookupResourceType(modelManager,
				resourceTypeName) != null) {
			ModelEntityFactory.log.error("Resource type with name '"
					+ resourceTypeName + "' exists already");
			return null;
		}

		final MemSwapType res =
				modelManager.getTypeRepositoryManager()
						.createAndRegisterMemSwapResourceType(resourceTypeName);

		return res;
	}

	/**
	 * 
	 * @param modelManager
	 * @param resourceTypeName
	 * @return
	 */
	public static final CPUType createCPUResourceType(
			final ModelManager modelManager, final String resourceTypeName) {
		if (ModelEntityFactory.lookupResourceType(modelManager,
				resourceTypeName) != null) {
			ModelEntityFactory.log.error("Resource type with name '"
					+ resourceTypeName + "' exists already");
			return null;
		}

		final CPUType res =
				modelManager.getTypeRepositoryManager()
						.createAndRegisterCPUResourceType(resourceTypeName);

		return res;
	}

	/**
	 * Creates a new resource with the given name and associated with the given
	 * {@link ExecutionContainer}. The method adds the
	 * {@link ResourceSpecification} to the
	 * {@link ExecutionContainer#getExecutionContainerType()}'s
	 * {@link ExecutionContainerType#getResources()}.
	 * 
	 * @param modelManager
	 * @param resourceType
	 * @param executionContainer
	 * @param resourceName
	 * @return
	 */
	public static Resource createResourceSpecificationAndResource(
			final ModelManager modelManager, final ResourceType resourceType,
			final ExecutionContainer executionContainer,
			final String resourceName) {

		// Create resource specification and add it to the container type
		final ResourceSpecification resourceSpecification =
				modelManager.getTypeRepositoryManager()
						.createAndAddResourceSpecification(
								executionContainer.getExecutionContainerType(),
								resourceType, resourceName);

		final Resource resource =
				ExecutionEnvironmentFactory.eINSTANCE.createResource();
		resource.setExecutionContainer(executionContainer);
		resource.setResourceSpecification(resourceSpecification);

		return resource;
	}
}
