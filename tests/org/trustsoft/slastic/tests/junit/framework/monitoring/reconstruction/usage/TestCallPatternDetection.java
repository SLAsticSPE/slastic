package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.ICEPEventReceiver;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.esper.EPServiceProviderFactory;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
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

	private final TraceReconstructor traceReceiver =
			new
			TraceReconstructor(this.epService, TestCallPatternDetection.TRACE_DETECTION_TIMEOUT_MILLIS);

	private void registerSubscribers() {
		// Register an ExecutionCounter
		final EPStatement statement = this.epService.getEPAdministrator().createEPL(
				this.executionCounter.getCEPStatement());
		statement.setSubscriber(this.executionCounter);

		// final Register a TraceReceiver
		final EPStatement statement2 =
				this.epService.getEPAdministrator().createEPL(
						this.traceReceiver.getCEPStatement());
		statement2.addListener(this.traceReceiver);
	}

	private void checkResults() {
		// Assert that the ExecutionCounter received the correct number of
		// events
		Assert.assertEquals("Unexpected number of executions received",
				TestCallPatternDetection.NUM_TRACES_TO_GENERATE
						* BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE,
						this.executionCounter.getNumEventsReceived());
		System.out.println(this.executionCounter.getNumEventsReceived() + " Events");

		// TODO: check reconstructed traces
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

		Collections.shuffle(bookstoreExecutions);

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