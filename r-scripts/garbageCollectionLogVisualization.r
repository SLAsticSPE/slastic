## definition of a general function
createPdf <- function(filenameeps,filenamepdf,mytempfile="/tmp/delme.pdf") {    
        ## epstopdf
        command = paste(sep="","epstopdf ",filenameeps," --outfile=",filenamepdf); print(sprintf("%s",command))
        a = system(command,intern=TRUE); print(a)
        ## embedFonts and move
        embedFonts(filenamepdf, "pdfwrite", outfile = mytempfile,options="-dPDFX")
        ## pdfcrop
        command = paste("pdfcrop",mytempfile,filenamepdf)
        print(sprintf("%s",command))
        a = system(command,intern=TRUE)
        print(a)                                
}

## prepare garbage collection log 
#  cat garbageCollectionLog.csv | awk -F":" '{print $1}' > a.txt
#  cat garbageCollectionLog.csv | awk -F":" '{print $4}' | awk -F" " '{print $2}' > b.txt
#  cat garbageCollectionLog.csv | grep Tenur | awk -F"]" '{print $2}' | awk -F":" '{print $1}' > c.txt
#  cat garbageCollectionLog.csv | grep Tenur | awk -F"]" '{print $2}' | awk -F" " '{print $4}' > d.txt

csvpath = "/home/matthias/drarbeit/thesis/parts/08-08-26-garbageCollectionComparedToCPUpatterns/"
filea   = paste(sep="",csvpath,"a.txt")
fileb   = paste(sep="",csvpath,"b.txt")
filec   = paste(sep="",csvpath,"c.txt")
filed   = paste(sep="",csvpath,"d.txt")
timestamps=read.csv(filea,header=FALSE)
gctime=read.csv(fileb,header=FALSE)
gcdata=data.frame(t=timestamps,gct=gctime)
names(gcdata) <- c("t","gct")
summary(gcdata)
plot(gcdata)
		
## data dependent analysis
# #locator(1)
 r1=subset(gcdata, t < 3078)
# plot(r1)		
r2=subset(r1, t > 65)
r2$t = r2$t - min(r2$t)		
# gcdata=r2		
# plot(gcdata,type="l")
# r3=subset(r1, t > 100 & t < 400)
# plot(fft(r3$t),xlim=c(-200,-100))				
# length(r3$t)
# r4=r3$t[2:365]-r3$t[1:364]
# plot(r4)


## is distance between gcs a strong signal
r5=subset(r2, t > 0 & t < 2200)		
plot(r5)
r6=r5$t[2:length(r5$t)]-r5$t[1:length(r5$t)-1]	


epsfilename=paste(sep="",csvpath,"jpet2-gc-interevent-time.eps")
pdffilename=paste(sep="",csvpath,"jpet2-gc-interevent-time.pdf")
postscript(file=epsfilename,horizontal=FALSE,paper="special",width=5.5,height=4.0,pointsize=11,title="CPU Load and Memory Usage")	
plot(r5$t[2:length(r5$t)],r6,log="y",xlab="",ylab="",main="Node:  jpet2",yaxt="n",xaxt="n")
axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9)
axis(side=2,line=0,tick=TRUE,labels=FALSE)
axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9)
axis(side=1,line=0,tick=TRUE,labels=FALSE)	
mtext(side=1,line=1.8,cex=0.9,"Experiment Time (Seconds)")
mtext(side=2,line=1.8,cex=0.9,"Seconds between Garbage Collection events")
dev.off()

createPdf(epsfilename,pdffilename,mytempfile="/tmp/delme.pdf") 
        

r5[["gct"]]
plot(r5$t[2:length(r5$t)],r6,log="y",xlab="",ylab="",main="Node:  jpet2",yaxt="n",xaxt="n")
factor=mean(r6)/mean(r5[["gct"]])
axis(side=2,line=-0.3,tick=FALSE,cex.axis=0.9)
axis(side=2,line=0,tick=TRUE,labels=FALSE)
axis(side=1,line=-0.3,tick=FALSE,cex.axis=0.9)
axis(side=1,line=0,tick=TRUE,labels=FALSE)	
mtext(side=1,line=1.8,cex=0.9,"Experiment Time (Seconds)")
mtext(side=2,line=1.8,cex=0.9,"Seconds between Garbage Collection events")
par(new=TRUE)
plot(r5$t[2:length(r5$t)],r5[["gct"]][2:length(r5$t)]*factor,col="red",xaxt="n",yaxt="n",pch=2,log="y",xlab="",ylab="")
axis(side=4,line=-0.3,tick=FALSE,cex.axis=0.9)
axis(side=4,line=0,tick=TRUE,labels=FALSE)
#axis(side=3,line=-0.3,tick=FALSE,cex.axis=0.9)
#axis(side=3,line=0,tick=TRUE,labels=FALSE)	
#mtext(side=3,line=1.8,cex=0.9,"Experiment Time (Seconds)")
mtext(side=4,line=1.8,cex=0.9,"Garbage Collection Duration")

plot(r5[["gct"]][1:length(r5$t)-1],r6,log="y")