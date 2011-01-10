analysis.root.dir="/tmp/slastic-20110110-084237650-UTC-/"
source(file="r-scripts/performance-logger/common.r")
analysis.logger.rel.dir="PerformanceEvaluatorComponent-de.cau.se.ffi.cloud.slastic.control.performanceEvaluation.PerformanceEvaluator/de.cau.se.ffi.cloud.slastic.control.performanceEvaluation.performanceLogger.PerformanceLogger/"

# TODO: read time window / output interval
#str="# winTimeMin=5; outputIntervalMin=2"
# sub("^.*winTimeMin=(\\d*).*$", "\\1", str, perl=TRUE) # returns 5

pdf(file="plots.pdf")

## CPU Utilization
analysis.component.rel.dir="org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerCPUUtilizationLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="avanhoorn-thinkpad-1--cpu0-1-.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotExecutionContainerCPUUtilization(data.df)

## Memory/Swap Usage
analysis.component.rel.dir="org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerMemSwapUsageLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="avanhoorn-thinkpad-1--memSwap-3-.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotMemSwapUsage(data.df)

## Assembly Component Operation Execution Count Logger
analysis.component.rel.dir="org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentOperationExecutionCountLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter-kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter__T.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentOperationExecutionCount(data.df)

## Deployment Component Operation Execution Count Logger
analysis.component.rel.dir="org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentOperationExecutionCountLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="1--avanhoorn-thinkpad-1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentOperationExecutionCount(data.df)

## Assembly Component Average RTs
analysis.component.rel.dir="org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.AssemblyComponentAvgRTsLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter-kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter__T.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentAverageRT(data.df)

## Deployment Component Average RTs
analysis.component.rel.dir="org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.DeploymentComponentAvgRTsLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="1--avanhoorn-thinkpad-1--kieker.monitoring.probe.servlet.OperationExecutionRegistrationAndLoggingFilter.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentAverageRT(data.df)

#
dev.off()