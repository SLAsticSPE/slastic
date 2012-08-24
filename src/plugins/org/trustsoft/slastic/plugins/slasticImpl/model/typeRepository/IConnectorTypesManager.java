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

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 *
 * @author Andre van Hoorn
 */
public interface IConnectorTypesManager {

    /**
     * Returns the connector type with the given fully-qualified name or null
     * if no connector with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the connector type to lookup
     * @return the looked up connector type
     */
    public ConnectorType lookupConnectorType(final String fullyQualifiedName);

    /**
     * Returns the connector type with the given id or null if no connector
     * with this id.
     *
     * @param id the id of the connector type to lookup
     * @return the looked up connector type
     */
    public ConnectorType lookupConnectorType(final long id);

    /**
     * Creates and registers a new connector type with the given full-qualified
     * name fullyQualifiedName supporting the given interface.
     *
     * @param fullyQualifiedName
     * @param iface 
     * @return the new connector type
     * @throws IllegalArgumentException if a connector type with the given
     * fully-qualified name has already been registered
     */
    public ConnectorType createAndRegisterConnectorType (final String fullyQualifiedName, final Interface iface);
    
	/**
     * Creates and registers a new connector type with with a random but unique
     * full-qualified name name fullyQualifiedName supporting the given interface.
     *
     * @param fullyQualifiedName
     * @param iface 
     * @return the new connector type
	 */
    public ConnectorType createAndRegisterConnectorType (final Interface iface);
}
