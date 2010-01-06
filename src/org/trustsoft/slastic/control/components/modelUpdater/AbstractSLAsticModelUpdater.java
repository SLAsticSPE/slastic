package org.trustsoft.slastic.control.components.modelUpdater;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.AbstractSLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelUpdater extends AbstractSLAsticComponent implements ISLAsticModelUpdater {

    private AbstractSLAsticControl parentControlComponent;

    public boolean execute() { return true; }

    public void terminate() { }

    public final AbstractSLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(AbstractSLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }
}
