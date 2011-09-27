/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;

import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getConnectorType <em>Connector Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getProvidingComponent <em>Providing Component</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getRequiringComponent <em>Requiring Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyConnector()
 * @model
 * @generated
 */
public interface AssemblyConnector extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Connector Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connector Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connector Type</em>' reference.
	 * @see #setConnectorType(ConnectorType)
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyConnector_ConnectorType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ConnectorType getConnectorType();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getConnectorType <em>Connector Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector Type</em>' reference.
	 * @see #getConnectorType()
	 * @generated
	 */
	void setConnectorType(ConnectorType value);

	/**
	 * Returns the value of the '<em><b>Providing Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getRequiringConnectors <em>Requiring Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providing Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Providing Component</em>' reference.
	 * @see #setProvidingComponent(AssemblyComponent)
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyConnector_ProvidingComponent()
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getRequiringConnectors
	 * @model opposite="requiringConnectors" required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getProvidingComponent();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getProvidingComponent <em>Providing Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Providing Component</em>' reference.
	 * @see #getProvidingComponent()
	 * @generated
	 */
	void setProvidingComponent(AssemblyComponent value);

	/**
	 * Returns the value of the '<em><b>Requiring Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors <em>Providing Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requiring Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requiring Component</em>' reference.
	 * @see #setRequiringComponent(AssemblyComponent)
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyConnector_RequiringComponent()
	 * @see de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors
	 * @model opposite="providingConnectors" required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getRequiringComponent();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector#getRequiringComponent <em>Requiring Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requiring Component</em>' reference.
	 * @see #getRequiringComponent()
	 * @generated
	 */
	void setRequiringComponent(AssemblyComponent value);

} // AssemblyConnector
