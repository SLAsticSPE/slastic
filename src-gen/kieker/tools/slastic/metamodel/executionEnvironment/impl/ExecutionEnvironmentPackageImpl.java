/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;

import kieker.tools.slastic.metamodel.adaptation.impl.AdaptationPackageImpl;

import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import kieker.tools.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl;

import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage;

import kieker.tools.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl;

import kieker.tools.slastic.metamodel.core.CorePackage;

import kieker.tools.slastic.metamodel.core.impl.CorePackageImpl;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink;
import kieker.tools.slastic.metamodel.executionEnvironment.Resource;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;

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
public class ExecutionEnvironmentPackageImpl extends EPackageImpl implements ExecutionEnvironmentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionContainerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass networkLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionEnvironmentModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memSwapResourceSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceSpecificationEClass = null;

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
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExecutionEnvironmentPackageImpl() {
		super(eNS_URI, ExecutionEnvironmentFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ExecutionEnvironmentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExecutionEnvironmentPackage init() {
		if (isInited) return (ExecutionEnvironmentPackage)EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI);

		// Obtain or create and register package
		ExecutionEnvironmentPackageImpl theExecutionEnvironmentPackage = (ExecutionEnvironmentPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExecutionEnvironmentPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ExecutionEnvironmentPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		QosPackageImpl theQosPackage = (QosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) instanceof QosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) : QosPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		TypeRepositoryPackageImpl theTypeRepositoryPackage = (TypeRepositoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) instanceof TypeRepositoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) : TypeRepositoryPackage.eINSTANCE);
		ResourceTypesPackageImpl theResourceTypesPackage = (ResourceTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) instanceof ResourceTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) : ResourceTypesPackage.eINSTANCE);
		ComponentAssemblyPackageImpl theComponentAssemblyPackage = (ComponentAssemblyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) instanceof ComponentAssemblyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) : ComponentAssemblyPackage.eINSTANCE);
		ComponentDeploymentPackageImpl theComponentDeploymentPackage = (ComponentDeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) instanceof ComponentDeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) : ComponentDeploymentPackage.eINSTANCE);
		MonitoringPackageImpl theMonitoringPackage = (MonitoringPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) instanceof MonitoringPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) : MonitoringPackage.eINSTANCE);
		PlanPackageImpl thePlanPackage = (PlanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) instanceof PlanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) : PlanPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		AdaptationPackageImpl theAdaptationPackage = (AdaptationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) instanceof AdaptationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) : AdaptationPackage.eINSTANCE);
		UsagePackageImpl theUsagePackage = (UsagePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) instanceof UsagePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) : UsagePackage.eINSTANCE);

		// Create package meta-data objects
		theExecutionEnvironmentPackage.createPackageContents();
		theQosPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theTypeRepositoryPackage.createPackageContents();
		theResourceTypesPackage.createPackageContents();
		theComponentAssemblyPackage.createPackageContents();
		theComponentDeploymentPackage.createPackageContents();
		theMonitoringPackage.createPackageContents();
		thePlanPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theAdaptationPackage.createPackageContents();
		theUsagePackage.createPackageContents();

		// Initialize created meta-data
		theExecutionEnvironmentPackage.initializePackageContents();
		theQosPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theTypeRepositoryPackage.initializePackageContents();
		theResourceTypesPackage.initializePackageContents();
		theComponentAssemblyPackage.initializePackageContents();
		theComponentDeploymentPackage.initializePackageContents();
		theMonitoringPackage.initializePackageContents();
		thePlanPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theAdaptationPackage.initializePackageContents();
		theUsagePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExecutionEnvironmentPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExecutionEnvironmentPackage.eNS_URI, theExecutionEnvironmentPackage);
		return theExecutionEnvironmentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionContainer() {
		return executionContainerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContainer_ExecutionContainerType() {
		return (EReference)executionContainerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContainer_NetworkLinks() {
		return (EReference)executionContainerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionContainer_Resources() {
		return (EReference)executionContainerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNetworkLink() {
		return networkLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNetworkLink_NetworkLinkType() {
		return (EReference)networkLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNetworkLink_ExecutionContainers() {
		return (EReference)networkLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_ResourceSpecification() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResource_ExecutionContainer() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionEnvironmentModel() {
		return executionEnvironmentModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEnvironmentModel_ExecutionContainers() {
		return (EReference)executionEnvironmentModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEnvironmentModel_NetworkLinks() {
		return (EReference)executionEnvironmentModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionEnvironmentModel_AllocatedExecutionContainers() {
		return (EReference)executionEnvironmentModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMemSwapResourceSpecification() {
		return memSwapResourceSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMemSwapResourceSpecification_MemCapacityBytes() {
		return (EAttribute)memSwapResourceSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMemSwapResourceSpecification_SwapCapacityBytes() {
		return (EAttribute)memSwapResourceSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceSpecification() {
		return resourceSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceSpecification_ResourceType() {
		return (EReference)resourceSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionEnvironmentFactory getExecutionEnvironmentFactory() {
		return (ExecutionEnvironmentFactory)getEFactoryInstance();
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
		executionContainerEClass = createEClass(EXECUTION_CONTAINER);
		createEReference(executionContainerEClass, EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE);
		createEReference(executionContainerEClass, EXECUTION_CONTAINER__NETWORK_LINKS);
		createEReference(executionContainerEClass, EXECUTION_CONTAINER__RESOURCES);

		networkLinkEClass = createEClass(NETWORK_LINK);
		createEReference(networkLinkEClass, NETWORK_LINK__NETWORK_LINK_TYPE);
		createEReference(networkLinkEClass, NETWORK_LINK__EXECUTION_CONTAINERS);

		resourceEClass = createEClass(RESOURCE);
		createEReference(resourceEClass, RESOURCE__RESOURCE_SPECIFICATION);
		createEReference(resourceEClass, RESOURCE__EXECUTION_CONTAINER);

		executionEnvironmentModelEClass = createEClass(EXECUTION_ENVIRONMENT_MODEL);
		createEReference(executionEnvironmentModelEClass, EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS);
		createEReference(executionEnvironmentModelEClass, EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS);
		createEReference(executionEnvironmentModelEClass, EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS);

		memSwapResourceSpecificationEClass = createEClass(MEM_SWAP_RESOURCE_SPECIFICATION);
		createEAttribute(memSwapResourceSpecificationEClass, MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES);
		createEAttribute(memSwapResourceSpecificationEClass, MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES);

		resourceSpecificationEClass = createEClass(RESOURCE_SPECIFICATION);
		createEReference(resourceSpecificationEClass, RESOURCE_SPECIFICATION__RESOURCE_TYPE);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		executionContainerEClass.getESuperTypes().add(theCorePackage.getFQNamedEntity());
		networkLinkEClass.getESuperTypes().add(theCorePackage.getFQNamedEntity());
		executionEnvironmentModelEClass.getESuperTypes().add(theCorePackage.getSLAsticModel());
		memSwapResourceSpecificationEClass.getESuperTypes().add(this.getResourceSpecification());
		resourceSpecificationEClass.getESuperTypes().add(theCorePackage.getNamedEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(executionContainerEClass, ExecutionContainer.class, "ExecutionContainer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionContainer_ExecutionContainerType(), theTypeRepositoryPackage.getExecutionContainerType(), null, "executionContainerType", null, 1, 1, ExecutionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExecutionContainer_NetworkLinks(), this.getNetworkLink(), this.getNetworkLink_ExecutionContainers(), "networkLinks", null, 0, -1, ExecutionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExecutionContainer_Resources(), this.getResource(), this.getResource_ExecutionContainer(), "resources", null, 0, -1, ExecutionContainer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(networkLinkEClass, NetworkLink.class, "NetworkLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNetworkLink_NetworkLinkType(), theTypeRepositoryPackage.getNetworkLinkType(), null, "networkLinkType", null, 1, 1, NetworkLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getNetworkLink_ExecutionContainers(), this.getExecutionContainer(), this.getExecutionContainer_NetworkLinks(), "executionContainers", null, 0, -1, NetworkLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResource_ResourceSpecification(), this.getResourceSpecification(), null, "resourceSpecification", null, 1, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getResource_ExecutionContainer(), this.getExecutionContainer(), this.getExecutionContainer_Resources(), "executionContainer", null, 1, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(executionEnvironmentModelEClass, ExecutionEnvironmentModel.class, "ExecutionEnvironmentModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionEnvironmentModel_ExecutionContainers(), this.getExecutionContainer(), null, "executionContainers", null, 0, -1, ExecutionEnvironmentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExecutionEnvironmentModel_NetworkLinks(), this.getNetworkLink(), null, "networkLinks", null, 0, -1, ExecutionEnvironmentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExecutionEnvironmentModel_AllocatedExecutionContainers(), this.getExecutionContainer(), null, "allocatedExecutionContainers", null, 0, -1, ExecutionEnvironmentModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(memSwapResourceSpecificationEClass, MemSwapResourceSpecification.class, "MemSwapResourceSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMemSwapResourceSpecification_MemCapacityBytes(), ecorePackage.getELong(), "memCapacityBytes", null, 1, 1, MemSwapResourceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMemSwapResourceSpecification_SwapCapacityBytes(), ecorePackage.getELong(), "swapCapacityBytes", null, 1, 1, MemSwapResourceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(resourceSpecificationEClass, ResourceSpecification.class, "ResourceSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceSpecification_ResourceType(), theTypeRepositoryPackage.getResourceType(), null, "resourceType", null, 1, 1, ResourceSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ExecutionEnvironmentPackageImpl
