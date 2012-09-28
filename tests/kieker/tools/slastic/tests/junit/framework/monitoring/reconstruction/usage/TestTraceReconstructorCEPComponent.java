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

package kieker.tools.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;

import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.slastic.metamodel.usage.ExecutionTrace;
import kieker.tools.slastic.metamodel.usage.InvalidExecutionTrace;
import kieker.tools.slastic.metamodel.usage.ValidExecutionTrace;
import kieker.tools.slastic.plugins.slasticImpl.ModelManager;
import kieker.tools.slastic.plugins.slasticImpl.control.ICEPEventReceiver;
import kieker.tools.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import kieker.tools.slastic.plugins.slasticImpl.model.NameUtils;
import kieker.tools.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import kieker.tools.slastic.tests.junit.framework.esper.EPServiceProviderFactory;
import kieker.tools.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

/**
 * Tests the {@link TraceReconstructor} by sending {@link DeploymentComponentOperationExecution} for {@value #NUM_VALID_TRACES_TO_GENERATE} valid and
 * {@link #NUM_INVALID_TRACES_TO_GENERATE} invalid Bookstore traces, generated
 * by ( {@link BookstoreTraceFactory#createBookstoreTrace(ExecutionRecordTransformationFilter, long)} ), and checks of the expected number of
 * {@link ValidExecutionTrace}s and {@link InvalidExecutionTrace}s is send via the event bus.
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestTraceReconstructorCEPComponent extends TestCase {
	private static final Log LOG = LogFactory.getLog(TestTraceReconstructorCEPComponent.class);

	private static final int NUM_VALID_TRACES_TO_GENERATE = 3;
	private static final int NUM_INVALID_TRACES_TO_GENERATE = 2;
	private static final int NUM_EXECUTIONS_TO_REMOVE_FROM_INVALID_TRACES = 1;

	public static final long TRACE_DETECTION_TIMEOUT_MILLIS = 2000;

	/**
	 * Time to wait for CEP to complete.
	 */
	private static final long SHUTDOWN_TIMEOUT_MILLIS = 4000;

	private int nextTraceId = 0;

	// TODO: Register ExceptionHandler
	private final EPServiceProvider epService = EPServiceProviderFactory.createInstanceWithEMFSupport();

	private final ExecutionCounter executionCounter = new ExecutionCounter();

	/**
	 * Constructs a TraceReceiver which registers itself as a subscriber to the {@link EPServiceProvider}.
	 */
	@SuppressWarnings("unused")
	private final TraceReconstructor traceReceiver = new TraceReconstructor(this.epService, TRACE_DETECTION_TIMEOUT_MILLIS);

	private final ValidExecutionTraceCollector validExecutionTraceCollector = new ValidExecutionTraceCollector();

	private final InvalidExecutionTraceCollector invalidExecutionTraceCollector = new InvalidExecutionTraceCollector();

	private final PatternValidator patternTester = new PatternValidator();

	/**
	 * Executes the test.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void testTraceReconstructoreValidAndInvalidTraces() throws InterruptedException {
		this.registerSubscribers();

		// Send unbroken (broken = false) traces:
		this.sendBookstoreTraces(NUM_VALID_TRACES_TO_GENERATE, false);

		// Send broken (broken = true) traces:
		this.sendBookstoreTraces(NUM_INVALID_TRACES_TO_GENERATE, true);

		// We have to wait for this time period, to make sure that all traces
		// are detected
		LOG.info("Waiting " + SHUTDOWN_TIMEOUT_MILLIS + " millis for timeout to elapse");
		Thread.sleep(SHUTDOWN_TIMEOUT_MILLIS);

		this.checkResults();
	}

	/**
	 * Registers the {@link TraceReconstructor} as CEP listener and the {@link ExecutionCounter}, {@link ValidExecutionTraceCollector} and
	 * {@link InvalidExecutionTraceCollector} as CEP subscribers.
	 */
	private void registerSubscribers() {
		// The TraceReconstructor registers itself

		// Now register the remaining ICEPEventReceivers as subscribers:
		final Collection<ICEPEventReceiver> subscribers = new ArrayList<ICEPEventReceiver>();
		subscribers.add(this.executionCounter);
		subscribers.add(this.validExecutionTraceCollector);
		subscribers.add(this.invalidExecutionTraceCollector);
		subscribers.add(this.patternTester);

		for (final ICEPEventReceiver subscriber : subscribers) {
			// Register an ExecutionCounter
			final EPStatement statement = this.epService.getEPAdministrator().createEPL(subscriber.getCEPStatement());
			statement.setSubscriber(subscriber);
		}
	}

	private void checkResults() {
		// Assert that the ExecutionCounter received the correct number of
		// events
		final int expectedNumTraces =
				(NUM_VALID_TRACES_TO_GENERATE * BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE)
						+ (NUM_INVALID_TRACES_TO_GENERATE * (BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE - NUM_EXECUTIONS_TO_REMOVE_FROM_INVALID_TRACES));
		Assert.assertEquals("Unexpected number of executions received", expectedNumTraces, this.executionCounter.getNumEventsReceived());

		// Assert that the correct number of valid/invalid traces was received
		Assert.assertEquals("Unexpected number of valid traces",
				NUM_VALID_TRACES_TO_GENERATE, this.validExecutionTraceCollector.getTraces().size());
		Assert.assertEquals("Unexpected number of invalid traces",
				NUM_INVALID_TRACES_TO_GENERATE, this.invalidExecutionTraceCollector.getTraces().size());
	}

	/**
	 * Sends the {@link DeploymentComponentOperationExecution}s for {@value #NUM_VALID_TRACES_TO_GENERATE} bookstore traces.
	 */
	private void sendBookstoreTraces(final int numTraces, final boolean breakTraces) {
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter = new ExecutionRecordTransformationFilter(systemModelManager, NameUtils.ABSTRACTION_MODE_CLASS);

		final List<DeploymentComponentOperationExecution> bookstoreExecutions =
				new ArrayList<DeploymentComponentOperationExecution>(
						numTraces * BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE);

		for (int i = 0; i < numTraces; i++) {
			final Collection<DeploymentComponentOperationExecution> bookstoreTrace = BookstoreTraceFactory.createBookstoreTrace(execRecFilter, this.nextTraceId++);
			boolean brokeTrace = false;
			for (final DeploymentComponentOperationExecution exec : bookstoreTrace) {
				if (breakTraces && !brokeTrace) {
					brokeTrace = true;
					continue; // break trace by removing an execution
								// (NUM_EXECUTIONS_TO_REMOVE_FROM_INVALID_TRACES=1,
								// currently)
				}
				bookstoreExecutions.add(exec);
			}
		}

		Collections.shuffle(bookstoreExecutions);

		for (final DeploymentComponentOperationExecution exec : bookstoreExecutions) {
			this.epService.getEPRuntime().sendEvent(exec);
		}
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

	private static final String expression = "select * from " + DeploymentComponentOperationExecution.class.getName();

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
 * This class basically exists to debug the pattern found in {@link TraceReconstructor}.
 * 
 * @author avanhoorn
 * 
 */
class PatternValidator implements ICEPEventReceiver {
	private static final String EXECUTION_EVENT_TYPE = DeploymentComponentOperationExecution.class.getName();

	private static final String EXEC_A = "a";
	private static final String EXEC_B = "b";

	private static final String EXPRESSION_TEMPLATE =
			"select EXEC_A, EXEC_B from "
					+ "pattern [ every EXEC_A=EXECUTION_EVENT_TYPE "
					+ "-> ((EXEC_B=EXECUTION_EVENT_TYPE(traceId=EXEC_A.traceId) OR timer:interval(INTERVAL sec)) until timer:interval(INTERVAL+1 sec)) "
					+ "where timer:within(INTERVAL+5 sec) ] "; // TODO: why the last part?

	/**
	 * The CEP query for call events
	 */
	private static final String EXPRESSION =
			/*
			 * INTERVAL will be replaced in getCEPStatement
			 */
			EXPRESSION_TEMPLATE
					.replaceAll("EXECUTION_EVENT_TYPE", EXECUTION_EVENT_TYPE)
					.replaceAll("EXEC_A", EXEC_A)
					.replaceAll("EXEC_B", EXEC_B)
					.replaceAll("INTERVAL", Long.toString(TestTraceReconstructorCEPComponent.TRACE_DETECTION_TIMEOUT_MILLIS / 1000));

	@Override
	public String getCEPStatement() {
		return EXPRESSION;
	}

	private final ConcurrentMap<Long, Set<DeploymentComponentOperationExecution>> execsPerTrace =
			new ConcurrentHashMap<Long, Set<DeploymentComponentOperationExecution>>();

	public void update(final DeploymentComponentOperationExecution execA, final DeploymentComponentOperationExecution[] execsB) {
		final long traceId = execA.getTraceId();

		synchronized (this.execsPerTrace) {
			Set<DeploymentComponentOperationExecution> executionsInTrace = this.execsPerTrace.get(traceId);
			if (executionsInTrace == null) {
				executionsInTrace = new HashSet<DeploymentComponentOperationExecution>();
				this.execsPerTrace.put(traceId, executionsInTrace);
			}

			executionsInTrace.add(execA);
			if (execsB != null) {
				for (final DeploymentComponentOperationExecution execB : execsB) {
					// In this case, the EPL expression doesn't make sense:
					Assert.assertEquals("Invalid EPL expression: executions with different trace IDs", execA.getTraceId(), execB.getTraceId());
					executionsInTrace.add(execB);
				}
			} else {
				/* trace timed out */
				// TODO: remove output
				System.out.println("Trace with Id " + traceId + " has " + executionsInTrace.size() + " executions");
				this.execsPerTrace.remove(traceId);
			}
		}
	}
}

abstract class AbstractExecutionTraceCollector<H extends ExecutionTrace> implements ICEPEventReceiver {
	/**
	 * Logger not static to capture implementing class name.
	 */
	private static final Log LOG = LogFactory.getLog(AbstractExecutionTraceCollector.class);

	private final Collection<H> traces = new ArrayList<H>();

	/**
	 * @return the traces
	 */
	public final Collection<H> getTraces() {
		return this.traces;
	}

	public void update(final H trace) {
		AbstractExecutionTraceCollector.LOG.info("Received trace: " + trace);
		this.traces.add(trace);
	}
}

class ValidExecutionTraceCollector extends AbstractExecutionTraceCollector<ValidExecutionTrace> {

	private static final String EXPRESSION = "select * from " + ValidExecutionTrace.class.getName();

	@Override
	public String getCEPStatement() {
		return ValidExecutionTraceCollector.EXPRESSION;
	}
}

class InvalidExecutionTraceCollector extends AbstractExecutionTraceCollector<ValidExecutionTrace> {

	private static final String EXPRESSION = "select * from " + InvalidExecutionTrace.class.getName();

	@Override
	public String getCEPStatement() {
		return InvalidExecutionTraceCollector.EXPRESSION;
	}
}
