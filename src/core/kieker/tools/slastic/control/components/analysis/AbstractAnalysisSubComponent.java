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

package kieker.tools.slastic.control.components.analysis;

import kieker.tools.slastic.common.AbstractSLAsticComponent;
import kieker.tools.slastic.control.components.events.ISimpleEventService;
import kieker.tools.slastic.control.components.events.ISimpleEventServiceClient;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractAnalysisSubComponent extends AbstractSLAsticComponent implements ISimpleEventServiceClient {

    private AbstractAnalysisComponent parentAnalysisComponent;
    private ISimpleEventService simpleSLAsticEventService;

    public final AbstractAnalysisComponent getParentAnalysisComponent() {
        return parentAnalysisComponent;
    }

    public final void setParentAnalysisComponent(final AbstractAnalysisComponent parentAnalysisComponent) {
        this.parentAnalysisComponent = parentAnalysisComponent;
    }

    public final ISimpleEventService getSimpleSLAsticEventService() {
        return simpleSLAsticEventService;
    }

    public final void setSimpleSLAsticEventService(final ISimpleEventService simpleSLAsticEventService) {
        this.simpleSLAsticEventService = simpleSLAsticEventService;
    }

}
