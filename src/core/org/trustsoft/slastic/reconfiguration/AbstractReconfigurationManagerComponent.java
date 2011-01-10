package org.trustsoft.slastic.reconfiguration;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
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
	// TODO: add concept to implement role-backs in (implementing classes)
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

		final List<ReconfigurationOperation> executedOperations =
				new ArrayList<ReconfigurationOperation>();

		for (final ReconfigurationOperation op : plan.getOperations()) {
			// Check of which type the Operation is and executing belonging
			// method
			if (op instanceof ComponentReplication) {
				final AssemblyComponent comp =
						((ComponentReplication) op).getComponent();
				final ExecutionContainer destination =
						((ComponentReplication) op).getDestination();
				final DeploymentComponent resDeploymentComponent =
						this.replicateComponent(comp, destination);
				// TODO: evaluate return value
			} else if (op instanceof ComponentDereplication) {
				final DeploymentComponent comp =
						((ComponentDereplication) op).getComponent();
				final boolean success = this.dereplicateComponent(comp);
				// TODO: evaluate return value
			} else if (op instanceof ComponentMigration) {
				final DeploymentComponent comp =
						((ComponentMigration) op).getComponent();
				final ExecutionContainer destination =
						((ComponentMigration) op).getDestination();
				final DeploymentComponent resDeploymentComponent =
						this.migrateComponent(comp, destination);
				// TODO: evaluate return value
			} else if (op instanceof ContainerAllocation) {
				final ExecutionContainerType container =
						((ContainerAllocation) op).getContainerType();
				final ExecutionContainer resContainer =
						this.allocateExecutionContainer(container);
				// TODO: evaluate return value
			} else if (op instanceof ContainerDeallocation) {
				final ExecutionContainer container =
						((ContainerDeallocation) op).getContainer();
				final boolean success =
						this.concreteDeallocateExecutionContainer(container);
				// TODO: evaluate return value
			} else {
				// throw new IllegalReconfigurationOperationException();
				this.log.fatal("Illegal reconfiguration operation "
						+ op.getClass().getName());
			}
			executedOperations.add(op);
		}
	}

	// TODO: remove
	private int nextId = 1;

	/**
	 * TODO: change visibility to private as soon as extended plan meta-model
	 * exists
	 * 
	 * @param assemblyComponent
	 * @param toExecutionContainer
	 * @return
	 */
	public DeploymentComponent replicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer) {
		DeploymentComponent resDeploymentComponent;
		{ // TODO: acquire instance from Model Manager
			resDeploymentComponent =
					ComponentDeploymentFactory.eINSTANCE
							.createDeploymentComponent();
			resDeploymentComponent.setAssemblyComponent(assemblyComponent);
			resDeploymentComponent.setExecutionContainer(toExecutionContainer);
			resDeploymentComponent.setId(this.nextId++);
		}

		final boolean success =
				this.concreteReplicateComponent(assemblyComponent,
						toExecutionContainer, resDeploymentComponent);

		if (!success) {
			// TODO: remove instance from Model Manager?
			resDeploymentComponent = null;
			this.log.error("concreteReplicateComponent failed");
		}

		// TODO: log event

		return resDeploymentComponent;
	}

	/**
	 * Creates and new deployment component for the given assembly component and
	 * execution container.
	 * 
	 * @param assemblyComponent
	 * @param toExecutionContainer
	 * @return
	 */
	protected abstract boolean concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer,
			final DeploymentComponent resDeploymentComponent);

	/**
	 * TODO: change visibility to private as soon as extended plan meta-model
	 * exists
	 * 
	 * @param deploymentContainer
	 * @return
	 */
	public boolean dereplicateComponent(
			final DeploymentComponent deploymentContainer) {
		final boolean success =
				this.concreteDereplicateComponent(deploymentContainer);

		// TODO: Notify model manager (do we need a "State Manager?")

		if (!success) {
			this.log.error("concreteDereplicateComponent failed");
		}
		// TODO: log event

		return success;
	}

	/**
	 * Removes the given deployment component from the system model.
	 * 
	 * @param deploymentComponent
	 */
	protected abstract boolean concreteDereplicateComponent(
			final DeploymentComponent deploymentComponent);

	/**
	 * TODO: change visibility to private as soon as extended plan meta-model
	 * exists
	 * 
	 * @param deploymentComponent
	 * @param destination
	 * @return
	 */
	public DeploymentComponent migrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination) {
		DeploymentComponent resDeploymentComponent;
		{ // TODO: acquire instance from Model Manager
			resDeploymentComponent =
					ComponentDeploymentFactory.eINSTANCE
							.createDeploymentComponent();
			resDeploymentComponent.setAssemblyComponent(deploymentComponent
					.getAssemblyComponent());
			resDeploymentComponent.setExecutionContainer(destination);
			resDeploymentComponent.setId(this.nextId++);
		}

		final boolean success =
				this.concreteMigrateComponent(deploymentComponent, destination,
						resDeploymentComponent);

		if (!success) {
			// TODO: remove instance from Model Manager?
			resDeploymentComponent = null;
			this.log.error("concreteMigrateComponent failed");
		}

		// TODO: log event

		return resDeploymentComponent;
	}

	/**
	 * Migrates the deployment component deploymentComponent from its current
	 * execution container to the execution container toExecutionContainer.
	 * 
	 * @param deploymentComponent
	 * @param destination
	 * @return
	 */
	protected abstract boolean concreteMigrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination,
			final DeploymentComponent resDeploymentComponent);

	/**
	 * TODO: change visibility to private as soon as extended plan meta-model
	 * exists
	 * 
	 * @param executionContainerType
	 * @return
	 */
	public ExecutionContainer allocateExecutionContainer(
			final ExecutionContainerType executionContainerType) {
		ExecutionContainer resContainer;
		{ // TODO: acquire instance from Model Manager
			resContainer =
					ExecutionEnvironmentFactory.eINSTANCE
							.createExecutionContainer();
			resContainer.setExecutionContainerType(executionContainerType);
			resContainer.setName("hostname-" + this.nextId++);
			// TODO: we need to store this mapping somewhere where the
			// Monitoring Manager has access to this information
			resContainer.setId(this.nextId++);
		}

		final boolean success =
				this.concreteAllocateExecutionContainer(executionContainerType,
						resContainer);

		if (!success) {
			// TODO: remove instance from Model Manager?
			resContainer = null;
			this.log.error("concreteAllocateExecutionContainer failed");
		}

		// TODO: log event

		return resContainer;
	}

	/**
	 * 
	 * @param executionContainerType
	 * @return
	 */
	protected abstract boolean concreteAllocateExecutionContainer(
			final ExecutionContainerType executionContainerType,
			final ExecutionContainer resExecutionContainer);

	/**
	 * TODO: change visibility to private as soon as extended plan meta-model
	 * exists
	 * 
	 * @param executionContainer
	 * @return
	 */
	public boolean deallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		final boolean success =
				this.concreteDeallocateExecutionContainer(executionContainer);

		// TODO: Notify model manager (do we need a "State Manager?")

		if (!success) {
			this.log.error("concreteDeallocateExecutionContainer failed");
		}
		// TODO: log event

		return success;
	}

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
