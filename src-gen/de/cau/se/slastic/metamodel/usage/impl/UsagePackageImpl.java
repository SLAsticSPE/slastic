/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.adaptation.AdaptationPackage;

import de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl;

import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl;

import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentPackage;

import de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl;

import de.cau.se.slastic.metamodel.core.CorePackage;

import de.cau.se.slastic.metamodel.core.impl.CorePackageImpl;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;

import de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl;

import de.cau.se.slastic.metamodel.monitoring.MonitoringPackage;

import de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl;

import de.cau.se.slastic.metamodel.qos.QosPackage;

import de.cau.se.slastic.metamodel.qos.impl.QosPackageImpl;

import de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage;

import de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl;

import de.cau.se.slastic.metamodel.reconfiguration.specification.SpecificationPackage;

import de.cau.se.slastic.metamodel.reconfiguration.specification.impl.SpecificationPackageImpl;

import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage;

import de.cau.se.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl;

import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage;

import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl;

import de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;
import de.cau.se.slastic.metamodel.usage.FrequencyDistribution;
import de.cau.se.slastic.metamodel.usage.OperationCallFrequency;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.UsageModel;
import de.cau.se.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.ecore.EAttribute;
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
public class UsagePackageImpl extends EPackageImpl implements UsagePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass usageModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callingRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass frequencyDistributionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationCallFrequencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyConnectorCallFrequencyEClass = null;

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
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private UsagePackageImpl() {
		super(eNS_URI, UsageFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UsagePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static UsagePackage init() {
		if (isInited) return (UsagePackage)EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI);

		// Obtain or create and register package
		UsagePackageImpl theUsagePackage = (UsagePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UsagePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UsagePackageImpl());

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
		AdaptationPackageImpl theAdaptationPackage = (AdaptationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) instanceof AdaptationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) : AdaptationPackage.eINSTANCE);

		// Create package meta-data objects
		theUsagePackage.createPackageContents();
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
		theAdaptationPackage.createPackageContents();

		// Initialize created meta-data
		theUsagePackage.initializePackageContents();
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
		theAdaptationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUsagePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UsagePackage.eNS_URI, theUsagePackage);
		return theUsagePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUsageModel() {
		return usageModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUsageModel_CallingRelationships() {
		return (EReference)usageModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUsageModel_OperationCallFrequencies() {
		return (EReference)usageModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUsageModel_AssemblyConnectorCallFrequencies() {
		return (EReference)usageModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCallingRelationship() {
		return callingRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallingRelationship_CalledInterface() {
		return (EReference)callingRelationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallingRelationship_CalledSignature() {
		return (EReference)callingRelationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallingRelationship_CallingOperation() {
		return (EReference)callingRelationshipEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCallingRelationship_FrequencyDistribution() {
		return (EReference)callingRelationshipEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFrequencyDistribution() {
		return frequencyDistributionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFrequencyDistribution_Values() {
		return (EAttribute)frequencyDistributionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFrequencyDistribution_Frequencies() {
		return (EAttribute)frequencyDistributionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationCallFrequency() {
		return operationCallFrequencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationCallFrequency_Frequency() {
		return (EAttribute)operationCallFrequencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationCallFrequency_Operation() {
		return (EReference)operationCallFrequencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyConnectorCallFrequency() {
		return assemblyConnectorCallFrequencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAssemblyConnectorCallFrequency_Frequency() {
		return (EAttribute)assemblyConnectorCallFrequencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyConnectorCallFrequency_AssemblyConnector() {
		return (EReference)assemblyConnectorCallFrequencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyConnectorCallFrequency_Signature() {
		return (EReference)assemblyConnectorCallFrequencyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageFactory getUsageFactory() {
		return (UsageFactory)getEFactoryInstance();
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
		usageModelEClass = createEClass(USAGE_MODEL);
		createEReference(usageModelEClass, USAGE_MODEL__CALLING_RELATIONSHIPS);
		createEReference(usageModelEClass, USAGE_MODEL__OPERATION_CALL_FREQUENCIES);
		createEReference(usageModelEClass, USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES);

		callingRelationshipEClass = createEClass(CALLING_RELATIONSHIP);
		createEReference(callingRelationshipEClass, CALLING_RELATIONSHIP__CALLED_INTERFACE);
		createEReference(callingRelationshipEClass, CALLING_RELATIONSHIP__CALLED_SIGNATURE);
		createEReference(callingRelationshipEClass, CALLING_RELATIONSHIP__CALLING_OPERATION);
		createEReference(callingRelationshipEClass, CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION);

		frequencyDistributionEClass = createEClass(FREQUENCY_DISTRIBUTION);
		createEAttribute(frequencyDistributionEClass, FREQUENCY_DISTRIBUTION__VALUES);
		createEAttribute(frequencyDistributionEClass, FREQUENCY_DISTRIBUTION__FREQUENCIES);

		operationCallFrequencyEClass = createEClass(OPERATION_CALL_FREQUENCY);
		createEAttribute(operationCallFrequencyEClass, OPERATION_CALL_FREQUENCY__FREQUENCY);
		createEReference(operationCallFrequencyEClass, OPERATION_CALL_FREQUENCY__OPERATION);

		assemblyConnectorCallFrequencyEClass = createEClass(ASSEMBLY_CONNECTOR_CALL_FREQUENCY);
		createEAttribute(assemblyConnectorCallFrequencyEClass, ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY);
		createEReference(assemblyConnectorCallFrequencyEClass, ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTOR);
		createEReference(assemblyConnectorCallFrequencyEClass, ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE);
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
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		TypeRepositoryPackage theTypeRepositoryPackage = (TypeRepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI);
		ComponentAssemblyPackage theComponentAssemblyPackage = (ComponentAssemblyPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		usageModelEClass.getESuperTypes().add(theCorePackage.getSLAsticModel());

		// Initialize classes and features; add operations and parameters
		initEClass(usageModelEClass, UsageModel.class, "UsageModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUsageModel_CallingRelationships(), this.getCallingRelationship(), null, "callingRelationships", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUsageModel_OperationCallFrequencies(), this.getOperationCallFrequency(), null, "operationCallFrequencies", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUsageModel_AssemblyConnectorCallFrequencies(), this.getAssemblyConnectorCallFrequency(), null, "assemblyConnectorCallFrequencies", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(callingRelationshipEClass, CallingRelationship.class, "CallingRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallingRelationship_CalledInterface(), theTypeRepositoryPackage.getInterface(), null, "calledInterface", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCallingRelationship_CalledSignature(), theTypeRepositoryPackage.getSignature(), null, "calledSignature", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCallingRelationship_CallingOperation(), theTypeRepositoryPackage.getOperation(), null, "callingOperation", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCallingRelationship_FrequencyDistribution(), this.getFrequencyDistribution(), null, "frequencyDistribution", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(frequencyDistributionEClass, FrequencyDistribution.class, "FrequencyDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFrequencyDistribution_Values(), ecorePackage.getELong(), "values", null, 0, -1, FrequencyDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getFrequencyDistribution_Frequencies(), ecorePackage.getELong(), "frequencies", null, 0, -1, FrequencyDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(operationCallFrequencyEClass, OperationCallFrequency.class, "OperationCallFrequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperationCallFrequency_Frequency(), ecorePackage.getELong(), "frequency", null, 1, 1, OperationCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOperationCallFrequency_Operation(), theTypeRepositoryPackage.getOperation(), null, "operation", null, 1, 1, OperationCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assemblyConnectorCallFrequencyEClass, AssemblyConnectorCallFrequency.class, "AssemblyConnectorCallFrequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssemblyConnectorCallFrequency_Frequency(), ecorePackage.getELong(), "frequency", null, 1, 1, AssemblyConnectorCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssemblyConnectorCallFrequency_AssemblyConnector(), theComponentAssemblyPackage.getAssemblyConnector(), null, "assemblyConnector", null, 1, 1, AssemblyConnectorCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssemblyConnectorCallFrequency_Signature(), theTypeRepositoryPackage.getSignature(), null, "signature", null, 1, 1, AssemblyConnectorCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //UsagePackageImpl
