package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import java.util.Collection;
import java.util.TreeMap;

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

	/**
	 * Map assembly component ID x deployments for assembly component
	 */
	private final TreeMap<Long, TreeMap<Long, DeploymentComponent>> deploymentsForAsmComponentIDs = new TreeMap<Long, TreeMap<Long, DeploymentComponent>>();

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

	/**
	 * Returns a list of all registered {@link DeploymentComponent}s associated
	 * with the passed {@link AssemblyComponent} or null if the
	 * {@link AssemblyComponent} is not registered.
	 * 
	 * @param assemblyComponent
	 * @return
	 */
	public Collection<DeploymentComponent> deploymentComponentsForAssemblyComponents(
			final AssemblyComponent assemblyComponent) {
		return this.deploymentsForAsmComponentIDs
				.get(assemblyComponent.getId()).values();
	}

	/**
	 * Returns the {@link DeploymentComponent} associated with the given
	 * {@link AssemblyComponent} and {@link ExecutionContainer}, or null if no
	 * such {@link DeploymentComponent} exists.
	 * 
	 * @param assemblyComponent
	 * @param executionContainer
	 * @return
	 */
	public DeploymentComponent deploymentComponentForAssemblyComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final Collection<DeploymentComponent> deployments = this
				.deploymentComponentsForAssemblyComponents(assemblyComponent);
		for (final DeploymentComponent depl : deployments) {
			if (depl.getExecutionContainer() == executionContainer) {
				return depl;
			}
		}

		return null;
	}

	/**
	 * Created and registers a new {@link DeploymentComponent} associated with
	 * the given {@link AssemblyComponent} and {@link ExecutionContainer}.
	 */
	@Override
	public DeploymentComponent createAndRegisterDeploymentComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final DeploymentComponent newDeploymentComponent = this.deploymentComponentsManager
				.createAndRegisterDeploymentComponent(assemblyComponent,
						executionContainer);
		this.addDeploymentComponentForAssemblyComponent(assemblyComponent,
				newDeploymentComponent);
		return newDeploymentComponent;
	}

	/**
	 * Adds the given {@link DeploymentComponent} to the list of deployment for
	 * the given {@link AssemblyComponent}.
	 * 
	 * @param assemblyComponent
	 * @param newDeploymentComponent
	 */
	private void addDeploymentComponentForAssemblyComponent(
			final AssemblyComponent assemblyComponent,
			final DeploymentComponent newDeploymentComponent) {
		TreeMap<Long, DeploymentComponent> deploymentsForComponent = this.deploymentsForAsmComponentIDs
				.get(assemblyComponent.getId());
		if (deploymentsForComponent == null) {
			/*
			 * First deployment for assembly component -- create new deployment
			 * set
			 */
			deploymentsForComponent = new TreeMap<Long, DeploymentComponent>();
			this.deploymentsForAsmComponentIDs.put(assemblyComponent.getId(),
					deploymentsForComponent);
		}
		/* Add new deployment */
		deploymentsForComponent.put(newDeploymentComponent.getId(),
				newDeploymentComponent);
	}

	@Override
	public boolean deleteDeploymentComponent(
			final DeploymentComponent deploymentComponent) {

		/* Remove deployment component from both internal tables */
		if (!(this
				.removeDeploymentComponentForAssemblyComponent(
						deploymentComponent.getAssemblyComponent(),
						deploymentComponent))
				|| !(this.deploymentComponentsManager
						.deleteDeploymentComponent(deploymentComponent))) {
			ComponentDeploymentModelManager.log
					.error("Failed to remove deployment component from internal maps");
			return false;
		}

		return true;
	}

	/**
	 * Removes the given {@link DeploymentComponent} from the list of
	 * deployments for the given {@link AssemblyComponent}.
	 * 
	 * @param assemblyComponent
	 * @param oldDeploymentComponent
	 * @return
	 */
	private boolean removeDeploymentComponentForAssemblyComponent(
			final AssemblyComponent assemblyComponent,
			final DeploymentComponent oldDeploymentComponent) {
		final TreeMap<Long, DeploymentComponent> deploymentsForComponent = this.deploymentsForAsmComponentIDs
				.get(assemblyComponent.getId());
		if (deploymentsForComponent == null) {
			ComponentDeploymentModelManager.log
					.fatal("List of deployment for assembly component is null. Assembly compoenent ID: "
							+ assemblyComponent.getId());
			return false;
		}

		return deploymentsForComponent.remove(oldDeploymentComponent.getId()) == oldDeploymentComponent;
	}

	/**
	 * Sets the {@link DeploymentComponent}'s execution container field to the
	 * given {@link ExecutionContainer}.
	 */
	@Override
	public void migrateDeploymentComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer) {
		// in the current implementation there's no need to modify the map
		// deploymentsForAsmComponentIDs
		this.deploymentComponentsManager.migrateDeploymentComponent(
				deploymentComponent, toExecutionContainer);
	}
}
