/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage.impl;

import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;

import kieker.tools.slastic.metamodel.usage.CallingRelationship;
import kieker.tools.slastic.metamodel.usage.FrequencyDistribution;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Calling Relationship</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.CallingRelationshipImpl#getCalledInterface <em>Called Interface</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.CallingRelationshipImpl#getCalledSignature <em>Called Signature</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.CallingRelationshipImpl#getCallingOperation <em>Calling Operation</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.CallingRelationshipImpl#getFrequencyDistribution <em>Frequency Distribution</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CallingRelationshipImpl extends EObjectImpl implements CallingRelationship {
	/**
	 * The cached value of the '{@link #getCalledInterface() <em>Called Interface</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledInterface()
	 * @generated
	 * @ordered
	 */
	protected Interface calledInterface;

	/**
	 * The cached value of the '{@link #getCalledSignature() <em>Called Signature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalledSignature()
	 * @generated
	 * @ordered
	 */
	protected Signature calledSignature;

	/**
	 * The cached value of the '{@link #getCallingOperation() <em>Calling Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCallingOperation()
	 * @generated
	 * @ordered
	 */
	protected Operation callingOperation;

	/**
	 * The cached value of the '{@link #getFrequencyDistribution() <em>Frequency Distribution</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrequencyDistribution()
	 * @generated
	 * @ordered
	 */
	protected FrequencyDistribution frequencyDistribution;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CallingRelationshipImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.CALLING_RELATIONSHIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface getCalledInterface() {
		if (calledInterface != null && calledInterface.eIsProxy()) {
			InternalEObject oldCalledInterface = (InternalEObject)calledInterface;
			calledInterface = (Interface)eResolveProxy(oldCalledInterface);
			if (calledInterface != oldCalledInterface) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.CALLING_RELATIONSHIP__CALLED_INTERFACE, oldCalledInterface, calledInterface));
			}
		}
		return calledInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interface basicGetCalledInterface() {
		return calledInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledInterface(Interface newCalledInterface) {
		Interface oldCalledInterface = calledInterface;
		calledInterface = newCalledInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.CALLING_RELATIONSHIP__CALLED_INTERFACE, oldCalledInterface, calledInterface));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signature getCalledSignature() {
		if (calledSignature != null && calledSignature.eIsProxy()) {
			InternalEObject oldCalledSignature = (InternalEObject)calledSignature;
			calledSignature = (Signature)eResolveProxy(oldCalledSignature);
			if (calledSignature != oldCalledSignature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.CALLING_RELATIONSHIP__CALLED_SIGNATURE, oldCalledSignature, calledSignature));
			}
		}
		return calledSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Signature basicGetCalledSignature() {
		return calledSignature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalledSignature(Signature newCalledSignature) {
		Signature oldCalledSignature = calledSignature;
		calledSignature = newCalledSignature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.CALLING_RELATIONSHIP__CALLED_SIGNATURE, oldCalledSignature, calledSignature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getCallingOperation() {
		if (callingOperation != null && callingOperation.eIsProxy()) {
			InternalEObject oldCallingOperation = (InternalEObject)callingOperation;
			callingOperation = (Operation)eResolveProxy(oldCallingOperation);
			if (callingOperation != oldCallingOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.CALLING_RELATIONSHIP__CALLING_OPERATION, oldCallingOperation, callingOperation));
			}
		}
		return callingOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetCallingOperation() {
		return callingOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCallingOperation(Operation newCallingOperation) {
		Operation oldCallingOperation = callingOperation;
		callingOperation = newCallingOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.CALLING_RELATIONSHIP__CALLING_OPERATION, oldCallingOperation, callingOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FrequencyDistribution getFrequencyDistribution() {
		return frequencyDistribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFrequencyDistribution(FrequencyDistribution newFrequencyDistribution, NotificationChain msgs) {
		FrequencyDistribution oldFrequencyDistribution = frequencyDistribution;
		frequencyDistribution = newFrequencyDistribution;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION, oldFrequencyDistribution, newFrequencyDistribution);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrequencyDistribution(FrequencyDistribution newFrequencyDistribution) {
		if (newFrequencyDistribution != frequencyDistribution) {
			NotificationChain msgs = null;
			if (frequencyDistribution != null)
				msgs = ((InternalEObject)frequencyDistribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION, null, msgs);
			if (newFrequencyDistribution != null)
				msgs = ((InternalEObject)newFrequencyDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION, null, msgs);
			msgs = basicSetFrequencyDistribution(newFrequencyDistribution, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION, newFrequencyDistribution, newFrequencyDistribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION:
				return basicSetFrequencyDistribution(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_INTERFACE:
				if (resolve) return getCalledInterface();
				return basicGetCalledInterface();
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_SIGNATURE:
				if (resolve) return getCalledSignature();
				return basicGetCalledSignature();
			case UsagePackage.CALLING_RELATIONSHIP__CALLING_OPERATION:
				if (resolve) return getCallingOperation();
				return basicGetCallingOperation();
			case UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION:
				return getFrequencyDistribution();
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
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_INTERFACE:
				setCalledInterface((Interface)newValue);
				return;
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_SIGNATURE:
				setCalledSignature((Signature)newValue);
				return;
			case UsagePackage.CALLING_RELATIONSHIP__CALLING_OPERATION:
				setCallingOperation((Operation)newValue);
				return;
			case UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION:
				setFrequencyDistribution((FrequencyDistribution)newValue);
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
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_INTERFACE:
				setCalledInterface((Interface)null);
				return;
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_SIGNATURE:
				setCalledSignature((Signature)null);
				return;
			case UsagePackage.CALLING_RELATIONSHIP__CALLING_OPERATION:
				setCallingOperation((Operation)null);
				return;
			case UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION:
				setFrequencyDistribution((FrequencyDistribution)null);
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
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_INTERFACE:
				return calledInterface != null;
			case UsagePackage.CALLING_RELATIONSHIP__CALLED_SIGNATURE:
				return calledSignature != null;
			case UsagePackage.CALLING_RELATIONSHIP__CALLING_OPERATION:
				return callingOperation != null;
			case UsagePackage.CALLING_RELATIONSHIP__FREQUENCY_DISTRIBUTION:
				return frequencyDistribution != null;
		}
		return super.eIsSet(featureID);
	}

} //CallingRelationshipImpl
