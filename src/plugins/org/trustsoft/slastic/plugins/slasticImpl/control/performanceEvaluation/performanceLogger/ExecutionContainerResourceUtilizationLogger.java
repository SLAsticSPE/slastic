package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.common.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.monitoring.ResourceUtilization;

/**
 * 
 * @author Andre van Hoorn
 *
 */
public class ExecutionContainerResourceUtilizationLogger extends
		AbstractPerformanceMeasureLogger<Resource> implements
		IExecutionContainerResourceUtilizationReceiver {

	private static final Log log = LogFactory
			.getLog(ExecutionContainerResourceUtilizationLogger.class);

	public ExecutionContainerResourceUtilizationLogger(
			final IComponentContext context) {
		super(context);
	}

	/**
	 * 
	 * @param currentTimestampMillis
	 * @param deplComp
	 * @param avgRTMillis
	 *            average response time for the time period or null if no
	 *            observation
	 * @return
	 */
	private final String[] createRow(final long currentTimestampMillis,
			final Resource resource, final Double utilization) {

		final String currentTimeUTCString =
				LoggingTimestampConverter
						.convertLoggingTimestampToUTCString(currentTimestampMillis
								* (1000 * 1000));
		return new String[] {
				/* 0: timestamp: */
				Long.toString(currentTimestampMillis),
				/* 1: human-readable UTC string: */
				currentTimeUTCString,
				/* 2: execution container name + id: */
				new StringBuilder()
						.append(resource.getExecutionContainer().getName())
						.append("(")
						.append(resource.getExecutionContainer().getId())
						.append(")").toString(),
				/* 3: resource (specification) name + id */
				new StringBuilder()
						.append(resource.getResourceSpecification().getName())
						.append("(")
						.append(resource.getResourceSpecification().getId())
						.append(")").toString(),
				/* 4: utilization */
				utilization == null ? "NA" : Double.toString(utilization) };
	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final Resource resource, final Double utilization) {

		super.writeRow(resource,
				this.createRow(currentTimestampMillis, resource, utilization));
	}

	private final String[] HEADER = { "timestamp", "UTCString",
			"executionContainer", "resource", "utilization" };

	@Override
	protected String[] createHeader() {
		return this.HEADER;
	}

	@Override
	protected String createFilename(final Resource resource) {
		final ExecutionContainer executionContainer =
				resource.getExecutionContainer();
		final ResourceSpecification resourceSpecification =
				resource.getResourceSpecification();

		return (new StringBuilder()
				.append(executionContainer.getName()).append("-")
				.append(executionContainer.getId()).append("--")
				.append(resourceSpecification.getName()).append("-")
				.append(resourceSpecification.getId()).append("-")
				.append(".csv")).toString();
	}

	public final String epStatement =
			"select "
					+ "current_timestamp as currentTimestampMillis, resource, utilization"
					+ " from " + ResourceUtilization.class.getName()
					//+ ".win:time(4 min)" // 60
					+ " group by resource" + " output last every 5 minutes";

	@Override
	protected String createEPStatement() {
		return this.epStatement;
	}
}
