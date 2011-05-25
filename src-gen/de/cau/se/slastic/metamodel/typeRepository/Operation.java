/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository;

import de.cau.se.slastic.metamodel.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Operation#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Entity {
	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.typeRepository.ComponentType#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Type</em>' container reference.
	 * @see #setComponentType(ComponentType)
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getOperation_ComponentType()
	 * @see de.cau.se.slastic.metamodel.typeRepository.ComponentType#getOperations
	 * @model opposite="operations" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ComponentType getComponentType();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.typeRepository.Operation#getComponentType <em>Component Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' container reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(ComponentType value);

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.typeRepository.Signature#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' containment reference.
	 * @see #setSignature(Signature)
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getOperation_Signature()
	 * @see de.cau.se.slastic.metamodel.typeRepository.Signature#getOperation
	 * @model opposite="operation" containment="true" required="true" ordered="false"
	 * @generated
	 */
	Signature getSignature();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' containment reference.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(Signature value);

} // Operation
