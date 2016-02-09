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
 * A representation of the model object '<em><b>Qo SCharacteristics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.QoSCharacteristics#getQosModel <em>Qos Model</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.qos.QosPackage#getQoSCharacteristics()
 * @model
 * @generated
 */
public interface QoSCharacteristics extends EObject {
	/**
	 * Returns the value of the '<em><b>Qos Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.qos.QoSModel#getQosCharacteristics <em>Qos Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qos Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qos Model</em>' container reference.
	 * @see #setQosModel(QoSModel)
	 * @see kieker.tools.slastic.metamodel.qos.QosPackage#getQoSCharacteristics_QosModel()
	 * @see kieker.tools.slastic.metamodel.qos.QoSModel#getQosCharacteristics
	 * @model opposite="qosCharacteristics" required="true" transient="false" ordered="false"
	 * @generated
	 */
	QoSModel getQosModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.qos.QoSCharacteristics#getQosModel <em>Qos Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qos Model</em>' container reference.
	 * @see #getQosModel()
	 * @generated
	 */
	void setQosModel(QoSModel value);

} // QoSCharacteristics
