/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan.util;

import kieker.tools.slastic.metamodel.reconfiguration.plan.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage
 * @generated
 */
public class PlanAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static PlanPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PlanAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = PlanPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PlanSwitch<Adapter> modelSwitch =
		new PlanSwitch<Adapter>() {
			@Override
			public Adapter caseReconfigurationPlan(ReconfigurationPlan object) {
				return createReconfigurationPlanAdapter();
			}
			@Override
			public Adapter caseReconfigurationOperation(ReconfigurationOperation object) {
				return createReconfigurationOperationAdapter();
			}
			@Override
			public Adapter caseComponentReplication(ComponentReplication object) {
				return createComponentReplicationAdapter();
			}
			@Override
			public Adapter caseComponentDereplication(ComponentDereplication object) {
				return createComponentDereplicationAdapter();
			}
			@Override
			public Adapter caseComponentMigration(ComponentMigration object) {
				return createComponentMigrationAdapter();
			}
			@Override
			public Adapter caseContainerAllocation(ContainerAllocation object) {
				return createContainerAllocationAdapter();
			}
			@Override
			public Adapter caseContainerDeallocation(ContainerDeallocation object) {
				return createContainerDeallocationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan <em>Reconfiguration Plan</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan
	 * @generated
	 */
	public Adapter createReconfigurationPlanAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationOperation <em>Reconfiguration Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationOperation
	 * @generated
	 */
	public Adapter createReconfigurationOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentReplication <em>Component Replication</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentReplication
	 * @generated
	 */
	public Adapter createComponentReplicationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentDereplication <em>Component Dereplication</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentDereplication
	 * @generated
	 */
	public Adapter createComponentDereplicationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentMigration <em>Component Migration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentMigration
	 * @generated
	 */
	public Adapter createComponentMigrationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerAllocation <em>Container Allocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerAllocation
	 * @generated
	 */
	public Adapter createContainerAllocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerDeallocation <em>Container Deallocation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerDeallocation
	 * @generated
	 */
	public Adapter createContainerDeallocationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //PlanAdapterFactory
