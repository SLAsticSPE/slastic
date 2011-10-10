/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Required Interface Delegation Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getRequiringComponent <em>Requiring Component</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getSystemRequiredInterfaceDelegationConnector()
 * @model
 * @generated
 */
public interface SystemRequiredInterfaceDelegationConnector extends SystemInterfaceDelegationConnector {
	/**
	 * Returns the value of the '<em><b>Requiring Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requiring Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requiring Component</em>' reference.
	 * @see #setRequiringComponent(AssemblyComponent)
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getSystemRequiredInterfaceDelegationConnector_RequiringComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getRequiringComponent();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getRequiringComponent <em>Requiring Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requiring Component</em>' reference.
	 * @see #getRequiringComponent()
	 * @generated
	 */
	void setRequiringComponent(AssemblyComponent value);

	/**
	 * Returns the value of the '<em><b>Component Assembly Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaceDelegationConnectors <em>System Required Interface Delegation Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Assembly Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Assembly Model</em>' container reference.
	 * @see #setComponentAssemblyModel(ComponentAssemblyModel)
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getSystemRequiredInterfaceDelegationConnector_ComponentAssemblyModel()
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemRequiredInterfaceDelegationConnectors
	 * @model opposite="systemRequiredInterfaceDelegationConnectors" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ComponentAssemblyModel getComponentAssemblyModel();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.componentAssembly.SystemRequiredInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Assembly Model</em>' container reference.
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	void setComponentAssemblyModel(ComponentAssemblyModel value);

} // SystemRequiredInterfaceDelegationConnector
