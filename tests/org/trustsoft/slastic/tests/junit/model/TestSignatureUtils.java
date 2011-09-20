package org.trustsoft.slastic.tests.junit.model;

import java.util.Arrays;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.model.util.SignatureUtils;

import de.cau.se.slastic.metamodel.typeRepository.Signature;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestSignatureUtils extends TestCase {

	/**
	 * Tests the method
	 * {@link SignatureUtils#createSignature(String, String[], String)}.
	 */
	public void testCreateSignature() {
		final String name = "opName";
		final String[] paramTypes = { Integer.class.getName(), Boolean.class.getName() };
		final String returnType = Object.class.getName();

		final Signature signature = SignatureUtils.createSignature(name, paramTypes, returnType);

		Assert.assertEquals("Deviating names", name, signature.getName());
		Assert.assertTrue("Deviating paramTypes", Arrays.equals(paramTypes, signature.getParamTypes().toArray()));
		Assert.assertEquals("Deviating return type", returnType, signature.getReturnType());
	}

	/**
	 * Tests the method
	 * {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two
	 * equal {@link Signature}s.
	 */
	public void testCreateSignaturesEqual_Equal() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType");
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName(), signature0.getParamTypes()
						.toArray(new String[] {}), signature0.getReturnType());

		Assert.assertTrue("Expected signatures to be equal", SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method
	 * {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two
	 * {@link Signature}s differing in their name only.
	 */
	public void testCreateSignaturesEqual_NamesDiffer() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType");
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName() + "_", signature0.getParamTypes()
						.toArray(new String[] {}), signature0.getReturnType());

		Assert.assertFalse("Expected signatures not to be equal",
				SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method
	 * {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two
	 * {@link Signature}s differing in their param types only
	 */
	public void testCreateSignaturesEqual_ParamTypesDiffer() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType");
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName(),
						new String[] { "Y" } /* Differs from {"C", "D"} */,
						signature0.getReturnType());

		Assert.assertFalse("Expected signatures not to be equal",
				SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method
	 * {@link SignatureUtils#signaturesEqual(Signature, Signature)} with two
	 * {@link Signature}s differing in their param types only
	 */
	public void testCreateSignaturesEqual_ReturnTypesDiffer() {
		final Signature signature0 = SignatureUtils.createSignature("a", new String[] { "C", "D" }, "RetType");
		final Signature signature1 =
				SignatureUtils.createSignature(signature0.getName(), signature0.getParamTypes()
						.toArray(new String[] {}), signature0.getReturnType() + "_");

		Assert.assertFalse("Expected signatures not to be equal",
				SignatureUtils.signaturesEqual(signature0, signature1));
	}

	/**
	 * Tests the method
	 * {@link SignatureUtils#createSignature(String, String[], String)}.
	 */
	public void testToString_WithParams() {
		final String name = "opName";
		final String[] paramTypes = { Integer.class.getName(), Boolean.class.getName() };
		final String returnType = Object.class.getName();

		final Signature signature = SignatureUtils.createSignature(name, paramTypes, returnType);

		final StringBuilder expectedStringB = new StringBuilder(name);
		// Append parameter list:
		expectedStringB.append('(').append(Integer.class.getName()).append(',').append(Boolean.class.getName())
				.append(')');
		// Append return value
		expectedStringB.append(":").append(Object.class.getName());

		Assert.assertEquals("Deviating string representations", expectedStringB.toString(),
				SignatureUtils.signatureAsString(signature));
	}
	
	/**
	 * Tests the method
	 * {@link SignatureUtils#createSignature(String, String[], String)}.
	 */
	public void testToString_NoParams() {
		final String name = "opName";
		final String[] paramTypes = { };
		final String returnType = Object.class.getName();

		final Signature signature = SignatureUtils.createSignature(name, paramTypes, returnType);

		final StringBuilder expectedStringB = new StringBuilder(name);
		// Append empty parameter list:
		expectedStringB.append("()");
		// Append return value
		expectedStringB.append(":").append(Object.class.getName());

		Assert.assertEquals("Deviating string representations", expectedStringB.toString(),
				SignatureUtils.signatureAsString(signature));
	}
}
