/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage.impl;

import java.util.Collection;

import kieker.tools.slastic.metamodel.core.impl.SLAsticModelImpl;

import kieker.tools.slastic.metamodel.usage.AssemblyComponentConnectorCallFrequency;
import kieker.tools.slastic.metamodel.usage.CallingRelationship;
import kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship;
import kieker.tools.slastic.metamodel.usage.DeploymentOperationCallFrequency;
import kieker.tools.slastic.metamodel.usage.OperationCallFrequency;
import kieker.tools.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency;
import kieker.tools.slastic.metamodel.usage.UsageModel;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

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
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.UsageModelImpl#getCallingRelationships <em>Calling Relationships</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.UsageModelImpl#getOperationCallFrequencies <em>Operation Call Frequencies</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.UsageModelImpl#getAssemblyComponentConnectorCallFrequencies <em>Assembly Component Connector Call Frequencies</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.UsageModelImpl#getSystemProvidedInterfaceDelegationConnectorFrequencies <em>System Provided Interface Delegation Connector Frequencies</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.UsageModelImpl#getDeploymentCallingRelationships <em>Deployment Calling Relationships</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.UsageModelImpl#getDeploymentOperationCallFrequencies <em>Deployment Operation Call Frequencies</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UsageModelImpl extends SLAsticModelImpl implements UsageModel {
	/**
	 * The cached value of the '{@link #getCallingRelationships() <em>Calling Relationships</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallingRelationships()
	 * @generated
	 * @ordered
	 */
	protected EList<CallingRelationship> callingRelationships;

	/**
	 * The cached value of the '{@link #getOperationCallFrequencies() <em>Operation Call Frequencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationCallFrequencies()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationCallFrequency> operationCallFrequencies;

	/**
	 * The cached value of the '{@link #getAssemblyComponentConnectorCallFrequencies() <em>Assembly Component Connector Call Frequencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyComponentConnectorCallFrequencies()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyComponentConnectorCallFrequency> assemblyComponentConnectorCallFrequencies;

	/**
	 * The cached value of the '{@link #getSystemProvidedInterfaceDelegationConnectorFrequencies() <em>System Provided Interface Delegation Connector Frequencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemProvidedInterfaceDelegationConnectorFrequencies()
	 * @generated
	 * @ordered
	 */
	protected EList<SystemProvidedInterfaceDelegationConnectorFrequency> systemProvidedInterfaceDelegationConnectorFrequencies;

	/**
	 * The cached value of the '{@link #getDeploymentCallingRelationships() <em>Deployment Calling Relationships</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentCallingRelationships()
	 * @generated
	 * @ordered
	 */
	protected EList<DeploymentCallingRelationship> deploymentCallingRelationships;

	/**
	 * The cached value of the '{@link #getDeploymentOperationCallFrequencies() <em>Deployment Operation Call Frequencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentOperationCallFrequencies()
	 * @generated
	 * @ordered
	 */
	protected EList<DeploymentOperationCallFrequency> deploymentOperationCallFrequencies;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UsageModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.USAGE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CallingRelationship> getCallingRelationships() {
		if (callingRelationships == null) {
			callingRelationships = new EObjectContainmentEList<CallingRelationship>(CallingRelationship.class, this, UsagePackage.USAGE_MODEL__CALLING_RELATIONSHIPS);
		}
		return callingRelationships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationCallFrequency> getOperationCallFrequencies() {
		if (operationCallFrequencies == null) {
			operationCallFrequencies = new EObjectContainmentEList<OperationCallFrequency>(OperationCallFrequency.class, this, UsagePackage.USAGE_MODEL__OPERATION_CALL_FREQUENCIES);
		}
		return operationCallFrequencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssemblyComponentConnectorCallFrequency> getAssemblyComponentConnectorCallFrequencies() {
		if (assemblyComponentConnectorCallFrequencies == null) {
			assemblyComponentConnectorCallFrequencies = new EObjectContainmentEList<AssemblyComponentConnectorCallFrequency>(AssemblyComponentConnectorCallFrequency.class, this, UsagePackage.USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES);
		}
		return assemblyComponentConnectorCallFrequencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SystemProvidedInterfaceDelegationConnectorFrequency> getSystemProvidedInterfaceDelegationConnectorFrequencies() {
		if (systemProvidedInterfaceDelegationConnectorFrequencies == null) {
			systemProvidedInterfaceDelegationConnectorFrequencies = new EObjectContainmentEList<SystemProvidedInterfaceDelegationConnectorFrequency>(SystemProvidedInterfaceDelegationConnectorFrequency.class, this, UsagePackage.USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES);
		}
		return systemProvidedInterfaceDelegationConnectorFrequencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeploymentCallingRelationship> getDeploymentCallingRelationships() {
		if (deploymentCallingRelationships == null) {
			deploymentCallingRelationships = new EObjectContainmentEList<DeploymentCallingRelationship>(DeploymentCallingRelationship.class, this, UsagePackage.USAGE_MODEL__DEPLOYMENT_CALLING_RELATIONSHIPS);
		}
		return deploymentCallingRelationships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeploymentOperationCallFrequency> getDeploymentOperationCallFrequencies() {
		if (deploymentOperationCallFrequencies == null) {
			deploymentOperationCallFrequencies = new EObjectContainmentEList<DeploymentOperationCallFrequency>(DeploymentOperationCallFrequency.class, this, UsagePackage.USAGE_MODEL__DEPLOYMENT_OPERATION_CALL_FREQUENCIES);
		}
		return deploymentOperationCallFrequencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsagePackage.USAGE_MODEL__CALLING_RELATIONSHIPS:
				return ((InternalEList<?>)getCallingRelationships()).basicRemove(otherEnd, msgs);
			case UsagePackage.USAGE_MODEL__OPERATION_CALL_FREQUENCIES:
				return ((InternalEList<?>)getOperationCallFrequencies()).basicRemove(otherEnd, msgs);
			case UsagePackage.USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES:
				return ((InternalEList<?>)getAssemblyComponentConnectorCallFrequencies()).basicRemove(otherEnd, msgs);
			case UsagePackage.USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES:
				return ((InternalEList<?>)getSystemProvidedInterfaceDelegationConnectorFrequencies()).basicRemove(otherEnd, msgs);
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_CALLING_RELATIONSHIPS:
				return ((InternalEList<?>)getDeploymentCallingRelationships()).basicRemove(otherEnd, msgs);
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_OPERATION_CALL_FREQUENCIES:
				return ((InternalEList<?>)getDeploymentOperationCallFrequencies()).basicRemove(otherEnd, msgs);
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
			case UsagePackage.USAGE_MODEL__CALLING_RELATIONSHIPS:
				return getCallingRelationships();
			case UsagePackage.USAGE_MODEL__OPERATION_CALL_FREQUENCIES:
				return getOperationCallFrequencies();
			case UsagePackage.USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES:
				return getAssemblyComponentConnectorCallFrequencies();
			case UsagePackage.USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES:
				return getSystemProvidedInterfaceDelegationConnectorFrequencies();
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_CALLING_RELATIONSHIPS:
				return getDeploymentCallingRelationships();
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_OPERATION_CALL_FREQUENCIES:
				return getDeploymentOperationCallFrequencies();
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
			case UsagePackage.USAGE_MODEL__CALLING_RELATIONSHIPS:
				getCallingRelationships().clear();
				getCallingRelationships().addAll((Collection<? extends CallingRelationship>)newValue);
				return;
			case UsagePackage.USAGE_MODEL__OPERATION_CALL_FREQUENCIES:
				getOperationCallFrequencies().clear();
				getOperationCallFrequencies().addAll((Collection<? extends OperationCallFrequency>)newValue);
				return;
			case UsagePackage.USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES:
				getAssemblyComponentConnectorCallFrequencies().clear();
				getAssemblyComponentConnectorCallFrequencies().addAll((Collection<? extends AssemblyComponentConnectorCallFrequency>)newValue);
				return;
			case UsagePackage.USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES:
				getSystemProvidedInterfaceDelegationConnectorFrequencies().clear();
				getSystemProvidedInterfaceDelegationConnectorFrequencies().addAll((Collection<? extends SystemProvidedInterfaceDelegationConnectorFrequency>)newValue);
				return;
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_CALLING_RELATIONSHIPS:
				getDeploymentCallingRelationships().clear();
				getDeploymentCallingRelationships().addAll((Collection<? extends DeploymentCallingRelationship>)newValue);
				return;
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_OPERATION_CALL_FREQUENCIES:
				getDeploymentOperationCallFrequencies().clear();
				getDeploymentOperationCallFrequencies().addAll((Collection<? extends DeploymentOperationCallFrequency>)newValue);
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
			case UsagePackage.USAGE_MODEL__CALLING_RELATIONSHIPS:
				getCallingRelationships().clear();
				return;
			case UsagePackage.USAGE_MODEL__OPERATION_CALL_FREQUENCIES:
				getOperationCallFrequencies().clear();
				return;
			case UsagePackage.USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES:
				getAssemblyComponentConnectorCallFrequencies().clear();
				return;
			case UsagePackage.USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES:
				getSystemProvidedInterfaceDelegationConnectorFrequencies().clear();
				return;
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_CALLING_RELATIONSHIPS:
				getDeploymentCallingRelationships().clear();
				return;
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_OPERATION_CALL_FREQUENCIES:
				getDeploymentOperationCallFrequencies().clear();
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
			case UsagePackage.USAGE_MODEL__CALLING_RELATIONSHIPS:
				return callingRelationships != null && !callingRelationships.isEmpty();
			case UsagePackage.USAGE_MODEL__OPERATION_CALL_FREQUENCIES:
				return operationCallFrequencies != null && !operationCallFrequencies.isEmpty();
			case UsagePackage.USAGE_MODEL__ASSEMBLY_COMPONENT_CONNECTOR_CALL_FREQUENCIES:
				return assemblyComponentConnectorCallFrequencies != null && !assemblyComponentConnectorCallFrequencies.isEmpty();
			case UsagePackage.USAGE_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FREQUENCIES:
				return systemProvidedInterfaceDelegationConnectorFrequencies != null && !systemProvidedInterfaceDelegationConnectorFrequencies.isEmpty();
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_CALLING_RELATIONSHIPS:
				return deploymentCallingRelationships != null && !deploymentCallingRelationships.isEmpty();
			case UsagePackage.USAGE_MODEL__DEPLOYMENT_OPERATION_CALL_FREQUENCIES:
				return deploymentOperationCallFrequencies != null && !deploymentOperationCallFrequencies.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UsageModelImpl
