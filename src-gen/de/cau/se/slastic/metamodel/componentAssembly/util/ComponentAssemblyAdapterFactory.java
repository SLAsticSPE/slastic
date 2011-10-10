/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.util;

import de.cau.se.slastic.metamodel.componentAssembly.*;

import de.cau.se.slastic.metamodel.core.Entity;
import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import de.cau.se.slastic.metamodel.core.NamedEntity;
import de.cau.se.slastic.metamodel.core.SLAsticModel;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage
 * @generated
 */
public class ComponentAssemblyAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ComponentAssemblyPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ComponentAssemblyPackage.eINSTANCE;
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
	protected ComponentAssemblySwitch<Adapter> modelSwitch =
		new ComponentAssemblySwitch<Adapter>() {
			@Override
			public Adapter caseAssemblyComponent(AssemblyComponent object) {
				return createAssemblyComponentAdapter();
			}
			@Override
			public Adapter caseAssemblyComponentConnector(AssemblyComponentConnector object) {
				return createAssemblyComponentConnectorAdapter();
			}
			@Override
			public Adapter caseAssemblyConnector(AssemblyConnector object) {
				return createAssemblyConnectorAdapter();
			}
			@Override
			public Adapter caseSystemInterfaceDelegationConnector(SystemInterfaceDelegationConnector object) {
				return createSystemInterfaceDelegationConnectorAdapter();
			}
			@Override
			public Adapter caseSystemProvidedInterfaceDelegationConnector(SystemProvidedInterfaceDelegationConnector object) {
				return createSystemProvidedInterfaceDelegationConnectorAdapter();
			}
			@Override
			public Adapter caseSystemRequiredInterfaceDelegationConnector(SystemRequiredInterfaceDelegationConnector object) {
				return createSystemRequiredInterfaceDelegationConnectorAdapter();
			}
			@Override
			public Adapter caseComponentAssemblyModel(ComponentAssemblyModel object) {
				return createComponentAssemblyModelAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter caseNamedEntity(NamedEntity object) {
				return createNamedEntityAdapter();
			}
			@Override
			public Adapter caseFQNamedEntity(FQNamedEntity object) {
				return createFQNamedEntityAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent <em>Assembly Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent
	 * @generated
	 */
	public Adapter createAssemblyComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector <em>Assembly Component Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector
	 * @generated
	 */
	public Adapter createAssemblyComponentConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector <em>Assembly Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector
	 * @generated
	 */
	public Adapter createAssemblyConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemInterfaceDelegationConnector <em>System Interface Delegation Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemInterfaceDelegationConnector
	 * @generated
	 */
	public Adapter createSystemInterfaceDelegationConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector <em>System Provided Interface Delegation Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector
	 * @generated
	 */
	public Adapter createSystemProvidedInterfaceDelegationConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector <em>System Required Interface Delegation Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector
	 * @generated
	 */
	public Adapter createSystemRequiredInterfaceDelegationConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel
	 * @generated
	 */
	public Adapter createComponentAssemblyModelAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.core.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.core.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.core.NamedEntity <em>Named Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.core.NamedEntity
	 * @generated
	 */
	public Adapter createNamedEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.cau.se.slastic.metamodel.core.FQNamedEntity <em>FQ Named Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.cau.se.slastic.metamodel.core.FQNamedEntity
	 * @generated
	 */
	public Adapter createFQNamedEntityAdapter() {
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

} //ComponentAssemblyAdapterFactory
