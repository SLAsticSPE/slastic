/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.typeRepository.impl;

import de.cau.se.slastic.metamodel.core.impl.FQNamedEntityImpl;

import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.typeRepository.TypeRepositoryPackage;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.typeRepository.impl.InterfaceImpl#getSignatures <em>Signatures</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterfaceImpl extends FQNamedEntityImpl implements Interface {
	/**
	 * The cached value of the '{@link #getSignatures() <em>Signatures</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignatures()
	 * @generated
	 * @ordered
	 */
	protected EList<Signature> signatures;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypeRepositoryPackage.Literals.INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signature> getSignatures() {
		if (signatures == null) {
			signatures = new EObjectResolvingEList<Signature>(Signature.class, this, TypeRepositoryPackage.INTERFACE__SIGNATURES);
		}
		return signatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TypeRepositoryPackage.INTERFACE__SIGNATURES:
				return getSignatures();
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
			case TypeRepositoryPackage.INTERFACE__SIGNATURES:
				getSignatures().clear();
				getSignatures().addAll((Collection<? extends Signature>)newValue);
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
			case TypeRepositoryPackage.INTERFACE__SIGNATURES:
				getSignatures().clear();
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
			case TypeRepositoryPackage.INTERFACE__SIGNATURES:
				return signatures != null && !signatures.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //InterfaceImpl
