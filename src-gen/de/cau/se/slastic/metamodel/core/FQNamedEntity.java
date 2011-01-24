/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FQ Named Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.core.FQNamedEntity#getPackageName <em>Package Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.core.CorePackage#getFQNamedEntity()
 * @model
 * @generated
 */
public interface FQNamedEntity extends NamedEntity {
	/**
	 * Returns the value of the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Name</em>' attribute.
	 * @see #setPackageName(String)
	 * @see de.cau.se.slastic.metamodel.core.CorePackage#getFQNamedEntity_PackageName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.core.FQNamedEntity#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

} // FQNamedEntity
