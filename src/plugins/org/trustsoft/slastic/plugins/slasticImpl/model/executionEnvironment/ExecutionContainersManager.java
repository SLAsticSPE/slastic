package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 */
public class ExecutionContainersManager extends
		AbstractFQNamedEntityManager<ExecutionContainer> implements
		IExecutionContainersManager {
	private static final Log log = LogFactory
			.getLog(ExecutionContainersManager.class);

	public ExecutionContainersManager(
			final List<ExecutionContainer> ExecutionContainers) {
		super(ExecutionContainers);
	}

	@Override
	public ExecutionContainer lookupExecutionContainer(
			final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ExecutionContainer lookupExecutionContainer(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public ExecutionContainer createAndRegisterExecutionContainer(
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		final ExecutionContainer executionContainer =
				this.createAndRegister(fullyQualifiedName);
		executionContainer.setExecutionContainerType(executionContainerType);
		return executionContainer;
	}

	@Override
	protected ExecutionContainer createEntity() {
		return ExecutionEnvironmentFactory.eINSTANCE.createExecutionContainer();
	}

	private ResourceSpecification lookupResourceSpecification(
			final ExecutionContainerType executionContainerType,
			final String resourceSpecificationName) {
		for (final ResourceSpecification resSpec : executionContainerType
				.getResources()) {
			if (resSpec.getName().equals(resourceSpecificationName)) {
				return resSpec;
			}
		}
		return null;
	}

	private Resource lookupCachedContainerResource(
			final ExecutionContainer executionContainer,
			final ResourceSpecification resSpecification) {
		for (final Resource res : executionContainer.getResources()) {
			if (res.getResourceSpecification() == resSpecification) {
				return res;
			}
		}
		return null;
	}

	private Resource createCachedResource(
			final ExecutionContainer executionContainer,
			final ResourceSpecification resSpecification) {
		final Resource resource =
				ExecutionEnvironmentFactory.eINSTANCE.createResource();
		resource.setExecutionContainer(executionContainer);
		resource.setResourceSpecification(resSpecification);
		executionContainer.getResources().add(resource);
		return resource;
	}

	@Override
	public Resource lookupExecutionContainerResource(
			final ExecutionContainer executionContainer,
			final String resourceSpecificationName) {
		if ((resourceSpecificationName == null)
				|| resourceSpecificationName.isEmpty()) {
			final String errorMsg =
					"resourceSpecificationName must not be null or empty (found: "
							+ resourceSpecificationName + ")";
			ExecutionContainersManager.log.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}

		/* 1: Check whether resource exists for type */
		final ResourceSpecification resSpec =
				this.lookupResourceSpecification(
						executionContainer.getExecutionContainerType(),
						resourceSpecificationName);
		if (resSpec == null) {
			// such resource does not exist
			return null;
		}

		/* 1: Check internal cache */
		Resource resource =
				this.lookupCachedContainerResource(executionContainer, resSpec);

		if (resource != null) {
			return resource;
		}

		/* 2: Create resource and add to internal cache entry */
		resource = this.createCachedResource(executionContainer, resSpec);

		return resource;
	}
}
