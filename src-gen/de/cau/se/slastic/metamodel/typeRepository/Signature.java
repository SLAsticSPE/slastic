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
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getOperation <em>Operation</em>}</li>
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
	 * @model unique="false" ordered="false"
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
	 * Returns the value of the '<em><b>Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' container reference.
	 * @see #setOperation(Operation)
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getSignature_Operation()
	 * @see de.cau.se.slastic.metamodel.typeRepository.Operation#getSignature
	 * @model opposite="signature" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getOperation <em>Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' container reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

} // Signature
