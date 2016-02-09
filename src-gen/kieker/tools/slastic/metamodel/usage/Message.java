/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage;

import kieker.tools.slastic.metamodel.monitoring.OperationExecution;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.Message#getSender <em>Sender</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.Message#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.Message#getReceiver <em>Receiver</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getMessage()
 * @model
 * @generated
 */
public interface Message extends EObject {
	/**
	 * Returns the value of the '<em><b>Sender</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sender</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sender</em>' reference.
	 * @see #setSender(OperationExecution)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getMessage_Sender()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	OperationExecution getSender();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.Message#getSender <em>Sender</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sender</em>' reference.
	 * @see #getSender()
	 * @generated
	 */
	void setSender(OperationExecution value);

	/**
	 * Returns the value of the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timestamp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timestamp</em>' attribute.
	 * @see #setTimestamp(long)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getMessage_Timestamp()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getTimestamp();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.Message#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(long value);

	/**
	 * Returns the value of the '<em><b>Receiver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receiver</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receiver</em>' reference.
	 * @see #setReceiver(OperationExecution)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getMessage_Receiver()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	OperationExecution getReceiver();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.Message#getReceiver <em>Receiver</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receiver</em>' reference.
	 * @see #getReceiver()
	 * @generated
	 */
	void setReceiver(OperationExecution value);

} // Message
