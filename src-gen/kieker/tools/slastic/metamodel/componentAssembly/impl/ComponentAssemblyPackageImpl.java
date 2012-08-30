/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;

import kieker.tools.slastic.metamodel.adaptation.impl.AdaptationPackageImpl;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyConnector;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;
import kieker.tools.slastic.metamodel.componentAssembly.SystemInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector;

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
public class ComponentAssemblyPackageImpl extends EPackageImpl implements ComponentAssemblyPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyComponentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyComponentConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemInterfaceDelegationConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemProvidedInterfaceDelegationConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemRequiredInterfaceDelegationConnectorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentAssemblyModelEClass = null;

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
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ComponentAssemblyPackageImpl() {
		super(eNS_URI, ComponentAssemblyFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ComponentAssemblyPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ComponentAssemblyPackage init() {
		if (isInited) return (ComponentAssemblyPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI);

		// Obtain or create and register package
		ComponentAssemblyPackageImpl theComponentAssemblyPackage = (ComponentAssemblyPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ComponentAssemblyPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ComponentAssemblyPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		QosPackageImpl theQosPackage = (QosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) instanceof QosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) : QosPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		TypeRepositoryPackageImpl theTypeRepositoryPackage = (TypeRepositoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) instanceof TypeRepositoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) : TypeRepositoryPackage.eINSTANCE);
		ResourceTypesPackageImpl theResourceTypesPackage = (ResourceTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) instanceof ResourceTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) : ResourceTypesPackage.eINSTANCE);
		ExecutionEnvironmentPackageImpl theExecutionEnvironmentPackage = (ExecutionEnvironmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) instanceof ExecutionEnvironmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) : ExecutionEnvironmentPackage.eINSTANCE);
		ComponentDeploymentPackageImpl theComponentDeploymentPackage = (ComponentDeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) instanceof ComponentDeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) : ComponentDeploymentPackage.eINSTANCE);
		MonitoringPackageImpl theMonitoringPackage = (MonitoringPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) instanceof MonitoringPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI) : MonitoringPackage.eINSTANCE);
		PlanPackageImpl thePlanPackage = (PlanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) instanceof PlanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) : PlanPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		AdaptationPackageImpl theAdaptationPackage = (AdaptationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) instanceof AdaptationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) : AdaptationPackage.eINSTANCE);
		UsagePackageImpl theUsagePackage = (UsagePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) instanceof UsagePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) : UsagePackage.eINSTANCE);

		// Create package meta-data objects
		theComponentAssemblyPackage.createPackageContents();
		theQosPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theTypeRepositoryPackage.createPackageContents();
		theResourceTypesPackage.createPackageContents();
		theExecutionEnvironmentPackage.createPackageContents();
		theComponentDeploymentPackage.createPackageContents();
		theMonitoringPackage.createPackageContents();
		thePlanPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theAdaptationPackage.createPackageContents();
		theUsagePackage.createPackageContents();

		// Initialize created meta-data
		theComponentAssemblyPackage.initializePackageContents();
		theQosPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theTypeRepositoryPackage.initializePackageContents();
		theResourceTypesPackage.initializePackageContents();
		theExecutionEnvironmentPackage.initializePackageContents();
		theComponentDeploymentPackage.initializePackageContents();
		theMonitoringPackage.initializePackageContents();
		thePlanPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theAdaptationPackage.initializePackageContents();
		theUsagePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theComponentAssemblyPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ComponentAssemblyPackage.eNS_URI, theComponentAssemblyPackage);
		return theComponentAssemblyPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyComponent() {
		return assemblyComponentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyComponent_ComponentType() {
		return (EReference)assemblyComponentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyComponent_ProvidingConnectors() {
		return (EReference)assemblyComponentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyComponent_RequiringConnectors() {
		return (EReference)assemblyComponentEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyComponentConnector() {
		return assemblyComponentConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyComponentConnector_ProvidingComponent() {
		return (EReference)assemblyComponentConnectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyComponentConnector_RequiringComponent() {
		return (EReference)assemblyComponentConnectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAssemblyConnector() {
		return assemblyConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyConnector_ConnectorType() {
		return (EReference)assemblyConnectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemInterfaceDelegationConnector() {
		return systemInterfaceDelegationConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemProvidedInterfaceDelegationConnector() {
		return systemProvidedInterfaceDelegationConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemProvidedInterfaceDelegationConnector_ProvidingComponent() {
		return (EReference)systemProvidedInterfaceDelegationConnectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemProvidedInterfaceDelegationConnector_ComponentAssemblyModel() {
		return (EReference)systemProvidedInterfaceDelegationConnectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemRequiredInterfaceDelegationConnector() {
		return systemRequiredInterfaceDelegationConnectorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemRequiredInterfaceDelegationConnector_RequiringComponent() {
		return (EReference)systemRequiredInterfaceDelegationConnectorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemRequiredInterfaceDelegationConnector_ComponentAssemblyModel() {
		return (EReference)systemRequiredInterfaceDelegationConnectorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentAssemblyModel() {
		return componentAssemblyModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_SystemProvidedInterfaceDelegationConnectors() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_SystemRequiredInterfaceDelegationConnectors() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_AssemblyComponents() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_AssemblyComponentConnectors() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_SystemProvidedInterfaces() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_SystemRequiredInterfaces() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyFactory getComponentAssemblyFactory() {
		return (ComponentAssemblyFactory)getEFactoryInstance();
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
		assemblyComponentEClass = createEClass(ASSEMBLY_COMPONENT);
		createEReference(assemblyComponentEClass, ASSEMBLY_COMPONENT__COMPONENT_TYPE);
		createEReference(assemblyComponentEClass, ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS);
		createEReference(assemblyComponentEClass, ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS);

		assemblyComponentConnectorEClass = createEClass(ASSEMBLY_COMPONENT_CONNECTOR);
		createEReference(assemblyComponentConnectorEClass, ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT);
		createEReference(assemblyComponentConnectorEClass, ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT);

		assemblyConnectorEClass = createEClass(ASSEMBLY_CONNECTOR);
		createEReference(assemblyConnectorEClass, ASSEMBLY_CONNECTOR__CONNECTOR_TYPE);

		systemInterfaceDelegationConnectorEClass = createEClass(SYSTEM_INTERFACE_DELEGATION_CONNECTOR);

		systemProvidedInterfaceDelegationConnectorEClass = createEClass(SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR);
		createEReference(systemProvidedInterfaceDelegationConnectorEClass, SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT);
		createEReference(systemProvidedInterfaceDelegationConnectorEClass, SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL);

		systemRequiredInterfaceDelegationConnectorEClass = createEClass(SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR);
		createEReference(systemRequiredInterfaceDelegationConnectorEClass, SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT);
		createEReference(systemRequiredInterfaceDelegationConnectorEClass, SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL);

		componentAssemblyModelEClass = createEClass(COMPONENT_ASSEMBLY_MODEL);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES);
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
		assemblyComponentEClass.getESuperTypes().add(theCorePackage.getFQNamedEntity());
		assemblyComponentConnectorEClass.getESuperTypes().add(this.getAssemblyConnector());
		assemblyConnectorEClass.getESuperTypes().add(theCorePackage.getFQNamedEntity());
		systemInterfaceDelegationConnectorEClass.getESuperTypes().add(this.getAssemblyConnector());
		systemProvidedInterfaceDelegationConnectorEClass.getESuperTypes().add(this.getSystemInterfaceDelegationConnector());
		systemRequiredInterfaceDelegationConnectorEClass.getESuperTypes().add(this.getSystemInterfaceDelegationConnector());
		componentAssemblyModelEClass.getESuperTypes().add(theCorePackage.getSLAsticModel());

		// Initialize classes and features; add operations and parameters
		initEClass(assemblyComponentEClass, AssemblyComponent.class, "AssemblyComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyComponent_ComponentType(), theTypeRepositoryPackage.getComponentType(), null, "componentType", null, 1, 1, AssemblyComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssemblyComponent_ProvidingConnectors(), this.getAssemblyComponentConnector(), this.getAssemblyComponentConnector_RequiringComponent(), "providingConnectors", null, 0, -1, AssemblyComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssemblyComponent_RequiringConnectors(), this.getAssemblyComponentConnector(), this.getAssemblyComponentConnector_ProvidingComponent(), "requiringConnectors", null, 0, -1, AssemblyComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assemblyComponentConnectorEClass, AssemblyComponentConnector.class, "AssemblyComponentConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyComponentConnector_ProvidingComponent(), this.getAssemblyComponent(), this.getAssemblyComponent_RequiringConnectors(), "providingComponent", null, 1, 1, AssemblyComponentConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssemblyComponentConnector_RequiringComponent(), this.getAssemblyComponent(), this.getAssemblyComponent_ProvidingConnectors(), "requiringComponent", null, 1, 1, AssemblyComponentConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assemblyConnectorEClass, AssemblyConnector.class, "AssemblyConnector", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyConnector_ConnectorType(), theTypeRepositoryPackage.getConnectorType(), null, "connectorType", null, 1, 1, AssemblyConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(systemInterfaceDelegationConnectorEClass, SystemInterfaceDelegationConnector.class, "SystemInterfaceDelegationConnector", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(systemProvidedInterfaceDelegationConnectorEClass, SystemProvidedInterfaceDelegationConnector.class, "SystemProvidedInterfaceDelegationConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemProvidedInterfaceDelegationConnector_ProvidingComponent(), this.getAssemblyComponent(), null, "providingComponent", null, 1, 1, SystemProvidedInterfaceDelegationConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSystemProvidedInterfaceDelegationConnector_ComponentAssemblyModel(), this.getComponentAssemblyModel(), this.getComponentAssemblyModel_SystemProvidedInterfaceDelegationConnectors(), "componentAssemblyModel", null, 1, 1, SystemProvidedInterfaceDelegationConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(systemRequiredInterfaceDelegationConnectorEClass, SystemRequiredInterfaceDelegationConnector.class, "SystemRequiredInterfaceDelegationConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemRequiredInterfaceDelegationConnector_RequiringComponent(), this.getAssemblyComponent(), null, "requiringComponent", null, 1, 1, SystemRequiredInterfaceDelegationConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getSystemRequiredInterfaceDelegationConnector_ComponentAssemblyModel(), this.getComponentAssemblyModel(), this.getComponentAssemblyModel_SystemRequiredInterfaceDelegationConnectors(), "componentAssemblyModel", null, 1, 1, SystemRequiredInterfaceDelegationConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentAssemblyModelEClass, ComponentAssemblyModel.class, "ComponentAssemblyModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentAssemblyModel_SystemProvidedInterfaceDelegationConnectors(), this.getSystemProvidedInterfaceDelegationConnector(), this.getSystemProvidedInterfaceDelegationConnector_ComponentAssemblyModel(), "systemProvidedInterfaceDelegationConnectors", null, 1, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentAssemblyModel_SystemRequiredInterfaceDelegationConnectors(), this.getSystemRequiredInterfaceDelegationConnector(), this.getSystemRequiredInterfaceDelegationConnector_ComponentAssemblyModel(), "systemRequiredInterfaceDelegationConnectors", null, 0, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentAssemblyModel_AssemblyComponents(), this.getAssemblyComponent(), null, "assemblyComponents", null, 0, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentAssemblyModel_AssemblyComponentConnectors(), this.getAssemblyComponentConnector(), null, "assemblyComponentConnectors", null, 0, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentAssemblyModel_SystemProvidedInterfaces(), theTypeRepositoryPackage.getInterface(), null, "systemProvidedInterfaces", null, 1, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentAssemblyModel_SystemRequiredInterfaces(), theTypeRepositoryPackage.getInterface(), null, "systemRequiredInterfaces", null, 0, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ComponentAssemblyPackageImpl
