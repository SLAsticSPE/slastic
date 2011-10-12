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
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl <em>Assembly Component Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyComponentConnector()
	 * @generated
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR = 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl <em>Assembly Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyConnectorImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyConnector()
	 * @generated
	 */
	int ASSEMBLY_CONNECTOR = 2;

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
	 * The number of structural features of the '<em>Assembly Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_CONNECTOR_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__ID = ASSEMBLY_CONNECTOR__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__ACTIVE = ASSEMBLY_CONNECTOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__NAME = ASSEMBLY_CONNECTOR__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__PACKAGE_NAME = ASSEMBLY_CONNECTOR__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__CONNECTOR_TYPE = ASSEMBLY_CONNECTOR__CONNECTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Providing Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT = ASSEMBLY_CONNECTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Requiring Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT = ASSEMBLY_CONNECTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Assembly Component Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSEMBLY_COMPONENT_CONNECTOR_FEATURE_COUNT = ASSEMBLY_CONNECTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.SystemInterfaceDelegationConnectorImpl <em>System Interface Delegation Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.SystemInterfaceDelegationConnectorImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getSystemInterfaceDelegationConnector()
	 * @generated
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR__ID = ASSEMBLY_CONNECTOR__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR__ACTIVE = ASSEMBLY_CONNECTOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR__NAME = ASSEMBLY_CONNECTOR__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR__PACKAGE_NAME = ASSEMBLY_CONNECTOR__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR__CONNECTOR_TYPE = ASSEMBLY_CONNECTOR__CONNECTOR_TYPE;

	/**
	 * The number of structural features of the '<em>System Interface Delegation Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT = ASSEMBLY_CONNECTOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.SystemProvidedInterfaceDelegationConnectorImpl <em>System Provided Interface Delegation Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.SystemProvidedInterfaceDelegationConnectorImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getSystemProvidedInterfaceDelegationConnector()
	 * @generated
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__ID = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__ACTIVE = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__NAME = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PACKAGE_NAME = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__CONNECTOR_TYPE = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__CONNECTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Providing Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT = SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Component Assembly Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL = SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System Provided Interface Delegation Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT = SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.SystemRequiredInterfaceDelegationConnectorImpl <em>System Required Interface Delegation Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.SystemRequiredInterfaceDelegationConnectorImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getSystemRequiredInterfaceDelegationConnector()
	 * @generated
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__ID = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__ACTIVE = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__NAME = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__PACKAGE_NAME = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__CONNECTOR_TYPE = SYSTEM_INTERFACE_DELEGATION_CONNECTOR__CONNECTOR_TYPE;

	/**
	 * The feature id for the '<em><b>Requiring Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT = SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Component Assembly Model</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL = SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>System Required Interface Delegation Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT = SYSTEM_INTERFACE_DELEGATION_CONNECTOR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyModelImpl
	 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getComponentAssemblyModel()
	 * @generated
	 */
	int COMPONENT_ASSEMBLY_MODEL = 6;

	/**
	 * The feature id for the '<em><b>System Provided Interface Delegation Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System Required Interface Delegation Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Assembly Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Assembly Component Connectors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>System Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>System Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_ASSEMBLY_MODEL_FEATURE_COUNT = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 6;


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
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector <em>Assembly Component Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Assembly Component Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector
	 * @generated
	 */
	EClass getAssemblyComponentConnector();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getProvidingComponent <em>Providing Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Providing Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getProvidingComponent()
	 * @see #getAssemblyComponentConnector()
	 * @generated
	 */
	EReference getAssemblyComponentConnector_ProvidingComponent();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getRequiringComponent <em>Requiring Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Requiring Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getRequiringComponent()
	 * @see #getAssemblyComponentConnector()
	 * @generated
	 */
	EReference getAssemblyComponentConnector_RequiringComponent();

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
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemInterfaceDelegationConnector <em>System Interface Delegation Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Interface Delegation Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemInterfaceDelegationConnector
	 * @generated
	 */
	EClass getSystemInterfaceDelegationConnector();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector <em>System Provided Interface Delegation Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Provided Interface Delegation Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector
	 * @generated
	 */
	EClass getSystemProvidedInterfaceDelegationConnector();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getProvidingComponent <em>Providing Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Providing Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getProvidingComponent()
	 * @see #getSystemProvidedInterfaceDelegationConnector()
	 * @generated
	 */
	EReference getSystemProvidedInterfaceDelegationConnector_ProvidingComponent();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Component Assembly Model</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getComponentAssemblyModel()
	 * @see #getSystemProvidedInterfaceDelegationConnector()
	 * @generated
	 */
	EReference getSystemProvidedInterfaceDelegationConnector_ComponentAssemblyModel();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector <em>System Required Interface Delegation Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>System Required Interface Delegation Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector
	 * @generated
	 */
	EClass getSystemRequiredInterfaceDelegationConnector();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getRequiringComponent <em>Requiring Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Requiring Component</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getRequiringComponent()
	 * @see #getSystemRequiredInterfaceDelegationConnector()
	 * @generated
	 */
	EReference getSystemRequiredInterfaceDelegationConnector_RequiringComponent();

	/**
	 * Returns the meta object for the container reference '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Component Assembly Model</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getComponentAssemblyModel()
	 * @see #getSystemRequiredInterfaceDelegationConnector()
	 * @generated
	 */
	EReference getSystemRequiredInterfaceDelegationConnector_ComponentAssemblyModel();

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
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaceDelegationConnectors <em>System Provided Interface Delegation Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>System Provided Interface Delegation Connectors</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaceDelegationConnectors()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_SystemProvidedInterfaceDelegationConnectors();

	/**
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaceDelegationConnectors <em>System Required Interface Delegation Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>System Required Interface Delegation Connectors</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaceDelegationConnectors()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_SystemRequiredInterfaceDelegationConnectors();

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
	 * Returns the meta object for the containment reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponentConnectors <em>Assembly Component Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Assembly Component Connectors</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponentConnectors()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_AssemblyComponentConnectors();

	/**
	 * Returns the meta object for the reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaces <em>System Provided Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>System Provided Interfaces</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaces()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_SystemProvidedInterfaces();

	/**
	 * Returns the meta object for the reference list '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaces <em>System Required Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>System Required Interfaces</em>'.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaces()
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	EReference getComponentAssemblyModel_SystemRequiredInterfaces();

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
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl <em>Assembly Component Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.AssemblyComponentConnectorImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getAssemblyComponentConnector()
		 * @generated
		 */
		EClass ASSEMBLY_COMPONENT_CONNECTOR = eINSTANCE.getAssemblyComponentConnector();

		/**
		 * The meta object literal for the '<em><b>Providing Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_COMPONENT_CONNECTOR__PROVIDING_COMPONENT = eINSTANCE.getAssemblyComponentConnector_ProvidingComponent();

		/**
		 * The meta object literal for the '<em><b>Requiring Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSEMBLY_COMPONENT_CONNECTOR__REQUIRING_COMPONENT = eINSTANCE.getAssemblyComponentConnector_RequiringComponent();

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
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.SystemInterfaceDelegationConnectorImpl <em>System Interface Delegation Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.SystemInterfaceDelegationConnectorImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getSystemInterfaceDelegationConnector()
		 * @generated
		 */
		EClass SYSTEM_INTERFACE_DELEGATION_CONNECTOR = eINSTANCE.getSystemInterfaceDelegationConnector();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.SystemProvidedInterfaceDelegationConnectorImpl <em>System Provided Interface Delegation Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.SystemProvidedInterfaceDelegationConnectorImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getSystemProvidedInterfaceDelegationConnector()
		 * @generated
		 */
		EClass SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR = eINSTANCE.getSystemProvidedInterfaceDelegationConnector();

		/**
		 * The meta object literal for the '<em><b>Providing Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__PROVIDING_COMPONENT = eINSTANCE.getSystemProvidedInterfaceDelegationConnector_ProvidingComponent();

		/**
		 * The meta object literal for the '<em><b>Component Assembly Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL = eINSTANCE.getSystemProvidedInterfaceDelegationConnector_ComponentAssemblyModel();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.componentAssembly.impl.SystemRequiredInterfaceDelegationConnectorImpl <em>System Required Interface Delegation Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.SystemRequiredInterfaceDelegationConnectorImpl
		 * @see de.cau.se.slastic.metamodel.componentAssembly.impl.ComponentAssemblyPackageImpl#getSystemRequiredInterfaceDelegationConnector()
		 * @generated
		 */
		EClass SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR = eINSTANCE.getSystemRequiredInterfaceDelegationConnector();

		/**
		 * The meta object literal for the '<em><b>Requiring Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__REQUIRING_COMPONENT = eINSTANCE.getSystemRequiredInterfaceDelegationConnector_RequiringComponent();

		/**
		 * The meta object literal for the '<em><b>Component Assembly Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR__COMPONENT_ASSEMBLY_MODEL = eINSTANCE.getSystemRequiredInterfaceDelegationConnector_ComponentAssemblyModel();

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
		 * The meta object literal for the '<em><b>System Provided Interface Delegation Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTORS = eINSTANCE.getComponentAssemblyModel_SystemProvidedInterfaceDelegationConnectors();

		/**
		 * The meta object literal for the '<em><b>System Required Interface Delegation Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTORS = eINSTANCE.getComponentAssemblyModel_SystemRequiredInterfaceDelegationConnectors();

		/**
		 * The meta object literal for the '<em><b>Assembly Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENTS = eINSTANCE.getComponentAssemblyModel_AssemblyComponents();

		/**
		 * The meta object literal for the '<em><b>Assembly Component Connectors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__ASSEMBLY_COMPONENT_CONNECTORS = eINSTANCE.getComponentAssemblyModel_AssemblyComponentConnectors();

		/**
		 * The meta object literal for the '<em><b>System Provided Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__SYSTEM_PROVIDED_INTERFACES = eINSTANCE.getComponentAssemblyModel_SystemProvidedInterfaces();

		/**
		 * The meta object literal for the '<em><b>System Required Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_ASSEMBLY_MODEL__SYSTEM_REQUIRED_INTERFACES = eINSTANCE.getComponentAssemblyModel_SystemRequiredInterfaces();

	}

} //ComponentAssemblyPackage
