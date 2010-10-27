package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import de.cau.se.slastic.metamodel.typeRepository.ResourceType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;

/**
 * TODO: interfaces, network link types
 * 
 * @author Andre van Hoorn
 */
public class TypeRepositoryModelManager extends
		AbstractModelManager<TypeRepositoryModel> implements
		IComponentTypesManager, IInterfacesManager, IConnectorTypesManager,
		IExecutionContainerTypesManager, INetworkLinkTypesManager,
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
		this.componentTypeManager =
				new ComponentTypesManager(
						typeRepositoryModel.getComponentTypes());
		this.connectorTypeManager =
				new ConnectorTypesManager(
						typeRepositoryModel.getConnectorTypes());
		this.interfaceManager =
				new InterfacesManager(typeRepositoryModel.getInterfaces());
		this.executionContainerTypeManager =
				new ExecutionContainerTypesManager(
						typeRepositoryModel.getExecutionContainerTypes());
		this.networkLinkTypeManager =
				new NetworkLinkTypesManager(
						typeRepositoryModel.getNetworkLinkTypes());
		this.resourceTypesManager = 
				new ResourceTypesManager(typeRepositoryModel.getResourceTypes());
	}

	@Override
	public ComponentType lookupComponentType(final String fullyQualifiedName) {
		return this.componentTypeManager
				.lookupComponentType(fullyQualifiedName);
	}

	@Override
	public ComponentType lookupComponentType(final long id) {
		return this.componentTypeManager.lookupComponentType(id);
	}

	@Override
	public ComponentType createAndRegisterComponentType(
			final String fullyQualifiedName) {
		return this.componentTypeManager
				.createAndRegisterComponentType(fullyQualifiedName);
	}

	@Override
	public ConnectorType lookupConnectorType(final String fullyQualifiedName) {
		return this.connectorTypeManager
				.lookupConnectorType(fullyQualifiedName);
	}

	@Override
	public ConnectorType lookupConnectorType(final long id) {
		return this.connectorTypeManager.lookupConnectorType(id);
	}

	@Override
	public ConnectorType createAndRegisterConnectorType(
			final String fullyQualifiedName) {
		return this.connectorTypeManager
				.createAndRegisterConnectorType(fullyQualifiedName);
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
	public NetworkLinkType createAndRegisterNetworkLinkType(
			final String fullyQualifiedName) {
		return this.networkLinkTypeManager
				.createAndRegisterNetworkLinkType(fullyQualifiedName);
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
	public ResourceType createAndRegisterResourceType(final String fullyQualifiedName) {
		return this.resourceTypesManager.createAndRegisterResourceType(fullyQualifiedName);
	}
}
