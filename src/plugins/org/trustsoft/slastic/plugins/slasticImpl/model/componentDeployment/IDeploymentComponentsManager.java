package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 *
 * @author Andre van Hoorn
 */
public interface IDeploymentComponentsManager {

    /**
     * Returns the deployment component with the given fully-qualified name or null
     * if no deployment component with this name.
     *
     * @param fullyQualifiedName the fully-qualified name of the deployment component to
     * lookup
     * @return the looked up deployment component
     */
    public DeploymentComponent lookupDeploymentComponent(final String fullyQualifiedName);

    /**
     * Returns the deployment component with the given id or null if no execution
     * container with this id.
     *
     * @param id the id of the deployment component to lookup
     * @return the looked up deployment component
     */
    public DeploymentComponent lookupDeploymentComponent(final long id);

    /**
     * Creates and registers a new deployment component with the given
     * full-qualified name fullyQualifiedName.
     *
     * @param fullyQualifiedName
     * @return the new deployment component
     * @throws IllegalArgumentException if an deployment component with the given
     * fully-qualified name has already been registered
     */
    public DeploymentComponent createAndRegisterDeploymentComponent (final String fullyQualifiedName);
}