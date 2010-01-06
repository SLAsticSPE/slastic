package org.trustsoft.slastic.control.components.modelUpdater;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.ISLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelUpdater extends AbstractSLAsticComponent implements ISLAsticModelUpdater {

    private ISLAsticControl parentControlComponent;

    public boolean execute() { return true; }

    public void terminate() { }

    public final ISLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(ISLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }
}
