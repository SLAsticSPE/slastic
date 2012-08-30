/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

import kieker.tools.slastic.metamodel.typeRepository.Operation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Component Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getDeploymentComponent <em>Deployment Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getDeploymentComponentOperationExecution()
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
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getDeploymentComponentOperationExecution_DeploymentComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DeploymentComponent getDeploymentComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getDeploymentComponent <em>Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment Component</em>' reference.
	 * @see #getDeploymentComponent()
	 * @generated
	 */
	void setDeploymentComponent(DeploymentComponent value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' reference.
	 * @see #setOperation(Operation)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getDeploymentComponentOperationExecution_Operation()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getOperation <em>Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

} // DeploymentComponentOperationExecution
