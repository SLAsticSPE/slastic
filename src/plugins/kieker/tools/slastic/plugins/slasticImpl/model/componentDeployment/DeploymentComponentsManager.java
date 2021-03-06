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

package kieker.tools.slastic.plugins.slasticImpl.model.componentDeployment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.plugins.slasticImpl.model.AbstractEntityManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentFactory;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public class DeploymentComponentsManager extends AbstractEntityManager<DeploymentComponent> implements IDeploymentComponentsManager {
	private static final Log LOG = LogFactory.getLog(DeploymentComponentsManager.class);

	// private static final ArrayList<DeploymentComponent> EMPTY_COLLECTION = new ArrayList<DeploymentComponent>();

	/**
	 * Map assembly component ID x deployments for assembly component
	 */
	// TODO: this cache leads to problems when starting with an existing input
	// slastic model
	// private final TreeMap<Long, TreeMap<Long, DeploymentComponent>>
	// deploymentsForAsmComponentIDs =
	// new TreeMap<Long, TreeMap<Long, DeploymentComponent>>();

	public DeploymentComponentsManager(final List<DeploymentComponent> deploymentComponents) {
		super(deploymentComponents);
	}

	@Override
	public DeploymentComponent lookupDeploymentComponent(final long id) {
		return this.lookupEntityById(id);
	}

	@Override
	public DeploymentComponent createAndRegisterDeploymentComponent(final AssemblyComponent assemblyComponent, final ExecutionContainer executionContainer) {
		final DeploymentComponent deploymentComponent = super.createAndRegisterEntity();
		deploymentComponent.setAssemblyComponent(assemblyComponent);
		deploymentComponent.setExecutionContainer(executionContainer);
		// this.addDeploymentComponentForAssemblyComponent(assemblyComponent,
		// deploymentComponent);
		return deploymentComponent;
	}

	@Override
	protected DeploymentComponent createEntity() {
		return ComponentDeploymentFactory.eINSTANCE.createDeploymentComponent();
	}

	/**
	 * TODO: Currently, we do not remove the {@link DeploymentComponent} but
	 * mark it inactive {@link DeploymentComponent#isActive()}.
	 */
	@Override
	public boolean deleteDeploymentComponent(final DeploymentComponent deploymentComponent) {
		if (!super.removeEntity(deploymentComponent)) { // throws IllegalStateException
			DeploymentComponentsManager.LOG.warn("removeEntity(..) returned false for deployment component "
					+ deploymentComponent + ". " + "This means that this component wasn't registered. ");
			return false;
		}
		// if
		// (!this.removeDeploymentComponentForAssemblyComponent(deploymentComponent.getAssemblyComponent(),
		// deploymentComponent)) {
		// DeploymentComponentsManager.log.error("Failed to remove deployment component from internal map: "
		// + deploymentComponent +
		// ". This means that the internal state is inconsistent now! ");
		// }
		return true;
	}

	@Override
	public DeploymentComponent migrateDeploymentComponent(final DeploymentComponent deploymentComponent,
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
	 * TODO: Currently, we do not remove the {@link DeploymentComponent} but
	 * mark it inactive {@link DeploymentComponent#isActive()}.
	 * 
	 * @param assemblyComponent
	 * @param oldDeploymentComponent
	 * @return
	 */
	// private boolean removeDeploymentComponentForAssemblyComponent(final
	// AssemblyComponent assemblyComponent,
	// final DeploymentComponent oldDeploymentComponent) {
	// final TreeMap<Long, DeploymentComponent> deploymentsForComponent =
	// this.deploymentsForAsmComponentIDs.get(assemblyComponent.getId());
	// if (deploymentsForComponent == null) {
	// DeploymentComponentsManager.log
	// .fatal("List of deployment for assembly component is null. Assembly component ID: "
	// + assemblyComponent.getId());
	// return false;
	// }
	//
	// // return deploymentsForComponent.remove(oldDeploymentComponent.getId())
	// // == oldDeploymentComponent;
	// return deploymentsForComponent.get(oldDeploymentComponent.getId()) ==
	// oldDeploymentComponent;
	// }

	@Override
	public Collection<DeploymentComponent> deploymentComponentsForAssemblyComponent(
			final AssemblyComponent assemblyComponent, final boolean includeInactive) {
		final Collection<DeploymentComponent> deploymentsForAssemblyComponent = new ArrayList<DeploymentComponent>();

		for (final DeploymentComponent deploymentComponent : super.getEntities()) {
			if (deploymentComponent.getAssemblyComponent() == assemblyComponent) {
				if (includeInactive || deploymentComponent.isActive()) {
					deploymentsForAssemblyComponent.add(deploymentComponent);
				}
			}
		}

		return deploymentsForAssemblyComponent;
	}

	@Override
	public DeploymentComponent deploymentComponentForAssemblyComponent(final AssemblyComponent assemblyComponent,
			final ExecutionContainer executionContainer) {
		final Collection<DeploymentComponent> deployments =
				this.deploymentComponentsForAssemblyComponent(assemblyComponent,
						/*
						 * do not include inactive ones
						 */false);
		for (final DeploymentComponent depl : deployments) {
			if (depl.getExecutionContainer() == executionContainer) {
				return depl;
			}
		}

		return null;
	}

	// /**
	// * Adds the given {@link DeploymentComponent} to the list of deployment
	// for
	// * the given {@link AssemblyComponent}.
	// *
	// * @param assemblyComponent
	// * @param newDeploymentComponent
	// */
	// private void addDeploymentComponentForAssemblyComponent(final
	// AssemblyComponent assemblyComponent,
	// final DeploymentComponent newDeploymentComponent) {
	// TreeMap<Long, DeploymentComponent> deploymentsForComponent =
	// this.deploymentsForAsmComponentIDs.get(assemblyComponent.getId());
	// if (deploymentsForComponent == null) {
	// /*
	// * First deployment for assembly component -- create new deployment
	// * set
	// */
	// deploymentsForComponent = new TreeMap<Long, DeploymentComponent>();
	// this.deploymentsForAsmComponentIDs.put(assemblyComponent.getId(),
	// deploymentsForComponent);
	// }
	// /* Add new deployment */
	// deploymentsForComponent.put(newDeploymentComponent.getId(),
	// newDeploymentComponent);
	// }
}
