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
 * A representation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.ComponentType#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.ComponentType#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType()
 * @model
 * @generated
 */
public interface ComponentType extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Provided Interfaces</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided Interfaces</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType_ProvidedInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Interface> getProvidedInterfaces();

	/**
	 * Returns the value of the '<em><b>Required Interfaces</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Interfaces</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType_RequiredInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Interface> getRequiredInterfaces();

} // ComponentType
