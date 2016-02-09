/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository.resourceTypes.impl;

import kieker.tools.slastic.metamodel.typeRepository.resourceTypes.*;

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
public class ResourceTypesFactoryImpl extends EFactoryImpl implements ResourceTypesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResourceTypesFactory init() {
		try {
			ResourceTypesFactory theResourceTypesFactory = (ResourceTypesFactory)EPackage.Registry.INSTANCE.getEFactory(ResourceTypesPackage.eNS_URI);
			if (theResourceTypesFactory != null) {
				return theResourceTypesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ResourceTypesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceTypesFactoryImpl() {
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
			case ResourceTypesPackage.CPU_TYPE: return createCPUType();
			case ResourceTypesPackage.GENERIC_RESOURCE_TYPE: return createGenericResourceType();
			case ResourceTypesPackage.MEM_SWAP_TYPE: return createMemSwapType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CPUType createCPUType() {
		CPUTypeImpl cpuType = new CPUTypeImpl();
		return cpuType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GenericResourceType createGenericResourceType() {
		GenericResourceTypeImpl genericResourceType = new GenericResourceTypeImpl();
		return genericResourceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemSwapType createMemSwapType() {
		MemSwapTypeImpl memSwapType = new MemSwapTypeImpl();
		return memSwapType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceTypesPackage getResourceTypesPackage() {
		return (ResourceTypesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ResourceTypesPackage getPackage() {
		return ResourceTypesPackage.eINSTANCE;
	}

} //ResourceTypesFactoryImpl
