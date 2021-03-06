/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;

import kieker.tools.slastic.metamodel.adaptation.impl.AdaptationPackageImpl;

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

import kieker.tools.slastic.metamodel.usage.AssemblyComponentConnectorCallFrequency;
import kieker.tools.slastic.metamodel.usage.AssemblyConnectorCallFrequency;
import kieker.tools.slastic.metamodel.usage.CallingRelationship;
import kieker.tools.slastic.metamodel.usage.ExecutionTrace;
import kieker.tools.slastic.metamodel.usage.FrequencyDistribution;
import kieker.tools.slastic.metamodel.usage.InvalidExecutionTrace;
import kieker.tools.slastic.metamodel.usage.InvalidTrace;
import kieker.tools.slastic.metamodel.usage.Message;
import kieker.tools.slastic.metamodel.usage.MessageTrace;
import kieker.tools.slastic.metamodel.usage.OperationCallFrequency;
import kieker.tools.slastic.metamodel.usage.SynchronousCallMessage;
import kieker.tools.slastic.metamodel.usage.SynchronousReplyMessage;
import kieker.tools.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency;
import kieker.tools.slastic.metamodel.usage.Trace;
import kieker.tools.slastic.metamodel.usage.UsageFactory;
import kieker.tools.slastic.metamodel.usage.UsageModel;
import kieker.tools.slastic.metamodel.usage.UsagePackage;
import kieker.tools.slastic.metamodel.usage.ValidExecutionTrace;
import kieker.tools.slastic.metamodel.usage.ValidTrace;

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
	private EClass assemblyComponentConnectorCallFrequencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assemblyConnectorCallFrequencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass systemProvidedInterfaceDelegationConnectorFrequencyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass traceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass validExecutionTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass executionTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidExecutionTraceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronousCallMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass synchronousReplyMessageEClass = null;

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
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#eNS_URI
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
	public EReference getUsageModel_AssemblyComponentConnectorCallFrequencies() {
		return (EReference)usageModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUsageModel_SystemProvidedInterfaceDelegationConnectorFrequencies() {
		return (EReference)usageModelEClass.getEStructuralFeatures().get(3);
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
	public EClass getAssemblyComponentConnectorCallFrequency() {
		return assemblyComponentConnectorCallFrequencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAssemblyComponentConnectorCallFrequency_Connector() {
		return (EReference)assemblyComponentConnectorCallFrequencyEClass.getEStructuralFeatures().get(0);
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
	public EReference getAssemblyConnectorCallFrequency_Signature() {
		return (EReference)assemblyConnectorCallFrequencyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSystemProvidedInterfaceDelegationConnectorFrequency() {
		return systemProvidedInterfaceDelegationConnectorFrequencyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSystemProvidedInterfaceDelegationConnectorFrequency_Connector() {
		return (EReference)systemProvidedInterfaceDelegationConnectorFrequencyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrace() {
		return traceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessageTrace() {
		return messageTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageTrace_Messages() {
		return (EReference)messageTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessageTrace_ExecutionTrace() {
		return (EReference)messageTraceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValidTrace() {
		return validTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getValidTrace_TraceId() {
		return (EAttribute)validTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMessage() {
		return messageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessage_Sender() {
		return (EReference)messageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getMessage_Timestamp() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMessage_Receiver() {
		return (EReference)messageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getValidExecutionTrace() {
		return validExecutionTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getValidExecutionTrace_MessageTrace() {
		return (EReference)validExecutionTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExecutionTrace() {
		return executionTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExecutionTrace_OperationExecutions() {
		return (EReference)executionTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvalidTrace() {
		return invalidTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvalidExecutionTrace() {
		return invalidExecutionTraceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getInvalidExecutionTrace_ErrorMsg() {
		return (EAttribute)invalidExecutionTraceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronousCallMessage() {
		return synchronousCallMessageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSynchronousReplyMessage() {
		return synchronousReplyMessageEClass;
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
		createEReference(usageModelEClass, USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES);
		createEReference(usageModelEClass, USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES);

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

		assemblyComponentConnectorCallFrequencyEClass = createEClass(ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCY);
		createEReference(assemblyComponentConnectorCallFrequencyEClass, ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCY__CONNECTOR);

		assemblyConnectorCallFrequencyEClass = createEClass(ASSEMBLY_CONNECTOR_CALL_FREQUENCY);
		createEAttribute(assemblyConnectorCallFrequencyEClass, ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY);
		createEReference(assemblyConnectorCallFrequencyEClass, ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE);

		systemProvidedInterfaceDelegationConnectorFrequencyEClass = createEClass(SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY);
		createEReference(systemProvidedInterfaceDelegationConnectorFrequencyEClass, SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR);

		traceEClass = createEClass(TRACE);

		messageTraceEClass = createEClass(MESSAGE_TRACE);
		createEReference(messageTraceEClass, MESSAGE_TRACE__MESSAGES);
		createEReference(messageTraceEClass, MESSAGE_TRACE__EXECUTION_TRACE);

		validTraceEClass = createEClass(VALID_TRACE);
		createEAttribute(validTraceEClass, VALID_TRACE__TRACE_ID);

		messageEClass = createEClass(MESSAGE);
		createEReference(messageEClass, MESSAGE__SENDER);
		createEAttribute(messageEClass, MESSAGE__TIMESTAMP);
		createEReference(messageEClass, MESSAGE__RECEIVER);

		validExecutionTraceEClass = createEClass(VALID_EXECUTION_TRACE);
		createEReference(validExecutionTraceEClass, VALID_EXECUTION_TRACE__MESSAGE_TRACE);

		executionTraceEClass = createEClass(EXECUTION_TRACE);
		createEReference(executionTraceEClass, EXECUTION_TRACE__OPERATION_EXECUTIONS);

		invalidTraceEClass = createEClass(INVALID_TRACE);

		invalidExecutionTraceEClass = createEClass(INVALID_EXECUTION_TRACE);
		createEAttribute(invalidExecutionTraceEClass, INVALID_EXECUTION_TRACE__ERROR_MSG);

		synchronousCallMessageEClass = createEClass(SYNCHRONOUS_CALL_MESSAGE);

		synchronousReplyMessageEClass = createEClass(SYNCHRONOUS_REPLY_MESSAGE);
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
		MonitoringPackage theMonitoringPackage = (MonitoringPackage)EPackage.Registry.INSTANCE.getEPackage(MonitoringPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		usageModelEClass.getESuperTypes().add(theCorePackage.getSLAsticModel());
		assemblyComponentConnectorCallFrequencyEClass.getESuperTypes().add(this.getAssemblyConnectorCallFrequency());
		systemProvidedInterfaceDelegationConnectorFrequencyEClass.getESuperTypes().add(this.getAssemblyConnectorCallFrequency());
		messageTraceEClass.getESuperTypes().add(this.getValidTrace());
		validTraceEClass.getESuperTypes().add(this.getTrace());
		validExecutionTraceEClass.getESuperTypes().add(this.getExecutionTrace());
		validExecutionTraceEClass.getESuperTypes().add(this.getValidTrace());
		executionTraceEClass.getESuperTypes().add(this.getTrace());
		invalidTraceEClass.getESuperTypes().add(this.getTrace());
		invalidExecutionTraceEClass.getESuperTypes().add(this.getExecutionTrace());
		invalidExecutionTraceEClass.getESuperTypes().add(this.getInvalidTrace());
		synchronousCallMessageEClass.getESuperTypes().add(this.getMessage());
		synchronousReplyMessageEClass.getESuperTypes().add(this.getMessage());

		// Initialize classes and features; add operations and parameters
		initEClass(usageModelEClass, UsageModel.class, "UsageModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUsageModel_CallingRelationships(), this.getCallingRelationship(), null, "callingRelationships", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUsageModel_OperationCallFrequencies(), this.getOperationCallFrequency(), null, "operationCallFrequencies", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUsageModel_AssemblyComponentConnectorCallFrequencies(), this.getAssemblyComponentConnectorCallFrequency(), null, "assemblyComponentConnectorCallFrequencies", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getUsageModel_SystemProvidedInterfaceDelegationConnectorFrequencies(), this.getSystemProvidedInterfaceDelegationConnectorFrequency(), null, "systemProvidedInterfaceDelegationConnectorFrequencies", null, 0, -1, UsageModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(callingRelationshipEClass, CallingRelationship.class, "CallingRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCallingRelationship_CalledInterface(), theTypeRepositoryPackage.getInterface(), null, "calledInterface", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCallingRelationship_CalledSignature(), theTypeRepositoryPackage.getSignature(), null, "calledSignature", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCallingRelationship_CallingOperation(), theTypeRepositoryPackage.getOperation(), null, "callingOperation", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getCallingRelationship_FrequencyDistribution(), this.getFrequencyDistribution(), null, "frequencyDistribution", null, 1, 1, CallingRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(frequencyDistributionEClass, FrequencyDistribution.class, "FrequencyDistribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFrequencyDistribution_Values(), ecorePackage.getELong(), "values", null, 0, -1, FrequencyDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFrequencyDistribution_Frequencies(), ecorePackage.getELong(), "frequencies", null, 0, -1, FrequencyDistribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(operationCallFrequencyEClass, OperationCallFrequency.class, "OperationCallFrequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOperationCallFrequency_Frequency(), ecorePackage.getELong(), "frequency", null, 1, 1, OperationCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getOperationCallFrequency_Operation(), theTypeRepositoryPackage.getOperation(), null, "operation", null, 1, 1, OperationCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assemblyComponentConnectorCallFrequencyEClass, AssemblyComponentConnectorCallFrequency.class, "AssemblyComponentConnectorCallFrequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAssemblyComponentConnectorCallFrequency_Connector(), theComponentAssemblyPackage.getAssemblyComponentConnector(), null, "connector", null, 1, 1, AssemblyComponentConnectorCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(assemblyConnectorCallFrequencyEClass, AssemblyConnectorCallFrequency.class, "AssemblyConnectorCallFrequency", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAssemblyConnectorCallFrequency_Frequency(), ecorePackage.getELong(), "frequency", null, 1, 1, AssemblyConnectorCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAssemblyConnectorCallFrequency_Signature(), theTypeRepositoryPackage.getSignature(), null, "signature", null, 1, 1, AssemblyConnectorCallFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(systemProvidedInterfaceDelegationConnectorFrequencyEClass, SystemProvidedInterfaceDelegationConnectorFrequency.class, "SystemProvidedInterfaceDelegationConnectorFrequency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSystemProvidedInterfaceDelegationConnectorFrequency_Connector(), theComponentAssemblyPackage.getSystemProvidedInterfaceDelegationConnector(), null, "connector", null, 1, 1, SystemProvidedInterfaceDelegationConnectorFrequency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(traceEClass, Trace.class, "Trace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(messageTraceEClass, MessageTrace.class, "MessageTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMessageTrace_Messages(), this.getMessage(), null, "messages", null, 2, -1, MessageTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMessageTrace_ExecutionTrace(), this.getValidExecutionTrace(), this.getValidExecutionTrace_MessageTrace(), "executionTrace", null, 1, 1, MessageTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(validTraceEClass, ValidTrace.class, "ValidTrace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValidTrace_TraceId(), ecorePackage.getELong(), "traceId", null, 1, 1, ValidTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(messageEClass, Message.class, "Message", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMessage_Sender(), theMonitoringPackage.getOperationExecution(), null, "sender", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getMessage_Timestamp(), ecorePackage.getELong(), "timestamp", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getMessage_Receiver(), theMonitoringPackage.getOperationExecution(), null, "receiver", null, 1, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(validExecutionTraceEClass, ValidExecutionTrace.class, "ValidExecutionTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getValidExecutionTrace_MessageTrace(), this.getMessageTrace(), this.getMessageTrace_ExecutionTrace(), "messageTrace", null, 1, 1, ValidExecutionTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(executionTraceEClass, ExecutionTrace.class, "ExecutionTrace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExecutionTrace_OperationExecutions(), theMonitoringPackage.getOperationExecution(), null, "operationExecutions", null, 1, -1, ExecutionTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(invalidTraceEClass, InvalidTrace.class, "InvalidTrace", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(invalidExecutionTraceEClass, InvalidExecutionTrace.class, "InvalidExecutionTrace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInvalidExecutionTrace_ErrorMsg(), ecorePackage.getEString(), "errorMsg", null, 1, 1, InvalidExecutionTrace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(synchronousCallMessageEClass, SynchronousCallMessage.class, "SynchronousCallMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(synchronousReplyMessageEClass, SynchronousReplyMessage.class, "SynchronousReplyMessage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //UsagePackageImpl
