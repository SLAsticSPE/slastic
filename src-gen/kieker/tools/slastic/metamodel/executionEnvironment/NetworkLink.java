/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

import kieker.tools.slastic.metamodel.typeRepository.NetworkLinkType;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Network Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getNetworkLinkType <em>Network Link Type</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getExecutionContainers <em>Execution Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getNetworkLink()
 * @model
 * @generated
 */
public interface NetworkLink extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Network Link Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Network Link Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Network Link Type</em>' reference.
	 * @see #setNetworkLinkType(NetworkLinkType)
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getNetworkLink_NetworkLinkType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	NetworkLinkType getNetworkLinkType();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.executionEnvironment.NetworkLink#getNetworkLinkType <em>Network Link Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Network Link Type</em>' reference.
	 * @see #getNetworkLinkType()
	 * @generated
	 */
	void setNetworkLinkType(NetworkLinkType value);

	/**
	 * Returns the value of the '<em><b>Execution Containers</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer}.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getNetworkLink <em>Network Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Containers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Containers</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getNetworkLink_ExecutionContainers()
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getNetworkLink
	 * @model opposite="networkLink" ordered="false"
	 * @generated
	 */
	EList<ExecutionContainer> getExecutionContainers();

} // NetworkLink
