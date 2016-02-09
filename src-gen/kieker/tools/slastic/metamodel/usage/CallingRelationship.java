/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage;

import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Calling Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getCalledInterface <em>Called Interface</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getCalledSignature <em>Called Signature</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getCallingOperation <em>Calling Operation</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getFrequencyDistribution <em>Frequency Distribution</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getCallingRelationship()
 * @model
 * @generated
 */
public interface CallingRelationship extends EObject {
	/**
	 * Returns the value of the '<em><b>Called Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Interface</em>' reference.
	 * @see #setCalledInterface(Interface)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getCallingRelationship_CalledInterface()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Interface getCalledInterface();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getCalledInterface <em>Called Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Interface</em>' reference.
	 * @see #getCalledInterface()
	 * @generated
	 */
	void setCalledInterface(Interface value);

	/**
	 * Returns the value of the '<em><b>Called Signature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Signature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Signature</em>' reference.
	 * @see #setCalledSignature(Signature)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getCallingRelationship_CalledSignature()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Signature getCalledSignature();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getCalledSignature <em>Called Signature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Signature</em>' reference.
	 * @see #getCalledSignature()
	 * @generated
	 */
	void setCalledSignature(Signature value);

	/**
	 * Returns the value of the '<em><b>Calling Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calling Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calling Operation</em>' reference.
	 * @see #setCallingOperation(Operation)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getCallingRelationship_CallingOperation()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Operation getCallingOperation();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getCallingOperation <em>Calling Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calling Operation</em>' reference.
	 * @see #getCallingOperation()
	 * @generated
	 */
	void setCallingOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Frequency Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frequency Distribution</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frequency Distribution</em>' containment reference.
	 * @see #setFrequencyDistribution(FrequencyDistribution)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getCallingRelationship_FrequencyDistribution()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	FrequencyDistribution getFrequencyDistribution();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.CallingRelationship#getFrequencyDistribution <em>Frequency Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency Distribution</em>' containment reference.
	 * @see #getFrequencyDistribution()
	 * @generated
	 */
	void setFrequencyDistribution(FrequencyDistribution value);

} // CallingRelationship
