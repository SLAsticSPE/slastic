/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.core;

import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;

import de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;

import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.core.SystemModel#getTypeRepositoryModel <em>Type Repository Model</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.core.SystemModel#getComponentAssemblyModel <em>Component Assembly Model</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.core.SystemModel#getComponentDeploymentModel <em>Component Deployment Model</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.core.SystemModel#getExecutionEnvironmentModel <em>Execution Environment Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.core.CorePackage#getSystemModel()
 * @model
 * @generated
 */
public interface SystemModel extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Repository Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Repository Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Repository Model</em>' containment reference.
	 * @see #setTypeRepositoryModel(TypeRepositoryModel)
	 * @see de.cau.se.slastic.metamodel.core.CorePackage#getSystemModel_TypeRepositoryModel()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	TypeRepositoryModel getTypeRepositoryModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.core.SystemModel#getTypeRepositoryModel <em>Type Repository Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type Repository Model</em>' containment reference.
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	void setTypeRepositoryModel(TypeRepositoryModel value);

	/**
	 * Returns the value of the '<em><b>Component Assembly Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Assembly Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Assembly Model</em>' containment reference.
	 * @see #setComponentAssemblyModel(ComponentAssemblyModel)
	 * @see de.cau.se.slastic.metamodel.core.CorePackage#getSystemModel_ComponentAssemblyModel()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	ComponentAssemblyModel getComponentAssemblyModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.core.SystemModel#getComponentAssemblyModel <em>Component Assembly Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Assembly Model</em>' containment reference.
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	void setComponentAssemblyModel(ComponentAssemblyModel value);

	/**
	 * Returns the value of the '<em><b>Component Deployment Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Deployment Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Deployment Model</em>' containment reference.
	 * @see #setComponentDeploymentModel(ComponentDeploymentModel)
	 * @see de.cau.se.slastic.metamodel.core.CorePackage#getSystemModel_ComponentDeploymentModel()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	ComponentDeploymentModel getComponentDeploymentModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.core.SystemModel#getComponentDeploymentModel <em>Component Deployment Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Deployment Model</em>' containment reference.
	 * @see #getComponentDeploymentModel()
	 * @generated
	 */
	void setComponentDeploymentModel(ComponentDeploymentModel value);

	/**
	 * Returns the value of the '<em><b>Execution Environment Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Environment Model</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Environment Model</em>' containment reference.
	 * @see #setExecutionEnvironmentModel(ExecutionEnvironmentModel)
	 * @see de.cau.se.slastic.metamodel.core.CorePackage#getSystemModel_ExecutionEnvironmentModel()
	 * @model containment="true" required="true" ordered="false"
	 * @generated
	 */
	ExecutionEnvironmentModel getExecutionEnvironmentModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.core.SystemModel#getExecutionEnvironmentModel <em>Execution Environment Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Environment Model</em>' containment reference.
	 * @see #getExecutionEnvironmentModel()
	 * @generated
	 */
	void setExecutionEnvironmentModel(ExecutionEnvironmentModel value);

} // SystemModel
