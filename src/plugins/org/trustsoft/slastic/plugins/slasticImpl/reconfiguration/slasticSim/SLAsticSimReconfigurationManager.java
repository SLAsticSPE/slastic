package org.trustsoft.slastic.plugins.slasticImpl.reconfiguration.slasticSim;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipe;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipeBroker;
import org.trustsoft.slastic.plugins.starter.reconfigurationPipe.ReconfigurationPipeException;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;
import org.trustsoft.slastic.simulation.listeners.ReconfEventListener;

import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import ReconfigurationPlanModel.SLAsticReconfigurationPlan;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 */
public class SLAsticSimReconfigurationManager extends
		AbstractReconfigurationManagerComponent implements ReconfEventListener {

	private ReconfigurationPipe reconfigurationPipe;
	private static final String PROPERTY_PIPE_NAME = "pipeName";
	private String pipeName;
	private static final Log log = LogFactory
			.getLog(SLAsticSimReconfigurationManager.class);

	@Override
	public boolean init() {
		this.pipeName =
				super.getInitProperty(SLAsticSimReconfigurationManager.PROPERTY_PIPE_NAME);
		if ((this.pipeName == null) || (this.pipeName.length() == 0)) {
			SLAsticSimReconfigurationManager.log
					.error("Invalid or missing pipeName value for property '"
							+ SLAsticSimReconfigurationManager.PROPERTY_PIPE_NAME
							+ "'");
			throw new IllegalArgumentException(
					"Invalid or missing pipeName value:" + this.pipeName);
		}
		this.reconfigurationPipe =
				ReconfigurationPipeBroker.getInstance().acquirePipe(
						this.pipeName);
		if (this.reconfigurationPipe == null) {
			SLAsticSimReconfigurationManager.log
					.error("Failed to get pipe with name:" + this.pipeName);
			throw new IllegalArgumentException("Failed to get pipe with name:"
					+ this.pipeName);
		}
		SLAsticSimReconfigurationManager.log.info("Connected to pipe '"
				+ this.pipeName + "'" + " (" + this.reconfigurationPipe + ")");
		return true;
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {
	}

	@Override
	public void doReconfiguration(final SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		try {
			if (plan.getOperations().size() == 0) {
				throw new ReconfigurationException("Plan contains 0 operations");
			} else {

				SLAsticSimReconfigurationManager.log
						.debug("Requesting plan with "
								+ plan.getOperations().size() + " operations");
			}
			this.reconfigurationPipe.reconfigure(plan, this);
		} catch (final ReconfigurationPipeException ex) {
			SLAsticSimReconfigurationManager.log.error(
					"reconfiguration failed", ex);
			throw new ReconfigurationException("reconfiguration failed", ex);
		}
	}

	@Override
	public void notifyPlanDone(final SLAsticReconfigurationPlan plan) {
		if (plan == null) {
			SLAsticSimReconfigurationManager.log.fatal("Returned plan is null");
			return;
		}

		try {
			SLAsticSimReconfigurationManager.log
					.info("notifyPlanDone received; plan: " + plan);
			this.getControlComponent().getModelManager()
					.doReconfiguration(plan);
		} catch (final ReconfigurationException ex) {
			SLAsticSimReconfigurationManager.log.error(
					"Failed to reflect reconfiguration in runtime model", ex);
		}
	}

	@Override
	public void notifyOpFailed(final SLAsticReconfigurationPlan plan,
			final SLAsticReconfigurationOpType reconfOp) {
		if (plan == null) {
			SLAsticSimReconfigurationManager.log.fatal("Returned plan is null");
			return;
		}

		SLAsticSimReconfigurationManager.log
				.warn("notifyOpFailed received; plan: " + plan);
	}

	@Override
	public void notifyPlanFailed(final SLAsticReconfigurationPlan plan) {
		if (plan == null) {
			SLAsticSimReconfigurationManager.log.fatal("Returned plan is null");
			return;
		}

		SLAsticSimReconfigurationManager.log
				.warn("notifyOpFailed received; plan: " + plan);
	}

	@Override
	public void doReconfiguration(final ReconfigurationPlan plan)
			throws ReconfigurationException {
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
	protected DeploymentComponent createPreliminaryDeploymentComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void deletePreliminaryDeploymentComponent(
			final DeploymentComponent deploymentComponent) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected ExecutionContainer createPreliminaryExecutionContainer(
			final String fullyQualifiedName,
			final ExecutionContainerType executionContainerType) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void deletePreliminaryExecutionContainer(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}
}
