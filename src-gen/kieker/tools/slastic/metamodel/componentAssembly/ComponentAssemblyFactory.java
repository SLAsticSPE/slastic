/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage
 * @generated
 */
public interface ComponentAssemblyFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentAssemblyFactory eINSTANCE = kieker.tools.slastic.metamodel.componentAssembly.impl.ComponentAssemblyFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Assembly Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assembly Component</em>'.
	 * @generated
	 */
	AssemblyComponent createAssemblyComponent();

	/**
	 * Returns a new object of class '<em>Assembly Component Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assembly Component Connector</em>'.
	 * @generated
	 */
	AssemblyComponentConnector createAssemblyComponentConnector();

	/**
	 * Returns a new object of class '<em>System Provided Interface Delegation Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Provided Interface Delegation Connector</em>'.
	 * @generated
	 */
	SystemProvidedInterfaceDelegationConnector createSystemProvidedInterfaceDelegationConnector();

	/**
	 * Returns a new object of class '<em>System Required Interface Delegation Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>System Required Interface Delegation Connector</em>'.
	 * @generated
	 */
	SystemRequiredInterfaceDelegationConnector createSystemRequiredInterfaceDelegationConnector();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	ComponentAssemblyModel createComponentAssemblyModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ComponentAssemblyPackage getComponentAssemblyPackage();

} //ComponentAssemblyFactory
