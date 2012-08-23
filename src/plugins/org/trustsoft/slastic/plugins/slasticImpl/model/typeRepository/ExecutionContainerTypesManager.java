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

package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.Collection;
import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class ExecutionContainerTypesManager extends
		AbstractFQNamedEntityManager<ExecutionContainerType> implements
		IExecutionContainerTypesManager {
	
	public ExecutionContainerTypesManager(
			final List<ExecutionContainerType> componentTypes) {
		super(componentTypes);
	}

	@Override
	public ExecutionContainerType lookupExecutionContainerType(
			final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ExecutionContainerType lookupExecutionContainerType(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public ExecutionContainerType createAndRegisterExecutionContainerType(
			final String fullyQualifiedName) {
		return this.createAndRegister(fullyQualifiedName);
	}

	@Override
	protected ExecutionContainerType createEntity() {
		return TypeRepositoryFactory.eINSTANCE.createExecutionContainerType();
	}

	// TODO: I'm pretty sure we'll have problems with this when starting with a deserialized model
	private volatile long nextId = 1;

	@Override
	public ResourceSpecification createAndAddResourceSpecification(
			final ExecutionContainerType executionContainerType,
			final ResourceType resourceType,
			final String resourceSpecificatioName) {
		final Collection<ResourceSpecification> resources =
				executionContainerType.getResources();
		final ResourceSpecification resourceSpecification =
				ExecutionEnvironmentFactory.eINSTANCE
						.createResourceSpecification();

		{ /* Initialize fields and add to container type's list of resources */
			resourceSpecification.setId(this.nextId++);
			resourceSpecification.setName(resourceSpecificatioName);
			resourceSpecification.setResourceType(resourceType);
			resources.add(resourceSpecification);
		}

		return resourceSpecification;
	}

	@Override
	public MemSwapResourceSpecification createAndAddMemSwapResourceSpecification(
			final ExecutionContainerType executionContainerType,
			final long memCapacityBytes, final long swapCapacityBytes,
			final ResourceType resourceType,
			final String resourceSpecificatioName) {
		final Collection<ResourceSpecification> resources =
				executionContainerType.getResources();
		final MemSwapResourceSpecification resourceSpecification =
				ExecutionEnvironmentFactory.eINSTANCE
						.createMemSwapResourceSpecification();

		{ /* Initialize fields and add to container type's list of resources */
			resourceSpecification.setId(this.nextId++);
			resourceSpecification.setName(resourceSpecificatioName);
			resourceSpecification.setResourceType(resourceType);
			resourceSpecification.setMemCapacityBytes(memCapacityBytes);
			resourceSpecification.setSwapCapacityBytes(swapCapacityBytes);
			resources.add(resourceSpecification);
		}

		return resourceSpecification;
	}
}
