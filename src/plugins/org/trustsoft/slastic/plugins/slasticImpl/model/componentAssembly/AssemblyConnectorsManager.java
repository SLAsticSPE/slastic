package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class AssemblyConnectorsManager extends AbstractFQNamedEntityManager<AssemblyConnector> implements IAssemblyConnectorsManager {
    public AssemblyConnectorsManager(final List<AssemblyConnector> AssemblyConnectors){
        super(AssemblyConnectors);
    }

    @Override
    public AssemblyConnector lookupAssemblyConnector(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public AssemblyConnector lookupAssemblyConnector(final long id){
        return this.lookup(id);
    }

    @Override
    public AssemblyConnector createAndRegisterAssemblyConnector(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected AssemblyConnector createEntity() {
        return ComponentAssemblyFactory.eINSTANCE.createAssemblyConnector();
    }
}
