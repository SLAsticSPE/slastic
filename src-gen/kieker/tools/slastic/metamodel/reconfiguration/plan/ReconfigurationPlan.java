/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.plan;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reconfiguration Plan</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationPlan#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getReconfigurationPlan()
 * @model
 * @generated
 */
public interface ReconfigurationPlan extends EObject {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link kieker.tools.slastic.metamodel.reconfiguration.plan.ReconfigurationOperation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see kieker.tools.slastic.metamodel.reconfiguration.plan.PlanPackage#getReconfigurationPlan_Operations()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<ReconfigurationOperation> getOperations();

} // ReconfigurationPlan
