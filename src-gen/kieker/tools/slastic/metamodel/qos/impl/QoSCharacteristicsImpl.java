/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.qos.impl;

import kieker.tools.slastic.metamodel.qos.QoSCharacteristics;
import kieker.tools.slastic.metamodel.qos.QoSModel;
import kieker.tools.slastic.metamodel.qos.QosPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qo SCharacteristics</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSCharacteristicsImpl#getQosModel <em>Qos Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QoSCharacteristicsImpl extends EObjectImpl implements QoSCharacteristics {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QoSCharacteristicsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QosPackage.Literals.QO_SCHARACTERISTICS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSModel getQosModel() {
		if (eContainerFeatureID() != QosPackage.QO_SCHARACTERISTICS__QOS_MODEL) return null;
		return (QoSModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQosModel(QoSModel newQosModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newQosModel, QosPackage.QO_SCHARACTERISTICS__QOS_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQosModel(QoSModel newQosModel) {
		if (newQosModel != eInternalContainer() || (eContainerFeatureID() != QosPackage.QO_SCHARACTERISTICS__QOS_MODEL && newQosModel != null)) {
			if (EcoreUtil.isAncestor(this, newQosModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newQosModel != null)
				msgs = ((InternalEObject)newQosModel).eInverseAdd(this, QosPackage.QO_SMODEL__QOS_CHARACTERISTICS, QoSModel.class, msgs);
			msgs = basicSetQosModel(newQosModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SCHARACTERISTICS__QOS_MODEL, newQosModel, newQosModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetQosModel((QoSModel)otherEnd, msgs);
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
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				return basicSetQosModel(null, msgs);
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
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				return eInternalContainer().eInverseRemove(this, QosPackage.QO_SMODEL__QOS_CHARACTERISTICS, QoSModel.class, msgs);
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
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				return getQosModel();
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
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				setQosModel((QoSModel)newValue);
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
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				setQosModel((QoSModel)null);
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
			case QosPackage.QO_SCHARACTERISTICS__QOS_MODEL:
				return getQosModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //QoSCharacteristicsImpl
