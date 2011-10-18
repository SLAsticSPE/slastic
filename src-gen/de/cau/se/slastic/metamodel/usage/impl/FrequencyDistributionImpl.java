/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.usage.FrequencyDistribution;
import de.cau.se.slastic.metamodel.usage.UsagePackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Frequency Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.FrequencyDistributionImpl#getValues <em>Values</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.FrequencyDistributionImpl#getFrequencies <em>Frequencies</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FrequencyDistributionImpl extends EObjectImpl implements FrequencyDistribution {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Long> values;

	/**
	 * The cached value of the '{@link #getFrequencies() <em>Frequencies</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Long> frequencies;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FrequencyDistributionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.FREQUENCY_DISTRIBUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Long> getValues() {
		if (values == null) {
			values = new EDataTypeUniqueEList<Long>(Long.class, this, UsagePackage.FREQUENCY_DISTRIBUTION__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Long> getFrequencies() {
		if (frequencies == null) {
			frequencies = new EDataTypeEList<Long>(Long.class, this, UsagePackage.FREQUENCY_DISTRIBUTION__FREQUENCIES);
		}
		return frequencies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.FREQUENCY_DISTRIBUTION__VALUES:
				return getValues();
			case UsagePackage.FREQUENCY_DISTRIBUTION__FREQUENCIES:
				return getFrequencies();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UsagePackage.FREQUENCY_DISTRIBUTION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends Long>)newValue);
				return;
			case UsagePackage.FREQUENCY_DISTRIBUTION__FREQUENCIES:
				getFrequencies().clear();
				getFrequencies().addAll((Collection<? extends Long>)newValue);
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
			case UsagePackage.FREQUENCY_DISTRIBUTION__VALUES:
				getValues().clear();
				return;
			case UsagePackage.FREQUENCY_DISTRIBUTION__FREQUENCIES:
				getFrequencies().clear();
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
			case UsagePackage.FREQUENCY_DISTRIBUTION__VALUES:
				return values != null && !values.isEmpty();
			case UsagePackage.FREQUENCY_DISTRIBUTION__FREQUENCIES:
				return frequencies != null && !frequencies.isEmpty();
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
		result.append(" (values: ");
		result.append(values);
		result.append(", frequencies: ");
		result.append(frequencies);
		result.append(')');
		return result.toString();
	}

} //FrequencyDistributionImpl
