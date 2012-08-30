/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan.impl;

import kieker.tools.slastic.metamodel.reconfiguration.plan.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PlanFactoryImpl extends EFactoryImpl implements PlanFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PlanFactory init() {
		try {
			PlanFactory thePlanFactory = (PlanFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/reconfiguration/plan.ecore"); 
			if (thePlanFactory != null) {
				return thePlanFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PlanFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PlanPackage.RECONFIGURATION_PLAN: return createReconfigurationPlan();
			case PlanPackage.COMPONENT_REPLICATION: return createComponentReplication();
			case PlanPackage.COMPONENT_DEREPLICATION: return createComponentDereplication();
			case PlanPackage.COMPONENT_MIGRATION: return createComponentMigration();
			case PlanPackage.CONTAINER_ALLOCATION: return createContainerAllocation();
			case PlanPackage.CONTAINER_DEALLOCATION: return createContainerDeallocation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfigurationPlan createReconfigurationPlan() {
		ReconfigurationPlanImpl reconfigurationPlan = new ReconfigurationPlanImpl();
		return reconfigurationPlan;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentReplication createComponentReplication() {
		ComponentReplicationImpl componentReplication = new ComponentReplicationImpl();
		return componentReplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDereplication createComponentDereplication() {
		ComponentDereplicationImpl componentDereplication = new ComponentDereplicationImpl();
		return componentDereplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentMigration createComponentMigration() {
		ComponentMigrationImpl componentMigration = new ComponentMigrationImpl();
		return componentMigration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerAllocation createContainerAllocation() {
		ContainerAllocationImpl containerAllocation = new ContainerAllocationImpl();
		return containerAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContainerDeallocation createContainerDeallocation() {
		ContainerDeallocationImpl containerDeallocation = new ContainerDeallocationImpl();
		return containerDeallocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanPackage getPlanPackage() {
		return (PlanPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PlanPackage getPackage() {
		return PlanPackage.eINSTANCE;
	}

} //PlanFactoryImpl
