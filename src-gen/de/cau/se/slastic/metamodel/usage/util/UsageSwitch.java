/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.util;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

import de.cau.se.slastic.metamodel.usage.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage
 * @generated
 */
public class UsageSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UsagePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageSwitch() {
		if (modelPackage == null) {
			modelPackage = UsagePackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case UsagePackage.USAGE_MODEL: {
				UsageModel usageModel = (UsageModel)theEObject;
				T result = caseUsageModel(usageModel);
				if (result == null) result = caseSLAsticModel(usageModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.CALLING_RELATIONSHIP: {
				CallingRelationship callingRelationship = (CallingRelationship)theEObject;
				T result = caseCallingRelationship(callingRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.FREQUENCY_DISTRIBUTION: {
				FrequencyDistribution frequencyDistribution = (FrequencyDistribution)theEObject;
				T result = caseFrequencyDistribution(frequencyDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.OPERATION_CALL_FREQUENCY: {
				OperationCallFrequency operationCallFrequency = (OperationCallFrequency)theEObject;
				T result = caseOperationCallFrequency(operationCallFrequency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCY: {
				AssemblyComponentConnectorCallFrequency assemblyComponentConnectorCallFrequency = (AssemblyComponentConnectorCallFrequency)theEObject;
				T result = caseAssemblyComponentConnectorCallFrequency(assemblyComponentConnectorCallFrequency);
				if (result == null) result = caseAssemblyConnectorCallFrequency(assemblyComponentConnectorCallFrequency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY: {
				AssemblyConnectorCallFrequency assemblyConnectorCallFrequency = (AssemblyConnectorCallFrequency)theEObject;
				T result = caseAssemblyConnectorCallFrequency(assemblyConnectorCallFrequency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY: {
				SystemProvidedInterfaceDelegationConnectorFrequency systemProvidedInterfaceDelegationConnectorFrequency = (SystemProvidedInterfaceDelegationConnectorFrequency)theEObject;
				T result = caseSystemProvidedInterfaceDelegationConnectorFrequency(systemProvidedInterfaceDelegationConnectorFrequency);
				if (result == null) result = caseAssemblyConnectorCallFrequency(systemProvidedInterfaceDelegationConnectorFrequency);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.TRACE: {
				Trace trace = (Trace)theEObject;
				T result = caseTrace(trace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.MESSAGE_TRACE: {
				MessageTrace messageTrace = (MessageTrace)theEObject;
				T result = caseMessageTrace(messageTrace);
				if (result == null) result = caseValidTrace(messageTrace);
				if (result == null) result = caseTrace(messageTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.VALID_TRACE: {
				ValidTrace validTrace = (ValidTrace)theEObject;
				T result = caseValidTrace(validTrace);
				if (result == null) result = caseTrace(validTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.MESSAGE: {
				Message message = (Message)theEObject;
				T result = caseMessage(message);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.VALID_EXECUTION_TRACE: {
				ValidExecutionTrace validExecutionTrace = (ValidExecutionTrace)theEObject;
				T result = caseValidExecutionTrace(validExecutionTrace);
				if (result == null) result = caseExecutionTrace(validExecutionTrace);
				if (result == null) result = caseValidTrace(validExecutionTrace);
				if (result == null) result = caseTrace(validExecutionTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.EXECUTION_TRACE: {
				ExecutionTrace executionTrace = (ExecutionTrace)theEObject;
				T result = caseExecutionTrace(executionTrace);
				if (result == null) result = caseTrace(executionTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.INVALID_TRACE: {
				InvalidTrace invalidTrace = (InvalidTrace)theEObject;
				T result = caseInvalidTrace(invalidTrace);
				if (result == null) result = caseTrace(invalidTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.INVALID_EXECUTION_TRACE: {
				InvalidExecutionTrace invalidExecutionTrace = (InvalidExecutionTrace)theEObject;
				T result = caseInvalidExecutionTrace(invalidExecutionTrace);
				if (result == null) result = caseExecutionTrace(invalidExecutionTrace);
				if (result == null) result = caseInvalidTrace(invalidExecutionTrace);
				if (result == null) result = caseTrace(invalidExecutionTrace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.SYNCHRONOUS_CALL_MESSAGE: {
				SynchronousCallMessage synchronousCallMessage = (SynchronousCallMessage)theEObject;
				T result = caseSynchronousCallMessage(synchronousCallMessage);
				if (result == null) result = caseMessage(synchronousCallMessage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UsagePackage.SYNCHRONOUS_REPLY_MESSAGE: {
				SynchronousReplyMessage synchronousReplyMessage = (SynchronousReplyMessage)theEObject;
				T result = caseSynchronousReplyMessage(synchronousReplyMessage);
				if (result == null) result = caseMessage(synchronousReplyMessage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUsageModel(UsageModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Calling Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Calling Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCallingRelationship(CallingRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Frequency Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Frequency Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFrequencyDistribution(FrequencyDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Call Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Call Frequency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCallFrequency(OperationCallFrequency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Component Connector Call Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Component Connector Call Frequency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyComponentConnectorCallFrequency(AssemblyComponentConnectorCallFrequency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Connector Call Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Connector Call Frequency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyConnectorCallFrequency(AssemblyConnectorCallFrequency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Provided Interface Delegation Connector Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Provided Interface Delegation Connector Frequency</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemProvidedInterfaceDelegationConnectorFrequency(SystemProvidedInterfaceDelegationConnectorFrequency object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrace(Trace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessageTrace(MessageTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Valid Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Valid Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidTrace(ValidTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Message</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Message</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMessage(Message object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Valid Execution Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Valid Execution Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValidExecutionTrace(ValidExecutionTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionTrace(ExecutionTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidTrace(InvalidTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Execution Trace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Execution Trace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidExecutionTrace(InvalidExecutionTrace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Synchronous Call Message</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronous Call Message</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronousCallMessage(SynchronousCallMessage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Synchronous Reply Message</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Synchronous Reply Message</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSynchronousReplyMessage(SynchronousReplyMessage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>SL Astic Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>SL Astic Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSLAsticModel(SLAsticModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	public T defaultCase(EObject object) {
		return null;
	}

} //UsageSwitch
