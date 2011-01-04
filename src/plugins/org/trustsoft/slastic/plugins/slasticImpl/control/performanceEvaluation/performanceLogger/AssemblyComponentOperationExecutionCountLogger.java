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
public class AssemblyComponentOperationExecutionCountLogger extends
		AbstractPerformanceMeasureLogger<AssemblyComponent> implements
		IAssemblyComponentArrivalCountReceiver {

	private static final Log log = LogFactory
			.getLog(AssemblyComponentOperationExecutionCountLogger.class);

	public AssemblyComponentOperationExecutionCountLogger(
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
				new StringBuilder()
						.append(assemblyComponent.getPackageName())
						.append(".")
						.append(assemblyComponent.getName())
						.append("(")
						.append(Long.toString(assemblyComponent.getId()))
						.append(")")
						.toString(),
				/* 3: count */
				count == null ? "NA" : Long.toString(count) };
	}

	/**
	 * Handler for incoming {@link AssemblyComponent}'s average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final AssemblyComponent assemblyComponent, final Long count) {

		super.writeRow(
				assemblyComponent,
				this.createRow(currentTimestampMillis, assemblyComponent, count));
	}

	private final String[] HEADER = { "timestamp", "UTCString",
			"assemblyComponent", "count" };

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

	private final String epStatement =
			"select "
					+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent, count(*)"
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
