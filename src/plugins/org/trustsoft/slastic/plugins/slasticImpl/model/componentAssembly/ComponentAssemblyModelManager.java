package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 */
public class ComponentAssemblyModelManager extends AbstractModelManager<ComponentAssemblyModel> implements
		IAssemblyComponentsManager, IAssemblyConnectorsManager {

	private final TypeRepositoryModelManager typeRepositoryModelManager;
	private final AssemblyComponentsManager assemblyComponentsManager;
	private final AssemblyConnectorsManager assemblyConnectorsManager;

	private ComponentAssemblyModelManager() {
		super(null);
		this.typeRepositoryModelManager = null;
		this.assemblyComponentsManager = null;
		this.assemblyConnectorsManager = null;
	}

	public ComponentAssemblyModelManager(final ComponentAssemblyModel componentAssemblyModel, final TypeRepositoryModelManager typeRepositoryManager) {
		super(componentAssemblyModel);
		this.typeRepositoryModelManager = typeRepositoryManager;
		this.assemblyComponentsManager = new AssemblyComponentsManager(componentAssemblyModel.getAssemblyComponents());
		this.assemblyConnectorsManager = new AssemblyConnectorsManager(componentAssemblyModel.getAssemblyConnectors(), this.typeRepositoryModelManager);
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
	public AssemblyConnector lookupAssemblyConnector(final String fullyQualifiedName) {
		return this.assemblyConnectorsManager.lookupAssemblyConnector(fullyQualifiedName);
	}

	@Override
	public AssemblyConnector lookupAssemblyConnector(final long id) {
		return this.assemblyConnectorsManager.lookupAssemblyConnector(id);
	}

	@Override
	public AssemblyConnector createAndRegisterAssemblyConnector(
			final String fullyQualifiedName,
			final ConnectorType connectorType) {
		return this.assemblyConnectorsManager.createAndRegisterAssemblyConnector(fullyQualifiedName, connectorType);
	}

	@Override
	public AssemblyConnector createAndRegisterAssemblyConnector(final ConnectorType connectorType) {
		return this.assemblyConnectorsManager.createAndRegisterAssemblyConnector(connectorType);
	}

	@Override
	public boolean connect(final AssemblyConnector assemblyConnector, final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent) {
		return this.assemblyConnectorsManager.connect(assemblyConnector, requiringComponent, providingComponent);
	}

	@Override
	public AssemblyConnector lookupAssemblyConnector(final AssemblyComponent requiringComponent,
			final AssemblyComponent providingComponent, final Signature signature) {
		return this.assemblyConnectorsManager
				.lookupAssemblyConnector(requiringComponent, providingComponent, signature);
	}
}
