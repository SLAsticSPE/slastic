package org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.probe.aspectJ.sla;

import kieker.monitoring.core.ControlFlowRegistry;
import kieker.monitoring.core.MonitoringController;
import kieker.monitoring.probe.IMonitoringProbe;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * @author Andre van Hoorn
 */
@Aspect
public class SLAMonitoringProbe implements IMonitoringProbe {

    protected static final MonitoringController ctrlInst = MonitoringController.getInstance();
    protected static final ControlFlowRegistry cfRegistry = ControlFlowRegistry.getInstance();
    private static final String vmName = ctrlInst.getVmName();

    protected SLOMonitoringRecord initMonitoringRecord(ProceedingJoinPoint thisJoinPoint) {
       // e.g. "getBook" 
        String methodname = thisJoinPoint.getSignature().getName();
        // toLongString provides e.g. "public kieker.tests.springTest.Book kieker.tests.springTest.CatalogService.getBook()"
        String paramList = thisJoinPoint.getSignature().toLongString();
        int paranthIndex = paramList.lastIndexOf('(');
        paramList = paramList.substring(paranthIndex); // paramList is now e.g.,  "()"

        SLOMonitoringRecord record = new SLOMonitoringRecord(
                thisJoinPoint.getSignature().getDeclaringTypeName() /* component */, 
                methodname + paramList /* operation */, 
                vmName);
        return record;
    }
    
    @Around(value="execution(@org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.annotation.SLAsticSLAMonitoringProbe * *(..)) && @annotation(sla)", argNames="thisJoinPoint,sla")
    public Object doBasicProfiling(ProceedingJoinPoint thisJoinPoint, SLAsticSLAMonitoringProbe sla) throws Throwable {
       if (!ctrlInst.isMonitoringEnabled()) {
            return thisJoinPoint.proceed();
        }
        SLOMonitoringRecord record  = this.initMonitoringRecord(thisJoinPoint);
         record.serviceId = sla.serviceId();
        try{
            this.proceedAndMeasure(thisJoinPoint, record);
        } catch (Exception e){
            throw e; // exceptions are forwarded
        } finally {
            /* note that proceedAndMeasure(...) even sets the variable name
             * in case the execution of the joint point resulted in an
             * exception! */
            ctrlInst.newMonitoringRecord(record);
        }
        return record.retVal;
    }

    protected void proceedAndMeasure(ProceedingJoinPoint thisJoinPoint,
            SLOMonitoringRecord record) throws Throwable {
        record.timestamp = ctrlInst.currentTimeNanos(); // startint stopwatch
        try {
            record.retVal = thisJoinPoint.proceed();
        } catch (Exception e) {
            throw e; // exceptions are forwarded
        } finally {
            record.rtNseconds = ctrlInst.currentTimeNanos()-record.timestamp;
        }
    }
}
