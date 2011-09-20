/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.componentAssembly;

import de.cau.se.slastic.metamodel.core.FQNamedEntity;

import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Assembly Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getProvidingConnectors <em>Providing Connectors</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyComponent()
 * @model
 * @generated
 */
public interface AssemblyComponent extends FQNamedEntity {
	/**
	 * Returns the value of the '<em><b>Component Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component Type</em>' reference.
	 * @see #setComponentType(ComponentType)
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyComponent_ComponentType()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	ComponentType getComponentType();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent#getComponentType <em>Component Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Component Type</em>' reference.
	 * @see #getComponentType()
	 * @generated
	 */
	void setComponentType(ComponentType value);

	/**
	 * Returns the value of the '<em><b>Providing Connectors</b></em>' reference list.
	 * The list contents are of type {@link de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Providing Connectors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Providing Connectors</em>' reference list.
	 * @see de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyPackage#getAssemblyComponent_ProvidingConnectors()
	 * @model ordered="false"
	 * @generated
	 */
	EList<AssemblyConnector> getProvidingConnectors();

} // AssemblyComponent
