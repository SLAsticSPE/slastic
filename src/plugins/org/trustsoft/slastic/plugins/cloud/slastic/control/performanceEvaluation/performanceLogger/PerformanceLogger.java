package org.trustsoft.slastic.plugins.cloud.slastic.control.performanceEvaluation.performanceLogger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.IComponentContext;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AbstractPerformanceMeasureLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentAvgRTsLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentInvocationCountLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentOperationExecutionCountLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentAvgRTsLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentInvocationCountLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentOperationExecutionCountLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerCPUUtilizationLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerMemSwapUsageLogger;
import org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerResourceUtilizationLogger;

import com.espertech.esper.client.EPServiceProvider;

/**
 * 
 * @author Andre van Hoorn
 * 
 */
public class PerformanceLogger extends AbstractPerformanceLogger {
	private static final Log log = LogFactory.getLog(PerformanceLogger.class);

	public static final int DEFAULT_WIN_TIME_SECONDS = 60;
	public static final int DEFAULT_OUTPUT_INTERVAL_SECONDS = 60;

	private final int winTimeSec;
	private final int outputIntervalSec;

	public PerformanceLogger(final EPServiceProvider epServiceProvider,
			final IComponentContext context, final int winTimeSec,
			final int outputIntervalSec) {
		super(epServiceProvider, context);
		this.winTimeSec = winTimeSec;
		this.outputIntervalSec = outputIntervalSec;

		this.startLoggers();
	}

	private IComponentContext createLoggerContext(
			final Class<? extends AbstractPerformanceMeasureLogger<?>> loggerClass,
			final int winTimeSec, final int outputIntervalSec) {
		return this.getContext().createSubcontext(
				loggerClass.getSimpleName() + "-winTimeSec_" + winTimeSec
						+ "-outputIntervalSec_" + outputIntervalSec);
	}

	/**
	 * 
	 */
	private void startLoggers() {
		/* 1. DeploymentComponentAvgRTsLogger */
		final DeploymentComponentAvgRTsLogger deploymentComponentAvgRTsLogger =
				new DeploymentComponentAvgRTsLogger(this.createLoggerContext(
						DeploymentComponentAvgRTsLogger.class, this.winTimeSec,
						this.outputIntervalSec), this.winTimeSec,
						this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(deploymentComponentAvgRTsLogger);

		/* 2. DeploymentComponentOperationExecutionCountLogger */
		final DeploymentComponentOperationExecutionCountLogger deploymentComponentOperationExecutionCountLogger =
				new DeploymentComponentOperationExecutionCountLogger(
						this.createLoggerContext(
								DeploymentComponentOperationExecutionCountLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(deploymentComponentOperationExecutionCountLogger);
		
		/* 3. DeploymentComponentInvocationCountLogger */
		final DeploymentComponentInvocationCountLogger deploymentComponentInvocationCountLogger =
				new DeploymentComponentInvocationCountLogger(
						this.createLoggerContext(
								DeploymentComponentInvocationCountLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(deploymentComponentInvocationCountLogger);

		/* 4. AssemblyComponentAvgRTsLogger */
		final AssemblyComponentAvgRTsLogger assemblyComponentAvgRTsLogger =
				new AssemblyComponentAvgRTsLogger(this.createLoggerContext(
						AssemblyComponentAvgRTsLogger.class, this.winTimeSec,
						this.outputIntervalSec), this.winTimeSec,
						this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(assemblyComponentAvgRTsLogger);

		/* 5. AssemblyComponentOperationExecutionCountLogger */
		final AssemblyComponentOperationExecutionCountLogger assemblyComponentOperationExecutionCountLogger =
				new AssemblyComponentOperationExecutionCountLogger(
						this.createLoggerContext(
								AssemblyComponentOperationExecutionCountLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(assemblyComponentOperationExecutionCountLogger);

		/* 6. AssemblyComponentInvocationCountLogger */
		final AssemblyComponentInvocationCountLogger assemblyComponentInvocationCountLogger =
				new AssemblyComponentInvocationCountLogger(
						this.createLoggerContext(
								AssemblyComponentInvocationCountLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(assemblyComponentInvocationCountLogger);
		
		/* 7. ExecutionContainerResourceUtilizationLogger */
		final ExecutionContainerResourceUtilizationLogger executionContainerResourceUtilizationLogger =
				new ExecutionContainerResourceUtilizationLogger(
						this.createLoggerContext(
								ExecutionContainerResourceUtilizationLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(executionContainerResourceUtilizationLogger);

		/* 8. ExecutionContainerCPUUtilizationLogger */
		final ExecutionContainerCPUUtilizationLogger executionContainerCPUUtilizationLogger =
				new ExecutionContainerCPUUtilizationLogger(
						this.createLoggerContext(
								ExecutionContainerCPUUtilizationLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(executionContainerCPUUtilizationLogger);

		/* 9. ExecutionContainerMemSwapUsageLogger */
		final ExecutionContainerMemSwapUsageLogger executionContainerMemSwapUsageLogger =
				new ExecutionContainerMemSwapUsageLogger(
						this.createLoggerContext(
								ExecutionContainerMemSwapUsageLogger.class,
								this.winTimeSec, this.outputIntervalSec),
						this.winTimeSec, this.outputIntervalSec);
		this.addAndRegisterLoggerAsSubscriber(executionContainerMemSwapUsageLogger);
	}
}
