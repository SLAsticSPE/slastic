/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository.impl;

import de.cau.se.slastic.metamodel.typeRepository.*;

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
public class TypeRepositoryFactoryImpl extends EFactoryImpl implements TypeRepositoryFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TypeRepositoryFactory init() {
		try {
			TypeRepositoryFactory theTypeRepositoryFactory = (TypeRepositoryFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/typeRepository.ecore"); 
			if (theTypeRepositoryFactory != null) {
				return theTypeRepositoryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TypeRepositoryFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRepositoryFactoryImpl() {
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
			case TypeRepositoryPackage.COMPONENT_TYPE: return createComponentType();
			case TypeRepositoryPackage.INTERFACE: return createInterface();
			case TypeRepositoryPackage.CONNECTOR_TYPE: return createConnectorType();
			case TypeRepositoryPackage.EXECUTION_CONTAINER_TYPE: return createExecutionContainerType();
			case TypeRepositoryPackage.NETWORK_LINK_TYPE: return createNetworkLinkType();
			case TypeRepositoryPackage.TYPE_REPOSITORY_MODEL: return createTypeRepositoryModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType createComponentType() {
		ComponentTypeImpl componentType = new ComponentTypeImpl();
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface createInterface() {
		InterfaceImpl interface_ = new InterfaceImpl();
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorType createConnectorType() {
		ConnectorTypeImpl connectorType = new ConnectorTypeImpl();
		return connectorType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainerType createExecutionContainerType() {
		ExecutionContainerTypeImpl executionContainerType = new ExecutionContainerTypeImpl();
		return executionContainerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkLinkType createNetworkLinkType() {
		NetworkLinkTypeImpl networkLinkType = new NetworkLinkTypeImpl();
		return networkLinkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRepositoryModel createTypeRepositoryModel() {
		TypeRepositoryModelImpl typeRepositoryModel = new TypeRepositoryModelImpl();
		return typeRepositoryModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRepositoryPackage getTypeRepositoryPackage() {
		return (TypeRepositoryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TypeRepositoryPackage getPackage() {
		return TypeRepositoryPackage.eINSTANCE;
	}

} //TypeRepositoryFactoryImpl
