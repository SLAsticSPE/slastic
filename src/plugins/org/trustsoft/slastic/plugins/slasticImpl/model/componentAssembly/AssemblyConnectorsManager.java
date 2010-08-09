package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
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
        return this.lookupEntityById(id);
    }

   @Override
    public AssemblyConnector createAndRegisterAssemblyConnector(
            final String fullyQualifiedName,
            final ConnectorType connectorType) {
        AssemblyConnector assemblyConnector =
                this.createAndRegister(fullyQualifiedName);
        assemblyConnector.setConnectorType(connectorType);
        return assemblyConnector;
    }

    @Override
    protected AssemblyConnector createEntity() {
        return ComponentAssemblyFactory.eINSTANCE.createAssemblyConnector();
    }
}
