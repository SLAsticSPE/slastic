package org.trustsoft.slastic.reconfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentDereplication;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerAllocation;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerDeallocation;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationOperation;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 */
public abstract class AbstractReconfigurationManagerComponent extends
		AbstractSLAsticComponent implements IReconfigurationPlanReceiver {
	private final Log log = LogFactory
			.getLog(AbstractReconfigurationManagerComponent.class);

	public static final String PROP_PREFIX = "slastic.reconfiguration";

	private AbstractControlComponent controlComponent;

	public final AbstractControlComponent getControlComponent() {
		return this.controlComponent;
	}

	public final void setControlComponent(
			final AbstractControlComponent controlComponent) {
		this.controlComponent = controlComponent;
	}

	// TODO: check whether it needs to be final 
	// TODO: add concept to implement role-backs in implementing classes 
	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		if (plan == null) {
			throw new IllegalArgumentException("plan is null");
		}

		if (plan.getOperations().size() == 0) {
			this.log.warn("Empty plan");
			return;
		}

		for (final ReconfigurationOperation op : plan.getOperations()) {
			// Check of which type the Operation is and executing belonging
			// method
			if (op instanceof ComponentReplication) {
				final AssemblyComponent comp =
						((ComponentReplication) op).getComponent();
				final ExecutionContainer destination =
						((ComponentReplication) op).getDestination();
				// TODO: evaluate return value
				this.concreteReplicateComponent(comp, destination);
			} else if (op instanceof ComponentDereplication) {
				final DeploymentComponent comp =
						((ComponentDereplication) op).getComponent();
				// TODO: evaluate return value
				this.concreteDereplicateComponent(comp);
			} else if (op instanceof ComponentMigration) {
				final DeploymentComponent comp =
						((ComponentMigration) op).getComponent();
				final ExecutionContainer destination = ((ComponentMigration)
						op).getDestination();
				// TODO: evaluate return value
				this.concreteMigrateComponent(comp, destination);
			} else if (op instanceof ContainerAllocation) {
				final ExecutionContainerType container =
						((ContainerAllocation) op).getContainerType();
				// TODO: evaluate return value
				this.concreteAllocateExecutionContainer(container);
			} else if (op instanceof ContainerDeallocation) {
				final ExecutionContainer container =
						((ContainerDeallocation) op).getContainer();
				// TODO: evaluate return value
				this.concreteDeallocateExecutionContainer(container);
			} else {
				// throw new IllegalReconfigurationOperationException();
				this.log
						.fatal("Illegal reconfiguration operation "
								+ op.getClass().getName());
			}
		}
	}
	
	/**
	 * Creates and returns a new deployment component for the given assembly
	 * component and execution container.
	 * 
	 * TODO: Pass prepared return value
	 * 
	 * @param assemblyComponent
	 * @param toExecutionContainer
	 * @return
	 */
	protected abstract DeploymentComponent concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer);

	/**
	 * Removes the given deployment component from the system model.
	 * 
	 * @param deploymentContainer
	 */
	protected abstract boolean concreteDereplicateComponent(
			final DeploymentComponent deploymentContainer);

	/**
	 * Migrates the deployment component deploymentComponent from its current
	 * execution container to the execution container toExecutionContainer.
	 * 
	 * TODO: Pass prepared return value
	 * 
	 * @param deploymentComponent
	 * @param destination
	 * @return
	 */
	protected abstract DeploymentComponent concreteMigrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination);

	/**
	 * TODO: Pass prepared return value
	 * 
	 * @param executionContainerType
	 * @return
	 */
	protected abstract ExecutionContainer concreteAllocateExecutionContainer(
			final ExecutionContainerType executionContainerType);

	/**
	 * Deallocates the execution container executionContainer.
	 * 
	 * @param executionContainer
	 * @return iff the execution container is newly deallocated, false if wasn't
	 *         allocated
	 */
	protected abstract boolean concreteDeallocateExecutionContainer(
			final ExecutionContainer executionContainer);

}
