package org.trustsoft.slastic.plugins.slasticImpl.model.componentDeployment;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractEntityManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public class DeploymentComponentsManager extends
		AbstractEntityManager<DeploymentComponent> implements
		IDeploymentComponentsManager {
	private static final Log log = LogFactory
			.getLog(DeploymentComponentsManager.class);

	/**
	 * Map assembly component ID x deployments for assembly component
	 */
	private final TreeMap<Long, TreeMap<Long, DeploymentComponent>> deploymentsForAsmComponentIDs = new TreeMap<Long, TreeMap<Long, DeploymentComponent>>();
	
	public DeploymentComponentsManager(
			final List<DeploymentComponent> DeploymentComponents) {
		super(DeploymentComponents);
	}

	@Override
	public DeploymentComponent lookupDeploymentComponent(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public DeploymentComponent createAndRegisterDeploymentComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final DeploymentComponent deploymentComponent = this
				.createAndRegisterEntity();
		deploymentComponent.setAssemblyComponent(assemblyComponent);
		deploymentComponent.setExecutionContainer(executionContainer);
		this.addDeploymentComponentForAssemblyComponent(assemblyComponent,
				deploymentComponent);
		return deploymentComponent;
	}

	@Override
	protected DeploymentComponent createEntity() {
		return ComponentDeploymentFactory.eINSTANCE.createDeploymentComponent();
	}

	@Override
	public boolean deleteDeploymentComponent(
			final DeploymentComponent deploymentComponent) {
		if (!this.removeEntity(deploymentComponent)) { // throws
														// IllegalStateException
			DeploymentComponentsManager.log
					.warn("removeEntity(..) returned false for deployment component "
							+ deploymentComponent
							+ ". "
							+ "This means that this component wasn't registered. ");
			return false;
		}
		if (!this
				.removeDeploymentComponentForAssemblyComponent(
						deploymentComponent.getAssemblyComponent(),
						deploymentComponent)) {
			DeploymentComponentsManager.log.error("Failed to remove deployment component from internal map: "
					+ deploymentComponent
					+ ". This means that the internal state is inconsistent now! ");
		}
		return true;
	}

	@Override
	public DeploymentComponent migrateDeploymentComponent(
			final DeploymentComponent deploymentComponent,
			final ExecutionContainer toExecutionContainer) {
		final DeploymentComponent newDeploymentComponent = 
			this.createAndRegisterDeploymentComponent(deploymentComponent.getAssemblyComponent(), toExecutionContainer);
		this.deleteDeploymentComponent(deploymentComponent);
		return newDeploymentComponent;
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
			DeploymentComponentsManager.log
					.fatal("List of deployment for assembly component is null. Assembly compoenent ID: "
							+ assemblyComponent.getId());
			return false;
		}

		return deploymentsForComponent.remove(oldDeploymentComponent.getId()) == oldDeploymentComponent;
	}

	@Override
	public Collection<DeploymentComponent> deploymentComponentsForAssemblyComponent(
			final AssemblyComponent assemblyComponent) {
		return this.deploymentsForAsmComponentIDs
				.get(assemblyComponent.getId()).values();
	}

	@Override
	public DeploymentComponent deploymentComponentForAssemblyComponent(
			final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final Collection<DeploymentComponent> deployments = this
				.deploymentComponentsForAssemblyComponent(assemblyComponent);
		for (final DeploymentComponent depl : deployments) {
			if (depl.getExecutionContainer() == executionContainer) {
				return depl;
			}
		}

		return null;
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
}
