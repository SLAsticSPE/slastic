package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 *
 * @author Andre van Hoorn
 */
public interface IExecutionContainerTypeManager {

    /**
     * Returns the execution container type with the given fully-qualified name or null
     * if no component with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the execution container type to lookup
     * @return the looked up execution container type
     */
    public ExecutionContainerType lookupExecutionContainerType(final String fullyQualifiedName);

    /**
     * Returns the execution container type with the given id or null if no component
     * with this id.
     *
     * @param id the id of the execution container type to lookup
     * @return the looked up execution container type
     */
    public ExecutionContainerType lookupExecutionContainerType(final long id);

    /**
     * Creates and registers a new execution container type with the given full-qualified
     * name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new execution container type
     * @throws IllegalArgumentException if a execution container type with the given
     * fully-qualified name has already been registered
     */
    public ExecutionContainerType createAndRegisterExecutionContainerType (final String fullyQualifiedName);
}
