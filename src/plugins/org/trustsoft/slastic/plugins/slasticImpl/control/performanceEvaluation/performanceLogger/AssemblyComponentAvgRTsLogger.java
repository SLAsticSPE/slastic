package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.common.util.LoggingTimestampConverter;

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
public class AssemblyComponentAvgRTsLogger extends
		AbstractPerformanceMeasureLogger<AssemblyComponent> implements
		IAssemblyComponentAverageResponseTimeReceiver {

	private static final Log log = LogFactory
			.getLog(AssemblyComponentAvgRTsLogger.class);

	public AssemblyComponentAvgRTsLogger(final IComponentContext context) {
		super(context);
	}

	/**
	 * 
	 * @param currentTimestampMillis
	 * @param assemblyComponent
	 * @param avgRTMillis
	 *            average response time for the time period or null if no
	 *            observation
	 * @return
	 */
	private final String[] createRow(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Double avgRTMillis) {

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
				new StringBuilder()
						.append(assemblyComponent.getPackageName())
						.append(".")
						.append(assemblyComponent.getName())
						.append("(")
						.append(Long.toString(assemblyComponent.getId()))
						.append(")")
						.toString(),
				/* 3: average RT (milliseconds) */
				avgRTMillis == null ? "NA" : Double.toString(avgRTMillis) };
	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Double avgRTNanos) {
		final Double avgRtsMillis =
				avgRTNanos == null ? null : avgRTNanos / (1000 * 1000);

		super.writeRow(assemblyComponent,
				this.createRow(currentTimestampMillis, assemblyComponent,
						avgRtsMillis));
	}

	private final String[] HEADER = { "timestamp", "UTCString",
			"assemblyComponent", "avgRTMillis" };

	@Override
	protected String[] createHeader() {
		return this.HEADER;
	}

	@Override
	protected String createFilename(
			final AssemblyComponent assemblyComponent) {
		final ComponentType componentType =
				assemblyComponent.getComponentType();

		return (new StringBuilder(Long.toString(assemblyComponent.getId())))
				.append("--").append(assemblyComponent.getPackageName())
				.append(".")
				.append(assemblyComponent.getName()).append("-")
				.append(componentType.getPackageName()).append(".")
				.append(componentType.getName()).append(".csv").toString();
	}

	public final String epStatement =
			"select "
					+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent, avg(tout-tin)"
					+ " from "
					+ DeploymentComponentOperationExecution.class.getName()
					+ ".win:time(5 min)" // 60
					+ " group by deploymentComponent.assemblyComponent"
					+ " output all every 5 minutes";

	@Override
	protected String createEPStatement() {
		return this.epStatement;
	}
}
