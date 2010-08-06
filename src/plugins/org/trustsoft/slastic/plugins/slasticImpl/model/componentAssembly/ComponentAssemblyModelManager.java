package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.core.SLAsticModel;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class ComponentAssemblyModelManager extends AbstractModelManager<ComponentAssemblyModel> implements IAssemblyComponentsManager, IAssemblyConnectorsManager {

    private final AssemblyComponentsManager AssemblyComponentsManager;
    private final AssemblyConnectorsManager AssemblyConnectorsManager;

    private ComponentAssemblyModelManager() {
        super(null);
        this.AssemblyComponentsManager = null;
        this.AssemblyConnectorsManager = null;
    }

    public ComponentAssemblyModelManager(final ComponentAssemblyModel componentAssemblyModel){
        super(componentAssemblyModel);
        this.AssemblyComponentsManager = new AssemblyComponentsManager(componentAssemblyModel.getAssemblyComponents());
        this.AssemblyConnectorsManager = new AssemblyConnectorsManager(componentAssemblyModel.getAssemblyConnectors());
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
