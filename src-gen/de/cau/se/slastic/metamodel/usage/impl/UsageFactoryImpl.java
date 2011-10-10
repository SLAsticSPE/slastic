/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.usage.*;

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
public class UsageFactoryImpl extends EFactoryImpl implements UsageFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UsageFactory init() {
		try {
			UsageFactory theUsageFactory = (UsageFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/usage.ecore"); 
			if (theUsageFactory != null) {
				return theUsageFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UsageFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageFactoryImpl() {
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
			case UsagePackage.USAGE_MODEL: return createUsageModel();
			case UsagePackage.CALLING_RELATIONSHIP: return createCallingRelationship();
			case UsagePackage.FREQUENCY_DISTRIBUTION: return createFrequencyDistribution();
			case UsagePackage.OPERATION_CALL_FREQUENCY: return createOperationCallFrequency();
			case UsagePackage.ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCY: return createAssemblyComponentConnectorCallFrequency();
			case UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY: return createSystemProvidedInterfaceDelegationConnectorFrequency();
			case UsagePackage.MESSAGE_TRACE: return createMessageTrace();
			case UsagePackage.MESSAGE: return createMessage();
			case UsagePackage.VALID_EXECUTION_TRACE: return createValidExecutionTrace();
			case UsagePackage.INVALID_EXECUTION_TRACE: return createInvalidExecutionTrace();
			case UsagePackage.SYNCHRONOUS_CALL_MESSAGE: return createSynchronousCallMessage();
			case UsagePackage.SYNCHRONOUS_REPLY_MESSAGE: return createSynchronousReplyMessage();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageModel createUsageModel() {
		UsageModelImpl usageModel = new UsageModelImpl();
		return usageModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallingRelationship createCallingRelationship() {
		CallingRelationshipImpl callingRelationship = new CallingRelationshipImpl();
		return callingRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FrequencyDistribution createFrequencyDistribution() {
		FrequencyDistributionImpl frequencyDistribution = new FrequencyDistributionImpl();
		return frequencyDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationCallFrequency createOperationCallFrequency() {
		OperationCallFrequencyImpl operationCallFrequency = new OperationCallFrequencyImpl();
		return operationCallFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponentConnectorCallFrequency createAssemblyComponentConnectorCallFrequency() {
		AssemblyComponentConnectorCallFrequencyImpl assemblyComponentConnectorCallFrequency = new AssemblyComponentConnectorCallFrequencyImpl();
		return assemblyComponentConnectorCallFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemProvidedInterfaceDelegationConnectorFrequency createSystemProvidedInterfaceDelegationConnectorFrequency() {
		SystemProvidedInterfaceDelegationConnectorFrequencyImpl systemProvidedInterfaceDelegationConnectorFrequency = new SystemProvidedInterfaceDelegationConnectorFrequencyImpl();
		return systemProvidedInterfaceDelegationConnectorFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageTrace createMessageTrace() {
		MessageTraceImpl messageTrace = new MessageTraceImpl();
		return messageTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Message createMessage() {
		MessageImpl message = new MessageImpl();
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidExecutionTrace createValidExecutionTrace() {
		ValidExecutionTraceImpl validExecutionTrace = new ValidExecutionTraceImpl();
		return validExecutionTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidExecutionTrace createInvalidExecutionTrace() {
		InvalidExecutionTraceImpl invalidExecutionTrace = new InvalidExecutionTraceImpl();
		return invalidExecutionTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronousCallMessage createSynchronousCallMessage() {
		SynchronousCallMessageImpl synchronousCallMessage = new SynchronousCallMessageImpl();
		return synchronousCallMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SynchronousReplyMessage createSynchronousReplyMessage() {
		SynchronousReplyMessageImpl synchronousReplyMessage = new SynchronousReplyMessageImpl();
		return synchronousReplyMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsagePackage getUsagePackage() {
		return (UsagePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UsagePackage getPackage() {
		return UsagePackage.eINSTANCE;
	}

} //UsageFactoryImpl
