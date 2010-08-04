package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;

/**
 *
 * @author Andre van Hoorn
 */
public interface IComponentTypeManager {

    /**
     * Returns the component type with the given fully-qualified name or null
     * if no component with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the component type to lookup
     * @return the looked up component type
     */
    public ComponentType lookupComponentType(final String fullyQualifiedName);

    /**
     * Returns the component type with the given id or null if no component
     * with this id.
     *
     * @param id the id of the component type to lookup
     * @return the looked up component type
     */
    public ComponentType lookupComponentType(final long id);

    /**
     * Creates and registers a new component type with the given full-qualified
     * name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new component type
     * @throws IllegalArgumentException if a component type with the given
     * fully-qualified name has already been registered
     */
    public ComponentType createAndRegisterComponentType (final String fullyQualifiedName);
}
