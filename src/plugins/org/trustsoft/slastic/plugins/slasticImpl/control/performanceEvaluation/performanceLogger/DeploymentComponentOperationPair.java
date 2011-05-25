package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

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
