/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.impl;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembly Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl#getProvidingConnectors <em>Providing Connectors</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl#getRequiringConnectors <em>Requiring Connectors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssemblyComponentImpl extends FQNamedEntityImpl implements AssemblyComponent {
	/**
	 * The cached value of the '{@link #getComponentType() <em>Component Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentType()
	 * @generated
	 * @ordered
	 */
	protected ComponentType componentType;

	/**
	 * The cached value of the '{@link #getProvidingConnectors() <em>Providing Connectors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidingConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyConnector> providingConnectors;

	/**
	 * The cached value of the '{@link #getRequiringConnectors() <em>Requiring Connectors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiringConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyConnector> requiringConnectors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblyComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentAssemblyPackage.Literals.ASSEMBLY_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType getComponentType() {
		if (componentType != null && componentType.eIsProxy()) {
			InternalEObject oldComponentType = (InternalEObject)componentType;
			componentType = (ComponentType)eResolveProxy(oldComponentType);
			if (componentType != oldComponentType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__COMPONENT_TYPE, oldComponentType, componentType));
			}
		}
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType basicGetComponentType() {
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentType(ComponentType newComponentType) {
		ComponentType oldComponentType = componentType;
		componentType = newComponentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__COMPONENT_TYPE, oldComponentType, componentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssemblyConnector> getProvidingConnectors() {
		if (providingConnectors == null) {
			providingConnectors = new EObjectWithInverseResolvingEList<AssemblyConnector>(AssemblyConnector.class, this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__REQUIRING_COMPONENT);
		}
		return providingConnectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssemblyConnector> getRequiringConnectors() {
		if (requiringConnectors == null) {
			requiringConnectors = new EObjectWithInverseResolvingEList<AssemblyConnector>(AssemblyConnector.class, this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT);
		}
		return requiringConnectors;
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProvidingConnectors()).basicAdd(otherEnd, msgs);
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRequiringConnectors()).basicAdd(otherEnd, msgs);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS:
				return ((InternalEList<?>)getProvidingConnectors()).basicRemove(otherEnd, msgs);
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS:
				return ((InternalEList<?>)getRequiringConnectors()).basicRemove(otherEnd, msgs);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__COMPONENT_TYPE:
				if (resolve) return getComponentType();
				return basicGetComponentType();
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS:
				return getProvidingConnectors();
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS:
				return getRequiringConnectors();
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__COMPONENT_TYPE:
				setComponentType((ComponentType)newValue);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS:
				getProvidingConnectors().clear();
				getProvidingConnectors().addAll((Collection<? extends AssemblyConnector>)newValue);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS:
				getRequiringConnectors().clear();
				getRequiringConnectors().addAll((Collection<? extends AssemblyConnector>)newValue);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__COMPONENT_TYPE:
				setComponentType((ComponentType)null);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS:
				getProvidingConnectors().clear();
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS:
				getRequiringConnectors().clear();
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__COMPONENT_TYPE:
				return componentType != null;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS:
				return providingConnectors != null && !providingConnectors.isEmpty();
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS:
				return requiringConnectors != null && !requiringConnectors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AssemblyComponentImpl
