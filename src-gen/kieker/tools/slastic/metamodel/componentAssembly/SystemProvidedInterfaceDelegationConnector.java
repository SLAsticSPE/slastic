/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>System Provided Interface Delegation Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getProvidingComponent <em>Providing Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getSystemProvidedInterfaceDelegationConnector()
 * @model
 * @generated
 */
public interface SystemProvidedInterfaceDelegationConnector extends SystemInterfaceDelegationConnector {
	/**
	 * Returns the value of the '<em><b>Providing Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providing Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Providing Component</em>' reference.
	 * @see #setProvidingComponent(AssemblyComponent)
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getSystemProvidedInterfaceDelegationConnector_ProvidingComponent()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getProvidingComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getProvidingComponent <em>Providing Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Providing Component</em>' reference.
	 * @see #getProvidingComponent()
	 * @generated
	 */
	void setProvidingComponent(AssemblyComponent value);

	/**
	 * Returns the value of the '<em><b>Component Assembly Model</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaceDelegationConnectors <em>System Provided Interface Delegation Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Assembly Model</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Assembly Model</em>' container reference.
	 * @see #setComponentAssemblyModel(ComponentAssemblyModel)
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getSystemProvidedInterfaceDelegationConnector_ComponentAssemblyModel()
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getSystemProvidedInterfaceDelegationConnectors
	 * @model opposite="systemProvidedInterfaceDelegationConnectors" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ComponentAssemblyModel getComponentAssemblyModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector#getComponentAssemblyModel <em>Component Assembly Model</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Assembly Model</em>' container reference.
	 * @see #getComponentAssemblyModel()
	 * @generated
	 */
	void setComponentAssemblyModel(ComponentAssemblyModel value);

} // SystemProvidedInterfaceDelegationConnector
