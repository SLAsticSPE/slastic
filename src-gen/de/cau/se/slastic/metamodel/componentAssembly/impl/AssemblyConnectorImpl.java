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

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembly Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl#getConnectorType <em>Connector Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl#getProvidingComponent <em>Providing Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssemblyConnectorImpl extends FQNamedEntityImpl implements AssemblyConnector {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblyConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentAssemblyPackage.Literals.ASSEMBLY_CONNECTOR;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE, oldConnectorType, connectorType));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE, oldConnectorType, connectorType));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT, oldProvidingComponent, providingComponent));
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
	public void setProvidingComponent(AssemblyComponent newProvidingComponent) {
		AssemblyComponent oldProvidingComponent = providingComponent;
		providingComponent = newProvidingComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT, oldProvidingComponent, providingComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE:
				if (resolve) return getConnectorType();
				return basicGetConnectorType();
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT:
				if (resolve) return getProvidingComponent();
				return basicGetProvidingComponent();
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE:
				setConnectorType((ConnectorType)newValue);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT:
				setProvidingComponent((AssemblyComponent)newValue);
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE:
				setConnectorType((ConnectorType)null);
				return;
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT:
				setProvidingComponent((AssemblyComponent)null);
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__CONNECTOR_TYPE:
				return connectorType != null;
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT:
				return providingComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //AssemblyConnectorImpl
