package org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment;

import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;

/**
 *
 * @author Andre van Hoorn
 */
public interface INetworkLinksManager {

    /**
     * Returns the network link with the given fully-qualified name or null
     * if no network link with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the network link to
     * lookup
     * @return the looked up network link
     */
    public NetworkLink lookupNetworkLink(final String fullyQualifiedName);

    /**
     * Returns the network link with the given id or null if no execution
     * container with this id.
     *
     * @param id the id of the network link to lookup
     * @return the looked up network link
     */
    public NetworkLink lookupNetworkLink(final long id);

    /**
     * Creates and registers a new network link with the given
     * full-qualified name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new network link
     * @throws IllegalArgumentException if an network link with the given
     * fully-qualified name has already been registered
     */
    public NetworkLink createAndRegisterNetworkLink (final String fullyQualifiedName);
}
