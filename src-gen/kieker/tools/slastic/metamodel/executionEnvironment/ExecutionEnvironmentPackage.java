/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment;

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
 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentFactory
 * @model kind="package"
 * @generated
 */
public interface ExecutionEnvironmentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "executionEnvironment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/executionEnvironment.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.executionEnvironment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ExecutionEnvironmentPackage eINSTANCE = kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl.init();

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl <em>Execution Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getExecutionContainer()
	 * @generated
	 */
	int EXECUTION_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Execution Container Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Network Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__NETWORK_LINKS = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER__RESOURCES = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Execution Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTAINER_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.NetworkLinkImpl <em>Network Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.NetworkLinkImpl
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getNetworkLink()
	 * @generated
	 */
	int NETWORK_LINK = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK__ID = CorePackage.FQ_NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK__ACTIVE = CorePackage.FQ_NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK__NAME = CorePackage.FQ_NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK__PACKAGE_NAME = CorePackage.FQ_NAMED_ENTITY__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Network Link Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK__NETWORK_LINK_TYPE = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Containers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK__EXECUTION_CONTAINERS = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Network Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NETWORK_LINK_FEATURE_COUNT = CorePackage.FQ_NAMED_ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceImpl
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 2;

	/**
	 * The feature id for the '<em><b>Resource Specification</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__RESOURCE_SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Execution Container</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__EXECUTION_CONTAINER = 1;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getExecutionEnvironmentModel()
	 * @generated
	 */
	int EXECUTION_ENVIRONMENT_MODEL = 3;

	/**
	 * The feature id for the '<em><b>Execution Containers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Network Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Allocated Execution Containers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_ENVIRONMENT_MODEL_FEATURE_COUNT = CorePackage.SL_ASTIC_MODEL_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceSpecificationImpl <em>Resource Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceSpecificationImpl
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getResourceSpecification()
	 * @generated
	 */
	int RESOURCE_SPECIFICATION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPECIFICATION__ID = CorePackage.NAMED_ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPECIFICATION__ACTIVE = CorePackage.NAMED_ENTITY__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPECIFICATION__NAME = CorePackage.NAMED_ENTITY__NAME;

	/**
	 * The feature id for the '<em><b>Resource Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPECIFICATION__RESOURCE_TYPE = CorePackage.NAMED_ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_SPECIFICATION_FEATURE_COUNT = CorePackage.NAMED_ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.MemSwapResourceSpecificationImpl <em>Mem Swap Resource Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.MemSwapResourceSpecificationImpl
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getMemSwapResourceSpecification()
	 * @generated
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION__ID = RESOURCE_SPECIFICATION__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION__ACTIVE = RESOURCE_SPECIFICATION__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION__NAME = RESOURCE_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Resource Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION__RESOURCE_TYPE = RESOURCE_SPECIFICATION__RESOURCE_TYPE;

	/**
	 * The feature id for the '<em><b>Mem Capacity Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES = RESOURCE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Swap Capacity Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES = RESOURCE_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Mem Swap Resource Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_RESOURCE_SPECIFICATION_FEATURE_COUNT = RESOURCE_SPECIFICATION_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer <em>Execution Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Container</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer
	 * @generated
	 */
	EClass getExecutionContainer();

	/**
	 * Returns the meta object for the reference '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getExecutionContainerType <em>Execution Container Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execution Container Type</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getExecutionContainerType()
	 * @see #getExecutionContainer()
	 * @generated
	 */
	EReference getExecutionContainer_ExecutionContainerType();

	/**
	 * Returns the meta object for the reference list '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getNetworkLinks <em>Network Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Network Links</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getNetworkLinks()
	 * @see #getExecutionContainer()
	 * @generated
	 */
	EReference getExecutionContainer_NetworkLinks();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getResources()
	 * @see #getExecutionContainer()
	 * @generated
	 */
	EReference getExecutionContainer_Resources();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink <em>Network Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Network Link</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink
	 * @generated
	 */
	EClass getNetworkLink();

	/**
	 * Returns the meta object for the reference '{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getNetworkLinkType <em>Network Link Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Network Link Type</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getNetworkLinkType()
	 * @see #getNetworkLink()
	 * @generated
	 */
	EReference getNetworkLink_NetworkLinkType();

	/**
	 * Returns the meta object for the reference list '{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getExecutionContainers <em>Execution Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Execution Containers</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getExecutionContainers()
	 * @see #getNetworkLink()
	 * @generated
	 */
	EReference getNetworkLink_ExecutionContainers();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the reference '{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getResourceSpecification <em>Resource Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource Specification</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.Resource#getResourceSpecification()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_ResourceSpecification();

	/**
	 * Returns the meta object for the container reference '{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getExecutionContainer <em>Execution Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Execution Container</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.Resource#getExecutionContainer()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_ExecutionContainer();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel
	 * @generated
	 */
	EClass getExecutionEnvironmentModel();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getExecutionContainers <em>Execution Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Execution Containers</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getExecutionContainers()
	 * @see #getExecutionEnvironmentModel()
	 * @generated
	 */
	EReference getExecutionEnvironmentModel_ExecutionContainers();

	/**
	 * Returns the meta object for the containment reference list '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getNetworkLinks <em>Network Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Network Links</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getNetworkLinks()
	 * @see #getExecutionEnvironmentModel()
	 * @generated
	 */
	EReference getExecutionEnvironmentModel_NetworkLinks();

	/**
	 * Returns the meta object for the reference list '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getAllocatedExecutionContainers <em>Allocated Execution Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Allocated Execution Containers</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getAllocatedExecutionContainers()
	 * @see #getExecutionEnvironmentModel()
	 * @generated
	 */
	EReference getExecutionEnvironmentModel_AllocatedExecutionContainers();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification <em>Mem Swap Resource Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mem Swap Resource Specification</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification
	 * @generated
	 */
	EClass getMemSwapResourceSpecification();

	/**
	 * Returns the meta object for the attribute '{@link kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getMemCapacityBytes <em>Mem Capacity Bytes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mem Capacity Bytes</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getMemCapacityBytes()
	 * @see #getMemSwapResourceSpecification()
	 * @generated
	 */
	EAttribute getMemSwapResourceSpecification_MemCapacityBytes();

	/**
	 * Returns the meta object for the attribute '{@link kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getSwapCapacityBytes <em>Swap Capacity Bytes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Swap Capacity Bytes</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getSwapCapacityBytes()
	 * @see #getMemSwapResourceSpecification()
	 * @generated
	 */
	EAttribute getMemSwapResourceSpecification_SwapCapacityBytes();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification <em>Resource Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Specification</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification
	 * @generated
	 */
	EClass getResourceSpecification();

	/**
	 * Returns the meta object for the reference '{@link kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification#getResourceType <em>Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource Type</em>'.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification#getResourceType()
	 * @see #getResourceSpecification()
	 * @generated
	 */
	EReference getResourceSpecification_ResourceType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ExecutionEnvironmentFactory getExecutionEnvironmentFactory();

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
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl <em>Execution Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionContainerImpl
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getExecutionContainer()
		 * @generated
		 */
		EClass EXECUTION_CONTAINER = eINSTANCE.getExecutionContainer();

		/**
		 * The meta object literal for the '<em><b>Execution Container Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTAINER__EXECUTION_CONTAINER_TYPE = eINSTANCE.getExecutionContainer_ExecutionContainerType();

		/**
		 * The meta object literal for the '<em><b>Network Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTAINER__NETWORK_LINKS = eINSTANCE.getExecutionContainer_NetworkLinks();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTAINER__RESOURCES = eINSTANCE.getExecutionContainer_Resources();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.NetworkLinkImpl <em>Network Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.NetworkLinkImpl
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getNetworkLink()
		 * @generated
		 */
		EClass NETWORK_LINK = eINSTANCE.getNetworkLink();

		/**
		 * The meta object literal for the '<em><b>Network Link Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK_LINK__NETWORK_LINK_TYPE = eINSTANCE.getNetworkLink_NetworkLinkType();

		/**
		 * The meta object literal for the '<em><b>Execution Containers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NETWORK_LINK__EXECUTION_CONTAINERS = eINSTANCE.getNetworkLink_ExecutionContainers();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceImpl
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Resource Specification</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__RESOURCE_SPECIFICATION = eINSTANCE.getResource_ResourceSpecification();

		/**
		 * The meta object literal for the '<em><b>Execution Container</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__EXECUTION_CONTAINER = eINSTANCE.getResource_ExecutionContainer();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentModelImpl
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getExecutionEnvironmentModel()
		 * @generated
		 */
		EClass EXECUTION_ENVIRONMENT_MODEL = eINSTANCE.getExecutionEnvironmentModel();

		/**
		 * The meta object literal for the '<em><b>Execution Containers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_ENVIRONMENT_MODEL__EXECUTION_CONTAINERS = eINSTANCE.getExecutionEnvironmentModel_ExecutionContainers();

		/**
		 * The meta object literal for the '<em><b>Network Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_ENVIRONMENT_MODEL__NETWORK_LINKS = eINSTANCE.getExecutionEnvironmentModel_NetworkLinks();

		/**
		 * The meta object literal for the '<em><b>Allocated Execution Containers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_ENVIRONMENT_MODEL__ALLOCATED_EXECUTION_CONTAINERS = eINSTANCE.getExecutionEnvironmentModel_AllocatedExecutionContainers();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.MemSwapResourceSpecificationImpl <em>Mem Swap Resource Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.MemSwapResourceSpecificationImpl
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getMemSwapResourceSpecification()
		 * @generated
		 */
		EClass MEM_SWAP_RESOURCE_SPECIFICATION = eINSTANCE.getMemSwapResourceSpecification();

		/**
		 * The meta object literal for the '<em><b>Mem Capacity Bytes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES = eINSTANCE.getMemSwapResourceSpecification_MemCapacityBytes();

		/**
		 * The meta object literal for the '<em><b>Swap Capacity Bytes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES = eINSTANCE.getMemSwapResourceSpecification_SwapCapacityBytes();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceSpecificationImpl <em>Resource Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ResourceSpecificationImpl
		 * @see kieker.tools.slastic.metamodel.executionEnvironment.impl.ExecutionEnvironmentPackageImpl#getResourceSpecification()
		 * @generated
		 */
		EClass RESOURCE_SPECIFICATION = eINSTANCE.getResourceSpecification();

		/**
		 * The meta object literal for the '<em><b>Resource Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_SPECIFICATION__RESOURCE_TYPE = eINSTANCE.getResourceSpecification_ResourceType();

	}

} //ExecutionEnvironmentPackage
