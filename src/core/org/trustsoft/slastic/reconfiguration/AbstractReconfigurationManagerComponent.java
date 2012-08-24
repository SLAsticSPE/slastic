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

package org.trustsoft.slastic.reconfiguration;

import java.util.ArrayList;
import java.util.List;

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
public abstract class AbstractReconfigurationManagerComponent extends AbstractSLAsticComponent implements IReconfigurationPlanReceiver {

	private static final Log LOG = LogFactory.getLog(AbstractReconfigurationManagerComponent.class);

	public static final String PROP_PREFIX = "slastic.reconfiguration";

	private AbstractControlComponent controlComponent;

	public final AbstractControlComponent getControlComponent() {
		return this.controlComponent;
	}

	public final void setControlComponent(final AbstractControlComponent controlComponent) {
		this.controlComponent = controlComponent;
	}

	// TODO: check whether it needs to be final
	// TODO: add concept to implement role-backs in (implementing classes)
	@Override
	public void doReconfiguration(final ReconfigurationPlan plan) throws ReconfigurationException {
		if (plan == null) {
			throw new IllegalArgumentException("plan is null");
		}

		if (plan.getOperations().size() == 0) {
			LOG.warn("Empty plan");
			return;
		}

		final List<ReconfigurationOperation> executedOperations =
				new ArrayList<ReconfigurationOperation>();

		for (final ReconfigurationOperation op : plan.getOperations()) {
			// Check of which type the Operation is and executing belonging
			// method
			if (op instanceof ComponentReplication) {
				final AssemblyComponent comp = ((ComponentReplication) op).getComponent();
				final ExecutionContainer destination = ((ComponentReplication) op).getDestination();
				final DeploymentComponent resDeploymentComponent = this.replicateComponent(comp, destination);
				// TODO: evaluate return value
			} else if (op instanceof ComponentDereplication) {
				final DeploymentComponent comp = ((ComponentDereplication) op).getComponent();
				final boolean success = this.dereplicateComponent(comp);
				// TODO: evaluate return value
			} else if (op instanceof ComponentMigration) {
				final DeploymentComponent comp = ((ComponentMigration) op).getComponent();
				final ExecutionContainer destination = ((ComponentMigration) op).getDestination();
				final DeploymentComponent resDeploymentComponent = this.migrateComponent(comp, destination);
				// TODO: evaluate return value
			} else if (op instanceof ContainerAllocation) {
				final ExecutionContainerType container =
						((ContainerAllocation) op).getContainerType();
				final ExecutionContainer resContainer =
						// TODO: extend meta-model such that nodeAllocation
						// operation requires an execution container name
						this.allocateExecutionContainer("FIXME:NoName", container);
				// TODO: evaluate return value
			} else if (op instanceof ContainerDeallocation) {
				final ExecutionContainer container = ((ContainerDeallocation) op).getContainer();
				final boolean success = this.concreteDeallocateExecutionContainer(container);
				// TODO: evaluate return value
			} else {
				// throw new IllegalReconfigurationOperationException();
				LOG.fatal("Illegal reconfiguration operation " + op.getClass().getName());
			}
			executedOperations.add(op);
		}
	}

	// TODO: remove or implement after we pulled the monitoring manager
	// interface up to the core package
	protected abstract DeploymentComponent createPreliminaryDeploymentComponentInModel(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer);

	// TODO: remove or implement after we pulled the monitoring manager
	// interface up to the core package
	protected abstract boolean deletePreliminaryDeploymentComponentFromModel(
			DeploymentComponent deploymentComponent);

	// TODO: remove or implement after we pulled the monitoring manager
	// interface up to the core package
	protected abstract ExecutionContainer createPreliminaryExecutionContainerInModel(
			String fullyQualifiedName,
			ExecutionContainerType executionContainerType);

	// TODO: remove or implement after we pulled the monitoring manager
	// interface up to the core package
	protected abstract boolean deletePreliminaryExecutionContainerFromModel(
			ExecutionContainer executionContainer);

	// TODO: remove or implement after we pulled the monitoring manager
	// interface up to the core package
	protected abstract boolean deleteExecutionContainerFromModel(
			ExecutionContainer executionContainer);

	// TODO: remove or implement after we pulled the monitoring manager
	// interface up to the core package
	protected abstract boolean deleteDeploymentComponentFromModel(
			DeploymentComponent deploymentComponent);

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
		DeploymentComponent resDeploymentComponent = this.createPreliminaryDeploymentComponentInModel(assemblyComponent, toExecutionContainer);

		final boolean success =
				this.concreteReplicateComponent(assemblyComponent, toExecutionContainer, resDeploymentComponent);

		if (!success) {
			this.deletePreliminaryDeploymentComponentFromModel(resDeploymentComponent);
			resDeploymentComponent = null;
			LOG.error("concreteReplicateComponent failed");
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
	 * @param deploymentComponent
	 * @return
	 */
	public boolean dereplicateComponent(
			final DeploymentComponent deploymentComponent) {
		final boolean success =
				this.concreteDereplicateComponent(deploymentComponent);

		LOG.info("Triggering removal of deployment component from model: "
				+ deploymentComponent);
		this.deleteDeploymentComponentFromModel(deploymentComponent);

		if (!success) {
			LOG.error("concreteDereplicateComponent failed");
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
		DeploymentComponent resDeploymentComponent = this.createPreliminaryDeploymentComponentInModel(deploymentComponent.getAssemblyComponent(), destination);

		boolean success = this.concreteMigrateComponent(deploymentComponent, destination, resDeploymentComponent);

		LOG.info("Triggering removal of deployment component from model: " + deploymentComponent);

		if (!this.deleteDeploymentComponentFromModel(deploymentComponent)) {
			LOG.error("Failed to deleteDeploymentComponentFromModel: " + deploymentComponent);
			success = false;
		}

		if (!success) {
			if (!this.deletePreliminaryDeploymentComponentFromModel(resDeploymentComponent)) {
				LOG.error("Failed to deletePreliminaryDeploymentComponentFromModel: " + resDeploymentComponent);
			}
			resDeploymentComponent = null;
			LOG.error("concreteMigrateComponent failed");
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
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		ExecutionContainer executionContainer = this.createPreliminaryExecutionContainerInModel(fullyQualifiedName, executionContainerType);

		final boolean success = this.concreteAllocateExecutionContainer(executionContainerType, executionContainer);

		if (!success) {
			if (!this.deletePreliminaryExecutionContainerFromModel(executionContainer)) {
				LOG.error("Failed to deletePreliminaryExecutionContainerFromModel: " + executionContainer);
			}
			executionContainer = null;
			LOG.error("concreteAllocateExecutionContainer failed");
		}

		// TODO: log event

		return executionContainer;
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
		boolean success = this.concreteDeallocateExecutionContainer(executionContainer);

		LOG.info("Triggering removal of execution container from model: " + executionContainer);

		if (!this.deleteExecutionContainerFromModel(executionContainer)) {
			LOG.error("Failed to deleteExecutionContainerFromModel: " + executionContainer);
			success = false;
		}

		if (!success) {
			LOG.error("concreteDeallocateExecutionContainer failed");
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
	protected abstract boolean concreteDeallocateExecutionContainer(final ExecutionContainer executionContainer);

}
