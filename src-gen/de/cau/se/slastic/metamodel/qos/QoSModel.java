/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.qos;

import de.cau.se.slastic.metamodel.core.SystemModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qo SModel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.QoSModel#getSystemModel <em>System Model</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosConstraints <em>Qos Constraints</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosInstrumentation <em>Qos Instrumentation</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosObservations <em>Qos Observations</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.QoSModel#getCostProfile <em>Cost Profile</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosCharacteristics <em>Qos Characteristics</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel()
 * @model
 * @generated
 */
public interface QoSModel extends EObject {
	/**
	 * Returns the value of the '<em><b>System Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Model</em>' reference.
	 * @see #setSystemModel(SystemModel)
	 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel_SystemModel()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	SystemModel getSystemModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getSystemModel <em>System Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>System Model</em>' reference.
	 * @see #getSystemModel()
	 * @generated
	 */
	void setSystemModel(SystemModel value);

	/**
	 * Returns the value of the '<em><b>Qos Constraints</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.qos.QoSConstraints#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qos Constraints</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qos Constraints</em>' containment reference.
	 * @see #setQosConstraints(QoSConstraints)
	 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel_QosConstraints()
	 * @see de.cau.se.slastic.metamodel.qos.QoSConstraints#getQoSModel
	 * @model opposite="qoSModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	QoSConstraints getQosConstraints();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosConstraints <em>Qos Constraints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qos Constraints</em>' containment reference.
	 * @see #getQosConstraints()
	 * @generated
	 */
	void setQosConstraints(QoSConstraints value);

	/**
	 * Returns the value of the '<em><b>Qos Instrumentation</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.qos.QoSInstrumentation#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qos Instrumentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qos Instrumentation</em>' containment reference.
	 * @see #setQosInstrumentation(QoSInstrumentation)
	 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel_QosInstrumentation()
	 * @see de.cau.se.slastic.metamodel.qos.QoSInstrumentation#getQoSModel
	 * @model opposite="qoSModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	QoSInstrumentation getQosInstrumentation();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosInstrumentation <em>Qos Instrumentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qos Instrumentation</em>' containment reference.
	 * @see #getQosInstrumentation()
	 * @generated
	 */
	void setQosInstrumentation(QoSInstrumentation value);

	/**
	 * Returns the value of the '<em><b>Qos Observations</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.qos.QoSObservations#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qos Observations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qos Observations</em>' containment reference.
	 * @see #setQosObservations(QoSObservations)
	 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel_QosObservations()
	 * @see de.cau.se.slastic.metamodel.qos.QoSObservations#getQoSModel
	 * @model opposite="qoSModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	QoSObservations getQosObservations();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosObservations <em>Qos Observations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qos Observations</em>' containment reference.
	 * @see #getQosObservations()
	 * @generated
	 */
	void setQosObservations(QoSObservations value);

	/**
	 * Returns the value of the '<em><b>Cost Profile</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.qos.CostProfile#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cost Profile</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cost Profile</em>' containment reference.
	 * @see #setCostProfile(CostProfile)
	 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel_CostProfile()
	 * @see de.cau.se.slastic.metamodel.qos.CostProfile#getQoSModel
	 * @model opposite="qoSModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	CostProfile getCostProfile();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getCostProfile <em>Cost Profile</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cost Profile</em>' containment reference.
	 * @see #getCostProfile()
	 * @generated
	 */
	void setCostProfile(CostProfile value);

	/**
	 * Returns the value of the '<em><b>Qos Characteristics</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.qos.QoSCharacteristics#getQosModel <em>Qos Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qos Characteristics</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qos Characteristics</em>' containment reference.
	 * @see #setQosCharacteristics(QoSCharacteristics)
	 * @see de.cau.se.slastic.metamodel.qos.QosPackage#getQoSModel_QosCharacteristics()
	 * @see de.cau.se.slastic.metamodel.qos.QoSCharacteristics#getQosModel
	 * @model opposite="qosModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	QoSCharacteristics getQosCharacteristics();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosCharacteristics <em>Qos Characteristics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qos Characteristics</em>' containment reference.
	 * @see #getQosCharacteristics()
	 * @generated
	 */
	void setQosCharacteristics(QoSCharacteristics value);

} // QoSModel
