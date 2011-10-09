/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Valid Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.ValidTrace#getTraceId <em>Trace Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getValidTrace()
 * @model abstract="true"
 * @generated
 */
public interface ValidTrace extends Trace {
	/**
	 * Returns the value of the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trace Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace Id</em>' attribute.
	 * @see #setTraceId(long)
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getValidTrace_TraceId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getTraceId();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.ValidTrace#getTraceId <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace Id</em>' attribute.
	 * @see #getTraceId()
	 * @generated
	 */
	void setTraceId(long value);

} // ValidTrace
