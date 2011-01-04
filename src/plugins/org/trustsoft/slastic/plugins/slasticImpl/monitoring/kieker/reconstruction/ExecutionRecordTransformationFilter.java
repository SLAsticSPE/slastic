package org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.OperationExecutionRecord;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.filters.AbstractSynchronousTransformationFilter;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyConnector;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.core.IEvent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.monitoring.ConnectorOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.MonitoringFactory;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;

/**
 * 
 * @author Andre van Hoorn
 */
public class ExecutionRecordTransformationFilter extends
		AbstractSynchronousTransformationFilter implements
		IExecutionRecordTransformation {

	private static final Log log = LogFactory
			.getLog(ExecutionRecordTransformationFilter.class);

	/**
	 * 
	 * @param modelManager
	 */
	public ExecutionRecordTransformationFilter(final ModelManager modelManager) {
		super(modelManager);
	}

	/**
	 * <p>
	 * Creates a new OperationExecution instance and initializes it based on the
	 * given execution. Depending on the instrumentation model, the returned
	 * object is an instance of {@link DeploymentComponentOperationExecution} or
	 * {@link ConnectorOperationExecution}.
	 * </p>
	 * 
	 * <p>
	 * The strategy is as follows, given the {@link OperationExecutionRecord}
	 * object with {@link OperationExecutionRecord#className} parameter whose
	 * value equals classname:
	 * <ol>
	 * <li>If an {@link AssemblyConnector} with a full-qualified name that
	 * equals classname is registered, the execution is assumed to be a
	 * {@link ConnectorOperationExecution}. An instance referencing to the
	 * {@link AssemblyConnector} is created.</li>
	 * <li>Otherwise, the execution is assumed to be a
	 * {@link DeploymentComponentOperationExecution}. An instance referencing to
	 * an {@link AssemblyComponent} with the full-qualified name that equals
	 * classname is created; if the {@link AssemblyComponent} doesn't exist, it
	 * is being created.</li>
	 * <li>The execution's {@link ExecutionContainer} is determined accordingly:
	 * if a {@link ExecutionContainer} with the
	 * {@link OperationExecutionRecord#hostName} does not exist, it is being
	 * created.</li>
	 * </ol>
	 * </p>
	 * 
	 * @see DeploymentComponentOperationExecution
	 * @see ConnectorOperationExecution
	 * 
	 * @param execution
	 * @return
	 */
	@Override
	public OperationExecution transformExecutionRecord(final OperationExecutionRecord execution) {

		/* Will become the return value. */
		final OperationExecution newExecution;

		ExecutionContainer executionContainer;
		{
			/*
			 * Lookup execution container
			 */
			executionContainer =
					this.getExecutionEnvModelManager()
							.lookupExecutionContainer(execution.hostName);
			if (executionContainer == null) {
				/* We need to create the execution container */
				executionContainer =
						ModelEntityFactory.createExecutionContainer(
								this.getModelManager(), execution.hostName);
			}
		}

		{
			/*
			 * 1.) Determine whether this is a deployment component execution or
			 * a connector execution.
			 */
			final String componentOrConnectorName = execution.className;
			final AssemblyConnector assemblyConnector =
					this.getAssemblyModelManager().lookupAssemblyConnector(
							componentOrConnectorName);
			if (assemblyConnector != null) {
				/* Is a connector execution */
				final ConnectorOperationExecution newConnectorExec =
						MonitoringFactory.eINSTANCE
								.createConnectorOperationExecution();
				newConnectorExec.setAssemblyConnector(assemblyConnector);
				newConnectorExec.setExecutionContainer(executionContainer);
				newConnectorExec.setExecutionContainer(executionContainer);
				newExecution = newConnectorExec;
			} else {
				/* Is an execution of a component (existing or non-existing) */
				final DeploymentComponentOperationExecution newComponentExec =
						MonitoringFactory.eINSTANCE
								.createDeploymentComponentOperationExecution();
				/* Determine assembly component */
				AssemblyComponent assemblyComponent =
						this.getAssemblyModelManager().lookupAssemblyComponent(
								componentOrConnectorName);
				if (assemblyComponent == null) {
					/* We need to create the assembly component */
					assemblyComponent =
							ModelEntityFactory.createAssemblyComponent(
									this.getModelManager(),
									componentOrConnectorName);
				}
				/* Determine deployment component */
				DeploymentComponent deploymentComponent =
						this.getDeploymentModelManager()
								.deploymentComponentForAssemblyComponent(
										assemblyComponent, executionContainer);
				if (deploymentComponent == null) {
					deploymentComponent =
							this.getDeploymentModelManager()
									.createAndRegisterDeploymentComponent(
											assemblyComponent,
											executionContainer);
				}
				newComponentExec.setDeploymentComponent(deploymentComponent);
				newExecution = newComponentExec;
			}
		}

		{
			/*
			 * 2.) Initialize the values common for component and connector
			 * executions.
			 */
			// TODO: what about the operation?
			newExecution.setEoi(execution.eoi);
			newExecution.setEss(execution.ess);
			newExecution.setSessionId(execution.sessionId);
			newExecution.setTin(execution.tin);
			newExecution.setTout(execution.tout);
			newExecution.setTraceId(execution.traceId);
		}

		return newExecution;
	}

	@Override
	public IEvent transform(final IMonitoringRecord record) {
		if (!(record instanceof OperationExecutionRecord)) {
			return null;
		}

		final OperationExecutionRecord execution = (OperationExecutionRecord) record;

		return this.transformExecutionRecord(execution);
	}
}
