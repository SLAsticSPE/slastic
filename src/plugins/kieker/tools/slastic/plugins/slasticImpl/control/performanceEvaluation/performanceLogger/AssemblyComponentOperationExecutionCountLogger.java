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
import kieker.tools.slastic.metamodel.monitoring.DeploymentComponentOperationExecution;
import kieker.tools.slastic.metamodel.typeRepository.ComponentType;
import kieker.tools.slastic.metamodel.typeRepository.Operation;
import kieker.tools.util.LoggingTimestampConverter;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class AssemblyComponentOperationExecutionCountLogger extends AbstractPerformanceMeasureLogger<AssemblyComponentOperationPair>
		implements IAssemblyComponentOperationExecutionCountReceiver {

	// private static final Log LOG = LogFactory.getLog(AssemblyComponentOperationExecutionCountLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public AssemblyComponentOperationExecutionCountLogger(final IComponentContext context) {
		this(context, DEFAULT_WIN_TIME_SECONDS, DEFAULT_OUTPUT_INTERVAL_SECONDS, IO_FLUSH_AFTER_EACH_RECORD_DEFAULT);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public AssemblyComponentOperationExecutionCountLogger(final IComponentContext context, final int winTimeSec, final int outputIntervalSec,
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
	private final String[] createRow(
			final long currentTimestampMillis,
			final AssemblyComponentOperationPair assemblyComponentOperationPair,
			final Long count) {
		final String currentTimeUTCString = LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000));

		final AssemblyComponent assemblyComponent = assemblyComponentOperationPair.getAssemblyComponent();
		final Operation operation = assemblyComponentOperationPair.getOperation();

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
			/* 4: count */
			count == null ? "NA" : Long.toString(count) };
	}

	/**
	 * Handler for incoming {@link AssemblyComponent}'s average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis, final AssemblyComponent assemblyComponent, final Operation operation, final Long count) {

		final AssemblyComponentOperationPair assemblyComponentOperationPair = new AssemblyComponentOperationPair(assemblyComponent, operation);

		super.writeRow(assemblyComponentOperationPair, this.createRow(currentTimestampMillis, assemblyComponentOperationPair, count));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "assemblyComponent", "operation", "count" };
	}

	@Override
	protected String createFilename(final AssemblyComponentOperationPair assemblyComponentOperationPair) {
		final AssemblyComponent assemblyComponent = assemblyComponentOperationPair.getAssemblyComponent();
		final Operation operation = assemblyComponentOperationPair.getOperation();

		final ComponentType componentType = assemblyComponent.getComponentType();

		return (new StringBuilder(Long.toString(assemblyComponent.getId())))
				.append("--").append(assemblyComponent.getPackageName())
				.append(".").append(assemblyComponent.getName()).append("-")
				.append(componentType.getPackageName()).append(".")
				.append(componentType.getName()).append("_")
				.append(operation.getSignature().getName())
				.append("_").append(operation.getId())
				.append(".csv").toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, deploymentComponent.assemblyComponent, operation, count(*)"
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
