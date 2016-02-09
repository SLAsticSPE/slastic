/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.adaptation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workload Forecasting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.WorkloadForecasting#getControl <em>Control</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getWorkloadForecasting()
 * @model
 * @generated
 */
public interface WorkloadForecasting extends EObject {
	/**
	 * Returns the value of the '<em><b>Control</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.adaptation.Control#getWorkloadForecaster <em>Workload Forecaster</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Control</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Control</em>' container reference.
	 * @see #setControl(Control)
	 * @see kieker.tools.slastic.metamodel.adaptation.AdaptationPackage#getWorkloadForecasting_Control()
	 * @see kieker.tools.slastic.metamodel.adaptation.Control#getWorkloadForecaster
	 * @model opposite="workloadForecaster" required="true" transient="false" ordered="false"
	 * @generated
	 */
	Control getControl();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.adaptation.WorkloadForecasting#getControl <em>Control</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Control</em>' container reference.
	 * @see #getControl()
	 * @generated
	 */
	void setControl(Control value);

} // WorkloadForecasting
