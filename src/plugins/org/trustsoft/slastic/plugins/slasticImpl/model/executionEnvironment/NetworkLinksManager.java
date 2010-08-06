package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;
import java.util.List;

/**
 *
 * @author Andre van Hoorn
 */
public class NetworkLinksManager extends AbstractFQNamedEntityManager<NetworkLink> implements INetworkLinksManager {
    public NetworkLinksManager(final List<NetworkLink> NetworkLinks){
        super(NetworkLinks);
    }

    @Override
    public NetworkLink lookupNetworkLink(
            final String fullyQualifiedName) {
        return this.lookup(fullyQualifiedName);
    }

    @Override
    public NetworkLink lookupNetworkLink(final long id){
        return this.lookup(id);
    }

    @Override
    public NetworkLink createAndRegisterNetworkLink(
            final String fullyQualifiedName) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected NetworkLink createEntity() {
        return ExecutionEnvironmentFactory.eINSTANCE.createNetworkLink();
    }
}
