/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.impl;

import de.cau.se.slastic.metamodel.adaptation.AdaptationPackage;

import de.cau.se.slastic.metamodel.adaptation.impl.AdaptationPackageImpl;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

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
	private EClass assemblyConnectorEClass = null;

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
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#eNS_URI
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
	public EClass getComponentAssemblyModel() {
		return componentAssemblyModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_AssemblyComponents() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentAssemblyModel_AssemblyConnectors() {
		return (EReference)componentAssemblyModelEClass.getEStructuralFeatures().get(1);
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

		assemblyConnectorEClass = createEClass(ASSEMBLY_CONNECTOR);
		createEReference(assemblyConnectorEClass, ASSEMBLY_CONNECTOR__CONNECTOR_TYPE);

		componentAssemblyModelEClass = createEClass(COMPONENT_ASSEMBLY_MODEL);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS);
		createEReference(componentAssemblyModelEClass, COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS);
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
		assemblyConnectorEClass.getESuperTypes().add(theCorePackage.getFQNamedEntity());
		componentAssemblyModelEClass.getESuperTypes().add(theCorePackage.getSLAsticModel());

		// Initialize classes and features; add operations and parameters
		initEClass(assemblyComponentEClass, AssemblyComponent.class, "AssemblyComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyComponent_ComponentType(), theTypeRepositoryPackage.getComponentType(), null, "componentType", null, 1, 1, AssemblyComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assemblyConnectorEClass, AssemblyConnector.class, "AssemblyConnector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyConnector_ConnectorType(), theTypeRepositoryPackage.getConnectorType(), null, "connectorType", null, 1, 1, AssemblyConnector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentAssemblyModelEClass, ComponentAssemblyModel.class, "ComponentAssemblyModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentAssemblyModel_AssemblyComponents(), this.getAssemblyComponent(), null, "assemblyComponents", null, 0, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentAssemblyModel_AssemblyConnectors(), this.getAssemblyConnector(), null, "assemblyConnectors", null, 0, -1, ComponentAssemblyModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ComponentAssemblyPackageImpl