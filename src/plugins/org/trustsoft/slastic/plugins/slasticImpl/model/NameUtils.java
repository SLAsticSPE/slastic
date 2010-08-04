/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.plugins.slasticImpl.model;

/**
 *
 * @author Andre van Hoorn
 */
public class NameUtils {
   /**
     * Returns an array containing the package name and identifier components
     * of a fully-qualified name string.
     *
     * @param fullyQualifiedName the fully-qualified name to split
     * @return the array field with index 0 contains the package name,
     *         the array field with index 1 contains the identifier
     *
     */
    public static String[] splitFullyQualifiedName(final String fullyQualifiedName) {
        final int idx = fullyQualifiedName.lastIndexOf(".");
        final String identifier;
        final String packageName;
        if (idx == -1) { // no package name
            packageName = "";
            identifier = fullyQualifiedName;
        } else {
            packageName = fullyQualifiedName.substring(0, idx);
            identifier = fullyQualifiedName.substring(idx + 1, fullyQualifiedName.length());
        }
        return new String[]{packageName, identifier};
    }
}
