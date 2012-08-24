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

package org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger;

import org.trustsoft.slastic.common.IComponentContext;

import de.cau.se.slastic.metamodel.componentAssembly.AssemblyComponent;
import de.cau.se.slastic.metamodel.componentDeployment.DeploymentComponent;
import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import de.cau.se.slastic.metamodel.typeRepository.Operation;

import kieker.tools.util.LoggingTimestampConverter;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class DeploymentComponentOperationExecutionCountLogger extends AbstractPerformanceMeasureLogger<DeploymentComponentOperationPair>
		implements IDeploymentComponentOperationExecutionCountReceiver {

	// private static final Log LOG = LogFactory.getLog(DeploymentComponentOperationExecutionCountLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5 * 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5 * 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public DeploymentComponentOperationExecutionCountLogger(final IComponentContext context) {
		this(context, DEFAULT_WIN_TIME_SECONDS, DEFAULT_OUTPUT_INTERVAL_SECONDS);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public DeploymentComponentOperationExecutionCountLogger(final IComponentContext context, final int winTimeSec, final int outputIntervalSec) {
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
	private final String[] createRow(final long currentTimestampMillis, final DeploymentComponentOperationPair deplComponentOperationPair, final Long count) {
		final String currentTimeUTCString = LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000));

		final DeploymentComponent deplComp = deplComponentOperationPair.getDeploymentComponent();
		final Operation operation = deplComponentOperationPair.getOperation();

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
			/* 5: operation name (ID): */
			new StringBuilder().append(operation.getSignature().getName())
					.append("(").append(Long.toString(operation.getId()))
					.append(")").toString(),
			/* 6: count */
			count == null ? "NA" : Long.toString(count) };

	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis, final DeploymentComponent deplComp, final Operation operation, final Long count) {

		final DeploymentComponentOperationPair deploymentComponentOperationPair = new DeploymentComponentOperationPair(deplComp, operation);

		super.writeRow(deploymentComponentOperationPair, this.createRow(currentTimestampMillis, deploymentComponentOperationPair, count));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "deplCompID", "executionContainer", "assemblyComponent", "operation", "count" };
	}

	@Override
	protected String createFilename(final DeploymentComponentOperationPair deploymentComponentOperationPair) {
		final Operation operation = deploymentComponentOperationPair.getOperation();
		final DeploymentComponent deploymentComponent = deploymentComponentOperationPair.getDeploymentComponent();

		final ExecutionContainer executionContainer = deploymentComponent.getExecutionContainer();
		final AssemblyComponent assemblyComponent = deploymentComponent.getAssemblyComponent();

		return (new StringBuilder(Long.toString(deploymentComponent.getId())))
				.append("--").append(executionContainer.getName()).append("-")
				.append(executionContainer.getId()).append("--")
				.append(assemblyComponent.getPackageName()).append(".")
				.append(assemblyComponent.getName()).append(".")
				.append(operation.getSignature().getName()).append("_")
				.append(operation.getId())
				.append(".csv").toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent, operation, count(*)"
				+ " from "
				+ DeploymentComponentOperationExecution.class.getName()
				+ ".win:time(" + this.winTimeSec
				+ " sec)" // 60
				+ " group by deploymentComponent, operation" + " output all every "
				+ this.outputIntervalSec + " seconds";

	}

	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec="
				+ this.outputIntervalSec;
	}
}
