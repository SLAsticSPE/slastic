source("/home/matthias/rscripts/pdftools.r")
## evaluate time log files
## use ../bin/plot... to combine all time*.log files into one

## you have to set the file!
# file = 1
		
		
timeLogDataDir = "/home/matthias/exp_jps5-distributed/08-04-28-timeSyncAnalysisData/"
if (file == 1) timeLogDataFile = paste(timeLogDataDir,"20080425-pretend-allTimeSync.log",sep="")
if (file == 2) timeLogDataFile = paste(timeLogDataDir,"20080425-sev0-allTimeSync.log",sep="")
if (file == 3) timeLogDataFile = paste(timeLogDataDir,"20080425-sev4-allTimeSync.log",sep="")				
		
epsfile="/tmp/timeSyncAnalysis.eps"
if (file == 1) pdffile=paste(timeLogDataDir,"20080425-pretend-allTimeSync.pdf",sep="")
if (file == 2) pdffile=paste(timeLogDataDir,"20080425-sev0-allTimeSync.pdf",sep="")
if (file == 3) pdffile=paste(timeLogDataDir,"20080425-sev4-allTimeSync.pdf",sep="")				
		
logData = read.csv2(file=timeLogDataFile, header = FALSE, sep = ";")
logsize = nrow(logData)

postscript(file=epsfile,paper="a4",horizontal=TRUE,family="sans")		
durations = sort(unique(logData[["V3"]]))		
## loop through all durations (= different update intervalls)
for (durIdx in durations) {
	logData2 = subset(logData, V3 == durations[durIdx]) 						
	timedata = data.frame(t=as.numeric(logData2[["V1"]]),node=as.character(logData2[["V4"]]),duration=as.double(as.character(logData2[["V3"]])),offset=as.double(as.character(logData2[["V5"]])))
	rm(logData2)
	nodes = unique(timedata$node)

		#plot(timedata$t,pmax(abs(timedata$offset*1000*1000),0.4),log="y",ylab="Offset in microseconds",xlab="Calendar time",type="n")
		
		# ## lines absolute offset log
		# par(mfrow=c(2,2))
		# for (curNodeIdx in nodes) {
		# 	curNode = nodes[curNodeIdx]
		# 	thisNodeIdx = which(timedata$node == curNode)
		# 	plot(timedata$t[thisNodeIdx]-min(timedata$t[thisNodeIdx]),pmax(abs(timedata$offset[thisNodeIdx]*1000*1000),0.4),type="l",ylab="Offset in microseconds",xlab="Exp time in seconds",log="y")
		# 	mtext(side=3,line=0.6,sprintf("Node %s; %.1f secs between time syncs",curNode,unique(timedata$duration)[1]),cex=0.9)
		# 	mtext(side=3,line=1.6,sprintf("Absolute offset (log)",curNode,unique(timedata$duration)[1]),cex=0.9)
		# #getPOSIXctAxis(timedata$t[thisNodeIdx]
		# }
		
		## line chart offset		
				
		absAvgOffset = rep(0,n=length(nodes))
		par(mfrow=c(2,2))
		for (curNodeIdx in nodes) {
			curNode = nodes[curNodeIdx]	
			absAvgOffset[curNodeIdx] = mean(abs(timedata$offset[thisNodeIdx]*1000*1000))	
			thisNodeIdx = which(timedata$node == curNode)
			plot(timedata$t[thisNodeIdx]-min(timedata$t[thisNodeIdx]),timedata$offset[thisNodeIdx]*1000*1000,type="l",ylab="",xlab="",xaxt="n",yaxt="n")
			mtext(side=3,line=0.6,c(sprintf("Avg. absolute offset micsec %.2f",absAvgOffset[curNodeIdx])),cex=0.9)
			mtext(side=3,line=1.6,sprintf("Node %s; %.1f secs between time syncs",curNode,unique(timedata$duration)[1]),cex=0.9)
			mtext(side=1,text="Experiment time in seconds",line=1.8,cex=0.92)
			mtext(side=2,text="Offset in microseconds",line=1.8,cex=0.92)
				axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=2,line=0,tick=TRUE,labels=FALSE);axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=1,line=0,tick=TRUE,labels=FALSE)
		
		}
		
		## ecdfs
		par(mfrow=c(2,2))
		for (curNodeIdx in nodes) {
			curNode = nodes[curNodeIdx]	
			absAvgOffset[curNodeIdx] = mean(abs(timedata$offset[thisNodeIdx]*1000*1000))	
			thisNodeIdx = which(timedata$node == curNode)
			plot(ecdf(timedata$offset[thisNodeIdx]*1000*1000),main="",xlab="",ylab="",xaxt="n",yaxt="n")
		#plot(timedata$t[thisNodeIdx],timedata$offset[thisNodeIdx]*1000*1000,type="l",ylab="Offset in microseconds",xlab="Calendar time")
			mtext(side=1,text="Offset in microseconds",line=1.8,cex=0.92)
			mtext(side=2,text="Fn(x)",line=1.8,cex=0.92)
				axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=2,line=0,tick=TRUE,labels=FALSE);axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=1,line=0,tick=TRUE,labels=FALSE)
		
			mtext(side=3,line=0.6,c(sprintf("Avg. absolute offset micsec %.2f; avg offset %.2f",absAvgOffset[curNodeIdx],mean(timedata$offset[thisNodeIdx]*1000*1000))),cex=0.9)
			mtext(side=3,line=1.6,sprintf("ECDF: Node %s; %.1f secs between time syncs",curNode,unique(timedata$duration)[1]),cex=0.9)
			abline(h=0.5,lty=4,col="grey")
			abline(v=0,lty=4,col="grey")
		}
		
		
		par(mfrow=c(1,2))
		## ecdfs all in one plot		
		mycolors = rainbow(length(nodes))
		plot(c(min(timedata$offset*1000*1000),max(timedata$offset*1000*1000)),c(0,1),main="",xlab="",ylab="",xaxt="n",yaxt="n",type="n")
		mtext(side=1,text="Offset in microseconds",line=1.8)
		mtext(side=2,text="Fn(x)",line=1.8)
		axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=2,line=0,tick=TRUE,labels=FALSE);axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=1,line=0,tick=TRUE,labels=FALSE)
		mtext(side=3,line=0.6,sprintf("%.1f secs between time syncs",unique(timedata$duration)[1]),cex=0.9)
					
		for (curNodeIdx in nodes) {
			curNode = nodes[curNodeIdx]	
			curIdexs = which(timedata$node == curNode)
			absAvgOffset[curNodeIdx] = mean(abs(timedata$offset[curIdexs]*1000*1000))	
			thisNodeIdx = which(timedata$node == curNode)
			lines(ecdf(timedata$offset[thisNodeIdx]*1000*1000),main="",xlab="Offset in microseconds",col.p=mycolors[curNodeIdx],lwd=2,pch=curNodeIdx)
		
		#,col=rainbow(length(nodes))[curNodeIdx]
		#plot(timedata$t[thisNodeIdx],timedata$offset[thisNodeIdx]*1000*1000,type="l",ylab="Offset in microseconds",xlab="Calendar time")
		#mtext(side=3,line=0.6,c(sprintf("Avg. absolute offset micsec %.2f; avg offset %.2f",absAvgOffset[curNodeIdx],mean(timedata$offset[thisNodeIdx]*1000*1000))))
		#mtext(side=3,line=1.6,sprintf("ECDF: Node %s; %.1f secs between time syncs",curNode,unique(timedata$duration)[1]))
			abline(h=0.5,lty=4,col="grey")
			abline(v=0,lty=4,col="grey")
		}
		legend("bottomright",legend=nodes[1:4],col=mycolors[1:length(nodes)],pch=1:4,bg="white")
		
		
		#legend("topright",c("Avg. absolute offset micsec:", sprintf("%s %.2f",nodes[1],absAvgOffset[1]),sprintf("%s %.2f",nodes[2],absAvgOffset[2]),sprintf("%s %.2f",nodes[3],absAvgOffset[3]),sprintf("%s %.2f",nodes[4],absAvgOffset[4])))
		
		## boxplot with averages
		boxplot((timedata$offset*1000*1000)~timedata$node,ylab="",yaxt="n")
		a=boxplot((timedata$offset*1000*1000)~timedata$node,ylab="",yaxt="n",plot=FALSE)
				mtext(side=3,line=0.6,sprintf("Node %s; %.1f secs between time syncs",curNode,unique(timedata$duration)[1]),cex=0.9)		
		avgs = rep(0,length(a$names)); i = 1;
		for (name in a$names){
			thisIdxs = which(timedata$node == name)
			avgs[i] = mean(timedata$offset[thisIdxs]*1000*1000)
			i = i + 1;
		}

		mtext(side=2,text="Offset in microseconds",line=1.8)
		axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9);axis(side=2,line=0,tick=TRUE,labels=FALSE);
		points(c(1,2,3,4),avgs,col="red",lwd=2,pch=3)		
		legend("topright",c("Avg. offset in micsec:", sprintf("%s %.2f",nodes[1],avgs[1]),sprintf("%s %.2f",nodes[2],avgs[2]),sprintf("%s %.2f",nodes[3],avgs[3]),sprintf("%s %.2f",nodes[4],avgs[4])))
}
dev.off()
createPdf(epsfile,pdffile)		
print(sprintf("Created %s",pdffile))
