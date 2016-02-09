/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository.impl;

import java.util.Collection;

import kieker.tools.slastic.metamodel.core.impl.SLAsticModelImpl;

import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;
import kieker.tools.slastic.metamodel.typeRepository.ResourceType;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel;
import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage;

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
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl#getComponentTypes <em>Component Types</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl#getExecutionContainerTypes <em>Execution Container Types</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl#getConnectorTypes <em>Connector Types</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl#getNetworkLinkTypes <em>Network Link Types</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl#getResourceTypes <em>Resource Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypeRepositoryModelImpl extends SLAsticModelImpl implements TypeRepositoryModel {
	/**
	 * The cached value of the '{@link #getComponentTypes() <em>Component Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentType> componentTypes;

	/**
	 * The cached value of the '{@link #getExecutionContainerTypes() <em>Execution Container Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContainerTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionContainerType> executionContainerTypes;

	/**
	 * The cached value of the '{@link #getInterfaces() <em>Interfaces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface> interfaces;

	/**
	 * The cached value of the '{@link #getConnectorTypes() <em>Connector Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectorTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ConnectorType> connectorTypes;

	/**
	 * The cached value of the '{@link #getNetworkLinkTypes() <em>Network Link Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNetworkLinkTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<NetworkLinkType> networkLinkTypes;

	/**
	 * The cached value of the '{@link #getResourceTypes() <em>Resource Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ResourceType> resourceTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeRepositoryModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRepositoryPackage.Literals.TYPE_REPOSITORY_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ComponentType> getComponentTypes() {
		if (componentTypes == null) {
			componentTypes = new EObjectContainmentEList<ComponentType>(ComponentType.class, this, TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__COMPONENT_TYPES);
		}
		return componentTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionContainerType> getExecutionContainerTypes() {
		if (executionContainerTypes == null) {
			executionContainerTypes = new EObjectContainmentEList<ExecutionContainerType>(ExecutionContainerType.class, this, TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES);
		}
		return executionContainerTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface> getInterfaces() {
		if (interfaces == null) {
			interfaces = new EObjectContainmentEList<Interface>(Interface.class, this, TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__INTERFACES);
		}
		return interfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ConnectorType> getConnectorTypes() {
		if (connectorTypes == null) {
			connectorTypes = new EObjectContainmentEList<ConnectorType>(ConnectorType.class, this, TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES);
		}
		return connectorTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NetworkLinkType> getNetworkLinkTypes() {
		if (networkLinkTypes == null) {
			networkLinkTypes = new EObjectContainmentEList<NetworkLinkType>(NetworkLinkType.class, this, TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES);
		}
		return networkLinkTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ResourceType> getResourceTypes() {
		if (resourceTypes == null) {
			resourceTypes = new EObjectContainmentEList<ResourceType>(ResourceType.class, this, TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__RESOURCE_TYPES);
		}
		return resourceTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__COMPONENT_TYPES:
				return ((InternalEList<?>)getComponentTypes()).basicRemove(otherEnd, msgs);
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES:
				return ((InternalEList<?>)getExecutionContainerTypes()).basicRemove(otherEnd, msgs);
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__INTERFACES:
				return ((InternalEList<?>)getInterfaces()).basicRemove(otherEnd, msgs);
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES:
				return ((InternalEList<?>)getConnectorTypes()).basicRemove(otherEnd, msgs);
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES:
				return ((InternalEList<?>)getNetworkLinkTypes()).basicRemove(otherEnd, msgs);
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__RESOURCE_TYPES:
				return ((InternalEList<?>)getResourceTypes()).basicRemove(otherEnd, msgs);
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
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__COMPONENT_TYPES:
				return getComponentTypes();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES:
				return getExecutionContainerTypes();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__INTERFACES:
				return getInterfaces();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES:
				return getConnectorTypes();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES:
				return getNetworkLinkTypes();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__RESOURCE_TYPES:
				return getResourceTypes();
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
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__COMPONENT_TYPES:
				getComponentTypes().clear();
				getComponentTypes().addAll((Collection<? extends ComponentType>)newValue);
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES:
				getExecutionContainerTypes().clear();
				getExecutionContainerTypes().addAll((Collection<? extends ExecutionContainerType>)newValue);
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__INTERFACES:
				getInterfaces().clear();
				getInterfaces().addAll((Collection<? extends Interface>)newValue);
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES:
				getConnectorTypes().clear();
				getConnectorTypes().addAll((Collection<? extends ConnectorType>)newValue);
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES:
				getNetworkLinkTypes().clear();
				getNetworkLinkTypes().addAll((Collection<? extends NetworkLinkType>)newValue);
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__RESOURCE_TYPES:
				getResourceTypes().clear();
				getResourceTypes().addAll((Collection<? extends ResourceType>)newValue);
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
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__COMPONENT_TYPES:
				getComponentTypes().clear();
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES:
				getExecutionContainerTypes().clear();
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__INTERFACES:
				getInterfaces().clear();
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES:
				getConnectorTypes().clear();
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES:
				getNetworkLinkTypes().clear();
				return;
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__RESOURCE_TYPES:
				getResourceTypes().clear();
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
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__COMPONENT_TYPES:
				return componentTypes != null && !componentTypes.isEmpty();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES:
				return executionContainerTypes != null && !executionContainerTypes.isEmpty();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__INTERFACES:
				return interfaces != null && !interfaces.isEmpty();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES:
				return connectorTypes != null && !connectorTypes.isEmpty();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES:
				return networkLinkTypes != null && !networkLinkTypes.isEmpty();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL__RESOURCE_TYPES:
				return resourceTypes != null && !resourceTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TypeRepositoryModelImpl
