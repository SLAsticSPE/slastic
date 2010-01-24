package org.trustsoft.slastic.reconfiguration;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractSLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticReconfigurationManager extends AbstractSLAsticComponent implements ISLAsticReconfigurationManager {
    public static final String PROP_PREFIX = "slastic.reconfiguration";
    
    private AbstractSLAsticControl controlComponent;

    public final AbstractSLAsticControl getControlComponent() {
        return controlComponent;
    }

    public final void setControlComponent(AbstractSLAsticControl controlComponent) {
        this.controlComponent = controlComponent;
    }
}
