/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getMessages <em>Messages</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getMessageTrace()
 * @model
 * @generated
 */
public interface MessageTrace extends ValidTrace {
	/**
	 * Returns the value of the '<em><b>Messages</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messages</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messages</em>' reference.
	 * @see #setMessages(Message)
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getMessageTrace_Messages()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Message getMessages();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getMessages <em>Messages</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Messages</em>' reference.
	 * @see #getMessages()
	 * @generated
	 */
	void setMessages(Message value);

} // MessageTrace
