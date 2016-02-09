/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan;

import kieker.tools.slastic.metamodel.typeRepository.ExecutionContainerType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Container Allocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerAllocation#getContainerType <em>Container Type</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getContainerAllocation()
 * @model
 * @generated
 */
public interface ContainerAllocation extends ReconfigurationOperation {
	/**
	 * Returns the value of the '<em><b>Container Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Container Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Container Type</em>' reference.
	 * @see #setContainerType(ExecutionContainerType)
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getContainerAllocation_ContainerType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ExecutionContainerType getContainerType();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ContainerAllocation#getContainerType <em>Container Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Container Type</em>' reference.
	 * @see #getContainerType()
	 * @generated
	 */
	void setContainerType(ExecutionContainerType value);

} // ContainerAllocation
