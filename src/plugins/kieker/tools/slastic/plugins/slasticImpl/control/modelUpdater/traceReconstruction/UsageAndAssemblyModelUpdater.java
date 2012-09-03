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

package kieker.tools.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import kieker.tools.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import kieker.tools.slastic.metamodel.componentAssembly.SystemProvidedInterfaceDelegationConnector;
import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.slastic.metamodel.monitoring.OperationExecution;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.metamodel.usage.Message;
import kieker.tools.slastic.metamodel.usage.MessageTrace;
import kieker.tools.slastic.metamodel.usage.SynchronousCallMessage;
import kieker.tools.slastic.metamodel.usage.SynchronousReplyMessage;
import kieker.tools.slastic.metamodel.usage.UsageModel;
import kieker.tools.slastic.metamodel.usage.ValidExecutionTrace;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.usage.UsageModelManager;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class UsageAndAssemblyModelUpdater {
	private static final Log LOG = LogFactory.getLog(UsageAndAssemblyModelUpdater.class);

	private static final String CEP_QUERY = "select * from " + ValidExecutionTrace.class.getName();

	private final EPServiceProvider epService;

	private final ModelManager modelManager;
	private final TypeRepositoryModelManager typeRepositoryModelManager;
	private final ComponentAssemblyModelManager assemblyModelManager;
	private final UsageModelManager usageModelManager;

	/**
	 * Constructs a {@link UsageAndAssemblyModelUpdater} which registers itself
	 * as a subscriber to the given {@link EPServiceProvider}.
	 * 
	 * @param epService
	 * @param modelManager
	 */
	public UsageAndAssemblyModelUpdater(final EPServiceProvider epService, final ModelManager modelManager) {
		this.epService = epService;
		this.modelManager = modelManager;
		this.typeRepositoryModelManager = this.modelManager.getTypeRepositoryManager();
		this.assemblyModelManager = this.modelManager.getComponentAssemblyModelManager();
		this.usageModelManager = this.modelManager.getUsageModelManager();

		final EPStatement statement = this.epService.getEPAdministrator().createEPL(CEP_QUERY);
		statement.setSubscriber(this);
	}

	/**
	 * Processes {@link ValidExecutionTrace}s by updating the {@link ComponentAssemblyModel} and the {@link UsageModel} via the
	 * respective managers.
	 * 
	 * @param validExecutionTrace
	 */
	public void update(final ValidExecutionTrace validExecutionTrace) {
		if (!this.processTrace(validExecutionTrace)) {
			LOG.error("Failed to process trace: " + validExecutionTrace);
		}
	}

	/**
	 * TODO: improve performance (the way we lookup entities can increased
	 * heavily)
	 * 
	 * @param validExecutionTrace
	 * @return
	 */
	private boolean processTrace(final ValidExecutionTrace validExecutionTrace) {
		final MessageTrace mt = validExecutionTrace.getMessageTrace();

		/**
		 * Used as an intermediate data-structure to keep track of interface
		 * signatures called by the distinct executions within this trace.
		 */
		final Stack<ExecutionCallRelationships> executionCallRelationshipStack = new Stack<ExecutionCallRelationships>();

		/**
		 * Maintains the frequency of calls to distinct operations within this
		 * trace.
		 */
		final Map<Operation, Long> operationCallFrequencies = new HashMap<Operation, Long>();

		/**
		 * For each Execution, this list contains the frequencies of called
		 * interface signatures.
		 */
		final List<ExecutionCallRelationships> executionCallRelationships = new ArrayList<ExecutionCallRelationships>();

		/**
		 * Connector used to delegate this trace's entry call. Will be set on
		 * return of entry call.
		 */
		SystemProvidedInterfaceDelegationConnector sysProvDelegConnector = null;

		/**
		 * Entry call signature invoked in this trace. Will be set on return of
		 * entry call.
		 */
		Signature entryCallInterfaceSignature = null;

		// TODO: assemblyComponentConnectorCallFrequencies

		for (final Message message : mt.getMessages()) {
			final DeploymentComponentOperationExecution sender = (DeploymentComponentOperationExecution) message.getSender();
			final DeploymentComponentOperationExecution receiver = (DeploymentComponentOperationExecution) message.getReceiver();

			if (message instanceof SynchronousCallMessage) {
				if (!(message.getSender() instanceof DeploymentComponentOperationExecution)
						|| !(message.getReceiver() instanceof DeploymentComponentOperationExecution)) {
					LOG.error("Currently only supporting " + DeploymentComponentOperationExecution.class.getName() + "s");
					return false;
				}

				/*
				 * Increment receiver's (callee's) operation call frequency by one
				 */
				final Operation calledOperation = receiver.getOperation();
				final Long operationCallFrequency = operationCallFrequencies.get(calledOperation);
				final long newFrequency = (operationCallFrequency == null) ? 1 : operationCallFrequency + 1;
				operationCallFrequencies.put(calledOperation, newFrequency);

				/*
				 * Create receiver's (callee's) calling relationship for called
				 * operation (will be modified on returns)
				 */
				final ExecutionCallRelationships executionCallRelationship = new ExecutionCallRelationships(receiver);
				executionCallRelationshipStack.push(executionCallRelationship);
			} else if (message instanceof SynchronousReplyMessage) {
				/*
				 * Pop calling relationship of returned execution
				 * (callee/sender)
				 */
				final ExecutionCallRelationships executionCallRelationshipCallee = executionCallRelationshipStack.pop();
				// Validity check during development; can be removed as soon as
				// implementation stable
				if (!executionCallRelationshipCallee.getExecution().equals(sender)) {
					LOG.error("Executions do not match: " + executionCallRelationshipCallee.getExecution() + " vs. " + sender);
					return false;
				}
				// Store execution call relationship for returned execution:
				executionCallRelationships.add(executionCallRelationshipCallee);

				final AssemblyComponent providingComponent = sender.getDeploymentComponent().getAssemblyComponent();
				final Signature operationSignature = sender.getOperation().getSignature();
				final Interface iface =
						this.typeRepositoryModelManager.lookupProvidedInterfaceForSignature(
								providingComponent.getComponentType(), operationSignature);
				if (iface == null) {
					LOG.error("Callee " + providingComponent + " does not provide interface with given signature " + operationSignature);
					return false;
				}

				final String signatureName = operationSignature.getName();
				final String signatureRetType = operationSignature.getReturnType();
				final String[] signatureArgTypes = operationSignature.getParamTypes().toArray(new String[] {});

				final Signature returningInterfaceSignature =
						this.typeRepositoryModelManager.lookupSignature(
								iface,
								signatureName, signatureRetType, signatureArgTypes);

				if (receiver.equals(UsageModelManager.ROOT_EXEC)) {
					entryCallInterfaceSignature = returningInterfaceSignature;
					sysProvDelegConnector = this.assemblyModelManager.lookupProvidedInterfaceDelegationConnector(providingComponent, operationSignature);
					if (sysProvDelegConnector == null) { // not yet registered -> register!
						// Register system-provided interface (which may be
						// registered already)
						this.assemblyModelManager.registerSystemProvidedInterface(iface);
						final ConnectorType connectorType = this.modelManager.getTypeRepositoryManager().createAndRegisterConnectorType(iface);
						sysProvDelegConnector = this.assemblyModelManager.createAndRegisterProvidedInterfaceDelegationConnector(connectorType);
						this.assemblyModelManager.delegate(sysProvDelegConnector, iface, providingComponent);
					}
				} else { // reply message not originating from entry call
					/*
					 * Update receiver's (i.e., caller's) calling relationship.
					 * Note that the peek operation must be executed in this
					 * block, because otherwise, we'll get an
					 * EmptyStackException for the entry call.
					 */
					final ExecutionCallRelationships executionCallRelationshipCaller = executionCallRelationshipStack.peek();
					// Validity check during development; can be removed as soon
					// as
					// implementation stable
					if (!executionCallRelationshipCaller.getExecution().equals(receiver)) {
						LOG.error("Executions do not match: " + executionCallRelationshipCaller.getExecution() + " vs. " + receiver);
						return false;
					}

					final AssemblyComponent requiringComponent = receiver.getDeploymentComponent().getAssemblyComponent();

					AssemblyComponentConnector connector =
							this.assemblyModelManager.lookupAssemblyConnector(requiringComponent, providingComponent, operationSignature);
					if (connector == null) {
						// requiring and providing component aren't connected,
						// yet -> connect
						/*
						 * If if the requiring component doesn't have the
						 * interface in its list of required interfaces, yet ->
						 * add it.
						 */
						final ComponentType requiringType = requiringComponent.getComponentType();
						if (!requiringType.getRequiredInterfaces().contains(iface)) {
							this.typeRepositoryModelManager.registerRequiredInterface(requiringType, iface);
						}

						final ConnectorType connectorT = this.typeRepositoryModelManager.createAndRegisterConnectorType(iface);
						connector = this.assemblyModelManager.createAndRegisterAssemblyConnector(connectorT);
						if (!this.assemblyModelManager.connect(connector, requiringComponent, providingComponent)) {
							LOG.error("Failed to connect " + requiringComponent + " and " + providingComponent + " via " + connector);
							return false;
						}
					}

					/*
					 * Update call frequency of receiving execution to
					 * interface's signature
					 */
					executionCallRelationshipCaller.incCount(iface, returningInterfaceSignature);
				}
			} else {
				LOG.error("Unexpected message type: " + message);
				return false;
			}
		}

		/*
		 * Updating call frequency to signature of system-provided interface
		 */
		if ((sysProvDelegConnector == null) || (entryCallInterfaceSignature == null)) {
			LOG.error("called system-provided delegation connector and/or signature not set; "
					+ "connector: " + sysProvDelegConnector + " ; signature: " + entryCallInterfaceSignature);
			return false;
		} else {
			this.usageModelManager.incSystemProvidedInterfaceSignatureCallFreq(sysProvDelegConnector, entryCallInterfaceSignature);
		}

		/*
		 * Updating operation call frequency.
		 */
		for (final Entry<Operation, Long> opCallFreq : operationCallFrequencies.entrySet()) {
			this.usageModelManager.incOperationCallFreq(opCallFreq.getKey(), opCallFreq.getValue());
		}

		/*
		 * Updating calling relationships by iterating over all
		 * executionCallRelationships
		 */
		for (final ExecutionCallRelationships execCR : executionCallRelationships) {
			final OperationExecution opExec = execCR.getExecution();
			if (!(opExec instanceof DeploymentComponentOperationExecution)) {
				LOG.error("Only supporting executions of type " + DeploymentComponentOperationExecution.class.getName()
						+ " so far; found: " + execCR.getClass().getName());
				return false;
			}
			final Operation operation = ((DeploymentComponentOperationExecution) execCR.getExecution()).getOperation();

			for (final InterfaceSignatureCallFrequencies ifaceFreq : execCR.getCallFrequencies().values()) {
				final Interface iface = ifaceFreq.getIface();
				for (final Entry<Signature, Long> signatureFreq : ifaceFreq.getFrequencies().entrySet()) {
					final Signature signature = signatureFreq.getKey();
					this.usageModelManager.incCallingRelationshipFreq(operation, iface, signature, signatureFreq.getValue());
				}
			}
		}

		return true;
	}
}

/**
 * For an {@link Interface}, this data structure maintains call frequencies to
 * the declared {@link Signature}s.
 * 
 * @author Andre van Hoorn
 * 
 */
class InterfaceSignatureCallFrequencies {
	private final Interface iface;

	private final Map<Signature, Long> frequencies = new HashMap<Signature, Long>();

	public InterfaceSignatureCallFrequencies(final Interface iface) {
		this.iface = iface;
	}

	/**
	 * @return the {@link Interface}
	 */
	public final Interface getIface() {
		return this.iface;
	}

	/**
	 * @return the frequencies
	 */
	public final Map<Signature, Long> getFrequencies() {
		return this.frequencies;
	}

	public void incCount(final Signature signature) {
		final Long frequency = this.frequencies.get(signature);
		if (frequency == null) {
			this.frequencies.put(signature, 1l);
		} else {
			this.frequencies.put(signature, frequency + 1);
		}
	}
}

/**
 * For an {@link OperationExecution}, this data structure maintains call
 * frequencies to {@link Interface} {@link Signature}s.
 * 
 * 
 * @author Andre van Hoorn
 * 
 */
class ExecutionCallRelationships {
	private final OperationExecution execution;
	private final Map<Interface, InterfaceSignatureCallFrequencies> interfaceSignatureCallFrequencies = new HashMap<Interface, InterfaceSignatureCallFrequencies>();

	public ExecutionCallRelationships(final OperationExecution execution) {
		this.execution = execution;
	}

	/**
	 * Increments the number of calls from this {@link OperationExecution} to
	 * the given {@link Signature} of the given {@link Interface} by one.
	 */
	public void incCount(final Interface iface, final Signature signature) {
		// Lookup signature call frequencies for the required interface
		InterfaceSignatureCallFrequencies signatureCallFrequencies = this.interfaceSignatureCallFrequencies.get(iface);
		if (signatureCallFrequencies == null) { // map doesn't exist, yet -> create
			signatureCallFrequencies = new InterfaceSignatureCallFrequencies(iface);
			this.interfaceSignatureCallFrequencies.put(iface, signatureCallFrequencies);
		}
		signatureCallFrequencies.incCount(signature);
	}

	/**
	 * @return the execution
	 */
	public final OperationExecution getExecution() {
		return this.execution;
	}

	/**
	 * Returns the call frequencies to other {@link Interface} {@link Signature} s.
	 * 
	 * @return
	 */
	public Map<Interface, InterfaceSignatureCallFrequencies> getCallFrequencies() {
		return this.interfaceSignatureCallFrequencies;
	}
}
