package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public class ComponentAssemblyModelManager extends AbstractModelManager<ComponentAssemblyModel> implements
		IAssemblyComponentsManager, IAssemblyConnectorsManager, ISystemProvidedInterfacesManager {

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
		this.systemProvidedInterfacesManager = new SystemProvidedInterfacesManager(componentAssemblyModel.getSystemProvidedInterfaces(), componentAssemblyModel.getSystemProvidedInterfaceDelegationConnectors(), typeRepositoryManager);
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
	public AssemblyComponent createAndRegisterAssemblyComponent(
			final String fullyQualifiedName,
			final ComponentType componentType) {
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
	public AssemblyComponentConnector createAndRegisterAssemblyConnector(
			final String fullyQualifiedName,
			final ConnectorType connectorType) {
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
	public AssemblyComponentConnector lookupAssemblyConnector(final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent, final Signature signature) {
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
}
