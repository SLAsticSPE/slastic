package org.trustsoft.slastic.monitoring;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractSLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticMonitoringManager extends AbstractSLAsticComponent implements ISLAsticMonitoringManager {
    public static final String PROP_PREFIX = "slastic.monitoring";
    
    private AbstractSLAsticControl controller;

    public void init(String initString) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public abstract boolean execute();

    public abstract void terminate();

    public final AbstractSLAsticControl getController() {
        return this.controller;
    }

    public final void setController(final AbstractSLAsticControl controller) {
        this.controller = controller;
    }

}
