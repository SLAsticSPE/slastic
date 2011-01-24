/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.qos;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.qos.QosFactory
 * @model kind="package"
 * @generated
 */
public interface QosPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "qos";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/qos.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.qos";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QosPackage eINSTANCE = de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSCharacteristicsImpl <em>Qo SCharacteristics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.qos.impl.QoSCharacteristicsImpl
	 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSCharacteristics()
	 * @generated
	 */
	int QO_SCHARACTERISTICS = 0;

	/**
	 * The feature id for the '<em><b>Qos Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SCHARACTERISTICS__QOS_MODEL = 0;

	/**
	 * The number of structural features of the '<em>Qo SCharacteristics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SCHARACTERISTICS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSModelImpl <em>Qo SModel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.qos.impl.QoSModelImpl
	 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSModel()
	 * @generated
	 */
	int QO_SMODEL = 1;

	/**
	 * The feature id for the '<em><b>System Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL__SYSTEM_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Qos Constraints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL__QOS_CONSTRAINTS = 1;

	/**
	 * The feature id for the '<em><b>Qos Instrumentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL__QOS_INSTRUMENTATION = 2;

	/**
	 * The feature id for the '<em><b>Qos Observations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL__QOS_OBSERVATIONS = 3;

	/**
	 * The feature id for the '<em><b>Cost Profile</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL__COST_PROFILE = 4;

	/**
	 * The feature id for the '<em><b>Qos Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL__QOS_CHARACTERISTICS = 5;

	/**
	 * The number of structural features of the '<em>Qo SModel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SMODEL_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSConstraintsImpl <em>Qo SConstraints</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.qos.impl.QoSConstraintsImpl
	 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSConstraints()
	 * @generated
	 */
	int QO_SCONSTRAINTS = 2;

	/**
	 * The feature id for the '<em><b>Qo SModel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SCONSTRAINTS__QO_SMODEL = 0;

	/**
	 * The number of structural features of the '<em>Qo SConstraints</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SCONSTRAINTS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSInstrumentationImpl <em>Qo SInstrumentation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.qos.impl.QoSInstrumentationImpl
	 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSInstrumentation()
	 * @generated
	 */
	int QO_SINSTRUMENTATION = 3;

	/**
	 * The feature id for the '<em><b>Qo SModel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SINSTRUMENTATION__QO_SMODEL = 0;

	/**
	 * The number of structural features of the '<em>Qo SInstrumentation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SINSTRUMENTATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSObservationsImpl <em>Qo SObservations</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.qos.impl.QoSObservationsImpl
	 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSObservations()
	 * @generated
	 */
	int QO_SOBSERVATIONS = 4;

	/**
	 * The feature id for the '<em><b>Qo SModel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SOBSERVATIONS__QO_SMODEL = 0;

	/**
	 * The number of structural features of the '<em>Qo SObservations</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QO_SOBSERVATIONS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.qos.impl.CostProfileImpl <em>Cost Profile</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.qos.impl.CostProfileImpl
	 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getCostProfile()
	 * @generated
	 */
	int COST_PROFILE = 5;

	/**
	 * The feature id for the '<em><b>Qo SModel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COST_PROFILE__QO_SMODEL = 0;

	/**
	 * The number of structural features of the '<em>Cost Profile</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COST_PROFILE_FEATURE_COUNT = 1;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.qos.QoSCharacteristics <em>Qo SCharacteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qo SCharacteristics</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSCharacteristics
	 * @generated
	 */
	EClass getQoSCharacteristics();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.qos.QoSCharacteristics#getQosModel <em>Qos Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Qos Model</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSCharacteristics#getQosModel()
	 * @see #getQoSCharacteristics()
	 * @generated
	 */
	EReference getQoSCharacteristics_QosModel();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.qos.QoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qo SModel</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel
	 * @generated
	 */
	EClass getQoSModel();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getSystemModel <em>System Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>System Model</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel#getSystemModel()
	 * @see #getQoSModel()
	 * @generated
	 */
	EReference getQoSModel_SystemModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosConstraints <em>Qos Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qos Constraints</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel#getQosConstraints()
	 * @see #getQoSModel()
	 * @generated
	 */
	EReference getQoSModel_QosConstraints();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosInstrumentation <em>Qos Instrumentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qos Instrumentation</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel#getQosInstrumentation()
	 * @see #getQoSModel()
	 * @generated
	 */
	EReference getQoSModel_QosInstrumentation();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosObservations <em>Qos Observations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qos Observations</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel#getQosObservations()
	 * @see #getQoSModel()
	 * @generated
	 */
	EReference getQoSModel_QosObservations();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getCostProfile <em>Cost Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cost Profile</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel#getCostProfile()
	 * @see #getQoSModel()
	 * @generated
	 */
	EReference getQoSModel_CostProfile();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.qos.QoSModel#getQosCharacteristics <em>Qos Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Qos Characteristics</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSModel#getQosCharacteristics()
	 * @see #getQoSModel()
	 * @generated
	 */
	EReference getQoSModel_QosCharacteristics();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.qos.QoSConstraints <em>Qo SConstraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qo SConstraints</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSConstraints
	 * @generated
	 */
	EClass getQoSConstraints();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.qos.QoSConstraints#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Qo SModel</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSConstraints#getQoSModel()
	 * @see #getQoSConstraints()
	 * @generated
	 */
	EReference getQoSConstraints_QoSModel();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.qos.QoSInstrumentation <em>Qo SInstrumentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qo SInstrumentation</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSInstrumentation
	 * @generated
	 */
	EClass getQoSInstrumentation();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.qos.QoSInstrumentation#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Qo SModel</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSInstrumentation#getQoSModel()
	 * @see #getQoSInstrumentation()
	 * @generated
	 */
	EReference getQoSInstrumentation_QoSModel();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.qos.QoSObservations <em>Qo SObservations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qo SObservations</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSObservations
	 * @generated
	 */
	EClass getQoSObservations();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.qos.QoSObservations#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Qo SModel</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.QoSObservations#getQoSModel()
	 * @see #getQoSObservations()
	 * @generated
	 */
	EReference getQoSObservations_QoSModel();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.qos.CostProfile <em>Cost Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cost Profile</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.CostProfile
	 * @generated
	 */
	EClass getCostProfile();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.qos.CostProfile#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Qo SModel</em>'.
	 * @see de.cau.se.slastic.metamodel.qos.CostProfile#getQoSModel()
	 * @see #getCostProfile()
	 * @generated
	 */
	EReference getCostProfile_QoSModel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QosFactory getQosFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSCharacteristicsImpl <em>Qo SCharacteristics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.qos.impl.QoSCharacteristicsImpl
		 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSCharacteristics()
		 * @generated
		 */
		EClass QO_SCHARACTERISTICS = eINSTANCE.getQoSCharacteristics();

		/**
		 * The meta object literal for the '<em><b>Qos Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SCHARACTERISTICS__QOS_MODEL = eINSTANCE.getQoSCharacteristics_QosModel();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSModelImpl <em>Qo SModel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.qos.impl.QoSModelImpl
		 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSModel()
		 * @generated
		 */
		EClass QO_SMODEL = eINSTANCE.getQoSModel();

		/**
		 * The meta object literal for the '<em><b>System Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SMODEL__SYSTEM_MODEL = eINSTANCE.getQoSModel_SystemModel();

		/**
		 * The meta object literal for the '<em><b>Qos Constraints</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SMODEL__QOS_CONSTRAINTS = eINSTANCE.getQoSModel_QosConstraints();

		/**
		 * The meta object literal for the '<em><b>Qos Instrumentation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SMODEL__QOS_INSTRUMENTATION = eINSTANCE.getQoSModel_QosInstrumentation();

		/**
		 * The meta object literal for the '<em><b>Qos Observations</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SMODEL__QOS_OBSERVATIONS = eINSTANCE.getQoSModel_QosObservations();

		/**
		 * The meta object literal for the '<em><b>Cost Profile</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SMODEL__COST_PROFILE = eINSTANCE.getQoSModel_CostProfile();

		/**
		 * The meta object literal for the '<em><b>Qos Characteristics</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SMODEL__QOS_CHARACTERISTICS = eINSTANCE.getQoSModel_QosCharacteristics();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSConstraintsImpl <em>Qo SConstraints</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.qos.impl.QoSConstraintsImpl
		 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSConstraints()
		 * @generated
		 */
		EClass QO_SCONSTRAINTS = eINSTANCE.getQoSConstraints();

		/**
		 * The meta object literal for the '<em><b>Qo SModel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SCONSTRAINTS__QO_SMODEL = eINSTANCE.getQoSConstraints_QoSModel();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSInstrumentationImpl <em>Qo SInstrumentation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.qos.impl.QoSInstrumentationImpl
		 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSInstrumentation()
		 * @generated
		 */
		EClass QO_SINSTRUMENTATION = eINSTANCE.getQoSInstrumentation();

		/**
		 * The meta object literal for the '<em><b>Qo SModel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SINSTRUMENTATION__QO_SMODEL = eINSTANCE.getQoSInstrumentation_QoSModel();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.qos.impl.QoSObservationsImpl <em>Qo SObservations</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.qos.impl.QoSObservationsImpl
		 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getQoSObservations()
		 * @generated
		 */
		EClass QO_SOBSERVATIONS = eINSTANCE.getQoSObservations();

		/**
		 * The meta object literal for the '<em><b>Qo SModel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QO_SOBSERVATIONS__QO_SMODEL = eINSTANCE.getQoSObservations_QoSModel();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.qos.impl.CostProfileImpl <em>Cost Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.qos.impl.CostProfileImpl
		 * @see de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl#getCostProfile()
		 * @generated
		 */
		EClass COST_PROFILE = eINSTANCE.getCostProfile();

		/**
		 * The meta object literal for the '<em><b>Qo SModel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COST_PROFILE__QO_SMODEL = eINSTANCE.getCostProfile_QoSModel();

	}

} //QosPackage
