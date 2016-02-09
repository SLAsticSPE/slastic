/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.core;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FQ Named Entity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.core.FQNamedEntity#getPackageName <em>Package Name</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.core.CorePackage#getFQNamedEntity()
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
	 * @see kieker.tools.slastic.metamodel.core.CorePackage#getFQNamedEntity_PackageName()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.core.FQNamedEntity#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

} // FQNamedEntity
