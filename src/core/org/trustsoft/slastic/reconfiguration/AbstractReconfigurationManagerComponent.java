package org.trustsoft.slastic.reconfiguration;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractReconfigurationManagerComponent extends AbstractSLAsticComponent implements IReconfigurationPlanReceiver {
    public static final String PROP_PREFIX = "slastic.reconfiguration";
    
    private AbstractControlComponent controlComponent;

    public final AbstractControlComponent getControlComponent() {
        return controlComponent;
    }

    public final void setControlComponent(final AbstractControlComponent controlComponent) {
        this.controlComponent = controlComponent;
    }
}
