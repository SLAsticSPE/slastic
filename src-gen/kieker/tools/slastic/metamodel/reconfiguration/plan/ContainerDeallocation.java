/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Deallocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerDeallocation#getContainer <em>Container</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getContainerDeallocation()
 * @model
 * @generated
 */
public interface ContainerDeallocation extends ReconfigurationOperation {
	/**
	 * Returns the value of the '<em><b>Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container</em>' reference.
	 * @see #setContainer(ExecutionContainer)
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getContainerDeallocation_Container()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainer getContainer();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerDeallocation#getContainer <em>Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container</em>' reference.
	 * @see #getContainer()
	 * @generated
	 */
	void setContainer(ExecutionContainer value);

} // ContainerDeallocation
