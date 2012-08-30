/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.plugins.slasticImpl.model.componentDeployment;

import java.util.Collection;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public interface IDeploymentComponentsManager {

	/**
	 * Returns the deployment component with the given id or null if no
	 * execution container with this id.
	 * 
	 * @param id
	 *            the id of the deployment component to lookup
	 * @return the looked up deployment component
	 */
	public DeploymentComponent lookupDeploymentComponent(final long id);

	/**
	 * Creates and registers a new deployment component for the assembly
	 * component assemblyComponent and the execution container
	 * executionContainer.
	 * 
	 * @param assemblyComponent
	 * @param executionContainer
	 * @return the new deployment component
	 * @throws IllegalArgumentException
	 *             if an deployment component with the given fully-qualified
	 *             name has already been registered
	 */
	public DeploymentComponent createAndRegisterDeploymentComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer);

	/**
	 * Removes the given deployment component from the model.
	 * 
	 * @param deploymentComponent
	 *            the deployment component to delete
	 * @return
	 * @throws NullPointerException
	 *             if deploymentComponent is null
	 */
	public boolean deleteDeploymentComponent(final DeploymentComponent deploymentComponent);

	/**
	 * Migrates the deployment component deploymentComponent from its current
	 * execution container to the execution container toExecutionContainer.
	 * 
	 * @param deploymentComponent
	 * @param toExecutionContainer
	 */
	public DeploymentComponent migrateDeploymentComponent(final DeploymentComponent deploymentComponent, final ExecutionContainer toExecutionContainer);

	/**
	 * Returns the deployment component associated with the given assembly
	 * component and execution container, or null if no such deployment
	 * component exists.
	 * 
	 * @param assemblyComponent
	 * @param executionContainer
	 * @return
	 */
	public DeploymentComponent deploymentComponentForAssemblyComponent(final AssemblyComponent assemblyComponent, final ExecutionContainer executionContainer);

	/**
	 * Returns a list of all registered deployment components associated with
	 * the passed assembly component or null if the assembly component is not
	 * registered.
	 * 
	 * @param assemblyComponent
	 * @return
	 */
	public Collection<DeploymentComponent> deploymentComponentsForAssemblyComponent(final AssemblyComponent assemblyComponent, final boolean includeInactive);
}
