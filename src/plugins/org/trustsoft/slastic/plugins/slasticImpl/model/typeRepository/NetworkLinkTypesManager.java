package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class NetworkLinkTypesManager extends AbstractFQNamedEntityManager<NetworkLinkType> implements INetworkLinkTypesManager {
    public NetworkLinkTypesManager(final List<NetworkLinkType> componentTypes){
        super(componentTypes);
    }

    @Override
    public NetworkLinkType lookupNetworkLinkType(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public NetworkLinkType lookupNetworkLinkType(final long id){
        return this.lookup(id);
    }

    @Override
    public NetworkLinkType createAndRegisterNetworkLinkType(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected NetworkLinkType createEntity() {
        return TypeRepositoryFactory.eINSTANCE.createNetworkLinkType();
    }
}
