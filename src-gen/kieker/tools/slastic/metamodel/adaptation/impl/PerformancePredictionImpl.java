/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.adaptation.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;
import kieker.tools.slastic.metamodel.adaptation.Control;
import kieker.tools.slastic.metamodel.adaptation.PerformancePrediction;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Performance Prediction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.impl.PerformancePredictionImpl#getControl <em>Control</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PerformancePredictionImpl extends EObjectImpl implements PerformancePrediction {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PerformancePredictionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdaptationPackage.Literals.PERFORMANCE_PREDICTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Control getControl() {
		if (eContainerFeatureID() != AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL) return null;
		return (Control)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetControl(Control newControl, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newControl, AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setControl(Control newControl) {
		if (newControl != eInternalContainer() || (eContainerFeatureID() != AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL && newControl != null)) {
			if (EcoreUtil.isAncestor(this, newControl))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newControl != null)
				msgs = ((InternalEObject)newControl).eInverseAdd(this, AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR, Control.class, msgs);
			msgs = basicSetControl(newControl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL, newControl, newControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetControl((Control)otherEnd, msgs);
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
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				return basicSetControl(null, msgs);
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
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				return eInternalContainer().eInverseRemove(this, AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR, Control.class, msgs);
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
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				return getControl();
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
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				setControl((Control)newValue);
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
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				setControl((Control)null);
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
			case AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL:
				return getControl() != null;
		}
		return super.eIsSet(featureID);
	}

} //PerformancePredictionImpl
