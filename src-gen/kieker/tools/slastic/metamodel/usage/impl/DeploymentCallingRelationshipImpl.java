/**
 */
package kieker.tools.slastic.metamodel.usage.impl;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

import kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Calling Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.DeploymentCallingRelationshipImpl#getCallingDeploymentComponent <em>Calling Deployment Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.DeploymentCallingRelationshipImpl#getCalledDeploymentComponent <em>Called Deployment Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeploymentCallingRelationshipImpl extends CallingRelationshipImpl implements DeploymentCallingRelationship {
	/**
	 * The cached value of the '{@link #getCallingDeploymentComponent() <em>Calling Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallingDeploymentComponent()
	 * @generated
	 * @ordered
	 */
	protected DeploymentComponent callingDeploymentComponent;

	/**
	 * The cached value of the '{@link #getCalledDeploymentComponent() <em>Called Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledDeploymentComponent()
	 * @generated
	 * @ordered
	 */
	protected DeploymentComponent calledDeploymentComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentCallingRelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.DEPLOYMENT_CALLING_RELATIONSHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent getCallingDeploymentComponent() {
		if (callingDeploymentComponent != null && callingDeploymentComponent.eIsProxy()) {
			InternalEObject oldCallingDeploymentComponent = (InternalEObject)callingDeploymentComponent;
			callingDeploymentComponent = (DeploymentComponent)eResolveProxy(oldCallingDeploymentComponent);
			if (callingDeploymentComponent != oldCallingDeploymentComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLING_DEPLOYMENT_COMPONENT, oldCallingDeploymentComponent, callingDeploymentComponent));
			}
		}
		return callingDeploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent basicGetCallingDeploymentComponent() {
		return callingDeploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallingDeploymentComponent(DeploymentComponent newCallingDeploymentComponent) {
		DeploymentComponent oldCallingDeploymentComponent = callingDeploymentComponent;
		callingDeploymentComponent = newCallingDeploymentComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLING_DEPLOYMENT_COMPONENT, oldCallingDeploymentComponent, callingDeploymentComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent getCalledDeploymentComponent() {
		if (calledDeploymentComponent != null && calledDeploymentComponent.eIsProxy()) {
			InternalEObject oldCalledDeploymentComponent = (InternalEObject)calledDeploymentComponent;
			calledDeploymentComponent = (DeploymentComponent)eResolveProxy(oldCalledDeploymentComponent);
			if (calledDeploymentComponent != oldCalledDeploymentComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLED_DEPLOYMENT_COMPONENT, oldCalledDeploymentComponent, calledDeploymentComponent));
			}
		}
		return calledDeploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent basicGetCalledDeploymentComponent() {
		return calledDeploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledDeploymentComponent(DeploymentComponent newCalledDeploymentComponent) {
		DeploymentComponent oldCalledDeploymentComponent = calledDeploymentComponent;
		calledDeploymentComponent = newCalledDeploymentComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLED_DEPLOYMENT_COMPONENT, oldCalledDeploymentComponent, calledDeploymentComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLING_DEPLOYMENT_COMPONENT:
				if (resolve) return getCallingDeploymentComponent();
				return basicGetCallingDeploymentComponent();
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLED_DEPLOYMENT_COMPONENT:
				if (resolve) return getCalledDeploymentComponent();
				return basicGetCalledDeploymentComponent();
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
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLING_DEPLOYMENT_COMPONENT:
				setCallingDeploymentComponent((DeploymentComponent)newValue);
				return;
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLED_DEPLOYMENT_COMPONENT:
				setCalledDeploymentComponent((DeploymentComponent)newValue);
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
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLING_DEPLOYMENT_COMPONENT:
				setCallingDeploymentComponent((DeploymentComponent)null);
				return;
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLED_DEPLOYMENT_COMPONENT:
				setCalledDeploymentComponent((DeploymentComponent)null);
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
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLING_DEPLOYMENT_COMPONENT:
				return callingDeploymentComponent != null;
			case UsagePackage.DEPLOYMENT_CALLING_RELATIONSHIP__CALLED_DEPLOYMENT_COMPONENT:
				return calledDeploymentComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //DeploymentCallingRelationshipImpl
