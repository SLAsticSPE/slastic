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
import kieker.tools.slastic.metamodel.executionEnvironment.ExecutionContainer;
import kieker.tools.slastic.metamodel.executionEnvironment.Resource;
import kieker.tools.slastic.metamodel.executionEnvironment.ResourceSpecification;
import kieker.tools.slastic.metamodel.monitoring.ResourceUtilization;
import kieker.tools.util.LoggingTimestampConverter;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ExecutionContainerResourceUtilizationLogger extends AbstractPerformanceMeasureLogger<Resource>
		implements IExecutionContainerResourceUtilizationReceiver {

	// private static final Log LOG = LogFactory.getLog(ExecutionContainerResourceUtilizationLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5 * 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5 * 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public ExecutionContainerResourceUtilizationLogger(final IComponentContext context) {
		this(context, DEFAULT_WIN_TIME_SECONDS, DEFAULT_OUTPUT_INTERVAL_SECONDS, IO_FLUSH_AFTER_EACH_RECORD_DEFAULT);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public ExecutionContainerResourceUtilizationLogger(final IComponentContext context, final int winTimeSec, final int outputIntervalSec,
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
	private final String[] createRow(final long currentTimestampMillis, final Resource resource, final Double utilization) {

		final String currentTimeUTCString = LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000));
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
			/* 4: utilization */
			utilization == null ? "NA" : Double.toString(utilization) };
	}

	/**
	 * Handler for incoming deployment components average RTs.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final Resource resource, final Double utilization) {

		super.writeRow(resource,
				this.createRow(currentTimestampMillis, resource, utilization));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "executionContainer", "resource", "utilization" };
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
				+ "current_timestamp as currentTimestampMillis, resource, utilization"
				+ " from " + ResourceUtilization.class.getName()
				// + ".win:time("+this.winTimeMin+" sec)" // 60
				+ " group by resource" + " output last every "
				+ this.outputIntervalSec + " seconds";
	}

	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec=" + this.outputIntervalSec;
	}
}
