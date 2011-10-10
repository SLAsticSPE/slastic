package org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.typeRepository.TypeRepositoryModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponentConnector;
import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.ConnectorType;
import de.cau.se.slastic.metamodel.typeRepository.Interface;
import de.cau.se.slastic.metamodel.typeRepository.Operation;
import de.cau.se.slastic.metamodel.typeRepository.Signature;
import de.cau.se.slastic.metamodel.usage.Message;
import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.SynchronousCallMessage;
import de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage;
import de.cau.se.slastic.metamodel.usage.UsageModel;
import de.cau.se.slastic.metamodel.usage.ValidExecutionTrace;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class UsageAndAssemblyModelUpdater {
	private static final Log LOG = LogFactory.getLog(UsageAndAssemblyModelUpdater.class);

	private static final String CEP_QUERY = "select * from "
			+ ValidExecutionTrace.class.getName();

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
	public UsageAndAssemblyModelUpdater(final EPServiceProvider epService,
			final ModelManager modelManager) {
		this.epService = epService;
		this.modelManager = modelManager;
		this.typeRepositoryModelManager = this.modelManager.getTypeRepositoryManager();
		this.assemblyModelManager = this.modelManager.getComponentAssemblyModelManager();
		this.usageModelManager = this.modelManager.getUsageModelManager();

		final EPStatement statement =
				this.epService.getEPAdministrator().createEPL(UsageAndAssemblyModelUpdater.CEP_QUERY);
		statement.setSubscriber(this);
	}

	/**
	 * Processes {@link ValidExecutionTrace}s by updating the
	 * {@link ComponentAssemblyModel} and the {@link UsageModel} via the
	 * respective managers.
	 * 
	 * @param validExecutionTrace
	 */
	public void update(final ValidExecutionTrace validExecutionTrace) {
		if (this.processTrace(validExecutionTrace)) {
			UsageAndAssemblyModelUpdater.LOG.info("Succesfully processed trace: " + validExecutionTrace);
		} else {
			UsageAndAssemblyModelUpdater.LOG.error("Failed to process trace: " + validExecutionTrace);
		}
	}

	private boolean processTrace(final ValidExecutionTrace validExecutionTrace) {
		final MessageTrace mt = validExecutionTrace.getMessageTrace();

		final Stack<ExecutionCallRelationships> executionCallRelationships = new Stack<ExecutionCallRelationships>();
		final Map<Operation, Long> operationCallFrequencies = new HashMap<Operation, Long>();
		// TODO: assemblyConnectorCallFrequencies
		for (final Message message : mt.getMessages()) {
			final DeploymentComponentOperationExecution sender =
					(DeploymentComponentOperationExecution) message.getSender();
			final DeploymentComponentOperationExecution receiver =
					(DeploymentComponentOperationExecution) message.getReceiver();

			if (message instanceof SynchronousCallMessage) {
				if (!(message.getSender() instanceof DeploymentComponentOperationExecution)
						|| !(message.getReceiver() instanceof DeploymentComponentOperationExecution)) {
					UsageAndAssemblyModelUpdater.LOG.error("Currently only supporting "
							+ DeploymentComponentOperationExecution.class.getName() + "s");
					return false;
				}

				/*
				 * Update receiver's (callee's) operation call frequency
				 */
				final Long operationCallFrequency = operationCallFrequencies.get(receiver.getOperation());
				if (operationCallFrequency == null) {
					operationCallFrequencies.put(receiver.getOperation(), 1l);
				} else {
					operationCallFrequencies.put(receiver.getOperation(), operationCallFrequency + 1);
				}

				/*
				 * Create receiver's (callee's) calling relationship for called
				 * operation (will be modified on returns)
				 */
				final ExecutionCallRelationships executionCallRelationship = new ExecutionCallRelationships(receiver);
				executionCallRelationships.push(executionCallRelationship);
			} else if (message instanceof SynchronousReplyMessage) {
				/*
				 * Pop calling relationship of returned execution
				 * (callee/sender)
				 */
				final ExecutionCallRelationships executionCallRelationshipCallee = executionCallRelationships.pop();
				// Validity check during development; can be removed as soon as
				// implementation stable
				if (!executionCallRelationshipCallee.getExecution().equals(sender)) {
					UsageAndAssemblyModelUpdater.LOG.error("Executions do not match: "
							+ executionCallRelationshipCallee.getExecution() + " vs. " + sender);
					return false;
				}

				if (!receiver.equals(UsageModelManager.rootExec)) {
					// TODO: executionCallRelationshipCallee: update usage model
					// or
					// store processed relationship
					// in list (would provide better testability)

					/*
					 * Update receiver's (i.e., caller's) calling relationship
					 */
					final ExecutionCallRelationships executionCallRelationshipCaller =
							executionCallRelationships.peek();
					// Validity check during development; can be removed as soon
					// as
					// implementation stable
					if (!executionCallRelationshipCaller.getExecution().equals(receiver)) {
						UsageAndAssemblyModelUpdater.LOG.error("Executions do not match: "
								+ executionCallRelationshipCaller.getExecution() + " vs. " + receiver);
						return false;
					}
					final AssemblyComponent requiringComponent =
							receiver.getDeploymentComponent().getAssemblyComponent();
					final AssemblyComponent providingComponent = sender.getDeploymentComponent().getAssemblyComponent();
					final Signature signature = sender.getOperation().getSignature();

					AssemblyComponentConnector connector =
							this.assemblyModelManager.lookupAssemblyConnector(requiringComponent, providingComponent,
									signature);
					if (connector == null) {
						// requiring and providing component aren't connected,
						// yet -> connect
						final Interface iface =
								this.typeRepositoryModelManager.lookupProvidedInterfaceForSignature(
										providingComponent.getComponentType(), signature);
						if (iface == null) {
							UsageAndAssemblyModelUpdater.LOG.error("Callee " + providingComponent
									+ " does not provide interface with given signature " + signature);
							return false;
						}
						
						/*
						 * If if the requiring component doesn't have the interface in its list of
						 * required interfaces, yet -> add it.
						 */
						final ComponentType requiringType = requiringComponent.getComponentType();
						if (!requiringType.getRequiredInterfaces().contains(iface)) {
							this.typeRepositoryModelManager.registerRequiredInterface(requiringType, iface);
						}
						
						final ConnectorType connectorT =
								this.typeRepositoryModelManager.createAndRegisterConnectorType(iface);
						connector = this.assemblyModelManager.createAndRegisterAssemblyConnector(connectorT);
						if (!this.assemblyModelManager.connect(connector, requiringComponent, providingComponent)) {
							UsageAndAssemblyModelUpdater.LOG.error("Failed to connect " + requiringComponent + " and "
									+ providingComponent + " via " + connector);
							return false;
						}

					}

					final Interface calledInterface = connector.getConnectorType().getInterface();
					final String signatureName = signature.getName();
					final String signatureRetType = signature.getReturnType();
					final String[] signatureArgTypes = signature.getParamTypes().toArray(new String[] {});

					final Signature interfaceSignature =
							this.typeRepositoryModelManager.lookupSignature(calledInterface,
									signatureName, signatureRetType, signatureArgTypes);
					executionCallRelationshipCaller.incCount(calledInterface, interfaceSignature);
				}
			} else {
				UsageAndAssemblyModelUpdater.LOG.error("Unexpected message type: " + message);
				return false;
			}
		}

		// TODO: operationCallFrequencies: update usage model or store processed
		// relationship in
		// list (would provide better testability)

		// TODO: Currently, I'd favor a "batch" update of the usage model for
		// traces that have been processed successfully

		return true;
	}
}

class ExecutionCallRelationships {
	private final OperationExecution execution;
	private final Map<Interface, Map<Signature, Long>> interfaceSignatureCallFrequencies =
			new HashMap<Interface, Map<Signature, Long>>();

	public ExecutionCallRelationships(final OperationExecution execution) {
		this.execution = execution;
	}

	/**
	 * Increments the number of calls from this {@link OperationExecution} to
	 * the given {@link Signature} of the given {@link Interface} by one.
	 */
	public void incCount(final Interface iface, final Signature signature) {
		// Lookup signature call frequencies for the required interface
		Map<Signature, Long> signatureCallFrequencies = this.interfaceSignatureCallFrequencies.get(iface);
		if (signatureCallFrequencies == null) { // map doesn't exist, yet
			signatureCallFrequencies = new HashMap<Signature, Long>();
			this.interfaceSignatureCallFrequencies.put(iface, signatureCallFrequencies);
		}

		final Long frequency = signatureCallFrequencies.get(signature);
		if (frequency == null) {
			signatureCallFrequencies.put(signature, 1l);
		} else {
			signatureCallFrequencies.put(signature, frequency + 1);
		}
	}

	/**
	 * @return the execution
	 */
	public final OperationExecution getExecution() {
		return this.execution;
	}

	/**
	 * Returns the call frequencies to other {@link Interface} {@link Signature}
	 * s.
	 * 
	 * @return
	 */
	public Map<Interface, Map<Signature, Long>> getCallFrequencies() {
		return this.interfaceSignatureCallFrequencies;
	}
}
