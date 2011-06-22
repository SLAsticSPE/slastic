package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.cloud.slastic.reconfiguration.EucalyptusReconfigurationManager;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

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

	private volatile int nextExecutionContainerIndex = 1;

	private final ModelManager modelManager;
	private final EucalyptusReconfigurationManager reconfigurationManager;

	/**
	 * The execution containers with these fully-qualified names and all
	 * deployment components on these containers will be excluded from
	 * deallocation/dereplication.
	 */
	final Collection<String> containerExcludeList;

	/**
	 * 
	 * @param modelManager
	 * @param reconfigurationManager
	 */
	public ConfigurationManager(final ModelManager modelManager,
			final EucalyptusReconfigurationManager reconfigurationManager, final Collection<String> containerExcludeList) {
		this.modelManager = modelManager;
		this.reconfigurationManager = reconfigurationManager;
		this.containerExcludeList = containerExcludeList;
	}

	/**
	 * 
	 * @param requestedNumNodes
	 * @return
	 */
	public final synchronized boolean reconfigure(final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType, final int requestedNumNodes) {
		final int currentNumNodes =
				this.modelManager.getComponentDeploymentModelManager()
						.deploymentComponentsForAssemblyComponent(assemblyComponent,
						/* do not include inactive ones */false).size();

		if (requestedNumNodes == currentNumNodes) {
			return true;
		}

		ConfigurationManager.log.info("Changing configuration from " + currentNumNodes + " to " + requestedNumNodes
				+ " instances of assembly component " + assemblyComponent);

		final boolean success;

		if (requestedNumNodes > currentNumNodes) {
			success =
					this.increaseCapacity(assemblyComponent, executionContainerType, requestedNumNodes
							- currentNumNodes);
		} else if (requestedNumNodes < currentNumNodes) {
			success = this.shrinkCapacity(assemblyComponent, currentNumNodes - requestedNumNodes);
		} else {
			/* make javac happy. This case cannot happen. */
			success = false;
		}

		return success;
	}

	/**
	 * 
	 * @return
	 */
	private Collection<DeploymentComponent> selectActiveDeploymentComponentsForDereplication(
			final AssemblyComponent assemblyComponent, final int numComponents) {
		final Collection<DeploymentComponent> activeDeplsForAssemblyComponent =
				this.modelManager.getComponentDeploymentModelManager().deploymentComponentsForAssemblyComponent(
						assemblyComponent,
						/* do not include inactive ones */false);

		ConfigurationManager.log.info("Found " + activeDeplsForAssemblyComponent.size()
				+ " ACTIVE deployment components");

		final Iterator<DeploymentComponent> it = activeDeplsForAssemblyComponent.iterator();
		final Collection<DeploymentComponent> result = new ArrayList<DeploymentComponent>();

		while (it.hasNext() && !(result.size() == numComponents)) {
			final DeploymentComponent deploymentComponent = it.next();
			final ExecutionContainer executionContainer = deploymentComponent.getExecutionContainer();
			final String fqContainerName =
					NameUtils.createFQName(executionContainer.getPackageName(), executionContainer.getName());
			if (!this.containerExcludeList.contains(fqContainerName)) {
				result.add(deploymentComponent);
			} 
		}

		if (result.size() < numComponents) {
			ConfigurationManager.log.error("Requested to select " + numComponents + " components but only "
					+ activeDeplsForAssemblyComponent.size() + " active (non-excluded) ones exist");
			return null;
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
				this.selectActiveDeploymentComponentsForDereplication(assemblyComponent, shrinkBy);
		ConfigurationManager.log.info("shrinkList: " + shrinkList);
		for (final DeploymentComponent deplComponent : shrinkList) {
			ConfigurationManager.log.info("Processing " + deplComponent);
			/* Un-deploy und de-allocate */
			success = this.reconfigurationManager.dereplicateComponent(deplComponent);
			if (!success) {
				ConfigurationManager.log.error("shrink capacity (" + (i_thRequest) + "/" + shrinkBy + "):"
						+ "Failed to de-replicate component '" + deplComponent + " -> Aborting reconfiguration");
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
				final DeploymentComponent deploymentComponent =
						this.reconfigurationManager.replicateComponent(assemblyComponent, container);
				if (deploymentComponent == null) {
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
		final String fqExecutionContainerTypeName =
				NameUtils.createFQName(executionContainerType.getPackageName(), executionContainerType.getName());
		return fqExecutionContainerTypeName + "-" + this.nextExecutionContainerIndex++;
	}
}