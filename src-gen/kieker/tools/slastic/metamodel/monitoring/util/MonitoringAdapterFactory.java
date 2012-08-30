/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package kieker.tools.slastic.metamodel.monitoring.util;

import kieker.tools.slastic.metamodel.core.IEvent;
import kieker.tools.slastic.metamodel.core.KiekerAnalysisEvent;

import kieker.tools.slastic.metamodel.monitoring.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see kieker.tools.slastic.metamodel.monitoring.MonitoringPackage
 * @generated
 */
public class MonitoringAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static MonitoringPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MonitoringAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = MonitoringPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MonitoringSwitch<Adapter> modelSwitch =
		new MonitoringSwitch<Adapter>() {
			@Override
			public Adapter caseOperationExecution(OperationExecution object) {
				return createOperationExecutionAdapter();
			}
			@Override
			public Adapter caseDeploymentComponentOperationExecution(DeploymentComponentOperationExecution object) {
				return createDeploymentComponentOperationExecutionAdapter();
			}
			@Override
			public Adapter caseConnectorOperationExecution(ConnectorOperationExecution object) {
				return createConnectorOperationExecutionAdapter();
			}
			@Override
			public Adapter caseResourceUtilization(ResourceUtilization object) {
				return createResourceUtilizationAdapter();
			}
			@Override
			public Adapter caseResourceMeasurement(ResourceMeasurement object) {
				return createResourceMeasurementAdapter();
			}
			@Override
			public Adapter caseMemSwapUsage(MemSwapUsage object) {
				return createMemSwapUsageAdapter();
			}
			@Override
			public Adapter caseCPUUtilization(CPUUtilization object) {
				return createCPUUtilizationAdapter();
			}
			@Override
			public Adapter caseKiekerAnalysisEvent(KiekerAnalysisEvent object) {
				return createKiekerAnalysisEventAdapter();
			}
			@Override
			public Adapter caseIEvent(IEvent object) {
				return createIEventAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.OperationExecution <em>Operation Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.OperationExecution
	 * @generated
	 */
	public Adapter createOperationExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution <em>Deployment Component Operation Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution
	 * @generated
	 */
	public Adapter createDeploymentComponentOperationExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution <em>Connector Operation Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution
	 * @generated
	 */
	public Adapter createConnectorOperationExecutionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.ResourceUtilization <em>Resource Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.ResourceUtilization
	 * @generated
	 */
	public Adapter createResourceUtilizationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.ResourceMeasurement <em>Resource Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.ResourceMeasurement
	 * @generated
	 */
	public Adapter createResourceMeasurementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.MemSwapUsage <em>Mem Swap Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.MemSwapUsage
	 * @generated
	 */
	public Adapter createMemSwapUsageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.monitoring.CPUUtilization <em>CPU Utilization</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.monitoring.CPUUtilization
	 * @generated
	 */
	public Adapter createCPUUtilizationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.core.KiekerAnalysisEvent <em>Kieker Analysis Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.core.KiekerAnalysisEvent
	 * @generated
	 */
	public Adapter createKiekerAnalysisEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link kieker.tools.slastic.metamodel.core.IEvent <em>IEvent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see kieker.tools.slastic.metamodel.core.IEvent
	 * @generated
	 */
	public Adapter createIEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //MonitoringAdapterFactory
