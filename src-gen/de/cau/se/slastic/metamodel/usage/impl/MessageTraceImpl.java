/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.cau.se.slastic.metamodel.usage.impl;

import de.cau.se.slastic.metamodel.usage.Message;
import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.UsagePackage;

import de.cau.se.slastic.metamodel.usage.ValidExecutionTrace;
import java.util.Collection;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message Trace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.MessageTraceImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link de.cau.se.slastic.metamodel.usage.impl.MessageTraceImpl#getExecutionTrace <em>Execution Trace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MessageTraceImpl extends ValidTraceImpl implements MessageTrace {
	/**
	 * The cached value of the '{@link #getMessages() <em>Messages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<Message> messages;

	/**
	 * The cached value of the '{@link #getExecutionTrace() <em>Execution Trace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionTrace()
	 * @generated
	 * @ordered
	 */
	protected ValidExecutionTrace executionTrace;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageTraceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UsagePackage.Literals.MESSAGE_TRACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Message> getMessages() {
		if (messages == null) {
			messages = new EObjectResolvingEList<Message>(Message.class, this, UsagePackage.MESSAGE_TRACE__MESSAGES);
		}
		return messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidExecutionTrace getExecutionTrace() {
		if (executionTrace != null && executionTrace.eIsProxy()) {
			InternalEObject oldExecutionTrace = (InternalEObject)executionTrace;
			executionTrace = (ValidExecutionTrace)eResolveProxy(oldExecutionTrace);
			if (executionTrace != oldExecutionTrace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE, oldExecutionTrace, executionTrace));
			}
		}
		return executionTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidExecutionTrace basicGetExecutionTrace() {
		return executionTrace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutionTrace(ValidExecutionTrace newExecutionTrace, NotificationChain msgs) {
		ValidExecutionTrace oldExecutionTrace = executionTrace;
		executionTrace = newExecutionTrace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE, oldExecutionTrace, newExecutionTrace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionTrace(ValidExecutionTrace newExecutionTrace) {
		if (newExecutionTrace != executionTrace) {
			NotificationChain msgs = null;
			if (executionTrace != null)
				msgs = ((InternalEObject)executionTrace).eInverseRemove(this, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, ValidExecutionTrace.class, msgs);
			if (newExecutionTrace != null)
				msgs = ((InternalEObject)newExecutionTrace).eInverseAdd(this, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, ValidExecutionTrace.class, msgs);
			msgs = basicSetExecutionTrace(newExecutionTrace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE, newExecutionTrace, newExecutionTrace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE:
				if (executionTrace != null)
					msgs = ((InternalEObject)executionTrace).eInverseRemove(this, UsagePackage.VALID_EXECUTION_TRACE__MESSAGE_TRACE, ValidExecutionTrace.class, msgs);
				return basicSetExecutionTrace((ValidExecutionTrace)otherEnd, msgs);
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
			case UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE:
				return basicSetExecutionTrace(null, msgs);
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
			case UsagePackage.MESSAGE_TRACE__MESSAGES:
				return getMessages();
			case UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE:
				if (resolve) return getExecutionTrace();
				return basicGetExecutionTrace();
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
			case UsagePackage.MESSAGE_TRACE__MESSAGES:
				getMessages().clear();
				getMessages().addAll((Collection<? extends Message>)newValue);
				return;
			case UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE:
				setExecutionTrace((ValidExecutionTrace)newValue);
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
			case UsagePackage.MESSAGE_TRACE__MESSAGES:
				getMessages().clear();
				return;
			case UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE:
				setExecutionTrace((ValidExecutionTrace)null);
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
			case UsagePackage.MESSAGE_TRACE__MESSAGES:
				return messages != null && !messages.isEmpty();
			case UsagePackage.MESSAGE_TRACE__EXECUTION_TRACE:
				return executionTrace != null;
		}
		return super.eIsSet(featureID);
	}

} //MessageTraceImpl
