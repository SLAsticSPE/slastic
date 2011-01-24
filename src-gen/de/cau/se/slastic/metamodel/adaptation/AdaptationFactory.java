/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.adaptation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationPackage
 * @generated
 */
public interface AdaptationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AdaptationFactory eINSTANCE = de.cau.se.slastic.metamodel.adaptation.impl.AdaptationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Control</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Control</em>'.
	 * @generated
	 */
	Control createControl();

	/**
	 * Returns a new object of class '<em>Performance Evaluation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Performance Evaluation</em>'.
	 * @generated
	 */
	PerformanceEvaluation createPerformanceEvaluation();

	/**
	 * Returns a new object of class '<em>Workload Forecasting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workload Forecasting</em>'.
	 * @generated
	 */
	WorkloadForecasting createWorkloadForecasting();

	/**
	 * Returns a new object of class '<em>Performance Prediction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Performance Prediction</em>'.
	 * @generated
	 */
	PerformancePrediction createPerformancePrediction();

	/**
	 * Returns a new object of class '<em>Planning</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Planning</em>'.
	 * @generated
	 */
	AdaptationPlanning createAdaptationPlanning();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	AdaptationModel createAdaptationModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AdaptationPackage getAdaptationPackage();

} //AdaptationFactory
