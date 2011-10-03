/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Trace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.ExecutionTrace#getOperationExecutions <em>Operation Executions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getExecutionTrace()
 * @model abstract="true"
 * @generated
 */
public interface ExecutionTrace extends Trace {
	/**
	 * Returns the value of the '<em><b>Operation Executions</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.monitoring.OperationExecution}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Executions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Executions</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getExecutionTrace_OperationExecutions()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	EList<OperationExecution> getOperationExecutions();

} // ExecutionTrace
