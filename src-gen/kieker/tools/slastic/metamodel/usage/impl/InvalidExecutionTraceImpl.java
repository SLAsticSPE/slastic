/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.usage.impl;

import kieker.tools.slastic.metamodel.usage.InvalidExecutionTrace;
import kieker.tools.slastic.metamodel.usage.UsagePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Invalid Execution Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.usage.impl.InvalidExecutionTraceImpl#getErrorMsg <em>Error Msg</em>}</li>
 * </ul>
 *
 * @generated
 */
public class InvalidExecutionTraceImpl extends ExecutionTraceImpl implements InvalidExecutionTrace {
	/**
	 * The default value of the '{@link #getErrorMsg() <em>Error Msg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorMsg()
	 * @generated
	 * @ordered
	 */
	protected static final String ERROR_MSG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getErrorMsg() <em>Error Msg</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getErrorMsg()
	 * @generated
	 * @ordered
	 */
	protected String errorMsg = ERROR_MSG_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected InvalidExecutionTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.INVALID_EXECUTION_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setErrorMsg(String newErrorMsg) {
		String oldErrorMsg = errorMsg;
		errorMsg = newErrorMsg;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.INVALID_EXECUTION_TRACE__ERROR_MSG, oldErrorMsg, errorMsg));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UsagePackage.INVALID_EXECUTION_TRACE__ERROR_MSG:
				return getErrorMsg();
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
			case UsagePackage.INVALID_EXECUTION_TRACE__ERROR_MSG:
				setErrorMsg((String)newValue);
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
			case UsagePackage.INVALID_EXECUTION_TRACE__ERROR_MSG:
				setErrorMsg(ERROR_MSG_EDEFAULT);
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
			case UsagePackage.INVALID_EXECUTION_TRACE__ERROR_MSG:
				return ERROR_MSG_EDEFAULT == null ? errorMsg != null : !ERROR_MSG_EDEFAULT.equals(errorMsg);
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
		result.append(" (errorMsg: ");
		result.append(errorMsg);
		result.append(')');
		return result.toString();
	}

} //InvalidExecutionTraceImpl
