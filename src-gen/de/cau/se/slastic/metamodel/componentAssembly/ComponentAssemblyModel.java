/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly;

import de.cau.se.slastic.metamodel.core.SLAsticModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyComponents <em>Assembly Components</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel#getAssemblyConnectors <em>Assembly Connectors</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel()
 * @model
 * @generated
 */
public interface ComponentAssemblyModel extends SLAsticModel {
	/**
	 * Returns the value of the '<em><b>Assembly Components</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Components</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_AssemblyComponents()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AssemblyComponent> getAssemblyComponents();

	/**
	 * Returns the value of the '<em><b>Assembly Connectors</b></em>' containment reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assembly Connectors</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assembly Connectors</em>' containment reference list.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getComponentAssemblyModel_AssemblyConnectors()
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	EList<AssemblyConnector> getAssemblyConnectors();

} // ComponentAssemblyModel
