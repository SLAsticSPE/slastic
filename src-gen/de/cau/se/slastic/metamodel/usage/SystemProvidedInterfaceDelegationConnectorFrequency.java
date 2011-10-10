/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage;

import de.cau.se.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Provided Interface Delegation Connector Frequency</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency#getConnector <em>Connector</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getSystemProvidedInterfaceDelegationConnectorFrequency()
 * @model
 * @generated
 */
public interface SystemProvidedInterfaceDelegationConnectorFrequency extends AssemblyConnectorCallFrequency {
	/**
	 * Returns the value of the '<em><b>Connector</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector</em>' reference.
	 * @see #setConnector(SystemProvidedInterfaceDelegationConnector)
	 * @see de.cau.se.slastic.metamodel.usage.UsagePackage#getSystemProvidedInterfaceDelegationConnectorFrequency_Connector()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	SystemProvidedInterfaceDelegationConnector getConnector();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.usage.SystemProvidedInterfaceDelegationConnectorFrequency#getConnector <em>Connector</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector</em>' reference.
	 * @see #getConnector()
	 * @generated
	 */
	void setConnector(SystemProvidedInterfaceDelegationConnector value);

} // SystemProvidedInterfaceDelegationConnectorFrequency
