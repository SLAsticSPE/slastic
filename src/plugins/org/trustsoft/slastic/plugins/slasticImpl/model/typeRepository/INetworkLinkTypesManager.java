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
