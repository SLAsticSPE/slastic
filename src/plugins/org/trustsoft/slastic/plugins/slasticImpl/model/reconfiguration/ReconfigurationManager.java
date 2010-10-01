package org.trustsoft.slastic.plugins.slasticImpl.model.reconfiguration;

import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment.ComponentDeploymentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

public class ReconfigurationManager implements IReconfigurationManager {
	/* Managers for the submodels */
	private final TypeRepositoryModelManager typeRepositoryManager;
	private final ComponentAssemblyModelManager componentAssemblyModelManager;
	private final ExecutionEnvironmentModelManager executionEnvironmentModelManager;
	private final ComponentDeploymentModelManager componentDeploymentModelManager;

	/**
	 * Objects must not be created using this constructor.
	 */
	@SuppressWarnings("unused")
	private ReconfigurationManager() {
		this(null, null, null, null);
	}

	public ReconfigurationManager(
			final TypeRepositoryModelManager typeRepositoryManager,
			final ComponentAssemblyModelManager componentAssemblyModelManager,
			final ExecutionEnvironmentModelManager executionEnvironmentModelManager,
			final ComponentDeploymentModelManager componentDeploymentModelManager) {
		this.typeRepositoryManager = typeRepositoryManager;
		this.componentAssemblyModelManager = componentAssemblyModelManager;
		this.executionEnvironmentModelManager = executionEnvironmentModelManager;
		this.componentDeploymentModelManager = componentDeploymentModelManager;
	}
	

	@Override
	public DeploymentComponent replicateComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer toExecutionContainer) {
		return this.componentDeploymentModelManager
				.createAndRegisterDeploymentComponent(assemblyComponent,
						toExecutionContainer);
	}


	@Override
	public void dereplicateComponent(
			final DeploymentComponent deploymentContainer) {
		this.componentDeploymentModelManager
				.deleteDeploymentComponent(deploymentContainer);
	}

	@Override
	public DeploymentComponent migrateComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer) {
		return this.componentDeploymentModelManager.migrateDeploymentComponent(
				deploymentComponent, toExecutionContainer);
	}


	@Override
	public boolean allocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		return this.executionEnvironmentModelManager
				.allocateExecutionContainer(executionContainer);
	}

	
	@Override
	public boolean deallocateExecutionContainer(
			final ExecutionContainer executionContainer) {
		return this.executionEnvironmentModelManager
				.deallocateExecutionContainer(executionContainer);
	}
}
