/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.tests.junit.model;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import de.cau.se.slastic.metamodel.core.SLAsticModel;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.AbstractModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSubmodelManagerTest<M extends SLAsticModel, T extends FQNamedEntity> extends TestCase {

    private static final Log log = LogFactory.getLog(AbstractSubmodelManagerTest.class);

    protected abstract M createModel();

    protected abstract AbstractModelManager<M> createModelManager(M model);

    protected abstract T createAndRegisterEntity(AbstractModelManager<M> mgr, String fqEntityName);

    protected abstract T lookupEntity(AbstractModelManager<M> mgr, String fqEntityName);

    protected abstract T lookupEntity(AbstractModelManager<M> mgr, long entityId);

    /**
     * Tests whether full-qualified entity type names are properly split into
     * package name and identifier.
     */
    public void testFullQualifiedNameSplit() {
        final M model = this.createModel();
        final AbstractModelManager<M> mgr = this.createModelManager(model);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "EntityName";
        final T entity = this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName);
        assertEquals("Package names not equal", entity.getPackageName(), packageName);
        assertEquals("Enttiy names not equal", entity.getName(), componentTypeName);
    }

    /**
     * Tests whether full-qualified entity names are properly split into
     * package name and identifier. In this test, an empty package name is used.
     */
    public void testFullQualifiedNameSplitEmptyPackage() {
        final M model = this.createModel();
        final AbstractModelManager<M> mgr = this.createModelManager(model);
        final String packageName = "";
        final String componentTypeName = "ComponentTypeName";
        final T entity = this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName);
        assertEquals("Package names not equal", entity.getPackageName(), packageName);
        assertEquals("Enttiy names not equal", entity.getName(), componentTypeName);
    }

    /**
     * Tests whether the lookup functions work properly.
     */
    public void testRegisterNewAndLookup() {
        final M model = this.createModel();
        final AbstractModelManager<M> mgr = this.createModelManager(model);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "EntityName";
        final T entity = this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName);
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
    public void testRegisterNewExistingName() {
        final M model = this.createModel();
        final AbstractModelManager<M> mgr = this.createModelManager(model);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "EntityName";
        try {
            this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName);
            this.createAndRegisterEntity(mgr, packageName + "." + componentTypeName);
            fail("Expected " + IllegalArgumentException.class.getName() + " to be thrown");
        } catch (IllegalArgumentException exc) {
            /* we want this exception to be thrown */
        }
    }
}
