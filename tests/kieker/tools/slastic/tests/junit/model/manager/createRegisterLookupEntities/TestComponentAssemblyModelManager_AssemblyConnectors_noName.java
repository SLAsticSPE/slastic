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
import junit.framework.TestCase;

import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.AssemblyConnectorsManager;
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.core.SystemModel;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;

/**
 * Tests the functionalities provided by the connector assembly manager for creating,
 * registering, and looking up assembly connectors without passing a name, i.e. a
 * unique name is assigned by the connector assembly manager.
 * 
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_AssemblyConnectors_noName extends TestCase {

	// private static final Log log = LogFactory.getLog(TestComponentAssemblyModelManager_AssemblyConnectors_noName.class);

	public void testCreateLookupConnectorWithGeneratedName() throws Exception {
		final SystemModel systemModel = ModelManager.createInitializedSystemModel();
		final ModelManager systemModelManager = new ModelManager(systemModel);
		final ComponentAssemblyModelManager componentAssemblyModelManager = systemModelManager.getComponentAssemblyModelManager();

		/*
		 * A connector type is needed to create an assembly connector -- we'll
		 * create one with a generated name or use an existing one, if a
		 * connector type with this name exists already.
		 */
		final String connectorTypeName = "AConnectorType";
		final TypeRepositoryModelManager typeModelMgr = systemModelManager.getTypeRepositoryManager();
		ConnectorType connectorType = // use existing type instance if it exists already
		typeModelMgr.lookupConnectorType(connectorTypeName);
		if (connectorType == null) {
			final Interface iface = typeModelMgr.createAndRegisterInterface("ISomething");
			connectorType = typeModelMgr.createAndRegisterConnectorType(connectorTypeName, iface);
		}
		Assert.assertNotNull("Test invalid: connectorType == null", connectorType);

		/* Now, we'll create an assembly connector without providing a name and do some checks on the name */
		final AssemblyComponentConnector assemblyConnector = componentAssemblyModelManager.createAndRegisterAssemblyConnector(connectorType);
		final String fqConnectorName = NameUtils.createFQName(assemblyConnector.getPackageName(), assemblyConnector.getName());
		Assert.assertNotNull("Connector name is null!", fqConnectorName);
		Assert.assertTrue("Expecting connector name to start with " + AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX,
				fqConnectorName.startsWith(AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX));
		Assert.assertTrue("Connector name should be longer than prefix", fqConnectorName.length() > AssemblyConnectorsManager.ASMCONNECT_NO_NAME_PREFIX.length());

		/* Now, we'll perform a lookup */
		final AssemblyComponentConnector lookedUpConnector = componentAssemblyModelManager.lookupAssemblyConnector(fqConnectorName);
		Assert.assertNotNull("Failed to lookup assembly connector with name " + fqConnectorName, lookedUpConnector);
	}
}
