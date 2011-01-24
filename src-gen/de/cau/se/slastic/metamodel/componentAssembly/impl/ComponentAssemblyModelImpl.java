/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.impl;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getAssemblyComponents <em>Assembly Components</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getAssemblyConnectors <em>Assembly Connectors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentAssemblyModelImpl extends SLAsticModelImpl implements ComponentAssemblyModel {
	/**
	 * The cached value of the '{@link #getAssemblyComponents() <em>Assembly Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyComponent> assemblyComponents;

	/**
	 * The cached value of the '{@link #getAssemblyConnectors() <em>Assembly Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyConnector> assemblyConnectors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentAssemblyModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentAssemblyPackage.Literals.COMPONENT_ASSEMBLY_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssemblyComponent> getAssemblyComponents() {
		if (assemblyComponents == null) {
			assemblyComponents = new EObjectContainmentEList<AssemblyComponent>(AssemblyComponent.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS);
		}
		return assemblyComponents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssemblyConnector> getAssemblyConnectors() {
		if (assemblyConnectors == null) {
			assemblyConnectors = new EObjectContainmentEList<AssemblyConnector>(AssemblyConnector.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS);
		}
		return assemblyConnectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				return ((InternalEList<?>)getAssemblyComponents()).basicRemove(otherEnd, msgs);
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS:
				return ((InternalEList<?>)getAssemblyConnectors()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				return getAssemblyComponents();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS:
				return getAssemblyConnectors();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				getAssemblyComponents().clear();
				getAssemblyComponents().addAll((Collection<? extends AssemblyComponent>)newValue);
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS:
				getAssemblyConnectors().clear();
				getAssemblyConnectors().addAll((Collection<? extends AssemblyConnector>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				getAssemblyComponents().clear();
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS:
				getAssemblyConnectors().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				return assemblyComponents != null && !assemblyComponents.isEmpty();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS:
				return assemblyConnectors != null && !assemblyConnectors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComponentAssemblyModelImpl
