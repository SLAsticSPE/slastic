package org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.jms.net.connector.IllegalStateException;
import org.trustsoft.slastic.plugins.slasticImpl.control.ICEPEventReceiver;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.Message;
import de.cau.se.slastic.metamodel.usage.MessageTrace;
import de.cau.se.slastic.metamodel.usage.SynchronousCallMessage;
import de.cau.se.slastic.metamodel.usage.SynchronousReplyMessage;
import de.cau.se.slastic.metamodel.usage.UsageFactory;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TraceReconstructor implements ICEPEventReceiver, UpdateListener {
	private static final Log LOG = LogFactory.getLog(TraceReconstructor.class);

	private static final String EXECUTION_EVENT_TYPE = DeploymentComponentOperationExecution.class.getName();
	private static final String VAR_NAME = "a_traceId";

	private final EPServiceProvider epService;

	private final long traceDetectionTimeOutMillis;

	public TraceReconstructor(final EPServiceProvider epService, final long traceDetectionTimeOutMillis) {
		this.epService = epService;
		this.traceDetectionTimeOutMillis = traceDetectionTimeOutMillis;
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

	@Override
	public String getCEPStatement() {
		return TraceReconstructor.EXPRESSION
				.replaceAll("INTERVAL", Long.toString(this.traceDetectionTimeOutMillis / 1000));
	}

	@Override
	public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {
		// newEvents contains an array of DeploymentComponentOperationExecution
		// for a single trace id.
		System.out.println(newEvents.length + " newEvents");
		for (final EventBean newEvent : newEvents) {
			final DeploymentComponentOperationExecution[] exec1n =
					(DeploymentComponentOperationExecution[]) newEvent.get(TraceReconstructor.VAR_NAME);
			System.out.println("New trace: " + exec1n[0].getTraceId());
			for (final DeploymentComponentOperationExecution exec : exec1n) {
				System.out.println(exec);
			}

			// TODO: reconstruct trace and send it via epService
		}
	}

	/**
	 * Sorts the given set of {@link OperationExecution}s by
	 * {@link OperationExecution#getEoi()}.
	 * 
	 * @param executions
	 *            each must have the same
	 *            {@link OperationExecution#getTraceId()}
	 * @throws IllegalStateException
	 *             if not all {@link OperationExecution#getTraceId()}s in the
	 *             set are equal
	 */
	private static void sortExecutionSet(final List<OperationExecution> executions) throws IllegalStateException {
		final AtomicReference<IllegalStateException> iseRef =
				new AtomicReference<IllegalStateException>();

		Collections.sort(executions, new Comparator<OperationExecution>() {

			@Override
			public int compare(final OperationExecution e1, final OperationExecution e2) {
				if (e1.getTraceId() != e2.getTraceId()) {
					// will be thrown by the containing method
					iseRef.set(new IllegalStateException("traceId must be equal for each execution " +
							"in the given set. Found " + e1.getTraceId() + " != " + e2.getTraceId()));
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

		if (iseRef.get() != null) {
			throw iseRef.get();
		}
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
	private static final MessageTrace createMessageTrace(final long traceId, final List<Message> messages) {
		final MessageTrace mt = UsageFactory.eINSTANCE.createMessageTrace();
		mt.setTraceId(traceId);
		mt.getMessages().addAll(messages);
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
	public static MessageTrace reconstructTrace(final List<OperationExecution> executions,
			final OperationExecution rootExecution) throws IllegalStateException {
		long traceId = -1;

		final List<Message> mSeq =
				/*
				 * 2 messages per execution (call and reply)
				 */
				new ArrayList<Message>(executions.size() * 2);
		final Stack<Message> curStack = new Stack<Message>();
		final List<OperationExecution> sortedExecutions = new ArrayList<OperationExecution>(executions);
		TraceReconstructor.sortExecutionSet(sortedExecutions);

		final Iterator<OperationExecution> eSeqIt = sortedExecutions.iterator();

		OperationExecution prevE = rootExecution;
		int prevEoi = -1;
		for (int itNum = 0; eSeqIt.hasNext(); itNum++) {
			final OperationExecution curE = eSeqIt.next();

			if (traceId == -1) {
				// Retrieve trace id from first execution in list
				traceId = curE.getTraceId();
			} else {
				// Make sure that the trace id of all executions are equal
				if (traceId != curE.getTraceId()) {
					throw new IllegalStateException("First execution must have ess "
									+ "0 (found " + curE.getEss() + ")\n Causing execution: " + curE);
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
				TraceReconstructor.LOG.fatal("Found invalid trace:" + ex.getMessage());
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
		final MessageTrace mt = TraceReconstructor.createMessageTrace(traceId, mSeq);
		return mt;
	}
}