/**
 */
package kieker.tools.slastic.metamodel.usage;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Calling Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship#getCallingDeploymentComponent <em>Calling Deployment Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship#getCalledDeploymentComponent <em>Called Deployment Component</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getDeploymentCallingRelationship()
 * @model
 * @generated
 */
public interface DeploymentCallingRelationship extends CallingRelationship {
	/**
	 * Returns the value of the '<em><b>Calling Deployment Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calling Deployment Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Calling Deployment Component</em>' reference.
	 * @see #setCallingDeploymentComponent(DeploymentComponent)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getDeploymentCallingRelationship_CallingDeploymentComponent()
	 * @model required="true"
	 * @generated
	 */
	DeploymentComponent getCallingDeploymentComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship#getCallingDeploymentComponent <em>Calling Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calling Deployment Component</em>' reference.
	 * @see #getCallingDeploymentComponent()
	 * @generated
	 */
	void setCallingDeploymentComponent(DeploymentComponent value);

	/**
	 * Returns the value of the '<em><b>Called Deployment Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Deployment Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Deployment Component</em>' reference.
	 * @see #setCalledDeploymentComponent(DeploymentComponent)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getDeploymentCallingRelationship_CalledDeploymentComponent()
	 * @model required="true"
	 * @generated
	 */
	DeploymentComponent getCalledDeploymentComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.DeploymentCallingRelationship#getCalledDeploymentComponent <em>Called Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Deployment Component</em>' reference.
	 * @see #getCalledDeploymentComponent()
	 * @generated
	 */
	void setCalledDeploymentComponent(DeploymentComponent value);

} // DeploymentCallingRelationship
