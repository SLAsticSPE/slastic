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

package org.trustsoft.slastic.plugins.cloud.slastic.control.adaptationPlanning;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * Executes reconfiguration requests received via its {@link #reconfigure(int)} manager employing the {@link EucalyptusReconfigurationManager}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class ConfigurationManager {

	private static final Log LOG = LogFactory.getLog(ConfigurationManager.class);

	private volatile int nextExecutionContainerIndex = 1;

	private final ModelManager modelManager;
	private final AbstractReconfigurationManagerComponent reconfigurationManager;

	/**
	 * The execution containers with these fully-qualified names and all
	 * deployment components on these containers will be excluded from
	 * deallocation/dereplication.
	 */
	private final Collection<String> containerExcludeList;

	/**
	 * Fully qualified {@link ExecutionContainer} name x max. allowed # of
	 * instances
	 */
	private final Map<String, Integer> maxNumContainers;

	/**
	 * 
	 * @param modelManager
	 * @param reconfigurationManager
	 */
	public ConfigurationManager(final ModelManager modelManager,
			final AbstractReconfigurationManagerComponent reconfigurationManager,
			final Collection<String> containerExcludeList, final Map<String, Integer> maxNumContainers) {
		this.modelManager = modelManager;
		this.reconfigurationManager = reconfigurationManager;
		this.containerExcludeList = containerExcludeList;
		this.maxNumContainers = maxNumContainers;
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
						.deploymentComponentsForAssemblyComponent(assemblyComponent,/* do not include inactive ones */false).size();

		if (requestedNumNodes == currentNumNodes) {
			return true;
		}

		LOG.info("Changing configuration from " + currentNumNodes + " to " + requestedNumNodes + " instances of assembly component " + assemblyComponent);

		boolean success = true;

		if (requestedNumNodes > currentNumNodes) {
			success = this.increaseCapacity(assemblyComponent, executionContainerType, requestedNumNodes
					- currentNumNodes);
		} else if (requestedNumNodes < currentNumNodes) {
			success = this.shrinkCapacity(assemblyComponent, currentNumNodes - requestedNumNodes);
		} else {
			/* make javac happy. This case cannot happen. */
			success = false;
		}

		return success;
	}

	public final synchronized boolean reconfigureWithRelativeNumNodes(final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType, final int relativeNumNodes) {
		final List<DeploymentComponent> nodes = this.filterAppsrvNodes(assemblyComponent);

		final int currentNumNodes = nodes.size();

		if (relativeNumNodes == 0) {
			return true;
		}

		final int requestedNumNodes = currentNumNodes + relativeNumNodes;

		LOG.info("Changing configuration from " + currentNumNodes + " to " + requestedNumNodes
				+ " instances of assembly component " + assemblyComponent);

		boolean success = false;

		if (requestedNumNodes > currentNumNodes) {
			success =
					this.increaseCapacity(assemblyComponent, executionContainerType, requestedNumNodes
							- currentNumNodes);
		} else if (requestedNumNodes < currentNumNodes) {
			success = this.shrinkCapacity(assemblyComponent, currentNumNodes - requestedNumNodes);
		}

		return success;
	}

	/**
	 * 
	 * @return
	 */
	private Collection<DeploymentComponent> selectActiveDeploymentComponentsForDereplication(
			final AssemblyComponent assemblyComponent, final int numComponents) {
		final List<DeploymentComponent> nodes = this.filterAppsrvNodes(assemblyComponent);

		LOG.info("Found " + nodes.size() + " ACTIVE deployment components");

		final Iterator<DeploymentComponent> it = nodes.iterator();
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
			LOG.error("Requested to select " + numComponents + " components but only "
					+ nodes.size() + " active (non-excluded) ones exist");
			return null;
		}

		return result;
	}

	// TODO: why hard-coded "appsrv"?
	private List<DeploymentComponent> filterAppsrvNodes(final AssemblyComponent assemblyComponent) {
		final Collection<DeploymentComponent> nodesUnfiltered =
				this.modelManager.getComponentDeploymentModelManager()
						.deploymentComponentsForAssemblyComponent(assemblyComponent,
								/* do not include inactive ones */false);

		final List<DeploymentComponent> nodes = new ArrayList<DeploymentComponent>();

		final Iterator<DeploymentComponent> iterator = nodesUnfiltered.iterator();
		while (iterator.hasNext()) {
			final DeploymentComponent deploymentComponent = iterator.next();
			if (deploymentComponent.getExecutionContainer().getName().startsWith("appsrv")) {
				nodes.add(deploymentComponent);
			}
		}
		return nodes;
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
		LOG.info("shrinkList: " + shrinkList);
		for (final DeploymentComponent deplComponent : shrinkList) {
			LOG.info("Processing " + deplComponent);
			/* Un-deploy und de-allocate */
			success = this.reconfigurationManager.dereplicateComponent(deplComponent);
			if (!success) {
				LOG.error("shrink capacity (" + (i_thRequest) + "/" + shrinkBy + "):" + "Failed to de-replicate component '" + deplComponent
						+ " -> Aborting reconfiguration");
				break;
			}

			final ExecutionContainer executionContainer = deplComponent.getExecutionContainer();
			success = this.reconfigurationManager.deallocateExecutionContainer(executionContainer);
			if (!success) {
				LOG.error("shrink capacity (" + (i_thRequest) + "/" + shrinkBy + "):" +
						"Failed to de-allocate execution container " + executionContainer + " -> Aborting reconfiguration");
				break;
			}

			i_thRequest++;
		}
		return success;
	}

	// TODO: why hard-coded "appsrv"?
	private boolean increaseCapacity(final AssemblyComponent assemblyComponent,
			final ExecutionContainerType executionContainerType, final int increaseBy) {
		// TODO: conceptually, this would involve the creation of a
		// reconfiguration plan. Then, the reconfiguration
		// manager would take care of the transactional change
		// execution.

		boolean success = true;
		final Collection<ExecutionContainer> nodesUnfiltered =
				this.modelManager.getExecutionEnvironmentModelManager()
						.executionContainersForType(executionContainerType, false);

		final List<ExecutionContainer> nodes = new ArrayList<ExecutionContainer>();

		final Iterator<ExecutionContainer> iterator = nodesUnfiltered.iterator();
		while (iterator.hasNext()) {
			final ExecutionContainer executionContainer = iterator.next();
			if (executionContainer.getName().startsWith("appsrv")) {
				nodes.add(executionContainer);
			}
		}

		final int curNumContainersForType = nodes.size();

		final String fqExecContainerTypeName = NameUtils.createFQName(executionContainerType.getPackageName(), executionContainerType.getName());

		final Integer maxNumNodesForType = this.maxNumContainers.get(fqExecContainerTypeName);
		if (maxNumNodesForType != null) { // otherwise there's no max. number
			if ((curNumContainersForType + increaseBy) > maxNumNodesForType) {
				LOG.warn(String.format(
						"Rejecting increase capacity request due to max. number of containers for type rule.\n"
								+ "Current: %s; requested additional #: %s; max: %s", curNumContainersForType, increaseBy, maxNumNodesForType));
				return false;
			}
		}

		for (int i = 0; (i < increaseBy) && success; i++) {
			final String containerName = this.createExecutionContainerName(executionContainerType);
			final ExecutionContainer container = this.reconfigurationManager.allocateExecutionContainer(containerName, executionContainerType);
			if (container != null) {
				final DeploymentComponent deploymentComponent = this.reconfigurationManager.replicateComponent(assemblyComponent, container);
				if (deploymentComponent == null) {
					LOG.error("increase capacity (" + (i + 1) + "/" + increaseBy + "):"
							+ "Failed to replicate component '" + assemblyComponent + "' to container " + container + " -> Aborting reconfiguration");
					success = false;
					break;
				}
			} else {
				LOG.error("increase capacity (" + (i + 1) + "/" + increaseBy + "):"
						+ "Failed to allocate execution container with name '" + containerName + "' and type '" + executionContainerType
						+ "' -> Aborting reconfiguration");
				success = false;
				break;
			}
		}

		return success;
	}

	private final String createExecutionContainerName(final ExecutionContainerType executionContainerType) {
		final String fqExecutionContainerTypeName = NameUtils.createFQName(executionContainerType.getPackageName(), executionContainerType.getName());
		return fqExecutionContainerTypeName + "-" + this.nextExecutionContainerIndex++;
	}
}
