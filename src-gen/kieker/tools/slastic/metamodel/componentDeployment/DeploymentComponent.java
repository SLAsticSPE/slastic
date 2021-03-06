/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentDeployment;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;

import kieker.tools.slastic.metamodel.core.Entity;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent#getAssemblyComponent <em>Assembly Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent#getExecutionContainer <em>Execution Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage#getDeploymentComponent()
 * @model
 * @generated
 */
public interface DeploymentComponent extends Entity {
	/**
	 * Returns the value of the '<em><b>Assembly Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Component</em>' reference.
	 * @see #setAssemblyComponent(AssemblyComponent)
	 * @see kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage#getDeploymentComponent_AssemblyComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getAssemblyComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent#getAssemblyComponent <em>Assembly Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assembly Component</em>' reference.
	 * @see #getAssemblyComponent()
	 * @generated
	 */
	void setAssemblyComponent(AssemblyComponent value);

	/**
	 * Returns the value of the '<em><b>Execution Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Container</em>' reference.
	 * @see #setExecutionContainer(ExecutionContainer)
	 * @see kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage#getDeploymentComponent_ExecutionContainer()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainer getExecutionContainer();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent#getExecutionContainer <em>Execution Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Container</em>' reference.
	 * @see #getExecutionContainer()
	 * @generated
	 */
	void setExecutionContainer(ExecutionContainer value);

} // DeploymentComponent
