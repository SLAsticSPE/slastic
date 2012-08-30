/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring.impl;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

import kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution;
import kieker.tools.slastic.metamodel.monitoring.MonitoringPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.ConnectorOperationExecutionImpl#getAssemblyConnector <em>Assembly Connector</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.ConnectorOperationExecutionImpl#getExecutionContainer <em>Execution Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConnectorOperationExecutionImpl extends OperationExecutionImpl implements ConnectorOperationExecution {
	/**
	 * The cached value of the '{@link #getAssemblyConnector() <em>Assembly Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyConnector()
	 * @generated
	 * @ordered
	 */
	protected AssemblyComponentConnector assemblyConnector;

	/**
	 * The cached value of the '{@link #getExecutionContainer() <em>Execution Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContainer()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContainer executionContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConnectorOperationExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MonitoringPackage.Literals.CONNECTOR_OPERATION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponentConnector getAssemblyConnector() {
		if (assemblyConnector != null && assemblyConnector.eIsProxy()) {
			InternalEObject oldAssemblyConnector = (InternalEObject)assemblyConnector;
			assemblyConnector = (AssemblyComponentConnector)eResolveProxy(oldAssemblyConnector);
			if (assemblyConnector != oldAssemblyConnector) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR, oldAssemblyConnector, assemblyConnector));
			}
		}
		return assemblyConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponentConnector basicGetAssemblyConnector() {
		return assemblyConnector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssemblyConnector(AssemblyComponentConnector newAssemblyConnector) {
		AssemblyComponentConnector oldAssemblyConnector = assemblyConnector;
		assemblyConnector = newAssemblyConnector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR, oldAssemblyConnector, assemblyConnector));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainer getExecutionContainer() {
		if (executionContainer != null && executionContainer.eIsProxy()) {
			InternalEObject oldExecutionContainer = (InternalEObject)executionContainer;
			executionContainer = (ExecutionContainer)eResolveProxy(oldExecutionContainer);
			if (executionContainer != oldExecutionContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER, oldExecutionContainer, executionContainer));
			}
		}
		return executionContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainer basicGetExecutionContainer() {
		return executionContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionContainer(ExecutionContainer newExecutionContainer) {
		ExecutionContainer oldExecutionContainer = executionContainer;
		executionContainer = newExecutionContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER, oldExecutionContainer, executionContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR:
				if (resolve) return getAssemblyConnector();
				return basicGetAssemblyConnector();
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER:
				if (resolve) return getExecutionContainer();
				return basicGetExecutionContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR:
				setAssemblyConnector((AssemblyComponentConnector)newValue);
				return;
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER:
				setExecutionContainer((ExecutionContainer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR:
				setAssemblyConnector((AssemblyComponentConnector)null);
				return;
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER:
				setExecutionContainer((ExecutionContainer)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__ASSEMBLY_CONNECTOR:
				return assemblyConnector != null;
			case MonitoringPackage.CONNECTOR_OPERATION_EXECUTION__EXECUTION_CONTAINER:
				return executionContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //ConnectorOperationExecutionImpl
