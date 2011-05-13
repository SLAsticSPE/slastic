package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.tools.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class AssemblyComponentOperationExecutionCountLogger extends
		AbstractPerformanceMeasureLogger<AssemblyComponent> implements
		IAssemblyComponentArrivalCountReceiver {

	private static final Log log = LogFactory
			.getLog(AssemblyComponentOperationExecutionCountLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public AssemblyComponentOperationExecutionCountLogger(
			final IComponentContext context) {
		this(
				context,
				AssemblyComponentOperationExecutionCountLogger.DEFAULT_WIN_TIME_SECONDS,
				AssemblyComponentOperationExecutionCountLogger.DEFAULT_OUTPUT_INTERVAL_SECONDS);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public AssemblyComponentOperationExecutionCountLogger(
			final IComponentContext context, final int winTimeSec,
			final int outputIntervalSec) {
		super(context);
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;
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
			final AssemblyComponent assemblyComponent, final Long count) {
		final String currentTimeUTCString =
				LoggingTimestampConverter
						.convertLoggingTimestampToUTCString(currentTimestampMillis
								* (1000 * 1000));

		return new String[] {
				/* 0: timestamp: */
				Long.toString(currentTimestampMillis),
				/* 1: human-readable UTC string: */
				currentTimeUTCString,
				/* 2: assembly-component name (ID): */
				new StringBuilder().append(assemblyComponent.getPackageName())
						.append(".").append(assemblyComponent.getName())
						.append("(")
						.append(Long.toString(assemblyComponent.getId()))
						.append(")").toString(),
				/* 3: count */
				count == null ? "NA" : Long.toString(count) };
	}

	/**
	 * Handler for incoming {@link AssemblyComponent}'s average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Long count) {

		super.writeRow(assemblyComponent, this.createRow(
				currentTimestampMillis, assemblyComponent, count));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "assemblyComponent",
				"count" };
	}

	@Override
	protected String createFilename(final AssemblyComponent assemblyComponent) {
		final ComponentType componentType =
				assemblyComponent.getComponentType();

		return (new StringBuilder(Long.toString(assemblyComponent.getId())))
				.append("--").append(assemblyComponent.getPackageName())
				.append(".").append(assemblyComponent.getName()).append("-")
				.append(componentType.getPackageName()).append(".")
				.append(componentType.getName()).append(".csv").toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent, count(*)"
				+ " from "
				+ DeploymentComponentOperationExecution.class.getName()
				+ ".win:time(" + this.winTimeSec + " sec)"
				+ " group by deploymentComponent.assemblyComponent"
				+ " output all every " + this.outputIntervalSec + " seconds";
	}

	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec="
				+ this.outputIntervalSec;
	}
}
