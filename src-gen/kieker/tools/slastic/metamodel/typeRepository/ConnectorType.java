/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.ConnectorType#getInterface <em>Interface</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getConnectorType()
 * @model
 * @generated
 */
public interface ConnectorType extends FQNamedEntity {

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' reference.
	 * @see #setInterface(Interface)
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getConnectorType_Interface()
	 * @model required="true"
	 * @generated
	 */
	Interface getInterface();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.typeRepository.ConnectorType#getInterface <em>Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' reference.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(Interface value);

} // ConnectorType
