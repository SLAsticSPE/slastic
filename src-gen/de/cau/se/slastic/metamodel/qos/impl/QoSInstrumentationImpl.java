/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.qos.impl;

import de.cau.se.slastic.metamodel.qos.QoSInstrumentation;
import de.cau.se.slastic.metamodel.qos.QoSModel;
import de.cau.se.slastic.metamodel.qos.QosPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qo SInstrumentation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.qos.impl.QoSInstrumentationImpl#getQoSModel <em>Qo SModel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QoSInstrumentationImpl extends EObjectImpl implements QoSInstrumentation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QoSInstrumentationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QosPackage.Literals.QO_SINSTRUMENTATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSModel getQoSModel() {
		if (eContainerFeatureID() != QosPackage.QO_SINSTRUMENTATION__QO_SMODEL) return null;
		return (QoSModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQoSModel(QoSModel newQoSModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQoSModel, QosPackage.QO_SINSTRUMENTATION__QO_SMODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQoSModel(QoSModel newQoSModel) {
		if (newQoSModel != eInternalContainer() || (eContainerFeatureID() != QosPackage.QO_SINSTRUMENTATION__QO_SMODEL && newQoSModel != null)) {
			if (EcoreUtil.isAncestor(this, newQoSModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQoSModel != null)
				msgs = ((InternalEObject)newQoSModel).eInverseAdd(this, QosPackage.QO_SMODEL__QOS_INSTRUMENTATION, QoSModel.class, msgs);
			msgs = basicSetQoSModel(newQoSModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SINSTRUMENTATION__QO_SMODEL, newQoSModel, newQoSModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetQoSModel((QoSModel)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				return basicSetQoSModel(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				return eInternalContainer().eInverseRemove(this, QosPackage.QO_SMODEL__QOS_INSTRUMENTATION, QoSModel.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				return getQoSModel();
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
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				setQoSModel((QoSModel)newValue);
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
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				setQoSModel((QoSModel)null);
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
			case QosPackage.QO_SINSTRUMENTATION__QO_SMODEL:
				return getQoSModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //QoSInstrumentationImpl
