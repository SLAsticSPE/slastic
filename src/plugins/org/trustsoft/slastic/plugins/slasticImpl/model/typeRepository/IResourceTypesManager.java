package org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository;

import de.cau.se.slastic.metamodel.typeRepository.ResourceType;

/**
 *
 * @author Andre van Hoorn
 */
public interface IResourceTypesManager {

    /**
     * Returns the resource type with the given fully-qualified name or null
     * if no resource type with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the resource type to lookup
     * @return the looked up resource type
     */
    public ResourceType lookupResourceType(final String fullyQualifiedName);

    /**
     * Returns the resource type with the given id or null if no resource type
     * with this id.
     *
     * @param id the id of the network link type to lookup
     * @return the looked up network link type
     */
    public ResourceType lookupResourceType(final long id);

    /**
     * Creates and registers a resource type with the given full-qualified
     * name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new resource type
     * @throws IllegalArgumentException if a resource type with the given
     * fully-qualified name has already been registered
     */
    public ResourceType createAndRegisterResourceType (final String fullyQualifiedName);
}
