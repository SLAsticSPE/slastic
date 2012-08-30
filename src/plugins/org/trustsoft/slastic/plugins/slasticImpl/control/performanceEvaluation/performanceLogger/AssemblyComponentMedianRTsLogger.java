package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.tools.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.ComponentType;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class AssemblyComponentMedianRTsLogger extends
		AbstractPerformanceMeasureLogger<AssemblyComponentOperationPair>
		implements IAssemblyComponentMedianOperationExecutionResponseTimeReceiver {

	private static final Log log = LogFactory
			.getLog(AssemblyComponentMedianRTsLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5 * 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5 * 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public AssemblyComponentMedianRTsLogger(final IComponentContext context) {
		this(context, AssemblyComponentMedianRTsLogger.DEFAULT_WIN_TIME_SECONDS,
				AssemblyComponentMedianRTsLogger.DEFAULT_OUTPUT_INTERVAL_SECONDS);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public AssemblyComponentMedianRTsLogger(final IComponentContext context,
			final int winTimeSec, final int outputIntervalSec) {
		super(context);
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;
	}

	/**
	 * 
	 * @param currentTimestampMillis
	 * @param assemblyComponent
	 * @param medianRTMillis
	 *            average response time for the time period or null if no
	 *            observation
	 * @return
	 */
	private final String[] createRow(
			final long currentTimestampMillis,
			final AssemblyComponentOperationPair assemblyComponentOperationPair,
			final Double medianRTMillis) {
		final AssemblyComponent assemblyComponent =
				assemblyComponentOperationPair.getAssemblyComponent();
		final Operation operation =
				assemblyComponentOperationPair.getOperation();

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
				/* 3: operation name (ID): */
				new StringBuilder().append(operation.getSignature().getName())
						.append("(").append(Long.toString(operation.getId()))
						.append(")").toString(),
				/* 4: median RT (milliseconds) */
				medianRTMillis == null ? "NA" : Double.toString(medianRTMillis) };
	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent,
			final Operation operation, final Double medianRTNanos) {
		final Double avgRtsMillis =
				medianRTNanos == null ? null : medianRTNanos / (1000 * 1000);

		final AssemblyComponentOperationPair assemblyComponentOperationPair =
				new AssemblyComponentOperationPair(assemblyComponent, operation);

		super.writeRow(assemblyComponentOperationPair, this.createRow(
				currentTimestampMillis, assemblyComponentOperationPair,
				avgRtsMillis));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "assemblyComponent",
				"operation", "medianRTMillis" };
	}

	@Override
	protected String createFilename(
			final AssemblyComponentOperationPair assemblyComponentOperationPair) {
		final AssemblyComponent assemblyComponent =
				assemblyComponentOperationPair.getAssemblyComponent();
		final Operation operation =
				assemblyComponentOperationPair.getOperation();

		final ComponentType componentType =
				assemblyComponent.getComponentType();

		return (new StringBuilder())
				.append(assemblyComponent.getPackageName())
				.append(".").append(assemblyComponent.getName()).append("-")
				.append(componentType.getPackageName()).append(".")
				.append(componentType.getName()).append(".")
				.append(operation.getSignature().getName()).append("_")
				.append(operation.getId()).append(".csv").toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent, operation, median(tout-tin)"
				+ " from "
				+ DeploymentComponentOperationExecution.class.getName()
				+ ".win:time(" + this.winTimeSec + " sec)"
				+ " group by deploymentComponent.assemblyComponent, operation"
				+ " output all every " + this.outputIntervalSec + " seconds";
	}

	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec="
				+ this.outputIntervalSec;
	}
}
