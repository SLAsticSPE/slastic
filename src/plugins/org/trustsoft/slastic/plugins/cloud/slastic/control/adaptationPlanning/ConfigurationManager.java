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

	private static final Log log = LogFactory
			.getLog(ConfigurationManager.class);

	/**
	 * The number of nodes allocated in the current configuration.
	 */
	// 1 is a HACK to allow to manage running instances
	// TODO: - this should be renamed to currentNumDeploymentComponents are alike
	//         (should then become a HashMap assembly component x int)
	//       - better: look up from the model manager
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
	public final void setAssemblyComponent(
			final AssemblyComponent assemblyComponent) {
		this.assemblyComponent = assemblyComponent;
	}

	/**
	 * TODO: remove setter
	 * 
	 * @param executionContainerType
	 *            the executionContainerType to set
	 */
	public final void setExecutionContainerType(
			final ExecutionContainerType executionContainerType) {
		this.executionContainerType = executionContainerType;
	}

	/**
	 * 
	 * @param modelManager
	 * @param assemblyComponent
	 * @param executionContainerType
	 * @param reconfigurationManager
	 */
	// TODO: Remove AssemblyComponent, ExecutionContainerType
	public ConfigurationManager(final ModelManager modelManager,
			final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType,
			final EucalyptusReconfigurationManager reconfigurationManager) {
		this.modelManager = modelManager;
		this.assemblyComponent = assemblyComponent;
		this.executionContainerType = executionContainerType;
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
	 */
	// TODO: pass AssemblyComponent (+ ExecutionContainerType)
	// TODO: return the return value of increase/shrink
	// TODO: set synchronized in order to emphasize that only one 
	//       reconfiguration is executed a time
	public final void reconfigure(final int requestedNumNodes) {
		ConfigurationManager.log.info("Changing configuration from "
				+ this.currentNumNodes + " to " + requestedNumNodes);

		if (requestedNumNodes > this.currentNumNodes) {
			this.increaseCapacity(requestedNumNodes - this.currentNumNodes);
		} else {
			this.shrinkCapacity(this.currentNumNodes - requestedNumNodes);
		}

		this.currentNumNodes = requestedNumNodes;
	}

	/**
	 * 
	 * @return
	 */
	// TODO: Pass AssemblyComponent
	private Collection<DeploymentComponent> selectActiveDeploymentComponents(
			final int numComponents) {
		final Collection<DeploymentComponent> result =
				new ArrayList<DeploymentComponent>();

		/*
		 * Fetch all deployments for the assembly component (including inactive
		 * ones)
		 */
		final Collection<DeploymentComponent> deplsForAssemblyComponents =
				this.modelManager.getComponentDeploymentModelManager()
						.deploymentComponentsForAssemblyComponent(
								this.assemblyComponent);

		ConfigurationManager.log.info("Found "
				+ deplsForAssemblyComponents.size()
				+ " deployment components (including inactive)");

		/* Select the active deployment components */
		final Collection<DeploymentComponent> activeDeplsForAssemblyComponents =
				new ArrayList<DeploymentComponent>();
		for (final DeploymentComponent deplComp : deplsForAssemblyComponents) {
			if (deplComp.isActive()) {
				activeDeplsForAssemblyComponents.add(deplComp);
			}
		}

		ConfigurationManager.log.info("Found "
				+ activeDeplsForAssemblyComponents.size()
				+ " ACTIVE deployment components");

		if (activeDeplsForAssemblyComponents.size() < numComponents) {
			ConfigurationManager.log.error("Requested to select "
					+ numComponents + " components but only "
					+ activeDeplsForAssemblyComponents.size()
					+ " active one exist");
			return null;
		}

		final Iterator<DeploymentComponent> it =
				activeDeplsForAssemblyComponents.iterator();
		// TODO: Remove HACK:
		it.next();
		for (int curIdx = 0; curIdx < numComponents; curIdx++) {
			result.add(it.next());
		}

		return result;
	}

	// TODO: pass AssemblyComponent
	private boolean shrinkCapacity(final int shrinkBy) {
		final Collection<DeploymentComponent> shrinkList =
				this.selectActiveDeploymentComponents(shrinkBy);
		ConfigurationManager.log.info("shrinkList: " + shrinkList);
		for (final DeploymentComponent deplComponent : shrinkList) {
			ConfigurationManager.log.info("Processing " + deplComponent);
			/* Un-deploy und de-allocate */
			if (!this.reconfigurationManager
					.dereplicateComponent(deplComponent)) {
				ConfigurationManager.log.error("Failed to dereplicateComponent" + deplComponent);
				return false;
			}

			if (!this.reconfigurationManager
					.deallocateExecutionContainer(deplComponent
							.getExecutionContainer())) {
				ConfigurationManager.log.error("Failed to deallocateExecutionContainer: "
						+ deplComponent.getExecutionContainer());
				return false;
			}
		}
		return true;
	}

	private void increaseCapacity(final int increaseBy) {
		for (int i = 0; i < increaseBy; i++) {
			final ExecutionContainer container =
					this.reconfigurationManager.allocateExecutionContainer(
							this.createExecutionContainerName(),
							this.executionContainerType);
			this.reconfigurationManager.replicateComponent(
					this.assemblyComponent, container);
		}
	}
	
	// TODO: refine
	private final String createExecutionContainerName() {
		return ConfigurationManager.EXECUTION_CONTAINER_NAME_PREFIX
				+ this.nextExecutionContainerIndex++;
	}
}