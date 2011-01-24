/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring.impl;

import de.cau.se.slastic.metamodel.monitoring.*;

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
public class MonitoringFactoryImpl extends EFactoryImpl implements MonitoringFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MonitoringFactory init() {
		try {
			MonitoringFactory theMonitoringFactory = (MonitoringFactory)EPackage.Registry.INSTANCE.getEFactory("http:///metamodel/monitoring.ecore"); 
			if (theMonitoringFactory != null) {
				return theMonitoringFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MonitoringFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MonitoringFactoryImpl() {
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
			case MonitoringPackage.DEPLOYMENT_COMPONENT_OPERATION_EXECUTION: return createDeploymentComponentOperationExecution();
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION: return createConnectorOperationExecution();
			case MonitoringPackage.RESOURCE_UTILIZATION: return createResourceUtilization();
			case MonitoringPackage.MEM_SWAP_USAGE: return createMemSwapUsage();
			case MonitoringPackage.CPU_UTILIZATION: return createCPUUtilization();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeploymentComponentOperationExecution createDeploymentComponentOperationExecution() {
		DeploymentComponentOperationExecutionImpl deploymentComponentOperationExecution = new DeploymentComponentOperationExecutionImpl();
		return deploymentComponentOperationExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectorOperationExecution createConnectorOperationExecution() {
		ConnectorOperationExecutionImpl connectorOperationExecution = new ConnectorOperationExecutionImpl();
		return connectorOperationExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResourceUtilization createResourceUtilization() {
		ResourceUtilizationImpl resourceUtilization = new ResourceUtilizationImpl();
		return resourceUtilization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MemSwapUsage createMemSwapUsage() {
		MemSwapUsageImpl memSwapUsage = new MemSwapUsageImpl();
		return memSwapUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CPUUtilization createCPUUtilization() {
		CPUUtilizationImpl cpuUtilization = new CPUUtilizationImpl();
		return cpuUtilization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MonitoringPackage getMonitoringPackage() {
		return (MonitoringPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static MonitoringPackage getPackage() {
		return MonitoringPackage.eINSTANCE;
	}

} //MonitoringFactoryImpl
