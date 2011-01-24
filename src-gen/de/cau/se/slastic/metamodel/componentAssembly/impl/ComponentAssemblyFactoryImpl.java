/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly.impl;

import de.cau.se.slastic.metamodel.componentAssembly.*;

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
			case ComponentAssemblyPackage.ASSEMBLY_CONNECTOR: return createAssemblyConnector();
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
	public AssemblyConnector createAssemblyConnector() {
		AssemblyConnectorImpl assemblyConnector = new AssemblyConnectorImpl();
		return assemblyConnector;
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
