package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 *
 * @author Andre van Hoorn
 */
public interface IDeploymentComponentsManager {

    /**
     * Returns the deployment component with the given id or null if no execution
     * container with this id.
     *
     * @param id the id of the deployment component to lookup
     * @return the looked up deployment component
     */
    public DeploymentComponent lookupDeploymentComponent(final long id);

    /**
     * Creates and registers a new deployment component for the assembly
     * component assemblyComponent and the execution container executionContainer.
     *
     * @param assemblyComponent
     * @param executionContainer
     * @return the new deployment component
     * @throws IllegalArgumentException if an deployment component with the given
     * fully-qualified name has already been registered
     */
    public DeploymentComponent createAndRegisterDeploymentComponent (
            final AssemblyComponent assemblyComponent,
            final ExecutionContainer executionContainer);
}
