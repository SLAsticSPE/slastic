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
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;

/**
 * Tests the functionalities provided by the component assembly manager for creating,
 * registering, and looking up assembly components. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 * 
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_AssemblyComponents extends
		AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ComponentAssemblyModel, AssemblyComponent> {

	// private static final Log LOG = LogFactory.getLog(TestComponentAssemblyModelManager_AssemblyComponents.class);

	@Override
	protected ComponentAssemblyModel createModel() {
		return ComponentAssemblyFactory.eINSTANCE.createComponentAssemblyModel();
	}

	@Override
	protected AssemblyComponent createAndRegisterEntity(final AbstractModelManager<ComponentAssemblyModel> mgr, final String fqEntityName,
			final ModelManager systemModelMgr) {
		if (!(mgr instanceof ComponentAssemblyModelManager)) {
			Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
			return null;
		}
		/*
		 * A component type is needed to create an assembly component -- we'll
		 * create one with a generated name or use an existing one, if a
		 * component type with this name exists already.
		 */
		final String componentTypeName = fqEntityName + "Type"; // create type name from assembly component name
		ComponentType componentType = // use existing type instance if it exists already
		systemModelMgr.getTypeRepositoryManager().lookupComponentType(componentTypeName);
		if (componentType == null) {
			componentType = systemModelMgr.getTypeRepositoryManager().createAndRegisterComponentType(componentTypeName);
		}
		Assert.assertNotNull("Test invalid: componentType == null", componentType);
		return ((ComponentAssemblyModelManager) mgr).createAndRegisterAssemblyComponent(fqEntityName, componentType);
	}

	@Override
	protected AssemblyComponent lookupEntity(final AbstractModelManager<ComponentAssemblyModel> mgr, final String fqEntityName) {
		if (!(mgr instanceof ComponentAssemblyModelManager)) {
			Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
			return null;
		}
		return ((ComponentAssemblyModelManager) mgr).lookupAssemblyComponent(fqEntityName);
	}

	@Override
	protected AssemblyComponent lookupEntity(final AbstractModelManager<ComponentAssemblyModel> mgr, final long entityId) {
		if (!(mgr instanceof ComponentAssemblyModelManager)) {
			Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
			return null;
		}
		return ((ComponentAssemblyModelManager) mgr).lookupAssemblyComponent(entityId);
	}

	@Override
	protected AbstractModelManager<ComponentAssemblyModel> getModelManager(final ModelManager systemModelMgr) {
		return systemModelMgr.getComponentAssemblyModelManager();
	}
}
