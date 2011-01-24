/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring;

import de.cau.se.slastic.metamodel.core.CorePackage;

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
 * @see de.cau.se.slastic.metamodel.monitoring.MonitoringFactory
 * @model kind="package"
 * @generated
 */
public interface MonitoringPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "monitoring";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///metamodel/monitoring.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "metamodel.monitoring";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	MonitoringPackage eINSTANCE = de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl <em>Operation Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getOperationExecution()
	 * @generated
	 */
	int OPERATION_EXECUTION = 0;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION__TRACE_ID = CorePackage.IEVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Eoi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION__EOI = CorePackage.IEVENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ess</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION__ESS = CorePackage.IEVENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION__TIN = CorePackage.IEVENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Tout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION__TOUT = CorePackage.IEVENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Session Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION__SESSION_ID = CorePackage.IEVENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Operation Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_EXECUTION_FEATURE_COUNT = CorePackage.IEVENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.DeploymentComponentOperationExecutionImpl <em>Deployment Component Operation Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.DeploymentComponentOperationExecutionImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getDeploymentComponentOperationExecution()
	 * @generated
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION = 1;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__TRACE_ID = OPERATION_EXECUTION__TRACE_ID;

	/**
	 * The feature id for the '<em><b>Eoi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__EOI = OPERATION_EXECUTION__EOI;

	/**
	 * The feature id for the '<em><b>Ess</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__ESS = OPERATION_EXECUTION__ESS;

	/**
	 * The feature id for the '<em><b>Tin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__TIN = OPERATION_EXECUTION__TIN;

	/**
	 * The feature id for the '<em><b>Tout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__TOUT = OPERATION_EXECUTION__TOUT;

	/**
	 * The feature id for the '<em><b>Session Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__SESSION_ID = OPERATION_EXECUTION__SESSION_ID;

	/**
	 * The feature id for the '<em><b>Deployment Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT = OPERATION_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Deployment Component Operation Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_COMPONENT_OPERATION_EXECUTION_FEATURE_COUNT = OPERATION_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.ConnectorOperationExecutionImpl <em>Connector Operation Execution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.ConnectorOperationExecutionImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getConnectorOperationExecution()
	 * @generated
	 */
	int CONNECTOR_OPERATION_EXECUTION = 2;

	/**
	 * The feature id for the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__TRACE_ID = OPERATION_EXECUTION__TRACE_ID;

	/**
	 * The feature id for the '<em><b>Eoi</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__EOI = OPERATION_EXECUTION__EOI;

	/**
	 * The feature id for the '<em><b>Ess</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__ESS = OPERATION_EXECUTION__ESS;

	/**
	 * The feature id for the '<em><b>Tin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__TIN = OPERATION_EXECUTION__TIN;

	/**
	 * The feature id for the '<em><b>Tout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__TOUT = OPERATION_EXECUTION__TOUT;

	/**
	 * The feature id for the '<em><b>Session Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__SESSION_ID = OPERATION_EXECUTION__SESSION_ID;

	/**
	 * The feature id for the '<em><b>Assembly Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR = OPERATION_EXECUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER = OPERATION_EXECUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Connector Operation Execution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_EXECUTION_FEATURE_COUNT = OPERATION_EXECUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.ResourceMeasurementImpl <em>Resource Measurement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.ResourceMeasurementImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getResourceMeasurement()
	 * @generated
	 */
	int RESOURCE_MEASUREMENT = 4;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MEASUREMENT__RESOURCE = CorePackage.IEVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MEASUREMENT__TIMESTAMP = CorePackage.IEVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource Measurement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MEASUREMENT_FEATURE_COUNT = CorePackage.IEVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.ResourceUtilizationImpl <em>Resource Utilization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.ResourceUtilizationImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getResourceUtilization()
	 * @generated
	 */
	int RESOURCE_UTILIZATION = 3;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_UTILIZATION__RESOURCE = RESOURCE_MEASUREMENT__RESOURCE;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_UTILIZATION__TIMESTAMP = RESOURCE_MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Utilization</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_UTILIZATION__UTILIZATION = RESOURCE_MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Resource Utilization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_UTILIZATION_FEATURE_COUNT = RESOURCE_MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl <em>Mem Swap Usage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getMemSwapUsage()
	 * @generated
	 */
	int MEM_SWAP_USAGE = 5;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE__RESOURCE = RESOURCE_MEASUREMENT__RESOURCE;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE__TIMESTAMP = RESOURCE_MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>Mem Used Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE__MEM_USED_BYTES = RESOURCE_MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mem Free Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE__MEM_FREE_BYTES = RESOURCE_MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Swap Used Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE__SWAP_USED_BYTES = RESOURCE_MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Swap Free Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE__SWAP_FREE_BYTES = RESOURCE_MEASUREMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Mem Swap Usage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEM_SWAP_USAGE_FEATURE_COUNT = RESOURCE_MEASUREMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.CPUUtilizationImpl <em>CPU Utilization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.CPUUtilizationImpl
	 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getCPUUtilization()
	 * @generated
	 */
	int CPU_UTILIZATION = 6;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__RESOURCE = RESOURCE_MEASUREMENT__RESOURCE;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__TIMESTAMP = RESOURCE_MEASUREMENT__TIMESTAMP;

	/**
	 * The feature id for the '<em><b>User</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__USER = RESOURCE_MEASUREMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>System</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__SYSTEM = RESOURCE_MEASUREMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Wait</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__WAIT = RESOURCE_MEASUREMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Nice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__NICE = RESOURCE_MEASUREMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Irq</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__IRQ = RESOURCE_MEASUREMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Combined</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__COMBINED = RESOURCE_MEASUREMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Idle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION__IDLE = RESOURCE_MEASUREMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>CPU Utilization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CPU_UTILIZATION_FEATURE_COUNT = RESOURCE_MEASUREMENT_FEATURE_COUNT + 7;


	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution <em>Operation Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Execution</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution
	 * @generated
	 */
	EClass getOperationExecution();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTraceId <em>Trace Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Trace Id</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTraceId()
	 * @see #getOperationExecution()
	 * @generated
	 */
	EAttribute getOperationExecution_TraceId();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEoi <em>Eoi</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Eoi</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEoi()
	 * @see #getOperationExecution()
	 * @generated
	 */
	EAttribute getOperationExecution_Eoi();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEss <em>Ess</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ess</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution#getEss()
	 * @see #getOperationExecution()
	 * @generated
	 */
	EAttribute getOperationExecution_Ess();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTin <em>Tin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tin</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTin()
	 * @see #getOperationExecution()
	 * @generated
	 */
	EAttribute getOperationExecution_Tin();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTout <em>Tout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tout</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution#getTout()
	 * @see #getOperationExecution()
	 * @generated
	 */
	EAttribute getOperationExecution_Tout();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.OperationExecution#getSessionId <em>Session Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Session Id</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.OperationExecution#getSessionId()
	 * @see #getOperationExecution()
	 * @generated
	 */
	EAttribute getOperationExecution_SessionId();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution <em>Deployment Component Operation Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Component Operation Execution</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution
	 * @generated
	 */
	EClass getDeploymentComponentOperationExecution();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getDeploymentComponent <em>Deployment Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Deployment Component</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution#getDeploymentComponent()
	 * @see #getDeploymentComponentOperationExecution()
	 * @generated
	 */
	EReference getDeploymentComponentOperationExecution_DeploymentComponent();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution <em>Connector Operation Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Operation Execution</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution
	 * @generated
	 */
	EClass getConnectorOperationExecution();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution#getAssemblyConnector <em>Assembly Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Assembly Connector</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution#getAssemblyConnector()
	 * @see #getConnectorOperationExecution()
	 * @generated
	 */
	EReference getConnectorOperationExecution_AssemblyConnector();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution#getExecutionContainer <em>Execution Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Execution Container</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution#getExecutionContainer()
	 * @see #getConnectorOperationExecution()
	 * @generated
	 */
	EReference getConnectorOperationExecution_ExecutionContainer();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.ResourceUtilization <em>Resource Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Utilization</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ResourceUtilization
	 * @generated
	 */
	EClass getResourceUtilization();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.ResourceUtilization#getUtilization <em>Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Utilization</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ResourceUtilization#getUtilization()
	 * @see #getResourceUtilization()
	 * @generated
	 */
	EAttribute getResourceUtilization_Utilization();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement <em>Resource Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Measurement</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement
	 * @generated
	 */
	EClass getResourceMeasurement();

	/**
	 * Returns the meta object for the reference '{@link de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement#getResource()
	 * @see #getResourceMeasurement()
	 * @generated
	 */
	EReference getResourceMeasurement_Resource();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.ResourceMeasurement#getTimestamp()
	 * @see #getResourceMeasurement()
	 * @generated
	 */
	EAttribute getResourceMeasurement_Timestamp();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.MemSwapUsage <em>Mem Swap Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mem Swap Usage</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.MemSwapUsage
	 * @generated
	 */
	EClass getMemSwapUsage();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getMemUsedBytes <em>Mem Used Bytes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mem Used Bytes</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getMemUsedBytes()
	 * @see #getMemSwapUsage()
	 * @generated
	 */
	EAttribute getMemSwapUsage_MemUsedBytes();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getMemFreeBytes <em>Mem Free Bytes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mem Free Bytes</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getMemFreeBytes()
	 * @see #getMemSwapUsage()
	 * @generated
	 */
	EAttribute getMemSwapUsage_MemFreeBytes();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getSwapUsedBytes <em>Swap Used Bytes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Swap Used Bytes</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getSwapUsedBytes()
	 * @see #getMemSwapUsage()
	 * @generated
	 */
	EAttribute getMemSwapUsage_SwapUsedBytes();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getSwapFreeBytes <em>Swap Free Bytes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Swap Free Bytes</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.MemSwapUsage#getSwapFreeBytes()
	 * @see #getMemSwapUsage()
	 * @generated
	 */
	EAttribute getMemSwapUsage_SwapFreeBytes();

	/**
	 * Returns the meta object for class '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization <em>CPU Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CPU Utilization</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization
	 * @generated
	 */
	EClass getCPUUtilization();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>User</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getUser()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_User();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getSystem <em>System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>System</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getSystem()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_System();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getWait <em>Wait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getWait()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_Wait();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getNice <em>Nice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nice</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getNice()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_Nice();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIrq <em>Irq</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Irq</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIrq()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_Irq();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getCombined <em>Combined</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Combined</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getCombined()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_Combined();

	/**
	 * Returns the meta object for the attribute '{@link de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIdle <em>Idle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Idle</em>'.
	 * @see de.cau.se.slastic.metamodel.monitoring.CPUUtilization#getIdle()
	 * @see #getCPUUtilization()
	 * @generated
	 */
	EAttribute getCPUUtilization_Idle();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MonitoringFactory getMonitoringFactory();

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
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl <em>Operation Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getOperationExecution()
		 * @generated
		 */
		EClass OPERATION_EXECUTION = eINSTANCE.getOperationExecution();

		/**
		 * The meta object literal for the '<em><b>Trace Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_EXECUTION__TRACE_ID = eINSTANCE.getOperationExecution_TraceId();

		/**
		 * The meta object literal for the '<em><b>Eoi</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_EXECUTION__EOI = eINSTANCE.getOperationExecution_Eoi();

		/**
		 * The meta object literal for the '<em><b>Ess</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_EXECUTION__ESS = eINSTANCE.getOperationExecution_Ess();

		/**
		 * The meta object literal for the '<em><b>Tin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_EXECUTION__TIN = eINSTANCE.getOperationExecution_Tin();

		/**
		 * The meta object literal for the '<em><b>Tout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_EXECUTION__TOUT = eINSTANCE.getOperationExecution_Tout();

		/**
		 * The meta object literal for the '<em><b>Session Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_EXECUTION__SESSION_ID = eINSTANCE.getOperationExecution_SessionId();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.DeploymentComponentOperationExecutionImpl <em>Deployment Component Operation Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.DeploymentComponentOperationExecutionImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getDeploymentComponentOperationExecution()
		 * @generated
		 */
		EClass DEPLOYMENT_COMPONENT_OPERATION_EXECUTION = eINSTANCE.getDeploymentComponentOperationExecution();

		/**
		 * The meta object literal for the '<em><b>Deployment Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_COMPONENT_OPERATION_EXECUTION__DEPLOYMENT_COMPONENT = eINSTANCE.getDeploymentComponentOperationExecution_DeploymentComponent();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.ConnectorOperationExecutionImpl <em>Connector Operation Execution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.ConnectorOperationExecutionImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getConnectorOperationExecution()
		 * @generated
		 */
		EClass CONNECTOR_OPERATION_EXECUTION = eINSTANCE.getConnectorOperationExecution();

		/**
		 * The meta object literal for the '<em><b>Assembly Connector</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR = eINSTANCE.getConnectorOperationExecution_AssemblyConnector();

		/**
		 * The meta object literal for the '<em><b>Execution Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER = eINSTANCE.getConnectorOperationExecution_ExecutionContainer();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.ResourceUtilizationImpl <em>Resource Utilization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.ResourceUtilizationImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getResourceUtilization()
		 * @generated
		 */
		EClass RESOURCE_UTILIZATION = eINSTANCE.getResourceUtilization();

		/**
		 * The meta object literal for the '<em><b>Utilization</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_UTILIZATION__UTILIZATION = eINSTANCE.getResourceUtilization_Utilization();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.ResourceMeasurementImpl <em>Resource Measurement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.ResourceMeasurementImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getResourceMeasurement()
		 * @generated
		 */
		EClass RESOURCE_MEASUREMENT = eINSTANCE.getResourceMeasurement();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_MEASUREMENT__RESOURCE = eINSTANCE.getResourceMeasurement_Resource();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_MEASUREMENT__TIMESTAMP = eINSTANCE.getResourceMeasurement_Timestamp();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl <em>Mem Swap Usage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getMemSwapUsage()
		 * @generated
		 */
		EClass MEM_SWAP_USAGE = eINSTANCE.getMemSwapUsage();

		/**
		 * The meta object literal for the '<em><b>Mem Used Bytes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEM_SWAP_USAGE__MEM_USED_BYTES = eINSTANCE.getMemSwapUsage_MemUsedBytes();

		/**
		 * The meta object literal for the '<em><b>Mem Free Bytes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEM_SWAP_USAGE__MEM_FREE_BYTES = eINSTANCE.getMemSwapUsage_MemFreeBytes();

		/**
		 * The meta object literal for the '<em><b>Swap Used Bytes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEM_SWAP_USAGE__SWAP_USED_BYTES = eINSTANCE.getMemSwapUsage_SwapUsedBytes();

		/**
		 * The meta object literal for the '<em><b>Swap Free Bytes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEM_SWAP_USAGE__SWAP_FREE_BYTES = eINSTANCE.getMemSwapUsage_SwapFreeBytes();

		/**
		 * The meta object literal for the '{@link de.cau.se.slastic.metamodel.monitoring.impl.CPUUtilizationImpl <em>CPU Utilization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.CPUUtilizationImpl
		 * @see de.cau.se.slastic.metamodel.monitoring.impl.MonitoringPackageImpl#getCPUUtilization()
		 * @generated
		 */
		EClass CPU_UTILIZATION = eINSTANCE.getCPUUtilization();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__USER = eINSTANCE.getCPUUtilization_User();

		/**
		 * The meta object literal for the '<em><b>System</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__SYSTEM = eINSTANCE.getCPUUtilization_System();

		/**
		 * The meta object literal for the '<em><b>Wait</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__WAIT = eINSTANCE.getCPUUtilization_Wait();

		/**
		 * The meta object literal for the '<em><b>Nice</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__NICE = eINSTANCE.getCPUUtilization_Nice();

		/**
		 * The meta object literal for the '<em><b>Irq</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__IRQ = eINSTANCE.getCPUUtilization_Irq();

		/**
		 * The meta object literal for the '<em><b>Combined</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__COMBINED = eINSTANCE.getCPUUtilization_Combined();

		/**
		 * The meta object literal for the '<em><b>Idle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CPU_UTILIZATION__IDLE = eINSTANCE.getCPUUtilization_Idle();

	}

} //MonitoringPackage
