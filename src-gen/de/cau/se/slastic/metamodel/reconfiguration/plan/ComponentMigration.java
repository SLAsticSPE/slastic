/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.reconfiguration.plan;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Migration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getComponent <em>Component</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentMigration()
 * @model
 * @generated
 */
public interface ComponentMigration extends ReconfigurationOperation {
	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(DeploymentComponent)
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentMigration_Component()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DeploymentComponent getComponent();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(DeploymentComponent value);

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' reference.
	 * @see #setDestination(ExecutionContainer)
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentMigration_Destination()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainer getDestination();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentMigration#getDestination <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' reference.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(ExecutionContainer value);

} // ComponentMigration
