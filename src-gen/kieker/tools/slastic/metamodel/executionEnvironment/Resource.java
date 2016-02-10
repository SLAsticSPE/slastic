/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getResourceSpecification <em>Resource Specification</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getExecutionContainer <em>Execution Container</em>}</li>
 * </ul>
 * </p>
 *
 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends EObject {
	/**
	 * Returns the value of the '<em><b>Resource Specification</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Specification</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Specification</em>' reference.
	 * @see #setResourceSpecification(ResourceSpecification)
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResource_ResourceSpecification()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ResourceSpecification getResourceSpecification();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getResourceSpecification <em>Resource Specification</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Specification</em>' reference.
	 * @see #getResourceSpecification()
	 * @generated
	 */
	void setResourceSpecification(ResourceSpecification value);

	/**
	 * Returns the value of the '<em><b>Execution Container</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Container</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Container</em>' container reference.
	 * @see #setExecutionContainer(ExecutionContainer)
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getResource_ExecutionContainer()
	 * @see kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer#getResources
	 * @model opposite="resources" required="true" transient="false" ordered="false"
	 * @generated
	 */
	ExecutionContainer getExecutionContainer();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.executionEnvironment.Resource#getExecutionContainer <em>Execution Container</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Container</em>' container reference.
	 * @see #getExecutionContainer()
	 * @generated
	 */
	void setExecutionContainer(ExecutionContainer value);

} // Resource
