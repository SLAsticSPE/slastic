/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.util;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

import de.cau.se.slastic.metamodel.usage.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage
 * @generated
 */
public class UsageAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static UsagePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UsagePackage.eINSTANCE;
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
	protected UsageSwitch<Adapter> modelSwitch =
		new UsageSwitch<Adapter>() {
			@Override
			public Adapter caseUsageModel(UsageModel object) {
				return createUsageModelAdapter();
			}
			@Override
			public Adapter caseCallingRelationship(CallingRelationship object) {
				return createCallingRelationshipAdapter();
			}
			@Override
			public Adapter caseFrequencyDistribution(FrequencyDistribution object) {
				return createFrequencyDistributionAdapter();
			}
			@Override
			public Adapter caseOperationCallFrequency(OperationCallFrequency object) {
				return createOperationCallFrequencyAdapter();
			}
			@Override
			public Adapter caseAssemblyConnectorCallFrequency(AssemblyConnectorCallFrequency object) {
				return createAssemblyConnectorCallFrequencyAdapter();
			}
			@Override
			public Adapter caseSLAsticModel(SLAsticModel object) {
				return createSLAsticModelAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.usage.UsageModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.usage.UsageModel
	 * @generated
	 */
	public Adapter createUsageModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.usage.CallingRelationship <em>Calling Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.usage.CallingRelationship
	 * @generated
	 */
	public Adapter createCallingRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.usage.FrequencyDistribution <em>Frequency Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.usage.FrequencyDistribution
	 * @generated
	 */
	public Adapter createFrequencyDistributionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.usage.OperationCallFrequency <em>Operation Call Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.usage.OperationCallFrequency
	 * @generated
	 */
	public Adapter createOperationCallFrequencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency <em>Assembly Connector Call Frequency</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency
	 * @generated
	 */
	public Adapter createAssemblyConnectorCallFrequencyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.core.SLAsticModel <em>SL Astic Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.core.SLAsticModel
	 * @generated
	 */
	public Adapter createSLAsticModelAdapter() {
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

} //UsageAdapterFactory
