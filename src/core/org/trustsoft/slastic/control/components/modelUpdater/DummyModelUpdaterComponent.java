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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.trustsoft.slastic.control.components.modelUpdater;

import org.trustsoft.slastic.common.event.IObservationEvent;
import org.trustsoft.slastic.control.components.events.IEvent;

/**
 * A model update manager that simply doesn't do anything.
 *
 * @author Andree van Hoorn
 */
public class DummyModelUpdaterComponent extends AbstractModelUpdaterComponent {

    @Override
    public void newObservation(IObservationEvent event) {
        // do nothing
    }

    @Override
    public void handleEvent(IEvent ev) {
        // do nothing
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
