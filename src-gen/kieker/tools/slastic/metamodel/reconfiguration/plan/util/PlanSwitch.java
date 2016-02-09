/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan.util;

import java.util.List;

import kieker.tools.slastic.metamodel.reconfiguration.plan.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage
 * @generated
 */
public class PlanSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PlanPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanSwitch() {
		if (modelPackage == null) {
			modelPackage = PlanPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case PlanPackage.RECONFIGURATION_PLAN: {
				ReconfigurationPlan reconfigurationPlan = (ReconfigurationPlan)theEObject;
				T result = caseReconfigurationPlan(reconfigurationPlan);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PlanPackage.RECONFIGURATION_OPERATION: {
				ReconfigurationOperation reconfigurationOperation = (ReconfigurationOperation)theEObject;
				T result = caseReconfigurationOperation(reconfigurationOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PlanPackage.COMPONENT_REPLICATION: {
				ComponentReplication componentReplication = (ComponentReplication)theEObject;
				T result = caseComponentReplication(componentReplication);
				if (result == null) result = caseReconfigurationOperation(componentReplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PlanPackage.COMPONENT_DEREPLICATION: {
				ComponentDereplication componentDereplication = (ComponentDereplication)theEObject;
				T result = caseComponentDereplication(componentDereplication);
				if (result == null) result = caseReconfigurationOperation(componentDereplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PlanPackage.COMPONENT_MIGRATION: {
				ComponentMigration componentMigration = (ComponentMigration)theEObject;
				T result = caseComponentMigration(componentMigration);
				if (result == null) result = caseReconfigurationOperation(componentMigration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PlanPackage.CONTAINER_ALLOCATION: {
				ContainerAllocation containerAllocation = (ContainerAllocation)theEObject;
				T result = caseContainerAllocation(containerAllocation);
				if (result == null) result = caseReconfigurationOperation(containerAllocation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case PlanPackage.CONTAINER_DEALLOCATION: {
				ContainerDeallocation containerDeallocation = (ContainerDeallocation)theEObject;
				T result = caseContainerDeallocation(containerDeallocation);
				if (result == null) result = caseReconfigurationOperation(containerDeallocation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reconfiguration Plan</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reconfiguration Plan</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReconfigurationPlan(ReconfigurationPlan object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reconfiguration Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reconfiguration Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReconfigurationOperation(ReconfigurationOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Replication</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Replication</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentReplication(ComponentReplication object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Dereplication</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Dereplication</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentDereplication(ComponentDereplication object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Migration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Migration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentMigration(ComponentMigration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Allocation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerAllocation(ContainerAllocation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container Deallocation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container Deallocation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainerDeallocation(ContainerDeallocation object) {
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
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //PlanSwitch
