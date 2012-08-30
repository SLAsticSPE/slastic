/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment.impl;

import java.util.Collection;

import kieker.tools.slastic.metamodel.core.impl.FQNamedEntityImpl;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink;

import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Network Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.NetworkLinkImpl#getNetworkLinkType <em>Network Link Type</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.NetworkLinkImpl#getExecutionContainers <em>Execution Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NetworkLinkImpl extends FQNamedEntityImpl implements NetworkLink {
	/**
	 * The cached value of the '{@link #getNetworkLinkType() <em>Network Link Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworkLinkType()
	 * @generated
	 * @ordered
	 */
	protected NetworkLinkType networkLinkType;

	/**
	 * The cached value of the '{@link #getExecutionContainers() <em>Execution Containers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContainer> executionContainers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NetworkLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutionEnvironmentPackage.Literals.NETWORK_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkLinkType getNetworkLinkType() {
		if (networkLinkType != null && networkLinkType.eIsProxy()) {
			InternalEObject oldNetworkLinkType = (InternalEObject)networkLinkType;
			networkLinkType = (NetworkLinkType)eResolveProxy(oldNetworkLinkType);
			if (networkLinkType != oldNetworkLinkType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutionEnvironmentPackage.NETWORK_LINK__NETWORK_LINK_TYPE, oldNetworkLinkType, networkLinkType));
			}
		}
		return networkLinkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkLinkType basicGetNetworkLinkType() {
		return networkLinkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNetworkLinkType(NetworkLinkType newNetworkLinkType) {
		NetworkLinkType oldNetworkLinkType = networkLinkType;
		networkLinkType = newNetworkLinkType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.NETWORK_LINK__NETWORK_LINK_TYPE, oldNetworkLinkType, networkLinkType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionContainer> getExecutionContainers() {
		if (executionContainers == null) {
			executionContainers = new EObjectWithInverseResolvingEList<ExecutionContainer>(ExecutionContainer.class, this, ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINK);
		}
		return executionContainers;
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
			case ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecutionContainers()).basicAdd(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS:
				return ((InternalEList<?>)getExecutionContainers()).basicRemove(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.NETWORK_LINK__NETWORK_LINK_TYPE:
				if (resolve) return getNetworkLinkType();
				return basicGetNetworkLinkType();
			case ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS:
				return getExecutionContainers();
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
			case ExecutionEnvironmentPackage.NETWORK_LINK__NETWORK_LINK_TYPE:
				setNetworkLinkType((NetworkLinkType)newValue);
				return;
			case ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS:
				getExecutionContainers().clear();
				getExecutionContainers().addAll((Collection<? extends ExecutionContainer>)newValue);
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
			case ExecutionEnvironmentPackage.NETWORK_LINK__NETWORK_LINK_TYPE:
				setNetworkLinkType((NetworkLinkType)null);
				return;
			case ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS:
				getExecutionContainers().clear();
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
			case ExecutionEnvironmentPackage.NETWORK_LINK__NETWORK_LINK_TYPE:
				return networkLinkType != null;
			case ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS:
				return executionContainers != null && !executionContainers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NetworkLinkImpl
