/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.executionEnvironment.impl;

import de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import de.cau.se.slastic.metamodel.executionEnvironment.NetworkLink;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl#getExecutionContainers <em>Execution Containers</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl#getNetworkLinks <em>Network Links</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl#getAllocatedExecutionContainers <em>Allocated Execution Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionEnvironmentModelImpl extends SLAsticModelImpl implements ExecutionEnvironmentModel {
	/**
	 * The cached value of the '{@link #getExecutionContainers() <em>Execution Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContainer> executionContainers;

	/**
	 * The cached value of the '{@link #getNetworkLinks() <em>Network Links</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworkLinks()
	 * @generated
	 * @ordered
	 */
	protected EList<NetworkLink> networkLinks;

	/**
	 * The cached value of the '{@link #getAllocatedExecutionContainers() <em>Allocated Execution Containers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocatedExecutionContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContainer> allocatedExecutionContainers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionEnvironmentModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutionEnvironmentPackage.Literals.EXECUTION_ENVIRONMENT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionContainer> getExecutionContainers() {
		if (executionContainers == null) {
			executionContainers = new EObjectContainmentEList<ExecutionContainer>(ExecutionContainer.class, this, ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS);
		}
		return executionContainers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NetworkLink> getNetworkLinks() {
		if (networkLinks == null) {
			networkLinks = new EObjectContainmentEList<NetworkLink>(NetworkLink.class, this, ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS);
		}
		return networkLinks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionContainer> getAllocatedExecutionContainers() {
		if (allocatedExecutionContainers == null) {
			allocatedExecutionContainers = new EObjectResolvingEList<ExecutionContainer>(ExecutionContainer.class, this, ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS);
		}
		return allocatedExecutionContainers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS:
				return ((InternalEList<?>)getExecutionContainers()).basicRemove(otherEnd, msgs);
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS:
				return ((InternalEList<?>)getNetworkLinks()).basicRemove(otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS:
				return getExecutionContainers();
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS:
				return getNetworkLinks();
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS:
				return getAllocatedExecutionContainers();
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
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS:
				getExecutionContainers().clear();
				getExecutionContainers().addAll((Collection<? extends ExecutionContainer>)newValue);
				return;
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS:
				getNetworkLinks().clear();
				getNetworkLinks().addAll((Collection<? extends NetworkLink>)newValue);
				return;
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS:
				getAllocatedExecutionContainers().clear();
				getAllocatedExecutionContainers().addAll((Collection<? extends ExecutionContainer>)newValue);
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
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS:
				getExecutionContainers().clear();
				return;
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS:
				getNetworkLinks().clear();
				return;
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS:
				getAllocatedExecutionContainers().clear();
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
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS:
				return executionContainers != null && !executionContainers.isEmpty();
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS:
				return networkLinks != null && !networkLinks.isEmpty();
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS:
				return allocatedExecutionContainers != null && !allocatedExecutionContainers.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExecutionEnvironmentModelImpl
