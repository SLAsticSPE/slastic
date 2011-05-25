/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.Interface#getSignatures <em>Signatures</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getInterface()
 * @model
 * @generated
 */
public interface Interface extends FQNamedEntity {

	/**
	 * Returns the value of the '<em><b>Signatures</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.Signature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signatures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signatures</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getInterface_Signatures()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Signature> getSignatures();
} // Interface
