/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage;

import kieker.tools.slastic.metamodel.typeRepository.Signature;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Connector Call Frequency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency()
 * @model abstract="true"
 * @generated
 */
public interface AssemblyConnectorCallFrequency extends EObject {
	/**
	 * Returns the value of the '<em><b>Frequency</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frequency</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frequency</em>' attribute.
	 * @see #setFrequency(long)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency_Frequency()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getFrequency();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
	void setFrequency(long value);

	/**
	 * Returns the value of the '<em><b>Signature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signature</em>' reference.
	 * @see #setSignature(Signature)
	 * @see kieker.tools.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency_Signature()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Signature getSignature();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getSignature <em>Signature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' reference.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(Signature value);

} // AssemblyConnectorCallFrequency
