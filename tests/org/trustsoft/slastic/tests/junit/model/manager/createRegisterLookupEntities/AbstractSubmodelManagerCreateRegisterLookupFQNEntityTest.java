package org.trustsoft.slastic.tests.junit.model.manager.createRegisterLookupEntities;

import junit.framework.Assert;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import de.cau.se.slastic.metamodel.core.SLAsticModel;
import de.cau.se.slastic.metamodel.core.SystemModel;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest<M extends SLAsticModel, T extends FQNamedEntity> extends AbstractSubmodelManagerCreateRegisterLookupEntityTest<M, T> {

    private static final Log log = LogFactory.getLog(AbstractSubmodelManagerCreateRegisterLookupFQNEntityTest.class);

    protected abstract T createAndRegisterEntity(AbstractModelManager<M> mgr, String fqEntityName, ModelManager systemModelMgr);

    protected abstract T lookupEntity(AbstractModelManager<M> mgr, String fqEntityName);

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
        Assert.assertEquals("Package names not equal", entity.getPackageName(), packageName);
        Assert.assertEquals("Enttiy names not equal", entity.getName(), componentTypeName);
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
        Assert.assertEquals("Package names not equal", entity.getPackageName(), packageName);
        Assert.assertEquals("Entity names not equal", entity.getName(), componentTypeName);
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
        Assert.assertSame("Entity lookup by name failed", entity, entityLookedUpByName);
        final T componentTypeLookedUpById =
                this.lookupEntity(mgr, entity.getId());
        Assert.assertSame("Entity lookup by ID failed", entity, componentTypeLookedUpById);
    }

    /**
     * Tests whether the lookup functions work properly without a package name.
     */
    public final void testRegisterNewAndLookupEmptyPackageName() {
        final SystemModel systemModel = ModelManager.createInitializedSystemModel();
        final ModelManager systemModelMgr = new ModelManager(systemModel);
        final AbstractModelManager<M> mgr = this.getModelManager(systemModelMgr);
        final String componentTypeName = "EntityName";
        final T entity = this.createAndRegisterEntity(mgr, componentTypeName, systemModelMgr);
        final T entityLookedUpByName = this.lookupEntity(mgr, componentTypeName);
        Assert.assertSame("Entity lookup by name failed", entity, entityLookedUpByName);
        final T componentTypeLookedUpById =
                this.lookupEntity(mgr, entity.getId());
        Assert.assertSame("Entity lookup by ID failed", entity, componentTypeLookedUpById);
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
            Assert.fail("Expected " + IllegalArgumentException.class.getName() + " to be thrown");
        } catch (final IllegalArgumentException exc) {
            /* we want this exception to be thrown */
        }
    }
}
