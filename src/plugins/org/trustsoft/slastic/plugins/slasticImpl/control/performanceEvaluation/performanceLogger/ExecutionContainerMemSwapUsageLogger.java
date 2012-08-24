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

import de.cau.se.slastic.metamodel.executionEnvironment.ExecutionContainer;
import de.cau.se.slastic.metamodel.executionEnvironment.MemSwapResourceSpecification;
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.monitoring.MemSwapUsage;

import kieker.tools.util.LoggingTimestampConverter;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ExecutionContainerMemSwapUsageLogger extends AbstractPerformanceMeasureLogger<Resource> implements IExecutionContainerMemSwapUsageReceiver {

	// private static final Log LOG = LogFactory.getLog(ExecutionContainerMemSwapUsageLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5 * 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public ExecutionContainerMemSwapUsageLogger(final IComponentContext context) {
		this(context, DEFAULT_WIN_TIME_SECONDS, DEFAULT_OUTPUT_INTERVAL_SECONDS);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public ExecutionContainerMemSwapUsageLogger(final IComponentContext context, final int winTimeSec, final int outputIntervalSec) {
		super(context);
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;
	}

	private String[] createRow(final long currentTimestampMillis, final Resource resource, final Long memUsedBytes, final Long memFreeBytes,
			final Long swapUsedBytes, final Long swapFreeBytes) {
		final String currentTimeUTCString = LoggingTimestampConverter.convertLoggingTimestampToUTCString(currentTimestampMillis * (1000 * 1000));
		Long memCapacityBytes = null;
		Long swapCapacityBytes = null;
		if (resource.getResourceSpecification() instanceof MemSwapResourceSpecification) {
			final MemSwapResourceSpecification memSpec = (MemSwapResourceSpecification) resource.getResourceSpecification();
			memCapacityBytes = memSpec.getMemCapacityBytes();
			swapCapacityBytes = memSpec.getSwapCapacityBytes();
		}
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
			/* 4: */
			memCapacityBytes == null ? "NA" : Long
					.toString(memCapacityBytes),
			/* */
			memUsedBytes == null ? "NA" : Long.toString(memUsedBytes),
			/* */
			memFreeBytes == null ? "NA" : Long.toString(memFreeBytes),
			/* */
			swapCapacityBytes == null ? "NA" : Long
					.toString(swapCapacityBytes),
			/* */
			swapUsedBytes == null ? "NA" : Long.toString(swapUsedBytes),
			/* */
			swapFreeBytes == null ? "NA" : Long.toString(swapFreeBytes),

		};
	}

	@Override
	public void update(final long currentTimestampMillis,
			final Resource resource,
			final Long memUsedBytes, final Long memFreeBytes,
			final Long swapUsedBytes,
			final Long swapFreeBytes) {
		super.writeRow(resource, this.createRow(currentTimestampMillis, resource, memUsedBytes, memFreeBytes, swapUsedBytes, swapFreeBytes));
	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "executionContainer",
			"resource", "memCapacityBytes", "memUsedBytes", "memFreeBytes",
			"swapCapacityBytes", "swapUsedBytes", "swapFreeBytes" };
	}

	@Override
	protected String createFilename(final Resource resource) {
		final ExecutionContainer executionContainer = resource.getExecutionContainer();
		final ResourceSpecification resourceSpecification = resource.getResourceSpecification();

		return (new StringBuilder().append(executionContainer.getName())
				.append("-").append(executionContainer.getId()).append("--")
				.append(resourceSpecification.getName()).append("-")
				.append(resourceSpecification.getId()).append("-")
				.append(".csv")).toString();
	}

	@Override
	protected String createEPStatement() {
		return "select "
				+ "current_timestamp as currentTimestampMillis, resource"
				+
				", memUsedBytes, memFreeBytes "
				+
				", swapUsedBytes, swapFreeBytes"
				+ " from " + MemSwapUsage.class.getName()
				// + ".win:time("+this.winTimeMin+" sec)" // 60
				+ " group by resource" + " output last every "
				+ this.outputIntervalSec + " seconds";
	}

	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec="
				+ this.outputIntervalSec;
	}
}
