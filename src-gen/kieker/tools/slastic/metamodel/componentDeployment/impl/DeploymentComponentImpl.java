/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.componentDeployment.impl;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;

import kieker.tools.slastic.metamodel.componentDeployment.ComponentDeploymentPackage;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;

import kieker.tools.slastic.metamodel.core.impl.EntityImpl;

import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link kieker.tools.slastic.metamodel.componentDeployment.impl.DeploymentComponentImpl#getAssemblyComponent <em>Assembly Component</em>}</li>
 *   <li>{@link kieker.tools.slastic.metamodel.componentDeployment.impl.DeploymentComponentImpl#getExecutionContainer <em>Execution Container</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeploymentComponentImpl extends EntityImpl implements DeploymentComponent {
	/**
	 * The cached value of the '{@link #getAssemblyComponent() <em>Assembly Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyComponent()
	 * @generated
	 * @ordered
	 */
	protected AssemblyComponent assemblyComponent;

	/**
	 * The cached value of the '{@link #getExecutionContainer() <em>Execution Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionContainer()
	 * @generated
	 * @ordered
	 */
	protected ExecutionContainer executionContainer;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentDeploymentPackage.Literals.DEPLOYMENT_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent getAssemblyComponent() {
		if (assemblyComponent != null && assemblyComponent.eIsProxy()) {
			InternalEObject oldAssemblyComponent = (InternalEObject)assemblyComponent;
			assemblyComponent = (AssemblyComponent)eResolveProxy(oldAssemblyComponent);
			if (assemblyComponent != oldAssemblyComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT, oldAssemblyComponent, assemblyComponent));
			}
		}
		return assemblyComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssemblyComponent basicGetAssemblyComponent() {
		return assemblyComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssemblyComponent(AssemblyComponent newAssemblyComponent) {
		AssemblyComponent oldAssemblyComponent = assemblyComponent;
		assemblyComponent = newAssemblyComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT, oldAssemblyComponent, assemblyComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainer getExecutionContainer() {
		if (executionContainer != null && executionContainer.eIsProxy()) {
			InternalEObject oldExecutionContainer = (InternalEObject)executionContainer;
			executionContainer = (ExecutionContainer)eResolveProxy(oldExecutionContainer);
			if (executionContainer != oldExecutionContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER, oldExecutionContainer, executionContainer));
			}
		}
		return executionContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContainer basicGetExecutionContainer() {
		return executionContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionContainer(ExecutionContainer newExecutionContainer) {
		ExecutionContainer oldExecutionContainer = executionContainer;
		executionContainer = newExecutionContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER, oldExecutionContainer, executionContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT:
				if (resolve) return getAssemblyComponent();
				return basicGetAssemblyComponent();
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER:
				if (resolve) return getExecutionContainer();
				return basicGetExecutionContainer();
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
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT:
				setAssemblyComponent((AssemblyComponent)newValue);
				return;
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER:
				setExecutionContainer((ExecutionContainer)newValue);
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
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT:
				setAssemblyComponent((AssemblyComponent)null);
				return;
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER:
				setExecutionContainer((ExecutionContainer)null);
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
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__ASSEMBLY_COMPONENT:
				return assemblyComponent != null;
			case ComponentDeploymentPackage.DEPLOYMENT_COMPONENT__EXECUTION_CONTAINER:
				return executionContainer != null;
		}
		return super.eIsSet(featureID);
	}

} //DeploymentComponentImpl
