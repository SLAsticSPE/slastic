source(file="common.r")

## Set directory and file
analysis.root.dir="/tmp/slastic-20101201-085909670-UTC-/"
analysis.component.rel.dir="PerformanceEvaluatorComponent-net.voorn.wosp.control.performanceEvaluation.EWETELPerformanceEvaluator/net.voorn.wosp.control.performanceEvaluation.performanceLogger.PerformanceLogger/net.voorn.wosp.control.performanceEvaluation.performanceLogger.DeploymentComponentOperationExecutionCountLogger/"
analysis.data.rel.fn="10--pikdb0-3--de.ewetel.b2b.backend.service.css.EmailService.csv"

analysis.data.abs.fn=paste(analysis.root.dir, analysis.component.rel.dir, analysis.data.rel.fn, sep="/")

data.df=read.csv2(analysis.data.abs.fn, header = TRUE, sep = ";", quote="\"", dec=".",
          fill = TRUE, comment.char="")
data.df[["posixct"]]=as.POSIXct(data.df[["timestamp"]]/1000, tz="GMT", origin="1970-01-01")

deploymentComponent.name=paste(data.df$executionContainer[1], "::@", data.df$deplCompID[1], ":", data.df$assemblyComponent[1], sep="")

minTimestamp.sec=min(data.df[["timestamp"]])/1000
minTimestamp.posixct=as.POSIXct(minTimestamp.sec, tz="GMT", origin="1970-01-01")
maxTimestamp.sec=max(data.df[["timestamp"]])/1000
maxTimestamp.posixct=as.POSIXct(maxTimestamp.sec, tz="GMT", origin="1970-01-01")

par(mar=c(3,4,3,4)) # b/l/t/r
plot(data.df[["posixct"]], data.df[["count"]], col="blue", type="h", xaxt="n", xlab="", lwd=1, ylab="Arrival rate [#/5 min.]", main="Arrival Rates (Deployment Component Operation Execution)",cex.axis=0.95) #yaxt="n",

axis(side=1,line=-0.5,tick=TRUE,labels=FALSE,at=hours,col=0,col.ticks="darkgrey")
axis(side=4, tick=TRUE, col=0, col.ticks="black")
abline(v=days, col="darkgrey", lty="dashed")
axis.POSIXct(at=noons,side=3,line=-1,tick=FALSE, format="%b/%d (%a)",cex.axis=0.95) # %H:%M:%S
axis.POSIXct(at=days,side=3,line=0,tick=TRUE, labels=FALSE) # %H:%M:%S
axis.POSIXct(at=hours,side=1,line=0,tick=TRUE, format="%H:00",cex.axis=0.95) # %H:%M:%S
mtext(side=1,text=paste("UTC Calendar time (from ",minTimestamp.posixct, " to ", maxTimestamp.posixct, ")", sep=""),line=2,cex.axis=1) #
legend("topright",
       legend = deploymentComponent.name,
       col = "blue", lty = 1,box.lty=0,box.col="white", bg="lightgray")