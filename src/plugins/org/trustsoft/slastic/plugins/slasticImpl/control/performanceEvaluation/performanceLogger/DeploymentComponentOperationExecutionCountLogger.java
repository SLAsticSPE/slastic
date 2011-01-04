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
public class DeploymentComponentOperationExecutionCountLogger extends
		AbstractPerformanceMeasureLogger<DeploymentComponent> implements
		IDeploymentComponentArrivalCountReceiver {

	private static final Log log = LogFactory
			.getLog(DeploymentComponentOperationExecutionCountLogger.class);

	public DeploymentComponentOperationExecutionCountLogger(
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
			final DeploymentComponent deplComp, final Long count) {
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
				/* 5: count */
				count == null ? "NA" : Long.toString(count) };

	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final DeploymentComponent deplComp, final Long count) {

		super.writeRow(deplComp,
				this.createRow(currentTimestampMillis, deplComp, count));
	}

	private final String[] HEADER = { "timestamp", "UTCString", "deplCompID",
			"executionContainer", "assemblyComponent;count" };

	@Override
	protected String[] createHeader() {
		return this.HEADER;
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

	private final String epStatement =
			"select "
					+ "current_timestamp as currentTimestampMillis, deploymentComponent, count(*)"
					+ " from "
					+ DeploymentComponentOperationExecution.class.getName()
					+ ".win:time(5 min)" // 60
					+ " group by deploymentComponent"
					+ " output all every 5 minutes";

	@Override
	protected String createEPStatement() {
		return this.epStatement;
	}

}
