/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.qos;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qo SObservations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.QoSObservations#getQoSModel <em>Qo SModel</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.qos.QosPackage#getQoSObservations()
 * @model
 * @generated
 */
public interface QoSObservations extends EObject {
	/**
	 * Returns the value of the '<em><b>Qo SModel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.qos.QoSModel#getQosObservations <em>Qos Observations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qo SModel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qo SModel</em>' container reference.
	 * @see #setQoSModel(QoSModel)
	 * @see kieker.tools.slastic.metamodel.qos.QosPackage#getQoSObservations_QoSModel()
	 * @see kieker.tools.slastic.metamodel.qos.QoSModel#getQosObservations
	 * @model opposite="qosObservations" required="true" transient="false" ordered="false"
	 * @generated
	 */
	QoSModel getQoSModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.qos.QoSObservations#getQoSModel <em>Qo SModel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qo SModel</em>' container reference.
	 * @see #getQoSModel()
	 * @generated
	 */
	void setQoSModel(QoSModel value);

} // QoSObservations
