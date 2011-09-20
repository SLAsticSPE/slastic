/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.usage.*;

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
public class UsageFactoryImpl extends EFactoryImpl implements UsageFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UsageFactory init() {
		try {
			UsageFactory theUsageFactory = (UsageFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/usage.ecore"); 
			if (theUsageFactory != null) {
				return theUsageFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UsageFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageFactoryImpl() {
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
			case UsagePackage.USAGE_MODEL: return createUsageModel();
			case UsagePackage.CALLING_RELATIONSHIP: return createCallingRelationship();
			case UsagePackage.FREQUENCY_DISTRIBUTION: return createFrequencyDistribution();
			case UsagePackage.OPERATION_CALL_FREQUENCY: return createOperationCallFrequency();
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY: return createAssemblyConnectorCallFrequency();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageModel createUsageModel() {
		UsageModelImpl usageModel = new UsageModelImpl();
		return usageModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CallingRelationship createCallingRelationship() {
		CallingRelationshipImpl callingRelationship = new CallingRelationshipImpl();
		return callingRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FrequencyDistribution createFrequencyDistribution() {
		FrequencyDistributionImpl frequencyDistribution = new FrequencyDistributionImpl();
		return frequencyDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationCallFrequency createOperationCallFrequency() {
		OperationCallFrequencyImpl operationCallFrequency = new OperationCallFrequencyImpl();
		return operationCallFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyConnectorCallFrequency createAssemblyConnectorCallFrequency() {
		AssemblyConnectorCallFrequencyImpl assemblyConnectorCallFrequency = new AssemblyConnectorCallFrequencyImpl();
		return assemblyConnectorCallFrequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsagePackage getUsagePackage() {
		return (UsagePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static UsagePackage getPackage() {
		return UsagePackage.eINSTANCE;
	}

} //UsageFactoryImpl
