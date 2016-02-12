/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction;

import kieker.common.record.IMonitoringRecord;
import kieker.common.record.controlflow.OperationExecutionRecord;
import kieker.common.util.signature.ClassOperationSignaturePair;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.core.IEvent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.monitoring.ConnectorOperationExecution;
import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.slastic.metamodel.monitoring.MonitoringFactory;
import kieker.tools.slastic.metamodel.monitoring.OperationExecution;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.filters.ISynchronousTransformationFilter;

/**
 *
 * @author Andre van Hoorn
 */
public class ExecutionRecordTransformationFilter extends AbstractModelReconstructionComponent implements ISynchronousTransformationFilter,
IExecutionRecordTransformation {

	private final int componentDiscoveryHierarchyLevel;

	// private static final Log log = LogFactory.getLog(ExecutionRecordTransformationFilter.class);

	/**
	 *
	 * @param modelManager
	 */
	public ExecutionRecordTransformationFilter(final ModelManager modelManager, final int componentDiscoveryHierarchyLevel) {
		super(modelManager);
		this.componentDiscoveryHierarchyLevel = componentDiscoveryHierarchyLevel;
	}

	/**
	 * <p>
	 * Creates a new OperationExecution instance and initializes it based on the given execution. Depending on the instrumentation model, the returned object is an
	 * instance of {@link DeploymentComponentOperationExecution} or {@link ConnectorOperationExecution}.
	 * </p>
	 *
	 * <p>
	 * The strategy is as follows, given the {@link OperationExecutionRecord} object with {@link OperationExecutionRecord#className} parameter whose value equals
	 * classname:
	 * <ol>
	 * <li>If an {@link AssemblyComponentConnector} with a full-qualified name that equals classname is registered, the execution is assumed to be a
	 * {@link ConnectorOperationExecution}. An instance referencing to the {@link AssemblyComponentConnector} is created.</li>
	 * <li>Otherwise, the execution is assumed to be a {@link DeploymentComponentOperationExecution}. An instance referencing to an {@link AssemblyComponent} with
	 * the full-qualified name that equals classname is created; if the {@link AssemblyComponent} doesn't exist, it is being created.</li>
	 * <li>The execution's {@link ExecutionContainer} is determined accordingly: if a {@link ExecutionContainer} with the {@link OperationExecutionRecord#hostName}
	 * does not exist, it is being created.</li>
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

		final ExecutionContainer executionContainer = this.lookupOrCreateExecutionContainerByName(execution.getHostname());

		/*
		 * The values of the variables componentOrConnectorName and
		 * operationName depend on the componentDiscoveryHierarchyLevel.
		 */
		final String componentOrConnectorName;
		final String operationName;
		final String returnType;
		final String[] argTypes;
		final String[] modifiers;
		{
			final ClassOperationSignaturePair cosp = ClassOperationSignaturePair.splitOperationSignatureStr(execution.getOperationSignature());

			final String[] fqnSplit = NameUtils.splitFullyQualifiedName(cosp.getFqClassname());
			final String[] abstractedName = NameUtils.abstractFQName(fqnSplit[0], fqnSplit[1], cosp.getSignature().getName(), this.componentDiscoveryHierarchyLevel);

			componentOrConnectorName = NameUtils.createFQName(abstractedName[0], abstractedName[1]);
			operationName = abstractedName[2];

			// TODO: Note that we might also need to apply the name abstraction to these types
			returnType = cosp.getSignature().getReturnType();
			argTypes = cosp.getSignature().getParamTypeList();
			modifiers = cosp.getSignature().getModifier();
		}

		{
			/*
			 * 1.) Determine whether this is a deployment component execution or
			 * a connector execution.
			 */
			final AssemblyComponentConnector assemblyConnector =
					this.getAssemblyModelManager().lookupAssemblyConnector(componentOrConnectorName);
			if (assemblyConnector != null) {
				/* Is an (explicit) connector execution */
				final ConnectorOperationExecution newConnectorExec =
						MonitoringFactory.eINSTANCE.createConnectorOperationExecution();
				newConnectorExec.setAssemblyConnector(assemblyConnector);
				newConnectorExec.setExecutionContainer(executionContainer);
				newExecution = newConnectorExec;

				// TODO: operations for connector executions ...
			} else {
				/* Is an execution of a component (existing or non-existing) */
				final DeploymentComponentOperationExecution newComponentExec = MonitoringFactory.eINSTANCE.createDeploymentComponentOperationExecution();
				/* Determine assembly component */
				AssemblyComponent assemblyComponent = this.getAssemblyModelManager().lookupAssemblyComponent(componentOrConnectorName);
				if (assemblyComponent == null) {
					/* We need to create the assembly component */
					assemblyComponent = this.createAssemblyComponent(componentOrConnectorName);
				}
				/* Determine deployment component */
				DeploymentComponent deploymentComponent =
						this.getDeploymentModelManager().deploymentComponentForAssemblyComponent(assemblyComponent, executionContainer);
				if (deploymentComponent == null) {
					deploymentComponent = this.getDeploymentModelManager().createAndRegisterDeploymentComponent(assemblyComponent, executionContainer);
				}

				/* Lookup the operation */
				final Operation op = this.lookupOrCreateOperationByName(assemblyComponent.getComponentType(), operationName, returnType, argTypes, modifiers);
				newComponentExec.setDeploymentComponent(deploymentComponent);
				newComponentExec.setOperation(op);
				newExecution = newComponentExec;
			}
		}

		{
			/*
			 * 2.) Initialize the values common for component and connector
			 * executions.
			 */
			newExecution.setEoi(execution.getEoi());
			newExecution.setEss(execution.getEss());
			// TODO: reactivate (#31)? newExecution.setSessionId(execution.getSessionId());
			newExecution.setTin(execution.getTin());
			newExecution.setTout(execution.getTout());
			newExecution.setTraceId(execution.getTraceId());
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
