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

package org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration;

import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.ComponentDeploymentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ReconfigurationManager implements IReconfigurationManager {
	/* Managers for the submodels */

	@SuppressWarnings("unused")
	// currently unused, but should be present for reasons of consistency and future extensions
	private final TypeRepositoryModelManager typeRepositoryManager;

	@SuppressWarnings("unused")
	// currently unused, but should be present for reasons of consistency and future extensions
	private final ComponentAssemblyModelManager componentAssemblyModelManager;

	private final ExecutionEnvironmentModelManager executionEnvironmentModelManager;

	private final ComponentDeploymentModelManager componentDeploymentModelManager;

	/**
	 * Objects must not be created using this constructor.
	 */
	@SuppressWarnings("unused")
	private ReconfigurationManager() {
		this(null, null, null, null);
	}

	public ReconfigurationManager(
			final TypeRepositoryModelManager typeRepositoryManager,
			final ComponentAssemblyModelManager componentAssemblyModelManager,
			final ExecutionEnvironmentModelManager executionEnvironmentModelManager,
			final ComponentDeploymentModelManager componentDeploymentModelManager) {
		this.typeRepositoryManager = typeRepositoryManager;
		this.componentAssemblyModelManager = componentAssemblyModelManager;
		this.executionEnvironmentModelManager = executionEnvironmentModelManager;
		this.componentDeploymentModelManager = componentDeploymentModelManager;
	}

	@Override
	public DeploymentComponent replicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer) {
		return this.componentDeploymentModelManager.createAndRegisterDeploymentComponent(assemblyComponent, toExecutionContainer);
	}

	@Override
	public void dereplicateComponent(
			final DeploymentComponent deploymentContainer) {
		this.componentDeploymentModelManager.deleteDeploymentComponent(deploymentContainer);
	}

	@Override
	public DeploymentComponent migrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer) {
		return this.componentDeploymentModelManager.migrateDeploymentComponent(deploymentComponent, toExecutionContainer);
	}

	@Override
	public boolean allocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		return this.executionEnvironmentModelManager.allocateExecutionContainer(executionContainer);
	}

	@Override
	public boolean deallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		return this.executionEnvironmentModelManager.deallocateExecutionContainer(executionContainer);
	}
}
