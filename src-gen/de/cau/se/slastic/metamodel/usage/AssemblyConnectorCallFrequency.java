/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;

import de.cau.se.slastic.metamodel.typeRepository.Signature;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Connector Call Frequency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getAssemblyConnectors <em>Assembly Connectors</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency()
 * @model
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
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency_Frequency()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getFrequency();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getFrequency <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frequency</em>' attribute.
	 * @see #getFrequency()
	 * @generated
	 */
	void setFrequency(long value);

	/**
	 * Returns the value of the '<em><b>Assembly Connectors</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Connectors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Connectors</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency_AssemblyConnectors()
	 * @model ordered="false"
	 * @generated
	 */
	EList<AssemblyConnector> getAssemblyConnectors();

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
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getAssemblyConnectorCallFrequency_Signature()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	Signature getSignature();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency#getSignature <em>Signature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signature</em>' reference.
	 * @see #getSignature()
	 * @generated
	 */
	void setSignature(Signature value);

} // AssemblyConnectorCallFrequency
