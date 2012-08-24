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

package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;

/**
 *
 * @author Andre van Hoorn
 */
public interface INetworkLinkTypesManager {

    /**
     * Returns the network link type with the given fully-qualified name or null
     * if no network link with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the network link type to lookup
     * @return the looked up network link type
     */
    public NetworkLinkType lookupNetworkLinkType(final String fullyQualifiedName);

    /**
     * Returns the network link type with the given id or null if no network link
     * with this id.
     *
     * @param id the id of the network link type to lookup
     * @return the looked up network link type
     */
    public NetworkLinkType lookupNetworkLinkType(final long id);

    /**
     * Creates and registers a new network link type with the given full-qualified
     * name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new network link type
     * @throws IllegalArgumentException if a network link type with the given
     * fully-qualified name has already been registered
     */
    public NetworkLinkType createAndRegisterNetworkLinkType (final String fullyQualifiedName);
}
