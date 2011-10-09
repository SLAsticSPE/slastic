/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring.impl;

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

import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;
import de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.monitoring.MonitoringPackage;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement;
import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;

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

import de.cau.se.slastic.metamodel.usage.UsagePackage;

import de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl;

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
public class MonitoringPackageImpl extends EPackageImpl implements MonitoringPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deploymentComponentOperationExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectorOperationExecutionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceUtilizationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceMeasurementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass memSwapUsageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cpuUtilizationEClass = null;

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
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MonitoringPackageImpl() {
		super(eNS_URI, MonitoringFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link MonitoringPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MonitoringPackage init() {
		if (isInited) return (MonitoringPackage)EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI);

		// Obtain or create and register package
		MonitoringPackageImpl theMonitoringPackage = (MonitoringPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MonitoringPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MonitoringPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		QosPackageImpl theQosPackage = (QosPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) instanceof QosPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(QosPackage.eNS_URI) : QosPackage.eINSTANCE);
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		TypeRepositoryPackageImpl theTypeRepositoryPackage = (TypeRepositoryPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) instanceof TypeRepositoryPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI) : TypeRepositoryPackage.eINSTANCE);
		ResourceTypesPackageImpl theResourceTypesPackage = (ResourceTypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) instanceof ResourceTypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ResourceTypesPackage.eNS_URI) : ResourceTypesPackage.eINSTANCE);
		ExecutionEnvironmentPackageImpl theExecutionEnvironmentPackage = (ExecutionEnvironmentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) instanceof ExecutionEnvironmentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI) : ExecutionEnvironmentPackage.eINSTANCE);
		ComponentAssemblyPackageImpl theComponentAssemblyPackage = (ComponentAssemblyPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) instanceof ComponentAssemblyPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI) : ComponentAssemblyPackage.eINSTANCE);
		ComponentDeploymentPackageImpl theComponentDeploymentPackage = (ComponentDeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) instanceof ComponentDeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI) : ComponentDeploymentPackage.eINSTANCE);
		PlanPackageImpl thePlanPackage = (PlanPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) instanceof PlanPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PlanPackage.eNS_URI) : PlanPackage.eINSTANCE);
		SpecificationPackageImpl theSpecificationPackage = (SpecificationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) instanceof SpecificationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SpecificationPackage.eNS_URI) : SpecificationPackage.eINSTANCE);
		AdaptationPackageImpl theAdaptationPackage = (AdaptationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) instanceof AdaptationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AdaptationPackage.eNS_URI) : AdaptationPackage.eINSTANCE);
		UsagePackageImpl theUsagePackage = (UsagePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) instanceof UsagePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UsagePackage.eNS_URI) : UsagePackage.eINSTANCE);

		// Create package meta-data objects
		theMonitoringPackage.createPackageContents();
		theQosPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theTypeRepositoryPackage.createPackageContents();
		theResourceTypesPackage.createPackageContents();
		theExecutionEnvironmentPackage.createPackageContents();
		theComponentAssemblyPackage.createPackageContents();
		theComponentDeploymentPackage.createPackageContents();
		thePlanPackage.createPackageContents();
		theSpecificationPackage.createPackageContents();
		theAdaptationPackage.createPackageContents();
		theUsagePackage.createPackageContents();

		// Initialize created meta-data
		theMonitoringPackage.initializePackageContents();
		theQosPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theTypeRepositoryPackage.initializePackageContents();
		theResourceTypesPackage.initializePackageContents();
		theExecutionEnvironmentPackage.initializePackageContents();
		theComponentAssemblyPackage.initializePackageContents();
		theComponentDeploymentPackage.initializePackageContents();
		thePlanPackage.initializePackageContents();
		theSpecificationPackage.initializePackageContents();
		theAdaptationPackage.initializePackageContents();
		theUsagePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMonitoringPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MonitoringPackage.eNS_URI, theMonitoringPackage);
		return theMonitoringPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationExecution() {
		return operationExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationExecution_TraceId() {
		return (EAttribute)operationExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationExecution_Eoi() {
		return (EAttribute)operationExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationExecution_Ess() {
		return (EAttribute)operationExecutionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationExecution_Tin() {
		return (EAttribute)operationExecutionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationExecution_Tout() {
		return (EAttribute)operationExecutionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getOperationExecution_SessionId() {
		return (EAttribute)operationExecutionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeploymentComponentOperationExecution() {
		return deploymentComponentOperationExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeploymentComponentOperationExecution_DeploymentComponent() {
		return (EReference)deploymentComponentOperationExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDeploymentComponentOperationExecution_Operation() {
		return (EReference)deploymentComponentOperationExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnectorOperationExecution() {
		return connectorOperationExecutionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorOperationExecution_AssemblyConnector() {
		return (EReference)connectorOperationExecutionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnectorOperationExecution_ExecutionContainer() {
		return (EReference)connectorOperationExecutionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceUtilization() {
		return resourceUtilizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceUtilization_Utilization() {
		return (EAttribute)resourceUtilizationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getResourceMeasurement() {
		return resourceMeasurementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getResourceMeasurement_Resource() {
		return (EReference)resourceMeasurementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getResourceMeasurement_Timestamp() {
		return (EAttribute)resourceMeasurementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMemSwapUsage() {
		return memSwapUsageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMemSwapUsage_MemUsedBytes() {
		return (EAttribute)memSwapUsageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMemSwapUsage_MemFreeBytes() {
		return (EAttribute)memSwapUsageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMemSwapUsage_SwapUsedBytes() {
		return (EAttribute)memSwapUsageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMemSwapUsage_SwapFreeBytes() {
		return (EAttribute)memSwapUsageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCPUUtilization() {
		return cpuUtilizationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_User() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_System() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_Wait() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_Nice() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_Irq() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_Combined() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCPUUtilization_Idle() {
		return (EAttribute)cpuUtilizationEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MonitoringFactory getMonitoringFactory() {
		return (MonitoringFactory)getEFactoryInstance();
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
		operationExecutionEClass = createEClass(OPERATION_EXECUTION);
		createEAttribute(operationExecutionEClass, OPERATION_EXECUTION__TRACE_ID);
		createEAttribute(operationExecutionEClass, OPERATION_EXECUTION__EOI);
		createEAttribute(operationExecutionEClass, OPERATION_EXECUTION__ESS);
		createEAttribute(operationExecutionEClass, OPERATION_EXECUTION__TIN);
		createEAttribute(operationExecutionEClass, OPERATION_EXECUTION__TOUT);
		createEAttribute(operationExecutionEClass, OPERATION_EXECUTION__SESSION_ID);

		deploymentComponentOperationExecutionEClass = createEClass(DEPLOYMENT_COMPONENT_OPERATION_EXECUTION);
		createEReference(deploymentComponentOperationExecutionEClass, DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT);
		createEReference(deploymentComponentOperationExecutionEClass, DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION);

		connectorOperationExecutionEClass = createEClass(CONNECTOR_OPERATION_EXECUTION);
		createEReference(connectorOperationExecutionEClass, CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR);
		createEReference(connectorOperationExecutionEClass, CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER);

		resourceUtilizationEClass = createEClass(RESOURCE_UTILIZATION);
		createEAttribute(resourceUtilizationEClass, RESOURCE_UTILIZATION__UTILIZATION);

		resourceMeasurementEClass = createEClass(RESOURCE_MEASUREMENT);
		createEReference(resourceMeasurementEClass, RESOURCE_MEASUREMENT__RESOURCE);
		createEAttribute(resourceMeasurementEClass, RESOURCE_MEASUREMENT__TIMESTAMP);

		memSwapUsageEClass = createEClass(MEM_SWAP_USAGE);
		createEAttribute(memSwapUsageEClass, MEM_SWAP_USAGE__MEM_USED_BYTES);
		createEAttribute(memSwapUsageEClass, MEM_SWAP_USAGE__MEM_FREE_BYTES);
		createEAttribute(memSwapUsageEClass, MEM_SWAP_USAGE__SWAP_USED_BYTES);
		createEAttribute(memSwapUsageEClass, MEM_SWAP_USAGE__SWAP_FREE_BYTES);

		cpuUtilizationEClass = createEClass(CPU_UTILIZATION);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__USER);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__SYSTEM);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__WAIT);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__NICE);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__IRQ);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__COMBINED);
		createEAttribute(cpuUtilizationEClass, CPU_UTILIZATION__IDLE);
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
		ComponentDeploymentPackage theComponentDeploymentPackage = (ComponentDeploymentPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentDeploymentPackage.eNS_URI);
		TypeRepositoryPackage theTypeRepositoryPackage = (TypeRepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(TypeRepositoryPackage.eNS_URI);
		ComponentAssemblyPackage theComponentAssemblyPackage = (ComponentAssemblyPackage)EPackage.Registry.INSTANCE.getEPackage(ComponentAssemblyPackage.eNS_URI);
		ExecutionEnvironmentPackage theExecutionEnvironmentPackage = (ExecutionEnvironmentPackage)EPackage.Registry.INSTANCE.getEPackage(ExecutionEnvironmentPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		operationExecutionEClass.getESuperTypes().add(theCorePackage.getIEvent());
		deploymentComponentOperationExecutionEClass.getESuperTypes().add(this.getOperationExecution());
		connectorOperationExecutionEClass.getESuperTypes().add(this.getOperationExecution());
		resourceUtilizationEClass.getESuperTypes().add(this.getResourceMeasurement());
		resourceMeasurementEClass.getESuperTypes().add(theCorePackage.getIEvent());
		memSwapUsageEClass.getESuperTypes().add(this.getResourceMeasurement());
		cpuUtilizationEClass.getESuperTypes().add(this.getResourceMeasurement());

		// Initialize classes and features; add operations and parameters
		initEClass(operationExecutionEClass, OperationExecution.class, "OperationExecution", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperationExecution_TraceId(), ecorePackage.getELong(), "traceId", null, 1, 1, OperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getOperationExecution_Eoi(), ecorePackage.getEInt(), "eoi", null, 1, 1, OperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getOperationExecution_Ess(), ecorePackage.getEInt(), "ess", null, 1, 1, OperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getOperationExecution_Tin(), ecorePackage.getELong(), "tin", null, 1, 1, OperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getOperationExecution_Tout(), ecorePackage.getELong(), "tout", null, 1, 1, OperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getOperationExecution_SessionId(), ecorePackage.getEString(), "sessionId", null, 1, 1, OperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(deploymentComponentOperationExecutionEClass, DeploymentComponentOperationExecution.class, "DeploymentComponentOperationExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDeploymentComponentOperationExecution_DeploymentComponent(), theComponentDeploymentPackage.getDeploymentComponent(), null, "deploymentComponent", null, 1, 1, DeploymentComponentOperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDeploymentComponentOperationExecution_Operation(), theTypeRepositoryPackage.getOperation(), null, "operation", null, 1, 1, DeploymentComponentOperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectorOperationExecutionEClass, ConnectorOperationExecution.class, "ConnectorOperationExecution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnectorOperationExecution_AssemblyConnector(), theComponentAssemblyPackage.getAssemblyComponentConnector(), null, "assemblyConnector", null, 1, 1, ConnectorOperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getConnectorOperationExecution_ExecutionContainer(), theExecutionEnvironmentPackage.getExecutionContainer(), null, "executionContainer", null, 1, 1, ConnectorOperationExecution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(resourceUtilizationEClass, ResourceUtilization.class, "ResourceUtilization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceUtilization_Utilization(), ecorePackage.getEDouble(), "utilization", null, 1, 1, ResourceUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(resourceMeasurementEClass, ResourceMeasurement.class, "ResourceMeasurement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceMeasurement_Resource(), theExecutionEnvironmentPackage.getResource(), null, "resource", null, 1, 1, ResourceMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getResourceMeasurement_Timestamp(), ecorePackage.getELong(), "timestamp", null, 1, 1, ResourceMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(memSwapUsageEClass, MemSwapUsage.class, "MemSwapUsage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMemSwapUsage_MemUsedBytes(), ecorePackage.getELong(), "memUsedBytes", null, 1, 1, MemSwapUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMemSwapUsage_MemFreeBytes(), ecorePackage.getELong(), "memFreeBytes", null, 1, 1, MemSwapUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMemSwapUsage_SwapUsedBytes(), ecorePackage.getELong(), "swapUsedBytes", null, 1, 1, MemSwapUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMemSwapUsage_SwapFreeBytes(), ecorePackage.getELong(), "swapFreeBytes", null, 1, 1, MemSwapUsage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(cpuUtilizationEClass, CPUUtilization.class, "CPUUtilization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCPUUtilization_User(), ecorePackage.getEDouble(), "user", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUUtilization_System(), ecorePackage.getEDouble(), "system", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUUtilization_Wait(), ecorePackage.getEDouble(), "wait", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUUtilization_Nice(), ecorePackage.getEDouble(), "nice", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUUtilization_Irq(), ecorePackage.getEDouble(), "irq", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUUtilization_Combined(), ecorePackage.getEDouble(), "combined", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getCPUUtilization_Idle(), ecorePackage.getEDouble(), "idle", null, 1, 1, CPUUtilization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //MonitoringPackageImpl
