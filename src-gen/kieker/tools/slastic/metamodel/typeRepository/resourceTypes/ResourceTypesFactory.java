/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository.resourceTypes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage
 * @generated
 */
public interface ResourceTypesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ResourceTypesFactory eINSTANCE = kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>CPU Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CPU Type</em>'.
	 * @generated
	 */
	CPUType createCPUType();

	/**
	 * Returns a new object of class '<em>Generic Resource Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generic Resource Type</em>'.
	 * @generated
	 */
	GenericResourceType createGenericResourceType();

	/**
	 * Returns a new object of class '<em>Mem Swap Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mem Swap Type</em>'.
	 * @generated
	 */
	MemSwapType createMemSwapType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ResourceTypesPackage getResourceTypesPackage();

} //ResourceTypesFactory
