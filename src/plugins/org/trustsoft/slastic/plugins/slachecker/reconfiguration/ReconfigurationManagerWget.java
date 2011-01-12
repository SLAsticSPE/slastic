package org.trustsoft.slastic.plugins.slachecker.reconfiguration;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.emf.common.util.EList;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;
import org.trustsoft.slastic.reconfiguration.ReconfigurationException;
import org.trustsoft.slastic.reconfiguration.ShellExecutor;

import ReconfigurationPlanModel.ComponentRedeploymentOP;
import ReconfigurationPlanModel.SLAsticReconfigurationOpType;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 */
public class ReconfigurationManagerWget extends
		AbstractReconfigurationManagerComponent {

	private final Log log = LogFactory.getLog(ReconfigurationManagerWget.class);

	@Override
	public synchronized void doReconfiguration(
			final ReconfigurationPlanModel.SLAsticReconfigurationPlan plan)
			throws ReconfigurationException {
		final EList<SLAsticReconfigurationOpType> operations =
				plan.getOperations();
		for (final SLAsticReconfigurationOpType op : operations) {
			// Check of which type the Operation is
			// if (op instanceof ComponentDeReplicationOPImpl) {
			// throw new UnsupportedOperationException();
			// } else if (op instanceof ComponentMigrationOPImpl) {
			// throw new UnsupportedOperationException();
			// } else if (op instanceof ComponentReplicationOPImpl) {
			// throw new UnsupportedOperationException();
			// } else if (op instanceof NodeAllocationOPImpl) {
			// throw new UnsupportedOperationException();
			// } else if (op instanceof NodeDeAllocationOPImpl) {
			// throw new UnsupportedOperationException();
			// } else {
			if (op instanceof ComponentRedeploymentOP) {
				this.log.info("Initiating Redeployment");
				final ArrayList<String> argList = new ArrayList<String>();
				argList.add("-c");
				if (System.getProperty("os.name").contains("Mac")) {
					argList.add("/usr/local/bin/wget 'http://127.0.0.1:8080/catalogComplexityManagerServlet/index?action=setComplexity&complexity=200'");
				} else {
					argList.add("wget 'http://127.0.0.1:8080/catalogComplexityManagerServlet/index?action=setComplexity&complexity=200'");
				}
				ShellExecutor.invoke("/bin/bash", /* command */
				argList, /* arg list */
				true);
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	@Override
	public boolean execute() {
		return true;
	}

	@Override
	public void terminate(final boolean error) {
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
	protected void deletePreliminaryDeploymentComponentFromModel(
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
	protected void deletePreliminaryExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	protected void deleteExecutionContainerFromModel(
			final ExecutionContainer executionContainer) {
		throw new UnsupportedOperationException();	
	}

	@Override
	protected void deleteDeploymentComponentFromModel(
			final DeploymentComponent deploymentComponent) {
		throw new UnsupportedOperationException();			
	}
}
