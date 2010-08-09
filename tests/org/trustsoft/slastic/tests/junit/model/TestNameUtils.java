package org.trustsoft.slastic.tests.junit.model;

import junit.framework.TestCase;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;

/**
 *
 * @author Andre van Hoorn
 */
public class TestNameUtils extends TestCase {

    /**
     * Tests whether full-qualified names are properly split into
     * package name and identifier. In this test, an empty package
     * name is used.
     */
    public void testSplitNoPackage (){
        final String packageName = ""; // no package name
        final String name = "TheName";

        final String[] split =
                NameUtils.splitFullyQualifiedName(packageName+"."+name);
        assertEquals("Package names don't match",
                packageName, split[0]);
        assertEquals("Names don't match",
                name, split[1]);
    }

    /**
     * Tests whether full-qualified names are properly split into
     * package name and identifier. In this test, a package without
     * subpackages is used
     */
    public void testSplitTopLevelPackage (){
        final String packageName = "a"; // no package name
        final String name = "TheName";

        final String[] split =
                NameUtils.splitFullyQualifiedName(packageName+"."+name);
        assertEquals("Package names don't match",
                packageName, split[0]);
        assertEquals("Names don't match",
                name, split[1]);
    }
}
