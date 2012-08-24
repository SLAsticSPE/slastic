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

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;

/**
 * Tests the functionalities provided by the execution environment manager for creating,
 * registering, and looking up network links. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestExecutionEnvironenmentModelManager_NetworkLinks extends
		AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ExecutionEnvironmentModel, NetworkLink> {

	// private static final Log LOG = LogFactory.getLog(TestExecutionEnvironenmentModelManager_NetworkLinks.class);

	@Override
	protected ExecutionEnvironmentModel createModel() {
		return ExecutionEnvironmentFactory.eINSTANCE.createExecutionEnvironmentModel();
	}

	@Override
	protected NetworkLink createAndRegisterEntity(final AbstractModelManager<ExecutionEnvironmentModel> mgr, final String fqEntityName,
			final ModelManager systemModelMgr) {
		if (!(mgr instanceof ExecutionEnvironmentModelManager)) {
			Assert.fail("mgr must be instance of ExecutionEnvironmentModelManager");
			return null;
		}
		/*
		 * A network link type is needed to create an network link
		 * -- we'll create one with a generated name or use an existing one, if an
		 * network link type with this name exists already.
		 */
		final String networkLinkName = fqEntityName + "Type"; // create type name from network link name
		NetworkLinkType networkLinkType = // use existing type instance if it exists already
		systemModelMgr.getTypeRepositoryManager().lookupNetworkLinkType(networkLinkName);
		if (networkLinkType == null) {
			networkLinkType = systemModelMgr.getTypeRepositoryManager().createAndRegisterNetworkLinkType(networkLinkName);
		}
		Assert.assertNotNull("Test invalid: networkLink == null", networkLinkType);
		return ((ExecutionEnvironmentModelManager) mgr).createAndRegisterNetworkLink(fqEntityName, networkLinkType);
	}

	@Override
	protected NetworkLink lookupEntity(final AbstractModelManager<ExecutionEnvironmentModel> mgr, final String fqEntityName) {
		if (!(mgr instanceof ExecutionEnvironmentModelManager)) {
			Assert.fail("mgr must be instance of ExecutionEnvironmentModelManager");
			return null;
		}
		return ((ExecutionEnvironmentModelManager) mgr).lookupNetworkLink(fqEntityName);
	}

	@Override
	protected NetworkLink lookupEntity(final AbstractModelManager<ExecutionEnvironmentModel> mgr, final long entityId) {
		if (!(mgr instanceof ExecutionEnvironmentModelManager)) {
			Assert.fail("mgr must be instance of ExecutionEnvironmentModelManager");
			return null;
		}
		return ((ExecutionEnvironmentModelManager) mgr).lookupNetworkLink(entityId);
	}

	@Override
	protected AbstractModelManager<ExecutionEnvironmentModel> getModelManager(final ModelManager systemModelMgr) {
		return systemModelMgr.getExecutionEnvironmentModelManager();
	}
}
