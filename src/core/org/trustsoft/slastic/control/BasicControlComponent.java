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

package org.trustsoft.slastic.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.monitoring.IObservationEventReceiver;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicControlComponent extends AbstractControlComponent {

    private static final Log log = LogFactory.getLog(BasicControlComponent.class);

    private final IObservationEventReceiver monitoringClientPort = new IObservationEventReceiver() {

            public void newObservation(final IObservationEvent event) {
                getModelUpdater().newObservation(event);
            }
        };

    @Override
    public IObservationEventReceiver getMonitoringClientPort() {
        return this.monitoringClientPort;
    }

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean execute() {
        return true;
    }

    public void terminate(boolean error) {
        // do nothing
    }
}
