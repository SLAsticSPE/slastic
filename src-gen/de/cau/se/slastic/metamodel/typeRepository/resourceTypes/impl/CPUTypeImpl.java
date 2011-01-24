/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository.resourceTypes.impl;

import de.cau.se.slastic.metamodel.typeRepository.impl.ResourceTypeImpl;

import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.CPUType;
import de.cau.se.slastic.metamodel.typeRepository.resourceTypes.ResourceTypesPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CPU Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl#getVendor <em>Vendor</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl#getModel <em>Model</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.resourceTypes.impl.CPUTypeImpl#getSpeedMhz <em>Speed Mhz</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CPUTypeImpl extends ResourceTypeImpl implements CPUType {
	/**
	 * The default value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected static final String VENDOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVendor() <em>Vendor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVendor()
	 * @generated
	 * @ordered
	 */
	protected String vendor = VENDOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModel() <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModel()
	 * @generated
	 * @ordered
	 */
	protected String model = MODEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpeedMhz() <em>Speed Mhz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeedMhz()
	 * @generated
	 * @ordered
	 */
	protected static final long SPEED_MHZ_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSpeedMhz() <em>Speed Mhz</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpeedMhz()
	 * @generated
	 * @ordered
	 */
	protected long speedMhz = SPEED_MHZ_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CPUTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourceTypesPackage.Literals.CPU_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVendor() {
		return vendor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVendor(String newVendor) {
		String oldVendor = vendor;
		vendor = newVendor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourceTypesPackage.CPU_TYPE__VENDOR, oldVendor, vendor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModel() {
		return model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModel(String newModel) {
		String oldModel = model;
		model = newModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourceTypesPackage.CPU_TYPE__MODEL, oldModel, model));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSpeedMhz() {
		return speedMhz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpeedMhz(long newSpeedMhz) {
		long oldSpeedMhz = speedMhz;
		speedMhz = newSpeedMhz;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ResourceTypesPackage.CPU_TYPE__SPEED_MHZ, oldSpeedMhz, speedMhz));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ResourceTypesPackage.CPU_TYPE__VENDOR:
				return getVendor();
			case ResourceTypesPackage.CPU_TYPE__MODEL:
				return getModel();
			case ResourceTypesPackage.CPU_TYPE__SPEED_MHZ:
				return getSpeedMhz();
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
			case ResourceTypesPackage.CPU_TYPE__VENDOR:
				setVendor((String)newValue);
				return;
			case ResourceTypesPackage.CPU_TYPE__MODEL:
				setModel((String)newValue);
				return;
			case ResourceTypesPackage.CPU_TYPE__SPEED_MHZ:
				setSpeedMhz((Long)newValue);
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
			case ResourceTypesPackage.CPU_TYPE__VENDOR:
				setVendor(VENDOR_EDEFAULT);
				return;
			case ResourceTypesPackage.CPU_TYPE__MODEL:
				setModel(MODEL_EDEFAULT);
				return;
			case ResourceTypesPackage.CPU_TYPE__SPEED_MHZ:
				setSpeedMhz(SPEED_MHZ_EDEFAULT);
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
			case ResourceTypesPackage.CPU_TYPE__VENDOR:
				return VENDOR_EDEFAULT == null ? vendor != null : !VENDOR_EDEFAULT.equals(vendor);
			case ResourceTypesPackage.CPU_TYPE__MODEL:
				return MODEL_EDEFAULT == null ? model != null : !MODEL_EDEFAULT.equals(model);
			case ResourceTypesPackage.CPU_TYPE__SPEED_MHZ:
				return speedMhz != SPEED_MHZ_EDEFAULT;
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
		result.append(" (vendor: ");
		result.append(vendor);
		result.append(", model: ");
		result.append(model);
		result.append(", speedMhz: ");
		result.append(speedMhz);
		result.append(')');
		return result.toString();
	}

} //CPUTypeImpl
