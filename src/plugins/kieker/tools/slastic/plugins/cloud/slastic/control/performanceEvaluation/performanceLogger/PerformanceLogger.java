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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.espertech.esper.client.EPServiceProvider;

import kieker.tools.slastic.common.IComponentContext;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceMeasureLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentInvocationCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentOperationAvgRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentOperationExecutionCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentOperationMedianRTsLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentInvocationCountLogger;
import kieker.tools.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentOperationAvgRTsLogger;
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
	private final Set<String> loggerClassesToActivate = new HashSet<String>();

	public PerformanceLogger(final EPServiceProvider epServiceProvider, final IComponentContext context, final int winTimeSec, final int outputIntervalSec,
			final boolean ioAutoFlush, final List<String> loggerClassesToActivate) {
		super(epServiceProvider, context);
		this.ioAutoFlush = ioAutoFlush;
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;
		for (final String loggerClass : loggerClassesToActivate) {
			this.loggerClassesToActivate.add(loggerClass);
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

		/* 1. DeploymentComponentAvgRTsLogger */
		if (this.loggerClassesToActivate.remove(DeploymentComponentOperationAvgRTsLogger.class.getSimpleName())) {
			final DeploymentComponentOperationAvgRTsLogger deploymentComponentAvgRTsLogger =
					new DeploymentComponentOperationAvgRTsLogger(this.createLoggerContext(DeploymentComponentOperationAvgRTsLogger.class, this.winTimeSec,
							this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(deploymentComponentAvgRTsLogger);
			numLoggerActive++;
		}

		/* 2. DeploymentComponentOperationExecutionCountLogger */
		if (this.loggerClassesToActivate.remove(DeploymentComponentOperationExecutionCountLogger.class.getSimpleName())) {
			final DeploymentComponentOperationExecutionCountLogger deploymentComponentOperationExecutionCountLogger =
					new DeploymentComponentOperationExecutionCountLogger(
							this.createLoggerContext(DeploymentComponentOperationExecutionCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(deploymentComponentOperationExecutionCountLogger);
			numLoggerActive++;
		}

		/* 3. DeploymentComponentInvocationCountLogger */
		if (this.loggerClassesToActivate.remove(DeploymentComponentInvocationCountLogger.class.getSimpleName())) {
			final DeploymentComponentInvocationCountLogger deploymentComponentInvocationCountLogger =
					new DeploymentComponentInvocationCountLogger(
							this.createLoggerContext(DeploymentComponentInvocationCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(deploymentComponentInvocationCountLogger);
			numLoggerActive++;
		}

		/* 4. AssemblyComponentAvgRTsLogger */
		if (this.loggerClassesToActivate.remove(AssemblyComponentOperationAvgRTsLogger.class.getSimpleName())) {
			final AssemblyComponentOperationAvgRTsLogger assemblyComponentAvgRTsLogger =
					new AssemblyComponentOperationAvgRTsLogger(
							this.createLoggerContext(AssemblyComponentOperationAvgRTsLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentAvgRTsLogger);
			numLoggerActive++;
		}

		/* 4.1. AssemblyComponentMedianRTsLogger */
		if (this.loggerClassesToActivate.remove(AssemblyComponentOperationMedianRTsLogger.class.getSimpleName())) {
			final AssemblyComponentOperationMedianRTsLogger assemblyComponentMedianRTsLogger =
					new AssemblyComponentOperationMedianRTsLogger(this.createLoggerContext(AssemblyComponentOperationMedianRTsLogger.class, this.winTimeSec,
							this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentMedianRTsLogger);
			numLoggerActive++;
		}

		/* 5. AssemblyComponentOperationExecutionCountLogger */
		if (this.loggerClassesToActivate.remove(AssemblyComponentOperationExecutionCountLogger.class.getSimpleName())) {
			final AssemblyComponentOperationExecutionCountLogger assemblyComponentOperationExecutionCountLogger =
					new AssemblyComponentOperationExecutionCountLogger(
							this.createLoggerContext(AssemblyComponentOperationExecutionCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentOperationExecutionCountLogger);
			numLoggerActive++;
		}

		/* 6. AssemblyComponentInvocationCountLogger */
		if (this.loggerClassesToActivate.remove(AssemblyComponentInvocationCountLogger.class.getSimpleName())) {
			final AssemblyComponentInvocationCountLogger assemblyComponentInvocationCountLogger =
					new AssemblyComponentInvocationCountLogger(
							this.createLoggerContext(AssemblyComponentInvocationCountLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(assemblyComponentInvocationCountLogger);
			numLoggerActive++;
		}

		/* 7. ExecutionContainerResourceUtilizationLogger */
		if (this.loggerClassesToActivate.remove(ExecutionContainerResourceUtilizationLogger.class.getSimpleName())) {
			final ExecutionContainerResourceUtilizationLogger executionContainerResourceUtilizationLogger =
					new ExecutionContainerResourceUtilizationLogger(
							this.createLoggerContext(ExecutionContainerResourceUtilizationLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(executionContainerResourceUtilizationLogger);
			numLoggerActive++;
		}

		/* 8. ExecutionContainerCPUUtilizationLogger */
		if (this.loggerClassesToActivate.remove(ExecutionContainerCPUUtilizationLogger.class.getSimpleName())) {
			final ExecutionContainerCPUUtilizationLogger executionContainerCPUUtilizationLogger =
					new ExecutionContainerCPUUtilizationLogger(
							this.createLoggerContext(ExecutionContainerCPUUtilizationLogger.class, this.winTimeSec, this.outputIntervalSec),
							this.winTimeSec, this.outputIntervalSec, this.ioAutoFlush);
			this.addAndRegisterLoggerAsSubscriber(executionContainerCPUUtilizationLogger);
			numLoggerActive++;
		}

		/* 9. ExecutionContainerMemSwapUsageLogger */
		if (this.loggerClassesToActivate.remove(ExecutionContainerMemSwapUsageLogger.class.getSimpleName())) {
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

		if (!this.loggerClassesToActivate.isEmpty()) {
			LOG.warn("The following logger classes could not be resolved:" + this.loggerClassesToActivate);
		}
	}
}
