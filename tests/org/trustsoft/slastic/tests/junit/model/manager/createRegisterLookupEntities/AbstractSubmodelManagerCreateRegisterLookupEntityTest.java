package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import de.cau.se.slastic.metamodel.core.SLAsticModel;
import de.cau.se.slastic.metamodel.core.SystemModel;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSubmodelManagerCreateRegisterLookupEntityTest<M extends SLAsticModel, T extends FQNamedEntity> extends TestCase {

    private static final Log log = LogFactory.getLog(AbstractSubmodelManagerCreateRegisterLookupEntityTest.class);

    protected abstract M createModel();

    protected abstract AbstractModelManager<M> getModelManager(ModelManager systemModelMgr);

    protected abstract T createAndRegisterEntity(AbstractModelManager<M> mgr, String fqEntityName, ModelManager systemModelMgr);

    protected abstract T lookupEntity(AbstractModelManager<M> mgr, String fqEntityName);

    protected abstract T lookupEntity(AbstractModelManager<M> mgr, long entityId);

    /**
     * Tests whether full-qualified entity type names are properly split into
     * package name and identifier.
     */
    public final void testFullQualifiedNameSplit() {
        final SystemModel systemModel = ModelManager.createInitializedSystemModel();
        final ModelManager systemModelMgr = new ModelManager(systemModel);
        final AbstractModelManager<M> mgr = this.getModelManager(systemModelMgr);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "EntityName";
        final T entity = this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName, systemModelMgr);
        assertEquals("Package names not equal", entity.getPackageName(), packageName);
        assertEquals("Enttiy names not equal", entity.getName(), componentTypeName);
    }

    /**
     * Tests whether full-qualified entity names are properly split into
     * package name and identifier. In this test, an empty package name is used.
     */
    public final void testFullQualifiedNameSplitEmptyPackage() {
        final SystemModel systemModel = ModelManager.createInitializedSystemModel();
        final ModelManager systemModelMgr = new ModelManager(systemModel);
        final AbstractModelManager<M> mgr = this.getModelManager(systemModelMgr);
        final String packageName = "";
        final String componentTypeName = "ComponentTypeName";
        final T entity = this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName, systemModelMgr);
        assertEquals("Package names not equal", entity.getPackageName(), packageName);
        assertEquals("Enttiy names not equal", entity.getName(), componentTypeName);
    }

    /**
     * Tests whether the lookup functions work properly.
     */
    public final void testRegisterNewAndLookup() {
        final SystemModel systemModel = ModelManager.createInitializedSystemModel();
        final ModelManager systemModelMgr = new ModelManager(systemModel);
        final AbstractModelManager<M> mgr = this.getModelManager(systemModelMgr);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "EntityName";
        final T entity = this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName, systemModelMgr);
        final T entityLookedUpByName = this.lookupEntity(mgr, packageName + "." + componentTypeName);
        assertSame("Entity lookup by name failed", entity, entityLookedUpByName);
        final T componentTypeLookedUpById =
                this.lookupEntity(mgr, entity.getId());
        assertSame("Entity lookup by ID failed", entity, componentTypeLookedUpById);
    }

    /**
     * Make sure that the createAndRegister... function throws an
     * ${@link IllegalArgumentException} if one tries to add an entity with a
     * name that is already registered.
     */
    public final void testRegisterNewExistingName() {
        final SystemModel systemModel = ModelManager.createInitializedSystemModel();
        final ModelManager systemModelMgr = new ModelManager(systemModel);
        final AbstractModelManager<M> mgr = this.getModelManager(systemModelMgr);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "EntityName";
        try {
            this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName, systemModelMgr);
            this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName, systemModelMgr);
            fail("Expected " + IllegalArgumentException.class.getName() + " to be thrown");
        } catch (IllegalArgumentException exc) {
            /* we want this exception to be thrown */
        }
    }
}
