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

package kieker.tools.slastic.tests.junit.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;

import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;

/**
 * Tests for the method {@link NameUtils#abstractFQName(String, String, String, int)}.
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestNameUtilsAbstraction extends TestCase {

	/**
	 * 
	 */
	public void testClassModeNoPackage() {
		final String packageNameIn = "";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = packageNameIn;
		final String classNameExpected = classNameIn;
		final String operationNameExpected = operationNameIn;
		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				NameUtils.ABSTRACTION_MODE_CLASS);
	}

	/**
	 * 
	 * @throws IllegalArgumentException
	 *             iff {@link NameUtils#abstractFQName(String, String, String, int)} throws an {@link IllegalArgumentException}
	 */
	public void testClassModeWithPackage() throws IllegalArgumentException {
		final String packageNameIn = "a.b.c";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = packageNameIn;
		final String classNameExpected = classNameIn;
		final String operationNameExpected = operationNameIn;
		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				NameUtils.ABSTRACTION_MODE_CLASS);
	}

	/**
	 * Tests whether {@link NameUtils#abstractFQName(String, String, String, int)} throws an {@link IllegalArgumentException}
	 * {@link NameUtils#ABSTRACTION_MODE_PACKAGE_STRICT} when the package name
	 * is empty.
	 */
	public void testPackageStrictModeNoPackage() {
		final String packageNameIn = "";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = null;
		final String classNameExpected = null;
		final String operationNameExpected = null;

		try {
			this.checkAbstraction(
					new String[] { packageNameIn, classNameIn, operationNameIn },
					new String[] { packageNameExpected, classNameExpected, operationNameExpected },
					NameUtils.ABSTRACTION_MODE_PACKAGE_STRICT);
			Assert.fail("Expected IllegalArgumentException if ABSTRACTION_MODE_PACKAGE_STRICT and package empty");
		} catch (final IllegalArgumentException exc) {
			// we expect this exception to be thrown
		}
	}

	public void testPackageStrictMode() {
		final String packageNameIn = "a.b.c.d";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "a.b.c";
		final String classNameExpected = "D";
		final String operationNameExpected = "theClass__op1";

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				NameUtils.ABSTRACTION_MODE_PACKAGE_STRICT);
	}

	public void testPackageStrictModeSingleLevelPackage() {
		final String packageNameIn = "a";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "";
		final String classNameExpected = "A";
		final String operationNameExpected = "theClass__op1";

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				NameUtils.ABSTRACTION_MODE_PACKAGE_STRICT);
	}

	public void testSingleComponentModeNoPackage() {
		final String packageNameIn = "";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "";
		final String classNameExpected = NameUtils.UNDEF_COMPONENT_NAME;
		final String operationNameExpected = "theClass__op1";

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				NameUtils.ABSTRACTION_MODE_SINGLE_COMPONENT);
	}

	public void testSingleComponentModeWithPackage() {
		final String packageNameIn = "a.b.c";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "";
		final String classNameExpected = NameUtils.UNDEF_COMPONENT_NAME;
		final String operationNameExpected = "a_b_c_theClass__op1";

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				NameUtils.ABSTRACTION_MODE_SINGLE_COMPONENT);
	}

	public void testLevelOldDeeperThanNew() {
		final String packageNameIn = "a.b.c.d.e.f";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "a.b.c.d";
		final String classNameExpected = "E";
		final String operationNameExpected = "f_theClass__op1";

		final int hierarchyLevel = 5;

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				hierarchyLevel);
	}

	public void testLevelNewDeeperThanOld() {
		final String packageNameIn = "a.b.c";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "";
		final String classNameExpected = NameUtils.UNDEF_COMPONENT_NAME;
		final String operationNameExpected = "a_b_c_theClass__op1";

		final int hierarchyLevel = 6;

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				hierarchyLevel);
	}

	public void testLevel1NoPackage() {
		final String packageNameIn = "";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "";
		final String classNameExpected = classNameIn;
		final String operationNameExpected = operationNameIn;

		final int hierarchyLevel = 1;

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				hierarchyLevel);
	}

	public void testLevel1WithPackage() {
		final String packageNameIn = "a.b";
		final String classNameIn = "TheClass";
		final String operationNameIn = "op1";

		final String packageNameExpected = "";
		final String classNameExpected = "A";
		final String operationNameExpected = "b_theClass__op1";

		final int hierarchyLevel = 1;

		this.checkAbstraction(
				new String[] { packageNameIn, classNameIn, operationNameIn },
				new String[] { packageNameExpected, classNameExpected, operationNameExpected },
				hierarchyLevel);
	}

	public static void printExamples() {
		final List<String[]> examples = new ArrayList<String[]>();
		examples.add(new String[] { "", "A", "op()" });
		examples.add(new String[] { "a", "B", "op()" });
		examples.add(new String[] { "a.b", "C", "op()" });
		examples.add(new String[] { "a.b.c", "D", "op()" });
		// examples.add(new String[] { "a.b.c.d", "E", "op" });

		final int[][] ranges = { { NameUtils.ABSTRACTION_MODE_CLASS, 0 }, { 1, 3 } };

		for (final int[] curRange : ranges) {
			System.out.println("%% cur abstraction level range: " + curRange[0] + "-" + curRange[1]);
			int curDepth = 1;
			for (final String[] example : examples) {
				System.out.println("%%%%");
				final String inputFQName;
				if (example[0].isEmpty()) {
					inputFQName = example[1];
				} else {
					inputFQName = example[0] + "." + example[1];
				}
				System.out.println(inputFQName + ".op()" + " & " + curDepth + "% input name & depth ");
				for (int level = curRange[0]; level <= curRange[1]; level++) {
					System.out.print("& ");
					String cols = ""; // remains on exception
					try {
						final String[] abstractNameComponents = NameUtils.abstractFQName(example[0], example[1], example[2], level);
						final String[] abstractFQNameAndOp = new String[2];
						if (abstractNameComponents[0].isEmpty()) {
							abstractFQNameAndOp[0] = abstractNameComponents[1];
						} else {
							abstractFQNameAndOp[0] = abstractNameComponents[0] + "." + abstractNameComponents[1];
						}
						abstractFQNameAndOp[1] = abstractNameComponents[2];
						cols = StringUtils.join(abstractFQNameAndOp, ".");
					} catch (final Exception exc) {
						// leaving empty
					}
					System.out.println(cols.replaceAll("_", "\\\\_").replaceAll("UnnamedClass", "@") + " % abstraction level: " + level);
				}
				System.out.println("\\\\\\hline");
				curDepth++;
			}
		}
	}

	public static void main(final String[] args) {
		TestNameUtilsAbstraction.printExamples();
	}

	/**
	 * 
	 * @param packageNameIn
	 * @param packageNameExpected
	 * @param classNameIn
	 * @param classNameExpected
	 * @param operationNameIn
	 * @param operationNameExpected
	 */
	private void checkAbstraction(final String[] input, final String[] expectedResult, final int abstractionMode) {
		Assert.assertNotNull("input must not be null", input);
		Assert.assertNotNull("expectedResult must not be null", expectedResult);
		Assert.assertEquals("invalid length of array input", 3, input.length);
		Assert.assertEquals("invalid length of array expectedResult", 3, expectedResult.length);

		final String[] abstractedNameArray =
				NameUtils.abstractFQName(input[0], input[1], input[2], abstractionMode);
		Assert.assertNotNull("Resulting array is null", abstractedNameArray);
		Assert.assertEquals("Unexpected number of array elements", 3, abstractedNameArray.length);
		Assert.assertEquals("Unexpected package name", expectedResult[0], abstractedNameArray[0]);
		Assert.assertEquals("Unexpected class name", expectedResult[1], abstractedNameArray[1]);
		Assert.assertEquals("Unexpected operation name", expectedResult[2], abstractedNameArray[2]);
	}
}
