/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.adaptation.impl;

import de.cau.se.slastic.metamodel.adaptation.AdaptationModel;
import de.cau.se.slastic.metamodel.adaptation.AdaptationPackage;
import de.cau.se.slastic.metamodel.adaptation.Control;

import de.cau.se.slastic.metamodel.qos.QoSModel;

import de.cau.se.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification;
import de.cau.se.slastic.metamodel.reconfiguration.specification.SpecificationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl#getControlModel <em>Control Model</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl#getQoSModel <em>Qo SModel</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.adaptation.impl.AdaptationModelImpl#getReconfigurationSpecification <em>Reconfiguration Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdaptationModelImpl extends EObjectImpl implements AdaptationModel {
	/**
	 * The cached value of the '{@link #getControlModel() <em>Control Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControlModel()
	 * @generated
	 * @ordered
	 */
	protected Control controlModel;

	/**
	 * The cached value of the '{@link #getQoSModel() <em>Qo SModel</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQoSModel()
	 * @generated
	 * @ordered
	 */
	protected QoSModel qoSModel;

	/**
	 * The cached value of the '{@link #getReconfigurationSpecification() <em>Reconfiguration Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReconfigurationSpecification()
	 * @generated
	 * @ordered
	 */
	protected ReconfigurationSpecification reconfigurationSpecification;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdaptationPackage.Literals.ADAPTATION_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Control getControlModel() {
		return controlModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetControlModel(Control newControlModel, NotificationChain msgs) {
		Control oldControlModel = controlModel;
		controlModel = newControlModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL, oldControlModel, newControlModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlModel(Control newControlModel) {
		if (newControlModel != controlModel) {
			NotificationChain msgs = null;
			if (controlModel != null)
				msgs = ((InternalEObject)controlModel).eInverseRemove(this, AdaptationPackage.CONTROL__ADAPTATION_MODEL, Control.class, msgs);
			if (newControlModel != null)
				msgs = ((InternalEObject)newControlModel).eInverseAdd(this, AdaptationPackage.CONTROL__ADAPTATION_MODEL, Control.class, msgs);
			msgs = basicSetControlModel(newControlModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL, newControlModel, newControlModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSModel getQoSModel() {
		if (qoSModel != null && qoSModel.eIsProxy()) {
			InternalEObject oldQoSModel = (InternalEObject)qoSModel;
			qoSModel = (QoSModel)eResolveProxy(oldQoSModel);
			if (qoSModel != oldQoSModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AdaptationPackage.ADAPTATION_MODEL__QO_SMODEL, oldQoSModel, qoSModel));
			}
		}
		return qoSModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSModel basicGetQoSModel() {
		return qoSModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQoSModel(QoSModel newQoSModel) {
		QoSModel oldQoSModel = qoSModel;
		qoSModel = newQoSModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_MODEL__QO_SMODEL, oldQoSModel, qoSModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReconfigurationSpecification getReconfigurationSpecification() {
		return reconfigurationSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReconfigurationSpecification(ReconfigurationSpecification newReconfigurationSpecification, NotificationChain msgs) {
		ReconfigurationSpecification oldReconfigurationSpecification = reconfigurationSpecification;
		reconfigurationSpecification = newReconfigurationSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION, oldReconfigurationSpecification, newReconfigurationSpecification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReconfigurationSpecification(ReconfigurationSpecification newReconfigurationSpecification) {
		if (newReconfigurationSpecification != reconfigurationSpecification) {
			NotificationChain msgs = null;
			if (reconfigurationSpecification != null)
				msgs = ((InternalEObject)reconfigurationSpecification).eInverseRemove(this, SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL, ReconfigurationSpecification.class, msgs);
			if (newReconfigurationSpecification != null)
				msgs = ((InternalEObject)newReconfigurationSpecification).eInverseAdd(this, SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL, ReconfigurationSpecification.class, msgs);
			msgs = basicSetReconfigurationSpecification(newReconfigurationSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION, newReconfigurationSpecification, newReconfigurationSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL:
				if (controlModel != null)
					msgs = ((InternalEObject)controlModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL, null, msgs);
				return basicSetControlModel((Control)otherEnd, msgs);
			case AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION:
				if (reconfigurationSpecification != null)
					msgs = ((InternalEObject)reconfigurationSpecification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION, null, msgs);
				return basicSetReconfigurationSpecification((ReconfigurationSpecification)otherEnd, msgs);
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
			case AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL:
				return basicSetControlModel(null, msgs);
			case AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION:
				return basicSetReconfigurationSpecification(null, msgs);
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
			case AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL:
				return getControlModel();
			case AdaptationPackage.ADAPTATION_MODEL__QO_SMODEL:
				if (resolve) return getQoSModel();
				return basicGetQoSModel();
			case AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION:
				return getReconfigurationSpecification();
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
			case AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL:
				setControlModel((Control)newValue);
				return;
			case AdaptationPackage.ADAPTATION_MODEL__QO_SMODEL:
				setQoSModel((QoSModel)newValue);
				return;
			case AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION:
				setReconfigurationSpecification((ReconfigurationSpecification)newValue);
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
			case AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL:
				setControlModel((Control)null);
				return;
			case AdaptationPackage.ADAPTATION_MODEL__QO_SMODEL:
				setQoSModel((QoSModel)null);
				return;
			case AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION:
				setReconfigurationSpecification((ReconfigurationSpecification)null);
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
			case AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL:
				return controlModel != null;
			case AdaptationPackage.ADAPTATION_MODEL__QO_SMODEL:
				return qoSModel != null;
			case AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION:
				return reconfigurationSpecification != null;
		}
		return super.eIsSet(featureID);
	}

} //AdaptationModelImpl
