/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage.impl;

import java.util.Collection;

import kieker.tools.slastic.metamodel.monitoring.OperationExecution;

import kieker.tools.slastic.metamodel.usage.ExecutionTrace;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.ExecutionTraceImpl#getOperationExecutions <em>Operation Executions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ExecutionTraceImpl extends TraceImpl implements ExecutionTrace {
	/**
	 * The cached value of the '{@link #getOperationExecutions() <em>Operation Executions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperationExecutions()
	 * @generated
	 * @ordered
	 */
	protected EList<OperationExecution> operationExecutions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.EXECUTION_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OperationExecution> getOperationExecutions() {
		if (operationExecutions == null) {
			operationExecutions = new EObjectResolvingEList<OperationExecution>(OperationExecution.class, this, UsagePackage.EXECUTION_TRACE__OPERATION_EXECUTIONS);
		}
		return operationExecutions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.EXECUTION_TRACE__OPERATION_EXECUTIONS:
				return getOperationExecutions();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UsagePackage.EXECUTION_TRACE__OPERATION_EXECUTIONS:
				getOperationExecutions().clear();
				getOperationExecutions().addAll((Collection<? extends OperationExecution>)newValue);
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
			case UsagePackage.EXECUTION_TRACE__OPERATION_EXECUTIONS:
				getOperationExecutions().clear();
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
			case UsagePackage.EXECUTION_TRACE__OPERATION_EXECUTIONS:
				return operationExecutions != null && !operationExecutions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExecutionTraceImpl
