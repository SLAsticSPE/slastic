/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage
 * @generated
 */
public interface UsageFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UsageFactory eINSTANCE = de.cau.se.slastic.metamodel.usage.impl.UsageFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	UsageModel createUsageModel();

	/**
	 * Returns a new object of class '<em>Calling Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Calling Relationship</em>'.
	 * @generated
	 */
	CallingRelationship createCallingRelationship();

	/**
	 * Returns a new object of class '<em>Frequency Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Frequency Distribution</em>'.
	 * @generated
	 */
	FrequencyDistribution createFrequencyDistribution();

	/**
	 * Returns a new object of class '<em>Operation Call Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Operation Call Frequency</em>'.
	 * @generated
	 */
	OperationCallFrequency createOperationCallFrequency();

	/**
	 * Returns a new object of class '<em>Assembly Connector Call Frequency</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Assembly Connector Call Frequency</em>'.
	 * @generated
	 */
	AssemblyConnectorCallFrequency createAssemblyConnectorCallFrequency();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UsagePackage getUsagePackage();

} //UsageFactory
