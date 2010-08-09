/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.trustsoft.slastic.tests.junit.model;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public class TestTypeRepositoryComponentTypes extends TestCase {

    private static final Log log = LogFactory.getLog(TestTypeRepositoryComponentTypes.class);

    /**
     * Tests whether full-qualified component type names are properly split into
     * package name and identifier.
     */
    public void testFullQualifiedNameSplit() {
        final TypeRepositoryModel typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
        final TypeRepositoryModelManager repoMgr = new TypeRepositoryModelManager(typeRepositoryModel);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "ComponentTypeName";
        final ComponentType componentType =
                repoMgr.createAndRegisterComponentType(packageName + "." + componentTypeName);
        assertEquals("Package names not equal", componentType.getPackageName(), packageName);
        assertEquals("Component type names not equal", componentType.getName(), componentTypeName);
    }

    /**
     * Tests whether full-qualified component type names are properly split into
     * package name and identifier. In this test, an empty package name is used.
     */
    public void testFullQualifiedNameSplitEmptyPackage() {
       final TypeRepositoryModel typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
        final TypeRepositoryModelManager repoMgr = new TypeRepositoryModelManager(typeRepositoryModel);
        final String packageName = "";
        final String componentTypeName = "ComponentTypeName";
        final ComponentType componentType =
                repoMgr.createAndRegisterComponentType(packageName + "." + componentTypeName);
        assertEquals("Package names not equal", componentType.getPackageName(), packageName);
        assertEquals("Component type names not equal", componentType.getName(), componentTypeName);
    }

    /**
     * Tests whether the lookup functions work properly.
     */
    public void testRegisterNewAndLookup() {
       final TypeRepositoryModel typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
        final TypeRepositoryModelManager repoMgr = new TypeRepositoryModelManager(typeRepositoryModel);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "ComponentTypeName";
        final ComponentType componentType =
                repoMgr.createAndRegisterComponentType(packageName + "." + componentTypeName);
        final ComponentType componentTypeLookedUpByName =
                repoMgr.lookupComponentType(packageName + "." + componentTypeName);
        assertSame("Component type lookup by name failed",
                componentType, componentTypeLookedUpByName);
        final ComponentType componentTypeLookedUpById =
                repoMgr.lookupComponentType(componentType.getId());
        assertSame("Component type lookup by ID failed",
                componentType, componentTypeLookedUpById);
    }

    /**
     * Tests whether the createAndRegisterComponentType function throws an
     * ${@link IllegalArgumentException} if one tries to add a component type
     * with a name that is already registered.
     */
    public void testRegisterNewExistingName() {
       final TypeRepositoryModel typeRepositoryModel =
                TypeRepositoryFactory.eINSTANCE.createTypeRepositoryModel();
        final TypeRepositoryModelManager repoMgr = new TypeRepositoryModelManager(typeRepositoryModel);
        final String packageName = "de.cau.se.slastic.package";
        final String componentTypeName = "ComponentTypeName";
        try {
            repoMgr.createAndRegisterComponentType(packageName + "." + componentTypeName);
            repoMgr.createAndRegisterComponentType(packageName + "." + componentTypeName);
            fail("Expected " + IllegalArgumentException.class.getName() + " to be thrown");
        } catch (IllegalArgumentException exc) {
            /* we want this exception to be thrown */
        }
    }
}
