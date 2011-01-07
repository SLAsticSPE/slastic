package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.common.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ExecutionContainerMemSwapUsageLogger extends
		AbstractPerformanceMeasureLogger<Resource> implements
		IExecutionContainerMemSwapUsageReceiver {

	private static final Log log = LogFactory
			.getLog(ExecutionContainerMemSwapUsageLogger.class);

	public static final int DEFAULT_WIN_TIME_MINUTES = 5;
	public static final int DEFAULT_OUTPUT_INTERVAL_MINUTES = 5;

	private final int winTimeMin;
	private final int outputIntervalMin;

	public ExecutionContainerMemSwapUsageLogger(
			final IComponentContext context) {
		this(
				context,
				ExecutionContainerMemSwapUsageLogger.DEFAULT_WIN_TIME_MINUTES,
				ExecutionContainerMemSwapUsageLogger.DEFAULT_OUTPUT_INTERVAL_MINUTES);
	}

	/**
	 * @param context
	 * @param winTimeMin
	 * @param outputIntervalMin
	 */
	public ExecutionContainerMemSwapUsageLogger(
			final IComponentContext context, final int winTimeMin,
			final int outputIntervalMin) {
		super(context);
		this.winTimeMin = winTimeMin;
		this.outputIntervalMin = outputIntervalMin;
	}

	private String[] createRow(final long currentTimestampMillis,
			final Resource resource,
			final Long memUsedBytes, final Long memFreeBytes,
			final Long swapUsedBytes,
			final Long swapFreeBytes) {
		final String currentTimeUTCString =
				LoggingTimestampConverter
						.convertLoggingTimestampToUTCString(currentTimestampMillis
								* (1000 * 1000));
		Long memCapacityBytes = null;
		Long swapCapacityBytes = null;
		if (resource.getResourceSpecification() instanceof MemSwapResourceSpecification) {
			final MemSwapResourceSpecification memSpec =
					(MemSwapResourceSpecification) resource
							.getResourceSpecification();
			memCapacityBytes = memSpec.getMemCapacityBytes();
			swapCapacityBytes = memSpec.getSwapCapacityBytes();
		}
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
				/* 4: */
				memCapacityBytes == null ? "NA" : Long
						.toString(memCapacityBytes),
				/* */
				memUsedBytes == null ? "NA" : Long.toString(memUsedBytes),
				/* */
				memFreeBytes == null ? "NA" : Long.toString(memFreeBytes),
				/* */
				swapCapacityBytes == null ? "NA" : Long
						.toString(swapCapacityBytes),
				/* */
				swapUsedBytes == null ? "NA" : Long.toString(swapUsedBytes),
				/* */
				swapFreeBytes == null ? "NA" : Long.toString(swapFreeBytes),

		};
	}

	@Override
	public void update(final long currentTimestampMillis,
			final Resource resource,
			final Long memUsedBytes, final Long memFreeBytes,
			final Long swapUsedBytes,
			final Long swapFreeBytes) {
		super.writeRow(resource,
				this.createRow(currentTimestampMillis, resource, memUsedBytes,
						memFreeBytes, swapUsedBytes, swapFreeBytes));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "executionContainer",
				"resource", "memCapacityBytes", "memUsedBytes", "memFreeBytes",
				"swapCapacityBytes", "swapUsedBytes", "swapFreeBytes" };
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
				+ "current_timestamp as currentTimestampMillis, resource"
				+
				", memUsedBytes, memFreeBytes "
				+
				", swapUsedBytes, swapFreeBytes"
				+ " from " + MemSwapUsage.class.getName()
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
