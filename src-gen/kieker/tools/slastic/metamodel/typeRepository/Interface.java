/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.Interface#getSignatures <em>Signatures</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getInterface()
 * @model
 * @generated
 */
public interface Interface extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Signatures</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.typeRepository.Signature}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signatures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signatures</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getInterface_Signatures()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<Signature> getSignatures();

} // Interface
