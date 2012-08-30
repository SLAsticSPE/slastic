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

package kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class DeploymentComponentOperationPair {
	private final DeploymentComponent deploymentComponent;
	private final Operation operation;

	/**
	 * @return the deploymentComponent
	 */
	public final DeploymentComponent getDeploymentComponent() {
		return this.deploymentComponent;
	}

	/**
	 * @return the operation
	 */
	public final Operation getOperation() {
		return this.operation;
	}

	/**
	 * @param deploymentComponent
	 * @param operation
	 */
	public DeploymentComponentOperationPair(
			final DeploymentComponent deploymentComponent, final Operation operation) {
		this.deploymentComponent = deploymentComponent;
		this.operation = operation;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result =
				prime
						* result
						+ ((this.deploymentComponent == null) ? 0
								: this.deploymentComponent.hashCode());
		result =
				prime * result
						+ ((this.operation == null) ? 0 : this.operation.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DeploymentComponentOperationPair)) {
			return false;
		}
		final DeploymentComponentOperationPair other =
				(DeploymentComponentOperationPair) obj;
		if (this.deploymentComponent == null) {
			if (other.deploymentComponent != null) {
				return false;
			}
		} else if (!this.deploymentComponent.equals(other.deploymentComponent)) {
			return false;
		}
		if (this.operation == null) {
			if (other.operation != null) {
				return false;
			}
		} else if (!this.operation.equals(other.operation)) {
			return false;
		}
		return true;
	}

	
}
