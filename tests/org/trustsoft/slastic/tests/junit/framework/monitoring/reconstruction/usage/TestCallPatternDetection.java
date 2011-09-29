package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
 * @author Andre van Hoorn
 * 
 */
public class TestCallPatternDetection extends TestCase {
	private static final int NUM_TRACES_TO_GENERATE = 10;

	// TODO: Register ExceptionHandler
	private final EPServiceProvider epService =
			EPServiceProviderFactory.createInstanceWithEMFSupport();

	private final ExecutionCounter executionCounter = new ExecutionCounter();
	private final CallPatternReceiver callPatternReceiver = new CallPatternReceiver();

	/**
	 * 
	 */
	public void testCreateSendReceiveEvent() {
		this.registerSubscribers();

		this.sendBookstoreTraces();

		this.checkResults();
	}

	private void registerSubscribers() {
		// Register an ExecutionCounter
		final EPStatement statement = this.epService.getEPAdministrator().createEPL(
				this.executionCounter.getCEPStatement());
		statement.setSubscriber(this.executionCounter);

		// Register an CallPatternReceiver
		final EPStatement statement2 = this.epService.getEPAdministrator().createEPL(
				this.callPatternReceiver.getCEPStatement());
		statement2.addListener(this.callPatternReceiver);
	}

	private void checkResults() {
		// Assert that the ExecutionCounter received the correct number of
		// events
		Assert.assertEquals("Unexpected number of executions received",
				TestCallPatternDetection.NUM_TRACES_TO_GENERATE
						* BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE,
						this.executionCounter.getNumEventsReceived());

		for (final Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution> p : this.callPatternReceiver
				.getCalls()) {
			System.out.println(p);
		}
		System.out.println(this.callPatternReceiver.getCalls().size() + " Pairs");
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
}

interface ICEPEventReceiver {
	public String getCEPStatement();
}

/**
 * Counts the total number of {@link DeploymentComponentOperationExecution}s
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
class CallPatternReceiver implements ICEPEventReceiver, UpdateListener {
	private final Collection<Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>> calls =
			new ArrayList<Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>>();

	private static final String EXECUTION_EVENT_TYPE = DeploymentComponentOperationExecution.class.getName();
	private static final String EXEC0_NAME = "exec0";
	private static final String EXEC1_NAME = "exec1";

	private static final String expression = "select * from pattern ["
			+ "every "
			+ CallPatternReceiver.EXEC0_NAME + "=" + CallPatternReceiver.EXECUTION_EVENT_TYPE
			+ " -> "
			+ CallPatternReceiver.EXEC1_NAME + "=" + CallPatternReceiver.EXECUTION_EVENT_TYPE
			+ "(traceId=" + CallPatternReceiver.EXEC0_NAME +".traceId)"
			+ " ]";

	@Override
	public String getCEPStatement() {
		return CallPatternReceiver.expression;
	}

	@Override
	public void update(final EventBean[] newEvents, final EventBean[] oldEvents) {
		final DeploymentComponentOperationExecution exec0 =
				(DeploymentComponentOperationExecution) newEvents[0].get(CallPatternReceiver.EXEC0_NAME);
		final DeploymentComponentOperationExecution exec1 =
				(DeploymentComponentOperationExecution) newEvents[0].get(CallPatternReceiver.EXEC1_NAME);
		this.calls.add(
				new Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>(exec0, exec1));
	}

	/**
	 * @return the calls
	 */
	public Collection<Pair<DeploymentComponentOperationExecution, DeploymentComponentOperationExecution>> getCalls() {
		return this.calls;
	}
}