## @author Nina

## initialization: see sysstatlog_plot.sh
#inputcount = 6
#inputnames_cpu = paste("log/sysstat/jpet", 1:inputcount,"-sar-cpu.csv",sep="")
#inputnames_mem = paste("log/sysstat/jpet", 1:inputcount,"-sar-mem.csv",sep="")
#inputnames_memtotal = paste("log/sysstat/jpet", 1:inputcount,"-sar-memtotal",sep="")
#epsfilename = "plots/sysstat-result.eps"

## test:
#data_cpu = read.csv("/tmp/sar-result-cpu.csv",sep=";",dec=",")
#data_mem = read.csv("/tmp/sar-result-mem.csv",sep=";",dec=",")
#setwd("/home/nina/exp_jps5-distributed/jmeter-profiles/jpetstore-AntProfile")

## read csv data
data_cpu = lapply(inputnames_cpu, read.csv, sep=";", dec=",", comment.char='#', header=TRUE)
data_mem = lapply(inputnames_mem, read.csv, sep=";", dec=",", comment.char='#', header=TRUE)
#print(data_mem)
data_memtotal = lapply(inputnames_memtotal, read.table, col.names=c("memtotal"))
#data_pow = read.csv2("foen.nums.csv", header=FALSE, col.names=c("timestamp","current"), sep=";", dec=".")
#data_pow = lapply(inputnames_pow, read.csv, sep=";", dec=".", comment.char="#", header=FALSE, col.names=c("timestamp","current"))

## open output file
#postscript(file=epsfilename,horizontal=TRUE,paper="a4",pointsize=11,title="CPU Load and Memory Usage")
postscript(file=epsfilename,horizontal=FALSE,paper="special",width=11.0,height=8.0,pointsize=11,title="CPU Load, Memory Usage, and Power Consumption")

## set page layout to 3x2 figure
#par(mfrow=c(3,2),mar=c(4,4,2,4))
par(mar=c(4,4,2,4))

## needed if input values are like 0.00 or 0.10 to cut off the trailing zeroes.
unlevel=function(x){
	as.double(as.character(x))
}

## draw cpu + memory figures
for( host in 1:inputcount ) {		## jpet1..n
	data_timestamp = data_cpu[[host]][["timestamp"]] - data_cpu[[host]][["timestamp"]][[1]]
	data_cpu_usage = unlevel(data_cpu[[host]][["user"]]) + unlevel(data_cpu[[host]][["nice"]]) + unlevel(data_cpu[[host]][["system"]])
#	data_mem_usage = ( data_memtotal[[host]][[1]] - data_mem[[host]][["kbmemfree"]] - data_mem[[host]][["kbbuffers"]] - data_mem[[host]][["kbcached"]] - data_mem[[host]][["kbswpcad"]] ) / 1024
	data_mem_usage = data_mem[[host]][["memused"]]
#print(data_mem_usage)
	plot(data_timestamp,data_cpu_usage,type="n",xlab="",ylab="",main=paste("Node:  ",data_cpu[[host]][["node"]][1],sep=""),col="red",xaxt="n",yaxt="n")
	grid()
	lines(data_timestamp,data_cpu_usage,col="darkred")
	axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=2,line=0,tick=TRUE,labels=FALSE)
	par(new=TRUE)
	plot(data_timestamp,data_mem_usage, xaxt="n",yaxt="n",xlab="",ylab="",type="l",main="",col="darkblue",ylim=c(0,100))
	axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=1,line=0,tick=TRUE,labels=FALSE)
	axis(side=4,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=4,line=0,tick=TRUE,labels=FALSE,ylim=c(0,100)) #pretty(range(data_mem_usage))
#	axis(2,pretty(c(0,200)))
#	axis(4,pretty(c(0,1024)))
	mtext(side=1,line=1.8,cex=0.8,"Experiment Time (Seconds)")
	mtext(side=2,line=1.8,cex=0.9,"CPU Load (Percent)",col="darkred")
	mtext(side=4,line=1.8,cex=0.9,paste("Memory Usage (%)  of",as.integer(data_memtotal[[host]][[1]]/1024),"MB"),col="darkblue")
}

#legend("topleft",c("jpet1","jpet2","jpet3","jpet4","jpet5","jpet6"),c("blue","red","orange","darkgreen","magenta","black"))

palered = rgb(0.9,0.3,0.3)

## draw cpu + wattage figures
for( host in 1:inputcount ) {		## jpet1..n
#	if( length( data_pow[[host]][["timestamp"]] ) < 2 ) {
	if( 1 ) {
		print( paste("Not printing powerstat diagram for jpet",host," - not enough data.",sep="") )
		next
	}
	data_timestamp = data_cpu[[host]][["timestamp"]] - data_cpu[[host]][["timestamp"]][[1]]
	data_cpu_usage = unlevel(data_cpu[[host]][["user"]]) + unlevel(data_cpu[[host]][["nice"]]) + unlevel(data_cpu[[host]][["system"]])
	plot(data_timestamp,data_cpu_usage,type="n",xlab="",ylab="",main=paste("Node:  jpet",host,sep=""),col="red",xaxt="n",yaxt="n")
	grid()
	lines(data_timestamp,data_cpu_usage,col=palered)
	axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=2,line=0,tick=TRUE,labels=FALSE)
	par(new=TRUE)

	data_timestamp = data_pow[[host]][["timestamp"]] - data_pow[[host]][["timestamp"]][[1]]
	data_pow_usage = data_pow[[host]][["current"]] * 220			# multiply current with 220 V to get wattage
	plot(data_timestamp,data_pow_usage, xaxt="n",yaxt="n",xlab="",ylab="",type="l",main="",col="darkgreen")
	axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=1,line=0,tick=TRUE,labels=FALSE)
	axis(side=4,line=-0.3,tick=FALSE,cex.axis=0.9)
	axis(side=4,line=0,tick=TRUE,labels=FALSE,pretty(range(data_pow_usage)))

	mtext(side=1,line=1.8,cex=0.8,"Experiment Time (Seconds)")
	mtext(side=2,line=1.8,cex=0.9,"CPU Load (Percent)",col=palered)
	mtext(side=4,line=1.8,cex=0.9,"Watt (220 V*I)",col="darkgreen")
}

## close output file
dev.off()
