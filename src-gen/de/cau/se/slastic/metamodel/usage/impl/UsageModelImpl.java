/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.core.impl.SLAsticModelImpl;

import de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency;
import de.cau.se.slastic.metamodel.usage.CallingRelationship;
import de.cau.se.slastic.metamodel.usage.OperationCallFrequency;
import de.cau.se.slastic.metamodel.usage.UsageModel;
import de.cau.se.slastic.metamodel.usage.UsagePackage;

import java.util.Collection;

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
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl#getCallingRelationships <em>Calling Relationships</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl#getOperationCallFrequencies <em>Operation Call Frequencies</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.UsageModelImpl#getAssemblyConnectorCallFrequencies <em>Assembly Connector Call Frequencies</em>}</li>
 * </ul>
 * </p>
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
	 * The cached value of the '{@link #getAssemblyConnectorCallFrequencies() <em>Assembly Connector Call Frequencies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyConnectorCallFrequencies()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyConnectorCallFrequency> assemblyConnectorCallFrequencies;

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
	public EList<AssemblyConnectorCallFrequency> getAssemblyConnectorCallFrequencies() {
		if (assemblyConnectorCallFrequencies == null) {
			assemblyConnectorCallFrequencies = new EObjectContainmentEList<AssemblyConnectorCallFrequency>(AssemblyConnectorCallFrequency.class, this, UsagePackage.USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES);
		}
		return assemblyConnectorCallFrequencies;
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
			case UsagePackage.USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES:
				return ((InternalEList<?>)getAssemblyConnectorCallFrequencies()).basicRemove(otherEnd, msgs);
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
			case UsagePackage.USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES:
				return getAssemblyConnectorCallFrequencies();
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
			case UsagePackage.USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES:
				getAssemblyConnectorCallFrequencies().clear();
				getAssemblyConnectorCallFrequencies().addAll((Collection<? extends AssemblyConnectorCallFrequency>)newValue);
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
			case UsagePackage.USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES:
				getAssemblyConnectorCallFrequencies().clear();
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
			case UsagePackage.USAGE_MODEL__ASSEMBLY_CONNECTOR_CALL_FREQUENCIES:
				return assemblyConnectorCallFrequencies != null && !assemblyConnectorCallFrequencies.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UsageModelImpl
