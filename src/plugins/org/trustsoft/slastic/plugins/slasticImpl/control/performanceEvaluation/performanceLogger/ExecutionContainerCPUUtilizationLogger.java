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
import de.cau.se.slastic.metamodel.executionEnvironment.Resource;
import de.cau.se.slastic.metamodel.executionEnvironment.ResourceSpecification;
import de.cau.se.slastic.metamodel.monitoring.CPUUtilization;

import kieker.tools.util.LoggingTimestampConverter;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class ExecutionContainerCPUUtilizationLogger extends AbstractPerformanceMeasureLogger<Resource> implements IExecutionContainerCPUUtilizationReceiver {

	// private static final Log LOG = LogFactory.getLog(ExecutionContainerCPUUtilizationLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 5 * 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 5 * 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public ExecutionContainerCPUUtilizationLogger(
			final IComponentContext context) {
		this(context, DEFAULT_WIN_TIME_SECONDS, DEFAULT_OUTPUT_INTERVAL_SECONDS);
	}

	/**
	 * @param context
	 * @param winTimeSec
	 * @param outputIntervalSec
	 */
	public ExecutionContainerCPUUtilizationLogger(final IComponentContext context, final int winTimeSec, final int outputIntervalSec) {
		super(context);
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;
	}

	private String[] createRow(final long currentTimestampMillis,
			final Resource resource,
			final Double user, final Double system, final Double wait,
			final Double nice, final Double irq,
			final Double combined, final Double idle) {
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
			/* 4-10 Remaining values */
			user == null ? "NA" : Double.toString(user),
			system == null ? "NA" : Double.toString(system),
			wait == null ? "NA" : Double.toString(wait),
			nice == null ? "NA" : Double.toString(nice),
			irq == null ? "NA" : Double.toString(irq),
			combined == null ? "NA" : Double.toString(combined),
			idle == null ? "NA" : Double.toString(idle), };
	}

	/**
	 * Handler for incoming measurements.
	 */
	@Override
	public void update(final long currentTimestampMillis,
			final Resource resource,
			final Double user, final Double system, final Double wait,
			final Double nice, final Double irq,
			final Double combined, final Double idle) {
		super.writeRow(resource, this.createRow(currentTimestampMillis, resource, user, system, wait, nice, irq, combined, idle));

	}

	@Override
	protected String[] createHeader() {
		return new String[] { "timestamp", "UTCString", "executionContainer", "resource", "user", "system", "wait", "nice", "irq", "combined", "idle" };
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
				+ "current_timestamp as currentTimestampMillis, resource, user"
				+
				", system,  wait, nice, irq, combined, idle"
				+ " from " + CPUUtilization.class.getName()
				// + ".win:time("+this.winTimeSec+" sec)" // 60
				+ " group by resource" + " output last every "
				+ this.outputIntervalSec + " seconds";
	}

	@Override
	protected String createMetaInfoLine() {
		return "winTimeSec=" + this.winTimeSec + "; outputIntervalSec=" + this.outputIntervalSec;
	}
}
