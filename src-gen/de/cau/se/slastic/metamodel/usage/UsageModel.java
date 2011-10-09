/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.UsageModel#getCallingRelationships <em>Calling Relationships</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.UsageModel#getOperationCallFrequencies <em>Operation Call Frequencies</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.UsageModel#getAssemblyComponentConnectorCallFrequencies <em>Assembly Component Connector Call Frequencies</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.UsageModel#getSystemProvidedInterfaceDelegationConnectorFrequencies <em>System Provided Interface Delegation Connector Frequencies</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getUsageModel()
 * @model
 * @generated
 */
public interface UsageModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>Calling Relationships</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.usage.CallingRelationship}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calling Relationships</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calling Relationships</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getUsageModel_CallingRelationships()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<CallingRelationship> getCallingRelationships();

	/**
	 * Returns the value of the '<em><b>Operation Call Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.usage.OperationCallFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Call Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Call Frequencies</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getUsageModel_OperationCallFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<OperationCallFrequency> getOperationCallFrequencies();

	/**
	 * Returns the value of the '<em><b>Assembly Component Connector Call Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.usage.AssemblyComponentConnectorCallFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Component Connector Call Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Component Connector Call Frequencies</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getUsageModel_AssemblyComponentConnectorCallFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AssemblyComponentConnectorCallFrequency> getAssemblyComponentConnectorCallFrequencies();

	/**
	 * Returns the value of the '<em><b>System Provided Interface Delegation Connector Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Provided Interface Delegation Connector Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Provided Interface Delegation Connector Frequencies</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getUsageModel_SystemProvidedInterfaceDelegationConnectorFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<SystemProvidedInterfaceDelegationConnectorFrequency> getSystemProvidedInterfaceDelegationConnectorFrequencies();

} // UsageModel
