/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.UsagePackage;
import de.cau.se.slastic.metamodel.usage.ValidExecutionTrace;

import org.eclipse.emf.common.notify.Notification;
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
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.ValidExecutionTraceImpl#getMessageTrace <em>Message Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValidExecutionTraceImpl extends ExecutionTraceImpl implements ValidExecutionTrace {
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
	public void setMessageTrace(MessageTrace newMessageTrace) {
		MessageTrace oldMessageTrace = messageTrace;
		messageTrace = newMessageTrace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, oldMessageTrace, messageTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
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
			case UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE:
				return messageTrace != null;
		}
		return super.eIsSet(featureID);
	}

} //ValidExecutionTraceImpl
