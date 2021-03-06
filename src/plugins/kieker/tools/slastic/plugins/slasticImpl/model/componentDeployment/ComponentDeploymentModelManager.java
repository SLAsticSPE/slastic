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

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentModel;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public class ComponentDeploymentModelManager extends AbstractModelManager<ComponentDeploymentModel> implements IDeploymentComponentsManager {

	// private static final Log LOG = LogFactory.getLog(ComponentDeploymentModelManager.class);

	private final TypeRepositoryModelManager typeRepositoryModelManager;
	private final ComponentAssemblyModelManager componentAssemblyModelManager;
	private final ExecutionEnvironmentModelManager executionEnvironmentModelManager;
	private final DeploymentComponentsManager deploymentComponentsManager;

	private ComponentDeploymentModelManager() {
		super(null);
		this.typeRepositoryModelManager = null;
		this.componentAssemblyModelManager = null;
		this.executionEnvironmentModelManager = null;
		this.deploymentComponentsManager = null;
	}

	public ComponentDeploymentModelManager(
			final ComponentDeploymentModel ComponentDeploymentModel, final TypeRepositoryModelManager typeRepositoryManager,
			final ComponentAssemblyModelManager componentAssemblyModelManager, final ExecutionEnvironmentModelManager executionEnvironmentModelManager) {
		super(ComponentDeploymentModel);
		this.typeRepositoryModelManager = typeRepositoryManager;
		this.componentAssemblyModelManager = componentAssemblyModelManager;
		this.executionEnvironmentModelManager = executionEnvironmentModelManager;
		this.deploymentComponentsManager = new DeploymentComponentsManager(ComponentDeploymentModel.getDeploymentComponents());
	}

	@Override
	public DeploymentComponent lookupDeploymentComponent(final long id) {
		return this.deploymentComponentsManager.lookupDeploymentComponent(id);
	}

	@Override
	public DeploymentComponent createAndRegisterDeploymentComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final DeploymentComponent newDeploymentComponent =
				this.deploymentComponentsManager.createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
		return newDeploymentComponent;
	}

	@Override
	public boolean deleteDeploymentComponent(
			final DeploymentComponent deploymentComponent) {
		return this.deploymentComponentsManager.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	public DeploymentComponent migrateDeploymentComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer) {
		// in the current implementation there's no need to modify the map
		// deploymentsForAsmComponentIDs
		return this.deploymentComponentsManager.migrateDeploymentComponent(deploymentComponent, toExecutionContainer);
	}

	@Override
	public DeploymentComponent deploymentComponentForAssemblyComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		return this.deploymentComponentsManager.deploymentComponentForAssemblyComponent(assemblyComponent, executionContainer);
	}

	@Override
	public Collection<DeploymentComponent> deploymentComponentsForAssemblyComponent(
			final AssemblyComponent assemblyComponent, final boolean includeInactive) {
		return this.deploymentComponentsManager.deploymentComponentsForAssemblyComponent(assemblyComponent, includeInactive);
	}
}
