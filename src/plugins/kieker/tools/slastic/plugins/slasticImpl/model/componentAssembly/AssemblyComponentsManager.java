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

package kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly;

import java.util.List;

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;

/**
 * 
 * @author Andre van Hoorn
 */
public class AssemblyComponentsManager extends AbstractFQNamedEntityManager<AssemblyComponent> implements IAssemblyComponentsManager {
	public AssemblyComponentsManager(final List<AssemblyComponent> AssemblyComponents) {
		super(AssemblyComponents);
	}

	@Override
	public AssemblyComponent lookupAssemblyComponent(
			final String fullyQualifiedName) {
		return this.lookup(fullyQualifiedName);
	}

	@Override
	public AssemblyComponent lookupAssemblyComponent(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public AssemblyComponent createAndRegisterAssemblyComponent(final String fullyQualifiedName, final ComponentType componentType) {
		final AssemblyComponent assemblyComponent = this.createAndRegister(fullyQualifiedName);
		assemblyComponent.setComponentType(componentType);
		return assemblyComponent;
	}

	@Override
	protected AssemblyComponent createEntity() {
		return ComponentAssemblyFactory.eINSTANCE.createAssemblyComponent();
	}
}
