# @author Nina
#
# (mein allererstes R-Skript ;-)

# initialization: see sysmonlog_plot.sh
#inputcount = 6
#inputnames = paste("log/hwlog/jpet", 1:inputcount,".csv",sep="")
#epsfilename = "plots/hwlog-result.eps"

# read csv data
#data1 = read.csv(inputnames[1])
data = lapply(inputnames, read.csv)

# open output file
postscript(file=epsfilename,horizontal=TRUE,paper="a4",pointsize=11,title="CPU Load and Memory Usage")

# set page layout to 3x2 figure
par(mfrow=c(3,2),mar=c(4,4,2,4))

# draw figures
for( i in 1:inputcount ) {
	plot(data[[i]][["sec"]],data[[i]][["cpuload"]],type="n",xlab="",ylab="",main=paste("Node: jpet",i,sep=""),col="red",xaxt="n",yaxt="n")
	grid()
	lines(data[[i]][["sec"]],data[[i]][["cpuload"]],col="red")
	axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=2,line=0,tick=TRUE,labels=FALSE)
	par(new=TRUE)
	plot(data[[i]][["sec"]],data[[i]][["mem"]]/1024, xaxt="n",yaxt="n",xlab="",ylab="",type="l",main="",col="blue")
	axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=1,line=0,tick=TRUE,labels=FALSE)
	axis(side=4,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=4,line=0,tick=TRUE,labels=FALSE,pretty(range(data[[i]][["mem"]])/1024))
#	axis(2,pretty(c(0,200)))
#	axis(4,pretty(c(0,1024)))
	mtext(side=1,line=1.8,cex=0.8,"Experiment Time (Seconds)")
	mtext(side=2,line=1.8,cex=0.9,"CPU Load (Percent)",col="darkred")
	mtext(side=4,line=1.8,cex=0.9,"Memory Usage (MB)",col="darkblue")
}

#legend("topleft",c("jpet1","jpet2","jpet3","jpet4","jpet5","jpet6"),c("blue","red","orange","darkgreen","magenta","black"))

# close output file
dev.off()
