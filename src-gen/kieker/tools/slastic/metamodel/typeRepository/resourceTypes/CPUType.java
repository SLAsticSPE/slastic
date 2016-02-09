/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.typeRepository.resourceTypes;

import kieker.tools.slastic.metamodel.typeRepository.ResourceType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CPU Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getVendor <em>Vendor</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getModel <em>Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getSpeedMhz <em>Speed Mhz</em>}</li>
 * </ul>
 *
 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage#getCPUType()
 * @model
 * @generated
 */
public interface CPUType extends ResourceType {
	/**
	 * Returns the value of the '<em><b>Vendor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vendor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vendor</em>' attribute.
	 * @see #setVendor(String)
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage#getCPUType_Vendor()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getVendor();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getVendor <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor</em>' attribute.
	 * @see #getVendor()
	 * @generated
	 */
	void setVendor(String value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' attribute.
	 * @see #setModel(String)
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage#getCPUType_Model()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	String getModel();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getModel <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' attribute.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(String value);

	/**
	 * Returns the value of the '<em><b>Speed Mhz</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speed Mhz</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speed Mhz</em>' attribute.
	 * @see #setSpeedMhz(long)
	 * @see kieker.tools.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage#getCPUType_SpeedMhz()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getSpeedMhz();

	/**
	 * Sets the value of the '{@link kieker.tools.slastic.metamodel.typeRepository.resourceTypes.CPUType#getSpeedMhz <em>Speed Mhz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Speed Mhz</em>' attribute.
	 * @see #getSpeedMhz()
	 * @generated
	 */
	void setSpeedMhz(long value);

} // CPUType
