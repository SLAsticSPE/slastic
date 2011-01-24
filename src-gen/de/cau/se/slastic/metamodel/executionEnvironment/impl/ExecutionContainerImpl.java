/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.executionEnvironment.impl;

import de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;

import de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl#getExecutionContainerType <em>Execution Container Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl#getNetworkLink <em>Network Link</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionContainerImpl extends FQNamedEntityImpl implements ExecutionContainer {
	/**
	 * The cached value of the '{@link #getExecutionContainerType() <em>Execution Container Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContainerType()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContainerType executionContainerType;

	/**
	 * The cached value of the '{@link #getNetworkLink() <em>Network Link</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworkLink()
	 * @generated
	 * @ordered
	 */
	protected NetworkLink networkLink;

	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> resources;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutionEnvironmentPackage.Literals.EXECUTION_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainerType getExecutionContainerType() {
		if (executionContainerType != null && executionContainerType.eIsProxy()) {
			InternalEObject oldExecutionContainerType = (InternalEObject)executionContainerType;
			executionContainerType = (ExecutionContainerType)eResolveProxy(oldExecutionContainerType);
			if (executionContainerType != oldExecutionContainerType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE, oldExecutionContainerType, executionContainerType));
			}
		}
		return executionContainerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainerType basicGetExecutionContainerType() {
		return executionContainerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionContainerType(ExecutionContainerType newExecutionContainerType) {
		ExecutionContainerType oldExecutionContainerType = executionContainerType;
		executionContainerType = newExecutionContainerType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE, oldExecutionContainerType, executionContainerType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkLink getNetworkLink() {
		if (networkLink != null && networkLink.eIsProxy()) {
			InternalEObject oldNetworkLink = (InternalEObject)networkLink;
			networkLink = (NetworkLink)eResolveProxy(oldNetworkLink);
			if (networkLink != oldNetworkLink) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK, oldNetworkLink, networkLink));
			}
		}
		return networkLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkLink basicGetNetworkLink() {
		return networkLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNetworkLink(NetworkLink newNetworkLink, NotificationChain msgs) {
		NetworkLink oldNetworkLink = networkLink;
		networkLink = newNetworkLink;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK, oldNetworkLink, newNetworkLink);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNetworkLink(NetworkLink newNetworkLink) {
		if (newNetworkLink != networkLink) {
			NotificationChain msgs = null;
			if (networkLink != null)
				msgs = ((InternalEObject)networkLink).eInverseRemove(this, ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS, NetworkLink.class, msgs);
			if (newNetworkLink != null)
				msgs = ((InternalEObject)newNetworkLink).eInverseAdd(this, ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS, NetworkLink.class, msgs);
			msgs = basicSetNetworkLink(newNetworkLink, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK, newNetworkLink, newNetworkLink));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getResources() {
		if (resources == null) {
			resources = new EObjectContainmentWithInverseEList<Resource>(Resource.class, this, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES, ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER);
		}
		return resources;
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK:
				if (networkLink != null)
					msgs = ((InternalEObject)networkLink).eInverseRemove(this, ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS, NetworkLink.class, msgs);
				return basicSetNetworkLink((NetworkLink)otherEnd, msgs);
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResources()).basicAdd(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK:
				return basicSetNetworkLink(null, msgs);
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE:
				if (resolve) return getExecutionContainerType();
				return basicGetExecutionContainerType();
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK:
				if (resolve) return getNetworkLink();
				return basicGetNetworkLink();
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				return getResources();
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE:
				setExecutionContainerType((ExecutionContainerType)newValue);
				return;
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK:
				setNetworkLink((NetworkLink)newValue);
				return;
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE:
				setExecutionContainerType((ExecutionContainerType)null);
				return;
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK:
				setNetworkLink((NetworkLink)null);
				return;
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				getResources().clear();
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE:
				return executionContainerType != null;
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK:
				return networkLink != null;
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				return resources != null && !resources.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExecutionContainerImpl
