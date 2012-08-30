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

package kieker.tools.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.List;

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import kieker.tools.slastic.metamodel.typeRepository.ResourceType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesFactory;

/**
 * 
 * @author Andre van Hoorn
 */
public class ResourceTypesManager extends AbstractFQNamedEntityManager<ResourceType> implements IResourceTypesManager {
	public ResourceTypesManager(final List<ResourceType> resourceTypes) {
		super(resourceTypes);
	}

	@Override
	public ResourceType lookupResourceType(final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public ResourceType lookupResourceType(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public GenericResourceType createAndRegisterGenericResourceType(
			final String fullyQualifiedName) {
		final GenericResourceType resT = this.createGenericResourceType();
		this.assignNameIdAndRegister(resT, fullyQualifiedName);
		return resT;
	}

	@Override
	public MemSwapType createAndRegisterMemSwapResourceType(
			final String fullyQualifiedName) {
		final MemSwapType resT = this.createMemSwapResourceType();
		this.assignNameIdAndRegister(resT, fullyQualifiedName);
		return resT;
	}

	@Override
	public CPUType createAndRegisterCPUResourceType(
			final String fullyQualifiedName) {
		final CPUType resT = this.createCPUResourceType();
		this.assignNameIdAndRegister(resT, fullyQualifiedName);
		return resT;
	}

	/**
	 * Returns a {@link GenericResourceType}
	 */
	@Override
	protected ResourceType createEntity() {
		return this.createGenericResourceType();
	}

	private GenericResourceType createGenericResourceType() {
		return ResourceTypesFactory.eINSTANCE.createGenericResourceType();
	}

	private MemSwapType createMemSwapResourceType() {
		return ResourceTypesFactory.eINSTANCE.createMemSwapType();
	}

	private CPUType createCPUResourceType() {
		return ResourceTypesFactory.eINSTANCE.createCPUType();
	}
}
