/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly;

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
 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyFactory
 * @model kind="package"
 * @generated
 */
public interface ComponentAssemblyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "componentAssembly";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/componentAssembly.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.componentAssembly";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ComponentAssemblyPackage eINSTANCE = de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl <em>Assembly Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyComponent()
	 * @generated
	 */
	int ASSEMBLY_COMPONENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__COMPONENT_TYPE = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Providing Connectors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requiring Connectors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Assembly Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl <em>Assembly Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyConnector()
	 * @generated
	 */
	int ASSEMBLY_CONNECTOR = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__CONNECTOR_TYPE = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Providing Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Requiring Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR__REQUIRING_COMPONENT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Assembly Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getComponentAssemblyModel()
	 * @generated
	 */
	int COMPONENT_ASSEMBLY_MODEL = 2;

	/**
	 * The feature id for the '<em><b>Assembly Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Assembly Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL_FEATURE_COUNT = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent <em>Assembly Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent
	 * @generated
	 */
	EClass getAssemblyComponent();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component Type</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getComponentType()
	 * @see #getAssemblyComponent()
	 * @generated
	 */
	EReference getAssemblyComponent_ComponentType();

	/**
	 * Returns the meta object for the reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors <em>Providing Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Providing Connectors</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors()
	 * @see #getAssemblyComponent()
	 * @generated
	 */
	EReference getAssemblyComponent_ProvidingConnectors();

	/**
	 * Returns the meta object for the reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getRequiringConnectors <em>Requiring Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Requiring Connectors</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getRequiringConnectors()
	 * @see #getAssemblyComponent()
	 * @generated
	 */
	EReference getAssemblyComponent_RequiringConnectors();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector <em>Assembly Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector
	 * @generated
	 */
	EClass getAssemblyConnector();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getConnectorType <em>Connector Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Connector Type</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getConnectorType()
	 * @see #getAssemblyConnector()
	 * @generated
	 */
	EReference getAssemblyConnector_ConnectorType();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getProvidingComponent <em>Providing Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Providing Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getProvidingComponent()
	 * @see #getAssemblyConnector()
	 * @generated
	 */
	EReference getAssemblyConnector_ProvidingComponent();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getRequiringComponent <em>Requiring Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Requiring Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getRequiringComponent()
	 * @see #getAssemblyConnector()
	 * @generated
	 */
	EReference getAssemblyConnector_RequiringComponent();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel
	 * @generated
	 */
	EClass getComponentAssemblyModel();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponents <em>Assembly Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assembly Components</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponents()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_AssemblyComponents();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyConnectors <em>Assembly Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assembly Connectors</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyConnectors()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_AssemblyConnectors();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ComponentAssemblyFactory getComponentAssemblyFactory();

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
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl <em>Assembly Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyComponent()
		 * @generated
		 */
		EClass ASSEMBLY_COMPONENT = eINSTANCE.getAssemblyComponent();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_COMPONENT__COMPONENT_TYPE = eINSTANCE.getAssemblyComponent_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Providing Connectors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_COMPONENT__PROVIDING_CONNECTORS = eINSTANCE.getAssemblyComponent_ProvidingConnectors();

		/**
		 * The meta object literal for the '<em><b>Requiring Connectors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_COMPONENT__REQUIRING_CONNECTORS = eINSTANCE.getAssemblyComponent_RequiringConnectors();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl <em>Assembly Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyConnector()
		 * @generated
		 */
		EClass ASSEMBLY_CONNECTOR = eINSTANCE.getAssemblyConnector();

		/**
		 * The meta object literal for the '<em><b>Connector Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_CONNECTOR__CONNECTOR_TYPE = eINSTANCE.getAssemblyConnector_ConnectorType();

		/**
		 * The meta object literal for the '<em><b>Providing Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_CONNECTOR__PROVIDING_COMPONENT = eINSTANCE.getAssemblyConnector_ProvidingComponent();

		/**
		 * The meta object literal for the '<em><b>Requiring Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_CONNECTOR__REQUIRING_COMPONENT = eINSTANCE.getAssemblyConnector_RequiringComponent();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getComponentAssemblyModel()
		 * @generated
		 */
		EClass COMPONENT_ASSEMBLY_MODEL = eINSTANCE.getComponentAssemblyModel();

		/**
		 * The meta object literal for the '<em><b>Assembly Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS = eINSTANCE.getComponentAssemblyModel_AssemblyComponents();

		/**
		 * The meta object literal for the '<em><b>Assembly Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_CONNECTORS = eINSTANCE.getComponentAssemblyModel_AssemblyConnectors();

	}

} //ComponentAssemblyPackage
