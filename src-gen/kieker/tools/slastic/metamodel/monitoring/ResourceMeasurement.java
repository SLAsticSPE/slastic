/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring;

import kieker.tools.slastic.metamodel.core.IEvent;

import kieker.tools.slastic.metamodel.executionEnvironment.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.ResourceMeasurement#getResource <em>Resource</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.ResourceMeasurement#getTimestamp <em>Timestamp</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getResourceMeasurement()
 * @model abstract="true"
 * @generated
 */
public interface ResourceMeasurement extends IEvent {
	/**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(Resource)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getResourceMeasurement_Resource()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Resource getResource();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.ResourceMeasurement#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(Resource value);

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
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getResourceMeasurement_Timestamp()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getTimestamp();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.ResourceMeasurement#getTimestamp <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timestamp</em>' attribute.
	 * @see #getTimestamp()
	 * @generated
	 */
	void setTimestamp(long value);

} // ResourceMeasurement
