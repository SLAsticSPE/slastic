package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssembly;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentAssemblyManager implements IAssemblyComponentsManager, IAssemblyConnectorsManager {

    private final AssemblyComponentsManager AssemblyComponentsManager;
    private final AssemblyConnectorsManager AssemblyConnectorsManager;

    private ComponentAssemblyManager() {
        this.AssemblyComponentsManager = null;
        this.AssemblyConnectorsManager = null;
    }

    public ComponentAssemblyManager(final ComponentAssembly ComponentAssembly){
        this.AssemblyComponentsManager = new AssemblyComponentsManager(ComponentAssembly.getAssemblyComponents());
        this.AssemblyConnectorsManager = new AssemblyConnectorsManager(ComponentAssembly.getAssemblyConnectors());
    }

    @Override
    public AssemblyComponent lookupAssemblyComponent(String fullyQualifiedName) {
        return this.AssemblyComponentsManager.lookup(fullyQualifiedName);
    }

    @Override
    public AssemblyComponent lookupAssemblyComponent(long id) {
        return this.AssemblyComponentsManager.lookupAssemblyComponent(id);
    }

    @Override
    public AssemblyComponent createAndRegisterAssemblyComponent(String fullyQualifiedName) {
        return this.AssemblyComponentsManager.createAndRegisterAssemblyComponent(fullyQualifiedName);
    }

    @Override
    public AssemblyConnector lookupAssemblyConnector(String fullyQualifiedName) {
        return this.AssemblyConnectorsManager.lookupAssemblyConnector(fullyQualifiedName);
    }

    @Override
    public AssemblyConnector lookupAssemblyConnector(long id) {
        return this.AssemblyConnectorsManager.lookupAssemblyConnector(id);
    }

    @Override
    public AssemblyConnector createAndRegisterAssemblyConnector(String fullyQualifiedName) {
        return this.AssemblyConnectorsManager.createAndRegisterAssemblyConnector(fullyQualifiedName);
    }
}
