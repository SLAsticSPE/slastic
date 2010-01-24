package org.trustsoft.slastic.control.components.modelManager;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractSLAsticControl;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventService;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventServiceClient;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelManager extends AbstractSLAsticComponent implements ISLAsticModelManager, ISimpleSLAsticEventServiceClient {

    public static final String PROP_PREFIX = "slastic.control.modelmanagement";
    
    private AbstractSLAsticControl parentControlComponent;
    private ISimpleSLAsticEventService simpleSLAsticEventService;

    public boolean execute() {
        return true;
    }

    public void terminate() {
    }

    public final AbstractSLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(AbstractSLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }

    public final ISimpleSLAsticEventService getSimpleSLAsticEventService() {
        return simpleSLAsticEventService;
    }

    public final void setSimpleSLAsticEventService(ISimpleSLAsticEventService simpleSLAsticEventService) {
        this.simpleSLAsticEventService = simpleSLAsticEventService;
    }
}
