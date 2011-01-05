package org.trustsoft.slastic.reconfiguration;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * A reconfiguration manager that simply doesn't do anything.
 * 
 * @author Andre van Hoorn
 */
public class DummyReconfigurationManagerComponent extends
		AbstractReconfigurationManagerComponent {

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
	protected DeploymentComponent concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteDereplicateComponent(
			final DeploymentComponent deploymentContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected DeploymentComponent concreteMigrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ExecutionContainer concreteAllocateExecutionContainer(
			final ExecutionContainerType executionContainerType) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteDeallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}
}
