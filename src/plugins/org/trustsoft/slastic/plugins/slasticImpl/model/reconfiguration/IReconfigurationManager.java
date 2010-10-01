package org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public interface IReconfigurationManager {

	/**
	 * Creates and returns a new deployment component for the given assembly
	 * component and execution container.
	 * 
	 * @param assemblyComponent
	 * @param toExecutionContainer
	 * @return
	 */
	public abstract DeploymentComponent replicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer);

	/**
	 * Removes the given deployment component from the system model.
	 * 
	 * @param deploymentContainer
	 */
	public abstract void dereplicateComponent(
			final DeploymentComponent deploymentContainer);

	/**
	 * Migrates the deployment component deploymentComponent from its current
	 * execution container to the execution container toExecutionContainer.
	 * 
	 * @param deploymentComponent
	 * @param toExecutionContainer
	 * @return
	 */
	public abstract DeploymentComponent migrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer);

	/**
	 * Marks execution container executionContainer as allocated.
	 * 
	 * @param executionContainer
	 * @return iff the execution container is newly allocated, false if it was
	 *         already allocated
	 */
	public abstract boolean allocateExecutionContainer(
			final ExecutionContainer executionContainer);

	/**
	 * Marks execution container executionContainer as not allocated.
	 * 
	 * @param executionContainer
	 * @return iff the execution container is newly deallocated, false if wasn't
	 *         allocated
	 */
	public abstract boolean deallocateExecutionContainer(
			final ExecutionContainer executionContainer);

}
