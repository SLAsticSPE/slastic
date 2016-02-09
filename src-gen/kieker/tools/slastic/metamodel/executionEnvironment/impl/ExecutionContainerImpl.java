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
import kieker.tools.slastic.metamodel.executionEnvironment.Resource;

import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl#getExecutionContainerType <em>Execution Container Type</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl#getNetworkLinks <em>Network Links</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl#getResources <em>Resources</em>}</li>
 * </ul>
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
	 * The cached value of the '{@link #getNetworkLinks() <em>Network Links</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworkLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<NetworkLink> networkLinks;

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
	public EList<NetworkLink> getNetworkLinks() {
		if (networkLinks == null) {
			networkLinks = new EObjectWithInverseResolvingEList.ManyInverse<NetworkLink>(NetworkLink.class, this, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS, ExecutionEnvironmentPackage.NETWORK_LINK__EXECUTION_CONTAINERS);
		}
		return networkLinks;
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getNetworkLinks()).basicAdd(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS:
				return ((InternalEList<?>)getNetworkLinks()).basicRemove(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS:
				return getNetworkLinks();
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS:
				getNetworkLinks().clear();
				getNetworkLinks().addAll((Collection<? extends NetworkLink>)newValue);
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS:
				getNetworkLinks().clear();
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__NETWORK_LINKS:
				return networkLinks != null && !networkLinks.isEmpty();
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES:
				return resources != null && !resources.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExecutionContainerImpl
