/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.impl;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembly Component Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl#getConnectorType <em>Connector Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl#getProvidingComponent <em>Providing Component</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl#getRequiringComponent <em>Requiring Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssemblyComponentConnectorImpl extends FQNamedEntityImpl implements AssemblyComponentConnector {
	/**
	 * The cached value of the '{@link #getConnectorType() <em>Connector Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorType()
	 * @generated
	 * @ordered
	 */
	protected ConnectorType connectorType;

	/**
	 * The cached value of the '{@link #getProvidingComponent() <em>Providing Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidingComponent()
	 * @generated
	 * @ordered
	 */
	protected AssemblyComponent providingComponent;

	/**
	 * The cached value of the '{@link #getRequiringComponent() <em>Requiring Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiringComponent()
	 * @generated
	 * @ordered
	 */
	protected AssemblyComponent requiringComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblyComponentConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentAssemblyPackage.Literals.ASSEMBLY_COMPONENT_CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorType getConnectorType() {
		if (connectorType != null && connectorType.eIsProxy()) {
			InternalEObject oldConnectorType = (InternalEObject)connectorType;
			connectorType = (ConnectorType)eResolveProxy(oldConnectorType);
			if (connectorType != oldConnectorType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE, oldConnectorType, connectorType));
			}
		}
		return connectorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorType basicGetConnectorType() {
		return connectorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectorType(ConnectorType newConnectorType) {
		ConnectorType oldConnectorType = connectorType;
		connectorType = newConnectorType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE, oldConnectorType, connectorType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent getProvidingComponent() {
		if (providingComponent != null && providingComponent.eIsProxy()) {
			InternalEObject oldProvidingComponent = (InternalEObject)providingComponent;
			providingComponent = (AssemblyComponent)eResolveProxy(oldProvidingComponent);
			if (providingComponent != oldProvidingComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT, oldProvidingComponent, providingComponent));
			}
		}
		return providingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent basicGetProvidingComponent() {
		return providingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProvidingComponent(AssemblyComponent newProvidingComponent, NotificationChain msgs) {
		AssemblyComponent oldProvidingComponent = providingComponent;
		providingComponent = newProvidingComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT, oldProvidingComponent, newProvidingComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvidingComponent(AssemblyComponent newProvidingComponent) {
		if (newProvidingComponent != providingComponent) {
			NotificationChain msgs = null;
			if (providingComponent != null)
				msgs = ((InternalEObject)providingComponent).eInverseRemove(this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS, AssemblyComponent.class, msgs);
			if (newProvidingComponent != null)
				msgs = ((InternalEObject)newProvidingComponent).eInverseAdd(this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS, AssemblyComponent.class, msgs);
			msgs = basicSetProvidingComponent(newProvidingComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT, newProvidingComponent, newProvidingComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent getRequiringComponent() {
		if (requiringComponent != null && requiringComponent.eIsProxy()) {
			InternalEObject oldRequiringComponent = (InternalEObject)requiringComponent;
			requiringComponent = (AssemblyComponent)eResolveProxy(oldRequiringComponent);
			if (requiringComponent != oldRequiringComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT, oldRequiringComponent, requiringComponent));
			}
		}
		return requiringComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent basicGetRequiringComponent() {
		return requiringComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRequiringComponent(AssemblyComponent newRequiringComponent, NotificationChain msgs) {
		AssemblyComponent oldRequiringComponent = requiringComponent;
		requiringComponent = newRequiringComponent;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT, oldRequiringComponent, newRequiringComponent);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequiringComponent(AssemblyComponent newRequiringComponent) {
		if (newRequiringComponent != requiringComponent) {
			NotificationChain msgs = null;
			if (requiringComponent != null)
				msgs = ((InternalEObject)requiringComponent).eInverseRemove(this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS, AssemblyComponent.class, msgs);
			if (newRequiringComponent != null)
				msgs = ((InternalEObject)newRequiringComponent).eInverseAdd(this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS, AssemblyComponent.class, msgs);
			msgs = basicSetRequiringComponent(newRequiringComponent, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT, newRequiringComponent, newRequiringComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT:
				if (providingComponent != null)
					msgs = ((InternalEObject)providingComponent).eInverseRemove(this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS, AssemblyComponent.class, msgs);
				return basicSetProvidingComponent((AssemblyComponent)otherEnd, msgs);
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT:
				if (requiringComponent != null)
					msgs = ((InternalEObject)requiringComponent).eInverseRemove(this, ComponentAssemblyPackage.ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS, AssemblyComponent.class, msgs);
				return basicSetRequiringComponent((AssemblyComponent)otherEnd, msgs);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT:
				return basicSetProvidingComponent(null, msgs);
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT:
				return basicSetRequiringComponent(null, msgs);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE:
				if (resolve) return getConnectorType();
				return basicGetConnectorType();
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT:
				if (resolve) return getProvidingComponent();
				return basicGetProvidingComponent();
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT:
				if (resolve) return getRequiringComponent();
				return basicGetRequiringComponent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE:
				setConnectorType((ConnectorType)newValue);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT:
				setProvidingComponent((AssemblyComponent)newValue);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT:
				setRequiringComponent((AssemblyComponent)newValue);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE:
				setConnectorType((ConnectorType)null);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT:
				setProvidingComponent((AssemblyComponent)null);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT:
				setRequiringComponent((AssemblyComponent)null);
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE:
				return connectorType != null;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT:
				return providingComponent != null;
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT:
				return requiringComponent != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AssemblyConnector.class) {
			switch (derivedFeatureID) {
				case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE: return ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AssemblyConnector.class) {
			switch (baseFeatureID) {
				case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE: return ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //AssemblyComponentConnectorImpl
