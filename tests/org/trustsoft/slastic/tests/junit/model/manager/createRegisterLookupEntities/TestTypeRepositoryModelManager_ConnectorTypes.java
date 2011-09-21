/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;

/**
 * Tests the functionalities provided by the type repository manager for creating,
 * registering, and looking up connector types. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestTypeRepositoryModelManager_ConnectorTypes extends AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<TypeRepositoryModel, ConnectorType> {

    private static final Log log = LogFactory.getLog(TestTypeRepositoryModelManager_ConnectorTypes.class);

    @Override
    protected TypeRepositoryModel createModel() {
        return TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
    }

    @Override
    protected ConnectorType createAndRegisterEntity(final AbstractModelManager<TypeRepositoryModel> mgr, final String fqEntityName, final ModelManager systemModelMgr) {
        if (! (mgr instanceof TypeRepositoryModelManager)){
            Assert.fail("mgr must be instance of TypeRepositoryModelManager");
            return null;
        }
        final TypeRepositoryModelManager typeModelMgr = (TypeRepositoryModelManager)mgr;
        
        final Interface iface = typeModelMgr.createAndRegisterInterface("I"+fqEntityName);
        return typeModelMgr.createAndRegisterConnectorType(fqEntityName, iface);
    }

    @Override
    protected ConnectorType lookupEntity(final AbstractModelManager<TypeRepositoryModel> mgr, final String fqEntityName) {
        if (! (mgr instanceof TypeRepositoryModelManager)){
            Assert.fail("mgr must be instance of TypeRepositoryModelManager");
            return null;
        }
        return ((TypeRepositoryModelManager)mgr).lookupConnectorType(fqEntityName);
    }

    @Override
    protected ConnectorType lookupEntity(final AbstractModelManager<TypeRepositoryModel> mgr, final long entityId) {
        if (! (mgr instanceof TypeRepositoryModelManager)){
            Assert.fail("mgr must be instance of TypeRepositoryModelManager");
            return null;
        }
        return ((TypeRepositoryModelManager)mgr).lookupConnectorType(entityId);
    }

    @Override
    protected AbstractModelManager<TypeRepositoryModel> getModelManager(final ModelManager systemModelMgr) {
        return systemModelMgr.getTypeRepositoryManager();
    }
}
