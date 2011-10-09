/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getMessages <em>Messages</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getExecutionTrace <em>Execution Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getMessageTrace()
 * @model
 * @generated
 */
public interface MessageTrace extends ValidTrace {
	/**
	 * Returns the value of the '<em><b>Messages</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.usage.Message}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messages</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messages</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getMessageTrace_Messages()
	 * @model lower="2" ordered="false"
	 * @generated
	 */
	EList<Message> getMessages();

	/**
	 * Returns the value of the '<em><b>Execution Trace</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.usage.ValidExecutionTrace#getMessageTrace <em>Message Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Trace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Trace</em>' reference.
	 * @see #setExecutionTrace(ValidExecutionTrace)
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getMessageTrace_ExecutionTrace()
	 * @see de.cau.se.slastic.metamodel.usage.ValidExecutionTrace#getMessageTrace
	 * @model opposite="messageTrace" required="true" ordered="false"
	 * @generated
	 */
	ValidExecutionTrace getExecutionTrace();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getExecutionTrace <em>Execution Trace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Trace</em>' reference.
	 * @see #getExecutionTrace()
	 * @generated
	 */
	void setExecutionTrace(ValidExecutionTrace value);

} // MessageTrace
