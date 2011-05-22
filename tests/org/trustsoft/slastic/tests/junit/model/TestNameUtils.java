package org.trustsoft.slastic.tests.junit.model;

import junit.framework.Assert;
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
        Assert.assertEquals("Package names don't match",
                packageName, split[0]);
        Assert.assertEquals("Names don't match",
                name, split[1]);
    }

    /**
     * Tests whether full-qualified names are properly split into
     * package name and identifier. In this test, a package without
     * subpackages is used
     */
    public void testSplitTopLevelPackage (){
        final String packageName = "a"; 
        final String name = "TheName";

        final String[] split =
                NameUtils.splitFullyQualifiedName(packageName+"."+name);
        Assert.assertEquals("Package names don't match",
                packageName, split[0]);
        Assert.assertEquals("Names don't match",
                name, split[1]);
    }
}
