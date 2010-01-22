package org.trustsoft.slastic.monitoring;

import org.trustsoft.slastic.common.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.AbstractSLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public class AbstractSLAsticMonitoringManager extends AbstractSLAsticComponent implements ISLAsticMonitoringManager {

    private AbstractSLAsticControl controller;

    public void init(String initString) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void terminate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public final AbstractSLAsticControl getController() {
        return this.controller;
    }

    public final void setController(final AbstractSLAsticControl controller) {
        this.controller = controller;
    }

}
