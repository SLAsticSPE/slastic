/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring.util;

import de.cau.se.slastic.metamodel.core.IEvent;
import de.cau.se.slastic.metamodel.core.KiekerAnalysisEvent;

import de.cau.se.slastic.metamodel.monitoring.*;

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
 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage
 * @generated
 */
public class MonitoringSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MonitoringPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MonitoringSwitch() {
		if (modelPackage == null) {
			modelPackage = MonitoringPackage.eINSTANCE;
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
			case MonitoringPackage.OPERATION_EXECUTION: {
				OperationExecution operationExecution = (OperationExecution)theEObject;
				T result = caseOperationExecution(operationExecution);
				if (result == null) result = caseIEvent(operationExecution);
				if (result == null) result = caseKiekerAnalysisEvent(operationExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION: {
				DeploymentComponentOperationExecution deploymentComponentOperationExecution = (DeploymentComponentOperationExecution)theEObject;
				T result = caseDeploymentComponentOperationExecution(deploymentComponentOperationExecution);
				if (result == null) result = caseOperationExecution(deploymentComponentOperationExecution);
				if (result == null) result = caseIEvent(deploymentComponentOperationExecution);
				if (result == null) result = caseKiekerAnalysisEvent(deploymentComponentOperationExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION: {
				ConnectorOperationExecution connectorOperationExecution = (ConnectorOperationExecution)theEObject;
				T result = caseConnectorOperationExecution(connectorOperationExecution);
				if (result == null) result = caseOperationExecution(connectorOperationExecution);
				if (result == null) result = caseIEvent(connectorOperationExecution);
				if (result == null) result = caseKiekerAnalysisEvent(connectorOperationExecution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MonitoringPackage.RESOURCE_UTILIZATION: {
				ResourceUtilization resourceUtilization = (ResourceUtilization)theEObject;
				T result = caseResourceUtilization(resourceUtilization);
				if (result == null) result = caseResourceMeasurement(resourceUtilization);
				if (result == null) result = caseIEvent(resourceUtilization);
				if (result == null) result = caseKiekerAnalysisEvent(resourceUtilization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MonitoringPackage.RESOURCE_MEASUREMENT: {
				ResourceMeasurement resourceMeasurement = (ResourceMeasurement)theEObject;
				T result = caseResourceMeasurement(resourceMeasurement);
				if (result == null) result = caseIEvent(resourceMeasurement);
				if (result == null) result = caseKiekerAnalysisEvent(resourceMeasurement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MonitoringPackage.MEM_SWAP_USAGE: {
				MemSwapUsage memSwapUsage = (MemSwapUsage)theEObject;
				T result = caseMemSwapUsage(memSwapUsage);
				if (result == null) result = caseResourceMeasurement(memSwapUsage);
				if (result == null) result = caseIEvent(memSwapUsage);
				if (result == null) result = caseKiekerAnalysisEvent(memSwapUsage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MonitoringPackage.CPU_UTILIZATION: {
				CPUUtilization cpuUtilization = (CPUUtilization)theEObject;
				T result = caseCPUUtilization(cpuUtilization);
				if (result == null) result = caseResourceMeasurement(cpuUtilization);
				if (result == null) result = caseIEvent(cpuUtilization);
				if (result == null) result = caseKiekerAnalysisEvent(cpuUtilization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationExecution(OperationExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Component Operation Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Component Operation Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentComponentOperationExecution(DeploymentComponentOperationExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector Operation Execution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector Operation Execution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnectorOperationExecution(ConnectorOperationExecution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Utilization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Utilization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceUtilization(ResourceUtilization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Resource Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resource Measurement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseResourceMeasurement(ResourceMeasurement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mem Swap Usage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mem Swap Usage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMemSwapUsage(MemSwapUsage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CPU Utilization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CPU Utilization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCPUUtilization(CPUUtilization object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Kieker Analysis Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Kieker Analysis Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseKiekerAnalysisEvent(KiekerAnalysisEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IEvent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IEvent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIEvent(IEvent object) {
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

} //MonitoringSwitch
