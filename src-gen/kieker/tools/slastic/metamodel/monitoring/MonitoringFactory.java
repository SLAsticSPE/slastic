/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage
 * @generated
 */
public interface MonitoringFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MonitoringFactory eINSTANCE = kieker.tools.slastic.metamodel.monitoring.impl.MonitoringFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Deployment Component Operation Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployment Component Operation Execution</em>'.
	 * @generated
	 */
	DeploymentComponentOperationExecution createDeploymentComponentOperationExecution();

	/**
	 * Returns a new object of class '<em>Connector Operation Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Operation Execution</em>'.
	 * @generated
	 */
	ConnectorOperationExecution createConnectorOperationExecution();

	/**
	 * Returns a new object of class '<em>Resource Utilization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resource Utilization</em>'.
	 * @generated
	 */
	ResourceUtilization createResourceUtilization();

	/**
	 * Returns a new object of class '<em>Mem Swap Usage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mem Swap Usage</em>'.
	 * @generated
	 */
	MemSwapUsage createMemSwapUsage();

	/**
	 * Returns a new object of class '<em>CPU Utilization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CPU Utilization</em>'.
	 * @generated
	 */
	CPUUtilization createCPUUtilization();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MonitoringPackage getMonitoringPackage();

} //MonitoringFactory
