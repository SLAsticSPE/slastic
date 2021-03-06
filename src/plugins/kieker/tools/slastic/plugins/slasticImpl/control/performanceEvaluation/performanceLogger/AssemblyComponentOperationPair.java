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

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class AssemblyComponentOperationPair {
	private final AssemblyComponent assemblyComponent;
	private final Operation operation;

	/**
	 * @param assemblyComponent
	 * @param operatio
	 */
	public AssemblyComponentOperationPair(final AssemblyComponent assemblyComponent, final Operation operation) {
		this.assemblyComponent = assemblyComponent;
		this.operation = operation;
	}

	/**
	 * @return the assemblyComponent
	 */
	public final AssemblyComponent getAssemblyComponent() {
		return this.assemblyComponent;
	}

	/**
	 * @return the operation
	 */
	public final Operation getOperation() {
		return this.operation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result =
				(prime
						* result)
						+ ((this.assemblyComponent == null) ? 0 : this.assemblyComponent
								.hashCode());
		result =
				(prime * result)
						+ ((this.operation == null) ? 0 : this.operation.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!(obj instanceof AssemblyComponentOperationPair)) {
			return false;
		}
		final AssemblyComponentOperationPair other =
				(AssemblyComponentOperationPair) obj;
		if (this.assemblyComponent == null) {
			if (other.assemblyComponent != null) {
				return false;
			}
		} else if (!this.assemblyComponent.equals(other.assemblyComponent)) {
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
