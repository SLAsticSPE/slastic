/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.adaptation;

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
 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationFactory
 * @model kind="package"
 * @generated
 */
public interface AdaptationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "adaptation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/adaptation.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.adaptation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AdaptationPackage eINSTANCE = de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.ControlImpl <em>Control</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.ControlImpl
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getControl()
	 * @generated
	 */
	int CONTROL = 0;

	/**
	 * The feature id for the '<em><b>Adaptation Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__ADAPTATION_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Performance Evaluator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__PERFORMANCE_EVALUATOR = 1;

	/**
	 * The feature id for the '<em><b>Workload Forecaster</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__WORKLOAD_FORECASTER = 2;

	/**
	 * The feature id for the '<em><b>Performance Predictor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__PERFORMANCE_PREDICTOR = 3;

	/**
	 * The feature id for the '<em><b>Adaptation Planner</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL__ADAPTATION_PLANNER = 4;

	/**
	 * The number of structural features of the '<em>Control</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.PerformanceEvaluationImpl <em>Performance Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.PerformanceEvaluationImpl
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getPerformanceEvaluation()
	 * @generated
	 */
	int PERFORMANCE_EVALUATION = 1;

	/**
	 * The feature id for the '<em><b>Control</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_EVALUATION__CONTROL = 0;

	/**
	 * The number of structural features of the '<em>Performance Evaluation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_EVALUATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.WorkloadForecastingImpl <em>Workload Forecasting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.WorkloadForecastingImpl
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getWorkloadForecasting()
	 * @generated
	 */
	int WORKLOAD_FORECASTING = 2;

	/**
	 * The feature id for the '<em><b>Control</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_FORECASTING__CONTROL = 0;

	/**
	 * The number of structural features of the '<em>Workload Forecasting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKLOAD_FORECASTING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.PerformancePredictionImpl <em>Performance Prediction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.PerformancePredictionImpl
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getPerformancePrediction()
	 * @generated
	 */
	int PERFORMANCE_PREDICTION = 3;

	/**
	 * The feature id for the '<em><b>Control</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_PREDICTION__CONTROL = 0;

	/**
	 * The number of structural features of the '<em>Performance Prediction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERFORMANCE_PREDICTION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPlanningImpl <em>Planning</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPlanningImpl
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getAdaptationPlanning()
	 * @generated
	 */
	int ADAPTATION_PLANNING = 4;

	/**
	 * The feature id for the '<em><b>Control</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_PLANNING__CONTROL = 0;

	/**
	 * The number of structural features of the '<em>Planning</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_PLANNING_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl
	 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getAdaptationModel()
	 * @generated
	 */
	int ADAPTATION_MODEL = 5;

	/**
	 * The feature id for the '<em><b>Control Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_MODEL__CONTROL_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Qo SModel</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_MODEL__QO_SMODEL = 1;

	/**
	 * The feature id for the '<em><b>Reconfiguration Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION = 2;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTATION_MODEL_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.adaptation.Control <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.Control
	 * @generated
	 */
	EClass getControl();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.adaptation.Control#getAdaptationModel <em>Adaptation Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Adaptation Model</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.Control#getAdaptationModel()
	 * @see #getControl()
	 * @generated
	 */
	EReference getControl_AdaptationModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.adaptation.Control#getPerformanceEvaluator <em>Performance Evaluator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Performance Evaluator</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.Control#getPerformanceEvaluator()
	 * @see #getControl()
	 * @generated
	 */
	EReference getControl_PerformanceEvaluator();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.adaptation.Control#getWorkloadForecaster <em>Workload Forecaster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Workload Forecaster</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.Control#getWorkloadForecaster()
	 * @see #getControl()
	 * @generated
	 */
	EReference getControl_WorkloadForecaster();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.adaptation.Control#getPerformancePredictor <em>Performance Predictor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Performance Predictor</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.Control#getPerformancePredictor()
	 * @see #getControl()
	 * @generated
	 */
	EReference getControl_PerformancePredictor();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.adaptation.Control#getAdaptationPlanner <em>Adaptation Planner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Adaptation Planner</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.Control#getAdaptationPlanner()
	 * @see #getControl()
	 * @generated
	 */
	EReference getControl_AdaptationPlanner();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.adaptation.PerformanceEvaluation <em>Performance Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Performance Evaluation</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.PerformanceEvaluation
	 * @generated
	 */
	EClass getPerformanceEvaluation();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.adaptation.PerformanceEvaluation#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Control</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.PerformanceEvaluation#getControl()
	 * @see #getPerformanceEvaluation()
	 * @generated
	 */
	EReference getPerformanceEvaluation_Control();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.adaptation.WorkloadForecasting <em>Workload Forecasting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workload Forecasting</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.WorkloadForecasting
	 * @generated
	 */
	EClass getWorkloadForecasting();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.adaptation.WorkloadForecasting#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Control</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.WorkloadForecasting#getControl()
	 * @see #getWorkloadForecasting()
	 * @generated
	 */
	EReference getWorkloadForecasting_Control();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.adaptation.PerformancePrediction <em>Performance Prediction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Performance Prediction</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.PerformancePrediction
	 * @generated
	 */
	EClass getPerformancePrediction();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.adaptation.PerformancePrediction#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Control</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.PerformancePrediction#getControl()
	 * @see #getPerformancePrediction()
	 * @generated
	 */
	EReference getPerformancePrediction_Control();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationPlanning <em>Planning</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Planning</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationPlanning
	 * @generated
	 */
	EClass getAdaptationPlanning();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationPlanning#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Control</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationPlanning#getControl()
	 * @see #getAdaptationPlanning()
	 * @generated
	 */
	EReference getAdaptationPlanning_Control();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationModel
	 * @generated
	 */
	EClass getAdaptationModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getControlModel <em>Control Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Control Model</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getControlModel()
	 * @see #getAdaptationModel()
	 * @generated
	 */
	EReference getAdaptationModel_ControlModel();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getQoSModel <em>Qo SModel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Qo SModel</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getQoSModel()
	 * @see #getAdaptationModel()
	 * @generated
	 */
	EReference getAdaptationModel_QoSModel();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getReconfigurationSpecification <em>Reconfiguration Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Reconfiguration Specification</em>'.
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getReconfigurationSpecification()
	 * @see #getAdaptationModel()
	 * @generated
	 */
	EReference getAdaptationModel_ReconfigurationSpecification();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AdaptationFactory getAdaptationFactory();

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
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.ControlImpl <em>Control</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.ControlImpl
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getControl()
		 * @generated
		 */
		EClass CONTROL = eINSTANCE.getControl();

		/**
		 * The meta object literal for the '<em><b>Adaptation Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL__ADAPTATION_MODEL = eINSTANCE.getControl_AdaptationModel();

		/**
		 * The meta object literal for the '<em><b>Performance Evaluator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL__PERFORMANCE_EVALUATOR = eINSTANCE.getControl_PerformanceEvaluator();

		/**
		 * The meta object literal for the '<em><b>Workload Forecaster</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL__WORKLOAD_FORECASTER = eINSTANCE.getControl_WorkloadForecaster();

		/**
		 * The meta object literal for the '<em><b>Performance Predictor</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL__PERFORMANCE_PREDICTOR = eINSTANCE.getControl_PerformancePredictor();

		/**
		 * The meta object literal for the '<em><b>Adaptation Planner</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL__ADAPTATION_PLANNER = eINSTANCE.getControl_AdaptationPlanner();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.PerformanceEvaluationImpl <em>Performance Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.PerformanceEvaluationImpl
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getPerformanceEvaluation()
		 * @generated
		 */
		EClass PERFORMANCE_EVALUATION = eINSTANCE.getPerformanceEvaluation();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERFORMANCE_EVALUATION__CONTROL = eINSTANCE.getPerformanceEvaluation_Control();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.WorkloadForecastingImpl <em>Workload Forecasting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.WorkloadForecastingImpl
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getWorkloadForecasting()
		 * @generated
		 */
		EClass WORKLOAD_FORECASTING = eINSTANCE.getWorkloadForecasting();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKLOAD_FORECASTING__CONTROL = eINSTANCE.getWorkloadForecasting_Control();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.PerformancePredictionImpl <em>Performance Prediction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.PerformancePredictionImpl
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getPerformancePrediction()
		 * @generated
		 */
		EClass PERFORMANCE_PREDICTION = eINSTANCE.getPerformancePrediction();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERFORMANCE_PREDICTION__CONTROL = eINSTANCE.getPerformancePrediction_Control();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPlanningImpl <em>Planning</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPlanningImpl
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getAdaptationPlanning()
		 * @generated
		 */
		EClass ADAPTATION_PLANNING = eINSTANCE.getAdaptationPlanning();

		/**
		 * The meta object literal for the '<em><b>Control</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_PLANNING__CONTROL = eINSTANCE.getAdaptationPlanning_Control();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl
		 * @see de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl#getAdaptationModel()
		 * @generated
		 */
		EClass ADAPTATION_MODEL = eINSTANCE.getAdaptationModel();

		/**
		 * The meta object literal for the '<em><b>Control Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_MODEL__CONTROL_MODEL = eINSTANCE.getAdaptationModel_ControlModel();

		/**
		 * The meta object literal for the '<em><b>Qo SModel</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_MODEL__QO_SMODEL = eINSTANCE.getAdaptationModel_QoSModel();

		/**
		 * The meta object literal for the '<em><b>Reconfiguration Specification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION = eINSTANCE.getAdaptationModel_ReconfigurationSpecification();

	}

} //AdaptationPackage
