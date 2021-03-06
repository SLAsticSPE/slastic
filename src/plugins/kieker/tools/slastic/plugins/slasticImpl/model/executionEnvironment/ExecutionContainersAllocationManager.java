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

package kieker.tools.slastic.plugins.slasticImpl.model.executionEnvironment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * 
 * @author Andre van Hoorn
 */
public class ExecutionContainersAllocationManager implements IExecutionContainersAllocationManager {
	private static final Log LOG = LogFactory.getLog(ExecutionContainersAllocationManager.class);

	private final Map<Long, ExecutionContainer> allocatedContainersById = new HashMap<Long, ExecutionContainer>();
	private final List<ExecutionContainer> allocatedExecutionContainers;

	@SuppressWarnings("unused")
	private ExecutionContainersAllocationManager() {
		this.allocatedExecutionContainers = null;
	}

	public ExecutionContainersAllocationManager(final List<ExecutionContainer> allocatedExecutionContainers) {
		this.allocatedExecutionContainers = allocatedExecutionContainers;
		for (final ExecutionContainer container : allocatedExecutionContainers) {
			this.allocatedContainersById.put(container.getId(), container);
		}
	}

	@Override
	public boolean allocateExecutionContainer(final ExecutionContainer executionContainer) {
		final long executionContainerId = executionContainer.getId();
		if (this.allocatedContainersById.containsKey(executionContainerId)) {
			LOG.warn("Execution container with id " + executionContainerId + " already allocated");
			return false;
		}
		this.allocatedContainersById.put(executionContainerId, executionContainer);
		return this.allocatedExecutionContainers.add(executionContainer);
	}

	/**
	 * TODO: Currently, we do not remove the {@link ExecutionContainer} but mark
	 * it inactive {@link ExecutionContainer#isActive()}.
	 */
	@Override
	public boolean deallocateExecutionContainer(final ExecutionContainer executionContainer) {
		final long executionContainerId = executionContainer.getId();
		if (!this.allocatedContainersById.containsKey(executionContainerId)) {
			LOG.warn("Execution container with id " + executionContainerId + " not allocated");
			return false;
		}
		final ExecutionContainer removedContainer = this.allocatedContainersById.remove(executionContainerId);
		if (removedContainer != executionContainer) {
			final IllegalStateException ise =
					new IllegalStateException(
							"Unexpected element removed with id"
									+ executionContainerId + ". Expected: "
									+ executionContainer + " ; removed: "
									+ removedContainer);
			LOG.error("IllegalStateException: " + ise.getMessage(), ise);
			throw ise;
		}
		// return this.allocatedExecutionContainers.remove(executionContainer);
		return this.allocatedExecutionContainers.contains(executionContainer);
	}
}
