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
