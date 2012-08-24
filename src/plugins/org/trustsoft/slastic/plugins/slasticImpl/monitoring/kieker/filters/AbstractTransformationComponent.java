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

package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.IDeploymentComponentsManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public abstract class AbstractTransformationComponent {

	/**
	 * @return the modelManager
	 */
	protected final ModelManager getModelManager() {
		return this.modelManager;
	}

	/**
	 * @return the typeModelManager
	 */
	protected final TypeRepositoryModelManager getTypeModelManager() {
		return this.typeModelManager;
	}

	/**
	 * @return the assemblyModelManager
	 */
	protected final ComponentAssemblyModelManager getAssemblyModelManager() {
		return this.assemblyModelManager;
	}

	/**
	 * @return the deploymentModelManager
	 */
	protected final IDeploymentComponentsManager getDeploymentModelManager() {
		return this.deploymentModelManager;
	}

	/**
	 * @return the executionEnvModelManager
	 */
	protected final ExecutionEnvironmentModelManager getExecutionEnvModelManager() {
		return this.executionEnvModelManager;
	}

	private final ModelManager modelManager;
	private final TypeRepositoryModelManager typeModelManager;
	private final ComponentAssemblyModelManager assemblyModelManager;
	private final IDeploymentComponentsManager deploymentModelManager;
	private final ExecutionEnvironmentModelManager executionEnvModelManager;

	public AbstractTransformationComponent(final ModelManager modelManager) {
		this.modelManager = modelManager;
		this.typeModelManager = this.modelManager.getTypeRepositoryManager();
		this.assemblyModelManager = this.modelManager.getComponentAssemblyModelManager();
		this.executionEnvModelManager = this.modelManager.getExecutionEnvironmentModelManager();
		this.deploymentModelManager = this.modelManager.getComponentDeploymentModelManager();
	}

}
