/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;

import de.cau.se.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency;
import de.cau.se.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Provided Interface Delegation Connector Frequency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.SystemProvidedInterfaceDelegationConnectorFrequencyImpl#getConnector <em>Connector</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SystemProvidedInterfaceDelegationConnectorFrequencyImpl extends AssemblyConnectorCallFrequencyImpl implements SystemProvidedInterfaceDelegationConnectorFrequency {
	/**
	 * The cached value of the '{@link #getConnector() <em>Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnector()
	 * @generated
	 * @ordered
	 */
	protected SystemProvidedInterfaceDelegationConnector connector;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemProvidedInterfaceDelegationConnectorFrequencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemProvidedInterfaceDelegationConnector getConnector() {
		if (connector != null && connector.eIsProxy()) {
			InternalEObject oldConnector = (InternalEObject)connector;
			connector = (SystemProvidedInterfaceDelegationConnector)eResolveProxy(oldConnector);
			if (connector != oldConnector) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR, oldConnector, connector));
			}
		}
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemProvidedInterfaceDelegationConnector basicGetConnector() {
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnector(SystemProvidedInterfaceDelegationConnector newConnector) {
		SystemProvidedInterfaceDelegationConnector oldConnector = connector;
		connector = newConnector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR, oldConnector, connector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR:
				if (resolve) return getConnector();
				return basicGetConnector();
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
			case UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR:
				setConnector((SystemProvidedInterfaceDelegationConnector)newValue);
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
			case UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR:
				setConnector((SystemProvidedInterfaceDelegationConnector)null);
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
			case UsagePackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCY__CONNECTOR:
				return connector != null;
		}
		return super.eIsSet(featureID);
	}

} //SystemProvidedInterfaceDelegationConnectorFrequencyImpl
