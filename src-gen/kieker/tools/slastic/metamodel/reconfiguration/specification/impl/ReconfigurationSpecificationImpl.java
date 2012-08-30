/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.reconfiguration.specification.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationModel;
import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;

import kieker.tools.slastic.metamodel.reconfiguration.specification.ReconfigurationSpecification;
import kieker.tools.slastic.metamodel.reconfiguration.specification.SpecificationPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reconfiguration Specification</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.reconfiguration.specification.impl.ReconfigurationSpecificationImpl#getAdaptationModel <em>Adaptation Model</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReconfigurationSpecificationImpl extends EObjectImpl implements ReconfigurationSpecification {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReconfigurationSpecificationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SpecificationPackage.Literals.RECONFIGURATION_SPECIFICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationModel getAdaptationModel() {
		if (eContainerFeatureID() != SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL) return null;
		return (AdaptationModel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdaptationModel(AdaptationModel newAdaptationModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAdaptationModel, SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdaptationModel(AdaptationModel newAdaptationModel) {
		if (newAdaptationModel != eInternalContainer() || (eContainerFeatureID() != SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL && newAdaptationModel != null)) {
			if (EcoreUtil.isAncestor(this, newAdaptationModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAdaptationModel != null)
				msgs = ((InternalEObject)newAdaptationModel).eInverseAdd(this, AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION, AdaptationModel.class, msgs);
			msgs = basicSetAdaptationModel(newAdaptationModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL, newAdaptationModel, newAdaptationModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAdaptationModel((AdaptationModel)otherEnd, msgs);
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
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				return basicSetAdaptationModel(null, msgs);
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
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				return eInternalContainer().eInverseRemove(this, AdaptationPackage.ADAPTATION_MODEL__RECONFIGURATION_SPECIFICATION, AdaptationModel.class, msgs);
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
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				return getAdaptationModel();
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
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				setAdaptationModel((AdaptationModel)newValue);
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
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				setAdaptationModel((AdaptationModel)null);
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
			case SpecificationPackage.RECONFIGURATION_SPECIFICATION__ADAPTATION_MODEL:
				return getAdaptationModel() != null;
		}
		return super.eIsSet(featureID);
	}

} //ReconfigurationSpecificationImpl
