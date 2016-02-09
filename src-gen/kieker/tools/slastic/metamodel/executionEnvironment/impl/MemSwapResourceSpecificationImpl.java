/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.executionEnvironment.impl;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentPackage;
import kieker.tools.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mem Swap Resource Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.MemSwapResourceSpecificationImpl#getMemCapacityBytes <em>Mem Capacity Bytes</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.executionEnvironment.impl.MemSwapResourceSpecificationImpl#getSwapCapacityBytes <em>Swap Capacity Bytes</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MemSwapResourceSpecificationImpl extends ResourceSpecificationImpl implements MemSwapResourceSpecification {
	/**
	 * The default value of the '{@link #getMemCapacityBytes() <em>Mem Capacity Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemCapacityBytes()
	 * @generated
	 * @ordered
	 */
	protected static final long MEM_CAPACITY_BYTES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMemCapacityBytes() <em>Mem Capacity Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemCapacityBytes()
	 * @generated
	 * @ordered
	 */
	protected long memCapacityBytes = MEM_CAPACITY_BYTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwapCapacityBytes() <em>Swap Capacity Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwapCapacityBytes()
	 * @generated
	 * @ordered
	 */
	protected static final long SWAP_CAPACITY_BYTES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSwapCapacityBytes() <em>Swap Capacity Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwapCapacityBytes()
	 * @generated
	 * @ordered
	 */
	protected long swapCapacityBytes = SWAP_CAPACITY_BYTES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemSwapResourceSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExecutionEnvironmentPackage.Literals.MEM_SWAP_RESOURCE_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMemCapacityBytes() {
		return memCapacityBytes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemCapacityBytes(long newMemCapacityBytes) {
		long oldMemCapacityBytes = memCapacityBytes;
		memCapacityBytes = newMemCapacityBytes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES, oldMemCapacityBytes, memCapacityBytes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSwapCapacityBytes() {
		return swapCapacityBytes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwapCapacityBytes(long newSwapCapacityBytes) {
		long oldSwapCapacityBytes = swapCapacityBytes;
		swapCapacityBytes = newSwapCapacityBytes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES, oldSwapCapacityBytes, swapCapacityBytes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES:
				return getMemCapacityBytes();
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES:
				return getSwapCapacityBytes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES:
				setMemCapacityBytes((Long)newValue);
				return;
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES:
				setSwapCapacityBytes((Long)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES:
				setMemCapacityBytes(MEM_CAPACITY_BYTES_EDEFAULT);
				return;
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES:
				setSwapCapacityBytes(SWAP_CAPACITY_BYTES_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__MEM_CAPACITY_BYTES:
				return memCapacityBytes != MEM_CAPACITY_BYTES_EDEFAULT;
			case ExecutionEnvironmentPackage.MEM_SWAP_RESOURCE_SPECIFICATION__SWAP_CAPACITY_BYTES:
				return swapCapacityBytes != SWAP_CAPACITY_BYTES_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (memCapacityBytes: ");
		result.append(memCapacityBytes);
		result.append(", swapCapacityBytes: ");
		result.append(swapCapacityBytes);
		result.append(')');
		return result.toString();
	}

} //MemSwapResourceSpecificationImpl
