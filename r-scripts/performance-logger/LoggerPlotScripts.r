analysis.root.dir="/tmp/slastic-20110526-053549212-UTC/"
source(file="r-scripts/performance-logger/common.r")
analysis.logger.rel.dir="PerformanceEvaluator/PerformanceLogger/"

# TODO: read time window / output interval
#str="# winTimeMin=5; outputIntervalMin=2"
# sub("^.*winTimeMin=(\\d*).*$", "\\1", str, perl=TRUE) # returns 5

pdf(file="plots.pdf")

## CPU Utilization
analysis.component.rel.dir="ExecutionContainerCPUUtilizationLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="avanhoorn-thinkpad-1--cpu1-2-.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotExecutionContainerCPUUtilization(data.df)

## Memory/Swap Usage
analysis.component.rel.dir="ExecutionContainerMemSwapUsageLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="avanhoorn-thinkpad-1--memSwap-3-.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotMemSwapUsage(data.df)

## Assembly Component Invocation Count Logger
analysis.component.rel.dir="AssemblyComponentInvocationCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter-kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter__T.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentInvocationCount(data.df)

## Deployment Component Invocation Count Logger
analysis.component.rel.dir="DeploymentComponentInvocationCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="1--avanhoorn-thinkpad-1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentInvocationCount(data.df)

## Assembly Component Operation Execution Count Logger
analysis.component.rel.dir="AssemblyComponentOperationExecutionCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter-kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter__T_doFilter_1.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentOpExecCount(data.df)

## Deployment Component Operation Execution Count Logger
analysis.component.rel.dir="DeploymentComponentOperationExecutionCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="1--avanhoorn-thinkpad-1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter.doFilter_1.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentOpExecCount(data.df)

## Assembly Component Average Operation Execution RTs
analysis.component.rel.dir="AssemblyComponentAvgRTsLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter-kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter__T.doFilter_1.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentAvgOpExecRT(data.df)

## Deployment Component Average Operation Execution RTs
analysis.component.rel.dir="DeploymentComponentAvgRTsLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="1--avanhoorn-thinkpad-1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter.doFilter_1.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentAvgOpExecRT(data.df)

#
dev.off()