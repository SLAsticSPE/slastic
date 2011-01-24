/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CPU Utilization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getUser <em>User</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getSystem <em>System</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getWait <em>Wait</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getNice <em>Nice</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIrq <em>Irq</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getCombined <em>Combined</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIdle <em>Idle</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization()
 * @model
 * @generated
 */
public interface CPUUtilization extends ResourceMeasurement {
	/**
	 * Returns the value of the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' attribute.
	 * @see #setUser(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_User()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getUser();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getUser <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' attribute.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(double value);

	/**
	 * Returns the value of the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System</em>' attribute.
	 * @see #setSystem(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_System()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getSystem();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getSystem <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System</em>' attribute.
	 * @see #getSystem()
	 * @generated
	 */
	void setSystem(double value);

	/**
	 * Returns the value of the '<em><b>Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wait</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wait</em>' attribute.
	 * @see #setWait(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_Wait()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getWait();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getWait <em>Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait</em>' attribute.
	 * @see #getWait()
	 * @generated
	 */
	void setWait(double value);

	/**
	 * Returns the value of the '<em><b>Nice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nice</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nice</em>' attribute.
	 * @see #setNice(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_Nice()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getNice();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getNice <em>Nice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nice</em>' attribute.
	 * @see #getNice()
	 * @generated
	 */
	void setNice(double value);

	/**
	 * Returns the value of the '<em><b>Irq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Irq</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Irq</em>' attribute.
	 * @see #setIrq(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_Irq()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getIrq();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIrq <em>Irq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Irq</em>' attribute.
	 * @see #getIrq()
	 * @generated
	 */
	void setIrq(double value);

	/**
	 * Returns the value of the '<em><b>Combined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Combined</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Combined</em>' attribute.
	 * @see #setCombined(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_Combined()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getCombined();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getCombined <em>Combined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Combined</em>' attribute.
	 * @see #getCombined()
	 * @generated
	 */
	void setCombined(double value);

	/**
	 * Returns the value of the '<em><b>Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idle</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Idle</em>' attribute.
	 * @see #setIdle(double)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getCPUUtilization_Idle()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getIdle();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIdle <em>Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Idle</em>' attribute.
	 * @see #getIdle()
	 * @generated
	 */
	void setIdle(double value);

} // CPUUtilization
