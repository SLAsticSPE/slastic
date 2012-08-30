/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.qos.impl;

import kieker.tools.slastic.metamodel.core.SystemModel;

import kieker.tools.slastic.metamodel.qos.CostProfile;
import kieker.tools.slastic.metamodel.qos.QoSCharacteristics;
import kieker.tools.slastic.metamodel.qos.QoSConstraints;
import kieker.tools.slastic.metamodel.qos.QoSInstrumentation;
import kieker.tools.slastic.metamodel.qos.QoSModel;
import kieker.tools.slastic.metamodel.qos.QoSObservations;
import kieker.tools.slastic.metamodel.qos.QosPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qo SModel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSModelImpl#getSystemModel <em>System Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSModelImpl#getQosConstraints <em>Qos Constraints</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSModelImpl#getQosInstrumentation <em>Qos Instrumentation</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSModelImpl#getQosObservations <em>Qos Observations</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSModelImpl#getCostProfile <em>Cost Profile</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.qos.impl.QoSModelImpl#getQosCharacteristics <em>Qos Characteristics</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QoSModelImpl extends EObjectImpl implements QoSModel {
	/**
	 * The cached value of the '{@link #getSystemModel() <em>System Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystemModel()
	 * @generated
	 * @ordered
	 */
	protected SystemModel systemModel;

	/**
	 * The cached value of the '{@link #getQosConstraints() <em>Qos Constraints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQosConstraints()
	 * @generated
	 * @ordered
	 */
	protected QoSConstraints qosConstraints;

	/**
	 * The cached value of the '{@link #getQosInstrumentation() <em>Qos Instrumentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQosInstrumentation()
	 * @generated
	 * @ordered
	 */
	protected QoSInstrumentation qosInstrumentation;

	/**
	 * The cached value of the '{@link #getQosObservations() <em>Qos Observations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQosObservations()
	 * @generated
	 * @ordered
	 */
	protected QoSObservations qosObservations;

	/**
	 * The cached value of the '{@link #getCostProfile() <em>Cost Profile</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCostProfile()
	 * @generated
	 * @ordered
	 */
	protected CostProfile costProfile;

	/**
	 * The cached value of the '{@link #getQosCharacteristics() <em>Qos Characteristics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQosCharacteristics()
	 * @generated
	 * @ordered
	 */
	protected QoSCharacteristics qosCharacteristics;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QoSModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QosPackage.Literals.QO_SMODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemModel getSystemModel() {
		if (systemModel != null && systemModel.eIsProxy()) {
			InternalEObject oldSystemModel = (InternalEObject)systemModel;
			systemModel = (SystemModel)eResolveProxy(oldSystemModel);
			if (systemModel != oldSystemModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, QosPackage.QO_SMODEL__SYSTEM_MODEL, oldSystemModel, systemModel));
			}
		}
		return systemModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SystemModel basicGetSystemModel() {
		return systemModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystemModel(SystemModel newSystemModel) {
		SystemModel oldSystemModel = systemModel;
		systemModel = newSystemModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__SYSTEM_MODEL, oldSystemModel, systemModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSConstraints getQosConstraints() {
		return qosConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQosConstraints(QoSConstraints newQosConstraints, NotificationChain msgs) {
		QoSConstraints oldQosConstraints = qosConstraints;
		qosConstraints = newQosConstraints;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_CONSTRAINTS, oldQosConstraints, newQosConstraints);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQosConstraints(QoSConstraints newQosConstraints) {
		if (newQosConstraints != qosConstraints) {
			NotificationChain msgs = null;
			if (qosConstraints != null)
				msgs = ((InternalEObject)qosConstraints).eInverseRemove(this, QosPackage.QO_SCONSTRAINTS__QO_SMODEL, QoSConstraints.class, msgs);
			if (newQosConstraints != null)
				msgs = ((InternalEObject)newQosConstraints).eInverseAdd(this, QosPackage.QO_SCONSTRAINTS__QO_SMODEL, QoSConstraints.class, msgs);
			msgs = basicSetQosConstraints(newQosConstraints, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_CONSTRAINTS, newQosConstraints, newQosConstraints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSInstrumentation getQosInstrumentation() {
		return qosInstrumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQosInstrumentation(QoSInstrumentation newQosInstrumentation, NotificationChain msgs) {
		QoSInstrumentation oldQosInstrumentation = qosInstrumentation;
		qosInstrumentation = newQosInstrumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_INSTRUMENTATION, oldQosInstrumentation, newQosInstrumentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQosInstrumentation(QoSInstrumentation newQosInstrumentation) {
		if (newQosInstrumentation != qosInstrumentation) {
			NotificationChain msgs = null;
			if (qosInstrumentation != null)
				msgs = ((InternalEObject)qosInstrumentation).eInverseRemove(this, QosPackage.QO_SINSTRUMENTATION__QO_SMODEL, QoSInstrumentation.class, msgs);
			if (newQosInstrumentation != null)
				msgs = ((InternalEObject)newQosInstrumentation).eInverseAdd(this, QosPackage.QO_SINSTRUMENTATION__QO_SMODEL, QoSInstrumentation.class, msgs);
			msgs = basicSetQosInstrumentation(newQosInstrumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_INSTRUMENTATION, newQosInstrumentation, newQosInstrumentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSObservations getQosObservations() {
		return qosObservations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQosObservations(QoSObservations newQosObservations, NotificationChain msgs) {
		QoSObservations oldQosObservations = qosObservations;
		qosObservations = newQosObservations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_OBSERVATIONS, oldQosObservations, newQosObservations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQosObservations(QoSObservations newQosObservations) {
		if (newQosObservations != qosObservations) {
			NotificationChain msgs = null;
			if (qosObservations != null)
				msgs = ((InternalEObject)qosObservations).eInverseRemove(this, QosPackage.QO_SOBSERVATIONS__QO_SMODEL, QoSObservations.class, msgs);
			if (newQosObservations != null)
				msgs = ((InternalEObject)newQosObservations).eInverseAdd(this, QosPackage.QO_SOBSERVATIONS__QO_SMODEL, QoSObservations.class, msgs);
			msgs = basicSetQosObservations(newQosObservations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_OBSERVATIONS, newQosObservations, newQosObservations));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CostProfile getCostProfile() {
		return costProfile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCostProfile(CostProfile newCostProfile, NotificationChain msgs) {
		CostProfile oldCostProfile = costProfile;
		costProfile = newCostProfile;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__COST_PROFILE, oldCostProfile, newCostProfile);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCostProfile(CostProfile newCostProfile) {
		if (newCostProfile != costProfile) {
			NotificationChain msgs = null;
			if (costProfile != null)
				msgs = ((InternalEObject)costProfile).eInverseRemove(this, QosPackage.COST_PROFILE__QO_SMODEL, CostProfile.class, msgs);
			if (newCostProfile != null)
				msgs = ((InternalEObject)newCostProfile).eInverseAdd(this, QosPackage.COST_PROFILE__QO_SMODEL, CostProfile.class, msgs);
			msgs = basicSetCostProfile(newCostProfile, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__COST_PROFILE, newCostProfile, newCostProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QoSCharacteristics getQosCharacteristics() {
		return qosCharacteristics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQosCharacteristics(QoSCharacteristics newQosCharacteristics, NotificationChain msgs) {
		QoSCharacteristics oldQosCharacteristics = qosCharacteristics;
		qosCharacteristics = newQosCharacteristics;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_CHARACTERISTICS, oldQosCharacteristics, newQosCharacteristics);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQosCharacteristics(QoSCharacteristics newQosCharacteristics) {
		if (newQosCharacteristics != qosCharacteristics) {
			NotificationChain msgs = null;
			if (qosCharacteristics != null)
				msgs = ((InternalEObject)qosCharacteristics).eInverseRemove(this, QosPackage.QO_SCHARACTERISTICS__QOS_MODEL, QoSCharacteristics.class, msgs);
			if (newQosCharacteristics != null)
				msgs = ((InternalEObject)newQosCharacteristics).eInverseAdd(this, QosPackage.QO_SCHARACTERISTICS__QOS_MODEL, QoSCharacteristics.class, msgs);
			msgs = basicSetQosCharacteristics(newQosCharacteristics, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QosPackage.QO_SMODEL__QOS_CHARACTERISTICS, newQosCharacteristics, newQosCharacteristics));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QosPackage.QO_SMODEL__QOS_CONSTRAINTS:
				if (qosConstraints != null)
					msgs = ((InternalEObject)qosConstraints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QosPackage.QO_SMODEL__QOS_CONSTRAINTS, null, msgs);
				return basicSetQosConstraints((QoSConstraints)otherEnd, msgs);
			case QosPackage.QO_SMODEL__QOS_INSTRUMENTATION:
				if (qosInstrumentation != null)
					msgs = ((InternalEObject)qosInstrumentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QosPackage.QO_SMODEL__QOS_INSTRUMENTATION, null, msgs);
				return basicSetQosInstrumentation((QoSInstrumentation)otherEnd, msgs);
			case QosPackage.QO_SMODEL__QOS_OBSERVATIONS:
				if (qosObservations != null)
					msgs = ((InternalEObject)qosObservations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QosPackage.QO_SMODEL__QOS_OBSERVATIONS, null, msgs);
				return basicSetQosObservations((QoSObservations)otherEnd, msgs);
			case QosPackage.QO_SMODEL__COST_PROFILE:
				if (costProfile != null)
					msgs = ((InternalEObject)costProfile).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QosPackage.QO_SMODEL__COST_PROFILE, null, msgs);
				return basicSetCostProfile((CostProfile)otherEnd, msgs);
			case QosPackage.QO_SMODEL__QOS_CHARACTERISTICS:
				if (qosCharacteristics != null)
					msgs = ((InternalEObject)qosCharacteristics).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QosPackage.QO_SMODEL__QOS_CHARACTERISTICS, null, msgs);
				return basicSetQosCharacteristics((QoSCharacteristics)otherEnd, msgs);
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
			case QosPackage.QO_SMODEL__QOS_CONSTRAINTS:
				return basicSetQosConstraints(null, msgs);
			case QosPackage.QO_SMODEL__QOS_INSTRUMENTATION:
				return basicSetQosInstrumentation(null, msgs);
			case QosPackage.QO_SMODEL__QOS_OBSERVATIONS:
				return basicSetQosObservations(null, msgs);
			case QosPackage.QO_SMODEL__COST_PROFILE:
				return basicSetCostProfile(null, msgs);
			case QosPackage.QO_SMODEL__QOS_CHARACTERISTICS:
				return basicSetQosCharacteristics(null, msgs);
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
			case QosPackage.QO_SMODEL__SYSTEM_MODEL:
				if (resolve) return getSystemModel();
				return basicGetSystemModel();
			case QosPackage.QO_SMODEL__QOS_CONSTRAINTS:
				return getQosConstraints();
			case QosPackage.QO_SMODEL__QOS_INSTRUMENTATION:
				return getQosInstrumentation();
			case QosPackage.QO_SMODEL__QOS_OBSERVATIONS:
				return getQosObservations();
			case QosPackage.QO_SMODEL__COST_PROFILE:
				return getCostProfile();
			case QosPackage.QO_SMODEL__QOS_CHARACTERISTICS:
				return getQosCharacteristics();
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
			case QosPackage.QO_SMODEL__SYSTEM_MODEL:
				setSystemModel((SystemModel)newValue);
				return;
			case QosPackage.QO_SMODEL__QOS_CONSTRAINTS:
				setQosConstraints((QoSConstraints)newValue);
				return;
			case QosPackage.QO_SMODEL__QOS_INSTRUMENTATION:
				setQosInstrumentation((QoSInstrumentation)newValue);
				return;
			case QosPackage.QO_SMODEL__QOS_OBSERVATIONS:
				setQosObservations((QoSObservations)newValue);
				return;
			case QosPackage.QO_SMODEL__COST_PROFILE:
				setCostProfile((CostProfile)newValue);
				return;
			case QosPackage.QO_SMODEL__QOS_CHARACTERISTICS:
				setQosCharacteristics((QoSCharacteristics)newValue);
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
			case QosPackage.QO_SMODEL__SYSTEM_MODEL:
				setSystemModel((SystemModel)null);
				return;
			case QosPackage.QO_SMODEL__QOS_CONSTRAINTS:
				setQosConstraints((QoSConstraints)null);
				return;
			case QosPackage.QO_SMODEL__QOS_INSTRUMENTATION:
				setQosInstrumentation((QoSInstrumentation)null);
				return;
			case QosPackage.QO_SMODEL__QOS_OBSERVATIONS:
				setQosObservations((QoSObservations)null);
				return;
			case QosPackage.QO_SMODEL__COST_PROFILE:
				setCostProfile((CostProfile)null);
				return;
			case QosPackage.QO_SMODEL__QOS_CHARACTERISTICS:
				setQosCharacteristics((QoSCharacteristics)null);
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
			case QosPackage.QO_SMODEL__SYSTEM_MODEL:
				return systemModel != null;
			case QosPackage.QO_SMODEL__QOS_CONSTRAINTS:
				return qosConstraints != null;
			case QosPackage.QO_SMODEL__QOS_INSTRUMENTATION:
				return qosInstrumentation != null;
			case QosPackage.QO_SMODEL__QOS_OBSERVATIONS:
				return qosObservations != null;
			case QosPackage.QO_SMODEL__COST_PROFILE:
				return costProfile != null;
			case QosPackage.QO_SMODEL__QOS_CHARACTERISTICS:
				return qosCharacteristics != null;
		}
		return super.eIsSet(featureID);
	}

} //QoSModelImpl
