package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class ConnectorTypesManager extends AbstractFQNamedEntityManager<ConnectorType> implements IConnectorTypesManager {
    public ConnectorTypesManager(final List<ConnectorType> connectorTypes){
        super(connectorTypes);
    }

    @Override
    public ConnectorType lookupConnectorType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ConnectorType lookupConnectorType(final long id){
        return this.lookupEntityById(id);
    }

    @Override
    public ConnectorType createAndRegisterConnectorType(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected ConnectorType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createConnectorType();
    }
}
