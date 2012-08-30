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

package kieker.tools.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * A reconfiguration manager that simply doesn't do anything.
 * 
 * @author Andre van Hoorn
 */
public class DummyReconfigurationManagerComponent extends AbstractReconfigurationManagerComponent {

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {
		// do nothing
	}

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		// do nothing
	}

	@Override
	public boolean init() {
		return true;
	}

	@Override
	protected boolean concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer,
			final DeploymentComponent resDeploymentComponent) {
		// we successfully did nothing ;-)
		return true;
	}

	@Override
	protected boolean concreteDereplicateComponent(
			final DeploymentComponent deploymentContainer) {
		// we successfully did nothing ;-)
		return true;
	}

	@Override
	protected boolean concreteMigrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination,
			final DeploymentComponent resDeploymentComponent) {
		// we successfully did nothing ;-)
		return true;
	}

	@Override
	protected boolean concreteAllocateExecutionContainer(
			final ExecutionContainerType executionContainerType,
			final ExecutionContainer resExecutionContainer) {
		// we successfully did nothing ;-)
		return true;
	}

	@Override
	protected boolean concreteDeallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		// we successfully did nothing ;-)
		return true;
	}

	@Override
	protected DeploymentComponent createPreliminaryDeploymentComponentInModel(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deletePreliminaryDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ExecutionContainer createPreliminaryExecutionContainerInModel(
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deletePreliminaryExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deleteExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deleteDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		throw new UnsupportedOperationException();
	}
}
