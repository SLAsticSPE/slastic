/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.executionEnvironment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mem Swap Resource Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getMemCapacityBytes <em>Mem Capacity Bytes</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getSwapCapacityBytes <em>Swap Capacity Bytes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getMemSwapResourceSpecification()
 * @model
 * @generated
 */
public interface MemSwapResourceSpecification extends ResourceSpecification {
	/**
	 * Returns the value of the '<em><b>Mem Capacity Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mem Capacity Bytes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mem Capacity Bytes</em>' attribute.
	 * @see #setMemCapacityBytes(long)
	 * @see de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getMemSwapResourceSpecification_MemCapacityBytes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getMemCapacityBytes();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getMemCapacityBytes <em>Mem Capacity Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mem Capacity Bytes</em>' attribute.
	 * @see #getMemCapacityBytes()
	 * @generated
	 */
	void setMemCapacityBytes(long value);

	/**
	 * Returns the value of the '<em><b>Swap Capacity Bytes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Swap Capacity Bytes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Swap Capacity Bytes</em>' attribute.
	 * @see #setSwapCapacityBytes(long)
	 * @see de.cau.se.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage#getMemSwapResourceSpecification_SwapCapacityBytes()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	long getSwapCapacityBytes();

	/**
	 * Sets the value of the '{@link de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification#getSwapCapacityBytes <em>Swap Capacity Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Swap Capacity Bytes</em>' attribute.
	 * @see #getSwapCapacityBytes()
	 * @generated
	 */
	void setSwapCapacityBytes(long value);

} // MemSwapResourceSpecification
