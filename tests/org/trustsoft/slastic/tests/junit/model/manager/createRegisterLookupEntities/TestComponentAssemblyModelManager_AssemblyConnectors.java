package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;

/**
 * Tests the functionalities provided by the connector assembly manager for creating,
 * registering, and looking up assembly connectors. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_AssemblyConnectors extends AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ComponentAssemblyModel, AssemblyConnector> {

    private static final Log log = LogFactory.getLog(TestComponentAssemblyModelManager_AssemblyConnectors.class);

    @Override
    protected ComponentAssemblyModel createModel() {
        return ComponentAssemblyFactory.eINSTANCE.createComponentAssemblyModel();
    }

    @Override
    protected AssemblyConnector createAndRegisterEntity(AbstractModelManager<ComponentAssemblyModel> mgr, String fqEntityName, ModelManager systemModelMgr) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        /*
         * A connector type is needed to create an assembly connector -- we'll
         * create one with a generated name or use an existing one, if a
         * connector type with this name exists already.
         */
        String connectorTypeName = fqEntityName+"Type"; // create type name from assembly connector name
        ConnectorType connectorType = // use existing type instance if it exists already
                systemModelMgr.getTypeRepositoryManager().lookupConnectorType(connectorTypeName);
        if (connectorType == null){
            connectorType = systemModelMgr.getTypeRepositoryManager().createAndRegisterConnectorType(connectorTypeName);
        }
        assertNotNull("Test invalid: connectorType == null", connectorType);
        return ((ComponentAssemblyModelManager)mgr).createAndRegisterAssemblyConnector(fqEntityName, connectorType);
    }

    @Override
    protected AssemblyConnector lookupEntity(AbstractModelManager<ComponentAssemblyModel> mgr, String fqEntityName) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        return ((ComponentAssemblyModelManager)mgr).lookupAssemblyConnector(fqEntityName);
    }

    @Override
    protected AssemblyConnector lookupEntity(AbstractModelManager<ComponentAssemblyModel> mgr, long entityId) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        return ((ComponentAssemblyModelManager)mgr).lookupAssemblyConnector(entityId);
    }

    @Override
    protected AbstractModelManager<ComponentAssemblyModel> getModelManager(ModelManager systemModelMgr) {
        return systemModelMgr.getComponentAssemblyModelManager();
    }
}
