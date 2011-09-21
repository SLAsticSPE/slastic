package org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

/**
 *
 * @author Andre van Hoorn
 */
public interface IAssemblyConnectorsManager {

    /**
     * Returns the assembly connector with the given fully-qualified name or null
     * if no assembly connector with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the assembly connector to
     * lookup
     * @return the looked up assembly connector
     */
    public AssemblyConnector lookupAssemblyConnector(final String fullyQualifiedName);

    /**
     * Returns the assembly connector with the given id or null if no execution
     * container with this id.
     *
     * @param id the id of the assembly connector to lookup
     * @return the looked up assembly connector
     */
    public AssemblyConnector lookupAssemblyConnector(final long id);

    /**
     * Creates and registers a new assembly connector with the given
     * full-qualified name fullyQualifiedName and of type connectorType.
     *
     * @param fullyQualifiedName
     * @param connectorType
     * @return the new assembly connector
     * @throws IllegalArgumentException if an assembly connector with the given
     * fully-qualified name has already been registered
     */
    public AssemblyConnector createAndRegisterAssemblyConnector (
            final String fullyQualifiedName,
            final ConnectorType connectorType);
    
    /**
     * Creates and registers a new assembly connector with a random 
     * but unique full-qualified name and of type connectorType.
     *
     * @param connectorType
     * @return the new assembly connector
     */
    public AssemblyConnector createAndRegisterAssemblyConnector (
            final ConnectorType connectorType);
    
}
