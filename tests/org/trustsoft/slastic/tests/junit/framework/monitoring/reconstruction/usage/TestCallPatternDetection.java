package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.esper.EPServiceProviderFactory;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.collection.Pair;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
 * 
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestCallPatternDetection extends TestCase {
	private static final int NUM_TRACES_TO_GENERATE = 3;

	private static final long TRACE_DETECTION_TIMEOUT_MILLIS = 5000;

	// TODO: Register ExceptionHandler
	private final EPServiceProvider epService =
			EPServiceProviderFactory.createInstanceWithEMFSupport();

	private final ExecutionCounter executionCounter = new ExecutionCounter();
	// private final CallPatternReceiver callPatternReceiver = new
	// CallPatternReceiver();
	private final TraceReceiver traceReceiver =
			new
			TraceReceiver(TestCallPatternDetection.TRACE_DETECTION_TIMEOUT_MILLIS);

	// private final TraceReceiver2 traceReceiver2 =
	// new
	// TraceReceiver2(TestCallPatternDetection.TRACE_DETECTION_TIMEOUT_MILLIS);

	private void registerSubscribers() {
		// Register an ExecutionCounter
		final EPStatement statement = this.epService.getEPAdministrator().createEPL(
				this.executionCounter.getCEPStatement());
		statement.setSubscriber(this.executionCounter);

		// Register a CallPatternReceiver
		// final EPStatement statement2 =
		// this.epService.getEPAdministrator().createEPL(
		// this.callPatternReceiver.getCEPStatement());
		// statement2.addListener(this.callPatternReceiver);

		// final Register a TraceReceiver
		final EPStatement statement2 =
				this.epService.getEPAdministrator().createEPL(
						this.traceReceiver.getCEPStatement());
		statement2.addListener(this.traceReceiver);

		// final EPStatement statement3 =
		// this.epService.getEPAdministrator().createEPL(
		// this.traceReceiver2.getCEPStatement());
		// statement3.addListener(this.traceReceiver2);
	}

	private void checkResults() {
		// Assert that the ExecutionCounter received the correct number of
		// events
		Assert.assertEquals("Unexpected number of executions received",
				TestCallPatternDetection.NUM_TRACES_TO_GENERATE
						* BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE,
						this.executionCounter.getNumEventsReceived());
		System.out.println(this.executionCounter.getNumEventsReceived() + " Events");

		// for (final Pair<DeploymentComponentOperationExecution,
		// DeploymentComponentOperationExecution> p : this.callPatternReceiver
		// .getCalls()) {
		// System.out.println(p);
		// }
		// System.out.println(this.callPatternReceiver.getCalls().size() +
		// " Pairs");
	}

	/**
	 * Sends the {@link DeploymentComponentOperationExecution}s for
	 * {@value #NUM_TRACES_TO_GENERATE} bookstore traces.
	 */
	private void sendBookstoreTraces() {
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);
		;

		final List<DeploymentComponentOperationExecution> bookstoreExecutions =
				new ArrayList<DeploymentComponentOperationExecution>(TestCallPatternDetection.NUM_TRACES_TO_GENERATE
						* BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE);

		for (int i = 0; i < TestCallPatternDetection.NUM_TRACES_TO_GENERATE; i++) {
			final Collection<DeploymentComponentOperationExecution> bookstoreTrace =
					BookstoreTraceFactory.createBookstoreTrace(execRecFilter, i);
			bookstoreExecutions.addAll(bookstoreTrace);
		}

		// Collections.shuffle(bookstoreExecutions);

		for (final DeploymentComponentOperationExecution exec : bookstoreExecutions) {
			this.epService.getEPRuntime().sendEvent(exec);
		}
	}

	/**
	 * @throws InterruptedException
	 * 
	 */
	public void testCreateSendReceiveEvent() throws InterruptedException {
		this.registerSubscribers();

		this.sendBookstoreTraces();
		// We have to wait this time, to make sure that all traces are detected
		Thread.sleep(TestCallPatternDetection.TRACE_DETECTION_TIMEOUT_MILLIS);

		this.checkResults();
	}

}

interface ICEPEventReceiver {
	public String getCEPStatement();
}

/**
 * Counts the total number of {@link DeploymentComponentOperationExecution}s.
 * 
 * @author Andre van Hoorn
 * 
 */
class ExecutionCounter implements ICEPEventReceiver {
	private final AtomicInteger numEventsReceived = new AtomicInteger(0);

	private static final String expression = "select * from "
			+ DeploymentComponentOperationExecution.class.getName();

	@Override
	public String getCEPStatement() {
		return ExecutionCounter.expression;
	}

	public void update(final DeploymentComponentOperationExecution exec) {
		this.numEventsReceived.incrementAndGet();
	}

	/**
	 * @return the numEventsReceived
	 */
	public int getNumEventsReceived() {
		return this.numEventsReceived.get();
	}
}

/**
 * 
 * @author Andre van Hoorn
 * 
 */
class TraceReceiver implements ICEPEventReceiver, UpdateListener {
	private final Collection<Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>> calls =
			new ArrayList<Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>>();

	private static final String EXECUTION_EVENT_TYPE = DeploymentComponentOperationExecution.class.getName();
	private static final String VAR_NAME = "a_traceId";

	private final long traceDetectionTimeOutMillis;

	public TraceReceiver(final long traceDetectionTimeOutMillis) {
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
			TraceReceiver.EXPRESSION_TEMPLATE
					.replaceAll("EXECUTION_EVENT_TYPE", TraceReceiver.EXECUTION_EVENT_TYPE)
					.replaceAll("VAR_NAME", TraceReceiver.VAR_NAME);

	@Override
	public String getCEPStatement() {
		return TraceReceiver.EXPRESSION
				.replaceAll("INTERVAL", Long.toString(this.traceDetectionTimeOutMillis / 1000));
	}

	@Override
	public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {
		// newEvents contains an array of DeploymentComponentOperationExecution
		// for a single trace id.
		System.out.println(newEvents.length + " newEvents");
		for (final EventBean newEvent : newEvents) {
			final DeploymentComponentOperationExecution[] exec1n =
					(DeploymentComponentOperationExecution[]) newEvent.get(TraceReceiver.VAR_NAME);
			System.out.println("New trace: " + exec1n[0].getTraceId());
			for (final DeploymentComponentOperationExecution exec : exec1n) {
				System.out.println(exec);
			}
		}
	}

	/**
	 * @return the calls
	 */
	public Collection<Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>> getCalls() {
		return this.calls;
	}
}