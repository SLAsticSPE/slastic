package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.executionEnvironment.ExecutionEnvironmentModelManager;

/**
 * Tests the functionalities provided by the execution environment manager for creating,
 * registering, and looking up execution containers. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestExecutionEnvironenmentModelManager_ExecutionContainers extends AbstractSubmodelManagerCreateRegisterLookupEntityTest<ExecutionEnvironmentModel, ExecutionContainer> {

    private static final Log log = LogFactory.getLog(TestExecutionEnvironenmentModelManager_ExecutionContainers.class);

    @Override
    protected ExecutionEnvironmentModel createModel() {
        return ExecutionEnvironmentFactory.eINSTANCE.createExecutionEnvironmentModel();
    }

    @Override
    protected ExecutionContainer createAndRegisterEntity(AbstractModelManager<ExecutionEnvironmentModel> mgr, String fqEntityName, ModelManager systemModelMgr) {
        if (! (mgr instanceof ExecutionEnvironmentModelManager)){
            fail("mgr must be instance of ExecutionEnvironmentModelManager");
            return null;
        }
        /*
         * A execution container type is needed to create an execution container
         * -- we'll create one with a generated name or use an existing one, if an
         * execution container type with this name exists already.
         */
        String executionContainerName = fqEntityName+"Type"; // create type name from execution container name
        ExecutionContainerType executionContainerType = // use existing type instance if it exists already
                systemModelMgr.getTypeRepositoryManager().lookupExecutionContainerType(executionContainerName);
        if (executionContainerType == null){
            executionContainerType = systemModelMgr.getTypeRepositoryManager().createAndRegisterExecutionContainerType(executionContainerName);
        }
        assertNotNull("Test invalid: executionContainer == null", executionContainerType);
        return ((ExecutionEnvironmentModelManager)mgr).createAndRegisterExecutionContainer(fqEntityName, executionContainerType);
    }

    @Override
    protected ExecutionContainer lookupEntity(AbstractModelManager<ExecutionEnvironmentModel> mgr, String fqEntityName) {
        if (! (mgr instanceof ExecutionEnvironmentModelManager)){
            fail("mgr must be instance of ExecutionEnvironmentModelManager");
            return null;
        }
        return ((ExecutionEnvironmentModelManager)mgr).lookupExecutionContainer(fqEntityName);
    }

    @Override
    protected ExecutionContainer lookupEntity(AbstractModelManager<ExecutionEnvironmentModel> mgr, long entityId) {
        if (! (mgr instanceof ExecutionEnvironmentModelManager)){
            fail("mgr must be instance of ExecutionEnvironmentModelManager");
            return null;
        }
        return ((ExecutionEnvironmentModelManager)mgr).lookupExecutionContainer(entityId);
    }

    @Override
    protected AbstractModelManager<ExecutionEnvironmentModel> getModelManager(ModelManager systemModelMgr) {
        return systemModelMgr.getExecutionEnvironmentModelManager();
    }
}
