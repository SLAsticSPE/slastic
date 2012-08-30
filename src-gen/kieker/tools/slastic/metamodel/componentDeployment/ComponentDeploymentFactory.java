/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentDeployment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage
 * @generated
 */
public interface ComponentDeploymentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentDeploymentFactory eINSTANCE = kieker.tools.slastic.metamodel.componentDeployment.impl.ComponentDeploymentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Deployment Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Component</em>'.
	 * @generated
	 */
	DeploymentComponent createDeploymentComponent();

	/**
	 * Returns a new object of class '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model</em>'.
	 * @generated
	 */
	ComponentDeploymentModel createComponentDeploymentModel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ComponentDeploymentPackage getComponentDeploymentPackage();

} //ComponentDeploymentFactory
