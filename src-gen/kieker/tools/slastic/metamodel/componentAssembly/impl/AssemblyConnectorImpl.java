/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly.impl;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyConnector;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;

import kieker.tools.slastic.metamodel.core.impl.FQNamedEntityImpl;

import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;

import kieker.tools.slastic.metamodel.typeRepository.Interface;
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
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl#getConnectorType <em>Connector Type</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl#getInterface <em>Interface</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AssemblyConnectorImpl extends FQNamedEntityImpl implements AssemblyConnector {
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
	 * The cached value of the '{@link #getInterface() <em>Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface()
	 * @generated
	 * @ordered
	 */
	protected Interface interface_;

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
	public Interface getInterface() {
		if (interface_ != null && interface_.eIsProxy()) {
			InternalEObject oldInterface = (InternalEObject)interface_;
			interface_ = (Interface)eResolveProxy(oldInterface);
			if (interface_ != oldInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__INTERFACE, oldInterface, interface_));
			}
		}
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface basicGetInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterface(Interface newInterface) {
		Interface oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__INTERFACE, oldInterface, interface_));
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__INTERFACE:
				if (resolve) return getInterface();
				return basicGetInterface();
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__INTERFACE:
				setInterface((Interface)newValue);
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__INTERFACE:
				setInterface((Interface)null);
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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR__INTERFACE:
				return interface_ != null;
		}
		return super.eIsSet(featureID);
	}

} //AssemblyConnectorImpl
