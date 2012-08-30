/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage
 * @generated
 */
public interface PlanFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PlanFactory eINSTANCE = kieker.tools.slastic.metamodel.reconfiguration.plan.impl.PlanFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Reconfiguration Plan</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reconfiguration Plan</em>'.
	 * @generated
	 */
	ReconfigurationPlan createReconfigurationPlan();

	/**
	 * Returns a new object of class '<em>Component Replication</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Replication</em>'.
	 * @generated
	 */
	ComponentReplication createComponentReplication();

	/**
	 * Returns a new object of class '<em>Component Dereplication</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Dereplication</em>'.
	 * @generated
	 */
	ComponentDereplication createComponentDereplication();

	/**
	 * Returns a new object of class '<em>Component Migration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Migration</em>'.
	 * @generated
	 */
	ComponentMigration createComponentMigration();

	/**
	 * Returns a new object of class '<em>Container Allocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Allocation</em>'.
	 * @generated
	 */
	ContainerAllocation createContainerAllocation();

	/**
	 * Returns a new object of class '<em>Container Deallocation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Container Deallocation</em>'.
	 * @generated
	 */
	ContainerDeallocation createContainerDeallocation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PlanPackage getPlanPackage();

} //PlanFactory
