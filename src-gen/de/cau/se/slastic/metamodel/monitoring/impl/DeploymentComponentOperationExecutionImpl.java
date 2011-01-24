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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT:
				if (resolve) return getDeploymentComponent();
				return basicGetDeploymentComponent();
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
		}
		return super.eIsSet(featureID);
	}

} //DeploymentComponentOperationExecutionImpl
