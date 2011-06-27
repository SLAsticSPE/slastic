analysis.root.dir="/tmp/slastic-20110627-101440982-UTC/"
source(file="r-scripts/performance-logger/common.r")
analysis.logger.rel.dir="PerformanceEvaluator/PerformanceLogger/"

# TODO: read time window / output interval
#str="# winTimeMin=5; outputIntervalMin=2"
# sub("^.*winTimeMin=(\\d*).*$", "\\1", str, perl=TRUE) # returns 5

pdf(file="plots.pdf")

## CPU Utilization
analysis.component.rel.dir="ExecutionContainerCPUUtilizationLogger-winTimeSec_60-outputIntervalSec_15/"
#analysis.data.rel.fn="dbsrv-0-0--cpu0-1-.csv"
#analysis.data.rel.fn="appsrv-0-1--cpu0-3-.csv"
analysis.data.rel.fn="appsrv-0-1--cpu1-4-.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotExecutionContainerCPUUtilization(data.df)

## Memory/Swap Usage
analysis.component.rel.dir="ExecutionContainerMemSwapUsageLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="appsrv-0-1--memSwap-5-.csv"
#analysis.data.rel.fn="dbsrv-0-0--memSwap-2-.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotMemSwapUsage(data.df)

## Assembly Component Invocation Count Logger
analysis.component.rel.dir="AssemblyComponentInvocationCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="0--org.compiere.Wstore-org.compiere.Wstore__T.csv"
data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentInvocationCount(data.df)

analysis.component.rel.dir="AssemblyComponentInvocationCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="1--org.posterita.Businesslogic-org.posterita.Businesslogic__T.csv"
data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentInvocationCount(data.df)

## Deployment Component Invocation Count Logger
analysis.component.rel.dir="DeploymentComponentInvocationCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="0--appsrv-0-1--org.compiere.Wstore.csv"
#analysis.data.rel.fn="1--appsrv-0-1--org.posterita.Businesslogic.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentInvocationCount(data.df)

## Assembly Component Operation Execution Count Logger
analysis.component.rel.dir="AssemblyComponentOperationExecutionCountLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="0--org.compiere.Wstore-org.compiere.Wstore__T_loginServlet__doPost_35.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentOpExecCount(data.df)

## Deployment Component Operation Execution Count Logger
analysis.component.rel.dir="DeploymentComponentOperationExecutionCountLogger-winTimeSec_60-outputIntervalSec_15/"
#analysis.data.rel.fn="1--appsrv-0-1--org.posterita.Businesslogic.menuManager__buildMenuTree_55.csv"
#analysis.data.rel.fn="0--appsrv-0-1--org.compiere.Wstore.loginServlet__doGet_32.csv"
analysis.data.rel.fn="0--appsrv-0-1--org.compiere.Wstore.loginServlet__doPost_35.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentOpExecCount(data.df)

## Assembly Component Average Operation Execution RTs
analysis.component.rel.dir="AssemblyComponentAvgRTsLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="org.compiere.Wstore-org.compiere.Wstore__T.basketServlet__doPost_48.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotAssemblyComponentAvgOpExecRT(data.df)

## Deployment Component Average Operation Execution RTs
analysis.component.rel.dir="DeploymentComponentAvgRTsLogger-winTimeSec_60-outputIntervalSec_15/"
analysis.data.rel.fn="0--appsrv-0-1--org.compiere.Wstore.loginServlet__doPost_35.csv"

data.df=readTSFile(buildFN(analysis.root.dir,analysis.logger.rel.dir,analysis.component.rel.dir,analysis.data.rel.fn));
plotDeploymentComponentAvgOpExecRT(data.df)

#
dev.off()