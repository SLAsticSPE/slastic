/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */

package kieker.tools.slastic.plugins.slasticImpl.model;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Andre van Hoorn
 */
public class NameUtils {
	/**
	 * Returns an array containing the package name and identifier components of
	 * a fully-qualified name string.
	 * 
	 * @param fullyQualifiedName
	 *            the fully-qualified name to split
	 * @return the array field with index 0 contains the package name, the array
	 *         field with index 1 contains the identifier
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
		return new String[] { packageName, identifier };
	}

	public final static int ABSTRACTION_MODE_CLASS = -2;
	public final static int ABSTRACTION_MODE_PACKAGE_STRICT = -1;
	public final static int ABSTRACTION_MODE_SINGLE_COMPONENT = 0;

	public final static String UNDEF_COMPONENT_NAME = "UnnamedClass";

	/**
	 * 
	 * @param packageName
	 * @param className
	 * @param operationName
	 * @param newNameHierarchyDepth
	 * @return the array field with index 0 contains the package name, the array
	 *         field with index 1 contains the identifier, the array field with
	 *         index 2 contains the operationName
	 */
	public static String[] abstractFQName(final String packageName,
			final String className, final String operationName,
			int newNameHierarchyDepth) throws IllegalArgumentException {
		final String[] oldPackageHierarchy;
		if (packageName.isEmpty()) {
			oldPackageHierarchy = new String[] {};
		} else {
			oldPackageHierarchy = packageName.split("\\.");
		}
		// name hierarchy is package hierarchy + class name
		final int oldNameHierarchyDepth = oldPackageHierarchy.length + 1;

		/*
		 * Value of oldNameHierarchyDepth is >= 1 (minimal value with class but
		 * empty package name)
		 */

		if (newNameHierarchyDepth == ABSTRACTION_MODE_CLASS) {
			/*
			 * We could use the algorithm below also for this case by setting
			 * abstractionLevel = oldHierarchyDepth. But for performance
			 * reasons, we return the result immediately.
			 */
			return new String[] { packageName, className, operationName };
		} else if (newNameHierarchyDepth == ABSTRACTION_MODE_PACKAGE_STRICT) {
			if (oldNameHierarchyDepth == 1) {
				throw new IllegalArgumentException("Mode ABSTRACTION_MODE_PACKAGE_STRICT not allowed for empty package");
			}
			newNameHierarchyDepth = oldNameHierarchyDepth - 1;
		} else if (newNameHierarchyDepth == ABSTRACTION_MODE_SINGLE_COMPONENT) {
			newNameHierarchyDepth = 0;
		}

		/*
		 * Covers cases like "a.b.c.Class.op1()" with levels > 4. These are abstracted into
		 * UNDEF_COMPONENT_NAME._a_b_c_class__op1()
		 */
		if (oldNameHierarchyDepth < newNameHierarchyDepth) {
			newNameHierarchyDepth = 0;
		}

		/*
		 * Value of newNameHierarchyDepth is >= 0 (minimal value for
		 * ABSTRACTION_MODE_SINGLE_COMPONENT or class and empty package)
		 */

		/* Create old name hierarchy of package and class name */
		final String[] oldNameHierarchy = new String[oldNameHierarchyDepth];
		// Is there a smarter way to do this? JDK function? */
		for (int i = 0; i < (oldNameHierarchyDepth - 1); i++) {
			oldNameHierarchy[i] = oldPackageHierarchy[i];
		}
		oldNameHierarchy[oldNameHierarchyDepth - 1] = StringUtils.uncapitalize(className);

		/* Create the new package hierarchy */
		final int newPackageHierarchyDepth;
		if (newNameHierarchyDepth > 0) {
			newPackageHierarchyDepth = newNameHierarchyDepth - 1;
		} else {
			newPackageHierarchyDepth = 0;
		}

		final String[] newPackageHierarchy =
				new String[newPackageHierarchyDepth];
		for (int i = 0; i < newPackageHierarchyDepth; i++) {
			newPackageHierarchy[i] = oldNameHierarchy[i];
		}
		final String newPackageName = StringUtils.join(newPackageHierarchy, '.');

		/* Create the new className */
		final String newClassname;
		if ((newNameHierarchyDepth == 0) || (newNameHierarchyDepth > oldNameHierarchyDepth)) {
			newClassname = UNDEF_COMPONENT_NAME;
		} else {
			newClassname = StringUtils.capitalize(oldNameHierarchy[newNameHierarchyDepth - 1]);
		}

		/* Create the operation name prefix */
		final int opNamePrefixLength = (newNameHierarchyDepth > oldNameHierarchyDepth) ? 0 : oldNameHierarchyDepth - newNameHierarchyDepth;
		final String[] opNamePrefix = new String[opNamePrefixLength];
		for (int i = 0; i < opNamePrefixLength; i++) {
			opNamePrefix[i] = oldNameHierarchy[newNameHierarchyDepth + i];
		}

		/* Create the operation name */
		final String newOpName;
		if (opNamePrefixLength > 0) {
			newOpName = String.format("%s__%s", StringUtils.join(opNamePrefix, '_'), operationName);
		} else {
			newOpName = operationName;
		}

		return new String[] { newPackageName, newClassname, newOpName };
	}

	public static String createFQName(final String packageName, final String name) {
		final StringBuilder strB = new StringBuilder();

		if ((packageName != null) && !packageName.isEmpty()) {
			strB.append(packageName).append(".");
		}
		strB.append(name);
		return strB.toString();
	}

	/**
	 * Returns a unique name made up by the given prefix followed by
	 * a unique ID generated by {@link UUID#randomUUID()}.
	 */
	public static String createUniqueName(final String prefix) {
		final UUID uuid = UUID.randomUUID();
		return prefix + uuid.toString();
	}
}
