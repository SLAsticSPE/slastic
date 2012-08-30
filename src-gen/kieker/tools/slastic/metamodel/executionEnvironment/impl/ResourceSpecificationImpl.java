/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment.impl;

import kieker.tools.slastic.metamodel.core.impl.NamedEntityImpl;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;

import kieker.tools.slastic.metamodel.typeRepository.ResourceType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceSpecificationImpl#getResourceType <em>Resource Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceSpecificationImpl extends NamedEntityImpl implements ResourceSpecification {
	/**
	 * The cached value of the '{@link #getResourceType() <em>Resource Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceType()
	 * @generated
	 * @ordered
	 */
	protected ResourceType resourceType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutionEnvironmentPackage.Literals.RESOURCE_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType getResourceType() {
		if (resourceType != null && resourceType.eIsProxy()) {
			InternalEObject oldResourceType = (InternalEObject)resourceType;
			resourceType = (ResourceType)eResolveProxy(oldResourceType);
			if (resourceType != oldResourceType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION__RESOURCE_TYPE, oldResourceType, resourceType));
			}
		}
		return resourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceType basicGetResourceType() {
		return resourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResourceType(ResourceType newResourceType) {
		ResourceType oldResourceType = resourceType;
		resourceType = newResourceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION__RESOURCE_TYPE, oldResourceType, resourceType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION__RESOURCE_TYPE:
				if (resolve) return getResourceType();
				return basicGetResourceType();
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
			case ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION__RESOURCE_TYPE:
				setResourceType((ResourceType)newValue);
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
			case ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION__RESOURCE_TYPE:
				setResourceType((ResourceType)null);
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
			case ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION__RESOURCE_TYPE:
				return resourceType != null;
		}
		return super.eIsSet(featureID);
	}

} //ResourceSpecificationImpl
