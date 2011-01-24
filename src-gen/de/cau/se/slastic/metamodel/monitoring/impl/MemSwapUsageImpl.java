/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring.impl;

import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;
import de.cau.se.slastic.metamodel.monitoring.MonitoringPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mem Swap Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl#getMemUsedBytes <em>Mem Used Bytes</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl#getMemFreeBytes <em>Mem Free Bytes</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl#getSwapUsedBytes <em>Swap Used Bytes</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.MemSwapUsageImpl#getSwapFreeBytes <em>Swap Free Bytes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MemSwapUsageImpl extends ResourceMeasurementImpl implements MemSwapUsage {
	/**
	 * The default value of the '{@link #getMemUsedBytes() <em>Mem Used Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemUsedBytes()
	 * @generated
	 * @ordered
	 */
	protected static final long MEM_USED_BYTES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMemUsedBytes() <em>Mem Used Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemUsedBytes()
	 * @generated
	 * @ordered
	 */
	protected long memUsedBytes = MEM_USED_BYTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getMemFreeBytes() <em>Mem Free Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemFreeBytes()
	 * @generated
	 * @ordered
	 */
	protected static final long MEM_FREE_BYTES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getMemFreeBytes() <em>Mem Free Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemFreeBytes()
	 * @generated
	 * @ordered
	 */
	protected long memFreeBytes = MEM_FREE_BYTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwapUsedBytes() <em>Swap Used Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwapUsedBytes()
	 * @generated
	 * @ordered
	 */
	protected static final long SWAP_USED_BYTES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSwapUsedBytes() <em>Swap Used Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwapUsedBytes()
	 * @generated
	 * @ordered
	 */
	protected long swapUsedBytes = SWAP_USED_BYTES_EDEFAULT;

	/**
	 * The default value of the '{@link #getSwapFreeBytes() <em>Swap Free Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwapFreeBytes()
	 * @generated
	 * @ordered
	 */
	protected static final long SWAP_FREE_BYTES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSwapFreeBytes() <em>Swap Free Bytes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSwapFreeBytes()
	 * @generated
	 * @ordered
	 */
	protected long swapFreeBytes = SWAP_FREE_BYTES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemSwapUsageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MonitoringPackage.Literals.MEM_SWAP_USAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMemUsedBytes() {
		return memUsedBytes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemUsedBytes(long newMemUsedBytes) {
		long oldMemUsedBytes = memUsedBytes;
		memUsedBytes = newMemUsedBytes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.MEM_SWAP_USAGE__MEM_USED_BYTES, oldMemUsedBytes, memUsedBytes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getMemFreeBytes() {
		return memFreeBytes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMemFreeBytes(long newMemFreeBytes) {
		long oldMemFreeBytes = memFreeBytes;
		memFreeBytes = newMemFreeBytes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.MEM_SWAP_USAGE__MEM_FREE_BYTES, oldMemFreeBytes, memFreeBytes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSwapUsedBytes() {
		return swapUsedBytes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwapUsedBytes(long newSwapUsedBytes) {
		long oldSwapUsedBytes = swapUsedBytes;
		swapUsedBytes = newSwapUsedBytes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.MEM_SWAP_USAGE__SWAP_USED_BYTES, oldSwapUsedBytes, swapUsedBytes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSwapFreeBytes() {
		return swapFreeBytes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSwapFreeBytes(long newSwapFreeBytes) {
		long oldSwapFreeBytes = swapFreeBytes;
		swapFreeBytes = newSwapFreeBytes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.MEM_SWAP_USAGE__SWAP_FREE_BYTES, oldSwapFreeBytes, swapFreeBytes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_USED_BYTES:
				return getMemUsedBytes();
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_FREE_BYTES:
				return getMemFreeBytes();
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_USED_BYTES:
				return getSwapUsedBytes();
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_FREE_BYTES:
				return getSwapFreeBytes();
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
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_USED_BYTES:
				setMemUsedBytes((Long)newValue);
				return;
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_FREE_BYTES:
				setMemFreeBytes((Long)newValue);
				return;
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_USED_BYTES:
				setSwapUsedBytes((Long)newValue);
				return;
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_FREE_BYTES:
				setSwapFreeBytes((Long)newValue);
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
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_USED_BYTES:
				setMemUsedBytes(MEM_USED_BYTES_EDEFAULT);
				return;
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_FREE_BYTES:
				setMemFreeBytes(MEM_FREE_BYTES_EDEFAULT);
				return;
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_USED_BYTES:
				setSwapUsedBytes(SWAP_USED_BYTES_EDEFAULT);
				return;
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_FREE_BYTES:
				setSwapFreeBytes(SWAP_FREE_BYTES_EDEFAULT);
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
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_USED_BYTES:
				return memUsedBytes != MEM_USED_BYTES_EDEFAULT;
			case MonitoringPackage.MEM_SWAP_USAGE__MEM_FREE_BYTES:
				return memFreeBytes != MEM_FREE_BYTES_EDEFAULT;
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_USED_BYTES:
				return swapUsedBytes != SWAP_USED_BYTES_EDEFAULT;
			case MonitoringPackage.MEM_SWAP_USAGE__SWAP_FREE_BYTES:
				return swapFreeBytes != SWAP_FREE_BYTES_EDEFAULT;
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
		result.append(" (memUsedBytes: ");
		result.append(memUsedBytes);
		result.append(", memFreeBytes: ");
		result.append(memFreeBytes);
		result.append(", swapUsedBytes: ");
		result.append(swapUsedBytes);
		result.append(", swapFreeBytes: ");
		result.append(swapFreeBytes);
		result.append(')');
		return result.toString();
	}

} //MemSwapUsageImpl
