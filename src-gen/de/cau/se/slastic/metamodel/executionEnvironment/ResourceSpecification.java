/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.executionEnvironment;

import de.cau.se.slastic.metamodel.core.NamedEntity;

import de.cau.se.slastic.metamodel.typeRepository.ResourceType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification#getResourceType <em>Resource Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResourceSpecification()
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
	 * @see de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResourceSpecification_ResourceType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ResourceType getResourceType();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification#getResourceType <em>Resource Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Type</em>' reference.
	 * @see #getResourceType()
	 * @generated
	 */
	void setResourceType(ResourceType value);

} // ResourceSpecification
