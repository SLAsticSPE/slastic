/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Network Link Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType#getRepository <em>Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getNetworkLinkType()
 * @model
 * @generated
 */
public interface NetworkLinkType extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Repository</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getNetworkLinkTypes <em>Network Link Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repository</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repository</em>' container reference.
	 * @see #setRepository(TypeRepositoryModel)
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getNetworkLinkType_Repository()
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryModel#getNetworkLinkTypes
	 * @model opposite="networkLinkTypes" required="true" transient="false" ordered="false"
	 * @generated
	 */
	TypeRepositoryModel getRepository();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.typeRepository.NetworkLinkType#getRepository <em>Repository</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repository</em>' container reference.
	 * @see #getRepository()
	 * @generated
	 */
	void setRepository(TypeRepositoryModel value);

} // NetworkLinkType
