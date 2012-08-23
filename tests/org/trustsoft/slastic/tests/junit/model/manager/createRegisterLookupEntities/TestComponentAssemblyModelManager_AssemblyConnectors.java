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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;

/**
 * Tests the functionalities provided by the connector assembly manager for creating,
 * registering, and looking up assembly connectors. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_AssemblyConnectors extends AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ComponentAssemblyModel, AssemblyComponentConnector> {

    private static final Log log = LogFactory.getLog(TestComponentAssemblyModelManager_AssemblyConnectors.class);

    @Override
    protected ComponentAssemblyModel createModel() {
        return ComponentAssemblyFactory.eINSTANCE.createComponentAssemblyModel();
    }

    @Override
    protected AssemblyComponentConnector createAndRegisterEntity(final AbstractModelManager<ComponentAssemblyModel> mgr, final String fqEntityName, final ModelManager systemModelMgr) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        /*
         * A connector type is needed to create an assembly connector -- we'll
         * create one with a generated name or use an existing one, if a
         * connector type with this name exists already.
         */
        final String connectorTypeName = fqEntityName+"Type"; // create type name from assembly connector name
        final TypeRepositoryModelManager typeModelMgr = systemModelMgr.getTypeRepositoryManager();
        
        ConnectorType connectorType = // use existing type instance if it exists already
                typeModelMgr.lookupConnectorType(connectorTypeName);
        if (connectorType == null){
            final Interface iface = typeModelMgr.createAndRegisterInterface("I"+fqEntityName);
            connectorType = systemModelMgr.getTypeRepositoryManager().createAndRegisterConnectorType(connectorTypeName, iface);
        }
        Assert.assertNotNull("Test invalid: connectorType == null", connectorType);
        return ((ComponentAssemblyModelManager)mgr).createAndRegisterAssemblyConnector(fqEntityName, connectorType);
    }

    @Override
    protected AssemblyComponentConnector lookupEntity(final AbstractModelManager<ComponentAssemblyModel> mgr, final String fqEntityName) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        return ((ComponentAssemblyModelManager)mgr).lookupAssemblyConnector(fqEntityName);
    }

    @Override
    protected AssemblyComponentConnector lookupEntity(final AbstractModelManager<ComponentAssemblyModel> mgr, final long entityId) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            Assert.fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        return ((ComponentAssemblyModelManager)mgr).lookupAssemblyConnector(entityId);
    }

    @Override
    protected AbstractModelManager<ComponentAssemblyModel> getModelManager(final ModelManager systemModelMgr) {
        return systemModelMgr.getComponentAssemblyModelManager();
    }
}
