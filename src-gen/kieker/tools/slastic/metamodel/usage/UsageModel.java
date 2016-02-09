/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage;

import kieker.tools.slastic.metamodel.core.SLAsticModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.UsageModel#getCallingRelationships <em>Calling Relationships</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.UsageModel#getOperationCallFrequencies <em>Operation Call Frequencies</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.UsageModel#getAssemblyComponentConnectorCallFrequencies <em>Assembly Component Connector Call Frequencies</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.UsageModel#getSystemProvidedInterfaceDelegationConnectorFrequencies <em>System Provided Interface Delegation Connector Frequencies</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.UsageModel#getDeploymentCallingRelationships <em>Deployment Calling Relationships</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.UsageModel#getDeploymentOperationCallFrequencies <em>Deployment Operation Call Frequencies</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel()
 * @model
 * @generated
 */
public interface UsageModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>Calling Relationships</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.usage.CallingRelationship}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calling Relationships</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calling Relationships</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel_CallingRelationships()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<CallingRelationship> getCallingRelationships();

	/**
	 * Returns the value of the '<em><b>Operation Call Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.usage.OperationCallFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Call Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Call Frequencies</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel_OperationCallFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<OperationCallFrequency> getOperationCallFrequencies();

	/**
	 * Returns the value of the '<em><b>Assembly Component Connector Call Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.usage.AssemblyComponentConnectorCallFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Component Connector Call Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Component Connector Call Frequencies</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel_AssemblyComponentConnectorCallFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AssemblyComponentConnectorCallFrequency> getAssemblyComponentConnectorCallFrequencies();

	/**
	 * Returns the value of the '<em><b>System Provided Interface Delegation Connector Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>System Provided Interface Delegation Connector Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>System Provided Interface Delegation Connector Frequencies</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel_SystemProvidedInterfaceDelegationConnectorFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<SystemProvidedInterfaceDelegationConnectorFrequency> getSystemProvidedInterfaceDelegationConnectorFrequencies();

	/**
	 * Returns the value of the '<em><b>Deployment Calling Relationships</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment Calling Relationships</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment Calling Relationships</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel_DeploymentCallingRelationships()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<DeploymentCallingRelationship> getDeploymentCallingRelationships();

	/**
	 * Returns the value of the '<em><b>Deployment Operation Call Frequencies</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.usage.DeploymentOperationCallFrequency}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment Operation Call Frequencies</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment Operation Call Frequencies</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getUsageModel_DeploymentOperationCallFrequencies()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<DeploymentOperationCallFrequency> getDeploymentOperationCallFrequencies();

} // UsageModel
