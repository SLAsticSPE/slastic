/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly;

import kieker.tools.slastic.metamodel.core.SLAsticModel;

import kieker.tools.slastic.metamodel.typeRepository.Interface;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaceDelegationConnectors <em>System Provided Interface Delegation Connectors</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaceDelegationConnectors <em>System Required Interface Delegation Connectors</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponents <em>Assembly Components</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponentConnectors <em>Assembly Component Connectors</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaces <em>System Provided Interfaces</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaces <em>System Required Interfaces</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel()
 * @model
 * @generated
 */
public interface ComponentAssemblyModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>System Provided Interface Delegation Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector}.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Provided Interface Delegation Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Provided Interface Delegation Connectors</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_SystemProvidedInterfaceDelegationConnectors()
	 * @see kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getComponentAssemblyModel
	 * @model opposite="componentAssemblyModel" containment="true" required="true" ordered="false"
	 * @generated
	 */
	EList<SystemProvidedInterfaceDelegationConnector> getSystemProvidedInterfaceDelegationConnectors();

	/**
	 * Returns the value of the '<em><b>System Required Interface Delegation Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector}.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Required Interface Delegation Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Required Interface Delegation Connectors</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_SystemRequiredInterfaceDelegationConnectors()
	 * @see kieker.tools.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getComponentAssemblyModel
	 * @model opposite="componentAssemblyModel" containment="true" ordered="false"
	 * @generated
	 */
	EList<SystemRequiredInterfaceDelegationConnector> getSystemRequiredInterfaceDelegationConnectors();

	/**
	 * Returns the value of the '<em><b>Assembly Components</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Components</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_AssemblyComponents()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AssemblyComponent> getAssemblyComponents();

	/**
	 * Returns the value of the '<em><b>Assembly Component Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Component Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Component Connectors</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_AssemblyComponentConnectors()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AssemblyComponentConnector> getAssemblyComponentConnectors();

	/**
	 * Returns the value of the '<em><b>System Provided Interfaces</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Provided Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Provided Interfaces</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_SystemProvidedInterfaces()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<Interface> getSystemProvidedInterfaces();

	/**
	 * Returns the value of the '<em><b>System Required Interfaces</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Required Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Required Interfaces</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_SystemRequiredInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Interface> getSystemRequiredInterfaces();

} // ComponentAssemblyModel
