/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.adaptation.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationFactory;
import kieker.tools.slastic.metamodel.adaptation.AdaptationModel;
import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;
import kieker.tools.slastic.metamodel.adaptation.AdaptationPlanning;
import kieker.tools.slastic.metamodel.adaptation.Control;
import kieker.tools.slastic.metamodel.adaptation.PerformanceEvaluation;
import kieker.tools.slastic.metamodel.adaptation.PerformancePrediction;
import kieker.tools.slastic.metamodel.adaptation.WorkloadForecasting;

import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import kieker.tools.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl;

import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage;

import kieker.tools.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl;

import kieker.tools.slastic.metamodel.core.CorePackage;

import kieker.tools.slastic.metamodel.core.impl.CorePackageImpl;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;

import kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl;

import kieker.tools.slastic.metamodel.monitoring.MonitoringPackage;

import kieker.tools.slastic.metamodel.monitoring.impl.MonitoringPackageImpl;

import kieker.tools.slastic.metamodel.qos.QosPackage;

import kieker.tools.slastic.metamodel.qos.impl.QosPackageImpl;

import kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage;

import kieker.tools.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl;

import kieker.tools.slastic.metamodel.reconfiguration.specification.SpecificationPackage;

import kieker.tools.slastic.metamodel.reconfiguration.specification.impl.SpecificationPackageImpl;

import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage;

import kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl;

import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage;

import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl;

import kieker.tools.slastic.metamodel.usage.UsagePackage;

import kieker.tools.slastic.metamodel.usage.impl.UsagePackageImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AdaptationPackageImpl extends EPackageImpl implements AdaptationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass performanceEvaluationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workloadForecastingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass performancePredictionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationPlanningEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationModelEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AdaptationPackageImpl() {
		super(eNS_URI, AdaptationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link AdaptationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AdaptationPackage init() {
		if (isInited) return (AdaptationPackage)EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI);

		// Obtain or create and register package
		AdaptationPackageImpl theAdaptationPackage = (AdaptationPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof AdaptationPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new AdaptationPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		QosPackageImpl theQosPackage = (QosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) instanceof QosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) : QosPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		TypeRepositoryPackageImpl theTypeRepositoryPackage = (TypeRepositoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) instanceof TypeRepositoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) : TypeRepositoryPackage.eINSTANCE);
		ResourceTypesPackageImpl theResourceTypesPackage = (ResourceTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) instanceof ResourceTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) : ResourceTypesPackage.eINSTANCE);
		ExecutionEnvironmentPackageImpl theExecutionEnvironmentPackage = (ExecutionEnvironmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) instanceof ExecutionEnvironmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) : ExecutionEnvironmentPackage.eINSTANCE);
		ComponentAssemblyPackageImpl theComponentAssemblyPackage = (ComponentAssemblyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) instanceof ComponentAssemblyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) : ComponentAssemblyPackage.eINSTANCE);
		ComponentDeploymentPackageImpl theComponentDeploymentPackage = (ComponentDeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) instanceof ComponentDeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) : ComponentDeploymentPackage.eINSTANCE);
		MonitoringPackageImpl theMonitoringPackage = (MonitoringPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) instanceof MonitoringPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) : MonitoringPackage.eINSTANCE);
		PlanPackageImpl thePlanPackage = (PlanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) instanceof PlanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) : PlanPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		UsagePackageImpl theUsagePackage = (UsagePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) instanceof UsagePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) : UsagePackage.eINSTANCE);

		// Create package meta-data objects
		theAdaptationPackage.createPackageContents();
		theQosPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theTypeRepositoryPackage.createPackageContents();
		theResourceTypesPackage.createPackageContents();
		theExecutionEnvironmentPackage.createPackageContents();
		theComponentAssemblyPackage.createPackageContents();
		theComponentDeploymentPackage.createPackageContents();
		theMonitoringPackage.createPackageContents();
		thePlanPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theUsagePackage.createPackageContents();

		// Initialize created meta-data
		theAdaptationPackage.initializePackageContents();
		theQosPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theTypeRepositoryPackage.initializePackageContents();
		theResourceTypesPackage.initializePackageContents();
		theExecutionEnvironmentPackage.initializePackageContents();
		theComponentAssemblyPackage.initializePackageContents();
		theComponentDeploymentPackage.initializePackageContents();
		theMonitoringPackage.initializePackageContents();
		thePlanPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theUsagePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAdaptationPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AdaptationPackage.eNS_URI, theAdaptationPackage);
		return theAdaptationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControl() {
		return controlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControl_AdaptationModel() {
		return (EReference)controlEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControl_PerformanceEvaluator() {
		return (EReference)controlEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControl_WorkloadForecaster() {
		return (EReference)controlEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControl_PerformancePredictor() {
		return (EReference)controlEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControl_AdaptationPlanner() {
		return (EReference)controlEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPerformanceEvaluation() {
		return performanceEvaluationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerformanceEvaluation_Control() {
		return (EReference)performanceEvaluationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWorkloadForecasting() {
		return workloadForecastingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkloadForecasting_Control() {
		return (EReference)workloadForecastingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPerformancePrediction() {
		return performancePredictionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerformancePrediction_Control() {
		return (EReference)performancePredictionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptationPlanning() {
		return adaptationPlanningEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptationPlanning_Control() {
		return (EReference)adaptationPlanningEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdaptationModel() {
		return adaptationModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptationModel_ControlModel() {
		return (EReference)adaptationModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptationModel_QoSModel() {
		return (EReference)adaptationModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdaptationModel_ReconfigurationSpecification() {
		return (EReference)adaptationModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationFactory getAdaptationFactory() {
		return (AdaptationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		controlEClass = createEClass(CONTROL);
		createEReference(controlEClass, CONTROL__ADAPTATION_MODEL);
		createEReference(controlEClass, CONTROL__PERFORMANCE_EVALUATOR);
		createEReference(controlEClass, CONTROL__WORKLOAD_FORECASTER);
		createEReference(controlEClass, CONTROL__PERFORMANCE_PREDICTOR);
		createEReference(controlEClass, CONTROL__ADAPTATION_PLANNER);

		performanceEvaluationEClass = createEClass(PERFORMANCE_EVALUATION);
		createEReference(performanceEvaluationEClass, PERFORMANCE_EVALUATION__CONTROL);

		workloadForecastingEClass = createEClass(WORKLOAD_FORECASTING);
		createEReference(workloadForecastingEClass, WORKLOAD_FORECASTING__CONTROL);

		performancePredictionEClass = createEClass(PERFORMANCE_PREDICTION);
		createEReference(performancePredictionEClass, PERFORMANCE_PREDICTION__CONTROL);

		adaptationPlanningEClass = createEClass(ADAPTATION_PLANNING);
		createEReference(adaptationPlanningEClass, ADAPTATION_PLANNING__CONTROL);

		adaptationModelEClass = createEClass(ADAPTATION_MODEL);
		createEReference(adaptationModelEClass, ADAPTATION_MODEL__CONTROL_MODEL);
		createEReference(adaptationModelEClass, ADAPTATION_MODEL__QO_SMODEL);
		createEReference(adaptationModelEClass, ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		QosPackage theQosPackage = (QosPackage)EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI);
		SpecificationPackage theSpecificationPackage = (SpecificationPackage)EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(controlEClass, Control.class, "Control", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControl_AdaptationModel(), this.getAdaptationModel(), this.getAdaptationModel_ControlModel(), "adaptationModel", null, 1, 1, Control.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getControl_PerformanceEvaluator(), this.getPerformanceEvaluation(), this.getPerformanceEvaluation_Control(), "performanceEvaluator", null, 1, 1, Control.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getControl_WorkloadForecaster(), this.getWorkloadForecasting(), this.getWorkloadForecasting_Control(), "workloadForecaster", null, 1, 1, Control.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getControl_PerformancePredictor(), this.getPerformancePrediction(), this.getPerformancePrediction_Control(), "performancePredictor", null, 1, 1, Control.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getControl_AdaptationPlanner(), this.getAdaptationPlanning(), this.getAdaptationPlanning_Control(), "adaptationPlanner", null, 1, 1, Control.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(performanceEvaluationEClass, PerformanceEvaluation.class, "PerformanceEvaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPerformanceEvaluation_Control(), this.getControl(), this.getControl_PerformanceEvaluator(), "control", null, 1, 1, PerformanceEvaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(workloadForecastingEClass, WorkloadForecasting.class, "WorkloadForecasting", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkloadForecasting_Control(), this.getControl(), this.getControl_WorkloadForecaster(), "control", null, 1, 1, WorkloadForecasting.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(performancePredictionEClass, PerformancePrediction.class, "PerformancePrediction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPerformancePrediction_Control(), this.getControl(), this.getControl_PerformancePredictor(), "control", null, 1, 1, PerformancePrediction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(adaptationPlanningEClass, AdaptationPlanning.class, "AdaptationPlanning", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdaptationPlanning_Control(), this.getControl(), this.getControl_AdaptationPlanner(), "control", null, 1, 1, AdaptationPlanning.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(adaptationModelEClass, AdaptationModel.class, "AdaptationModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdaptationModel_ControlModel(), this.getControl(), this.getControl_AdaptationModel(), "controlModel", null, 1, 1, AdaptationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAdaptationModel_QoSModel(), theQosPackage.getQoSModel(), null, "qoSModel", null, 1, 1, AdaptationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAdaptationModel_ReconfigurationSpecification(), theSpecificationPackage.getReconfigurationSpecification(), theSpecificationPackage.getReconfigurationSpecification_AdaptationModel(), "reconfigurationSpecification", null, 1, 1, AdaptationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //AdaptationPackageImpl
