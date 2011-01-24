/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.executionEnvironment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage
 * @generated
 */
public interface ExecutionEnvironmentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExecutionEnvironmentFactory eINSTANCE = de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Execution Container</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Container</em>'.
	 * @generated
	 */
	ExecutionContainer createExecutionContainer();

	/**
	 * Returns a new object of class '<em>Network Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Network Link</em>'.
	 * @generated
	 */
	NetworkLink createNetworkLink();

	/**
	 * Returns a new object of class '<em>Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource</em>'.
	 * @generated
	 */
	Resource createResource();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	ExecutionEnvironmentModel createExecutionEnvironmentModel();

	/**
	 * Returns a new object of class '<em>Mem Swap Resource Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mem Swap Resource Specification</em>'.
	 * @generated
	 */
	MemSwapResourceSpecification createMemSwapResourceSpecification();

	/**
	 * Returns a new object of class '<em>Resource Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Specification</em>'.
	 * @generated
	 */
	ResourceSpecification createResourceSpecification();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ExecutionEnvironmentPackage getExecutionEnvironmentPackage();

} //ExecutionEnvironmentFactory
