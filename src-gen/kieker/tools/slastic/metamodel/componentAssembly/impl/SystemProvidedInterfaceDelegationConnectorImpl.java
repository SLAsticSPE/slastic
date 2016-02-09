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
import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Provided Interface Delegation Connector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.impl.SystemProvidedInterfaceDelegationConnectorImpl#getProvidingComponent <em>Providing Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.impl.SystemProvidedInterfaceDelegationConnectorImpl#getComponentAssemblyModel <em>Component Assembly Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemProvidedInterfaceDelegationConnectorImpl extends SystemInterfaceDelegationConnectorImpl implements SystemProvidedInterfaceDelegationConnector {
	/**
	 * The cached value of the '{@link #getProvidingComponent() <em>Providing Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvidingComponent()
	 * @generated
	 * @ordered
	 */
	protected AssemblyComponent providingComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemProvidedInterfaceDelegationConnectorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentAssemblyPackage.Literals.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent getProvidingComponent() {
		if (providingComponent != null && providingComponent.eIsProxy()) {
			InternalEObject oldProvidingComponent = (InternalEObject)providingComponent;
			providingComponent = (AssemblyComponent)eResolveProxy(oldProvidingComponent);
			if (providingComponent != oldProvidingComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT, oldProvidingComponent, providingComponent));
			}
		}
		return providingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent basicGetProvidingComponent() {
		return providingComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvidingComponent(AssemblyComponent newProvidingComponent) {
		AssemblyComponent oldProvidingComponent = providingComponent;
		providingComponent = newProvidingComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT, oldProvidingComponent, providingComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyModel getComponentAssemblyModel() {
		if (eContainerFeatureID() != ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL) return null;
		return (ComponentAssemblyModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentAssemblyModel(ComponentAssemblyModel newComponentAssemblyModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newComponentAssemblyModel, ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentAssemblyModel(ComponentAssemblyModel newComponentAssemblyModel) {
		if (newComponentAssemblyModel != eInternalContainer() || (eContainerFeatureID() != ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL && newComponentAssemblyModel != null)) {
			if (EcoreUtil.isAncestor(this, newComponentAssemblyModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newComponentAssemblyModel != null)
				msgs = ((InternalEObject)newComponentAssemblyModel).eInverseAdd(this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS, ComponentAssemblyModel.class, msgs);
			msgs = basicSetComponentAssemblyModel(newComponentAssemblyModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL, newComponentAssemblyModel, newComponentAssemblyModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
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
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
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
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				return eInternalContainer().eInverseRemove(this, ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS, ComponentAssemblyModel.class, msgs);
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
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT:
				if (resolve) return getProvidingComponent();
				return basicGetProvidingComponent();
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
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
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT:
				setProvidingComponent((AssemblyComponent)newValue);
				return;
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
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
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT:
				setProvidingComponent((AssemblyComponent)null);
				return;
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
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
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT:
				return providingComponent != null;
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL:
				return getComponentAssemblyModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //SystemProvidedInterfaceDelegationConnectorImpl
