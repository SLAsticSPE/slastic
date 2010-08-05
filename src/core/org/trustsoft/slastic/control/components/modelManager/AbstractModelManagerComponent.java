package org.trustsoft.slastic.control.components.modelManager;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;
import org.trustsoft.slastic.control.components.events.ISimpleEventService;
import org.trustsoft.slastic.control.components.events.ISimpleEventServiceClient;
import org.trustsoft.slastic.monitoring.IObservationEventReceiver;
import org.trustsoft.slastic.reconfiguration.IReconfigurationPlanReceiver;

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
