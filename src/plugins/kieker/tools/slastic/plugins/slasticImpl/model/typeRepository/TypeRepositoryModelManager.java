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

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractModelManager;

import kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.ResourceType;
import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;

/**
 * 
 * @author Andre van Hoorn
 */
public class TypeRepositoryModelManager extends AbstractModelManager<TypeRepositoryModel>
		implements IComponentTypesManager, IInterfacesManager, IConnectorTypesManager, IExecutionContainerTypesManager, INetworkLinkTypesManager,
		IResourceTypesManager {

	private final ComponentTypesManager componentTypeManager;
	private final ConnectorTypesManager connectorTypeManager;
	private final InterfacesManager interfaceManager;
	private final ExecutionContainerTypesManager executionContainerTypeManager;
	private final NetworkLinkTypesManager networkLinkTypeManager;
	private final ResourceTypesManager resourceTypesManager;

	private TypeRepositoryModelManager() {
		super(null);
		this.componentTypeManager = null;
		this.connectorTypeManager = null;
		this.interfaceManager = null;
		this.executionContainerTypeManager = null;
		this.networkLinkTypeManager = null;
		this.resourceTypesManager = null;
	}

	public TypeRepositoryModelManager(
			final TypeRepositoryModel typeRepositoryModel) {
		super(typeRepositoryModel);
		this.interfaceManager = new InterfacesManager(typeRepositoryModel.getInterfaces());
		this.componentTypeManager = new ComponentTypesManager(typeRepositoryModel.getComponentTypes(), this.interfaceManager);
		this.connectorTypeManager = new ConnectorTypesManager(typeRepositoryModel.getConnectorTypes());
		this.executionContainerTypeManager = new ExecutionContainerTypesManager(typeRepositoryModel.getExecutionContainerTypes());
		this.networkLinkTypeManager = new NetworkLinkTypesManager(typeRepositoryModel.getNetworkLinkTypes());
		this.resourceTypesManager = new ResourceTypesManager(typeRepositoryModel.getResourceTypes());
	}

	@Override
	public ComponentType lookupComponentType(final String fullyQualifiedName) {
		return this.componentTypeManager.lookupComponentType(fullyQualifiedName);
	}

	@Override
	public ComponentType lookupComponentType(final long id) {
		return this.componentTypeManager.lookupComponentType(id);
	}

	@Override
	public ComponentType createAndRegisterComponentType(
			final String fullyQualifiedName) {
		return this.componentTypeManager.createAndRegisterComponentType(fullyQualifiedName);
	}

	@Override
	public ConnectorType lookupConnectorType(final String fullyQualifiedName) {
		return this.connectorTypeManager.lookupConnectorType(fullyQualifiedName);
	}

	@Override
	public ConnectorType lookupConnectorType(final long id) {
		return this.connectorTypeManager.lookupConnectorType(id);
	}

	@Override
	public ConnectorType createAndRegisterConnectorType(
			final String fullyQualifiedName, final Interface iface) {
		return this.connectorTypeManager
				.createAndRegisterConnectorType(fullyQualifiedName, iface);
	}

	@Override
	public ConnectorType createAndRegisterConnectorType(final Interface iface) {
		return this.connectorTypeManager.createAndRegisterConnectorType(iface);
	}

	@Override
	public Interface lookupInterface(final String fullyQualifiedName) {
		return this.interfaceManager.lookupInterface(fullyQualifiedName);
	}

	@Override
	public Interface lookupInterface(final long id) {
		return this.interfaceManager.lookupInterface(id);
	}

	@Override
	public Interface createAndRegisterInterface(final String fullyQualifiedName) {
		return this.interfaceManager
				.createAndRegisterInterface(fullyQualifiedName);
	}

	@Override
	public ExecutionContainerType lookupExecutionContainerType(
			final String fullyQualifiedName) {
		return this.executionContainerTypeManager
				.lookupExecutionContainerType(fullyQualifiedName);
	}

	@Override
	public ExecutionContainerType lookupExecutionContainerType(final long id) {
		return this.executionContainerTypeManager
				.lookupExecutionContainerType(id);
	}

	@Override
	public ExecutionContainerType createAndRegisterExecutionContainerType(
			final String fullyQualifiedName) {
		return this.executionContainerTypeManager
				.createAndRegisterExecutionContainerType(fullyQualifiedName);
	}

	@Override
	public NetworkLinkType lookupNetworkLinkType(final String fullyQualifiedName) {
		return this.networkLinkTypeManager
				.lookupNetworkLinkType(fullyQualifiedName);
	}

	@Override
	public NetworkLinkType lookupNetworkLinkType(final long id) {
		return this.networkLinkTypeManager.lookupNetworkLinkType(id);
	}

	@Override
	public NetworkLinkType createAndRegisterNetworkLinkType(final String fullyQualifiedName) {
		return this.networkLinkTypeManager.createAndRegisterNetworkLinkType(fullyQualifiedName);
	}

	@Override
	public ResourceType lookupResourceType(final String fullyQualifiedName) {
		return this.resourceTypesManager.lookupResourceType(fullyQualifiedName);
	}

	@Override
	public ResourceType lookupResourceType(final long id) {
		return this.resourceTypesManager.lookupResourceType(id);
	}

	@Override
	public GenericResourceType createAndRegisterGenericResourceType(final String fullyQualifiedName) {
		return this.resourceTypesManager.createAndRegisterGenericResourceType(fullyQualifiedName);
	}

	@Override
	public MemSwapType createAndRegisterMemSwapResourceType(final String fullyQualifiedName) {
		return this.resourceTypesManager.createAndRegisterMemSwapResourceType(fullyQualifiedName);
	}

	@Override
	public CPUType createAndRegisterCPUResourceType(final String fullyQualifiedName) {
		return this.resourceTypesManager.createAndRegisterCPUResourceType(fullyQualifiedName);
	}

	@Override
	public ResourceSpecification createAndAddResourceSpecification(
			final ExecutionContainerType executionContainerType,
			final ResourceType resourceType,
			final String resourceSpecificatioName) {
		return this.executionContainerTypeManager.createAndAddResourceSpecification(executionContainerType, resourceType, resourceSpecificatioName);
	}

	@Override
	public MemSwapResourceSpecification createAndAddMemSwapResourceSpecification(
			final ExecutionContainerType executionContainerType,
			final long memCapacityBytes, final long swapCapacityBytes,
			final ResourceType resourceType,
			final String resourceSpecificatioName) {
		return this.executionContainerTypeManager
				.createAndAddMemSwapResourceSpecification(executionContainerType, memCapacityBytes, swapCapacityBytes, resourceType, resourceSpecificatioName);
	}

	@Override
	public Operation createAndRegisterOperation(final ComponentType componentType, final String operationName, final String returnType, final String[] argTypes) {
		return this.componentTypeManager.createAndRegisterOperation(componentType, operationName, returnType, argTypes);
	}

	@Override
	public Operation lookupOperation(final ComponentType componentType, final String operationName, final String returnType, final String[] argTypes) {
		return this.componentTypeManager.lookupOperation(componentType, operationName, returnType, argTypes);
	}

	@Override
	public Signature lookupSignature(final Interface iface, final String signatureName, final String returnType, final String[] argTypes) {
		return this.interfaceManager.lookupSignature(iface, signatureName, returnType, argTypes);
	}

	@Override
	public Signature createAndRegisterSignature(final Interface iface, final String signatureName, final String returnType, final String[] argTypes) {
		return this.interfaceManager.createAndRegisterSignature(iface, signatureName, returnType, argTypes);
	}

	@Override
	public void registerProvidedInterface(final ComponentType componentType, final Interface providedInterface) {
		this.componentTypeManager.registerProvidedInterface(componentType, providedInterface);
	}

	@Override
	public void registerRequiredInterface(final ComponentType componentType, final Interface requiredInterface) {
		this.componentTypeManager.registerRequiredInterface(componentType, requiredInterface);
	}

	@Override
	public Interface lookupProvidedInterfaceForSignature(final ComponentType componentType, final Signature signature) {
		return this.componentTypeManager.lookupProvidedInterfaceForSignature(componentType, signature);
	}

	@Override
	public Interface lookupRequiredInterfaceForSignature(final ComponentType componentType, final Signature signature) {
		return this.componentTypeManager.lookupRequiredInterfaceForSignature(componentType, signature);
	}
}
