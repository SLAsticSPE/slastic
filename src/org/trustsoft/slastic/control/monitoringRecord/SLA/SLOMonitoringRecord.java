package org.trustsoft.slastic.control.monitoringRecord.SLA;

/**
 * kieker.tpmon.KiekerBranchingRecord.java
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
 */

import java.util.Vector;
import kieker.tpmon.annotation.TpmonInternal;
import kieker.tpmon.core.TpmonController;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

/**
 * @author Andre van Hoorn
 */
public class SLOMonitoringRecord extends AbstractKiekerMonitoringRecord {

    private static final long serialVersionUID = 1113L;

   /** Used to identify the type of CSV records
    * This record type has a fixed value of 0
    */
    private static int typeId = TpmonController.getInstance().registerMonitoringRecordType(SLOMonitoringRecord.class);
    private static int numRecordFields = 5;
    private long timestamp = -1;
    private String componentName = null;
    private String operationName = null;
    private String host= null;
    private long rtNseconds = -1;
    
    @TpmonInternal()
    public void initFromStringVector(Vector<String> recordVector)
            throws IllegalArgumentException {
        // String[]
        if(recordVector.size() > SLOMonitoringRecord.numRecordFields) {
            throw new IllegalArgumentException("Expecting vector with "+
                    SLOMonitoringRecord.numRecordFields + " elements but found:" + recordVector.size());
        }
        this.timestamp = Long.parseLong(recordVector.elementAt(0));
        this.componentName = recordVector.elementAt(1);
        this.operationName = recordVector.elementAt(2);
        this.host = recordVector.elementAt(3);
        this.rtNseconds = Integer.parseInt(recordVector.elementAt(4));
        return;
    }

    @TpmonInternal()
    public Vector<String> toStringVector() {
        // String[] = {....}
        Vector<String> vec = new Vector<String>(3);
        vec.insertElementAt(Long.toString(timestamp), 0);
        vec.insertElementAt(this.componentName, 1);
        vec.insertElementAt(this.operationName, 2);
        vec.insertElementAt(this.host, 3);
        vec.insertElementAt(Long.toString(rtNseconds), 4);
        return vec;
    }

    @TpmonInternal()
    public int getRecordTypeId() {
        return typeId;
    }

    @TpmonInternal()
    public static AbstractKiekerMonitoringRecord getInstance() {
        return new SLOMonitoringRecord();
    }

    @TpmonInternal()
    public static SLOMonitoringRecord getInstance(long timestamp, int branchID, int branchingOutcome){
        SLOMonitoringRecord rec = (SLOMonitoringRecord)SLOMonitoringRecord.getInstance();
        return rec;
    }
}