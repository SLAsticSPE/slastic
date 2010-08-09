package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;

/**
 * Tests the functionalities provided by the component assembly manager for creating,
 * registering, and looking up assembly components. All test methods are inherited
 * from the abstract super class ${@link AbstractSubmodelManagerCreateRegisterLookupEntityTest}.
 *
 * @author Andre van Hoorn
 */
public class TestComponentAssemblyModelManager_AssemblyComponents extends AbstractSubmodelManagerCreateRegisterLookupEntityTest<ComponentAssemblyModel, AssemblyComponent> {

    private static final Log log = LogFactory.getLog(TestComponentAssemblyModelManager_AssemblyComponents.class);

    @Override
    protected ComponentAssemblyModel createModel() {
        return ComponentAssemblyFactory.eINSTANCE.createComponentAssemblyModel();
    }

    @Override
    protected AssemblyComponent createAndRegisterEntity(AbstractModelManager<ComponentAssemblyModel> mgr, String fqEntityName, ModelManager systemModelMgr) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        /*
         * A component type is needed to create an assembly component -- we'll
         * create one with a generated name or use an existing one, if a
         * component type with this name exists already.
         */
        String componentTypeName = fqEntityName+"Type"; // create type name from assembly component name
        ComponentType componentType = // use existing type instance if it exists already
                systemModelMgr.getTypeRepositoryManager().lookupComponentType(componentTypeName);
        if (componentType == null){
            componentType = systemModelMgr.getTypeRepositoryManager().createAndRegisterComponentType(componentTypeName);
        }
        assertNotNull("Test invalid: componentType == null", componentType);
        return ((ComponentAssemblyModelManager)mgr).createAndRegisterAssemblyComponent(fqEntityName, componentType);
    }

    @Override
    protected AssemblyComponent lookupEntity(AbstractModelManager<ComponentAssemblyModel> mgr, String fqEntityName) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        return ((ComponentAssemblyModelManager)mgr).lookupAssemblyComponent(fqEntityName);
    }

    @Override
    protected AssemblyComponent lookupEntity(AbstractModelManager<ComponentAssemblyModel> mgr, long entityId) {
        if (! (mgr instanceof ComponentAssemblyModelManager)){
            fail("mgr must be instance of ComponentAssemblyModelManager");
            return null;
        }
        return ((ComponentAssemblyModelManager)mgr).lookupAssemblyComponent(entityId);
    }

    @Override
    protected AbstractModelManager<ComponentAssemblyModel> getModelManager(ModelManager systemModelMgr) {
        return systemModelMgr.getAssemblyModelManager();
    }
}
