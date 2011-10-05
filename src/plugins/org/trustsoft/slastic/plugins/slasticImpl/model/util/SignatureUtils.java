package org.trustsoft.slastic.plugins.slasticImpl.model.util;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryFactory;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class SignatureUtils {
	/**
	 * Currently, IDs make little sense for {@link Signature}s. 
	 * Hence, we assign 0 to all of them.
	 */
	private final static long SIGNATURE_ID = 0;
	
	/**
	 * Creates a new {@link Signature} with the given name, parameter types and return type.
	 * 
	 * @param name
	 * @param paramTypes
	 * @param returnType
	 * @return
	 */
	public static Signature createSignature (final String name, final String[] paramTypes, final String returnType){
		// Create and assign the Signature of this Operation
		final Signature signature =
				TypeRepositoryFactory.eINSTANCE.createSignature();
		signature.setId(SignatureUtils.SIGNATURE_ID);
		signature.setName(name);
		signature.setReturnType(returnType);

		for (final String paramType : paramTypes) {
			signature.getParamTypes().add(paramType);
		}
		return signature;
	}
	
	/**
	 * Returns a String representation of the given {@link Signature}.
	 * 
	 * Examples: 
	 * <ul>
	 *  <li>methodA():void</li>
	 *  <li>methodB(Integer,boolean):boolean</li>
	 * </ul>
	 * 
	 * @param signature
	 * @return
	 */
	public static String signatureAsString (final Signature signature) {
		final StringBuilder strB = new StringBuilder(signature.getName()).append('(');
		strB.append(StringUtils.join(signature.getParamTypes(), ','));
		strB.append("):").append(signature.getReturnType());
		return strB.toString();
	}
	
	/**
	 * Returns whether the given {@link Signature}s are equal.
	 * 
	 * @param signature1
	 * @param signature2
	 * @return
	 */
	public static boolean signaturesEqual (final Signature signature1, final Signature signature2) {
		if (signature1 == signature2) {
			return true;
		}
		
		// compare operation name
		if (!signature1.getName().equals(signature2.getName())) {
			return false;
		}
		// compare return type
		if (!signature1.getReturnType().equals(signature2.getReturnType())) {
			return false;
		}
		// compare argument types
		if (!Arrays.equals(signature1.getParamTypes().toArray(), signature2.getParamTypes().toArray())) {
			return false;
		}
		
		return true;
	}
}