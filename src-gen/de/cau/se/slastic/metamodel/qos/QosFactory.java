/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.qos;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.qos.QosPackage
 * @generated
 */
public interface QosFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QosFactory eINSTANCE = de.cau.se.slastic.metamodel.qos.impl.QosFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Qo SCharacteristics</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qo SCharacteristics</em>'.
	 * @generated
	 */
	QoSCharacteristics createQoSCharacteristics();

	/**
	 * Returns a new object of class '<em>Qo SModel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qo SModel</em>'.
	 * @generated
	 */
	QoSModel createQoSModel();

	/**
	 * Returns a new object of class '<em>Qo SConstraints</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qo SConstraints</em>'.
	 * @generated
	 */
	QoSConstraints createQoSConstraints();

	/**
	 * Returns a new object of class '<em>Qo SInstrumentation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qo SInstrumentation</em>'.
	 * @generated
	 */
	QoSInstrumentation createQoSInstrumentation();

	/**
	 * Returns a new object of class '<em>Qo SObservations</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qo SObservations</em>'.
	 * @generated
	 */
	QoSObservations createQoSObservations();

	/**
	 * Returns a new object of class '<em>Cost Profile</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cost Profile</em>'.
	 * @generated
	 */
	CostProfile createCostProfile();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	QosPackage getQosPackage();

} //QosFactory
