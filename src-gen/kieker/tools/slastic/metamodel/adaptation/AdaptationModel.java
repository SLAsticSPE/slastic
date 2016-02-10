/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.adaptation;

import kieker.tools.slastic.metamodel.qos.QoSModel;

import kieker.tools.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getControlModel <em>Control Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getQoSModel <em>Qo SModel</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getReconfigurationSpecification <em>Reconfiguration Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getAdaptationModel()
 * @model
 * @generated
 */
public interface AdaptationModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Control Model</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.Control#getAdaptationModel <em>Adaptation Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control Model</em>' containment reference.
	 * @see #setControlModel(Control)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getAdaptationModel_ControlModel()
	 * @see kieker.tools.slastic.metamodel.adaptation.Control#getAdaptationModel
	 * @model opposite="adaptationModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	Control getControlModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getControlModel <em>Control Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control Model</em>' containment reference.
	 * @see #getControlModel()
	 * @generated
	 */
	void setControlModel(Control value);

	/**
	 * Returns the value of the '<em><b>Qo SModel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qo SModel</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qo SModel</em>' reference.
	 * @see #setQoSModel(QoSModel)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getAdaptationModel_QoSModel()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	QoSModel getQoSModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getQoSModel <em>Qo SModel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qo SModel</em>' reference.
	 * @see #getQoSModel()
	 * @generated
	 */
	void setQoSModel(QoSModel value);

	/**
	 * Returns the value of the '<em><b>Reconfiguration Specification</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification#getAdaptationModel <em>Adaptation Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reconfiguration Specification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reconfiguration Specification</em>' containment reference.
	 * @see #setReconfigurationSpecification(ReconfigurationSpecification)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getAdaptationModel_ReconfigurationSpecification()
	 * @see kieker.tools.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification#getAdaptationModel
	 * @model opposite="adaptationModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	ReconfigurationSpecification getReconfigurationSpecification();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getReconfigurationSpecification <em>Reconfiguration Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reconfiguration Specification</em>' containment reference.
	 * @see #getReconfigurationSpecification()
	 * @generated
	 */
	void setReconfigurationSpecification(ReconfigurationSpecification value);

} // AdaptationModel
