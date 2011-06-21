package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration.EucalyptusReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * Executes reconfiguration requests received via its {@link #reconfigure(int)}
 * manager employing the {@link EucalyptusReconfigurationManager}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class ConfigurationManager {

	private static final Log log = LogFactory.getLog(ConfigurationManager.class);

	/**
	 * The number of nodes allocated in the current configuration.
	 */
	// 1 is a HACK to allow to manage running instances
	// TODO: - this should be renamed to currentNumDeploymentComponents are
	// alike
	// (should then become a HashMap assembly component x int)
	// - better: look up from the model manager
	// private volatile int currentNumNodes = 1;

	private volatile int nextExecutionContainerIndex = 1;

	private final ModelManager modelManager;
	private final EucalyptusReconfigurationManager reconfigurationManager;

	/**
	 * 
	 * @param modelManager
	 * @param reconfigurationManager
	 */
	public ConfigurationManager(final ModelManager modelManager,
			final EucalyptusReconfigurationManager reconfigurationManager) {
		this.modelManager = modelManager;
		this.reconfigurationManager = reconfigurationManager;
	}

	/**
	 * 
	 * @param requestedNumNodes
	 * @return
	 */
	public final synchronized boolean reconfigure(final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType,
			final int requestedNumNodes) {
		final int currentNumNodes = this.activeDeplsForAssemblyComponent(assemblyComponent).size();

		ConfigurationManager.log.info("Changing configuration from " + currentNumNodes + " to "
				+ requestedNumNodes + " instances of assembly component " + assemblyComponent);

		final boolean success;

		if (requestedNumNodes > currentNumNodes) {
			success =
					this.increaseCapacity(assemblyComponent, executionContainerType, requestedNumNodes
							- currentNumNodes);
		} else if (requestedNumNodes < currentNumNodes) {
			success = this.shrinkCapacity(assemblyComponent, currentNumNodes - requestedNumNodes);
		} else {
			/* == */
			success = true;
		}

		//TODO: remove if proven stable: currentNumNodes = requestedNumNodes;

		return success;
	}

	/**
	 * @return the currentNumNodes
	 */
	private final Collection<DeploymentComponent> activeDeplsForAssemblyComponent(
			final AssemblyComponent assemblyComponent) {
		/*
		 * Fetch all deployments for the assembly component (including inactive
		 * ones)
		 */
		final Collection<DeploymentComponent> deplsForAssemblyComponent =
				this.modelManager.getComponentDeploymentModelManager().deploymentComponentsForAssemblyComponent(
						assemblyComponent);

		ConfigurationManager.log.info("Found " + deplsForAssemblyComponent.size()
				+ " deployment components (including inactive)");

		/* Select the active deployment components */
		final Collection<DeploymentComponent> activeDeplsForAssemblyComponents = new ArrayList<DeploymentComponent>();
		for (final DeploymentComponent deplComp : deplsForAssemblyComponent) {
			if (deplComp.isActive()) {
				activeDeplsForAssemblyComponents.add(deplComp);
			}
		}
		return activeDeplsForAssemblyComponents;
	}

	/**
	 * 
	 * @return
	 */
	private Collection<DeploymentComponent> selectActiveDeploymentComponents(final AssemblyComponent assemblyComponent,
			final int numComponents) {
		final Collection<DeploymentComponent> activeDeplsForAssemblyComponent =
				this.activeDeplsForAssemblyComponent(assemblyComponent);

		ConfigurationManager.log.info("Found " + activeDeplsForAssemblyComponent.size()
				+ " ACTIVE deployment components");

		if (activeDeplsForAssemblyComponent.size() < numComponents) {
			ConfigurationManager.log.error("Requested to select " + numComponents + " components but only "
					+ activeDeplsForAssemblyComponent.size() + " active one exist");
			return null;
		}

		final Iterator<DeploymentComponent> it = activeDeplsForAssemblyComponent.iterator();
		final Collection<DeploymentComponent> result = new ArrayList<DeploymentComponent>();

		// TODO: This used to be a HACK (remove comment as soon as proven to
		// work): -> it.next();
		for (int curIdx = 0; curIdx < numComponents; curIdx++) {
			result.add(it.next());
		}

		return result;
	}

	private boolean shrinkCapacity(final AssemblyComponent assemblyComponent, final int shrinkBy) {
		// TODO: conceptually, this would involve the creation of a
		// reconfiguration plan. Then, the reconfiguration
		// manager would take care of the transactional change
		// execution.

		int i_thRequest = 1;
		boolean success = true;

		final Collection<DeploymentComponent> shrinkList =
				this.selectActiveDeploymentComponents(assemblyComponent, shrinkBy);
		ConfigurationManager.log.info("shrinkList: " + shrinkList);
		for (final DeploymentComponent deplComponent : shrinkList) {
			ConfigurationManager.log.info("Processing " + deplComponent);
			/* Un-deploy und de-allocate */
			success = this.reconfigurationManager.dereplicateComponent(deplComponent);
			if (!success) {
				ConfigurationManager.log.error("shrink capacity (" + (i_thRequest) + "/" + shrinkBy + "):"
						+ "Failed to de-replicate component '" + deplComponent
						+ " -> Aborting reconfiguration");
				break;
			}

			final ExecutionContainer executionContainer = deplComponent.getExecutionContainer();
			success = this.reconfigurationManager.deallocateExecutionContainer(executionContainer);
			if (!success) {
				ConfigurationManager.log.error("shrink capacity (" + (i_thRequest) + "/" + shrinkBy + "):"
						+ "Failed to de-allocate execution container " + executionContainer
						+ " -> Aborting reconfiguration");
				break;
			}

			i_thRequest++;
		}
		return success;
	}

	private boolean increaseCapacity(final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType, final int increaseBy) {
		// TODO: conceptually, this would involve the creation of a
		// reconfiguration plan. Then, the reconfiguration
		// manager would take care of the transactional change
		// execution.

		boolean success = true;

		for (int i = 0; (i < increaseBy) && success; i++) {
			final String containerName = this.createExecutionContainerName(executionContainerType);
			final ExecutionContainer container =
					this.reconfigurationManager.allocateExecutionContainer(containerName, executionContainerType);
			if (container != null) {
				if (this.reconfigurationManager.replicateComponent(assemblyComponent, container) == null) {
					ConfigurationManager.log.error("increase capacity (" + (i + 1) + "/" + increaseBy + "):"
							+ "Failed to replicate component '" + assemblyComponent + "' to container " + container
							+ " -> Aborting reconfiguration");
					success = false;
					break;
				}
			} else {
				ConfigurationManager.log.error("increase capacity (" + (i + 1) + "/" + increaseBy + "):"
						+ "Failed to allocate execution container with name '" + containerName + "' and type '"
						+ executionContainerType + "' -> Aborting reconfiguration");
				success = false;
				break;
			}
		}

		return success;
	}

	private final String createExecutionContainerName(final ExecutionContainerType executionContainerType) {
		return executionContainerType + "-" + this.nextExecutionContainerIndex++;
	}
}