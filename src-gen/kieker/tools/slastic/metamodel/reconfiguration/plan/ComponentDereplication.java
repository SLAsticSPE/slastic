/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Dereplication</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentDereplication#getComponent <em>Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentDereplication()
 * @model
 * @generated
 */
public interface ComponentDereplication extends ReconfigurationOperation {
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
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getComponentDereplication_Component()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DeploymentComponent getComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ComponentDereplication#getComponent <em>Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component</em>' reference.
	 * @see #getComponent()
	 * @generated
	 */
	void setComponent(DeploymentComponent value);

} // ComponentDereplication
