/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mem Swap Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getMemUsedBytes <em>Mem Used Bytes</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getMemFreeBytes <em>Mem Free Bytes</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getSwapUsedBytes <em>Swap Used Bytes</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getSwapFreeBytes <em>Swap Free Bytes</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getMemSwapUsage()
 * @model
 * @generated
 */
public interface MemSwapUsage extends ResourceMeasurement {
	/**
	 * Returns the value of the '<em><b>Mem Used Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mem Used Bytes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mem Used Bytes</em>' attribute.
	 * @see #setMemUsedBytes(long)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getMemSwapUsage_MemUsedBytes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getMemUsedBytes();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getMemUsedBytes <em>Mem Used Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mem Used Bytes</em>' attribute.
	 * @see #getMemUsedBytes()
	 * @generated
	 */
	void setMemUsedBytes(long value);

	/**
	 * Returns the value of the '<em><b>Mem Free Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mem Free Bytes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mem Free Bytes</em>' attribute.
	 * @see #setMemFreeBytes(long)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getMemSwapUsage_MemFreeBytes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getMemFreeBytes();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getMemFreeBytes <em>Mem Free Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mem Free Bytes</em>' attribute.
	 * @see #getMemFreeBytes()
	 * @generated
	 */
	void setMemFreeBytes(long value);

	/**
	 * Returns the value of the '<em><b>Swap Used Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swap Used Bytes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swap Used Bytes</em>' attribute.
	 * @see #setSwapUsedBytes(long)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getMemSwapUsage_SwapUsedBytes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getSwapUsedBytes();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getSwapUsedBytes <em>Swap Used Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swap Used Bytes</em>' attribute.
	 * @see #getSwapUsedBytes()
	 * @generated
	 */
	void setSwapUsedBytes(long value);

	/**
	 * Returns the value of the '<em><b>Swap Free Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swap Free Bytes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swap Free Bytes</em>' attribute.
	 * @see #setSwapFreeBytes(long)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getMemSwapUsage_SwapFreeBytes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getSwapFreeBytes();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage#getSwapFreeBytes <em>Swap Free Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swap Free Bytes</em>' attribute.
	 * @see #getSwapFreeBytes()
	 * @generated
	 */
	void setSwapFreeBytes(long value);

} // MemSwapUsage
