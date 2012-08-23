/***************************************************************************
 * Copyright 2012 The SLAstic project
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
 ***************************************************************************/

package org.trustsoft.slastic.plugins.slachecker.control.modelUpdater;

import kieker.common.record.IMonitoringRecord;
import org.trustsoft.slastic.plugins.pcm.control.modelManager.ModelManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;
import org.trustsoft.slastic.control.components.modelUpdater.AbstractModelUpdaterComponent;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.KiekerMeasurementEvent;
import org.trustsoft.slastic.plugins.slachecker.monitoring.kieker.monitoringRecord.sla.SLOMonitoringRecord;

/**
 * This class is a RecordConsumer which gets the MonitoringRecords of Kieker and
 * collects specific MonitoringRecords to forward them to the ModelManager.
 * 
 * @author Lena Stoever
 * 
 */
public class ModelUpdater extends AbstractModelUpdaterComponent {

    private static final Log log = LogFactory.getLog(ModelUpdater.class);
    long count = 0;

    /**
     * The Only constructor of this class.
     *
     * @param defaultCapacity
     *            Capacity of the List of SLOMonitoringRecords that should be
     *            collected.
     */
    public ModelUpdater() {
    }

    @Override
    public void handleEvent(IEvent ev) {
    }

    @Override
    public void newObservation(IObservationEvent ev) {
        count++;
        if (ev instanceof KiekerMeasurementEvent) {
            IMonitoringRecord kiekerRecord = ((KiekerMeasurementEvent) ev).getKiekerRecord();
            if (kiekerRecord == null) {
                log.error("kiekerRecord field of event is null!");
                return;
            }
            if (kiekerRecord instanceof SLOMonitoringRecord) {
                ((ModelManager) this.getModelManager()).newObservation(ev);
            }
        }
        //Logging the number of Records that have been processed
        if (count % 500 == 0) {
            log.info("Number of Records: " + count);
        }
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(boolean error) {
        // do nothing
    }
}
