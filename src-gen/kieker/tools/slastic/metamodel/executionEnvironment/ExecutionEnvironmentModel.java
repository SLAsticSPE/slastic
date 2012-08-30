/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment;

import kieker.tools.slastic.metamodel.core.SLAsticModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getExecutionContainers <em>Execution Containers</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getNetworkLinks <em>Network Links</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel#getAllocatedExecutionContainers <em>Allocated Execution Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionEnvironmentModel()
 * @model
 * @generated
 */
public interface ExecutionEnvironmentModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>Execution Containers</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Containers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Containers</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionEnvironmentModel_ExecutionContainers()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ExecutionContainer> getExecutionContainers();

	/**
	 * Returns the value of the '<em><b>Network Links</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Network Links</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network Links</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionEnvironmentModel_NetworkLinks()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<NetworkLink> getNetworkLinks();

	/**
	 * Returns the value of the '<em><b>Allocated Execution Containers</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Allocated Execution Containers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Allocated Execution Containers</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionEnvironmentModel_AllocatedExecutionContainers()
	 * @model ordered="false"
	 * @generated
	 */
	EList<ExecutionContainer> getAllocatedExecutionContainers();

} // ExecutionEnvironmentModel
