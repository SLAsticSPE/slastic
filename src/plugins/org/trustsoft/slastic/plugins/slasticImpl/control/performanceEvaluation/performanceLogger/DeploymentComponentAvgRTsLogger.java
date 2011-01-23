package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.common.util.LoggingTimestampConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class DeploymentComponentAvgRTsLogger extends
		AbstractPerformanceMeasureLogger<DeploymentComponent> implements
		IDeploymentComponentAverageResponseTimeReceiver {

	private static final Log log = LogFactory
			.getLog(DeploymentComponentAvgRTsLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5*60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5*60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public DeploymentComponentAvgRTsLogger(final IComponentContext context) {
		this(context, DeploymentComponentAvgRTsLogger.DEFAULT_WIN_TIME_SECONDS,
				DeploymentComponentAvgRTsLogger.DEFAULT_OUTPUT_INTERVAL_SECONDS);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public DeploymentComponentAvgRTsLogger(final IComponentContext context,
			final int winTimeSec, final int outputIntervalSec) {
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
			final DeploymentComponent deplComp, final Double avgRTMillis) {

		final String currentTimeUTCString =
				LoggingTimestampConverter
						.convertLoggingTimestampToUTCString(currentTimestampMillis
								* (1000 * 1000));
		return new String[] {
				/* 0: timestamp: */
				Long.toString(currentTimestampMillis),
				/* 1: human-readable UTC string: */
				currentTimeUTCString,
				/* 2: deployment-component ID: */
				Long.toString(deplComp.getId()),
				/* 3: execution container name + id: */
				new StringBuilder()
						.append(deplComp.getExecutionContainer().getName())
						.append("(")
						.append(deplComp.getExecutionContainer().getId())
						.append(")").toString(),
				/* 4: assembly component name + id */
				new StringBuilder()
						.append(deplComp.getAssemblyComponent().getName())
						.append("(")
						.append(deplComp.getAssemblyComponent().getId())
						.append(")").toString(),
				/* 5: average RT (milliseconds) */
				avgRTMillis == null ? "NA" : Double.toString(avgRTMillis) };
	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final DeploymentComponent deplComp, final Double avgRTNanos) {
		final Double avgRtsMillis =
				avgRTNanos == null ? null : avgRTNanos / (1000 * 1000);

		super.writeRow(deplComp,
				this.createRow(currentTimestampMillis, deplComp, avgRtsMillis));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "deplCompID",
				"executionContainer", "assemblyComponent", "avgRTMillis" };
	}

	@Override
	protected String createFilename(
			final DeploymentComponent deploymentComponent) {
		final ExecutionContainer executionContainer =
				deploymentComponent.getExecutionContainer();
		final AssemblyComponent assemblyComponent =
				deploymentComponent.getAssemblyComponent();

		return (new StringBuilder(Long.toString(deploymentComponent.getId())))
				.append("--").append(executionContainer.getName()).append("-")
				.append(executionContainer.getId()).append("--")
				.append(assemblyComponent.getPackageName()).append(".")
				.append(assemblyComponent.getName()).append(".csv").toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent, avg(tout-tin)"
				+ " from "
				+ DeploymentComponentOperationExecution.class.getName()
				+ ".win:time(" + this.winTimeSec
				+ " sec)" // 60
				+ " group by deploymentComponent" + " output all every "
				+ this.outputIntervalSec + " seconds";
	}
	
	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec="
				+ this.outputIntervalSec;
	}
}
