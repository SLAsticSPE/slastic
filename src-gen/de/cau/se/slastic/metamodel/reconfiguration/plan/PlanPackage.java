/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.reconfiguration.plan;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanFactory
 * @model kind="package"
 * @generated
 */
public interface PlanPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "plan";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/reconfiguration/plan.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.reconfiguration.plan";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PlanPackage eINSTANCE = de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationPlanImpl <em>Reconfiguration Plan</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationPlanImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getReconfigurationPlan()
	 * @generated
	 */
	int RECONFIGURATION_PLAN = 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_PLAN__OPERATIONS = 0;

	/**
	 * The number of structural features of the '<em>Reconfiguration Plan</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_PLAN_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationOperationImpl <em>Reconfiguration Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationOperationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getReconfigurationOperation()
	 * @generated
	 */
	int RECONFIGURATION_OPERATION = 1;

	/**
	 * The number of structural features of the '<em>Reconfiguration Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_OPERATION_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentReplicationImpl <em>Component Replication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentReplicationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getComponentReplication()
	 * @generated
	 */
	int COMPONENT_REPLICATION = 2;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REPLICATION__COMPONENT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REPLICATION__DESTINATION = RECONFIGURATION_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Component Replication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_REPLICATION_FEATURE_COUNT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentDereplicationImpl <em>Component Dereplication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentDereplicationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getComponentDereplication()
	 * @generated
	 */
	int COMPONENT_DEREPLICATION = 3;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEREPLICATION__COMPONENT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component Dereplication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEREPLICATION_FEATURE_COUNT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentMigrationImpl <em>Component Migration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentMigrationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getComponentMigration()
	 * @generated
	 */
	int COMPONENT_MIGRATION = 4;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_MIGRATION__COMPONENT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_MIGRATION__DESTINATION = RECONFIGURATION_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Component Migration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_MIGRATION_FEATURE_COUNT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerAllocationImpl <em>Container Allocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerAllocationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getContainerAllocation()
	 * @generated
	 */
	int CONTAINER_ALLOCATION = 5;

	/**
	 * The feature id for the '<em><b>Container Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_ALLOCATION__CONTAINER_TYPE = RECONFIGURATION_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container Allocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_ALLOCATION_FEATURE_COUNT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerDeallocationImpl <em>Container Deallocation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerDeallocationImpl
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getContainerDeallocation()
	 * @generated
	 */
	int CONTAINER_DEALLOCATION = 6;

	/**
	 * The feature id for the '<em><b>Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_DEALLOCATION__CONTAINER = RECONFIGURATION_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Container Deallocation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_DEALLOCATION_FEATURE_COUNT = RECONFIGURATION_OPERATION_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan <em>Reconfiguration Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconfiguration Plan</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan
	 * @generated
	 */
	EClass getReconfigurationPlan();

	/**
	 * Returns the meta object for the reference list '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Operations</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan#getOperations()
	 * @see #getReconfigurationPlan()
	 * @generated
	 */
	EReference getReconfigurationPlan_Operations();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationOperation <em>Reconfiguration Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconfiguration Operation</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ReconfigurationOperation
	 * @generated
	 */
	EClass getReconfigurationOperation();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication <em>Component Replication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Replication</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication
	 * @generated
	 */
	EClass getComponentReplication();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getComponent()
	 * @see #getComponentReplication()
	 * @generated
	 */
	EReference getComponentReplication_Component();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getDestination()
	 * @see #getComponentReplication()
	 * @generated
	 */
	EReference getComponentReplication_Destination();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentDereplication <em>Component Dereplication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Dereplication</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentDereplication
	 * @generated
	 */
	EClass getComponentDereplication();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentDereplication#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentDereplication#getComponent()
	 * @see #getComponentDereplication()
	 * @generated
	 */
	EReference getComponentDereplication_Component();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration <em>Component Migration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Migration</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration
	 * @generated
	 */
	EClass getComponentMigration();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getComponent()
	 * @see #getComponentMigration()
	 * @generated
	 */
	EReference getComponentMigration_Component();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getDestination()
	 * @see #getComponentMigration()
	 * @generated
	 */
	EReference getComponentMigration_Destination();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerAllocation <em>Container Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Allocation</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerAllocation
	 * @generated
	 */
	EClass getContainerAllocation();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerAllocation#getContainerType <em>Container Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Container Type</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerAllocation#getContainerType()
	 * @see #getContainerAllocation()
	 * @generated
	 */
	EReference getContainerAllocation_ContainerType();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerDeallocation <em>Container Deallocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container Deallocation</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerDeallocation
	 * @generated
	 */
	EClass getContainerDeallocation();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerDeallocation#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Container</em>'.
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.ContainerDeallocation#getContainer()
	 * @see #getContainerDeallocation()
	 * @generated
	 */
	EReference getContainerDeallocation_Container();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PlanFactory getPlanFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationPlanImpl <em>Reconfiguration Plan</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationPlanImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getReconfigurationPlan()
		 * @generated
		 */
		EClass RECONFIGURATION_PLAN = eINSTANCE.getReconfigurationPlan();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECONFIGURATION_PLAN__OPERATIONS = eINSTANCE.getReconfigurationPlan_Operations();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationOperationImpl <em>Reconfiguration Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ReconfigurationOperationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getReconfigurationOperation()
		 * @generated
		 */
		EClass RECONFIGURATION_OPERATION = eINSTANCE.getReconfigurationOperation();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentReplicationImpl <em>Component Replication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentReplicationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getComponentReplication()
		 * @generated
		 */
		EClass COMPONENT_REPLICATION = eINSTANCE.getComponentReplication();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_REPLICATION__COMPONENT = eINSTANCE.getComponentReplication_Component();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_REPLICATION__DESTINATION = eINSTANCE.getComponentReplication_Destination();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentDereplicationImpl <em>Component Dereplication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentDereplicationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getComponentDereplication()
		 * @generated
		 */
		EClass COMPONENT_DEREPLICATION = eINSTANCE.getComponentDereplication();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_DEREPLICATION__COMPONENT = eINSTANCE.getComponentDereplication_Component();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentMigrationImpl <em>Component Migration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ComponentMigrationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getComponentMigration()
		 * @generated
		 */
		EClass COMPONENT_MIGRATION = eINSTANCE.getComponentMigration();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_MIGRATION__COMPONENT = eINSTANCE.getComponentMigration_Component();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_MIGRATION__DESTINATION = eINSTANCE.getComponentMigration_Destination();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerAllocationImpl <em>Container Allocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerAllocationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getContainerAllocation()
		 * @generated
		 */
		EClass CONTAINER_ALLOCATION = eINSTANCE.getContainerAllocation();

		/**
		 * The meta object literal for the '<em><b>Container Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_ALLOCATION__CONTAINER_TYPE = eINSTANCE.getContainerAllocation_ContainerType();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerDeallocationImpl <em>Container Deallocation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.ContainerDeallocationImpl
		 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.impl.PlanPackageImpl#getContainerDeallocation()
		 * @generated
		 */
		EClass CONTAINER_DEALLOCATION = eINSTANCE.getContainerDeallocation();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER_DEALLOCATION__CONTAINER = eINSTANCE.getContainerDeallocation_Container();

	}

} //PlanPackage
