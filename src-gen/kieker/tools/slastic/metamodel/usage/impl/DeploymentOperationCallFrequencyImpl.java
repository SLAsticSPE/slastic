/**
 */
package kieker.tools.slastic.metamodel.usage.impl;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

import kieker.tools.slastic.metamodel.usage.DeploymentOperationCallFrequency;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Operation Call Frequency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.DeploymentOperationCallFrequencyImpl#getDeploymentComponent <em>Deployment Component</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DeploymentOperationCallFrequencyImpl extends OperationCallFrequencyImpl implements DeploymentOperationCallFrequency {
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
	protected DeploymentOperationCallFrequencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.DEPLOYMENT_OPERATION_CALL_FREQUENCY;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.DEPLOYMENT_OPERATION_CALL_FREQUENCY__DEPLOYMENT_COMPONENT, oldDeploymentComponent, deploymentComponent));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.DEPLOYMENT_OPERATION_CALL_FREQUENCY__DEPLOYMENT_COMPONENT, oldDeploymentComponent, deploymentComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.DEPLOYMENT_OPERATION_CALL_FREQUENCY__DEPLOYMENT_COMPONENT:
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
			case UsagePackage.DEPLOYMENT_OPERATION_CALL_FREQUENCY__DEPLOYMENT_COMPONENT:
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
			case UsagePackage.DEPLOYMENT_OPERATION_CALL_FREQUENCY__DEPLOYMENT_COMPONENT:
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
			case UsagePackage.DEPLOYMENT_OPERATION_CALL_FREQUENCY__DEPLOYMENT_COMPONENT:
				return deploymentComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //DeploymentOperationCallFrequencyImpl
