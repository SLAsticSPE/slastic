source(file="r-scripts/performance-logger/common.r")

## Set directory and file
analysis.root.dir="/tmp/slastic-20110109-145233362-UTC-/"
analysis.component.rel.dir="PerformanceEvaluatorComponent-de.cau.se.ffi.cloud.slastic.control.performanceEvaluation.PerformanceEvaluator/de.cau.se.ffi.cloud.slastic.control.performanceEvaluation.performanceLogger.PerformanceLogger/org.trustsoft.slastic.plugins.slasticImpl.control.performanceEvaluation.performanceLogger.ExecutionContainerMemSwapUsageLogger-winTimeMin_1-outputIntervalMin_1/"
analysis.data.rel.fn="avanhoorn-thinkpad-1--memSwap-3-.csv"

analysis.data.abs.fn=paste(analysis.root.dir, analysis.component.rel.dir, analysis.data.rel.fn, sep="/")

data.df=read.csv2(analysis.data.abs.fn, header = TRUE, sep = ";", quote="\"", dec=".", 
          fill = TRUE, comment.char="#")
data.df[["posixct"]]=as.POSIXct(data.df[["timestamp"]]/1000, tz="GMT", origin="1970-01-01")

executionContainerResource.name=paste(data.df$executionContainer[1], "::", data.df$resource[1], sep="")

minTimestamp.sec=min(data.df[["timestamp"]])/1000
minTimestamp.posixct=as.POSIXct(minTimestamp.sec, tz="GMT", origin="1970-01-01")
maxTimestamp.sec=max(data.df[["timestamp"]])/1000
maxTimestamp.posixct=as.POSIXct(maxTimestamp.sec, tz="GMT", origin="1970-01-01")

memTotalMB=data.df[["memCapacityBytes"]][1]/1024/1024
swapTotalMB=data.df[["swapCapacityBytes"]][1]/1024/1024

# TODO: read time window / output interval

par(mar=c(3,4,3,4)) # b/l/t/r
plot(data.df[["posixct"]], data.df[["memUsedBytes"]]/1024/1024, col="blue", type="l", xaxt="n", xlab="", lwd=1, yaxt="n", ylab="", main="Memory & Swap Usage",cex.axis=0.95)
axis(side=2, tick=TRUE, col=0, col.ticks="black")
ylab=paste("Memory usage [MB] (total: ", round(memTotalMB) , " MB)")
mtext(side=2,text=ylab,line=2,cex.axis=1) #
mtext(side=3,text=executionContainerResource.name,line=0,cex.axis=0.95)
mtext(side=1,text=paste("UTC Calendar time (from ",minTimestamp.posixct, " to ", maxTimestamp.posixct, ")", sep=""),line=2,cex.axis=0.95) #
par(new=TRUE)
#plot(data[[i]][["sec"]],data[[i]][["mem"]]/1024, xaxt="n",yaxt="n",xlab="",ylab="",type="l",main="",col="blue")
plot(data.df[["posixct"]], data.df[["swapUsedBytes"]]/1024/1024, col="red", type="l", xaxt="n", xlab="", lwd=1, ylab="", main="",cex.axis=0.95,yaxt="n")
axis(side=4, tick=TRUE, col=0, col.ticks="black")
y2lab=paste("Swap usage [MB] (total: ", round(swapTotalMB) , " MB)")
mtext(side=4,text=y2lab,line=2,cex.axis=1) #
legend("topright",
        legend = c("Memory", "Swap"),
        col = c("blue", "red"), 
	lty = 1,box.lty=0,box.col="white", 
#	bg="lightgray"
)

# Begin: Axes for data over a couple of minutes
axis.POSIXct(1, at=seq(min(minTimestamp.posixct), max(maxTimestamp.posixct), "min"), length.out=NULL)
# End: Axes for data over a couple of minutes

# Begin: Axes for data over multiple weeks/months
# axis(side=1,line=-0.5,tick=TRUE,labels=FALSE,at=hours,col=0,col.ticks="darkgrey")
# abline(v=days, col="darkgrey", lty="dashed")
# axis.POSIXct(at=noons,side=3,line=-1,tick=FALSE, format="%b/%d (%a)",cex.axis=0.95) # Jan/07 (Fri)
# axis.POSIXct(at=days,side=3,line=0,tick=TRUE, labels=FALSE) # %H:%M:%S
# axis.POSIXct(at=hours,side=1,line=0,tick=TRUE, format="%H:00",cex.axis=0.95) # %H:%M:%S
# End: Axes for data over multiple weeks/months