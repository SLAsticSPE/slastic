/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring.impl;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MonitoringPackage;

import de.cau.se.slastic.metamodel.typeRepository.Operation;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Component Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.DeploymentComponentOperationExecutionImpl#getDeploymentComponent <em>Deployment Component</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.DeploymentComponentOperationExecutionImpl#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeploymentComponentOperationExecutionImpl extends OperationExecutionImpl implements DeploymentComponentOperationExecution {
	/**
	 * The cached value of the '{@link #getDeploymentComponent() <em>Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentComponent()
	 * @generated
	 * @ordered
	 */
	protected DeploymentComponent deploymentComponent;

	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation operation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentComponentOperationExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MonitoringPackage.Literals.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent getDeploymentComponent() {
		if (deploymentComponent != null && deploymentComponent.eIsProxy()) {
			InternalEObject oldDeploymentComponent = (InternalEObject)deploymentComponent;
			deploymentComponent = (DeploymentComponent)eResolveProxy(oldDeploymentComponent);
			if (deploymentComponent != oldDeploymentComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT, oldDeploymentComponent, deploymentComponent));
			}
		}
		return deploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent basicGetDeploymentComponent() {
		return deploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDeploymentComponent(DeploymentComponent newDeploymentComponent) {
		DeploymentComponent oldDeploymentComponent = deploymentComponent;
		deploymentComponent = newDeploymentComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT, oldDeploymentComponent, deploymentComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperation() {
		if (operation != null && operation.eIsProxy()) {
			InternalEObject oldOperation = (InternalEObject)operation;
			operation = (Operation)eResolveProxy(oldOperation);
			if (operation != oldOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION, oldOperation, operation));
			}
		}
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(Operation newOperation) {
		Operation oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION, oldOperation, operation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT:
				if (resolve) return getDeploymentComponent();
				return basicGetDeploymentComponent();
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION:
				if (resolve) return getOperation();
				return basicGetOperation();
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
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT:
				setDeploymentComponent((DeploymentComponent)newValue);
				return;
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION:
				setOperation((Operation)newValue);
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
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT:
				setDeploymentComponent((DeploymentComponent)null);
				return;
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION:
				setOperation((Operation)null);
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
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT:
				return deploymentComponent != null;
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__OPERATION:
				return operation != null;
		}
		return super.eIsSet(featureID);
	}

} //DeploymentComponentOperationExecutionImpl
