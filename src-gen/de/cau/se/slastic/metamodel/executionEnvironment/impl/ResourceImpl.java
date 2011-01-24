/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.executionEnvironment.impl;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ResourceImpl#getResourceSpecification <em>Resource Specification</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.impl.ResourceImpl#getExecutionContainer <em>Execution Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceImpl extends EObjectImpl implements Resource {
	/**
	 * The cached value of the '{@link #getResourceSpecification() <em>Resource Specification</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceSpecification()
	 * @generated
	 * @ordered
	 */
	protected ResourceSpecification resourceSpecification;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutionEnvironmentPackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSpecification getResourceSpecification() {
		if (resourceSpecification != null && resourceSpecification.eIsProxy()) {
			InternalEObject oldResourceSpecification = (InternalEObject)resourceSpecification;
			resourceSpecification = (ResourceSpecification)eResolveProxy(oldResourceSpecification);
			if (resourceSpecification != oldResourceSpecification) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutionEnvironmentPackage.RESOURCE__RESOURCE_SPECIFICATION, oldResourceSpecification, resourceSpecification));
			}
		}
		return resourceSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSpecification basicGetResourceSpecification() {
		return resourceSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceSpecification(ResourceSpecification newResourceSpecification) {
		ResourceSpecification oldResourceSpecification = resourceSpecification;
		resourceSpecification = newResourceSpecification;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.RESOURCE__RESOURCE_SPECIFICATION, oldResourceSpecification, resourceSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainer getExecutionContainer() {
		if (eContainerFeatureID() != ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER) return null;
		return (ExecutionContainer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutionContainer(ExecutionContainer newExecutionContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newExecutionContainer, ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionContainer(ExecutionContainer newExecutionContainer) {
		if (newExecutionContainer != eInternalContainer() || (eContainerFeatureID() != ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER && newExecutionContainer != null)) {
			if (EcoreUtil.isAncestor(this, newExecutionContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newExecutionContainer != null)
				msgs = ((InternalEObject)newExecutionContainer).eInverseAdd(this, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES, ExecutionContainer.class, msgs);
			msgs = basicSetExecutionContainer(newExecutionContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER, newExecutionContainer, newExecutionContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetExecutionContainer((ExecutionContainer)otherEnd, msgs);
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
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				return basicSetExecutionContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				return eInternalContainer().eInverseRemove(this, ExecutionEnvironmentPackage.EXECUTION_CONTAINER__RESOURCES, ExecutionContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.RESOURCE__RESOURCE_SPECIFICATION:
				if (resolve) return getResourceSpecification();
				return basicGetResourceSpecification();
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				return getExecutionContainer();
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
			case ExecutionEnvironmentPackage.RESOURCE__RESOURCE_SPECIFICATION:
				setResourceSpecification((ResourceSpecification)newValue);
				return;
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				setExecutionContainer((ExecutionContainer)newValue);
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
			case ExecutionEnvironmentPackage.RESOURCE__RESOURCE_SPECIFICATION:
				setResourceSpecification((ResourceSpecification)null);
				return;
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				setExecutionContainer((ExecutionContainer)null);
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
			case ExecutionEnvironmentPackage.RESOURCE__RESOURCE_SPECIFICATION:
				return resourceSpecification != null;
			case ExecutionEnvironmentPackage.RESOURCE__EXECUTION_CONTAINER:
				return getExecutionContainer() != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceImpl
