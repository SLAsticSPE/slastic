package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.events.ISimpleEventService;
import org.trustsoft.slastic.control.components.events.ISimpleEventServiceClient;

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
