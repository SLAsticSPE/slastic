/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository;

import de.cau.se.slastic.metamodel.core.NamedEntity;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getParamTypes <em>Param Types</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getSignature()
 * @model
 * @generated
 */
public interface Signature extends NamedEntity {
	/**
	 * Returns the value of the '<em><b>Param Types</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Param Types</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Param Types</em>' attribute list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getSignature_ParamTypes()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getParamTypes();

	/**
	 * Returns the value of the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Return Type</em>' attribute.
	 * @see #setReturnType(String)
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getSignature_ReturnType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getReturnType();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getReturnType <em>Return Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type</em>' attribute.
	 * @see #getReturnType()
	 * @generated
	 */
	void setReturnType(String value);

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.Operation}.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getSignature_Operations()
	 * @see de.cau.se.slastic.metamodel.typeRepository.Operation#getSignature
	 * @model opposite="signature" ordered="false"
	 * @generated
	 */
	EList<Operation> getOperations();

} // Signature
