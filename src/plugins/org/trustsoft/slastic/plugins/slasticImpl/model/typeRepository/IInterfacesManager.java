package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 *
 * @author Andre van Hoorn
 */
public interface IInterfacesManager {

    /**
     * Returns the interface with the given fully-qualified name or null
     * if no interface with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the interface to lookup
     * @return the looked up interface
     */
    public Interface lookupInterface(final String fullyQualifiedName);

    /**
     * Returns the interface with the given id or null if no component
     * with this id.
     *
     * @param id the id of the interface to lookup
     * @return the looked up interface
     */
    public Interface lookupInterface(final long id);

    /**
     * Creates and registers a new interface with the given full-qualified
     * name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new interface
     * @throws IllegalArgumentException if a interface with the given
     * fully-qualified name has already been registered
     */
    public Interface createAndRegisterInterface (final String fullyQualifiedName);
}
