package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentAssemblyModelManager extends AbstractModelManager<ComponentAssemblyModel> implements IAssemblyComponentsManager, IAssemblyConnectorsManager {

    private final AssemblyComponentsManager assemblyComponentsManager;
    private final AssemblyConnectorsManager assemblyConnectorsManager;

    private ComponentAssemblyModelManager() {
        super(null);
        this.assemblyComponentsManager = null;
        this.assemblyConnectorsManager = null;
    }

    public ComponentAssemblyModelManager(final ComponentAssemblyModel componentAssemblyModel){
        super(componentAssemblyModel);
        this.assemblyComponentsManager = new AssemblyComponentsManager(componentAssemblyModel.getAssemblyComponents());
        this.assemblyConnectorsManager = new AssemblyConnectorsManager(componentAssemblyModel.getAssemblyConnectors());
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
}
