package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import java.util.List;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

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
            final String fullyQualifiedName, final Interface iface) {
    	final ConnectorType ct = this.createAndRegister(fullyQualifiedName);
    	ct.setInterface(iface);
    	return ct;
    }

    @Override
    protected ConnectorType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createConnectorType();
    }
}
