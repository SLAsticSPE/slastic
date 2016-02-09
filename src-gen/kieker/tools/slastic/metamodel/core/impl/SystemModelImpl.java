/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.core.impl;

import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel;

import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentModel;

import kieker.tools.slastic.metamodel.core.CorePackage;
import kieker.tools.slastic.metamodel.core.SystemModel;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionEnvironmentModel;

import kieker.tools.slastic.metamodel.typeRepository.TypeRepositoryModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>System Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.core.impl.SystemModelImpl#getTypeRepositoryModel <em>Type Repository Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.core.impl.SystemModelImpl#getComponentAssemblyModel <em>Component Assembly Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.core.impl.SystemModelImpl#getComponentDeploymentModel <em>Component Deployment Model</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.core.impl.SystemModelImpl#getExecutionEnvironmentModel <em>Execution Environment Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SystemModelImpl extends EObjectImpl implements SystemModel {
	/**
	 * The cached value of the '{@link #getTypeRepositoryModel() <em>Type Repository Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeRepositoryModel()
	 * @generated
	 * @ordered
	 */
	protected TypeRepositoryModel typeRepositoryModel;

	/**
	 * The cached value of the '{@link #getComponentAssemblyModel() <em>Component Assembly Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentAssemblyModel()
	 * @generated
	 * @ordered
	 */
	protected ComponentAssemblyModel componentAssemblyModel;

	/**
	 * The cached value of the '{@link #getComponentDeploymentModel() <em>Component Deployment Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentDeploymentModel()
	 * @generated
	 * @ordered
	 */
	protected ComponentDeploymentModel componentDeploymentModel;

	/**
	 * The cached value of the '{@link #getExecutionEnvironmentModel() <em>Execution Environment Model</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionEnvironmentModel()
	 * @generated
	 * @ordered
	 */
	protected ExecutionEnvironmentModel executionEnvironmentModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SystemModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.SYSTEM_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeRepositoryModel getTypeRepositoryModel() {
		return typeRepositoryModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeRepositoryModel(TypeRepositoryModel newTypeRepositoryModel, NotificationChain msgs) {
		TypeRepositoryModel oldTypeRepositoryModel = typeRepositoryModel;
		typeRepositoryModel = newTypeRepositoryModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL, oldTypeRepositoryModel, newTypeRepositoryModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeRepositoryModel(TypeRepositoryModel newTypeRepositoryModel) {
		if (newTypeRepositoryModel != typeRepositoryModel) {
			NotificationChain msgs = null;
			if (typeRepositoryModel != null)
				msgs = ((InternalEObject)typeRepositoryModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL, null, msgs);
			if (newTypeRepositoryModel != null)
				msgs = ((InternalEObject)newTypeRepositoryModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL, null, msgs);
			msgs = basicSetTypeRepositoryModel(newTypeRepositoryModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL, newTypeRepositoryModel, newTypeRepositoryModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentAssemblyModel getComponentAssemblyModel() {
		return componentAssemblyModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentAssemblyModel(ComponentAssemblyModel newComponentAssemblyModel, NotificationChain msgs) {
		ComponentAssemblyModel oldComponentAssemblyModel = componentAssemblyModel;
		componentAssemblyModel = newComponentAssemblyModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL, oldComponentAssemblyModel, newComponentAssemblyModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentAssemblyModel(ComponentAssemblyModel newComponentAssemblyModel) {
		if (newComponentAssemblyModel != componentAssemblyModel) {
			NotificationChain msgs = null;
			if (componentAssemblyModel != null)
				msgs = ((InternalEObject)componentAssemblyModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL, null, msgs);
			if (newComponentAssemblyModel != null)
				msgs = ((InternalEObject)newComponentAssemblyModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL, null, msgs);
			msgs = basicSetComponentAssemblyModel(newComponentAssemblyModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL, newComponentAssemblyModel, newComponentAssemblyModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentDeploymentModel getComponentDeploymentModel() {
		return componentDeploymentModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComponentDeploymentModel(ComponentDeploymentModel newComponentDeploymentModel, NotificationChain msgs) {
		ComponentDeploymentModel oldComponentDeploymentModel = componentDeploymentModel;
		componentDeploymentModel = newComponentDeploymentModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL, oldComponentDeploymentModel, newComponentDeploymentModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentDeploymentModel(ComponentDeploymentModel newComponentDeploymentModel) {
		if (newComponentDeploymentModel != componentDeploymentModel) {
			NotificationChain msgs = null;
			if (componentDeploymentModel != null)
				msgs = ((InternalEObject)componentDeploymentModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL, null, msgs);
			if (newComponentDeploymentModel != null)
				msgs = ((InternalEObject)newComponentDeploymentModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL, null, msgs);
			msgs = basicSetComponentDeploymentModel(newComponentDeploymentModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL, newComponentDeploymentModel, newComponentDeploymentModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionEnvironmentModel getExecutionEnvironmentModel() {
		return executionEnvironmentModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutionEnvironmentModel(ExecutionEnvironmentModel newExecutionEnvironmentModel, NotificationChain msgs) {
		ExecutionEnvironmentModel oldExecutionEnvironmentModel = executionEnvironmentModel;
		executionEnvironmentModel = newExecutionEnvironmentModel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL, oldExecutionEnvironmentModel, newExecutionEnvironmentModel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionEnvironmentModel(ExecutionEnvironmentModel newExecutionEnvironmentModel) {
		if (newExecutionEnvironmentModel != executionEnvironmentModel) {
			NotificationChain msgs = null;
			if (executionEnvironmentModel != null)
				msgs = ((InternalEObject)executionEnvironmentModel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL, null, msgs);
			if (newExecutionEnvironmentModel != null)
				msgs = ((InternalEObject)newExecutionEnvironmentModel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL, null, msgs);
			msgs = basicSetExecutionEnvironmentModel(newExecutionEnvironmentModel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL, newExecutionEnvironmentModel, newExecutionEnvironmentModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL:
				return basicSetTypeRepositoryModel(null, msgs);
			case CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL:
				return basicSetComponentAssemblyModel(null, msgs);
			case CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL:
				return basicSetComponentDeploymentModel(null, msgs);
			case CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL:
				return basicSetExecutionEnvironmentModel(null, msgs);
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
			case CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL:
				return getTypeRepositoryModel();
			case CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL:
				return getComponentAssemblyModel();
			case CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL:
				return getComponentDeploymentModel();
			case CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL:
				return getExecutionEnvironmentModel();
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
			case CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL:
				setTypeRepositoryModel((TypeRepositoryModel)newValue);
				return;
			case CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL:
				setComponentAssemblyModel((ComponentAssemblyModel)newValue);
				return;
			case CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL:
				setComponentDeploymentModel((ComponentDeploymentModel)newValue);
				return;
			case CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL:
				setExecutionEnvironmentModel((ExecutionEnvironmentModel)newValue);
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
			case CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL:
				setTypeRepositoryModel((TypeRepositoryModel)null);
				return;
			case CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL:
				setComponentAssemblyModel((ComponentAssemblyModel)null);
				return;
			case CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL:
				setComponentDeploymentModel((ComponentDeploymentModel)null);
				return;
			case CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL:
				setExecutionEnvironmentModel((ExecutionEnvironmentModel)null);
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
			case CorePackage.SYSTEM_MODEL__TYPE_REPOSITORY_MODEL:
				return typeRepositoryModel != null;
			case CorePackage.SYSTEM_MODEL__COMPONENT_ASSEMBLY_MODEL:
				return componentAssemblyModel != null;
			case CorePackage.SYSTEM_MODEL__COMPONENT_DEPLOYMENT_MODEL:
				return componentDeploymentModel != null;
			case CorePackage.SYSTEM_MODEL__EXECUTION_ENVIRONMENT_MODEL:
				return executionEnvironmentModel != null;
		}
		return super.eIsSet(featureID);
	}

} //SystemModelImpl
