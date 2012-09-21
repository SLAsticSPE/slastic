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

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPServiceProvider;

import kieker.tools.slastic.common.IComponentContext;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceMeasureLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentAvgRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentInvocationCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentMedianRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentOperationExecutionCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentAvgRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentInvocationCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentOperationExecutionCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerCPUUtilizationLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerMemSwapUsageLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerResourceUtilizationLogger;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class PerformanceLogger extends AbstractPerformanceLogger {
	private static final Log LOG = LogFactory.getLog(PerformanceLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 60;

	private final boolean ioAutoFlush;
	private final int winTimeSec;
	private final int outputIntervalSec;
	private final ConcurrentMap<String, Boolean> loggerClassesToActivate = new ConcurrentHashMap<String, Boolean>();

	public PerformanceLogger(final EPServiceProvider epServiceProvider, final IComponentContext context, final int winTimeSec, final int outputIntervalSec,
			final boolean ioAutoFlush, final List<String> loggerClassesToActivate) {
		super(epServiceProvider, context);
		this.ioAutoFlush = ioAutoFlush;
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;
		for (final String loggerClass : loggerClassesToActivate) {
			this.loggerClassesToActivate.put(loggerClass, Boolean.TRUE);
		}
		this.startLoggers();
	}

	private IComponentContext createLoggerContext(
			final Class<? extends AbstractPerformanceMeasureLogger<?>> loggerClass, final int winTimeSec, final int outputIntervalSec) {
		return this.getContext().createSubcontext(
				loggerClass.getSimpleName() + "-winTimeSec_" + winTimeSec + "-outputIntervalSec_" + outputIntervalSec);
	}

	/**
	 * 
	 */
	private void startLoggers() {
		int numLoggerActive = 0;
		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(DeploymentComponentAvgRTsLogger.class.getSimpleName()))) {
			/* 1. DeploymentComponentAvgRTsLogger */
			final DeploymentComponentAvgRTsLogger deploymentComponentAvgRTsLogger =
					new DeploymentComponentAvgRTsLogger(this.createLoggerContext(DeploymentComponentAvgRTsLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(deploymentComponentAvgRTsLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(DeploymentComponentOperationExecutionCountLogger.class.getSimpleName()))) {
			/* 2. DeploymentComponentOperationExecutionCountLogger */
			final DeploymentComponentOperationExecutionCountLogger deploymentComponentOperationExecutionCountLogger =
					new DeploymentComponentOperationExecutionCountLogger(
							this.createLoggerContext(DeploymentComponentOperationExecutionCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(deploymentComponentOperationExecutionCountLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(DeploymentComponentInvocationCountLogger.class.getSimpleName()))) {
			/* 3. DeploymentComponentInvocationCountLogger */
			final DeploymentComponentInvocationCountLogger deploymentComponentInvocationCountLogger =
					new DeploymentComponentInvocationCountLogger(
							this.createLoggerContext(DeploymentComponentInvocationCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(deploymentComponentInvocationCountLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(AssemblyComponentAvgRTsLogger.class.getSimpleName()))) {
			/* 4. AssemblyComponentAvgRTsLogger */
			final AssemblyComponentAvgRTsLogger assemblyComponentAvgRTsLogger =
					new AssemblyComponentAvgRTsLogger(
							this.createLoggerContext(AssemblyComponentAvgRTsLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentAvgRTsLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(AssemblyComponentMedianRTsLogger.class.getSimpleName()))) {
			/* 4.1. AssemblyComponentMedianRTsLogger */
			final AssemblyComponentMedianRTsLogger assemblyComponentMedianRTsLogger =
					new AssemblyComponentMedianRTsLogger(this.createLoggerContext(AssemblyComponentMedianRTsLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentMedianRTsLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(AssemblyComponentOperationExecutionCountLogger.class.getSimpleName()))) {
			/* 5. AssemblyComponentOperationExecutionCountLogger */
			final AssemblyComponentOperationExecutionCountLogger assemblyComponentOperationExecutionCountLogger =
					new AssemblyComponentOperationExecutionCountLogger(
							this.createLoggerContext(AssemblyComponentOperationExecutionCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentOperationExecutionCountLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(AssemblyComponentInvocationCountLogger.class.getSimpleName()))) {
			/* 6. AssemblyComponentInvocationCountLogger */
			final AssemblyComponentInvocationCountLogger assemblyComponentInvocationCountLogger =
					new AssemblyComponentInvocationCountLogger(
							this.createLoggerContext(AssemblyComponentInvocationCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentInvocationCountLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(ExecutionContainerResourceUtilizationLogger.class.getSimpleName()))) {
			/* 7. ExecutionContainerResourceUtilizationLogger */
			final ExecutionContainerResourceUtilizationLogger executionContainerResourceUtilizationLogger =
					new ExecutionContainerResourceUtilizationLogger(
							this.createLoggerContext(ExecutionContainerResourceUtilizationLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(executionContainerResourceUtilizationLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(ExecutionContainerCPUUtilizationLogger.class.getSimpleName()))) {
			/* 8. ExecutionContainerCPUUtilizationLogger */
			final ExecutionContainerCPUUtilizationLogger executionContainerCPUUtilizationLogger =
					new ExecutionContainerCPUUtilizationLogger(
							this.createLoggerContext(ExecutionContainerCPUUtilizationLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(executionContainerCPUUtilizationLogger);
			numLoggerActive++;
		}

		if (Boolean.TRUE.equals(this.loggerClassesToActivate.get(ExecutionContainerMemSwapUsageLogger.class.getSimpleName()))) {
			/* 9. ExecutionContainerMemSwapUsageLogger */
			final ExecutionContainerMemSwapUsageLogger executionContainerMemSwapUsageLogger =
					new ExecutionContainerMemSwapUsageLogger(
							this.createLoggerContext(ExecutionContainerMemSwapUsageLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(executionContainerMemSwapUsageLogger);
			numLoggerActive++;
		}

		if (numLoggerActive == 0) {
			LOG.warn("No performance logger requested");
		} else {
			LOG.info(numLoggerActive + " performance loggers requested");
		}
	}
}
