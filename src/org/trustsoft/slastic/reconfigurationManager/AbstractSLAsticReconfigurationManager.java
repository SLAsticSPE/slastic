package org.trustsoft.slastic.reconfigurationManager;

import org.trustsoft.slastic.control.components.AbstractSLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticReconfigurationManager implements ISLAsticReconfigurationManager {
    private AbstractSLAsticControl controlComponent;

    public final AbstractSLAsticControl getControlComponent() {
        return controlComponent;
    }

    public final void setControlComponent(AbstractSLAsticControl controlComponent) {
        this.controlComponent = controlComponent;
    }
}
