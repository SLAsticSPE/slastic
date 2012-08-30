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

package kieker.tools.slastic.control.components.modelManager;

import kieker.tools.slastic.common.AbstractSLAsticComponent;
import kieker.tools.slastic.control.AbstractControlComponent;
import kieker.tools.slastic.control.components.events.ISimpleEventService;
import kieker.tools.slastic.control.components.events.ISimpleEventServiceClient;
import kieker.tools.slastic.monitoring.IObservationEventReceiver;
import kieker.tools.slastic.reconfiguration.IReconfigurationPlanReceiver;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractModelManagerComponent extends AbstractSLAsticComponent implements IReconfigurationPlanReceiver, IObservationEventReceiver, ISimpleEventServiceClient {

    public static final String PROP_PREFIX = "slastic.control.modelmanagement";
    
    private AbstractControlComponent parentControlComponent;
    private ISimpleEventService simpleSLAsticEventService;

    public final AbstractControlComponent getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(final AbstractControlComponent parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }

    public final ISimpleEventService getSimpleSLAsticEventService() {
        return simpleSLAsticEventService;
    }

    public final void setSimpleSLAsticEventService(final ISimpleEventService simpleSLAsticEventService) {
        this.simpleSLAsticEventService = simpleSLAsticEventService;
    }
}
