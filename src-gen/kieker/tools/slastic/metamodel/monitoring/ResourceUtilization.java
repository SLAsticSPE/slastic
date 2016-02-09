/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Utilization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.ResourceUtilization#getUtilization <em>Utilization</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getResourceUtilization()
 * @model
 * @generated
 */
public interface ResourceUtilization extends ResourceMeasurement {
	/**
	 * Returns the value of the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Utilization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Utilization</em>' attribute.
	 * @see #setUtilization(double)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getResourceUtilization_Utilization()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	double getUtilization();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.ResourceUtilization#getUtilization <em>Utilization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Utilization</em>' attribute.
	 * @see #getUtilization()
	 * @generated
	 */
	void setUtilization(double value);

} // ResourceUtilization
