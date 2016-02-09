/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository;

import kieker.tools.slastic.metamodel.core.Entity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getComponentType <em>Component Type</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends Entity {
	/**
	 * Returns the value of the '<em><b>Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' containment reference.
	 * @see #setSignature(Signature)
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getOperation_Signature()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	Signature getSignature();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' containment reference.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(Signature value);

	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Type</em>' container reference.
	 * @see #setComponentType(ComponentType)
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getOperation_ComponentType()
	 * @see kieker.tools.slastic.metamodel.typeRepository.ComponentType#getOperations
	 * @model opposite="operations" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ComponentType getComponentType();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getComponentType <em>Component Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' container reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(ComponentType value);

} // Operation
