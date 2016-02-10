/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.adaptation.impl;

import kieker.tools.slastic.metamodel.adaptation.AdaptationModel;
import kieker.tools.slastic.metamodel.adaptation.AdaptationPackage;
import kieker.tools.slastic.metamodel.adaptation.AdaptationPlanning;
import kieker.tools.slastic.metamodel.adaptation.Control;
import kieker.tools.slastic.metamodel.adaptation.PerformanceEvaluation;
import kieker.tools.slastic.metamodel.adaptation.PerformancePrediction;
import kieker.tools.slastic.metamodel.adaptation.WorkloadForecasting;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Control</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.impl.ControlImpl#getAdaptationModel <em>Adaptation Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.impl.ControlImpl#getPerformanceEvaluator <em>Performance Evaluator</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.impl.ControlImpl#getWorkloadForecaster <em>Workload Forecaster</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.impl.ControlImpl#getPerformancePredictor <em>Performance Predictor</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.adaptation.impl.ControlImpl#getAdaptationPlanner <em>Adaptation Planner</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ControlImpl extends EObjectImpl implements Control {
	/**
	 * The cached value of the '{@link #getPerformanceEvaluator() <em>Performance Evaluator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformanceEvaluator()
	 * @generated
	 * @ordered
	 */
	protected PerformanceEvaluation performanceEvaluator;

	/**
	 * The cached value of the '{@link #getWorkloadForecaster() <em>Workload Forecaster</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkloadForecaster()
	 * @generated
	 * @ordered
	 */
	protected WorkloadForecasting workloadForecaster;

	/**
	 * The cached value of the '{@link #getPerformancePredictor() <em>Performance Predictor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformancePredictor()
	 * @generated
	 * @ordered
	 */
	protected PerformancePrediction performancePredictor;

	/**
	 * The cached value of the '{@link #getAdaptationPlanner() <em>Adaptation Planner</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptationPlanner()
	 * @generated
	 * @ordered
	 */
	protected AdaptationPlanning adaptationPlanner;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControlImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AdaptationPackage.Literals.CONTROL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationModel getAdaptationModel() {
		if (eContainerFeatureID() != AdaptationPackage.CONTROL__ADAPTATION_MODEL) return null;
		return (AdaptationModel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdaptationModel(AdaptationModel newAdaptationModel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAdaptationModel, AdaptationPackage.CONTROL__ADAPTATION_MODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdaptationModel(AdaptationModel newAdaptationModel) {
		if (newAdaptationModel != eInternalContainer() || (eContainerFeatureID() != AdaptationPackage.CONTROL__ADAPTATION_MODEL && newAdaptationModel != null)) {
			if (EcoreUtil.isAncestor(this, newAdaptationModel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAdaptationModel != null)
				msgs = ((InternalEObject)newAdaptationModel).eInverseAdd(this, AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL, AdaptationModel.class, msgs);
			msgs = basicSetAdaptationModel(newAdaptationModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__ADAPTATION_MODEL, newAdaptationModel, newAdaptationModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerformanceEvaluation getPerformanceEvaluator() {
		return performanceEvaluator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPerformanceEvaluator(PerformanceEvaluation newPerformanceEvaluator, NotificationChain msgs) {
		PerformanceEvaluation oldPerformanceEvaluator = performanceEvaluator;
		performanceEvaluator = newPerformanceEvaluator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR, oldPerformanceEvaluator, newPerformanceEvaluator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformanceEvaluator(PerformanceEvaluation newPerformanceEvaluator) {
		if (newPerformanceEvaluator != performanceEvaluator) {
			NotificationChain msgs = null;
			if (performanceEvaluator != null)
				msgs = ((InternalEObject)performanceEvaluator).eInverseRemove(this, AdaptationPackage.PERFORMANCE_EVALUATION__CONTROL, PerformanceEvaluation.class, msgs);
			if (newPerformanceEvaluator != null)
				msgs = ((InternalEObject)newPerformanceEvaluator).eInverseAdd(this, AdaptationPackage.PERFORMANCE_EVALUATION__CONTROL, PerformanceEvaluation.class, msgs);
			msgs = basicSetPerformanceEvaluator(newPerformanceEvaluator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR, newPerformanceEvaluator, newPerformanceEvaluator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkloadForecasting getWorkloadForecaster() {
		return workloadForecaster;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkloadForecaster(WorkloadForecasting newWorkloadForecaster, NotificationChain msgs) {
		WorkloadForecasting oldWorkloadForecaster = workloadForecaster;
		workloadForecaster = newWorkloadForecaster;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__WORKLOAD_FORECASTER, oldWorkloadForecaster, newWorkloadForecaster);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkloadForecaster(WorkloadForecasting newWorkloadForecaster) {
		if (newWorkloadForecaster != workloadForecaster) {
			NotificationChain msgs = null;
			if (workloadForecaster != null)
				msgs = ((InternalEObject)workloadForecaster).eInverseRemove(this, AdaptationPackage.WORKLOAD_FORECASTING__CONTROL, WorkloadForecasting.class, msgs);
			if (newWorkloadForecaster != null)
				msgs = ((InternalEObject)newWorkloadForecaster).eInverseAdd(this, AdaptationPackage.WORKLOAD_FORECASTING__CONTROL, WorkloadForecasting.class, msgs);
			msgs = basicSetWorkloadForecaster(newWorkloadForecaster, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__WORKLOAD_FORECASTER, newWorkloadForecaster, newWorkloadForecaster));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerformancePrediction getPerformancePredictor() {
		return performancePredictor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPerformancePredictor(PerformancePrediction newPerformancePredictor, NotificationChain msgs) {
		PerformancePrediction oldPerformancePredictor = performancePredictor;
		performancePredictor = newPerformancePredictor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR, oldPerformancePredictor, newPerformancePredictor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformancePredictor(PerformancePrediction newPerformancePredictor) {
		if (newPerformancePredictor != performancePredictor) {
			NotificationChain msgs = null;
			if (performancePredictor != null)
				msgs = ((InternalEObject)performancePredictor).eInverseRemove(this, AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL, PerformancePrediction.class, msgs);
			if (newPerformancePredictor != null)
				msgs = ((InternalEObject)newPerformancePredictor).eInverseAdd(this, AdaptationPackage.PERFORMANCE_PREDICTION__CONTROL, PerformancePrediction.class, msgs);
			msgs = basicSetPerformancePredictor(newPerformancePredictor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR, newPerformancePredictor, newPerformancePredictor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdaptationPlanning getAdaptationPlanner() {
		return adaptationPlanner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAdaptationPlanner(AdaptationPlanning newAdaptationPlanner, NotificationChain msgs) {
		AdaptationPlanning oldAdaptationPlanner = adaptationPlanner;
		adaptationPlanner = newAdaptationPlanner;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__ADAPTATION_PLANNER, oldAdaptationPlanner, newAdaptationPlanner);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAdaptationPlanner(AdaptationPlanning newAdaptationPlanner) {
		if (newAdaptationPlanner != adaptationPlanner) {
			NotificationChain msgs = null;
			if (adaptationPlanner != null)
				msgs = ((InternalEObject)adaptationPlanner).eInverseRemove(this, AdaptationPackage.ADAPTATION_PLANNING__CONTROL, AdaptationPlanning.class, msgs);
			if (newAdaptationPlanner != null)
				msgs = ((InternalEObject)newAdaptationPlanner).eInverseAdd(this, AdaptationPackage.ADAPTATION_PLANNING__CONTROL, AdaptationPlanning.class, msgs);
			msgs = basicSetAdaptationPlanner(newAdaptationPlanner, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AdaptationPackage.CONTROL__ADAPTATION_PLANNER, newAdaptationPlanner, newAdaptationPlanner));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAdaptationModel((AdaptationModel)otherEnd, msgs);
			case AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR:
				if (performanceEvaluator != null)
					msgs = ((InternalEObject)performanceEvaluator).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR, null, msgs);
				return basicSetPerformanceEvaluator((PerformanceEvaluation)otherEnd, msgs);
			case AdaptationPackage.CONTROL__WORKLOAD_FORECASTER:
				if (workloadForecaster != null)
					msgs = ((InternalEObject)workloadForecaster).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.CONTROL__WORKLOAD_FORECASTER, null, msgs);
				return basicSetWorkloadForecaster((WorkloadForecasting)otherEnd, msgs);
			case AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR:
				if (performancePredictor != null)
					msgs = ((InternalEObject)performancePredictor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR, null, msgs);
				return basicSetPerformancePredictor((PerformancePrediction)otherEnd, msgs);
			case AdaptationPackage.CONTROL__ADAPTATION_PLANNER:
				if (adaptationPlanner != null)
					msgs = ((InternalEObject)adaptationPlanner).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - AdaptationPackage.CONTROL__ADAPTATION_PLANNER, null, msgs);
				return basicSetAdaptationPlanner((AdaptationPlanning)otherEnd, msgs);
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
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				return basicSetAdaptationModel(null, msgs);
			case AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR:
				return basicSetPerformanceEvaluator(null, msgs);
			case AdaptationPackage.CONTROL__WORKLOAD_FORECASTER:
				return basicSetWorkloadForecaster(null, msgs);
			case AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR:
				return basicSetPerformancePredictor(null, msgs);
			case AdaptationPackage.CONTROL__ADAPTATION_PLANNER:
				return basicSetAdaptationPlanner(null, msgs);
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
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				return eInternalContainer().eInverseRemove(this, AdaptationPackage.ADAPTATION_MODEL__CONTROL_MODEL, AdaptationModel.class, msgs);
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
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				return getAdaptationModel();
			case AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR:
				return getPerformanceEvaluator();
			case AdaptationPackage.CONTROL__WORKLOAD_FORECASTER:
				return getWorkloadForecaster();
			case AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR:
				return getPerformancePredictor();
			case AdaptationPackage.CONTROL__ADAPTATION_PLANNER:
				return getAdaptationPlanner();
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
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				setAdaptationModel((AdaptationModel)newValue);
				return;
			case AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR:
				setPerformanceEvaluator((PerformanceEvaluation)newValue);
				return;
			case AdaptationPackage.CONTROL__WORKLOAD_FORECASTER:
				setWorkloadForecaster((WorkloadForecasting)newValue);
				return;
			case AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR:
				setPerformancePredictor((PerformancePrediction)newValue);
				return;
			case AdaptationPackage.CONTROL__ADAPTATION_PLANNER:
				setAdaptationPlanner((AdaptationPlanning)newValue);
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
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				setAdaptationModel((AdaptationModel)null);
				return;
			case AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR:
				setPerformanceEvaluator((PerformanceEvaluation)null);
				return;
			case AdaptationPackage.CONTROL__WORKLOAD_FORECASTER:
				setWorkloadForecaster((WorkloadForecasting)null);
				return;
			case AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR:
				setPerformancePredictor((PerformancePrediction)null);
				return;
			case AdaptationPackage.CONTROL__ADAPTATION_PLANNER:
				setAdaptationPlanner((AdaptationPlanning)null);
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
			case AdaptationPackage.CONTROL__ADAPTATION_MODEL:
				return getAdaptationModel() != null;
			case AdaptationPackage.CONTROL__PERFORMANCE_EVALUATOR:
				return performanceEvaluator != null;
			case AdaptationPackage.CONTROL__WORKLOAD_FORECASTER:
				return workloadForecaster != null;
			case AdaptationPackage.CONTROL__PERFORMANCE_PREDICTOR:
				return performancePredictor != null;
			case AdaptationPackage.CONTROL__ADAPTATION_PLANNER:
				return adaptationPlanner != null;
		}
		return super.eIsSet(featureID);
	}

} //ControlImpl
