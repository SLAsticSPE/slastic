/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.monitoring.impl;

import de.cau.se.slastic.metamodel.monitoring.MonitoringPackage;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Execution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl#getEoi <em>Eoi</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl#getEss <em>Ess</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl#getTin <em>Tin</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl#getTout <em>Tout</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.monitoring.impl.OperationExecutionImpl#getSessionId <em>Session Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OperationExecutionImpl extends EObjectImpl implements OperationExecution {
	/**
	 * The default value of the '{@link #getTraceId() <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraceId()
	 * @generated
	 * @ordered
	 */
	protected static final long TRACE_ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTraceId() <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraceId()
	 * @generated
	 * @ordered
	 */
	protected long traceId = TRACE_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getEoi() <em>Eoi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEoi()
	 * @generated
	 * @ordered
	 */
	protected static final int EOI_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEoi() <em>Eoi</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEoi()
	 * @generated
	 * @ordered
	 */
	protected int eoi = EOI_EDEFAULT;

	/**
	 * The default value of the '{@link #getEss() <em>Ess</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEss()
	 * @generated
	 * @ordered
	 */
	protected static final int ESS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEss() <em>Ess</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEss()
	 * @generated
	 * @ordered
	 */
	protected int ess = ESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getTin() <em>Tin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTin()
	 * @generated
	 * @ordered
	 */
	protected static final long TIN_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTin() <em>Tin</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTin()
	 * @generated
	 * @ordered
	 */
	protected long tin = TIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTout() <em>Tout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTout()
	 * @generated
	 * @ordered
	 */
	protected static final long TOUT_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTout() <em>Tout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTout()
	 * @generated
	 * @ordered
	 */
	protected long tout = TOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getSessionId() <em>Session Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionId()
	 * @generated
	 * @ordered
	 */
	protected static final String SESSION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSessionId() <em>Session Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessionId()
	 * @generated
	 * @ordered
	 */
	protected String sessionId = SESSION_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationExecutionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MonitoringPackage.Literals.OPERATION_EXECUTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTraceId() {
		return traceId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTraceId(long newTraceId) {
		long oldTraceId = traceId;
		traceId = newTraceId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.OPERATION_EXECUTION__TRACE_ID, oldTraceId, traceId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEoi() {
		return eoi;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEoi(int newEoi) {
		int oldEoi = eoi;
		eoi = newEoi;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.OPERATION_EXECUTION__EOI, oldEoi, eoi));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEss() {
		return ess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEss(int newEss) {
		int oldEss = ess;
		ess = newEss;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.OPERATION_EXECUTION__ESS, oldEss, ess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTin() {
		return tin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTin(long newTin) {
		long oldTin = tin;
		tin = newTin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.OPERATION_EXECUTION__TIN, oldTin, tin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTout() {
		return tout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTout(long newTout) {
		long oldTout = tout;
		tout = newTout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.OPERATION_EXECUTION__TOUT, oldTout, tout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSessionId(String newSessionId) {
		String oldSessionId = sessionId;
		sessionId = newSessionId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.OPERATION_EXECUTION__SESSION_ID, oldSessionId, sessionId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MonitoringPackage.OPERATION_EXECUTION__TRACE_ID:
				return getTraceId();
			case MonitoringPackage.OPERATION_EXECUTION__EOI:
				return getEoi();
			case MonitoringPackage.OPERATION_EXECUTION__ESS:
				return getEss();
			case MonitoringPackage.OPERATION_EXECUTION__TIN:
				return getTin();
			case MonitoringPackage.OPERATION_EXECUTION__TOUT:
				return getTout();
			case MonitoringPackage.OPERATION_EXECUTION__SESSION_ID:
				return getSessionId();
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
			case MonitoringPackage.OPERATION_EXECUTION__TRACE_ID:
				setTraceId((Long)newValue);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__EOI:
				setEoi((Integer)newValue);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__ESS:
				setEss((Integer)newValue);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__TIN:
				setTin((Long)newValue);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__TOUT:
				setTout((Long)newValue);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__SESSION_ID:
				setSessionId((String)newValue);
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
			case MonitoringPackage.OPERATION_EXECUTION__TRACE_ID:
				setTraceId(TRACE_ID_EDEFAULT);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__EOI:
				setEoi(EOI_EDEFAULT);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__ESS:
				setEss(ESS_EDEFAULT);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__TIN:
				setTin(TIN_EDEFAULT);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__TOUT:
				setTout(TOUT_EDEFAULT);
				return;
			case MonitoringPackage.OPERATION_EXECUTION__SESSION_ID:
				setSessionId(SESSION_ID_EDEFAULT);
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
			case MonitoringPackage.OPERATION_EXECUTION__TRACE_ID:
				return traceId != TRACE_ID_EDEFAULT;
			case MonitoringPackage.OPERATION_EXECUTION__EOI:
				return eoi != EOI_EDEFAULT;
			case MonitoringPackage.OPERATION_EXECUTION__ESS:
				return ess != ESS_EDEFAULT;
			case MonitoringPackage.OPERATION_EXECUTION__TIN:
				return tin != TIN_EDEFAULT;
			case MonitoringPackage.OPERATION_EXECUTION__TOUT:
				return tout != TOUT_EDEFAULT;
			case MonitoringPackage.OPERATION_EXECUTION__SESSION_ID:
				return SESSION_ID_EDEFAULT == null ? sessionId != null : !SESSION_ID_EDEFAULT.equals(sessionId);
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
		result.append(" (traceId: ");
		result.append(traceId);
		result.append(", eoi: ");
		result.append(eoi);
		result.append(", ess: ");
		result.append(ess);
		result.append(", tin: ");
		result.append(tin);
		result.append(", tout: ");
		result.append(tout);
		result.append(", sessionId: ");
		result.append(sessionId);
		result.append(')');
		return result.toString();
	}

} //OperationExecutionImpl
