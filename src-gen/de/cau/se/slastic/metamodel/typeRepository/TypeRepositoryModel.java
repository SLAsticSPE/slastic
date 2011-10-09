/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository;

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
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getComponentTypes <em>Component Types</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getExecutionContainerTypes <em>Execution Container Types</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getInterfaces <em>Interfaces</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getConnectorTypes <em>Connector Types</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getNetworkLinkTypes <em>Network Link Types</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getResourceTypes <em>Resource Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel()
 * @model
 * @generated
 */
public interface TypeRepositoryModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>Component Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.ComponentType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Types</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel_ComponentTypes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ComponentType> getComponentTypes();

	/**
	 * Returns the value of the '<em><b>Execution Container Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.ExecutionContainerType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Container Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Container Types</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel_ExecutionContainerTypes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ExecutionContainerType> getExecutionContainerTypes();

	/**
	 * Returns the value of the '<em><b>Interfaces</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interfaces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interfaces</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel_Interfaces()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Interface> getInterfaces();

	/**
	 * Returns the value of the '<em><b>Connector Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.ConnectorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Types</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel_ConnectorTypes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ConnectorType> getConnectorTypes();

	/**
	 * Returns the value of the '<em><b>Network Link Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Network Link Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network Link Types</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel_NetworkLinkTypes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<NetworkLinkType> getNetworkLinkTypes();

	/**
	 * Returns the value of the '<em><b>Resource Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.ResourceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Types</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getTypeRepositoryModel_ResourceTypes()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ResourceType> getResourceTypes();

} // TypeRepositoryModel
