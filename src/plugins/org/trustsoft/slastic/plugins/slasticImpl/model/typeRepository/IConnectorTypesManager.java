package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

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
     * name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new connector type
     * @throws IllegalArgumentException if a connector type with the given
     * fully-qualified name has already been registered
     */
    public ConnectorType createAndRegisterConnectorType (final String fullyQualifiedName);
}
