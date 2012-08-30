/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentDeployment.impl;

import kieker.tools.slastic.metamodel.componentDeployment.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentDeploymentFactoryImpl extends EFactoryImpl implements ComponentDeploymentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentDeploymentFactory init() {
		try {
			ComponentDeploymentFactory theComponentDeploymentFactory = (ComponentDeploymentFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/componentDeployment.ecore"); 
			if (theComponentDeploymentFactory != null) {
				return theComponentDeploymentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentDeploymentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDeploymentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT: return createDeploymentComponent();
			case ComponentDeploymentPackage.COMPONENT_DEPLOYMENT_MODEL: return createComponentDeploymentModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponent createDeploymentComponent() {
		DeploymentComponentImpl deploymentComponent = new DeploymentComponentImpl();
		return deploymentComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDeploymentModel createComponentDeploymentModel() {
		ComponentDeploymentModelImpl componentDeploymentModel = new ComponentDeploymentModelImpl();
		return componentDeploymentModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDeploymentPackage getComponentDeploymentPackage() {
		return (ComponentDeploymentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentDeploymentPackage getPackage() {
		return ComponentDeploymentPackage.eINSTANCE;
	}

} //ComponentDeploymentFactoryImpl
