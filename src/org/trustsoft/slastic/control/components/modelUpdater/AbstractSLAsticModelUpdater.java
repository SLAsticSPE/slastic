package org.trustsoft.slastic.control.components.modelUpdater;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.AbstractSLAsticControl;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventService;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventServiceClient;
import org.trustsoft.slastic.control.components.modelManager.AbstractSLAsticModelManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelUpdater extends AbstractSLAsticComponent implements ISLAsticModelUpdater, ISimpleSLAsticEventServiceClient {

    private AbstractSLAsticControl parentControlComponent;
    private AbstractSLAsticModelManager modelManager;
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

    public final AbstractSLAsticModelManager getModelManager() {
        return modelManager;
    }

    public final void setModelManager(AbstractSLAsticModelManager modelManager) {
        this.modelManager = modelManager;
    }

    public final ISimpleSLAsticEventService getSimpleSLAsticEventService() {
        return simpleSLAsticEventService;
    }

    public final void setSimpleSLAsticEventService(ISimpleSLAsticEventService simpleSLAsticEventService) {
        this.simpleSLAsticEventService = simpleSLAsticEventService;
    }
}
