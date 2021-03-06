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
import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;

/**
 *
 * @author Andre van Hoorn
 */
public interface INetworkLinksManager {

    /**
     * Returns the network link with the given fully-qualified name or null
     * if no network link with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the network link to
     * lookup
     * @return the looked up network link
     */
    public NetworkLink lookupNetworkLink(final String fullyQualifiedName);

    /**
     * Returns the network link with the given id or null if no execution
     * container with this id.
     *
     * @param id the id of the network link to lookup
     * @return the looked up network link
     */
    public NetworkLink lookupNetworkLink(final long id);

    /**
     * Creates and registers a new network link with the given
     * full-qualified name fullyQualifiedName and the type networkLinkType.
     *
     * @param fullyQualifiedName
     * @param networkLinkType 
     * @return the new network link
     * @throws IllegalArgumentException if an network link with the given
     * fully-qualified name has already been registered
     */
    public NetworkLink createAndRegisterNetworkLink (final String fullyQualifiedName, final NetworkLinkType networkLinkType);
}
