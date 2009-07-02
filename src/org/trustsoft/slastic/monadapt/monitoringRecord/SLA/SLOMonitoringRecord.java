package org.trustsoft.slastic.monadapt.monitoringRecord.SLA;

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
    public long timestamp = -1;
    public String componentName = null;
    public String operationName = null;
    public String host= null;
    public long rtNseconds = -1;
    public Object retVal = null;

    public void initFromStringVector(String[] recordVector)
            throws IllegalArgumentException {
        // String[]
        if(recordVector.length > SLOMonitoringRecord.numRecordFields) {
            throw new IllegalArgumentException("Expecting vector with "+
                    SLOMonitoringRecord.numRecordFields + " elements but found:" + recordVector.length);
        }
        this.timestamp = Long.parseLong(recordVector[0]);
        this.componentName = recordVector[1];
        this.operationName = recordVector[2];
        this.host = recordVector[3];
        this.rtNseconds = Long.parseLong(recordVector[4]);
        return;
    }

    public String[] toStringVector() {
        // String[] = {....}
        String[] vec = {
            Long.toString(timestamp),
            this.componentName,
            this.operationName,
            this.host,
            Long.toString(rtNseconds)
        };
        return vec;
    }

    public int getRecordTypeId() {
        return typeId;
    }

    public static AbstractKiekerMonitoringRecord getInstance() {
        return new SLOMonitoringRecord();
    }

    public static SLOMonitoringRecord getInstance(String componentName,
            String operationName, String host){
        SLOMonitoringRecord rec = (SLOMonitoringRecord)SLOMonitoringRecord.getInstance();
        rec.componentName = componentName;
        rec.operationName = operationName;
        rec.host = host;
        return rec;
    }
}