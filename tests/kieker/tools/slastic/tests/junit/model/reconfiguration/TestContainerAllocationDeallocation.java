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

package kieker.tools.slastic.tests.junit.model.reconfiguration;

import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.plugins.slasticImpl.ModelManager;

import kieker.tools.slastic.metamodel.core.SystemModel;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestContainerAllocationDeallocation extends TestCase {

	private final String CONTAINER_TYPE_NAME = "CONTAINER_T";

	public void testAllocateDeallocate() {
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager mgr = new ModelManager(systemModel);

		final ExecutionContainerType executionContainerType = mgr.getTypeRepositoryManager().createAndRegisterExecutionContainerType(this.CONTAINER_TYPE_NAME);

		final int nextId = 0;

		final String fqContainerName = this.CONTAINER_TYPE_NAME + "-" + nextId;

		final ExecutionContainer executionContainer =
				mgr.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(fqContainerName,
						executionContainerType,
						/* mark allocated */false);

		/* Lookup by name and make sure that marked inactive */

		final ExecutionContainer executionContainerLookup = mgr.getExecutionEnvironmentModelManager().lookupExecutionContainer(fqContainerName);

		Assert.assertSame(executionContainer, executionContainerLookup);

		Assert.assertEquals("Expected execution container to be marked inactive", false, executionContainerLookup.isActive());

		/* Lookup by type (include/exclude inactive ones) */
		{
			final Collection<ExecutionContainer> activeAndInactiveContainersOfType =
					mgr.getExecutionEnvironmentModelManager().executionContainersForType(executionContainerType, true);
			Assert.assertEquals(1, activeAndInactiveContainersOfType.size());

			final Collection<ExecutionContainer> activeContainersOfType =
					mgr.getExecutionEnvironmentModelManager().executionContainersForType(executionContainerType, false);
			Assert.assertEquals(0, activeContainersOfType.size());
		}

		/* Activate (allocate) and make sure that flag set */
		Assert.assertTrue(mgr.getExecutionEnvironmentModelManager().allocateExecutionContainer(executionContainerLookup));
		Assert.assertEquals("Expected execution container to be marked active", true, executionContainerLookup.isActive());

		/* Lookup by type (include/exclude inactive ones) */
		{
			final Collection<ExecutionContainer> activeAndInactiveContainersOfType =
					mgr.getExecutionEnvironmentModelManager().executionContainersForType(executionContainerType, true);
			Assert.assertEquals(1, activeAndInactiveContainersOfType.size());

			final Collection<ExecutionContainer> activeContainersOfType =
					mgr.getExecutionEnvironmentModelManager().executionContainersForType(executionContainerType, false);
			Assert.assertEquals(1, activeContainersOfType.size());
		}

		/* Deallocate node */
		Assert.assertTrue(mgr.getExecutionEnvironmentModelManager().deallocateExecutionContainer(executionContainer));

		/* Lookup by type (include/exclude inactive ones) */
		{
			final Collection<ExecutionContainer> activeAndInactiveContainersOfType =
					mgr.getExecutionEnvironmentModelManager().executionContainersForType(executionContainerType, true);
			Assert.assertEquals(1, activeAndInactiveContainersOfType.size());

			final Collection<ExecutionContainer> activeContainersOfType =
					mgr.getExecutionEnvironmentModelManager().executionContainersForType(executionContainerType, false);
			Assert.assertEquals(0, activeContainersOfType.size());
		}
	}
}
