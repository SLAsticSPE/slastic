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

package kieker.tools.slastic.plugins.cloud.slastic.control.performanceEvaluation.performanceLogger;


import com.espertech.esper.client.EPServiceProvider;

import kieker.tools.slastic.common.IComponentContext;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceMeasureLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentAvgRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentInvocationCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentMedianRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentInvocationCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerCPUUtilizationLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerMemSwapUsageLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerResourceUtilizationLogger;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class PerformanceLogger extends AbstractPerformanceLogger {
	// private static final Log LOG = LogFactory.getLog(PerformanceLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public PerformanceLogger(final EPServiceProvider epServiceProvider, final IComponentContext context, final int winTimeSec, final int outputIntervalSec) {
		super(epServiceProvider, context);
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;

		this.startLoggers();
	}

	private IComponentContext createLoggerContext(
			final Class<? extends AbstractPerformanceMeasureLogger<?>> loggerClass,
			final int winTimeSec, final int outputIntervalSec) {
		return this.getContext().createSubcontext(
				loggerClass.getSimpleName() + "-winTimeSec_" + winTimeSec + "-outputIntervalSec_" + outputIntervalSec);
	}

	/**
	 * 
	 */
	private void startLoggers() {
		/**
		 * TODO: Re-activate loggers as soon as problem with the number of
		 * File handles has been resolved
		 */
		/* 1. DeploymentComponentAvgRTsLogger */
		// final DeploymentComponentAvgRTsLogger deploymentComponentAvgRTsLogger =
		// new DeploymentComponentAvgRTsLogger(this.createLoggerContext(
		// DeploymentComponentAvgRTsLogger.class, this.winTimeSec,
		// this.outputIntervalSec), this.winTimeSec,
		// this.outputIntervalSec);
		// this.addAndRegisterLoggerAsSubscriber(deploymentComponentAvgRTsLogger);

		/**
		 * TODO: Re-activate loggers as soon as problem with the number of
		 * File handles has been resolved
		 */
		// /* 2. DeploymentComponentOperationExecutionCountLogger */
		// final DeploymentComponentOperationExecutionCountLogger deploymentComponentOperationExecutionCountLogger =
		// new DeploymentComponentOperationExecutionCountLogger(
		// this.createLoggerContext(
		// DeploymentComponentOperationExecutionCountLogger.class,
		// this.winTimeSec, this.outputIntervalSec),
		// this.winTimeSec, this.outputIntervalSec);
		// this.addAndRegisterLoggerAsSubscriber(deploymentComponentOperationExecutionCountLogger);

		/* 3. DeploymentComponentInvocationCountLogger */
		final DeploymentComponentInvocationCountLogger deploymentComponentInvocationCountLogger =
				new DeploymentComponentInvocationCountLogger(
						this.createLoggerContext(DeploymentComponentInvocationCountLogger.class, this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(deploymentComponentInvocationCountLogger);

		/* 4. AssemblyComponentAvgRTsLogger */
		final AssemblyComponentAvgRTsLogger assemblyComponentAvgRTsLogger =
				new AssemblyComponentAvgRTsLogger(this.createLoggerContext(
						AssemblyComponentAvgRTsLogger.class, this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(assemblyComponentAvgRTsLogger);

		/* 4.1. AssemblyComponentMedianRTsLogger */
		final AssemblyComponentMedianRTsLogger assemblyComponentMedianRTsLogger =
				new AssemblyComponentMedianRTsLogger(this.createLoggerContext(
						AssemblyComponentMedianRTsLogger.class, this.winTimeSec,
						this.outputIntervalSec), this.winTimeSec,
						this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(assemblyComponentMedianRTsLogger);

		/**
		 * TODO: Re-activate loggers as soon as problem with the number of
		 * File handles has been resolved
		 */
		// /* 5. AssemblyComponentOperationExecutionCountLogger */
		// final AssemblyComponentOperationExecutionCountLogger assemblyComponentOperationExecutionCountLogger =
		// new AssemblyComponentOperationExecutionCountLogger(
		// this.createLoggerContext(
		// AssemblyComponentOperationExecutionCountLogger.class,
		// this.winTimeSec, this.outputIntervalSec),
		// this.winTimeSec, this.outputIntervalSec);
		// this.addAndRegisterLoggerAsSubscriber(assemblyComponentOperationExecutionCountLogger);

		/* 6. AssemblyComponentInvocationCountLogger */
		final AssemblyComponentInvocationCountLogger assemblyComponentInvocationCountLogger =
				new AssemblyComponentInvocationCountLogger(
						this.createLoggerContext(
								AssemblyComponentInvocationCountLogger.class, this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(assemblyComponentInvocationCountLogger);

		/* 7. ExecutionContainerResourceUtilizationLogger */
		final ExecutionContainerResourceUtilizationLogger executionContainerResourceUtilizationLogger =
				new ExecutionContainerResourceUtilizationLogger(
						this.createLoggerContext(
								ExecutionContainerResourceUtilizationLogger.class, this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(executionContainerResourceUtilizationLogger);

		/* 8. ExecutionContainerCPUUtilizationLogger */
		final ExecutionContainerCPUUtilizationLogger executionContainerCPUUtilizationLogger =
				new ExecutionContainerCPUUtilizationLogger(
						this.createLoggerContext(
								ExecutionContainerCPUUtilizationLogger.class, this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(executionContainerCPUUtilizationLogger);

		/* 9. ExecutionContainerMemSwapUsageLogger */
		final ExecutionContainerMemSwapUsageLogger executionContainerMemSwapUsageLogger =
				new ExecutionContainerMemSwapUsageLogger(
						this.createLoggerContext(
								ExecutionContainerMemSwapUsageLogger.class, this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(executionContainerMemSwapUsageLogger);
	}
}
