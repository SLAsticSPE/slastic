package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ModelEntityFactory {

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

	private static final String DEFAULT_RESOURCE_TYPE_NAME =
			"DEFAULT.RESOURCE_TYPE";

	/**
	 * Creates a new resource with the given name and associated with the given
	 * {@link ExecutionContainer}. The method adds the
	 * {@link ResourceSpecification} to the
	 * {@link ExecutionContainer#getExecutionContainerType()}'s
	 * {@link ExecutionContainerType#getResources()}.
	 * 
	 * @param modelManager
	 * @param executionContainer
	 * @param resourceName
	 * @return
	 */
	public static Resource createResourceSpecificationAndResource(
			final ModelManager modelManager,
			final ExecutionContainer executionContainer,
			final String resourceName) {

		// TODO: This needs to be refined in the future!
		ResourceType resourceType =
				modelManager.getTypeRepositoryManager().lookupResourceType(
						ModelEntityFactory.DEFAULT_RESOURCE_TYPE_NAME);

		if (resourceType == null) {
			resourceType =
					modelManager
							.getTypeRepositoryManager()
							.createAndRegisterResourceType(
									ModelEntityFactory.DEFAULT_RESOURCE_TYPE_NAME);
		}

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
