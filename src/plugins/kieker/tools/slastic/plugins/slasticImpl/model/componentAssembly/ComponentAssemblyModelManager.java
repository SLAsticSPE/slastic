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

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public class ComponentAssemblyModelManager extends AbstractModelManager<ComponentAssemblyModel>
		implements IAssemblyComponentsManager, IAssemblyConnectorsManager, ISystemProvidedInterfacesManager {

	private final TypeRepositoryModelManager typeRepositoryModelManager;
	private final AssemblyComponentsManager assemblyComponentsManager;
	private final AssemblyConnectorsManager assemblyConnectorsManager;
	private final SystemProvidedInterfacesManager systemProvidedInterfacesManager;

	private ComponentAssemblyModelManager() {
		super(null);
		this.typeRepositoryModelManager = null;
		this.assemblyComponentsManager = null;
		this.assemblyConnectorsManager = null;
		this.systemProvidedInterfacesManager = null;
	}

	public ComponentAssemblyModelManager(final ComponentAssemblyModel componentAssemblyModel, final TypeRepositoryModelManager typeRepositoryManager) {
		super(componentAssemblyModel);
		this.typeRepositoryModelManager = typeRepositoryManager;
		this.assemblyComponentsManager = new AssemblyComponentsManager(componentAssemblyModel.getAssemblyComponents());
		this.assemblyConnectorsManager = new AssemblyConnectorsManager(componentAssemblyModel.getAssemblyComponentConnectors(), this.typeRepositoryModelManager);
		this.systemProvidedInterfacesManager =
				new SystemProvidedInterfacesManager(componentAssemblyModel.getSystemProvidedInterfaces(),
						componentAssemblyModel.getSystemProvidedInterfaceDelegationConnectors(), typeRepositoryManager);
	}

	@Override
	public AssemblyComponent lookupAssemblyComponent(final String fullyQualifiedName) {
		return this.assemblyComponentsManager.lookup(fullyQualifiedName);
	}

	@Override
	public AssemblyComponent lookupAssemblyComponent(final long id) {
		return this.assemblyComponentsManager.lookupAssemblyComponent(id);
	}

	@Override
	public AssemblyComponent createAndRegisterAssemblyComponent(final String fullyQualifiedName, final ComponentType componentType) {
		return this.assemblyComponentsManager.createAndRegisterAssemblyComponent(fullyQualifiedName, componentType);
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final String fullyQualifiedName) {
		return this.assemblyConnectorsManager.lookupAssemblyConnector(fullyQualifiedName);
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final long id) {
		return this.assemblyConnectorsManager.lookupAssemblyConnector(id);
	}

	@Override
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(final String fullyQualifiedName, final ConnectorType connectorType) {
		return this.assemblyConnectorsManager.createAndRegisterAssemblyConnector(fullyQualifiedName, connectorType);
	}

	@Override
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(final ConnectorType connectorType) {
		return this.assemblyConnectorsManager.createAndRegisterAssemblyConnector(connectorType);
	}

	@Override
	public boolean connect(final AssemblyComponentConnector assemblyConnector, final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent) {
		return this.assemblyConnectorsManager.connect(assemblyConnector, requiringComponent, providingComponent);
	}

	@Override
	public AssemblyComponentConnector lookupAssemblyConnector(final AssemblyComponent requiringComponent, final AssemblyComponent providingComponent,
			final Signature signature) {
		return this.assemblyConnectorsManager
				.lookupAssemblyConnector(requiringComponent, providingComponent, signature);
	}

	@Override
	public boolean registerSystemProvidedInterface(final Interface iface) {
		return this.systemProvidedInterfacesManager.registerSystemProvidedInterface(iface);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(
			final String fullyQualifiedName) {
		return this.systemProvidedInterfacesManager.lookupProvidedInterfaceDelegationConnector(fullyQualifiedName);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(final long id) {
		return this.systemProvidedInterfacesManager.lookupProvidedInterfaceDelegationConnector(id);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector lookupProvidedInterfaceDelegationConnector(
			final AssemblyComponent providingComponent, final Signature signature) {
		return this.systemProvidedInterfacesManager.lookupProvidedInterfaceDelegationConnector(providingComponent, signature);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(
			final String fullyQualifiedName, final ConnectorType connectorType) {
		return this.systemProvidedInterfacesManager.createAndRegisterProvidedInterfaceDelegationConnector(fullyQualifiedName, connectorType);
	}

	@Override
	public SystemProvidedInterfaceDelegationConnector createAndRegisterProvidedInterfaceDelegationConnector(
			final ConnectorType connectorType) {
		return this.systemProvidedInterfacesManager.createAndRegisterProvidedInterfaceDelegationConnector(connectorType);
	}

	@Override
	public boolean delegate(final SystemProvidedInterfaceDelegationConnector delegationConnector,
			final Interface providedInterface, final AssemblyComponent providingComponent) {
		return this.systemProvidedInterfacesManager.delegate(delegationConnector, providedInterface, providingComponent);
	}

	@Override
	public List<Interface> getSystemProvidedInterfaces() {
		return this.systemProvidedInterfacesManager.getSystemProvidedInterfaces();
	}
}
