package org.trustsoft.slastic.monitoring;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractControlComponent;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractMonitoringManagerComponent extends AbstractSLAsticComponent {
    public static final String PROP_PREFIX = "slastic.monitoring";
    
    private AbstractControlComponent controller;

    public void init(String initString) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public final AbstractControlComponent getController() {
        return this.controller;
    }

    public final void setController(final AbstractControlComponent controller) {
        this.controller = controller;
    }

}
