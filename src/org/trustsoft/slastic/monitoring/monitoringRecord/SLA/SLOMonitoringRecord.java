package org.trustsoft.slastic.monitoring.monitoringRecord.SLA;

import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

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



/**
 * @author Andre van Hoorn
 */
public class SLOMonitoringRecord extends AbstractKiekerMonitoringRecord implements Comparable<SLOMonitoringRecord>{

    private static final long serialVersionUID = 1113L;

   /** Used to identify the type of CSV records
    * This record type has a fixed value of 0
    */
    private static int typeId = AbstractKiekerMonitoringRecord.registerMonitoringRecordType(SLOMonitoringRecord.class);
    private static int numRecordFields = 6;
    public long timestamp = -1;
    public int serviceId = -1;
    public String componentName = null;
    public String operationName = null;
    public String host= null;
    public long rtNseconds = -1;
    public Object retVal = null;

    public void initFromStringArray(String[] recordVector)
            throws IllegalArgumentException {
        // String[]
        if(recordVector.length > SLOMonitoringRecord.numRecordFields) {
            throw new IllegalArgumentException("Expecting vector with "+
                    SLOMonitoringRecord.numRecordFields + " elements but found:" + recordVector.length);
        }
        this.timestamp = Long.parseLong(recordVector[0]);
        this.serviceId=Integer.parseInt(recordVector[1]);
        this.componentName = recordVector[2];
        this.operationName = recordVector[3];
        this.host = recordVector[4];
        this.rtNseconds = Long.parseLong(recordVector[5]);
        return;
    }

    public String[] toStringArray() {
        // String[] = {....}
        String[] vec = {
            Long.toString(timestamp),
            Integer.toString(this.serviceId),
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
    
    public int compareTo(SLOMonitoringRecord slo){
    	if(slo == this){
    		return 0;
    	}else if(this.rtNseconds > slo.rtNseconds){
    	  return 1;
    	}else{
    	  return -1;
      }
      
    }
}