/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.impl;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;
import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector;

import de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl;

import de.cau.se.slastic.metamodel.typeRepository.Interface;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getSystemProvidedInterfaceDelegationConnectors <em>System Provided Interface Delegation Connectors</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getSystemRequiredInterfaceDelegationConnectors <em>System Required Interface Delegation Connectors</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getAssemblyComponents <em>Assembly Components</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getAssemblyComponentConnectors <em>Assembly Component Connectors</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getSystemProvidedInterfaces <em>System Provided Interfaces</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl#getSystemRequiredInterfaces <em>System Required Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentAssemblyModelImpl extends SLAsticModelImpl implements ComponentAssemblyModel {
	/**
	 * The cached value of the '{@link #getSystemProvidedInterfaceDelegationConnectors() <em>System Provided Interface Delegation Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemProvidedInterfaceDelegationConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemProvidedInterfaceDelegationConnector> systemProvidedInterfaceDelegationConnectors;

	/**
	 * The cached value of the '{@link #getSystemRequiredInterfaceDelegationConnectors() <em>System Required Interface Delegation Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemRequiredInterfaceDelegationConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemRequiredInterfaceDelegationConnector> systemRequiredInterfaceDelegationConnectors;

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
	 * The cached value of the '{@link #getAssemblyComponentConnectors() <em>Assembly Component Connectors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyComponentConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyComponentConnector> assemblyComponentConnectors;

	/**
	 * The cached value of the '{@link #getSystemProvidedInterfaces() <em>System Provided Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemProvidedInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> systemProvidedInterfaces;

	/**
	 * The cached value of the '{@link #getSystemRequiredInterfaces() <em>System Required Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemRequiredInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> systemRequiredInterfaces;

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
	public EList<SystemProvidedInterfaceDelegationConnector> getSystemProvidedInterfaceDelegationConnectors() {
		if (systemProvidedInterfaceDelegationConnectors == null) {
			systemProvidedInterfaceDelegationConnectors = new EObjectContainmentWithInverseEList<SystemProvidedInterfaceDelegationConnector>(SystemProvidedInterfaceDelegationConnector.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS, ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL);
		}
		return systemProvidedInterfaceDelegationConnectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SystemRequiredInterfaceDelegationConnector> getSystemRequiredInterfaceDelegationConnectors() {
		if (systemRequiredInterfaceDelegationConnectors == null) {
			systemRequiredInterfaceDelegationConnectors = new EObjectContainmentWithInverseEList<SystemRequiredInterfaceDelegationConnector>(SystemRequiredInterfaceDelegationConnector.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS, ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL);
		}
		return systemRequiredInterfaceDelegationConnectors;
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
	public EList<AssemblyComponentConnector> getAssemblyComponentConnectors() {
		if (assemblyComponentConnectors == null) {
			assemblyComponentConnectors = new EObjectContainmentEList<AssemblyComponentConnector>(AssemblyComponentConnector.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS);
		}
		return assemblyComponentConnectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getSystemProvidedInterfaces() {
		if (systemProvidedInterfaces == null) {
			systemProvidedInterfaces = new EObjectResolvingEList<Interface>(Interface.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES);
		}
		return systemProvidedInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getSystemRequiredInterfaces() {
		if (systemRequiredInterfaces == null) {
			systemRequiredInterfaces = new EObjectResolvingEList<Interface>(Interface.class, this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES);
		}
		return systemRequiredInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSystemProvidedInterfaceDelegationConnectors()).basicAdd(otherEnd, msgs);
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSystemRequiredInterfaceDelegationConnectors()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS:
				return ((InternalEList<?>)getSystemProvidedInterfaceDelegationConnectors()).basicRemove(otherEnd, msgs);
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS:
				return ((InternalEList<?>)getSystemRequiredInterfaceDelegationConnectors()).basicRemove(otherEnd, msgs);
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				return ((InternalEList<?>)getAssemblyComponents()).basicRemove(otherEnd, msgs);
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS:
				return ((InternalEList<?>)getAssemblyComponentConnectors()).basicRemove(otherEnd, msgs);
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
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS:
				return getSystemProvidedInterfaceDelegationConnectors();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS:
				return getSystemRequiredInterfaceDelegationConnectors();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				return getAssemblyComponents();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS:
				return getAssemblyComponentConnectors();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES:
				return getSystemProvidedInterfaces();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES:
				return getSystemRequiredInterfaces();
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
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS:
				getSystemProvidedInterfaceDelegationConnectors().clear();
				getSystemProvidedInterfaceDelegationConnectors().addAll((Collection<? extends SystemProvidedInterfaceDelegationConnector>)newValue);
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS:
				getSystemRequiredInterfaceDelegationConnectors().clear();
				getSystemRequiredInterfaceDelegationConnectors().addAll((Collection<? extends SystemRequiredInterfaceDelegationConnector>)newValue);
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				getAssemblyComponents().clear();
				getAssemblyComponents().addAll((Collection<? extends AssemblyComponent>)newValue);
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS:
				getAssemblyComponentConnectors().clear();
				getAssemblyComponentConnectors().addAll((Collection<? extends AssemblyComponentConnector>)newValue);
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES:
				getSystemProvidedInterfaces().clear();
				getSystemProvidedInterfaces().addAll((Collection<? extends Interface>)newValue);
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES:
				getSystemRequiredInterfaces().clear();
				getSystemRequiredInterfaces().addAll((Collection<? extends Interface>)newValue);
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
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS:
				getSystemProvidedInterfaceDelegationConnectors().clear();
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS:
				getSystemRequiredInterfaceDelegationConnectors().clear();
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				getAssemblyComponents().clear();
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS:
				getAssemblyComponentConnectors().clear();
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES:
				getSystemProvidedInterfaces().clear();
				return;
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES:
				getSystemRequiredInterfaces().clear();
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
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS:
				return systemProvidedInterfaceDelegationConnectors != null && !systemProvidedInterfaceDelegationConnectors.isEmpty();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS:
				return systemRequiredInterfaceDelegationConnectors != null && !systemRequiredInterfaceDelegationConnectors.isEmpty();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS:
				return assemblyComponents != null && !assemblyComponents.isEmpty();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS:
				return assemblyComponentConnectors != null && !assemblyComponentConnectors.isEmpty();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES:
				return systemProvidedInterfaces != null && !systemProvidedInterfaces.isEmpty();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES:
				return systemRequiredInterfaces != null && !systemRequiredInterfaces.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComponentAssemblyModelImpl
