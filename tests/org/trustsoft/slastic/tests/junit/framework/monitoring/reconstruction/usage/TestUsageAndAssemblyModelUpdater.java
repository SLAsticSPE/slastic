package org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.usage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.plugins.slasticImpl.ModelManager;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.TraceReconstructor;
import org.trustsoft.slastic.plugins.slasticImpl.control.modelUpdater.traceReconstruction.UsageAndAssemblyModelUpdater;
import org.trustsoft.slastic.plugins.slasticImpl.model.NameUtils;
import org.trustsoft.slastic.plugins.slasticImpl.monitoring.kieker.reconstruction.ExecutionRecordTransformationFilter;
import org.trustsoft.slastic.tests.junit.framework.esper.EPServiceProviderFactory;
import org.trustsoft.slastic.tests.junit.framework.monitoring.reconstruction.exampleTraces.BookstoreTraceFactory;

import com.espertech.esper.client.EPServiceProvider;

import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class TestUsageAndAssemblyModelUpdater extends TestCase {
	private static final Log LOG = LogFactory.getLog(TestTraceReconstructorCEPComponent.class);

	private static final int NUM_VALID_TRACES_TO_GENERATE = 3;

	private static final long TRACE_DETECTION_TIMEOUT_MILLIS = 2000;

	/**
	 * Time to wait for CEP to complete.
	 */
	private static final long SHUTDOWN_TIMEOUT_MILLIS = 4000;

	private int nextTraceId = 0;

	final ModelManager systemModelManager = new ModelManager();

	// TODO: Register ExceptionHandler
	private final EPServiceProvider epService =
			EPServiceProviderFactory.createInstanceWithEMFSupport();

	/**
	 * Constructs a {@link TraceReconstructor} which registers itself as a
	 * subscriber to the {@link EPServiceProvider}.
	 */
	@SuppressWarnings("unused")
	private final TraceReconstructor traceReceiver =
			new TraceReconstructor(this.epService, TestUsageAndAssemblyModelUpdater.TRACE_DETECTION_TIMEOUT_MILLIS);

	/**
	 * Constructs a {@link UsageAndAssemblyModelUpdater} which registers itself
	 * as a subscriber to the {@link EPServiceProvider}.
	 */
	@SuppressWarnings("unused")
	private final UsageAndAssemblyModelUpdater usageAndAssemblyModelUpdater =
			new UsageAndAssemblyModelUpdater(this.epService, this.systemModelManager);

	/**
	 * Executes the test.
	 * 
	 * @throws InterruptedException
	 * 
	 */
	public void testUsageAndAssemblyModelUpdater() throws InterruptedException {
		// Send unbroken (broken = false) traces:
		this.sendBookstoreTraces(TestUsageAndAssemblyModelUpdater.NUM_VALID_TRACES_TO_GENERATE);

		// We have to wait for this time period, to make sure that all traces
		// are detected
		TestUsageAndAssemblyModelUpdater.LOG.info("Waiting "
				+ TestUsageAndAssemblyModelUpdater.SHUTDOWN_TIMEOUT_MILLIS + " millis for timeout to elapse");
		Thread.sleep(TestUsageAndAssemblyModelUpdater.SHUTDOWN_TIMEOUT_MILLIS);

		// this.checkResults();
	}

	/**
	 * Sends the {@link DeploymentComponentOperationExecution}s for
	 * {@value #NUM_VALID_TRACES_TO_GENERATE} bookstore traces.
	 */
	private void sendBookstoreTraces(final int numTraces) {
		final ModelManager systemModelManager = new ModelManager();
		final ExecutionRecordTransformationFilter execRecFilter =
				new ExecutionRecordTransformationFilter(systemModelManager,
						NameUtils.ABSTRACTION_MODE_CLASS);

		final List<DeploymentComponentOperationExecution> bookstoreExecutions =
				new ArrayList<DeploymentComponentOperationExecution>(
						numTraces
								* BookstoreTraceFactory.NUM_EXECUTIONS_PER_TRACE);

		for (int i = 0; i < numTraces; i++) {
			final Collection<DeploymentComponentOperationExecution> bookstoreTrace =
					BookstoreTraceFactory.createBookstoreTrace(execRecFilter, this.nextTraceId++);
			for (final DeploymentComponentOperationExecution exec : bookstoreTrace) {
				bookstoreExecutions.add(exec);
			}
		}

		Collections.shuffle(bookstoreExecutions);

		for (final DeploymentComponentOperationExecution exec : bookstoreExecutions) {
			this.epService.getEPRuntime().sendEvent(exec);
		}
	}
}
