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
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.slastic.metamodel.monitoring.OperationExecution;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.ConnectorType;
import kieker.tools.slastic.metamodel.typeRepository.Interface;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.slastic.metamodel.typeRepository.Signature;
import kieker.tools.slastic.metamodel.usage.InvalidExecutionTrace;
import kieker.tools.slastic.metamodel.usage.Message;
import kieker.tools.slastic.metamodel.usage.MessageTrace;
import kieker.tools.slastic.metamodel.usage.SynchronousCallMessage;
import kieker.tools.slastic.metamodel.usage.SynchronousReplyMessage;
import kieker.tools.slastic.metamodel.usage.Trace;
import kieker.tools.slastic.metamodel.usage.UsageModel;
import kieker.tools.slastic.metamodel.usage.ValidExecutionTrace;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import kieker.tools.slastic.plugins.slasticImpl.model.usage.UsageModelManager;

/**
 *
 * @author Andre van Hoorn, Teerat Pitakrat
 *
 */
public class UsageAndAssemblyModelUpdater {
	private static final Log LOG = LogFactory.getLog(UsageAndAssemblyModelUpdater.class);

	private static final String CEP_QUERY = "select * from " + Trace.class.getName();

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
	public void update(final Trace trace) {
		if (trace instanceof ValidExecutionTrace) {
			final ValidExecutionTrace validExecutionTrace = (ValidExecutionTrace) trace;
			if (!this.processTrace(validExecutionTrace)) {
				LOG.error("Failed to process trace: " + validExecutionTrace);
			}
		} else {
			final InvalidExecutionTrace invalidExecutionTrace = (InvalidExecutionTrace) trace;
			LOG.warn("Received InvalidExecutionTrace" + invalidExecutionTrace + "; Reason:" + invalidExecutionTrace.getErrorMsg());
		}
	}

	/**
	 * TODO: improve performance (the way we lookup entities can be increased heavily)
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

		final Stack<DeploymentCallingRelationshipCounter> deploymentCallingRelationshipCounterStack = new Stack<DeploymentCallingRelationshipCounter>();

		/**
		 * Maintains the frequency of calls to distinct operations within this
		 * trace.
		 */
		final Map<Operation, Long> operationCallFrequencies = new HashMap<Operation, Long>();

		/**
		 * Maintains the frequency calls to distinct operations of deployment components within this trace.
		 */
		final Map<DeploymentOperationCallee, Long> deploymentOperationCallFrequencies = new HashMap<DeploymentOperationCallee, Long>();

		/**
		 * For each Execution, this list contains the frequencies of called
		 * interface signatures.
		 */
		final List<ExecutionCallRelationships> executionCallRelationships = new ArrayList<ExecutionCallRelationships>();

		/**
		 * For each execution, this list contains the frequencies of called interface signatures on deployment components.
		 */
		final List<DeploymentCallingRelationshipCounter> deploymentExecutionCallRelationshipCounters = new ArrayList<DeploymentCallingRelationshipCounter>();

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
				 * Increment receiver's (callee's) deployment operation call frequency by one
				 */
				final DeploymentComponent calledDeploymentComponent = receiver.getDeploymentComponent();
				final DeploymentOperationCallee deploymentOperationCallee = new DeploymentOperationCallee(calledOperation, calledDeploymentComponent);
				if (deploymentOperationCallFrequencies.containsKey(deploymentOperationCallee)) {
					final long count = deploymentOperationCallFrequencies.get(deploymentOperationCallee);
					deploymentOperationCallFrequencies.put(deploymentOperationCallee, count + 1);
				} else {
					deploymentOperationCallFrequencies.put(deploymentOperationCallee, 1L);
				}

				/*
				 * Create receiver's (callee's) calling relationship for called
				 * operation (will be modified on returns)
				 */
				final ExecutionCallRelationships executionCallRelationship = new ExecutionCallRelationships(receiver);
				executionCallRelationshipStack.push(executionCallRelationship);

				/*
				 * Create receiver's (callee's) calling relationship for called deployment operation (will be modified on returns)
				 */
				final DeploymentCallingRelationshipCounter deploymentCallingRelationshipCounter = new DeploymentCallingRelationshipCounter();
				deploymentCallingRelationshipCounterStack.add(deploymentCallingRelationshipCounter);

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

				final DeploymentCallingRelationshipCounter deploymentCallingRelationshipCounter = deploymentCallingRelationshipCounterStack.pop();
				// TODO: validity check
				deploymentExecutionCallRelationshipCounters.add(deploymentCallingRelationshipCounter);

				final DeploymentComponent providingDeploymentComponent = sender.getDeploymentComponent();
				final AssemblyComponent providingAssemblyComponent = providingDeploymentComponent.getAssemblyComponent();
				final Signature operationSignature = sender.getOperation().getSignature();
				final Interface iface = this.typeRepositoryModelManager.lookupProvidedInterfaceForSignature(
						providingAssemblyComponent.getComponentType(), operationSignature);
				if (iface == null) {
					LOG.error("Callee " + providingAssemblyComponent + " does not provide interface with given signature " + operationSignature);
					return false;
				}

				final String signatureName = operationSignature.getName();
				final String signatureRetType = operationSignature.getReturnType();
				final String[] signatureArgTypes = operationSignature.getParamTypes().toArray(new String[] {});
				final String[] signatureModifiers = operationSignature.getModifiers().toArray(new String[] {});

				final Signature returningInterfaceSignature = this.typeRepositoryModelManager.lookupSignature(
						iface,
						signatureName, signatureRetType, signatureArgTypes, signatureModifiers);

				if (receiver.equals(UsageModelManager.ROOT_EXEC)) {
					entryCallInterfaceSignature = returningInterfaceSignature;
					sysProvDelegConnector = this.assemblyModelManager.lookupProvidedInterfaceDelegationConnector(providingAssemblyComponent, operationSignature);
					if (sysProvDelegConnector == null) { // not yet registered -> register!
						// Register system-provided interface (which may be
						// registered already)
						this.assemblyModelManager.registerSystemProvidedInterface(iface);
						final ConnectorType connectorType = this.modelManager.getTypeRepositoryManager().createAndRegisterConnectorType(iface);
						sysProvDelegConnector = this.assemblyModelManager.createAndRegisterProvidedInterfaceDelegationConnector(connectorType);
						this.assemblyModelManager.delegate(sysProvDelegConnector, iface, providingAssemblyComponent);
					}

					// calling frequency of sysProvDelegConnector incremented below
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

					final DeploymentComponent requiringDeploymentComponent = receiver.getDeploymentComponent();
					final AssemblyComponent requiringAssemblyComponent = requiringDeploymentComponent.getAssemblyComponent();
					final Operation callingOperation = receiver.getOperation();

					AssemblyComponentConnector connector = this.assemblyModelManager.lookupAssemblyConnector(requiringAssemblyComponent, providingAssemblyComponent,
							operationSignature);
					if (connector == null) {
						// requiring and providing component aren't connected,
						// yet -> connect
						/*
						 * If if the requiring component doesn't have the
						 * interface in its list of required interfaces, yet ->
						 * add it.
						 */
						final ComponentType requiringType = requiringAssemblyComponent.getComponentType();
						if (!requiringType.getRequiredInterfaces().contains(iface)) {
							this.typeRepositoryModelManager.registerRequiredInterface(requiringType, iface);
						}

						final ConnectorType connectorT = this.typeRepositoryModelManager.createAndRegisterConnectorType(iface);
						connector = this.assemblyModelManager.createAndRegisterAssemblyConnector(connectorT);
						if (!this.assemblyModelManager.connect(connector, requiringAssemblyComponent, providingAssemblyComponent)) {
							LOG.error("Failed to connect " + requiringAssemblyComponent + " and " + providingAssemblyComponent + " via " + connector);
							return false;
						}
					}

					// TODO: Update connector frequency?

					/*
					 * Update call frequency of receiving execution to
					 * interface's signature
					 */
					executionCallRelationshipCaller.incCount(iface, returningInterfaceSignature);

					/*
					 * Update call frequency of receiving execution to
					 * interface's signature of deployment component
					 */
					final DeploymentCallingRelationshipCounter nextDeploymentCallingRelationshipCounter = deploymentCallingRelationshipCounterStack.peek();
					final DeploymentCallingRelationshipCaller deploymentCallingRelationshipCaller = new DeploymentCallingRelationshipCaller(callingOperation,
							requiringDeploymentComponent);
					final DeploymentCallingRelationshipCallee deploymentCallingRelationshipCallee = new DeploymentCallingRelationshipCallee(iface,
							returningInterfaceSignature, providingDeploymentComponent);
					nextDeploymentCallingRelationshipCounter.incCount(deploymentCallingRelationshipCaller, deploymentCallingRelationshipCallee);
				}
			} else {
				LOG.error("Unexpected message type: " + message);
				return false;
			}
		}

		/*
		 * Updating call frequency to signature of system-provided interface
		 */
		if ((sysProvDelegConnector == null) || (entryCallInterfaceSignature == null))

		{
			LOG.error("called system-provided delegation connector and/or signature not set; "
					+ "connector: " + sysProvDelegConnector + " ; signature: " + entryCallInterfaceSignature);
			return false;
		} else

		{
			this.usageModelManager.incSystemProvidedInterfaceSignatureCallFreq(sysProvDelegConnector, entryCallInterfaceSignature);
		}

		/*
		 * Updating operation call frequency.
		 */
		for (final Entry<Operation, Long> opCallFreq : operationCallFrequencies.entrySet()) {
			this.usageModelManager.incOperationCallFreq(opCallFreq.getKey(), opCallFreq.getValue());
		}

		/*
		 * Updating deployment operation call frequency.
		 */
		for (final Entry<DeploymentOperationCallee, Long> entry : deploymentOperationCallFrequencies.entrySet()) {
			final Operation operation = entry.getKey().getCalledOperation();
			final DeploymentComponent deploymentComponent = entry.getKey().getCalledDeploymentComponent();
			final long count = entry.getValue();
			this.usageModelManager.incDeploymentOperationCallFreq(operation, deploymentComponent, count);
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

		/*
		 * Updating deployment calling relationships
		 */
		for (final DeploymentCallingRelationshipCounter deploymentCallingRelationshipCounter : deploymentExecutionCallRelationshipCounters) {
			final Map<DeploymentCallingRelationshipCaller, Map<DeploymentCallingRelationshipCallee, Long>> callMap = deploymentCallingRelationshipCounter
					.getDeploymentCallingRelationshipMap();
			for (final Entry<DeploymentCallingRelationshipCaller, Map<DeploymentCallingRelationshipCallee, Long>> entry : callMap.entrySet()) {
				final DeploymentCallingRelationshipCaller deploymentCallingRelationshipCaller = entry.getKey();
				final DeploymentComponent callingDeploymentComponent = deploymentCallingRelationshipCaller.getCallingDeploymentComponent();
				final Operation callingOperation = deploymentCallingRelationshipCaller.getCallingOperation();
				final Map<DeploymentCallingRelationshipCallee, Long> subMap = entry.getValue();
				for (final Entry<DeploymentCallingRelationshipCallee, Long> subEntry : subMap.entrySet()) {
					final DeploymentCallingRelationshipCallee deploymentCallingRelationshipCallee = subEntry.getKey();
					final Interface calledInterface = deploymentCallingRelationshipCallee.getIface();
					final Signature calledSignature = deploymentCallingRelationshipCallee.getSignature();
					final DeploymentComponent calledDeploymentComponent = deploymentCallingRelationshipCallee.getCalledDeploymentComponent();
					final long count = subEntry.getValue();
					this.usageModelManager.incDeploymentCallingRelationshipFreq(callingDeploymentComponent, callingOperation, calledInterface, calledSignature,
							calledDeploymentComponent, count);
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

/**
 * Data structure containing operation and deployment component. It is to be used as a key for the HashMap which stores number of calls to distinct operations of
 * deployment components.
 *
 * @author Teerat Pitakrat
 *
 */
class DeploymentOperationCallee {
	private final Operation calledOperation;
	private final DeploymentComponent calledDeploymentComponent;

	public DeploymentOperationCallee(final Operation calledOperation, final DeploymentComponent calledDeploymentComponent) {
		super();
		this.calledOperation = calledOperation;
		this.calledDeploymentComponent = calledDeploymentComponent;
	}

	public Operation getCalledOperation() {
		return this.calledOperation;
	}

	public DeploymentComponent getCalledDeploymentComponent() {
		return this.calledDeploymentComponent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.calledDeploymentComponent == null) ? 0 : this.calledDeploymentComponent.hashCode());
		result = (prime * result) + ((this.calledOperation == null) ? 0 : this.calledOperation.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final DeploymentOperationCallee other = (DeploymentOperationCallee) obj;
		if (this.calledDeploymentComponent == null) {
			if (other.calledDeploymentComponent != null) {
				return false;
			}
		} else if (!this.calledDeploymentComponent.equals(other.calledDeploymentComponent)) {
			return false;
		}
		if (this.calledOperation == null) {
			if (other.calledOperation != null) {
				return false;
			}
		} else if (!this.calledOperation.equals(other.calledOperation)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("Operation: " + this.calledOperation.getSignature());
		sb.append("Deployment component: " + this.calledDeploymentComponent.getAssemblyComponent() + "-" + this.calledDeploymentComponent.getId());
		return sb.toString();
	}
}

/**
 * Data structure containing calling operation and calling deployment component. It is to be used in {@link DeploymentCallingRelationshipCounter} as the first-level
 * key of a 2-level HashMap.
 *
 * @author Teerat Pitakrat
 *
 */
class DeploymentCallingRelationshipCaller {
	final private Operation callingOperation;
	final private DeploymentComponent callingDeploymentComponent;

	public DeploymentCallingRelationshipCaller(final Operation callingOperation, final DeploymentComponent callingDeploymentComponent) {
		this.callingOperation = callingOperation;
		this.callingDeploymentComponent = callingDeploymentComponent;
	}

	public Operation getCallingOperation() {
		return this.callingOperation;
	}

	public DeploymentComponent getCallingDeploymentComponent() {
		return this.callingDeploymentComponent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.callingDeploymentComponent == null) ? 0 : this.callingDeploymentComponent.hashCode());
		result = (prime * result) + ((this.callingOperation == null) ? 0 : this.callingOperation.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final DeploymentCallingRelationshipCaller other = (DeploymentCallingRelationshipCaller) obj;
		if (this.callingDeploymentComponent == null) {
			if (other.callingDeploymentComponent != null) {
				return false;
			}
		} else if (!this.callingDeploymentComponent.equals(other.callingDeploymentComponent)) {
			return false;
		}
		if (this.callingOperation == null) {
			if (other.callingOperation != null) {
				return false;
			}
		} else if (!this.callingOperation.equals(other.callingOperation)) {
			return false;
		}
		return true;
	}
}

/**
 * Data structure containing called interface, called signature and called deployment component. It is to be used in {@link DeploymentCallingRelationshipCounter} as
 * the second-level key of a 2-level HashMap.
 *
 * @author Teerat Pitakrat
 *
 */
class DeploymentCallingRelationshipCallee {
	final private Interface iface;
	final private Signature signature;
	final private DeploymentComponent calledDeploymentComponent;

	public DeploymentCallingRelationshipCallee(final Interface iface, final Signature signature, final DeploymentComponent calledDeploymentComponent) {
		this.iface = iface;
		this.signature = signature;
		this.calledDeploymentComponent = calledDeploymentComponent;
	}

	public Interface getIface() {
		return this.iface;
	}

	public Signature getSignature() {
		return this.signature;
	}

	public DeploymentComponent getCalledDeploymentComponent() {
		return this.calledDeploymentComponent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.calledDeploymentComponent == null) ? 0 : this.calledDeploymentComponent.hashCode());
		result = (prime * result) + ((this.iface == null) ? 0 : this.iface.hashCode());
		result = (prime * result) + ((this.signature == null) ? 0 : this.signature.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final DeploymentCallingRelationshipCallee other = (DeploymentCallingRelationshipCallee) obj;
		if (this.calledDeploymentComponent == null) {
			if (other.calledDeploymentComponent != null) {
				return false;
			}
		} else if (!this.calledDeploymentComponent.equals(other.calledDeploymentComponent)) {
			return false;
		}
		if (this.iface == null) {
			if (other.iface != null) {
				return false;
			}
		} else if (!this.iface.equals(other.iface)) {
			return false;
		}
		if (this.signature == null) {
			if (other.signature != null) {
				return false;
			}
		} else if (!this.signature.equals(other.signature)) {
			return false;
		}
		return true;
	}
}

/**
 * Data structure containing 2-level HashMap which stores the frequency calls from an operation of a deployment component to another deployment component.
 *
 * @author Teerat Pitakrat
 *
 */
class DeploymentCallingRelationshipCounter {
	private final Map<DeploymentCallingRelationshipCaller, Map<DeploymentCallingRelationshipCallee, Long>> deploymentCallingRelationshipCallerMap = new HashMap<DeploymentCallingRelationshipCaller, Map<DeploymentCallingRelationshipCallee, Long>>();

	public void addCaller(final DeploymentCallingRelationshipCaller deploymentCallingRelationshipCaller) {
		if (this.deploymentCallingRelationshipCallerMap.containsKey(deploymentCallingRelationshipCaller)) {
			// Caller is not in the map
			this.deploymentCallingRelationshipCallerMap.put(deploymentCallingRelationshipCaller, new HashMap<DeploymentCallingRelationshipCallee, Long>());
		}
	}

	public void incCount(final DeploymentCallingRelationshipCaller deploymentCallingRelationshipCaller,
			final DeploymentCallingRelationshipCallee deploymentCallingRelationshipCallee) {
		if (this.deploymentCallingRelationshipCallerMap.containsKey(deploymentCallingRelationshipCaller)) {
			// Caller is already in the map
			final Map<DeploymentCallingRelationshipCallee, Long> deploymentCallingRelationshipCalleeMap = this.deploymentCallingRelationshipCallerMap
					.get(deploymentCallingRelationshipCaller);
			if (deploymentCallingRelationshipCalleeMap.containsKey(deploymentCallingRelationshipCallee)) {
				// Callee is already in the sub-map
				final long count = deploymentCallingRelationshipCalleeMap.get(deploymentCallingRelationshipCallee);
				deploymentCallingRelationshipCalleeMap.put(deploymentCallingRelationshipCallee, count + 1);
			} else {
				// Callee is not in the sub-map
				deploymentCallingRelationshipCalleeMap.put(deploymentCallingRelationshipCallee, 1L);
			}
		} else {
			// Caller is not in the map, callee sub-map does not exist
			this.deploymentCallingRelationshipCallerMap.put(deploymentCallingRelationshipCaller, new HashMap<DeploymentCallingRelationshipCallee, Long>());
			final Map<DeploymentCallingRelationshipCallee, Long> deploymentCallingRelationshipCalleeMap = this.deploymentCallingRelationshipCallerMap
					.get(deploymentCallingRelationshipCaller);
			deploymentCallingRelationshipCalleeMap.put(deploymentCallingRelationshipCallee, 1L);
		}
	}

	public Map<DeploymentCallingRelationshipCaller, Map<DeploymentCallingRelationshipCallee, Long>> getDeploymentCallingRelationshipMap() {
		return this.deploymentCallingRelationshipCallerMap;
	}
}
