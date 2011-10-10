/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository.resourceTypes.impl;

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

import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.MemSwapType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesFactory;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage;

import de.cau.se.slastic.metamodel.usage.UsagePackage;

import de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceTypesPackageImpl extends EPackageImpl implements ResourceTypesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cpuTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass genericResourceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memSwapTypeEClass = null;

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
	 * @see de.cau.se.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ResourceTypesPackageImpl() {
		super(eNS_URI, ResourceTypesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ResourceTypesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ResourceTypesPackage init() {
		if (isInited) return (ResourceTypesPackage)EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI);

		// Obtain or create and register package
		ResourceTypesPackageImpl theResourceTypesPackage = (ResourceTypesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ResourceTypesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ResourceTypesPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		QosPackageImpl theQosPackage = (QosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) instanceof QosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) : QosPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		TypeRepositoryPackageImpl theTypeRepositoryPackage = (TypeRepositoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) instanceof TypeRepositoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) : TypeRepositoryPackage.eINSTANCE);
		ExecutionEnvironmentPackageImpl theExecutionEnvironmentPackage = (ExecutionEnvironmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) instanceof ExecutionEnvironmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) : ExecutionEnvironmentPackage.eINSTANCE);
		ComponentAssemblyPackageImpl theComponentAssemblyPackage = (ComponentAssemblyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) instanceof ComponentAssemblyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) : ComponentAssemblyPackage.eINSTANCE);
		ComponentDeploymentPackageImpl theComponentDeploymentPackage = (ComponentDeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) instanceof ComponentDeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) : ComponentDeploymentPackage.eINSTANCE);
		MonitoringPackageImpl theMonitoringPackage = (MonitoringPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) instanceof MonitoringPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) : MonitoringPackage.eINSTANCE);
		PlanPackageImpl thePlanPackage = (PlanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) instanceof PlanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) : PlanPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		AdaptationPackageImpl theAdaptationPackage = (AdaptationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) instanceof AdaptationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) : AdaptationPackage.eINSTANCE);
		UsagePackageImpl theUsagePackage = (UsagePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) instanceof UsagePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) : UsagePackage.eINSTANCE);

		// Create package meta-data objects
		theResourceTypesPackage.createPackageContents();
		theQosPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theTypeRepositoryPackage.createPackageContents();
		theExecutionEnvironmentPackage.createPackageContents();
		theComponentAssemblyPackage.createPackageContents();
		theComponentDeploymentPackage.createPackageContents();
		theMonitoringPackage.createPackageContents();
		thePlanPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theAdaptationPackage.createPackageContents();
		theUsagePackage.createPackageContents();

		// Initialize created meta-data
		theResourceTypesPackage.initializePackageContents();
		theQosPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theTypeRepositoryPackage.initializePackageContents();
		theExecutionEnvironmentPackage.initializePackageContents();
		theComponentAssemblyPackage.initializePackageContents();
		theComponentDeploymentPackage.initializePackageContents();
		theMonitoringPackage.initializePackageContents();
		thePlanPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theAdaptationPackage.initializePackageContents();
		theUsagePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theResourceTypesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ResourceTypesPackage.eNS_URI, theResourceTypesPackage);
		return theResourceTypesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCPUType() {
		return cpuTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUType_Vendor() {
		return (EAttribute)cpuTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUType_Model() {
		return (EAttribute)cpuTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUType_SpeedMhz() {
		return (EAttribute)cpuTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGenericResourceType() {
		return genericResourceTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMemSwapType() {
		return memSwapTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceTypesFactory getResourceTypesFactory() {
		return (ResourceTypesFactory)getEFactoryInstance();
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
		cpuTypeEClass = createEClass(CPU_TYPE);
		createEAttribute(cpuTypeEClass, CPU_TYPE__VENDOR);
		createEAttribute(cpuTypeEClass, CPU_TYPE__MODEL);
		createEAttribute(cpuTypeEClass, CPU_TYPE__SPEED_MHZ);

		genericResourceTypeEClass = createEClass(GENERIC_RESOURCE_TYPE);

		memSwapTypeEClass = createEClass(MEM_SWAP_TYPE);
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
		TypeRepositoryPackage theTypeRepositoryPackage = (TypeRepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		cpuTypeEClass.getESuperTypes().add(theTypeRepositoryPackage.getResourceType());
		genericResourceTypeEClass.getESuperTypes().add(theTypeRepositoryPackage.getResourceType());
		memSwapTypeEClass.getESuperTypes().add(theTypeRepositoryPackage.getResourceType());

		// Initialize classes and features; add operations and parameters
		initEClass(cpuTypeEClass, CPUType.class, "CPUType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCPUType_Vendor(), ecorePackage.getEString(), "vendor", null, 1, 1, CPUType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUType_Model(), ecorePackage.getEString(), "model", null, 1, 1, CPUType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUType_SpeedMhz(), ecorePackage.getELong(), "speedMhz", null, 1, 1, CPUType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(genericResourceTypeEClass, GenericResourceType.class, "GenericResourceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(memSwapTypeEClass, MemSwapType.class, "MemSwapType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
	}

} //ResourceTypesPackageImpl
