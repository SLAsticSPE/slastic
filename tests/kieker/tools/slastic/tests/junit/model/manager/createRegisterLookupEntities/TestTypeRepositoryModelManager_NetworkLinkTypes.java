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

package kieker.tools.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.AbstractModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel;

/**
 * Tests the functionalities provided by the type repository manager for creating,
 * registering, and looking up component type. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestTypeRepositoryModelManager_NetworkLinkTypes extends AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<TypeRepositoryModel, NetworkLinkType> {

	// private static final Log LOG = LogFactory.getLog(TestTypeRepositoryModelManager_NetworkLinkTypes.class);

	@Override
	protected TypeRepositoryModel createModel() {
		return TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
	}

	@Override
	protected NetworkLinkType createAndRegisterEntity(final AbstractModelManager<TypeRepositoryModel> mgr,
			final String fqEntityName, final ModelManager systemModelMgr) {
		if (!(mgr instanceof TypeRepositoryModelManager)) {
			Assert.fail("mgr must be instance of TypeRepositoryModelManager");
			return null;
		}
		return ((TypeRepositoryModelManager) mgr).createAndRegisterNetworkLinkType(fqEntityName);
	}

	@Override
	protected NetworkLinkType lookupEntity(final AbstractModelManager<TypeRepositoryModel> mgr, final String fqEntityName) {
		if (!(mgr instanceof TypeRepositoryModelManager)) {
			Assert.fail("mgr must be instance of TypeRepositoryModelManager");
			return null;
		}
		return ((TypeRepositoryModelManager) mgr).lookupNetworkLinkType(fqEntityName);
	}

	@Override
	protected NetworkLinkType lookupEntity(final AbstractModelManager<TypeRepositoryModel> mgr, final long entityId) {
		if (!(mgr instanceof TypeRepositoryModelManager)) {
			Assert.fail("mgr must be instance of TypeRepositoryModelManager");
			return null;
		}
		return ((TypeRepositoryModelManager) mgr).lookupNetworkLinkType(entityId);
	}

	@Override
	protected AbstractModelManager<TypeRepositoryModel> getModelManager(final ModelManager systemModelMgr) {
		return systemModelMgr.getTypeRepositoryManager();
	}
}
