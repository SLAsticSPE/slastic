package org.trustsoft.slastic.monadapt.monitoringRecord.redeployment;

/**
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

import kieker.tpmon.annotation.TpmonInternal;
import kieker.tpmon.monitoringRecord.AbstractKiekerMonitoringRecord;

/**
 * @author Andre van Hoorn
 */
public class SLAsticRedeploymentRecord extends AbstractKiekerMonitoringRecord {

    private static final long serialVersionUID = 1823L;

    private static int typeId = AbstractKiekerMonitoringRecord.registerMonitoringRecordType(SLAsticRedeploymentRecord.class);
    private static int numRecordFields = 3;
    private long timestamp = -1;
    private int branchID = -1;
    private int branchingOutcome = -1;
    
    @TpmonInternal()
    public void initFromStringArray(String[] recordVector)
            throws IllegalArgumentException {
        if(recordVector.length > SLAsticRedeploymentRecord.numRecordFields) {
            throw new IllegalArgumentException("Expecting vector with "+
                    SLAsticRedeploymentRecord.numRecordFields + " elements but found:" + recordVector.length);
        }
        this.timestamp = Long.parseLong(recordVector[0]);
        this.branchID = Integer.parseInt(recordVector[1]);
        this.branchingOutcome = Integer.parseInt(recordVector[2]);
        return;
    }

    @TpmonInternal()
    public String[] toStringArray() {
        String[] vec = {
            Long.toString(timestamp),
            Integer.toString(branchID),
            Integer.toString(branchingOutcome)
        };
        return vec;
    }

    @TpmonInternal()
    public int getRecordTypeId() {
        return typeId;
    }

    @TpmonInternal()
    public static AbstractKiekerMonitoringRecord getInstance() {
        return new SLAsticRedeploymentRecord();
    }

    @TpmonInternal()
    public static SLAsticRedeploymentRecord getInstance(long timestamp, int branchID, int branchingOutcome){
        SLAsticRedeploymentRecord rec = (SLAsticRedeploymentRecord)SLAsticRedeploymentRecord.getInstance();
        rec.timestamp = timestamp;
        rec.branchID = branchID;
        rec.branchingOutcome = branchingOutcome;
        return rec;
    }
}