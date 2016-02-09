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
 * A representation of the model object '<em><b>Qo SConstraints</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.QoSConstraints#getQoSModel <em>Qo SModel</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.qos.QosPackage#getQoSConstraints()
 * @model
 * @generated
 */
public interface QoSConstraints extends EObject {
	/**
	 * Returns the value of the '<em><b>Qo SModel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.qos.QoSModel#getQosConstraints <em>Qos Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qo SModel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qo SModel</em>' container reference.
	 * @see #setQoSModel(QoSModel)
	 * @see kieker.tools.slastic.metamodel.qos.QosPackage#getQoSConstraints_QoSModel()
	 * @see kieker.tools.slastic.metamodel.qos.QoSModel#getQosConstraints
	 * @model opposite="qosConstraints" required="true" transient="false" ordered="false"
	 * @generated
	 */
	QoSModel getQoSModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.qos.QoSConstraints#getQoSModel <em>Qo SModel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qo SModel</em>' container reference.
	 * @see #getQoSModel()
	 * @generated
	 */
	void setQoSModel(QoSModel value);

} // QoSConstraints
