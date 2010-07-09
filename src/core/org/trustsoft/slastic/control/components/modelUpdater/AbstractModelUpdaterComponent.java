package org.trustsoft.slastic.control.components.modelUpdater;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.components.events.ISimpleEventService;
import org.trustsoft.slastic.control.components.events.ISimpleEventServiceClient;
import org.trustsoft.slastic.control.components.modelManager.AbstractModelManagerComponent;
import org.trustsoft.slastic.monitoring.IObservationEventReceiver;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractModelUpdaterComponent extends AbstractSLAsticComponent implements IObservationEventReceiver, ISimpleEventServiceClient {

    public static final String PROP_PREFIX = "slastic.control.modelupdating";
    
    private AbstractControlComponent parentControlComponent;
    private AbstractModelManagerComponent modelManager;
    private ISimpleEventService simpleSLAsticEventService;

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(final boolean error) {
    }

    public final AbstractControlComponent getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(final AbstractControlComponent parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }

    public final AbstractModelManagerComponent getModelManager() {
        return modelManager;
    }

    public final void setModelManager(final AbstractModelManagerComponent modelManager) {
        this.modelManager = modelManager;
    }

    public final ISimpleEventService getSimpleSLAsticEventService() {
        return simpleSLAsticEventService;
    }

    public final void setSimpleSLAsticEventService(final ISimpleEventService simpleSLAsticEventService) {
        this.simpleSLAsticEventService = simpleSLAsticEventService;
    }
}
