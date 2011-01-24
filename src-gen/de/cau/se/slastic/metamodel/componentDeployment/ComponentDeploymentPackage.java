/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentDeployment;

import de.cau.se.slastic.metamodel.core.CorePackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentDeploymentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "componentDeployment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/componentDeployment.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.componentDeployment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentDeploymentPackage eINSTANCE = de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentDeployment.impl.DeploymentComponentImpl <em>Deployment Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.DeploymentComponentImpl
	 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl#getDeploymentComponent()
	 * @generated
	 */
	int DEPLOYMENT_COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT__ACTIVE = CorePackage.ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Assembly Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deployment Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentModelImpl
	 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl#getComponentDeploymentModel()
	 * @generated
	 */
	int COMPONENT_DEPLOYMENT_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Deployment Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_DEPLOYMENT_MODEL_FEATURE_COUNT = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent <em>Deployment Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent
	 * @generated
	 */
	EClass getDeploymentComponent();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent#getAssemblyComponent <em>Assembly Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Assembly Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent#getAssemblyComponent()
	 * @see #getDeploymentComponent()
	 * @generated
	 */
	EReference getDeploymentComponent_AssemblyComponent();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent#getExecutionContainer <em>Execution Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execution Container</em>'.
	 * @see de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent#getExecutionContainer()
	 * @see #getDeploymentComponent()
	 * @generated
	 */
	EReference getDeploymentComponent_ExecutionContainer();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel
	 * @generated
	 */
	EClass getComponentDeploymentModel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel#getDeploymentComponents <em>Deployment Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deployment Components</em>'.
	 * @see de.cau.se.slastic.metamodel.componentDeployment.ComponentDeploymentModel#getDeploymentComponents()
	 * @see #getComponentDeploymentModel()
	 * @generated
	 */
	EReference getComponentDeploymentModel_DeploymentComponents();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentDeploymentFactory getComponentDeploymentFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentDeployment.impl.DeploymentComponentImpl <em>Deployment Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.DeploymentComponentImpl
		 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl#getDeploymentComponent()
		 * @generated
		 */
		EClass DEPLOYMENT_COMPONENT = eINSTANCE.getDeploymentComponent();

		/**
		 * The meta object literal for the '<em><b>Assembly Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT = eINSTANCE.getDeploymentComponent_AssemblyComponent();

		/**
		 * The meta object literal for the '<em><b>Execution Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER = eINSTANCE.getDeploymentComponent_ExecutionContainer();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentModelImpl
		 * @see de.cau.se.slastic.metamodel.componentDeployment.impl.ComponentDeploymentPackageImpl#getComponentDeploymentModel()
		 * @generated
		 */
		EClass COMPONENT_DEPLOYMENT_MODEL = eINSTANCE.getComponentDeploymentModel();

		/**
		 * The meta object literal for the '<em><b>Deployment Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_DEPLOYMENT_MODEL__DEPLOYMENT_COMPONENTS = eINSTANCE.getComponentDeploymentModel_DeploymentComponents();

	}

} //ComponentDeploymentPackage
