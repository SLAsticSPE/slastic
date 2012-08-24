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

package org.trustsoft.slastic.plugins.pcm.reconfiguration;

import java.util.concurrent.ArrayBlockingQueue;

import org.trustsoft.slastic.control.exceptions.IllegalReconfigurationOperationException;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.AllocationContextNotInModelException;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ModelManager;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ServerNotAllocatedException;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;

import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * This Class tries to replace the simulator with forwarding the ReconfigurationPlan back to the ModelManager.
 * 
 * @author Lena Stoever
 * 
 */
public class ReconfigurationPlanForwarder extends AbstractReconfigurationManagerComponent {

	// private static final Log LOG = LogFactory.getLog(ReconfigurationPlanForwarder.class);

	private final ArrayBlockingQueue<SLAsticReconfigurationPlan> reconfigurationPlans;
	private volatile boolean terminated = false;
	// Maximum number of plans that ca be hold
	private final static int maxPlans = 20;

	public ReconfigurationPlanForwarder() {
		this.reconfigurationPlans = new ArrayBlockingQueue<SLAsticReconfigurationPlan>(ReconfigurationPlanForwarder.maxPlans);
	}

	public void forwardPlans() {
		while ((this.reconfigurationPlans.size() != 0) && !this.terminated) {
			try {
				// forward reconfiguration plan to the Model Manager. "true" for storing the result
				((ModelManager) this.getControlComponent().getModelManager()).doReconfiguration(this.reconfigurationPlans.take(), true);
			} catch (final InterruptedException e) {
				e.printStackTrace();
			} catch (final AllocationContextNotInModelException e) {
				e.printStackTrace();
			} catch (final ServerNotAllocatedException e) {
				e.printStackTrace();
			} catch (final IllegalReconfigurationOperationException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public synchronized void doReconfiguration(final SLAsticReconfigurationPlan plan) {
		try {
			this.reconfigurationPlans.put(plan);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void terminate(final boolean error) {
		this.terminated = true;
	}

	@Override
	public boolean execute() {
		this.forwardPlans();
		return true;
	}

	@Override
	public boolean init() {
		return true;
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer,
			final DeploymentComponent resDeploymentComponent) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteDereplicateComponent(final DeploymentComponent deploymentContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteMigrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer destination,
			final DeploymentComponent resDeploymentComponent) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteAllocateExecutionContainer(
			final ExecutionContainerType executionContainerType,
			final ExecutionContainer resExecutionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteDeallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
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
