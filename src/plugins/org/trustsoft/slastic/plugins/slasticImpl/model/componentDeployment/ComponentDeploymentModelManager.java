package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public class ComponentDeploymentModelManager extends
		AbstractModelManager<ComponentDeploymentModel> implements
		IDeploymentComponentsManager {

	private static final Log log = LogFactory
			.getLog(ComponentDeploymentModelManager.class);

	private final DeploymentComponentsManager deploymentComponentsManager;

	private ComponentDeploymentModelManager() {
		super(null);
		this.deploymentComponentsManager = null;
	}

	public ComponentDeploymentModelManager(
			final ComponentDeploymentModel ComponentDeploymentModel) {
		super(ComponentDeploymentModel);
		this.deploymentComponentsManager = new DeploymentComponentsManager(
				ComponentDeploymentModel.getDeploymentComponents());
	}

	@Override
	public DeploymentComponent lookupDeploymentComponent(final long id) {
		return this.deploymentComponentsManager.lookupDeploymentComponent(id);
	}

	@Override
	public DeploymentComponent createAndRegisterDeploymentComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final DeploymentComponent newDeploymentComponent = this.deploymentComponentsManager
				.createAndRegisterDeploymentComponent(assemblyComponent,
						executionContainer);
		return newDeploymentComponent;
	}

	@Override
	public boolean deleteDeploymentComponent(
			final DeploymentComponent deploymentComponent) {
		return this.deploymentComponentsManager
				.deleteDeploymentComponent(deploymentComponent);
	}

	@Override
	public DeploymentComponent migrateDeploymentComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer) {
		// in the current implementation there's no need to modify the map
		// deploymentsForAsmComponentIDs
		return this.deploymentComponentsManager.migrateDeploymentComponent(
				deploymentComponent, toExecutionContainer);
	}

	@Override
	public DeploymentComponent deploymentComponentForAssemblyComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		return this.deploymentComponentsManager
				.deploymentComponentForAssemblyComponent(assemblyComponent,
						executionContainer);
	}

	@Override
	public Collection<DeploymentComponent> deploymentComponentsForAssemblyComponent(
			final AssemblyComponent assemblyComponent, final boolean includeInactive) {
		return this.deploymentComponentsManager
				.deploymentComponentsForAssemblyComponent(assemblyComponent, includeInactive);
	}
}
