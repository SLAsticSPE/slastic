package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * Caches requests for {@link Resource}s via
	 * {@link #lookupExecutionContainerResource(ExecutionContainer, String)}.
	 */
	private final Map<ExecutionContainer, Map<String, Resource>> containerResourceCache =
			new HashMap<ExecutionContainer, Map<String, Resource>>();

	/**
	 * Returns a cached {@link Resource} contained in
	 * {@link #containerResourceCache} associated with the given
	 * {@link ExecutionContainer} and {@link ResourceSpecification} name; or
	 * null if no such cache entry exists.
	 * 
	 * @param executionContainer
	 * @param resourceSpecificationName
	 * @return
	 */
	private Resource lookupContainerResourceFromCache(
			final ExecutionContainer executionContainer,
			final String resourceSpecificationName) {
		final Map<String, Resource> containerResources =
				this.containerResourceCache.get(executionContainer);

		if (containerResources == null) {
			// cache miss since no entries exists for container so far
			return null;
		}

		// may result in cache hit or miss:
		return containerResources.get(resourceSpecificationName);
	}

	/**
	 * Adds a cache entry for a {@link Resource} to
	 * {@link #containerResourceCache} which is associated with the given
	 * {@link ExecutionContainer} and {@link ResourceSpecification} name.
	 * 
	 * @param executionContainer
	 * @param resourceSpecificationName
	 * @return the added {@link Resource}; null if no
	 *         {@link ResourceSpecification} with the name is associated to
	 *         {@link ExecutionContainer#getExecutionContainerType()}
	 */
	private Resource addContainerResourceCacheEntry(
			final ExecutionContainer executionContainer,
			final String resourceSpecificationName) {
		/**
		 * This is just to make sure that no such entry exists so far. Can be
		 * removed to pimp performance.
		 */
		Resource resource =
				this.lookupContainerResourceFromCache(executionContainer,
						resourceSpecificationName);
		if (resource != null) {
			ExecutionContainersManager.log.error("Cache entry exists already: "
					+ resource);
			return resource;
		}

		Map<String, Resource> containerResources =
				this.containerResourceCache.get(executionContainer);

		/*
		 * Get container's cached list of resources; if not existing, create
		 * empty list and add to cache
		 */
		if (containerResources == null) {
			containerResources = new HashMap<String, Resource>();
			this.containerResourceCache.put(executionContainer,
					containerResources);

		}

		final ExecutionContainerType executionContainerType =
				executionContainer.getExecutionContainerType();
		ResourceSpecification resourceSpecification = null;

		/* Lookup resource specification associated with the container's type */
		for (final ResourceSpecification rs : executionContainerType
				.getResources()) {
			if (rs.getName().equals(resourceSpecificationName)) {
				resourceSpecification = rs;
				break;
			}
		}

		if (resourceSpecification == null) {
			// not existing
			return null;
		}

		{ /* Create resource instance and add to cache */
			resource = ExecutionEnvironmentFactory.eINSTANCE.createResource();
			resource.setExecutionContainer(executionContainer);
			resource.setResourceSpecification(resourceSpecification);
			containerResources.put(resourceSpecificationName, resource);
		}

		// may result in cache hit or miss:
		return containerResources.get(resourceSpecificationName);
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

		/* 1: Check internal cache */
		Resource resource =
				this.lookupContainerResourceFromCache(executionContainer,
						resourceSpecificationName);

		if (resource == null) {
			/* 2: Add internal cache entry */
			resource =
					this.addContainerResourceCacheEntry(executionContainer,
							resourceSpecificationName);
		}

		return resource;
	}
}
