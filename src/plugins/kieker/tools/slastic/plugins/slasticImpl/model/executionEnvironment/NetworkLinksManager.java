/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.plugins.slasticImpl.model.executionEnvironment;

import kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractFQNamedEntityManager;

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
        return this.lookupEntityById(id);
    }

    @Override
    public NetworkLink createAndRegisterNetworkLink(
            final String fullyQualifiedName,
            final NetworkLinkType networkLinkType) {
        return this.createAndRegister(fullyQualifiedName);
    }

    @Override
    protected NetworkLink createEntity() {
        return ExecutionEnvironmentFactory.eINSTANCE.createNetworkLink();
    }
}
