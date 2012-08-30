/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository.resourceTypes;

import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

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
 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesFactory
 * @model kind="package"
 * @generated
 */
public interface ResourceTypesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "resourceTypes";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/typeRepository/resourceTypes.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.typeRepository.resourceTypes";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ResourceTypesPackage eINSTANCE = kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl.init();

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl <em>CPU Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl#getCPUType()
	 * @generated
	 */
	int CPU_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__ID = TypeRepositoryPackage.RESOURCE_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__ACTIVE = TypeRepositoryPackage.RESOURCE_TYPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__NAME = TypeRepositoryPackage.RESOURCE_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__PACKAGE_NAME = TypeRepositoryPackage.RESOURCE_TYPE__PACKAGE_NAME;

	/**
	 * The feature id for the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__VENDOR = TypeRepositoryPackage.RESOURCE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__MODEL = TypeRepositoryPackage.RESOURCE_TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Speed Mhz</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE__SPEED_MHZ = TypeRepositoryPackage.RESOURCE_TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>CPU Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_TYPE_FEATURE_COUNT = TypeRepositoryPackage.RESOURCE_TYPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.GenericResourceTypeImpl <em>Generic Resource Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.GenericResourceTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl#getGenericResourceType()
	 * @generated
	 */
	int GENERIC_RESOURCE_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_RESOURCE_TYPE__ID = TypeRepositoryPackage.RESOURCE_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_RESOURCE_TYPE__ACTIVE = TypeRepositoryPackage.RESOURCE_TYPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_RESOURCE_TYPE__NAME = TypeRepositoryPackage.RESOURCE_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_RESOURCE_TYPE__PACKAGE_NAME = TypeRepositoryPackage.RESOURCE_TYPE__PACKAGE_NAME;

	/**
	 * The number of structural features of the '<em>Generic Resource Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERIC_RESOURCE_TYPE_FEATURE_COUNT = TypeRepositoryPackage.RESOURCE_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.MemSwapTypeImpl <em>Mem Swap Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.MemSwapTypeImpl
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl#getMemSwapType()
	 * @generated
	 */
	int MEM_SWAP_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_TYPE__ID = TypeRepositoryPackage.RESOURCE_TYPE__ID;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_TYPE__ACTIVE = TypeRepositoryPackage.RESOURCE_TYPE__ACTIVE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_TYPE__NAME = TypeRepositoryPackage.RESOURCE_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_TYPE__PACKAGE_NAME = TypeRepositoryPackage.RESOURCE_TYPE__PACKAGE_NAME;

	/**
	 * The number of structural features of the '<em>Mem Swap Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_TYPE_FEATURE_COUNT = TypeRepositoryPackage.RESOURCE_TYPE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType <em>CPU Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CPU Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType
	 * @generated
	 */
	EClass getCPUType();

	/**
	 * Returns the meta object for the attribute '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getVendor <em>Vendor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Vendor</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getVendor()
	 * @see #getCPUType()
	 * @generated
	 */
	EAttribute getCPUType_Vendor();

	/**
	 * Returns the meta object for the attribute '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getModel()
	 * @see #getCPUType()
	 * @generated
	 */
	EAttribute getCPUType_Model();

	/**
	 * Returns the meta object for the attribute '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getSpeedMhz <em>Speed Mhz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Speed Mhz</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getSpeedMhz()
	 * @see #getCPUType()
	 * @generated
	 */
	EAttribute getCPUType_SpeedMhz();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType <em>Generic Resource Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generic Resource Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.GenericResourceType
	 * @generated
	 */
	EClass getGenericResourceType();

	/**
	 * Returns the meta object for class '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.MemSwapType <em>Mem Swap Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mem Swap Type</em>'.
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.MemSwapType
	 * @generated
	 */
	EClass getMemSwapType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ResourceTypesFactory getResourceTypesFactory();

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
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl <em>CPU Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl#getCPUType()
		 * @generated
		 */
		EClass CPU_TYPE = eINSTANCE.getCPUType();

		/**
		 * The meta object literal for the '<em><b>Vendor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_TYPE__VENDOR = eINSTANCE.getCPUType_Vendor();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_TYPE__MODEL = eINSTANCE.getCPUType_Model();

		/**
		 * The meta object literal for the '<em><b>Speed Mhz</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_TYPE__SPEED_MHZ = eINSTANCE.getCPUType_SpeedMhz();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.GenericResourceTypeImpl <em>Generic Resource Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.GenericResourceTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl#getGenericResourceType()
		 * @generated
		 */
		EClass GENERIC_RESOURCE_TYPE = eINSTANCE.getGenericResourceType();

		/**
		 * The meta object literal for the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.MemSwapTypeImpl <em>Mem Swap Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.MemSwapTypeImpl
		 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl.ResourceTypesPackageImpl#getMemSwapType()
		 * @generated
		 */
		EClass MEM_SWAP_TYPE = eINSTANCE.getMemSwapType();

	}

} //ResourceTypesPackage
