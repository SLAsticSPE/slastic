package org.trustsoft.slastic.tests.junit.model.reconfiguration;

import java.util.Collection;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;

import de.cau.se.slastic.metamodel.core.SystemModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

public class TestContainerAllocationDeallocation extends TestCase {

	private final String CONTAINER_TYPE_NAME = "CONTAINER_T";

	public void testAllocateDeallocate() {
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager mgr = new ModelManager(systemModel);

		final ExecutionContainerType executionContainerType =
				mgr.getTypeRepositoryManager().createAndRegisterExecutionContainerType(this.CONTAINER_TYPE_NAME);

		final int nextId = 0;

		final String fqContainerName = this.CONTAINER_TYPE_NAME + "-" + nextId;

		final ExecutionContainer executionContainer =
				mgr.getExecutionEnvironmentModelManager().createAndRegisterExecutionContainer(fqContainerName,
						executionContainerType,
						/* mark allocated */false);

		/* Lookup by name and make sure that marked inactive */

		final ExecutionContainer executionContainerLookup =
				mgr.getExecutionEnvironmentModelManager().lookupExecutionContainer(fqContainerName);

		Assert.assertSame(executionContainer, executionContainerLookup);

		Assert.assertEquals("Expected execution container to be marked inactive", false,
				executionContainerLookup.isActive());

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
		Assert.assertTrue(mgr.getExecutionEnvironmentModelManager()
				.allocateExecutionContainer(executionContainerLookup));
		Assert.assertEquals("Expected execution container to be marked active", true,
				executionContainerLookup.isActive());

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
