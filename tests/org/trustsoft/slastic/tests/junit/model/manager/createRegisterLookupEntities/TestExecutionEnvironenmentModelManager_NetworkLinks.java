package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;

/**
 * Tests the functionalities provided by the execution environment manager for creating,
 * registering, and looking up network links. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestExecutionEnvironenmentModelManager_NetworkLinks extends AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<ExecutionEnvironmentModel, NetworkLink> {

    private static final Log log = LogFactory.getLog(TestExecutionEnvironenmentModelManager_NetworkLinks.class);

    @Override
    protected ExecutionEnvironmentModel createModel() {
        return ExecutionEnvironmentFactory.eINSTANCE.createExecutionEnvironmentModel();
    }

    @Override
    protected NetworkLink createAndRegisterEntity(AbstractModelManager<ExecutionEnvironmentModel> mgr, String fqEntityName, ModelManager systemModelMgr) {
        if (! (mgr instanceof ExecutionEnvironmentModelManager)){
            fail("mgr must be instance of ExecutionEnvironmentModelManager");
            return null;
        }
        /*
         * A network link type is needed to create an network link
         * -- we'll create one with a generated name or use an existing one, if an
         * network link type with this name exists already.
         */
        String networkLinkName = fqEntityName+"Type"; // create type name from network link name
        NetworkLinkType networkLinkType = // use existing type instance if it exists already
                systemModelMgr.getTypeRepositoryManager().lookupNetworkLinkType(networkLinkName);
        if (networkLinkType == null){
            networkLinkType = systemModelMgr.getTypeRepositoryManager().createAndRegisterNetworkLinkType(networkLinkName);
        }
        assertNotNull("Test invalid: networkLink == null", networkLinkType);
        return ((ExecutionEnvironmentModelManager)mgr).createAndRegisterNetworkLink(fqEntityName, networkLinkType);
    }

    @Override
    protected NetworkLink lookupEntity(AbstractModelManager<ExecutionEnvironmentModel> mgr, String fqEntityName) {
        if (! (mgr instanceof ExecutionEnvironmentModelManager)){
            fail("mgr must be instance of ExecutionEnvironmentModelManager");
            return null;
        }
        return ((ExecutionEnvironmentModelManager)mgr).lookupNetworkLink(fqEntityName);
    }

    @Override
    protected NetworkLink lookupEntity(AbstractModelManager<ExecutionEnvironmentModel> mgr, long entityId) {
        if (! (mgr instanceof ExecutionEnvironmentModelManager)){
            fail("mgr must be instance of ExecutionEnvironmentModelManager");
            return null;
        }
        return ((ExecutionEnvironmentModelManager)mgr).lookupNetworkLink(entityId);
    }

    @Override
    protected AbstractModelManager<ExecutionEnvironmentModel> getModelManager(ModelManager systemModelMgr) {
        return systemModelMgr.getExecutionEnvironmentModelManager();
    }
}
