package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventService;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventServiceClient;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractAnalysisSubComponent extends AbstractSLAsticComponent implements ISLAsticAnalysisSubComponent, ISimpleSLAsticEventServiceClient {

    private AbstractSLAsticAnalysis parentAnalysisComponent;
    private ISimpleSLAsticEventService simpleSLAsticEventService;

    public final AbstractSLAsticAnalysis getParentAnalysisComponent() {
        return parentAnalysisComponent;
    }

    public final void setParentAnalysisComponent(AbstractSLAsticAnalysis parentAnalysisComponent) {
        this.parentAnalysisComponent = parentAnalysisComponent;
    }

    public final ISimpleSLAsticEventService getSimpleSLAsticEventService() {
        return simpleSLAsticEventService;
    }

    public final void setSimpleSLAsticEventService(ISimpleSLAsticEventService simpleSLAsticEventService) {
        this.simpleSLAsticEventService = simpleSLAsticEventService;
    }

}
