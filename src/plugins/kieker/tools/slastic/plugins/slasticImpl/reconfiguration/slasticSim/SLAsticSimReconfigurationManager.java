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

package kieker.tools.slastic.plugins.slasticImpl.reconfiguration.slasticSim;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipe;
import kieker.tools.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipeBroker;
import kieker.tools.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipeException;
import kieker.tools.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import kieker.tools.slastic.reconfiguration.ReconfigurationException;
import kieker.tools.slastic.simulation.listeners.IReconfigurationEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 */
public class SLAsticSimReconfigurationManager extends AbstractReconfigurationManagerComponent implements IReconfigurationEventListener {
	private static final Log LOG = LogFactory.getLog(SLAsticSimReconfigurationManager.class);

	private ReconfigurationPipe reconfigurationPipe;
	private static final String PROPERTY_PIPE_NAME = "pipeName";
	private String pipeName;

	@Override
	public boolean init() {
		this.pipeName = super.getInitProperty(PROPERTY_PIPE_NAME);
		if ((this.pipeName == null) || (this.pipeName.length() == 0)) {
			LOG.error("Invalid or missing pipeName value for property '" + PROPERTY_PIPE_NAME + "'");
			throw new IllegalArgumentException("Invalid or missing pipeName value:" + this.pipeName);
		}
		this.reconfigurationPipe = ReconfigurationPipeBroker.getInstance().acquirePipe(this.pipeName);
		if (this.reconfigurationPipe == null) {
			LOG.error("Failed to get pipe with name:" + this.pipeName);
			throw new IllegalArgumentException("Failed to get pipe with name:" + this.pipeName);
		}
		LOG.info("Connected to pipe '" + this.pipeName + "'" + " (" + this.reconfigurationPipe + ")");
		return true;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {}

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		try {
			if (plan.getOperations().size() == 0) {
				throw new ReconfigurationException("Plan contains 0 operations");
			} else {
				LOG.debug("Requesting plan with " + plan.getOperations().size() + " operations");
			}
			this.reconfigurationPipe.reconfigure(plan, this);
		} catch (final ReconfigurationPipeException ex) {
			LOG.error("reconfiguration failed", ex);
			throw new ReconfigurationException("reconfiguration failed", ex);
		}
	}

	@Override
	public void notifyPlanDone(final SLAsticReconfigurationPlan plan) {
		if (plan == null) {
			LOG.fatal("Returned plan is null");
			return;
		}

		try {
			LOG.info("notifyPlanDone received; plan: " + plan);
			this.getControlComponent().getModelManager().doReconfiguration(plan);
		} catch (final ReconfigurationException ex) {
			LOG.error("Failed to reflect reconfiguration in runtime model", ex);
		}
	}

	@Override
	public void notifyOpFailed(final SLAsticReconfigurationPlan plan,
			final SLAsticReconfigurationOpType reconfOp) {
		if (plan == null) {
			LOG.fatal("Returned plan is null");
			return;
		}

		LOG.warn("notifyOpFailed received; plan: " + plan);
	}

	@Override
	public void notifyPlanFailed(final SLAsticReconfigurationPlan plan) {
		if (plan == null) {
			LOG.fatal("Returned plan is null");
			return;
		}

		LOG.warn("notifyOpFailed received; plan: " + plan);
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan) throws ReconfigurationException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected boolean concreteReplicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer,
			final DeploymentComponent resDeploymentComponent) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean concreteDereplicateComponent(
			final DeploymentComponent deploymentContainer) {
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
	protected boolean deletePreliminaryExecutionContainerFromModel(final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deleteExecutionContainerFromModel(final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deleteDeploymentComponentFromModel(final DeploymentComponent deploymentComponent) {
		throw new UnsupportedOperationException();
	}
}
