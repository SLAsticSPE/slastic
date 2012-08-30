/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyConnector#getConnectorType <em>Connector Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyConnector()
 * @model abstract="true"
 * @generated
 */
public interface AssemblyConnector extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Type</em>' reference.
	 * @see #setConnectorType(ConnectorType)
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyConnector_ConnectorType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ConnectorType getConnectorType();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyConnector#getConnectorType <em>Connector Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Type</em>' reference.
	 * @see #getConnectorType()
	 * @generated
	 */
	void setConnectorType(ConnectorType value);

} // AssemblyConnector
