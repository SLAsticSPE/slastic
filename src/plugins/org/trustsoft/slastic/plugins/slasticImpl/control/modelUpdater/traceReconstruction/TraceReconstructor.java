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

package org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.model.usage.UsageModelManager;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.ExecutionTrace;
import de.cau.se.slastic.metamodel.usage.InvalidExecutionTrace;
import de.cau.se.slastic.metamodel.usage.Message;
import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.SynchronousCallMessage;
import de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage;
import de.cau.se.slastic.metamodel.usage.UsageFactory;
import de.cau.se.slastic.metamodel.usage.ValidExecutionTrace;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TraceReconstructor implements UpdateListener {
	// private static final Log LOG =
	// LogFactory.getLog(TraceReconstructor.class);

	private static final String EXECUTION_EVENT_TYPE = DeploymentComponentOperationExecution.class.getName();
	private static final String VAR_NAME = "a_traceId";

	private final EPServiceProvider epService;

	private final long traceDetectionTimeOutMillis;

	/**
	 * Constructs a {@link TraceReconstructor} which registers itself as a
	 * subscriber to the given {@link EPServiceProvider}.
	 * 
	 * @param epService
	 * @param traceDetectionTimeOutMillis
	 */
	public TraceReconstructor(final EPServiceProvider epService, final long traceDetectionTimeOutMillis) {
		this.epService = epService;
		this.traceDetectionTimeOutMillis = traceDetectionTimeOutMillis;

		final EPStatement statement =
				this.epService.getEPAdministrator().createEPL(
						this.getCEPStatement());
		statement.addListener(this);
	}

	/**
	 * Expression with place holders replaced in {@link #EXPRESSION}.
	 */
	private static final String EXPRESSION_TEMPLATE =
			"select * from EXECUTION_EVENT_TYPE "
					+ "match_recognize ("
					+ "partition by traceId "
					+ "measures A as VAR_NAME "
					+ "pattern (A+) "
					+ "interval INTERVAL seconds "
					+ "define A as true"
					+ ")";

	/**
	 * The CEP query for call events
	 */
	private static final String EXPRESSION =
			/*
			 * INTERVAL will be replaced in getCEPStatement
			 */
			TraceReconstructor.EXPRESSION_TEMPLATE
					.replaceAll("EXECUTION_EVENT_TYPE", TraceReconstructor.EXECUTION_EVENT_TYPE)
					.replaceAll("VAR_NAME", TraceReconstructor.VAR_NAME);

	private String getCEPStatement() {
		return TraceReconstructor.EXPRESSION
				.replaceAll("INTERVAL", Long.toString(this.traceDetectionTimeOutMillis / 1000));
	}

	@Override
	public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {
		// newEvents contains an array of DeploymentComponentOperationExecution
		// for a single trace id.
		for (final EventBean newEvent : newEvents) {
			// Processing a single trace

			final OperationExecution[] exec1n =
					(OperationExecution[]) newEvent.get(TraceReconstructor.VAR_NAME);
			final Collection<OperationExecution> executionsForTrace =
					new ArrayList<OperationExecution>(exec1n.length);

			for (final OperationExecution exec : exec1n) {
				executionsForTrace.add(exec);
			}

			final ExecutionTrace validOrInvalidExecutionTrace =
					TraceReconstructor.reconstructTraceSave(executionsForTrace, UsageModelManager.rootExec);
			this.epService.getEPRuntime().sendEvent(validOrInvalidExecutionTrace);
		}
	}

	/**
	 * Reconstructs an {@link ExecutionTrace}, valid or invalid, from the given
	 * set of {@link OperationExecution}s. The returned trace is of type
	 * {@link ValidExecutionTrace} if a valid trace could be reconstructed from
	 * the given executions (in this case, the property
	 * {@link ValidExecutionTrace#getMessageTrace()} is properly set). If the
	 * trace reconstruction failed, the method returns a trace of type
	 * {@link InvalidExecutionTrace}.
	 * 
	 * @param executions
	 * @param rootExecution
	 * @return the (valid or invalid) {@link ExecutionTrace}
	 */
	public static ExecutionTrace reconstructTraceSave(final Collection<? extends OperationExecution> executions,
			final OperationExecution rootExecution) {
		ExecutionTrace retTrace = null; // make javac happy
		try {
			final MessageTrace mt = TraceReconstructor.reconstructMessageTrace(executions, rootExecution);
			final ValidExecutionTrace validTrace = mt.getExecutionTrace();
			retTrace = validTrace;
		} catch (final IllegalStateException e) {
			final InvalidExecutionTrace invalidTrace = UsageFactory.eINSTANCE.createInvalidExecutionTrace();
			invalidTrace.setErrorMsg(e.getMessage());
			/*
			 * we cannot add all executions to
			 * invalidTrace.getOperationExecutions() and sort this list since
			 * the EList implementation raises an IllegalArgumentException (The
			 * 'no duplicates' constraint is violated) while being sorted.
			 */
			final List<OperationExecution> sortedExecutions = new ArrayList<OperationExecution>(executions.size());
			sortedExecutions.addAll(executions);
			// order executions by eoi
			TraceReconstructor.sortExecutionListByEoiAndCheckTraceId(sortedExecutions);
			invalidTrace.getOperationExecutions().addAll(sortedExecutions);
			retTrace = invalidTrace;
		}
		return retTrace;
	}

	/**
	 * Sorts the given set of {@link OperationExecution}s by
	 * {@link OperationExecution#getEoi()} and checks if all
	 * {@link OperationExecution}s have an equal
	 * {@link OperationExecution#getTraceId()}.
	 * 
	 * @param executions
	 *            each must have the same
	 *            {@link OperationExecution#getTraceId()}
	 * @return true iff all {@link OperationExecution#getTraceId()}s in the set
	 *         are equal
	 */
	private static boolean sortExecutionListByEoiAndCheckTraceId(final List<OperationExecution> executions) {
		final AtomicReference<Boolean> allExecutionsHaveEqualTraceID =
				new AtomicReference<Boolean>(true);

		Collections.sort(executions, new Comparator<OperationExecution>() {

			@Override
			public int compare(final OperationExecution e1, final OperationExecution e2) {
				if (e1.getTraceId() != e2.getTraceId()) {
					allExecutionsHaveEqualTraceID.set(false);
				}

				if (e1.getEoi() < e2.getEoi()) {
					return -1;
				}
				if (e1.getEoi() > e2.getEoi()) {
					return 1;
				}
				return 0;

			}
		});

		return allExecutionsHaveEqualTraceID.get();
	}

	/**
	 * Creates a new {@link SynchronousReplyMessage} with the given properties.
	 * 
	 * @param timestamp
	 * @param sender
	 * @param receiver
	 * @return
	 */
	private static SynchronousReplyMessage createSynchronousReplyMessage(final long timestamp,
			final OperationExecution sender, final OperationExecution receiver) {
		final SynchronousReplyMessage m =
				UsageFactory.eINSTANCE.createSynchronousReplyMessage();
		m.setTimestamp(timestamp);
		m.setSender(sender);
		m.setReceiver(receiver);
		return m;
	}

	/**
	 * Creates a new {@link SynchronousCallMessage} with the given properties.
	 * 
	 * @param timestamp
	 * @param sender
	 * @param receiver
	 * @return
	 */
	private static SynchronousCallMessage createSynchronousCallMessage(final long timestamp,
			final OperationExecution sender, final OperationExecution receiver) {
		final SynchronousCallMessage m =
				UsageFactory.eINSTANCE.createSynchronousCallMessage();
		m.setTimestamp(timestamp);
		m.setSender(sender);
		m.setReceiver(receiver);
		return m;
	}

	/**
	 * Creates a new {@link MessageTrace} with the given trace id and
	 * {@link Message}s.
	 * 
	 * @param traceId
	 * @param messages
	 * @return
	 */
	private static final MessageTrace createMessageTrace(final long traceId, final List<Message> messages,
			final ValidExecutionTrace validExecutionTrace) {
		final MessageTrace mt = UsageFactory.eINSTANCE.createMessageTrace();
		mt.setTraceId(traceId);
		mt.getMessages().addAll(messages);
		mt.setExecutionTrace(validExecutionTrace);
		return mt;
	}

	/**
	 * Works just like
	 * {@link kieker.tools.traceAnalysis.systemModel.ExecutionTrace#toMessageTrace(kieker.tools.traceAnalysis.systemModel.Execution)}
	 * (code has been copied/ported in large parts).
	 * 
	 * @param executions
	 *            each must have the same
	 *            {@link OperationExecution#getTraceId()}
	 * @throws IllegalStateException
	 *             if the trace is invalid
	 * @return
	 */
	public static MessageTrace reconstructMessageTrace(final Collection<? extends OperationExecution> executions,
			final OperationExecution rootExecution) throws IllegalStateException {
		final List<Message> mSeq =
				/*
				 * 2 messages per execution (call and reply)
				 */
				new ArrayList<Message>(executions.size() * 2);
		final Stack<Message> curStack = new Stack<Message>();

		long traceId = -1;

		final List<OperationExecution> sortedExecutions = new ArrayList<OperationExecution>(executions.size());
		sortedExecutions.addAll(executions);

		if (!TraceReconstructor.sortExecutionListByEoiAndCheckTraceId(sortedExecutions)) {
			throw new IllegalStateException("Not all executions have an equal trace id");
		}

		final Iterator<OperationExecution> eSeqIt = sortedExecutions.iterator();

		OperationExecution prevE = rootExecution;
		int prevEoi = -1;
		for (int itNum = 0; eSeqIt.hasNext(); itNum++) {
			final OperationExecution curE = eSeqIt.next();

			if (itNum == 0) {
				// Retrieve trace id from first execution in list
				traceId = curE.getTraceId();
			} else {
				// Make sure that the trace id of all executions are equal
				if (traceId != curE.getTraceId()) {
					throw new IllegalStateException("All executions must have an equal trace id; "
									+ "expecting " + traceId + ", found " + curE.getTraceId()
							+ ")\n Causing execution: " + curE);
				}
			}

			if ((itNum++ == 0) && (curE.getEss() != 0)) {
				throw new IllegalStateException("First execution must have ess "
								+ "0 (found " + curE.getEss() + ")\n Causing execution: " + curE);
			}
			if (prevEoi != curE.getEoi() - 1) {
				throw new IllegalStateException("Eois must increment by 1 --"
								+ "but found sequence <" + prevEoi + "," + curE.getEoi() + ">" + "(Execution: " + curE
								+ ")");
			}
			prevEoi = curE.getEoi();

			// First, we might need to clean up the stack for the next execution
			// callMessage
			if ((prevE != rootExecution) && (prevE.getEss() >= curE.getEss())) {
				OperationExecution curReturnReceiver; // receiverComponentName
														// of reply message
				while (curStack.size() > curE.getEss()) {
					final Message poppedCall = curStack.pop();
					prevE = poppedCall.getReceiver();
					curReturnReceiver = poppedCall.getSender();
					final Message m = TraceReconstructor.createSynchronousReplyMessage(prevE.getTout(),
							prevE, curReturnReceiver);
					mSeq.add(m);
					prevE = curReturnReceiver;
				}
			}
			// Now, we handle the current execution callMessage
			if (prevE == rootExecution) { // initial execution callMessage
				final Message m = TraceReconstructor.createSynchronousCallMessage(curE.getTin(), rootExecution, curE);
				mSeq.add(m);
				curStack.push(m);
			} else if (prevE.getEss() + 1 == curE.getEss()) {
				// usual callMessage with senderComponentName and
				// receiverComponentName
				final Message m = TraceReconstructor.createSynchronousCallMessage(curE.getTin(), prevE, curE);
				mSeq.add(m);
				curStack.push(m);
			} else if (prevE.getEss() < curE.getEss()) {
				// detect ess incrementation by > 1
				final IllegalStateException ex =
						new IllegalStateException("Ess are only allowed to increment by 1 --"
								+ "but found sequence <" + prevE.getEss() + "," + curE.getEss() + ">" + "(Execution: "
								+ curE + ")");
				throw ex;
			}
			if (!eSeqIt.hasNext()) { // empty stack completely, since no more
										// executions
				OperationExecution curReturnReceiver; // receiverComponentName
														// of reply
				// message
				while (!curStack.empty()) {
					final Message poppedCall = curStack.pop();
					prevE = poppedCall.getReceiver();
					curReturnReceiver = poppedCall.getSender();
					final Message m =
							TraceReconstructor.createSynchronousReplyMessage(prevE.getTout(), prevE, curReturnReceiver);
					mSeq.add(m);
					prevE = curReturnReceiver;
				}
			}
			prevE = curE; // prepair next loop
		}

		final ValidExecutionTrace validExecTrace = UsageFactory.eINSTANCE.createValidExecutionTrace();
		validExecTrace.setTraceId(traceId);
		validExecTrace.getOperationExecutions().addAll(sortedExecutions);
		final MessageTrace mt = TraceReconstructor.createMessageTrace(traceId, mSeq, validExecTrace);
		return mt;
	}
}