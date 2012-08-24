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

package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * Tests the functionalities provided by the execution environment manager for
 * creating, registering, and looking up execution containers. All test methods
 * are inherited from the abstract super class $ {@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestExecutionEnvironenmentModelManager_ExecutionContainers extends
		AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ExecutionEnvironmentModel, ExecutionContainer> {

	// private static final Log LOG = LogFactory.getLog(TestExecutionEnvironenmentModelManager_ExecutionContainers.class);

	@Override
	protected ExecutionEnvironmentModel createModel() {
		return ExecutionEnvironmentFactory.eINSTANCE.createExecutionEnvironmentModel();
	}

	@Override
	protected ExecutionContainer createAndRegisterEntity(final AbstractModelManager<ExecutionEnvironmentModel> mgr, final String fqEntityName,
			final ModelManager systemModelMgr) {
		if (!(mgr instanceof ExecutionEnvironmentModelManager)) {
			Assert.fail("mgr must be instance of ExecutionEnvironmentModelManager");
			return null;
		}
		/*
		 * A execution container type is needed to create an execution container
		 * -- we'll create one with a generated name or use an existing one, if
		 * an execution container type with this name exists already.
		 */
		// "Type:" create type name from execution container name
		final String executionContainerName = fqEntityName + "Type";

		ExecutionContainerType executionContainerType = // use existing type instance if it exists already
		systemModelMgr.getTypeRepositoryManager().lookupExecutionContainerType(executionContainerName);
		if (executionContainerType == null) {
			executionContainerType = systemModelMgr.getTypeRepositoryManager().createAndRegisterExecutionContainerType(executionContainerName);
		}
		Assert.assertNotNull("Test invalid: executionContainerType == null", executionContainerType);

		final ExecutionContainer executionContainer =
				((ExecutionEnvironmentModelManager) mgr).createAndRegisterExecutionContainer(fqEntityName, executionContainerType,/* mark allocated */true);
		return executionContainer;
	}

	@Override
	protected ExecutionContainer lookupEntity(final AbstractModelManager<ExecutionEnvironmentModel> mgr, final String fqEntityName) {
		if (!(mgr instanceof ExecutionEnvironmentModelManager)) {
			Assert.fail("mgr must be instance of ExecutionEnvironmentModelManager");
			return null;
		}
		return ((ExecutionEnvironmentModelManager) mgr).lookupExecutionContainer(fqEntityName);
	}

	@Override
	protected ExecutionContainer lookupEntity(final AbstractModelManager<ExecutionEnvironmentModel> mgr, final long entityId) {
		if (!(mgr instanceof ExecutionEnvironmentModelManager)) {
			Assert.fail("mgr must be instance of ExecutionEnvironmentModelManager");
			return null;
		}
		return ((ExecutionEnvironmentModelManager) mgr).lookupExecutionContainer(entityId);
	}

	@Override
	protected AbstractModelManager<ExecutionEnvironmentModel> getModelManager(final ModelManager systemModelMgr) {
		return systemModelMgr.getExecutionEnvironmentModelManager();
	}
}
