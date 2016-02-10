/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getExecutionContainerType <em>Execution Container Type</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getNetworkLinks <em>Network Links</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionContainer()
 * @model
 * @generated
 */
public interface ExecutionContainer extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Execution Container Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Container Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Container Type</em>' reference.
	 * @see #setExecutionContainerType(ExecutionContainerType)
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionContainer_ExecutionContainerType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainerType getExecutionContainerType();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getExecutionContainerType <em>Execution Container Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Container Type</em>' reference.
	 * @see #getExecutionContainerType()
	 * @generated
	 */
	void setExecutionContainerType(ExecutionContainerType value);

	/**
	 * Returns the value of the '<em><b>Network Links</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink}.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getExecutionContainers <em>Execution Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Network Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network Links</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionContainer_NetworkLinks()
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getExecutionContainers
	 * @model opposite="executionContainers" ordered="false"
	 * @generated
	 */
	EList<NetworkLink> getNetworkLinks();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.Resource}.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getExecutionContainer <em>Execution Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getExecutionContainer_Resources()
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.Resource#getExecutionContainer
	 * @model opposite="executionContainer" containment="true" ordered="false"
	 * @generated
	 */
	EList<Resource> getResources();

} // ExecutionContainer
