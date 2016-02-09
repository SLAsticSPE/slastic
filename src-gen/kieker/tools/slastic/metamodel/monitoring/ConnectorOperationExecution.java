/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution#getAssemblyConnector <em>Assembly Connector</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution#getExecutionContainer <em>Execution Container</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getConnectorOperationExecution()
 * @model
 * @generated
 */
public interface ConnectorOperationExecution extends OperationExecution {
	/**
	 * Returns the value of the '<em><b>Assembly Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Connector</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Connector</em>' reference.
	 * @see #setAssemblyConnector(AssemblyComponentConnector)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getConnectorOperationExecution_AssemblyConnector()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponentConnector getAssemblyConnector();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution#getAssemblyConnector <em>Assembly Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assembly Connector</em>' reference.
	 * @see #getAssemblyConnector()
	 * @generated
	 */
	void setAssemblyConnector(AssemblyComponentConnector value);

	/**
	 * Returns the value of the '<em><b>Execution Container</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Container</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Container</em>' reference.
	 * @see #setExecutionContainer(ExecutionContainer)
	 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage#getConnectorOperationExecution_ExecutionContainer()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainer getExecutionContainer();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution#getExecutionContainer <em>Execution Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Container</em>' reference.
	 * @see #getExecutionContainer()
	 * @generated
	 */
	void setExecutionContainer(ExecutionContainer value);

} // ConnectorOperationExecution
