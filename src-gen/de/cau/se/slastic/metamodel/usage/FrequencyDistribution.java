/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Frequency Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.FrequencyDistribution#getValues <em>Values</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.FrequencyDistribution#getFrequencies <em>Frequencies</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getFrequencyDistribution()
 * @model
 * @generated
 */
public interface FrequencyDistribution extends EObject {
	/**
	 * Returns the value of the '<em><b>Values</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' attribute list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getFrequencyDistribution_Values()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Integer> getValues();

	/**
	 * Returns the value of the '<em><b>Frequencies</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frequencies</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frequencies</em>' attribute list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getFrequencyDistribution_Frequencies()
	 * @model ordered="false"
	 * @generated
	 */
	EList<Integer> getFrequencies();

} // FrequencyDistribution
