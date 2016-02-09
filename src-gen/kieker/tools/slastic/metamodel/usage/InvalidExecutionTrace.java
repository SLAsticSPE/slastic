/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Invalid Execution Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.InvalidExecutionTrace#getErrorMsg <em>Error Msg</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getInvalidExecutionTrace()
 * @model
 * @generated
 */
public interface InvalidExecutionTrace extends ExecutionTrace, InvalidTrace {
	/**
	 * Returns the value of the '<em><b>Error Msg</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Msg</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Msg</em>' attribute.
	 * @see #setErrorMsg(String)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getInvalidExecutionTrace_ErrorMsg()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getErrorMsg();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.InvalidExecutionTrace#getErrorMsg <em>Error Msg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Msg</em>' attribute.
	 * @see #getErrorMsg()
	 * @generated
	 */
	void setErrorMsg(String value);

} // InvalidExecutionTrace
