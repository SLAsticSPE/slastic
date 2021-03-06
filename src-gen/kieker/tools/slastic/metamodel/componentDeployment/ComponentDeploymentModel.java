/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentDeployment;

import kieker.tools.slastic.metamodel.core.SLAsticModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentModel#getDeploymentComponents <em>Deployment Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage#getComponentDeploymentModel()
 * @model
 * @generated
 */
public interface ComponentDeploymentModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>Deployment Components</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Deployment Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Deployment Components</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage#getComponentDeploymentModel_DeploymentComponents()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<DeploymentComponent> getDeploymentComponents();

} // ComponentDeploymentModel
