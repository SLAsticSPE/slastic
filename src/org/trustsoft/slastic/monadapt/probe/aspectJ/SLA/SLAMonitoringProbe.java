package org.trustsoft.slastic.monadapt.probe.aspectJ.SLA;

import kieker.tpmon.core.TpmonController;
import kieker.tpmon.*;
import kieker.tpmon.core.ControlFlowRegistry;
import kieker.tpmon.probe.IKiekerMonitoringProbe;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.trustsoft.slastic.monadapt.annotation.SLAsticSLAMonitoringProbe;
import org.trustsoft.slastic.monadapt.monitoringRecord.SLA.SLOMonitoringRecord;

/*
 * org.trustsoft.slastic.control.probe.aspectJ.SLA.SLAMonitoringProbe
 *
 * ==================LICENCE=========================
 * Copyright 2006-2009 Kieker Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==================================================
 *
 * @author Andre van Hoorn
 */
@Aspect
public class SLAMonitoringProbe implements IKiekerMonitoringProbe {

    protected static final TpmonController ctrlInst = TpmonController.getInstance();
    protected static final ControlFlowRegistry cfRegistry = ControlFlowRegistry.getInstance();
    private static final String vmName = ctrlInst.getVmname();

    protected SLOMonitoringRecord initMonitoringRecord(ProceedingJoinPoint thisJoinPoint) {
       // e.g. "getBook" 
        String methodname = thisJoinPoint.getSignature().getName();
        // toLongString provides e.g. "public kieker.tests.springTest.Book kieker.tests.springTest.CatalogService.getBook()"
        String paramList = thisJoinPoint.getSignature().toLongString();
        int paranthIndex = paramList.lastIndexOf('(');
        paramList = paramList.substring(paranthIndex); // paramList is now e.g.,  "()"

        SLOMonitoringRecord record = SLOMonitoringRecord.getInstance(
                thisJoinPoint.getSignature().getDeclaringTypeName() /* component */, 
                methodname + paramList /* operation */, 
                vmName);
        return record;
    }
    
    @Around(value="execution(@org.trustsoft.slastic.monadapt.annotation.SLAsticSLAMonitoringProbe * *(..)) && @annotation(sla)", argNames="thisJoinPoint,sla")
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
            ctrlInst.logMonitoringRecord(record);
        }
        return record.retVal;
    }
    
    protected void proceedAndMeasure(ProceedingJoinPoint thisJoinPoint,
            SLOMonitoringRecord record) throws Throwable {
        record.timestamp = ctrlInst.getTime(); // startint stopwatch
        try {
            record.retVal = thisJoinPoint.proceed();
        } catch (Exception e) {
            throw e; // exceptions are forwarded
        } finally {
            record.rtNseconds = ctrlInst.getTime()-record.timestamp;
        }
    }
}
