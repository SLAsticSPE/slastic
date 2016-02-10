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

import java.util.Arrays;

import junit.framework.Assert;
import junit.framework.TestCase;

import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.plugins.slasticImpl.model.util.SignatureUtils;

/**
 *
 * @author Andre van Hoorn
 *
 */
public class TestSignatureUtils extends TestCase {

	/**
	 * Tests the method {@link SignatureUtils#createSignature(String, String[], String)}.
	 */
	public void testCreateSignature() {
		final String name = "opName";
		final String[] paramTypes = { Integer.class.getName(), Boolean.class.getName() };
		final String returnType = Object.class.getName();
		final String[] modifiers = { String.class.getName() };

		final Signature signature = SignatureUtils.createSignature(name, paramTypes, returnType, modifiers);

		Assert.assertEquals("Deviating names", name, signature.getName());
		Assert.assertTrue("Deviating paramTypes", Arrays.equals(paramTypes, signature.getParamTypes().toArray()));
		Assert.assertEquals("Deviating return type", returnType, signature.getReturnType());
	}

	/**
	 * Tests the method {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two
	 * equal {@link Signature}s.
	 */
	public void testCreateSignaturesEqual_Equal() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType", new String[] { "public" });
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName(), signature0.getParamTypes().toArray(new String[] {}), signature0.getReturnType(),
						signature0.getModifiers().toArray(new String[] {}));

		Assert.assertTrue("Expected signatures to be equal", SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two {@link Signature}s differing in their name only.
	 */
	public void testCreateSignaturesEqual_NamesDiffer() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType", new String[] { "public" });
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName() + "_", signature0.getParamTypes().toArray(new String[] {}), signature0.getReturnType(),
						signature0.getModifiers().toArray(new String[] {}));

		Assert.assertFalse("Expected signatures not to be equal", SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two {@link Signature}s differing in their param types only
	 */
	public void testCreateSignaturesEqual_ParamTypesDiffer() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType", new String[] { "public" });
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName(), new String[] { "Y" } /* Differs from {"C", "D"} */, signature0.getReturnType(), signature0
						.getModifiers().toArray(new String[] {}));

		Assert.assertFalse("Expected signatures not to be equal", SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two {@link Signature}s differing in their param types only
	 */
	public void testCreateSignaturesEqual_ReturnTypesDiffer() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType", new String[] { "public" });
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName(), signature0.getParamTypes().toArray(new String[] {}), signature0.getReturnType() + "_",
						signature0.getModifiers().toArray(new String[] {}));

		Assert.assertFalse("Expected signatures not to be equal", SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method {@link SignatureUtils#signatureAsString(Signature)} with a {@link Signature} having a non-empty set of parameter types.
	 */
	public void testToString_WithParams() {
		final String name = "opName";
		final String[] paramTypes = { Integer.class.getName(), Boolean.class.getName() };
		final String returnType = Object.class.getName();
		final String[] modifiers = { String.class.getName() };

		final Signature signature = SignatureUtils.createSignature(name, paramTypes, returnType, modifiers);

		final StringBuilder expectedStringB = new StringBuilder(name);
		// Append parameter list:
		expectedStringB.append('(').append(Integer.class.getName()).append(',').append(Boolean.class.getName())
		.append(')');
		// Append return value
		expectedStringB.append(":").append(Object.class.getName());

		Assert.assertEquals("Deviating string representations", expectedStringB.toString(), SignatureUtils.signatureAsString(signature));
	}

	/**
	 * Tests the method {@link SignatureUtils#signatureAsString(Signature)} with a {@link Signature} having an empty set of parameter types.
	 */
	public void testToString_NoParams() {
		final String name = "opName";
		final String[] paramTypes = {};
		final String returnType = Object.class.getName();
		final String[] modifiers = { String.class.getName() };

		final Signature signature = SignatureUtils.createSignature(name, paramTypes, returnType, modifiers);

		final StringBuilder expectedStringB = new StringBuilder(name);
		// Append empty parameter list:
		expectedStringB.append("()");
		// Append return value
		expectedStringB.append(":").append(Object.class.getName());

		Assert.assertEquals("Deviating string representations", expectedStringB.toString(), SignatureUtils.signatureAsString(signature));
	}
}
