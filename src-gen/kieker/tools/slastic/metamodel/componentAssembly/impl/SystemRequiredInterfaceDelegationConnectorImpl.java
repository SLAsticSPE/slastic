/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly.impl;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage;
import kieker.tools.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Required Interface Delegation Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.impl.SystemRequiredInterfaceDelegationConnectorImpl#getRequiringComponent <em>Requiring Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.impl.SystemRequiredInterfaceDelegationConnectorImpl#getComponentAssemblyModel <em>Component Assembly Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemRequiredInterfaceDelegationConnectorImpl extends SystemInterfaceDelegationConnectorImpl implements SystemRequiredInterfaceDelegationConnector {
	/**
	 * The cached value of the '{@link #getRequiringComponent() <em>Requiring Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiringComponent()
	 * @generated
	 * @ordered
	 */
	protected AssemblyComponent requiringComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemRequiredInterfaceDelegationConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentAssemblyPackage.Literals.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent getRequiringComponent() {
		if (requiringComponent != null && requiringComponent.eIsProxy()) {
			InternalEObject oldRequiringComponent = (InternalEObject)requiringComponent;
			requiringComponent = (AssemblyComponent)eResolveProxy(oldRequiringComponent);
			if (requiringComponent != oldRequiringComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT, oldRequiringComponent, requiringComponent));
			}
		}
		return requiringComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent basicGetRequiringComponent() {
		return requiringComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRequiringComponent(AssemblyComponent newRequiringComponent) {
		AssemblyComponent oldRequiringComponent = requiringComponent;
		requiringComponent = newRequiringComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT, oldRequiringComponent, requiringComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyModel getComponentAssemblyModel() {
		if (eContainerFeatureID() != ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL) return null;
		return (ComponentAssemblyModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentAssemblyModel(ComponentAssemblyModel newComponentAssemblyModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newComponentAssemblyModel, ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentAssemblyModel(ComponentAssemblyModel newComponentAssemblyModel) {
		if (newComponentAssemblyModel != eInternalContainer() || (eContainerFeatureID() != ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL && newComponentAssemblyModel != null)) {
			if (EcoreUtil.isAncestor(this, newComponentAssemblyModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newComponentAssemblyModel != null)
				msgs = ((InternalEObject)newComponentAssemblyModel).eInverseAdd(this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS, ComponentAssemblyModel.class, msgs);
			msgs = basicSetComponentAssemblyModel(newComponentAssemblyModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL, newComponentAssemblyModel, newComponentAssemblyModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetComponentAssemblyModel((ComponentAssemblyModel)otherEnd, msgs);
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
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				return basicSetComponentAssemblyModel(null, msgs);
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
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				return eInternalContainer().eInverseRemove(this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS, ComponentAssemblyModel.class, msgs);
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
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT:
				if (resolve) return getRequiringComponent();
				return basicGetRequiringComponent();
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				return getComponentAssemblyModel();
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
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT:
				setRequiringComponent((AssemblyComponent)newValue);
				return;
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				setComponentAssemblyModel((ComponentAssemblyModel)newValue);
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
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT:
				setRequiringComponent((AssemblyComponent)null);
				return;
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				setComponentAssemblyModel((ComponentAssemblyModel)null);
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
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT:
				return requiringComponent != null;
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				return getComponentAssemblyModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //SystemRequiredInterfaceDelegationConnectorImpl
