/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import de.cau.se.slastic.metamodel.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.usage.UsageFactory
 * @model kind="package"
 * @generated
 */
public interface UsagePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "usage";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/usage.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.usage";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UsagePackage eINSTANCE = de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getUsageModel()
	 * @generated
	 */
	int USAGE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Calling Relationships</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_MODEL__CALLING_RELATIONSHIPS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operation Call Frequencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_MODEL__OPERATION_CALL_FREQUENCIES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Assembly Connector Call Frequencies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USAGE_MODEL_FEATURE_COUNT = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.CallingRelationshipImpl <em>Calling Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.CallingRelationshipImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getCallingRelationship()
	 * @generated
	 */
	int CALLING_RELATIONSHIP = 1;

	/**
	 * The feature id for the '<em><b>Called Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLING_RELATIONSHIP__CALLED_INTERFACE = 0;

	/**
	 * The feature id for the '<em><b>Called Signature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLING_RELATIONSHIP__CALLED_SIGNATURE = 1;

	/**
	 * The feature id for the '<em><b>Calling Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLING_RELATIONSHIP__CALLING_OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Frequency Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION = 3;

	/**
	 * The number of structural features of the '<em>Calling Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALLING_RELATIONSHIP_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.FrequencyDistributionImpl <em>Frequency Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.FrequencyDistributionImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getFrequencyDistribution()
	 * @generated
	 */
	int FREQUENCY_DISTRIBUTION = 2;

	/**
	 * The feature id for the '<em><b>Values</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREQUENCY_DISTRIBUTION__VALUES = 0;

	/**
	 * The feature id for the '<em><b>Frequencies</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREQUENCY_DISTRIBUTION__FREQUENCIES = 1;

	/**
	 * The number of structural features of the '<em>Frequency Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FREQUENCY_DISTRIBUTION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.OperationCallFrequencyImpl <em>Operation Call Frequency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.OperationCallFrequencyImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getOperationCallFrequency()
	 * @generated
	 */
	int OPERATION_CALL_FREQUENCY = 3;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_FREQUENCY__FREQUENCY = 0;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_FREQUENCY__OPERATION = 1;

	/**
	 * The number of structural features of the '<em>Operation Call Frequency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_CALL_FREQUENCY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl <em>Assembly Connector Call Frequency</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getAssemblyConnectorCallFrequency()
	 * @generated
	 */
	int ASSEMBLY_CONNECTOR_CALL_FREQUENCY = 4;

	/**
	 * The feature id for the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY = 0;

	/**
	 * The feature id for the '<em><b>Assembly Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTOR = 1;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE = 2;

	/**
	 * The number of structural features of the '<em>Assembly Connector Call Frequency</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR_CALL_FREQUENCY_FEATURE_COUNT = 3;


	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.TraceImpl <em>Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.TraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getTrace()
	 * @generated
	 */
	int TRACE = 5;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE__TRACE_ID = 0;

	/**
	 * The number of structural features of the '<em>Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRACE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.ValidTraceImpl <em>Valid Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.ValidTraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getValidTrace()
	 * @generated
	 */
	int VALID_TRACE = 7;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALID_TRACE__TRACE_ID = TRACE__TRACE_ID;

	/**
	 * The number of structural features of the '<em>Valid Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALID_TRACE_FEATURE_COUNT = TRACE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.MessageTraceImpl <em>Message Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.MessageTraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getMessageTrace()
	 * @generated
	 */
	int MESSAGE_TRACE = 6;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_TRACE__TRACE_ID = VALID_TRACE__TRACE_ID;

	/**
	 * The feature id for the '<em><b>Messages</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_TRACE__MESSAGES = VALID_TRACE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Message Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_TRACE_FEATURE_COUNT = VALID_TRACE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.MessageImpl <em>Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.MessageImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getMessage()
	 * @generated
	 */
	int MESSAGE = 8;

	/**
	 * The feature id for the '<em><b>Sender</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__SENDER = 0;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__RECEIVER = 1;

	/**
	 * The number of structural features of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.ExecutionTraceImpl <em>Execution Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.ExecutionTraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getExecutionTrace()
	 * @generated
	 */
	int EXECUTION_TRACE = 9;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TRACE__TRACE_ID = TRACE__TRACE_ID;

	/**
	 * The feature id for the '<em><b>Operation Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TRACE__OPERATION_EXECUTIONS = TRACE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Execution Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_TRACE_FEATURE_COUNT = TRACE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.ValidExecutionTraceImpl <em>Valid Execution Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.ValidExecutionTraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getValidExecutionTrace()
	 * @generated
	 */
	int VALID_EXECUTION_TRACE = 10;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALID_EXECUTION_TRACE__TRACE_ID = EXECUTION_TRACE__TRACE_ID;

	/**
	 * The feature id for the '<em><b>Operation Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALID_EXECUTION_TRACE__OPERATION_EXECUTIONS = EXECUTION_TRACE__OPERATION_EXECUTIONS;

	/**
	 * The feature id for the '<em><b>Message Trace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALID_EXECUTION_TRACE__MESSAGE_TRACE = EXECUTION_TRACE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Valid Execution Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALID_EXECUTION_TRACE_FEATURE_COUNT = EXECUTION_TRACE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.InvalidTraceImpl <em>Invalid Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.InvalidTraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getInvalidTrace()
	 * @generated
	 */
	int INVALID_TRACE = 11;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_TRACE__TRACE_ID = TRACE__TRACE_ID;

	/**
	 * The number of structural features of the '<em>Invalid Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_TRACE_FEATURE_COUNT = TRACE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.InvalidExecutionTraceImpl <em>Invalid Execution Trace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.InvalidExecutionTraceImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getInvalidExecutionTrace()
	 * @generated
	 */
	int INVALID_EXECUTION_TRACE = 12;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_EXECUTION_TRACE__TRACE_ID = EXECUTION_TRACE__TRACE_ID;

	/**
	 * The feature id for the '<em><b>Operation Executions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_EXECUTION_TRACE__OPERATION_EXECUTIONS = EXECUTION_TRACE__OPERATION_EXECUTIONS;

	/**
	 * The number of structural features of the '<em>Invalid Execution Trace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INVALID_EXECUTION_TRACE_FEATURE_COUNT = EXECUTION_TRACE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.SynchronousCallMessageImpl <em>Synchronous Call Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.SynchronousCallMessageImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getSynchronousCallMessage()
	 * @generated
	 */
	int SYNCHRONOUS_CALL_MESSAGE = 13;

	/**
	 * The feature id for the '<em><b>Sender</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_CALL_MESSAGE__SENDER = MESSAGE__SENDER;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_CALL_MESSAGE__RECEIVER = MESSAGE__RECEIVER;

	/**
	 * The number of structural features of the '<em>Synchronous Call Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_CALL_MESSAGE_FEATURE_COUNT = MESSAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.usage.impl.SynchronousReplyMessageImpl <em>Synchronous Reply Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.usage.impl.SynchronousReplyMessageImpl
	 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getSynchronousReplyMessage()
	 * @generated
	 */
	int SYNCHRONOUS_REPLY_MESSAGE = 14;

	/**
	 * The feature id for the '<em><b>Sender</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_REPLY_MESSAGE__SENDER = MESSAGE__SENDER;

	/**
	 * The feature id for the '<em><b>Receiver</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_REPLY_MESSAGE__RECEIVER = MESSAGE__RECEIVER;

	/**
	 * The number of structural features of the '<em>Synchronous Reply Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYNCHRONOUS_REPLY_MESSAGE_FEATURE_COUNT = MESSAGE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.UsageModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.UsageModel
	 * @generated
	 */
	EClass getUsageModel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.usage.UsageModel#getCallingRelationships <em>Calling Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Calling Relationships</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.UsageModel#getCallingRelationships()
	 * @see #getUsageModel()
	 * @generated
	 */
	EReference getUsageModel_CallingRelationships();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.usage.UsageModel#getOperationCallFrequencies <em>Operation Call Frequencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operation Call Frequencies</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.UsageModel#getOperationCallFrequencies()
	 * @see #getUsageModel()
	 * @generated
	 */
	EReference getUsageModel_OperationCallFrequencies();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.usage.UsageModel#getAssemblyConnectorCallFrequencies <em>Assembly Connector Call Frequencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assembly Connector Call Frequencies</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.UsageModel#getAssemblyConnectorCallFrequencies()
	 * @see #getUsageModel()
	 * @generated
	 */
	EReference getUsageModel_AssemblyConnectorCallFrequencies();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.CallingRelationship <em>Calling Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Calling Relationship</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.CallingRelationship
	 * @generated
	 */
	EClass getCallingRelationship();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.CallingRelationship#getCalledInterface <em>Called Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Called Interface</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.CallingRelationship#getCalledInterface()
	 * @see #getCallingRelationship()
	 * @generated
	 */
	EReference getCallingRelationship_CalledInterface();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.CallingRelationship#getCalledSignature <em>Called Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Called Signature</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.CallingRelationship#getCalledSignature()
	 * @see #getCallingRelationship()
	 * @generated
	 */
	EReference getCallingRelationship_CalledSignature();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.CallingRelationship#getCallingOperation <em>Calling Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Calling Operation</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.CallingRelationship#getCallingOperation()
	 * @see #getCallingRelationship()
	 * @generated
	 */
	EReference getCallingRelationship_CallingOperation();

	/**
	 * Returns the meta object for the containment reference '{@link de.cau.se.slastic.metamodel.usage.CallingRelationship#getFrequencyDistribution <em>Frequency Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Frequency Distribution</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.CallingRelationship#getFrequencyDistribution()
	 * @see #getCallingRelationship()
	 * @generated
	 */
	EReference getCallingRelationship_FrequencyDistribution();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.FrequencyDistribution <em>Frequency Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Frequency Distribution</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.FrequencyDistribution
	 * @generated
	 */
	EClass getFrequencyDistribution();

	/**
	 * Returns the meta object for the attribute list '{@link de.cau.se.slastic.metamodel.usage.FrequencyDistribution#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.FrequencyDistribution#getValues()
	 * @see #getFrequencyDistribution()
	 * @generated
	 */
	EAttribute getFrequencyDistribution_Values();

	/**
	 * Returns the meta object for the attribute list '{@link de.cau.se.slastic.metamodel.usage.FrequencyDistribution#getFrequencies <em>Frequencies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Frequencies</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.FrequencyDistribution#getFrequencies()
	 * @see #getFrequencyDistribution()
	 * @generated
	 */
	EAttribute getFrequencyDistribution_Frequencies();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.OperationCallFrequency <em>Operation Call Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Frequency</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.OperationCallFrequency
	 * @generated
	 */
	EClass getOperationCallFrequency();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.usage.OperationCallFrequency#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.OperationCallFrequency#getFrequency()
	 * @see #getOperationCallFrequency()
	 * @generated
	 */
	EAttribute getOperationCallFrequency_Frequency();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.OperationCallFrequency#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.OperationCallFrequency#getOperation()
	 * @see #getOperationCallFrequency()
	 * @generated
	 */
	EReference getOperationCallFrequency_Operation();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency <em>Assembly Connector Call Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly Connector Call Frequency</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency
	 * @generated
	 */
	EClass getAssemblyConnectorCallFrequency();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getFrequency <em>Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getFrequency()
	 * @see #getAssemblyConnectorCallFrequency()
	 * @generated
	 */
	EAttribute getAssemblyConnectorCallFrequency_Frequency();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getAssemblyConnector <em>Assembly Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Assembly Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getAssemblyConnector()
	 * @see #getAssemblyConnectorCallFrequency()
	 * @generated
	 */
	EReference getAssemblyConnectorCallFrequency_AssemblyConnector();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Signature</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getSignature()
	 * @see #getAssemblyConnectorCallFrequency()
	 * @generated
	 */
	EReference getAssemblyConnectorCallFrequency_Signature();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.Trace <em>Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.Trace
	 * @generated
	 */
	EClass getTrace();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.usage.Trace#getTraceId <em>Trace Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Id</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.Trace#getTraceId()
	 * @see #getTrace()
	 * @generated
	 */
	EAttribute getTrace_TraceId();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.MessageTrace <em>Message Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.MessageTrace
	 * @generated
	 */
	EClass getMessageTrace();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.MessageTrace#getMessages <em>Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Messages</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.MessageTrace#getMessages()
	 * @see #getMessageTrace()
	 * @generated
	 */
	EReference getMessageTrace_Messages();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.ValidTrace <em>Valid Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Valid Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.ValidTrace
	 * @generated
	 */
	EClass getValidTrace();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.Message <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.Message
	 * @generated
	 */
	EClass getMessage();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.Message#getSender <em>Sender</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sender</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.Message#getSender()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_Sender();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.Message#getReceiver <em>Receiver</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Receiver</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.Message#getReceiver()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_Receiver();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.ExecutionTrace <em>Execution Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.ExecutionTrace
	 * @generated
	 */
	EClass getExecutionTrace();

	/**
	 * Returns the meta object for the reference list '{@link de.cau.se.slastic.metamodel.usage.ExecutionTrace#getOperationExecutions <em>Operation Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Operation Executions</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.ExecutionTrace#getOperationExecutions()
	 * @see #getExecutionTrace()
	 * @generated
	 */
	EReference getExecutionTrace_OperationExecutions();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.ValidExecutionTrace <em>Valid Execution Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Valid Execution Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.ValidExecutionTrace
	 * @generated
	 */
	EClass getValidExecutionTrace();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.usage.ValidExecutionTrace#getMessageTrace <em>Message Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Message Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.ValidExecutionTrace#getMessageTrace()
	 * @see #getValidExecutionTrace()
	 * @generated
	 */
	EReference getValidExecutionTrace_MessageTrace();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.InvalidTrace <em>Invalid Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.InvalidTrace
	 * @generated
	 */
	EClass getInvalidTrace();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.InvalidExecutionTrace <em>Invalid Execution Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Execution Trace</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.InvalidExecutionTrace
	 * @generated
	 */
	EClass getInvalidExecutionTrace();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.SynchronousCallMessage <em>Synchronous Call Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronous Call Message</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.SynchronousCallMessage
	 * @generated
	 */
	EClass getSynchronousCallMessage();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage <em>Synchronous Reply Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Synchronous Reply Message</em>'.
	 * @see de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage
	 * @generated
	 */
	EClass getSynchronousReplyMessage();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UsageFactory getUsageFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getUsageModel()
		 * @generated
		 */
		EClass USAGE_MODEL = eINSTANCE.getUsageModel();

		/**
		 * The meta object literal for the '<em><b>Calling Relationships</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USAGE_MODEL__CALLING_RELATIONSHIPS = eINSTANCE.getUsageModel_CallingRelationships();

		/**
		 * The meta object literal for the '<em><b>Operation Call Frequencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USAGE_MODEL__OPERATION_CALL_FREQUENCIES = eINSTANCE.getUsageModel_OperationCallFrequencies();

		/**
		 * The meta object literal for the '<em><b>Assembly Connector Call Frequencies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES = eINSTANCE.getUsageModel_AssemblyConnectorCallFrequencies();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.CallingRelationshipImpl <em>Calling Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.CallingRelationshipImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getCallingRelationship()
		 * @generated
		 */
		EClass CALLING_RELATIONSHIP = eINSTANCE.getCallingRelationship();

		/**
		 * The meta object literal for the '<em><b>Called Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLING_RELATIONSHIP__CALLED_INTERFACE = eINSTANCE.getCallingRelationship_CalledInterface();

		/**
		 * The meta object literal for the '<em><b>Called Signature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLING_RELATIONSHIP__CALLED_SIGNATURE = eINSTANCE.getCallingRelationship_CalledSignature();

		/**
		 * The meta object literal for the '<em><b>Calling Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLING_RELATIONSHIP__CALLING_OPERATION = eINSTANCE.getCallingRelationship_CallingOperation();

		/**
		 * The meta object literal for the '<em><b>Frequency Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION = eINSTANCE.getCallingRelationship_FrequencyDistribution();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.FrequencyDistributionImpl <em>Frequency Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.FrequencyDistributionImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getFrequencyDistribution()
		 * @generated
		 */
		EClass FREQUENCY_DISTRIBUTION = eINSTANCE.getFrequencyDistribution();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FREQUENCY_DISTRIBUTION__VALUES = eINSTANCE.getFrequencyDistribution_Values();

		/**
		 * The meta object literal for the '<em><b>Frequencies</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FREQUENCY_DISTRIBUTION__FREQUENCIES = eINSTANCE.getFrequencyDistribution_Frequencies();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.OperationCallFrequencyImpl <em>Operation Call Frequency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.OperationCallFrequencyImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getOperationCallFrequency()
		 * @generated
		 */
		EClass OPERATION_CALL_FREQUENCY = eINSTANCE.getOperationCallFrequency();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_FREQUENCY__FREQUENCY = eINSTANCE.getOperationCallFrequency_Frequency();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_FREQUENCY__OPERATION = eINSTANCE.getOperationCallFrequency_Operation();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl <em>Assembly Connector Call Frequency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getAssemblyConnectorCallFrequency()
		 * @generated
		 */
		EClass ASSEMBLY_CONNECTOR_CALL_FREQUENCY = eINSTANCE.getAssemblyConnectorCallFrequency();

		/**
		 * The meta object literal for the '<em><b>Frequency</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY = eINSTANCE.getAssemblyConnectorCallFrequency_Frequency();

		/**
		 * The meta object literal for the '<em><b>Assembly Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTOR = eINSTANCE.getAssemblyConnectorCallFrequency_AssemblyConnector();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE = eINSTANCE.getAssemblyConnectorCallFrequency_Signature();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.TraceImpl <em>Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.TraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getTrace()
		 * @generated
		 */
		EClass TRACE = eINSTANCE.getTrace();

		/**
		 * The meta object literal for the '<em><b>Trace Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRACE__TRACE_ID = eINSTANCE.getTrace_TraceId();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.MessageTraceImpl <em>Message Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.MessageTraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getMessageTrace()
		 * @generated
		 */
		EClass MESSAGE_TRACE = eINSTANCE.getMessageTrace();

		/**
		 * The meta object literal for the '<em><b>Messages</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_TRACE__MESSAGES = eINSTANCE.getMessageTrace_Messages();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.ValidTraceImpl <em>Valid Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.ValidTraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getValidTrace()
		 * @generated
		 */
		EClass VALID_TRACE = eINSTANCE.getValidTrace();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.MessageImpl <em>Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.MessageImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getMessage()
		 * @generated
		 */
		EClass MESSAGE = eINSTANCE.getMessage();

		/**
		 * The meta object literal for the '<em><b>Sender</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__SENDER = eINSTANCE.getMessage_Sender();

		/**
		 * The meta object literal for the '<em><b>Receiver</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__RECEIVER = eINSTANCE.getMessage_Receiver();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.ExecutionTraceImpl <em>Execution Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.ExecutionTraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getExecutionTrace()
		 * @generated
		 */
		EClass EXECUTION_TRACE = eINSTANCE.getExecutionTrace();

		/**
		 * The meta object literal for the '<em><b>Operation Executions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_TRACE__OPERATION_EXECUTIONS = eINSTANCE.getExecutionTrace_OperationExecutions();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.ValidExecutionTraceImpl <em>Valid Execution Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.ValidExecutionTraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getValidExecutionTrace()
		 * @generated
		 */
		EClass VALID_EXECUTION_TRACE = eINSTANCE.getValidExecutionTrace();

		/**
		 * The meta object literal for the '<em><b>Message Trace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALID_EXECUTION_TRACE__MESSAGE_TRACE = eINSTANCE.getValidExecutionTrace_MessageTrace();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.InvalidTraceImpl <em>Invalid Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.InvalidTraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getInvalidTrace()
		 * @generated
		 */
		EClass INVALID_TRACE = eINSTANCE.getInvalidTrace();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.InvalidExecutionTraceImpl <em>Invalid Execution Trace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.InvalidExecutionTraceImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getInvalidExecutionTrace()
		 * @generated
		 */
		EClass INVALID_EXECUTION_TRACE = eINSTANCE.getInvalidExecutionTrace();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.SynchronousCallMessageImpl <em>Synchronous Call Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.SynchronousCallMessageImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getSynchronousCallMessage()
		 * @generated
		 */
		EClass SYNCHRONOUS_CALL_MESSAGE = eINSTANCE.getSynchronousCallMessage();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.usage.impl.SynchronousReplyMessageImpl <em>Synchronous Reply Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.usage.impl.SynchronousReplyMessageImpl
		 * @see de.cau.se.slastic.metamodel.usage.impl.UsagePackageImpl#getSynchronousReplyMessage()
		 * @generated
		 */
		EClass SYNCHRONOUS_REPLY_MESSAGE = eINSTANCE.getSynchronousReplyMessage();

	}

} //UsagePackage
