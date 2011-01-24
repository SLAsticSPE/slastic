/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.adaptation.impl;

import de.cau.se.slastic.metamodel.adaptation.*;

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
public class AdaptationFactoryImpl extends EFactoryImpl implements AdaptationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AdaptationFactory init() {
		try {
			AdaptationFactory theAdaptationFactory = (AdaptationFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/adaptation.ecore"); 
			if (theAdaptationFactory != null) {
				return theAdaptationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new AdaptationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationFactoryImpl() {
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
			case AdaptationPackage.CONTROL: return createControl();
			case AdaptationPackage.PERFORMANCE_EVALUATION: return createPerformanceEvaluation();
			case AdaptationPackage.WORKLOAD_FORECASTING: return createWorkloadForecasting();
			case AdaptationPackage.PERFORMANCE_PREDICTION: return createPerformancePrediction();
			case AdaptationPackage.ADAPTATION_PLANNING: return createAdaptationPlanning();
			case AdaptationPackage.ADAPTATION_MODEL: return createAdaptationModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Control createControl() {
		ControlImpl control = new ControlImpl();
		return control;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerformanceEvaluation createPerformanceEvaluation() {
		PerformanceEvaluationImpl performanceEvaluation = new PerformanceEvaluationImpl();
		return performanceEvaluation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkloadForecasting createWorkloadForecasting() {
		WorkloadForecastingImpl workloadForecasting = new WorkloadForecastingImpl();
		return workloadForecasting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerformancePrediction createPerformancePrediction() {
		PerformancePredictionImpl performancePrediction = new PerformancePredictionImpl();
		return performancePrediction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationPlanning createAdaptationPlanning() {
		AdaptationPlanningImpl adaptationPlanning = new AdaptationPlanningImpl();
		return adaptationPlanning;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationModel createAdaptationModel() {
		AdaptationModelImpl adaptationModel = new AdaptationModelImpl();
		return adaptationModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationPackage getAdaptationPackage() {
		return (AdaptationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static AdaptationPackage getPackage() {
		return AdaptationPackage.eINSTANCE;
	}

} //AdaptationFactoryImpl
