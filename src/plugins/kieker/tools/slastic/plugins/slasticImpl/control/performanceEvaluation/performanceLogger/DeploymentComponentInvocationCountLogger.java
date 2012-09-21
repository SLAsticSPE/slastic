/***************************************************************************
 * Copyright 2012 The SLAstic project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/

package kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import kieker.tools.slastic.common.IComponentContext;
import kieker.tools.slastic.metamodel.componentAssembly.AssemblyComponent;
import kieker.tools.slastic.metamodel.componentDeployment.DeploymentComponent;
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.util.LoggingTimestampConverter;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class DeploymentComponentInvocationCountLogger extends AbstractPerformanceMeasureLogger<DeploymentComponent>
		implements IDeploymentComponentInvocationCountReceiver {

	// private static final Log LOG = LogFactory.getLog(DeploymentComponentInvocationCountLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5 * 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5 * 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public DeploymentComponentInvocationCountLogger(final IComponentContext context) {
		this(context, DEFAULT_WIN_TIME_SECONDS, DEFAULT_OUTPUT_INTERVAL_SECONDS, IO_FLUSH_AFTER_EACH_RECORD_DEFAULT);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public DeploymentComponentInvocationCountLogger(final IComponentContext context, final int winTimeSec, final int outputIntervalSec,
			final boolean ioFlushAfterEachRecord) {
		super(context, ioFlushAfterEachRecord);
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
	private final String[] createRow(final long currentTimestampMillis, final DeploymentComponent deplComp, final Long count) {
		final String currentTimeUTCString = LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000));

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
	public void update(final long currentTimestampMillis, final DeploymentComponent deplComp, final Long count) {

		super.writeRow(deplComp, this.createRow(currentTimestampMillis, deplComp, count));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "deplCompID", "executionContainer", "assemblyComponent;count" };
	}

	@Override
	protected String createFilename(final DeploymentComponent deploymentComponent) {
		final ExecutionContainer executionContainer = deploymentComponent.getExecutionContainer();
		final AssemblyComponent assemblyComponent = deploymentComponent.getAssemblyComponent();

		return (new StringBuilder(Long.toString(deploymentComponent.getId())))
				.append("--").append(executionContainer.getName()).append("-")
				.append(executionContainer.getId()).append("--")
				.append(assemblyComponent.getPackageName()).append(".")
				.append(assemblyComponent.getName()).append(".csv").toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent, count(*)"
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
