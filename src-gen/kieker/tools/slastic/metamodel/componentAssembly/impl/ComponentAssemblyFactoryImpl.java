/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly.impl;

import kieker.tools.slastic.metamodel.componentAssembly.*;

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
public class ComponentAssemblyFactoryImpl extends EFactoryImpl implements ComponentAssemblyFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ComponentAssemblyFactory init() {
		try {
			ComponentAssemblyFactory theComponentAssemblyFactory = (ComponentAssemblyFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/componentAssembly.ecore"); 
			if (theComponentAssemblyFactory != null) {
				return theComponentAssemblyFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ComponentAssemblyFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyFactoryImpl() {
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
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT: return createAssemblyComponent();
			case ComponentAssemblyPackage.ASSEMBLY_COMPONENT_CONNECTOR: return createAssemblyComponentConnector();
			case ComponentAssemblyPackage.SYSTEM_PROVIDED_INTERFACE_DELEGATION_CONNECTOR: return createSystemProvidedInterfaceDelegationConnector();
			case ComponentAssemblyPackage.SYSTEM_REQUIRED_INTERFACE_DELEGATION_CONNECTOR: return createSystemRequiredInterfaceDelegationConnector();
			case ComponentAssemblyPackage.COMPONENT_ASSEMBLY_MODEL: return createComponentAssemblyModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent createAssemblyComponent() {
		AssemblyComponentImpl assemblyComponent = new AssemblyComponentImpl();
		return assemblyComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponentConnector createAssemblyComponentConnector() {
		AssemblyComponentConnectorImpl assemblyComponentConnector = new AssemblyComponentConnectorImpl();
		return assemblyComponentConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemProvidedInterfaceDelegationConnector createSystemProvidedInterfaceDelegationConnector() {
		SystemProvidedInterfaceDelegationConnectorImpl systemProvidedInterfaceDelegationConnector = new SystemProvidedInterfaceDelegationConnectorImpl();
		return systemProvidedInterfaceDelegationConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemRequiredInterfaceDelegationConnector createSystemRequiredInterfaceDelegationConnector() {
		SystemRequiredInterfaceDelegationConnectorImpl systemRequiredInterfaceDelegationConnector = new SystemRequiredInterfaceDelegationConnectorImpl();
		return systemRequiredInterfaceDelegationConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyModel createComponentAssemblyModel() {
		ComponentAssemblyModelImpl componentAssemblyModel = new ComponentAssemblyModelImpl();
		return componentAssemblyModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyPackage getComponentAssemblyPackage() {
		return (ComponentAssemblyPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ComponentAssemblyPackage getPackage() {
		return ComponentAssemblyPackage.eINSTANCE;
	}

} //ComponentAssemblyFactoryImpl
