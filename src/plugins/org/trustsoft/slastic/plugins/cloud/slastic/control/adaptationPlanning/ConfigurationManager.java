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
	private volatile int currentNumNodes = 1;

	// TODO: refine
	private static final String EXECUTION_CONTAINER_NAME_PREFIX = "appsrv-";
	private volatile int nextExecutionContainerIndex = 1;

	private final ModelManager modelManager;
	private final EucalyptusReconfigurationManager reconfigurationManager;

	// TODO: remove; should be passed to the reconfigure method.
	private volatile AssemblyComponent assemblyComponent;
	private volatile ExecutionContainerType executionContainerType;

	/**
	 * TODO: Remove getter
	 * 
	 * @return the modelManager
	 */
	public final ModelManager getModelManager() {
		return this.modelManager;
	}

	/**
	 * TODO: remove setter
	 * 
	 * @param assemblyComponent
	 *            the assemblyComponent to set
	 */
	public final void setAssemblyComponent(final AssemblyComponent assemblyComponent) {
		this.assemblyComponent = assemblyComponent;
	}

	/**
	 * TODO: remove setter
	 * 
	 * @param executionContainerType
	 *            the executionContainerType to set
	 */
	public final void setExecutionContainerType(final ExecutionContainerType executionContainerType) {
		this.executionContainerType = executionContainerType;
	}

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
	 * @return the currentNumNodes
	 */
	public final int getCurrentNumNodes() {
		return this.currentNumNodes;
	}

	/**
	 * 
	 * @param requestedNumNodes
	 * @return
	 */
	public final synchronized boolean reconfigure(final AssemblyComponent asmComponent, final ExecutionContainerType executionContainerType,
			final int requestedNumNodes) {
		ConfigurationManager.log.info("Changing configuration from " + this.currentNumNodes + " to "
				+ requestedNumNodes);

		final boolean success;

		if (requestedNumNodes > this.currentNumNodes) {
			success =
					this.increaseCapacity(this.assemblyComponent, this.executionContainerType, requestedNumNodes
							- this.currentNumNodes);
		} else if (requestedNumNodes < this.currentNumNodes) {
			success = this.shrinkCapacity(asmComponent, this.currentNumNodes - requestedNumNodes);
		} else {
			/* == */
			success = true;
		}

		this.currentNumNodes = requestedNumNodes;

		return success;
	}

	/**
	 * 
	 * @return
	 */
	private Collection<DeploymentComponent> selectActiveDeploymentComponents(final AssemblyComponent assemblyComponent,
			final int numComponents) {
		final Collection<DeploymentComponent> result = new ArrayList<DeploymentComponent>();

		/*
		 * Fetch all deployments for the assembly component (including inactive
		 * ones)
		 */
		final Collection<DeploymentComponent> deplsForAssemblyComponents =
				this.modelManager.getComponentDeploymentModelManager().deploymentComponentsForAssemblyComponent(
						assemblyComponent);

		ConfigurationManager.log.info("Found " + deplsForAssemblyComponents.size()
				+ " deployment components (including inactive)");

		/* Select the active deployment components */
		final Collection<DeploymentComponent> activeDeplsForAssemblyComponents = new ArrayList<DeploymentComponent>();
		for (final DeploymentComponent deplComp : deplsForAssemblyComponents) {
			if (deplComp.isActive()) {
				activeDeplsForAssemblyComponents.add(deplComp);
			}
		}

		ConfigurationManager.log.info("Found " + activeDeplsForAssemblyComponents.size()
				+ " ACTIVE deployment components");

		if (activeDeplsForAssemblyComponents.size() < numComponents) {
			ConfigurationManager.log.error("Requested to select " + numComponents + " components but only "
					+ activeDeplsForAssemblyComponents.size() + " active one exist");
			return null;
		}

		final Iterator<DeploymentComponent> it = activeDeplsForAssemblyComponents.iterator();
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
			final String containerName = this.createExecutionContainerName();
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

	// TODO: refine
	private final String createExecutionContainerName() {
		return ConfigurationManager.EXECUTION_CONTAINER_NAME_PREFIX + this.nextExecutionContainerIndex++;
	}
}