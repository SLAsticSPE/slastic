/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment.impl;

import kieker.tools.slastic.metamodel.executionEnvironment.*;

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
public class ExecutionEnvironmentFactoryImpl extends EFactoryImpl implements ExecutionEnvironmentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExecutionEnvironmentFactory init() {
		try {
			ExecutionEnvironmentFactory theExecutionEnvironmentFactory = (ExecutionEnvironmentFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/executionEnvironment.ecore"); 
			if (theExecutionEnvironmentFactory != null) {
				return theExecutionEnvironmentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExecutionEnvironmentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionEnvironmentFactoryImpl() {
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
			case ExecutionEnvironmentPackage.EXECUTION_CONTAINER: return createExecutionContainer();
			case ExecutionEnvironmentPackage.NETWORK_LINK: return createNetworkLink();
			case ExecutionEnvironmentPackage.RESOURCE: return createResource();
			case ExecutionEnvironmentPackage.EXECUTION_ENVIRONMENT_MODEL: return createExecutionEnvironmentModel();
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION: return createMemSwapResourceSpecification();
			case ExecutionEnvironmentPackage.RESOURCE_SPECIFICATION: return createResourceSpecification();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainer createExecutionContainer() {
		ExecutionContainerImpl executionContainer = new ExecutionContainerImpl();
		return executionContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NetworkLink createNetworkLink() {
		NetworkLinkImpl networkLink = new NetworkLinkImpl();
		return networkLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Resource createResource() {
		ResourceImpl resource = new ResourceImpl();
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionEnvironmentModel createExecutionEnvironmentModel() {
		ExecutionEnvironmentModelImpl executionEnvironmentModel = new ExecutionEnvironmentModelImpl();
		return executionEnvironmentModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemSwapResourceSpecification createMemSwapResourceSpecification() {
		MemSwapResourceSpecificationImpl memSwapResourceSpecification = new MemSwapResourceSpecificationImpl();
		return memSwapResourceSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceSpecification createResourceSpecification() {
		ResourceSpecificationImpl resourceSpecification = new ResourceSpecificationImpl();
		return resourceSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionEnvironmentPackage getExecutionEnvironmentPackage() {
		return (ExecutionEnvironmentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExecutionEnvironmentPackage getPackage() {
		return ExecutionEnvironmentPackage.eINSTANCE;
	}

} //ExecutionEnvironmentFactoryImpl
