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
	public void testSplitNoPackage() {
		final String packageName = ""; // no package name
		final String name = "TheName";

		final String[] split = NameUtils.splitFullyQualifiedName(packageName + "." + name);
		Assert.assertEquals("Package names don't match", packageName, split[0]);
		Assert.assertEquals("Names don't match", name, split[1]);
	}

	/**
	 * Tests whether full-qualified names are properly split into
	 * package name and identifier. In this test, a package without
	 * subpackages is used
	 */
	public void testSplitTopLevelPackage() {
		final String packageName = "a";
		final String name = "TheName";

		final String[] split = NameUtils.splitFullyQualifiedName(packageName + "." + name);
		Assert.assertEquals("Package names don't match", packageName, split[0]);
		Assert.assertEquals("Names don't match", name, split[1]);
	}
}
