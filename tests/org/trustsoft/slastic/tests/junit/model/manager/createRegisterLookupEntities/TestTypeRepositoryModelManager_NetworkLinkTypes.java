package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

/**
 * Tests the functionalities provided by the type repository manager for creating,
 * registering, and looking up component type. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestTypeRepositoryModelManager_NetworkLinkTypes extends AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<TypeRepositoryModel, NetworkLinkType> {

    private static final Log log = LogFactory.getLog(TestTypeRepositoryModelManager_NetworkLinkTypes.class);

    @Override
    protected TypeRepositoryModel createModel() {
        return TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
    }

    @Override
    protected NetworkLinkType createAndRegisterEntity(AbstractModelManager<TypeRepositoryModel> mgr, String fqEntityName, ModelManager systemModelMgr) {
        if (! (mgr instanceof TypeRepositoryModelManager)){
            fail("mgr must be instance of TypeRepositoryModelManager");
            return null;
        }
        return ((TypeRepositoryModelManager)mgr).createAndRegisterNetworkLinkType(fqEntityName);
    }

    @Override
    protected NetworkLinkType lookupEntity(AbstractModelManager<TypeRepositoryModel> mgr, String fqEntityName) {
        if (! (mgr instanceof TypeRepositoryModelManager)){
            fail("mgr must be instance of TypeRepositoryModelManager");
            return null;
        }
        return ((TypeRepositoryModelManager)mgr).lookupNetworkLinkType(fqEntityName);
    }

    @Override
    protected NetworkLinkType lookupEntity(AbstractModelManager<TypeRepositoryModel> mgr, long entityId) {
        if (! (mgr instanceof TypeRepositoryModelManager)){
            fail("mgr must be instance of TypeRepositoryModelManager");
            return null;
        }
        return ((TypeRepositoryModelManager)mgr).lookupNetworkLinkType(entityId);
    }

    @Override
    protected AbstractModelManager<TypeRepositoryModel> getModelManager(ModelManager systemModelMgr) {
        return systemModelMgr.getTypeRepositoryManager();
    }
}
