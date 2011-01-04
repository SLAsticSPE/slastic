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

	public static final int DEFAULT_WIN_TIME_MINUTES = 5;
	public static final int DEFAULT_OUTPUT_INTERVAL_MINUTES = 5;

	private final int winTimeMin;
	private final int outputIntervalMin;

	public ExecutionContainerResourceUtilizationLogger(
			final IComponentContext context) {
		this(
				context,
				ExecutionContainerResourceUtilizationLogger.DEFAULT_WIN_TIME_MINUTES,
				ExecutionContainerResourceUtilizationLogger.DEFAULT_OUTPUT_INTERVAL_MINUTES);
	}

	/**
	 * @param context
	 * @param winTimeMin
	 * @param outputIntervalMin
	 */
	public ExecutionContainerResourceUtilizationLogger(
			final IComponentContext context, final int winTimeMin,
			final int outputIntervalMin) {
		super(context);
		this.winTimeMin = winTimeMin;
		this.outputIntervalMin = outputIntervalMin;
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

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "executionContainer",
				"resource", "utilization" };
	}

	@Override
	protected String createFilename(final Resource resource) {
		final ExecutionContainer executionContainer =
				resource.getExecutionContainer();
		final ResourceSpecification resourceSpecification =
				resource.getResourceSpecification();

		return (new StringBuilder().append(executionContainer.getName())
				.append("-").append(executionContainer.getId()).append("--")
				.append(resourceSpecification.getName()).append("-")
				.append(resourceSpecification.getId()).append("-")
				.append(".csv")).toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, resource, utilization"
				+ " from " + ResourceUtilization.class.getName()
				// + ".win:time("+this.winTimeMin+" min)" // 60
				+ " group by resource" + " output last every "
				+ this.outputIntervalMin + " minutes";
	}
	
	@Override
	protected String createMetaInfoLine() {
		return "winTimeMin=" + this.winTimeMin + "; outputIntervalMin="
				+ this.outputIntervalMin;
	}
}
