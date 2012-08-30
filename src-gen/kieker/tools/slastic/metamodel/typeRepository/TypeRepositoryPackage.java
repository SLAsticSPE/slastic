/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository;

import kieker.tools.slastic.metamodel.core.CorePackage;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryFactory
 * @model kind="package"
 * @generated
 */
public interface TypeRepositoryPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "typeRepository";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/typeRepository.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.typeRepository";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypeRepositoryPackage eINSTANCE = kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl.init();

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ResourceTypeImpl <em>Resource Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ResourceTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getResourceType()
	 * @generated
	 */
	int RESOURCE_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The number of structural features of the '<em>Resource Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_TYPE_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ComponentTypeImpl <em>Component Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ComponentTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getComponentType()
	 * @generated
	 */
	int COMPONENT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Provided Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__PROVIDED_INTERFACES = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required Interfaces</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__REQUIRED_INTERFACES = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__OPERATIONS = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.InterfaceImpl <em>Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.InterfaceImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getInterface()
	 * @generated
	 */
	int INTERFACE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Signatures</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE__SIGNATURES = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERFACE_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.SignatureImpl <em>Signature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.SignatureImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getSignature()
	 * @generated
	 */
	int SIGNATURE = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__ID = CorePackage.NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__ACTIVE = CorePackage.NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__NAME = CorePackage.NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Param Types</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__PARAM_TYPES = CorePackage.NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Return Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE__RETURN_TYPE = CorePackage.NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Signature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNATURE_FEATURE_COUNT = CorePackage.NAMED_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.OperationImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ID = CorePackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ACTIVE = CorePackage.ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__SIGNATURE = CorePackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__COMPONENT_TYPE = CorePackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = CorePackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ConnectorTypeImpl <em>Connector Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ConnectorTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getConnectorType()
	 * @generated
	 */
	int CONNECTOR_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE__INTERFACE = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connector Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_TYPE_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ExecutionContainerTypeImpl <em>Execution Container Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ExecutionContainerTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getExecutionContainerType()
	 * @generated
	 */
	int EXECUTION_CONTAINER_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_TYPE__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_TYPE__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_TYPE__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_TYPE__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_TYPE__RESOURCES = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Execution Container Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_TYPE_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.NetworkLinkTypeImpl <em>Network Link Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.NetworkLinkTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getNetworkLinkType()
	 * @generated
	 */
	int NETWORK_LINK_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK_TYPE__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK_TYPE__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK_TYPE__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK_TYPE__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The number of structural features of the '<em>Network Link Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK_TYPE_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getTypeRepositoryModel()
	 * @generated
	 */
	int TYPE_REPOSITORY_MODEL = 8;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL__COMPONENT_TYPES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Container Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Interfaces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL__INTERFACES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Connector Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Network Link Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Resource Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL__RESOURCE_TYPES = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_REPOSITORY_MODEL_FEATURE_COUNT = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 6;


	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.ResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ResourceType
	 * @generated
	 */
	EClass getResourceType();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ComponentType
	 * @generated
	 */
	EClass getComponentType();

	/**
	 * Returns the meta object for the reference list '{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getProvidedInterfaces <em>Provided Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Provided Interfaces</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ComponentType#getProvidedInterfaces()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_ProvidedInterfaces();

	/**
	 * Returns the meta object for the reference list '{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getRequiredInterfaces <em>Required Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Interfaces</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ComponentType#getRequiredInterfaces()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_RequiredInterfaces();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ComponentType#getOperations()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Operations();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.Interface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interface</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Interface
	 * @generated
	 */
	EClass getInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.Interface#getSignatures <em>Signatures</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Signatures</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Interface#getSignatures()
	 * @see #getInterface()
	 * @generated
	 */
	EReference getInterface_Signatures();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.Signature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signature</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Signature
	 * @generated
	 */
	EClass getSignature();

	/**
	 * Returns the meta object for the attribute list '{@link kieker.tools.slastic.metamodel.typeRepository.Signature#getParamTypes <em>Param Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Param Types</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Signature#getParamTypes()
	 * @see #getSignature()
	 * @generated
	 */
	EAttribute getSignature_ParamTypes();

	/**
	 * Returns the meta object for the attribute '{@link kieker.tools.slastic.metamodel.typeRepository.Signature#getReturnType <em>Return Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Return Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Signature#getReturnType()
	 * @see #getSignature()
	 * @generated
	 */
	EAttribute getSignature_ReturnType();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference '{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Signature</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Operation#getSignature()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Signature();

	/**
	 * Returns the meta object for the container reference '{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Component Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.Operation#getComponentType()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_ComponentType();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.ConnectorType <em>Connector Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ConnectorType
	 * @generated
	 */
	EClass getConnectorType();

	/**
	 * Returns the meta object for the reference '{@link kieker.tools.slastic.metamodel.typeRepository.ConnectorType#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Interface</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ConnectorType#getInterface()
	 * @see #getConnectorType()
	 * @generated
	 */
	EReference getConnectorType_Interface();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType <em>Execution Container Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Container Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType
	 * @generated
	 */
	EClass getExecutionContainerType();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType#getResources()
	 * @see #getExecutionContainerType()
	 * @generated
	 */
	EReference getExecutionContainerType_Resources();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType <em>Network Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network Link Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType
	 * @generated
	 */
	EClass getNetworkLinkType();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel
	 * @generated
	 */
	EClass getTypeRepositoryModel();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getComponentTypes <em>Component Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Types</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getComponentTypes()
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	EReference getTypeRepositoryModel_ComponentTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getExecutionContainerTypes <em>Execution Container Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution Container Types</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getExecutionContainerTypes()
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	EReference getTypeRepositoryModel_ExecutionContainerTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getInterfaces <em>Interfaces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Interfaces</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getInterfaces()
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	EReference getTypeRepositoryModel_Interfaces();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getConnectorTypes <em>Connector Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connector Types</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getConnectorTypes()
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	EReference getTypeRepositoryModel_ConnectorTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getNetworkLinkTypes <em>Network Link Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Network Link Types</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getNetworkLinkTypes()
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	EReference getTypeRepositoryModel_NetworkLinkTypes();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getResourceTypes <em>Resource Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Types</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel#getResourceTypes()
	 * @see #getTypeRepositoryModel()
	 * @generated
	 */
	EReference getTypeRepositoryModel_ResourceTypes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TypeRepositoryFactory getTypeRepositoryFactory();

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
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ResourceTypeImpl <em>Resource Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ResourceTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getResourceType()
		 * @generated
		 */
		EClass RESOURCE_TYPE = eINSTANCE.getResourceType();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ComponentTypeImpl <em>Component Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ComponentTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getComponentType()
		 * @generated
		 */
		EClass COMPONENT_TYPE = eINSTANCE.getComponentType();

		/**
		 * The meta object literal for the '<em><b>Provided Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__PROVIDED_INTERFACES = eINSTANCE.getComponentType_ProvidedInterfaces();

		/**
		 * The meta object literal for the '<em><b>Required Interfaces</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__REQUIRED_INTERFACES = eINSTANCE.getComponentType_RequiredInterfaces();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__OPERATIONS = eINSTANCE.getComponentType_Operations();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.InterfaceImpl <em>Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.InterfaceImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getInterface()
		 * @generated
		 */
		EClass INTERFACE = eINSTANCE.getInterface();

		/**
		 * The meta object literal for the '<em><b>Signatures</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERFACE__SIGNATURES = eINSTANCE.getInterface_Signatures();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.SignatureImpl <em>Signature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.SignatureImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getSignature()
		 * @generated
		 */
		EClass SIGNATURE = eINSTANCE.getSignature();

		/**
		 * The meta object literal for the '<em><b>Param Types</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE__PARAM_TYPES = eINSTANCE.getSignature_ParamTypes();

		/**
		 * The meta object literal for the '<em><b>Return Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNATURE__RETURN_TYPE = eINSTANCE.getSignature_ReturnType();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.OperationImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__SIGNATURE = eINSTANCE.getOperation_Signature();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__COMPONENT_TYPE = eINSTANCE.getOperation_ComponentType();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ConnectorTypeImpl <em>Connector Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ConnectorTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getConnectorType()
		 * @generated
		 */
		EClass CONNECTOR_TYPE = eINSTANCE.getConnectorType();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_TYPE__INTERFACE = eINSTANCE.getConnectorType_Interface();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.ExecutionContainerTypeImpl <em>Execution Container Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.ExecutionContainerTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getExecutionContainerType()
		 * @generated
		 */
		EClass EXECUTION_CONTAINER_TYPE = eINSTANCE.getExecutionContainerType();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTAINER_TYPE__RESOURCES = eINSTANCE.getExecutionContainerType_Resources();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.NetworkLinkTypeImpl <em>Network Link Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.NetworkLinkTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getNetworkLinkType()
		 * @generated
		 */
		EClass NETWORK_LINK_TYPE = eINSTANCE.getNetworkLinkType();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryModelImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.impl.TypeRepositoryPackageImpl#getTypeRepositoryModel()
		 * @generated
		 */
		EClass TYPE_REPOSITORY_MODEL = eINSTANCE.getTypeRepositoryModel();

		/**
		 * The meta object literal for the '<em><b>Component Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REPOSITORY_MODEL__COMPONENT_TYPES = eINSTANCE.getTypeRepositoryModel_ComponentTypes();

		/**
		 * The meta object literal for the '<em><b>Execution Container Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REPOSITORY_MODEL__EXECUTION_CONTAINER_TYPES = eINSTANCE.getTypeRepositoryModel_ExecutionContainerTypes();

		/**
		 * The meta object literal for the '<em><b>Interfaces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REPOSITORY_MODEL__INTERFACES = eINSTANCE.getTypeRepositoryModel_Interfaces();

		/**
		 * The meta object literal for the '<em><b>Connector Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REPOSITORY_MODEL__CONNECTOR_TYPES = eINSTANCE.getTypeRepositoryModel_ConnectorTypes();

		/**
		 * The meta object literal for the '<em><b>Network Link Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REPOSITORY_MODEL__NETWORK_LINK_TYPES = eINSTANCE.getTypeRepositoryModel_NetworkLinkTypes();

		/**
		 * The meta object literal for the '<em><b>Resource Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_REPOSITORY_MODEL__RESOURCE_TYPES = eINSTANCE.getTypeRepositoryModel_ResourceTypes();

	}

} //TypeRepositoryPackage
