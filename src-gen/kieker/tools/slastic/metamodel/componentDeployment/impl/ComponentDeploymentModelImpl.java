/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentDeployment.impl;

import java.util.Collection;

import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentModel;
import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

import kieker.tools.slastic.metamodel.core.impl.SLAsticModelImpl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentDeployment.impl.ComponentDeploymentModelImpl#getDeploymentComponents <em>Deployment Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentDeploymentModelImpl extends SLAsticModelImpl implements ComponentDeploymentModel {
	/**
	 * The cached value of the '{@link #getDeploymentComponents() <em>Deployment Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<DeploymentComponent> deploymentComponents;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentDeploymentModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentDeploymentPackage.Literals.COMPONENT_DEPLOYMENT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeploymentComponent> getDeploymentComponents() {
		if (deploymentComponents == null) {
			deploymentComponents = new EObjectContainmentEList<DeploymentComponent>(DeploymentComponent.class, this, ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS);
		}
		return deploymentComponents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS:
				return ((InternalEList<?>)getDeploymentComponents()).basicRemove(otherEnd, msgs);
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
			case ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS:
				return getDeploymentComponents();
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
			case ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS:
				getDeploymentComponents().clear();
				getDeploymentComponents().addAll((Collection<? extends DeploymentComponent>)newValue);
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
			case ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS:
				getDeploymentComponents().clear();
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
			case ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS:
				return deploymentComponents != null && !deploymentComponents.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ComponentDeploymentModelImpl
