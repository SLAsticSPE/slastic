/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Valid Execution Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.ValidExecutionTrace#getMessageTrace <em>Message Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getValidExecutionTrace()
 * @model
 * @generated
 */
public interface ValidExecutionTrace extends ExecutionTrace, ValidTrace {
	/**
	 * Returns the value of the '<em><b>Message Trace</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getExecutionTrace <em>Execution Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Trace</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Trace</em>' reference.
	 * @see #setMessageTrace(MessageTrace)
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getValidExecutionTrace_MessageTrace()
	 * @see de.cau.se.slastic.metamodel.usage.MessageTrace#getExecutionTrace
	 * @model opposite="executionTrace" required="true" ordered="false"
	 * @generated
	 */
	MessageTrace getMessageTrace();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.ValidExecutionTrace#getMessageTrace <em>Message Trace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Trace</em>' reference.
	 * @see #getMessageTrace()
	 * @generated
	 */
	void setMessageTrace(MessageTrace value);

} // ValidExecutionTrace
