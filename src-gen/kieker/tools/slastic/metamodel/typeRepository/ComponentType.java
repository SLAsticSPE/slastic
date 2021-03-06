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
 * A representation of the model object '<em><b>Component Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getProvidedInterfaces <em>Provided Interfaces</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getRequiredInterfaces <em>Required Interfaces</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.ComponentType#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType()
 * @model
 * @generated
 */
public interface ComponentType extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Provided Interfaces</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Provided Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Provided Interfaces</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType_ProvidedInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Interface> getProvidedInterfaces();

	/**
	 * Returns the value of the '<em><b>Required Interfaces</b></em>' reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.typeRepository.Interface}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Required Interfaces</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Required Interfaces</em>' reference list.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType_RequiredInterfaces()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Interface> getRequiredInterfaces();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.typeRepository.Operation}.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.typeRepository.Operation#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getComponentType_Operations()
	 * @see kieker.tools.slastic.metamodel.typeRepository.Operation#getComponentType
	 * @model opposite="componentType" containment="true" ordered="false"
	 * @generated
	 */
	EList<Operation> getOperations();

} // ComponentType
