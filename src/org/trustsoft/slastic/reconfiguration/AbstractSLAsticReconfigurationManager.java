package org.trustsoft.slastic.reconfiguration;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.AbstractSLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticReconfigurationManager extends AbstractSLAsticComponent implements ISLAsticReconfigurationManager {
    private AbstractSLAsticControl controlComponent;

    public final AbstractSLAsticControl getControlComponent() {
        return controlComponent;
    }

    public final void setControlComponent(AbstractSLAsticControl controlComponent) {
        this.controlComponent = controlComponent;
    }
}
