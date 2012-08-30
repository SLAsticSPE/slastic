/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly.util;

import java.util.List;

import kieker.tools.slastic.metamodel.componentAssembly.*;

import kieker.tools.slastic.metamodel.core.Entity;
import kieker.tools.slastic.metamodel.core.FQNamedEntity;
import kieker.tools.slastic.metamodel.core.NamedEntity;
import kieker.tools.slastic.metamodel.core.SLAsticModel;

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
 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage
 * @generated
 */
public class ComponentAssemblySwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentAssemblyPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblySwitch() {
		if (modelPackage == null) {
			modelPackage = ComponentAssemblyPackage.eINSTANCE;
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT: {
				AssemblyComponent assemblyComponent = (AssemblyComponent)theEObject;
				T result = caseAssemblyComponent(assemblyComponent);
				if (result == null) result = caseFQNamedEntity(assemblyComponent);
				if (result == null) result = caseNamedEntity(assemblyComponent);
				if (result == null) result = caseEntity(assemblyComponent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR: {
				AssemblyComponentConnector assemblyComponentConnector = (AssemblyComponentConnector)theEObject;
				T result = caseAssemblyComponentConnector(assemblyComponentConnector);
				if (result == null) result = caseAssemblyConnector(assemblyComponentConnector);
				if (result == null) result = caseFQNamedEntity(assemblyComponentConnector);
				if (result == null) result = caseNamedEntity(assemblyComponentConnector);
				if (result == null) result = caseEntity(assemblyComponentConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR: {
				AssemblyConnector assemblyConnector = (AssemblyConnector)theEObject;
				T result = caseAssemblyConnector(assemblyConnector);
				if (result == null) result = caseFQNamedEntity(assemblyConnector);
				if (result == null) result = caseNamedEntity(assemblyConnector);
				if (result == null) result = caseEntity(assemblyConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentAssemblyPackage.SYSTEM_INTERFACE_DELEGATION_CONNECTOR: {
				SystemInterfaceDelegationConnector systemInterfaceDelegationConnector = (SystemInterfaceDelegationConnector)theEObject;
				T result = caseSystemInterfaceDelegationConnector(systemInterfaceDelegationConnector);
				if (result == null) result = caseAssemblyConnector(systemInterfaceDelegationConnector);
				if (result == null) result = caseFQNamedEntity(systemInterfaceDelegationConnector);
				if (result == null) result = caseNamedEntity(systemInterfaceDelegationConnector);
				if (result == null) result = caseEntity(systemInterfaceDelegationConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR: {
				SystemProvidedInterfaceDelegationConnector systemProvidedInterfaceDelegationConnector = (SystemProvidedInterfaceDelegationConnector)theEObject;
				T result = caseSystemProvidedInterfaceDelegationConnector(systemProvidedInterfaceDelegationConnector);
				if (result == null) result = caseSystemInterfaceDelegationConnector(systemProvidedInterfaceDelegationConnector);
				if (result == null) result = caseAssemblyConnector(systemProvidedInterfaceDelegationConnector);
				if (result == null) result = caseFQNamedEntity(systemProvidedInterfaceDelegationConnector);
				if (result == null) result = caseNamedEntity(systemProvidedInterfaceDelegationConnector);
				if (result == null) result = caseEntity(systemProvidedInterfaceDelegationConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR: {
				SystemRequiredInterfaceDelegationConnector systemRequiredInterfaceDelegationConnector = (SystemRequiredInterfaceDelegationConnector)theEObject;
				T result = caseSystemRequiredInterfaceDelegationConnector(systemRequiredInterfaceDelegationConnector);
				if (result == null) result = caseSystemInterfaceDelegationConnector(systemRequiredInterfaceDelegationConnector);
				if (result == null) result = caseAssemblyConnector(systemRequiredInterfaceDelegationConnector);
				if (result == null) result = caseFQNamedEntity(systemRequiredInterfaceDelegationConnector);
				if (result == null) result = caseNamedEntity(systemRequiredInterfaceDelegationConnector);
				if (result == null) result = caseEntity(systemRequiredInterfaceDelegationConnector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL: {
				ComponentAssemblyModel componentAssemblyModel = (ComponentAssemblyModel)theEObject;
				T result = caseComponentAssemblyModel(componentAssemblyModel);
				if (result == null) result = caseSLAsticModel(componentAssemblyModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyComponent(AssemblyComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Component Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Component Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyComponentConnector(AssemblyComponentConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Assembly Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Assembly Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssemblyConnector(AssemblyConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Interface Delegation Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Interface Delegation Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemInterfaceDelegationConnector(SystemInterfaceDelegationConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Provided Interface Delegation Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Provided Interface Delegation Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemProvidedInterfaceDelegationConnector(SystemProvidedInterfaceDelegationConnector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>System Required Interface Delegation Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>System Required Interface Delegation Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSystemRequiredInterfaceDelegationConnector(SystemRequiredInterfaceDelegationConnector object) {
		return null;
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
	public T caseComponentAssemblyModel(ComponentAssemblyModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedEntity(NamedEntity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>FQ Named Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>FQ Named Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFQNamedEntity(FQNamedEntity object) {
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

} //ComponentAssemblySwitch
