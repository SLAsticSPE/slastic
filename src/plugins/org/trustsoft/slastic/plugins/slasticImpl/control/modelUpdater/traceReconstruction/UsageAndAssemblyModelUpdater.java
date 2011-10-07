package org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.model.componentAssembly.ComponentAssemblyModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

import de.cau.se.slastic.metamodel.componentAssembly.ComponentAssemblyModel;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
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
	private final ComponentAssemblyModelManager assemblyModelManager;
	private final UsageModelManager usageModelManager;

	public UsageAndAssemblyModelUpdater(final EPServiceProvider epService,
			final ComponentAssemblyModelManager assemblyModelManager,
			final UsageModelManager usageModelManager) {
		this.epService = epService;
		this.assemblyModelManager = assemblyModelManager;
		this.usageModelManager = usageModelManager;

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
		UsageAndAssemblyModelUpdater.LOG.info("Received trace: " + validExecutionTrace);
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
				 * Create receiver's (callee's) calling relationship for called operation (will be modified on returns)
				 */
				final ExecutionCallRelationships executionCallRelationship = new ExecutionCallRelationships(receiver);
				executionCallRelationships.push(executionCallRelationship);
			} else if (message instanceof SynchronousReplyMessage) {
				/*
				 * Pop calling relationship of returned execution (callee/sender) 
				 */
				final ExecutionCallRelationships executionCallRelationshipCallee = executionCallRelationships.pop();
				// Validity check during development; can be removed as soon as implementation stable
				if (!executionCallRelationshipCallee.getExecution().equals(sender)) {
					UsageAndAssemblyModelUpdater.LOG.error("Executions do not match: " + executionCallRelationshipCallee.getExecution() + " vs. " + sender);
					return false;
				}
				// TODO: update usage model or store processed relationship in list (would provide better testability)
				
				/*
				 * Update receiver's (i.e., caller's) calling relationship
				 */
				final ExecutionCallRelationships executionCallRelationshipCaller = executionCallRelationships.peek();
				// Validity check during development; can be removed as soon as implementation stable
				if (!executionCallRelationshipCaller.getExecution().equals(sender)) {
					UsageAndAssemblyModelUpdater.LOG.error("Executions do not match: " + executionCallRelationshipCaller.getExecution() + " vs. " + receiver);
					return false;
				}
				// TODO: lookup callee's interface
				final Signature signature = sender.getOperation().getSignature();
				// TODO: assemblyManager needs a lookupAssemblyConnector(AssemblyComponent, AssemblyComponent) method
				// TODO: hier weiter!
			} else {
				UsageAndAssemblyModelUpdater.LOG.error("Unexpected message type: " + message);
				return false;
			}
		}

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
