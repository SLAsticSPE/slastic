/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring.impl;

import kieker.tools.slastic.metamodel.monitoring.CPUUtilization;
import kieker.tools.slastic.metamodel.monitoring.MonitoringPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CPU Utilization</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getUser <em>User</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getSystem <em>System</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getWait <em>Wait</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getNice <em>Nice</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getIrq <em>Irq</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getCombined <em>Combined</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.monitoring.impl.CPUUtilizationImpl#getIdle <em>Idle</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CPUUtilizationImpl extends ResourceMeasurementImpl implements CPUUtilization {
	/**
	 * The default value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected static final double USER_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getUser() <em>User</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUser()
	 * @generated
	 * @ordered
	 */
	protected double user = USER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected static final double SYSTEM_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSystem() <em>System</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSystem()
	 * @generated
	 * @ordered
	 */
	protected double system = SYSTEM_EDEFAULT;

	/**
	 * The default value of the '{@link #getWait() <em>Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWait()
	 * @generated
	 * @ordered
	 */
	protected static final double WAIT_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWait() <em>Wait</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWait()
	 * @generated
	 * @ordered
	 */
	protected double wait = WAIT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNice() <em>Nice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNice()
	 * @generated
	 * @ordered
	 */
	protected static final double NICE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getNice() <em>Nice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNice()
	 * @generated
	 * @ordered
	 */
	protected double nice = NICE_EDEFAULT;

	/**
	 * The default value of the '{@link #getIrq() <em>Irq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIrq()
	 * @generated
	 * @ordered
	 */
	protected static final double IRQ_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getIrq() <em>Irq</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIrq()
	 * @generated
	 * @ordered
	 */
	protected double irq = IRQ_EDEFAULT;

	/**
	 * The default value of the '{@link #getCombined() <em>Combined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombined()
	 * @generated
	 * @ordered
	 */
	protected static final double COMBINED_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCombined() <em>Combined</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombined()
	 * @generated
	 * @ordered
	 */
	protected double combined = COMBINED_EDEFAULT;

	/**
	 * The default value of the '{@link #getIdle() <em>Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdle()
	 * @generated
	 * @ordered
	 */
	protected static final double IDLE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getIdle() <em>Idle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdle()
	 * @generated
	 * @ordered
	 */
	protected double idle = IDLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CPUUtilizationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MonitoringPackage.Literals.CPU_UTILIZATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getUser() {
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUser(double newUser) {
		double oldUser = user;
		user = newUser;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__USER, oldUser, user));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSystem() {
		return system;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSystem(double newSystem) {
		double oldSystem = system;
		system = newSystem;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__SYSTEM, oldSystem, system));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWait() {
		return wait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWait(double newWait) {
		double oldWait = wait;
		wait = newWait;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__WAIT, oldWait, wait));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getNice() {
		return nice;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNice(double newNice) {
		double oldNice = nice;
		nice = newNice;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__NICE, oldNice, nice));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getIrq() {
		return irq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIrq(double newIrq) {
		double oldIrq = irq;
		irq = newIrq;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__IRQ, oldIrq, irq));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCombined() {
		return combined;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCombined(double newCombined) {
		double oldCombined = combined;
		combined = newCombined;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__COMBINED, oldCombined, combined));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getIdle() {
		return idle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdle(double newIdle) {
		double oldIdle = idle;
		idle = newIdle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MonitoringPackage.CPU_UTILIZATION__IDLE, oldIdle, idle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MonitoringPackage.CPU_UTILIZATION__USER:
				return getUser();
			case MonitoringPackage.CPU_UTILIZATION__SYSTEM:
				return getSystem();
			case MonitoringPackage.CPU_UTILIZATION__WAIT:
				return getWait();
			case MonitoringPackage.CPU_UTILIZATION__NICE:
				return getNice();
			case MonitoringPackage.CPU_UTILIZATION__IRQ:
				return getIrq();
			case MonitoringPackage.CPU_UTILIZATION__COMBINED:
				return getCombined();
			case MonitoringPackage.CPU_UTILIZATION__IDLE:
				return getIdle();
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
			case MonitoringPackage.CPU_UTILIZATION__USER:
				setUser((Double)newValue);
				return;
			case MonitoringPackage.CPU_UTILIZATION__SYSTEM:
				setSystem((Double)newValue);
				return;
			case MonitoringPackage.CPU_UTILIZATION__WAIT:
				setWait((Double)newValue);
				return;
			case MonitoringPackage.CPU_UTILIZATION__NICE:
				setNice((Double)newValue);
				return;
			case MonitoringPackage.CPU_UTILIZATION__IRQ:
				setIrq((Double)newValue);
				return;
			case MonitoringPackage.CPU_UTILIZATION__COMBINED:
				setCombined((Double)newValue);
				return;
			case MonitoringPackage.CPU_UTILIZATION__IDLE:
				setIdle((Double)newValue);
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
			case MonitoringPackage.CPU_UTILIZATION__USER:
				setUser(USER_EDEFAULT);
				return;
			case MonitoringPackage.CPU_UTILIZATION__SYSTEM:
				setSystem(SYSTEM_EDEFAULT);
				return;
			case MonitoringPackage.CPU_UTILIZATION__WAIT:
				setWait(WAIT_EDEFAULT);
				return;
			case MonitoringPackage.CPU_UTILIZATION__NICE:
				setNice(NICE_EDEFAULT);
				return;
			case MonitoringPackage.CPU_UTILIZATION__IRQ:
				setIrq(IRQ_EDEFAULT);
				return;
			case MonitoringPackage.CPU_UTILIZATION__COMBINED:
				setCombined(COMBINED_EDEFAULT);
				return;
			case MonitoringPackage.CPU_UTILIZATION__IDLE:
				setIdle(IDLE_EDEFAULT);
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
			case MonitoringPackage.CPU_UTILIZATION__USER:
				return user != USER_EDEFAULT;
			case MonitoringPackage.CPU_UTILIZATION__SYSTEM:
				return system != SYSTEM_EDEFAULT;
			case MonitoringPackage.CPU_UTILIZATION__WAIT:
				return wait != WAIT_EDEFAULT;
			case MonitoringPackage.CPU_UTILIZATION__NICE:
				return nice != NICE_EDEFAULT;
			case MonitoringPackage.CPU_UTILIZATION__IRQ:
				return irq != IRQ_EDEFAULT;
			case MonitoringPackage.CPU_UTILIZATION__COMBINED:
				return combined != COMBINED_EDEFAULT;
			case MonitoringPackage.CPU_UTILIZATION__IDLE:
				return idle != IDLE_EDEFAULT;
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
		result.append(" (user: ");
		result.append(user);
		result.append(", system: ");
		result.append(system);
		result.append(", wait: ");
		result.append(wait);
		result.append(", nice: ");
		result.append(nice);
		result.append(", irq: ");
		result.append(irq);
		result.append(", combined: ");
		result.append(combined);
		result.append(", idle: ");
		result.append(idle);
		result.append(')');
		return result.toString();
	}

} //CPUUtilizationImpl
