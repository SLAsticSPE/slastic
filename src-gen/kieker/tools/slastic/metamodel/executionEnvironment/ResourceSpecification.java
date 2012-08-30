/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment;

import kieker.tools.slastic.metamodel.core.NamedEntity;

import kieker.tools.slastic.metamodel.typeRepository.ResourceType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification#getResourceType <em>Resource Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResourceSpecification()
 * @model
 * @generated
 */
public interface ResourceSpecification extends NamedEntity {
	/**
	 * Returns the value of the '<em><b>Resource Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Type</em>' reference.
	 * @see #setResourceType(ResourceType)
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResourceSpecification_ResourceType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ResourceType getResourceType();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification#getResourceType <em>Resource Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Type</em>' reference.
	 * @see #getResourceType()
	 * @generated
	 */
	void setResourceType(ResourceType value);

} // ResourceSpecification
