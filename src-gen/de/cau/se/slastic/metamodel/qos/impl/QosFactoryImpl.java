/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.qos.impl;

import de.cau.se.slastic.metamodel.qos.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QosFactoryImpl extends EFactoryImpl implements QosFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QosFactory init() {
		try {
			QosFactory theQosFactory = (QosFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/qos.ecore"); 
			if (theQosFactory != null) {
				return theQosFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QosFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QosFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case QosPackage.QO_SCHARACTERISTICS: return createQoSCharacteristics();
			case QosPackage.QO_SMODEL: return createQoSModel();
			case QosPackage.QO_SCONSTRAINTS: return createQoSConstraints();
			case QosPackage.QO_SINSTRUMENTATION: return createQoSInstrumentation();
			case QosPackage.QO_SOBSERVATIONS: return createQoSObservations();
			case QosPackage.COST_PROFILE: return createCostProfile();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSCharacteristics createQoSCharacteristics() {
		QoSCharacteristicsImpl qoSCharacteristics = new QoSCharacteristicsImpl();
		return qoSCharacteristics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSModel createQoSModel() {
		QoSModelImpl qoSModel = new QoSModelImpl();
		return qoSModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSConstraints createQoSConstraints() {
		QoSConstraintsImpl qoSConstraints = new QoSConstraintsImpl();
		return qoSConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSInstrumentation createQoSInstrumentation() {
		QoSInstrumentationImpl qoSInstrumentation = new QoSInstrumentationImpl();
		return qoSInstrumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSObservations createQoSObservations() {
		QoSObservationsImpl qoSObservations = new QoSObservationsImpl();
		return qoSObservations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CostProfile createCostProfile() {
		CostProfileImpl costProfile = new CostProfileImpl();
		return costProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QosPackage getQosPackage() {
		return (QosPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QosPackage getPackage() {
		return QosPackage.eINSTANCE;
	}

} //QosFactoryImpl
