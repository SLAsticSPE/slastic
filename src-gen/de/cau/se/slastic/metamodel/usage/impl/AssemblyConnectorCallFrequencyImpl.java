/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;

import de.cau.se.slastic.metamodel.typeRepository.Signature;

import de.cau.se.slastic.metamodel.usage.AssemblyConnectorCallFrequency;
import de.cau.se.slastic.metamodel.usage.UsagePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assembly Connector Call Frequency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl#getFrequency <em>Frequency</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl#getAssemblyConnectors <em>Assembly Connectors</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.AssemblyConnectorCallFrequencyImpl#getSignature <em>Signature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssemblyConnectorCallFrequencyImpl extends EObjectImpl implements AssemblyConnectorCallFrequency {
	/**
	 * The default value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected static final long FREQUENCY_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getFrequency() <em>Frequency</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequency()
	 * @generated
	 * @ordered
	 */
	protected long frequency = FREQUENCY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAssemblyConnectors() <em>Assembly Connectors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyConnectors()
	 * @generated
	 * @ordered
	 */
	protected EList<AssemblyConnector> assemblyConnectors;

	/**
	 * The cached value of the '{@link #getSignature() <em>Signature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignature()
	 * @generated
	 * @ordered
	 */
	protected Signature signature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssemblyConnectorCallFrequencyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.ASSEMBLY_CONNECTOR_CALL_FREQUENCY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getFrequency() {
		return frequency;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrequency(long newFrequency) {
		long oldFrequency = frequency;
		frequency = newFrequency;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY, oldFrequency, frequency));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssemblyConnector> getAssemblyConnectors() {
		if (assemblyConnectors == null) {
			assemblyConnectors = new EObjectResolvingEList<AssemblyConnector>(AssemblyConnector.class, this, UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTORS);
		}
		return assemblyConnectors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signature getSignature() {
		if (signature != null && signature.eIsProxy()) {
			InternalEObject oldSignature = (InternalEObject)signature;
			signature = (Signature)eResolveProxy(oldSignature);
			if (signature != oldSignature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE, oldSignature, signature));
			}
		}
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signature basicGetSignature() {
		return signature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSignature(Signature newSignature) {
		Signature oldSignature = signature;
		signature = newSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE, oldSignature, signature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY:
				return getFrequency();
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTORS:
				return getAssemblyConnectors();
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE:
				if (resolve) return getSignature();
				return basicGetSignature();
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
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY:
				setFrequency((Long)newValue);
				return;
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTORS:
				getAssemblyConnectors().clear();
				getAssemblyConnectors().addAll((Collection<? extends AssemblyConnector>)newValue);
				return;
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE:
				setSignature((Signature)newValue);
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
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY:
				setFrequency(FREQUENCY_EDEFAULT);
				return;
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTORS:
				getAssemblyConnectors().clear();
				return;
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE:
				setSignature((Signature)null);
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
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__FREQUENCY:
				return frequency != FREQUENCY_EDEFAULT;
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__ASSEMBLY_CONNECTORS:
				return assemblyConnectors != null && !assemblyConnectors.isEmpty();
			case UsagePackage.ASSEMBLY_CONNECTOR_CALL_FREQUENCY__SIGNATURE:
				return signature != null;
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
		result.append(" (frequency: ");
		result.append(frequency);
		result.append(')');
		return result.toString();
	}

} //AssemblyConnectorCallFrequencyImpl
