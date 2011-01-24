/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.reconfiguration.specification;

import de.cau.se.slastic.metamodel.adaptation.AdaptationModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reconfiguration Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification#getAdaptationModel <em>Adaptation Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.SpecificationPackage#getReconfigurationSpecification()
 * @model
 * @generated
 */
public interface ReconfigurationSpecification extends EObject {
	/**
	 * Returns the value of the '<em><b>Adaptation Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getReconfigurationSpecification <em>Reconfiguration Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adaptation Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adaptation Model</em>' container reference.
	 * @see #setAdaptationModel(AdaptationModel)
	 * @see de.cau.se.slastic.metamodel.reconfiguration.specification.SpecificationPackage#getReconfigurationSpecification_AdaptationModel()
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationModel#getReconfigurationSpecification
	 * @model opposite="reconfigurationSpecification" required="true" transient="false" ordered="false"
	 * @generated
	 */
	AdaptationModel getAdaptationModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification#getAdaptationModel <em>Adaptation Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adaptation Model</em>' container reference.
	 * @see #getAdaptationModel()
	 * @generated
	 */
	void setAdaptationModel(AdaptationModel value);

} // ReconfigurationSpecification
