/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentAssembly;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Component Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getProvidingComponent <em>Providing Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getRequiringComponent <em>Requiring Component</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyComponentConnector()
 * @model
 * @generated
 */
public interface AssemblyComponentConnector extends AssemblyConnector {
	/**
	 * Returns the value of the '<em><b>Providing Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent#getRequiringConnectors <em>Requiring Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providing Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Providing Component</em>' reference.
	 * @see #setProvidingComponent(AssemblyComponent)
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyComponentConnector_ProvidingComponent()
	 * @see kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent#getRequiringConnectors
	 * @model opposite="requiringConnectors" required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getProvidingComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getProvidingComponent <em>Providing Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Providing Component</em>' reference.
	 * @see #getProvidingComponent()
	 * @generated
	 */
	void setProvidingComponent(AssemblyComponent value);

	/**
	 * Returns the value of the '<em><b>Requiring Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors <em>Providing Connectors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Requiring Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Requiring Component</em>' reference.
	 * @see #setRequiringComponent(AssemblyComponent)
	 * @see kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyComponentConnector_RequiringComponent()
	 * @see kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors
	 * @model opposite="providingConnectors" required="true" ordered="false"
	 * @generated
	 */
	AssemblyComponent getRequiringComponent();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector#getRequiringComponent <em>Requiring Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Requiring Component</em>' reference.
	 * @see #getRequiringComponent()
	 * @generated
	 */
	void setRequiringComponent(AssemblyComponent value);

} // AssemblyComponentConnector
