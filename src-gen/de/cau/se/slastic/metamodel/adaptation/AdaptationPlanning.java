/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.adaptation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Planning</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.adaptation.AdaptationPlanning#getControl <em>Control</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationPackage#getAdaptationPlanning()
 * @model
 * @generated
 */
public interface AdaptationPlanning extends EObject {
	/**
	 * Returns the value of the '<em><b>Control</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.adaptation.Control#getAdaptationPlanner <em>Adaptation Planner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control</em>' container reference.
	 * @see #setControl(Control)
	 * @see de.cau.se.slastic.metamodel.adaptation.AdaptationPackage#getAdaptationPlanning_Control()
	 * @see de.cau.se.slastic.metamodel.adaptation.Control#getAdaptationPlanner
	 * @model opposite="adaptationPlanner" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Control getControl();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.adaptation.AdaptationPlanning#getControl <em>Control</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control</em>' container reference.
	 * @see #getControl()
	 * @generated
	 */
	void setControl(Control value);

} // AdaptationPlanning
