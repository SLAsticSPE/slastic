package org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction;

import java.util.Collection;

import org.trustsoft.slastic.plugins.slasticImpl.control.ICEPEventReceiver;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.monitoring.OperationExecution;
import de.cau.se.slastic.metamodel.usage.ExecutionTrace;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TraceReconstructor implements ICEPEventReceiver, UpdateListener {
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
	 * 
	 * @param executions
	 * @return
	 */
	public static ExecutionTrace reconstructTrace (final Collection<OperationExecution> executions) {
		final ExecutionTrace retTrace = null;
		
// TODO: implement reconstruction 		
		
//	       final Vector<Message> mSeq = new Vector<Message>();
//	        final Stack<Message> curStack = new Stack<Message>();
//	        final Iterator<Execution> eSeqIt = this.set.iterator();
//
//	        Execution prevE = rootExecution;
//	        int prevEoi = -1;
//	        for (int itNum = 0; eSeqIt.hasNext(); itNum++) {
//	            final Execution curE = eSeqIt.next();
//	            if ((itNum++ == 0) && (curE.getEss() != 0)) {
//	                final InvalidTraceException ex =
//	                        new InvalidTraceException("First execution must have ess "
//	                        + "0 (found " + curE.getEss() + ")\n Causing execution: " + curE);
//	                ExecutionTrace.log.fatal("Found invalid trace:" + ex.getMessage()); // don't need the stack trace here
//	                throw ex;
//	            }
//	            if (prevEoi != curE.getEoi() - 1) {
//	                final InvalidTraceException ex =
//	                        new InvalidTraceException("Eois must increment by 1 --"
//	                        + "but found sequence <" + prevEoi + "," + curE.getEoi() + ">" + "(Execution: " + curE + ")");
//	                ExecutionTrace.log.fatal("Found invalid trace:" + ex.getMessage()); // don't need the stack trace here
//	                throw ex;
//	            }
//	            prevEoi = curE.getEoi();
//
//	            // First, we might need to clean up the stack for the next execution
//	            // callMessage
//	            if ((prevE != rootExecution) && (prevE.getEss() >= curE.getEss())) {
//	                Execution curReturnReceiver; // receiverComponentName of return message
//	                while (curStack.size() > curE.getEss()) {
//	                    final Message poppedCall = curStack.pop();
//	                    prevE = poppedCall.getReceivingExecution(); 
//	                    curReturnReceiver = poppedCall.getSendingExecution();
//	                    final Message m = new SynchronousReplyMessage(prevE.getTout(),
//	                            prevE, curReturnReceiver);
//	                    mSeq.add(m);
//	                    prevE = curReturnReceiver;
//	                }
//	            }
//	            // Now, we handle the current execution callMessage 
//	            if (prevE == rootExecution) { // initial execution callMessage
//	                final Message m = new SynchronousCallMessage(curE.getTin(), rootExecution, curE);
//	                mSeq.add(m);
//	                curStack.push(m);
//	            } else if (prevE.getEss() + 1 == curE.getEss()) { // usual callMessage with senderComponentName and receiverComponentName
//	                final Message m = new SynchronousCallMessage(curE.getTin(), prevE, curE);
//	                mSeq.add(m);
//	                curStack.push(m);
//	            } else if (prevE.getEss() < curE.getEss()) { // detect ess incrementation by > 1
//	                final InvalidTraceException ex =
//	                        new InvalidTraceException("Ess are only allowed to increment by 1 --"
//	                        + "but found sequence <" + prevE.getEss() + "," + curE.getEss() + ">" + "(Execution: " + curE + ")");
//	                ExecutionTrace.log.fatal("Found invalid trace:" + ex.getMessage()); // don't need the stack trace here
//	                throw ex;
//	            }
//	            if (!eSeqIt.hasNext()) { // empty stack completely, since no more executions
//	                Execution curReturnReceiver; // receiverComponentName of return message
//	                while (!curStack.empty()) {
//	                    final Message poppedCall = curStack.pop();
//	                    prevE = poppedCall.getReceivingExecution(); 
//	                    curReturnReceiver = poppedCall.getSendingExecution();
//	                    final Message m = new SynchronousReplyMessage(prevE.getTout(),
//	                            prevE, curReturnReceiver);
//	                    mSeq.add(m);
//	                    prevE = curReturnReceiver;
//	                }
//	            }
//	            prevE = curE; // prepair next loop
//	        }
//	        mt = new MessageTrace(this.getTraceId(), mSeq);
//	        this.messageTrace.set(mt);
//	        return mt;
		
		return retTrace;
	}
}