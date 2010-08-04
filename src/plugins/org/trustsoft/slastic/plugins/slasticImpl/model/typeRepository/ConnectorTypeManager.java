package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class ConnectorTypeManager extends AbstractFQNamedEntityManager<ConnectorType> implements IConnectorTypeManager {
    public ConnectorTypeManager(final List<ConnectorType> componentTypes){
        super(componentTypes);
    }

    @Override
    public ConnectorType lookupConnectorType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public ConnectorType lookupConnectorType(final long id){
        return this.lookup(id);
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
