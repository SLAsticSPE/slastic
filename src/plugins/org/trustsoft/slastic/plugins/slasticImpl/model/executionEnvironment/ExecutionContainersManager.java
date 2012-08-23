/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import java.util.ArrayList;
import java.util.Collection;
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
public class ExecutionContainersManager extends AbstractFQNamedEntityManager<ExecutionContainer> implements
		IExecutionContainersManager {
	private static final Log log = LogFactory.getLog(ExecutionContainersManager.class);

	public ExecutionContainersManager(final List<ExecutionContainer> ExecutionContainers) {
		super(ExecutionContainers);
	}

	@Override
	public ExecutionContainer lookupExecutionContainer(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ExecutionContainer lookupExecutionContainer(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public ExecutionContainer createAndRegisterExecutionContainer(final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType, final boolean markAllocated) {
		final ExecutionContainer executionContainer = this.createAndRegister(fullyQualifiedName);
		executionContainer.setExecutionContainerType(executionContainerType);
		executionContainer.setActive(markAllocated);
		return executionContainer;
	}

	@Override
	public boolean allocateExecutionContainer(final ExecutionContainer executionContainer) {
		executionContainer.setActive(true);
		return true;
	}

	@Override
	public boolean deallocateExecutionContainer(final ExecutionContainer executionContainer) {
		return this.removeEntity(executionContainer);
	}

	@Override
	protected ExecutionContainer createEntity() {
		return ExecutionEnvironmentFactory.eINSTANCE.createExecutionContainer();
	}

	private ResourceSpecification lookupResourceSpecification(final ExecutionContainerType executionContainerType,
			final String resourceSpecificationName) {
		for (final ResourceSpecification resSpec : executionContainerType.getResources()) {
			if (resSpec.getName().equals(resourceSpecificationName)) {
				return resSpec;
			}
		}
		return null;
	}

	private Resource lookupCachedContainerResource(final ExecutionContainer executionContainer,
			final ResourceSpecification resSpecification) {
		for (final Resource res : executionContainer.getResources()) {
			if (res.getResourceSpecification() == resSpecification) {
				return res;
			}
		}
		return null;
	}

	private Resource createCachedResource(final ExecutionContainer executionContainer,
			final ResourceSpecification resSpecification) {
		final Resource resource = ExecutionEnvironmentFactory.eINSTANCE.createResource();
		resource.setExecutionContainer(executionContainer);
		resource.setResourceSpecification(resSpecification);
		executionContainer.getResources().add(resource);
		return resource;
	}

	@Override
	public Resource lookupExecutionContainerResource(final ExecutionContainer executionContainer,
			final String resourceSpecificationName) {
		if ((resourceSpecificationName == null) || resourceSpecificationName.isEmpty()) {
			final String errorMsg =
					"resourceSpecificationName must not be null or empty (found: " + resourceSpecificationName + ")";
			ExecutionContainersManager.log.error(errorMsg);
			throw new IllegalArgumentException(errorMsg);
		}

		/* 1: Check whether resource exists for type */
		final ResourceSpecification resSpec =
				this.lookupResourceSpecification(executionContainer.getExecutionContainerType(),
						resourceSpecificationName);
		if (resSpec == null) {
			// such resource does not exist
			return null;
		}

		/* 1: Check internal cache */
		Resource resource = this.lookupCachedContainerResource(executionContainer, resSpec);

		if (resource != null) {
			return resource;
		}

		/* 2: Create resource and add to internal cache entry */
		resource = this.createCachedResource(executionContainer, resSpec);

		return resource;
	}

	@Override
	public Collection<ExecutionContainer> executionContainersForType(
			final ExecutionContainerType executionContainerType, final boolean includeInactive) {
		final Collection<ExecutionContainer> containersForType = new ArrayList<ExecutionContainer>();

		for (final ExecutionContainer container : super.getEntities()) {
			if (container.getExecutionContainerType() == executionContainerType) {
				if (includeInactive || container.isActive()) {
					containersForType.add(container);
				}
			}
		}

		return containersForType;
	}
}
