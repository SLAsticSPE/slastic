/**
 */
package kieker.tools.slastic.metamodel.usage;

import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Operation Call Frequency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.DeploymentOperationCallFrequency#getDeploymentComponent <em>Deployment Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getDeploymentOperationCallFrequency()
 * @model
 * @generated
 */
public interface DeploymentOperationCallFrequency extends OperationCallFrequency {
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
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getDeploymentOperationCallFrequency_DeploymentComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	DeploymentComponent getDeploymentComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.DeploymentOperationCallFrequency#getDeploymentComponent <em>Deployment Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Deployment Component</em>' reference.
	 * @see #getDeploymentComponent()
	 * @generated
	 */
	void setDeploymentComponent(DeploymentComponent value);

} // DeploymentOperationCallFrequency
