/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring;

import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Component Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getDeploymentComponent <em>Deployment Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getDeploymentComponentOperationExecution()
 * @model
 * @generated
 */
public interface DeploymentComponentOperationExecution extends OperationExecution {
	/**
	 * Returns the value of the '<em><b>Deployment Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment Component</em>' reference.
	 * @see #setDeploymentComponent(DeploymentComponent)
	 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringPackage#getDeploymentComponentOperationExecution_DeploymentComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DeploymentComponent getDeploymentComponent();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getDeploymentComponent <em>Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment Component</em>' reference.
	 * @see #getDeploymentComponent()
	 * @generated
	 */
	void setDeploymentComponent(DeploymentComponent value);

} // DeploymentComponentOperationExecution
