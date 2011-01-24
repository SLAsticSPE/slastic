/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.reconfiguration.plan;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Replication</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getComponent <em>Component</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getDestination <em>Destination</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentReplication()
 * @model
 * @generated
 */
public interface ComponentReplication extends ReconfigurationOperation {
	/**
	 * Returns the value of the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' reference.
	 * @see #setComponent(AssemblyComponent)
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentReplication_Component()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getComponent();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(AssemblyComponent value);

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
	 * @see de.cau.se.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentReplication_Destination()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainer getDestination();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.reconfiguration.plan.ComponentReplication#getDestination <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' reference.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(ExecutionContainer value);

} // ComponentReplication
