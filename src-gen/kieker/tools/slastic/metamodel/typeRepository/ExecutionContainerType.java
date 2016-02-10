/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository;

import kieker.tools.slastic.metamodel.core.FQNamedEntity;

import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Container Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getExecutionContainerType()
 * @model
 * @generated
 */
public interface ExecutionContainerType extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryPackage#getExecutionContainerType_Resources()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ResourceSpecification> getResources();

} // ExecutionContainerType
