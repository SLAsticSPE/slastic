/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.adaptation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Control</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.Control#getAdaptationModel <em>Adaptation Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.Control#getPerformanceEvaluator <em>Performance Evaluator</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.Control#getWorkloadForecaster <em>Workload Forecaster</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.Control#getPerformancePredictor <em>Performance Predictor</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.Control#getAdaptationPlanner <em>Adaptation Planner</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getControl()
 * @model
 * @generated
 */
public interface Control extends EObject {
	/**
	 * Returns the value of the '<em><b>Adaptation Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getControlModel <em>Control Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adaptation Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adaptation Model</em>' container reference.
	 * @see #setAdaptationModel(AdaptationModel)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getControl_AdaptationModel()
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationModel#getControlModel
	 * @model opposite="controlModel" required="true" transient="false" ordered="false"
	 * @generated
	 */
	AdaptationModel getAdaptationModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.Control#getAdaptationModel <em>Adaptation Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adaptation Model</em>' container reference.
	 * @see #getAdaptationModel()
	 * @generated
	 */
	void setAdaptationModel(AdaptationModel value);

	/**
	 * Returns the value of the '<em><b>Performance Evaluator</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.PerformanceEvaluation#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performance Evaluator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performance Evaluator</em>' containment reference.
	 * @see #setPerformanceEvaluator(PerformanceEvaluation)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getControl_PerformanceEvaluator()
	 * @see kieker.tools.slastic.metamodel.adaptation.PerformanceEvaluation#getControl
	 * @model opposite="control" containment="true" required="true" ordered="false"
	 * @generated
	 */
	PerformanceEvaluation getPerformanceEvaluator();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.Control#getPerformanceEvaluator <em>Performance Evaluator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performance Evaluator</em>' containment reference.
	 * @see #getPerformanceEvaluator()
	 * @generated
	 */
	void setPerformanceEvaluator(PerformanceEvaluation value);

	/**
	 * Returns the value of the '<em><b>Workload Forecaster</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.WorkloadForecasting#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workload Forecaster</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workload Forecaster</em>' containment reference.
	 * @see #setWorkloadForecaster(WorkloadForecasting)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getControl_WorkloadForecaster()
	 * @see kieker.tools.slastic.metamodel.adaptation.WorkloadForecasting#getControl
	 * @model opposite="control" containment="true" required="true" ordered="false"
	 * @generated
	 */
	WorkloadForecasting getWorkloadForecaster();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.Control#getWorkloadForecaster <em>Workload Forecaster</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workload Forecaster</em>' containment reference.
	 * @see #getWorkloadForecaster()
	 * @generated
	 */
	void setWorkloadForecaster(WorkloadForecasting value);

	/**
	 * Returns the value of the '<em><b>Performance Predictor</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.PerformancePrediction#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performance Predictor</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performance Predictor</em>' containment reference.
	 * @see #setPerformancePredictor(PerformancePrediction)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getControl_PerformancePredictor()
	 * @see kieker.tools.slastic.metamodel.adaptation.PerformancePrediction#getControl
	 * @model opposite="control" containment="true" required="true" ordered="false"
	 * @generated
	 */
	PerformancePrediction getPerformancePredictor();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.Control#getPerformancePredictor <em>Performance Predictor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performance Predictor</em>' containment reference.
	 * @see #getPerformancePredictor()
	 * @generated
	 */
	void setPerformancePredictor(PerformancePrediction value);

	/**
	 * Returns the value of the '<em><b>Adaptation Planner</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.AdaptationPlanning#getControl <em>Control</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adaptation Planner</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adaptation Planner</em>' containment reference.
	 * @see #setAdaptationPlanner(AdaptationPlanning)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getControl_AdaptationPlanner()
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPlanning#getControl
	 * @model opposite="control" containment="true" required="true" ordered="false"
	 * @generated
	 */
	AdaptationPlanning getAdaptationPlanner();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.Control#getAdaptationPlanner <em>Adaptation Planner</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adaptation Planner</em>' containment reference.
	 * @see #getAdaptationPlanner()
	 * @generated
	 */
	void setAdaptationPlanner(AdaptationPlanning value);

} // Control
