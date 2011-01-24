/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring;

import de.cau.se.slastic.metamodel.core.IEvent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEoi <em>Eoi</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEss <em>Ess</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTin <em>Tin</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTout <em>Tout</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getSessionId <em>Session Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution()
 * @model abstract="true"
 * @generated
 */
public interface OperationExecution extends IEvent {
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
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution_TraceId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getTraceId();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTraceId <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace Id</em>' attribute.
	 * @see #getTraceId()
	 * @generated
	 */
	void setTraceId(long value);

	/**
	 * Returns the value of the '<em><b>Eoi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eoi</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eoi</em>' attribute.
	 * @see #setEoi(int)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution_Eoi()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getEoi();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEoi <em>Eoi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eoi</em>' attribute.
	 * @see #getEoi()
	 * @generated
	 */
	void setEoi(int value);

	/**
	 * Returns the value of the '<em><b>Ess</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ess</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ess</em>' attribute.
	 * @see #setEss(int)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution_Ess()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	int getEss();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEss <em>Ess</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ess</em>' attribute.
	 * @see #getEss()
	 * @generated
	 */
	void setEss(int value);

	/**
	 * Returns the value of the '<em><b>Tin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tin</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tin</em>' attribute.
	 * @see #setTin(long)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution_Tin()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getTin();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTin <em>Tin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tin</em>' attribute.
	 * @see #getTin()
	 * @generated
	 */
	void setTin(long value);

	/**
	 * Returns the value of the '<em><b>Tout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tout</em>' attribute.
	 * @see #setTout(long)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution_Tout()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getTout();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTout <em>Tout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tout</em>' attribute.
	 * @see #getTout()
	 * @generated
	 */
	void setTout(long value);

	/**
	 * Returns the value of the '<em><b>Session Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Session Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Session Id</em>' attribute.
	 * @see #setSessionId(String)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getOperationExecution_SessionId()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getSessionId();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getSessionId <em>Session Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Session Id</em>' attribute.
	 * @see #getSessionId()
	 * @generated
	 */
	void setSessionId(String value);

} // OperationExecution
