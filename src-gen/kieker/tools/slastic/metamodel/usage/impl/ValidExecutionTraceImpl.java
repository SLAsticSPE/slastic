/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage.impl;

import kieker.tools.slastic.metamodel.usage.MessageTrace;
import kieker.tools.slastic.metamodel.usage.UsagePackage;
import kieker.tools.slastic.metamodel.usage.ValidExecutionTrace;
import kieker.tools.slastic.metamodel.usage.ValidTrace;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Valid Execution Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.ValidExecutionTraceImpl#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.ValidExecutionTraceImpl#getMessageTrace <em>Message Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValidExecutionTraceImpl extends ExecutionTraceImpl implements ValidExecutionTrace {
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
	 * The cached value of the '{@link #getMessageTrace() <em>Message Trace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageTrace()
	 * @generated
	 * @ordered
	 */
	protected MessageTrace messageTrace;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValidExecutionTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.VALID_EXECUTION_TRACE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID, oldTraceId, traceId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageTrace getMessageTrace() {
		if (messageTrace != null && messageTrace.eIsProxy()) {
			InternalEObject oldMessageTrace = (InternalEObject)messageTrace;
			messageTrace = (MessageTrace)eResolveProxy(oldMessageTrace);
			if (messageTrace != oldMessageTrace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, oldMessageTrace, messageTrace));
			}
		}
		return messageTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageTrace basicGetMessageTrace() {
		return messageTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMessageTrace(MessageTrace newMessageTrace, NotificationChain msgs) {
		MessageTrace oldMessageTrace = messageTrace;
		messageTrace = newMessageTrace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, oldMessageTrace, newMessageTrace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageTrace(MessageTrace newMessageTrace) {
		if (newMessageTrace != messageTrace) {
			NotificationChain msgs = null;
			if (messageTrace != null)
				msgs = ((InternalEObject)messageTrace).eInverseRemove(this, UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE, MessageTrace.class, msgs);
			if (newMessageTrace != null)
				msgs = ((InternalEObject)newMessageTrace).eInverseAdd(this, UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE, MessageTrace.class, msgs);
			msgs = basicSetMessageTrace(newMessageTrace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, newMessageTrace, newMessageTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				if (messageTrace != null)
					msgs = ((InternalEObject)messageTrace).eInverseRemove(this, UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE, MessageTrace.class, msgs);
				return basicSetMessageTrace((MessageTrace)otherEnd, msgs);
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
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				return basicSetMessageTrace(null, msgs);
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
			case UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID:
				return getTraceId();
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				if (resolve) return getMessageTrace();
				return basicGetMessageTrace();
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
			case UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID:
				setTraceId((Long)newValue);
				return;
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				setMessageTrace((MessageTrace)newValue);
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
			case UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID:
				setTraceId(TRACE_ID_EDEFAULT);
				return;
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				setMessageTrace((MessageTrace)null);
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
			case UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID:
				return traceId != TRACE_ID_EDEFAULT;
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				return messageTrace != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ValidTrace.class) {
			switch (derivedFeatureID) {
				case UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID: return UsagePackage.VALID_TRACE__TRACE_ID;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ValidTrace.class) {
			switch (baseFeatureID) {
				case UsagePackage.VALID_TRACE__TRACE_ID: return UsagePackage.VALID_EXECUTION_TRACE__TRACE_ID;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(')');
		return result.toString();
	}

} //ValidExecutionTraceImpl
