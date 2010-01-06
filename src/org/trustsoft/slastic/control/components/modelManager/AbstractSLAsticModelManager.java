package org.trustsoft.slastic.control.components.modelManager;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.ISLAsticControl;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelManager extends AbstractSLAsticComponent implements ISLAsticModelManager {

    private ISLAsticControl parentControlComponent;

    public boolean execute() {
        return true;
    }

    public void terminate() {
    }

    public final ISLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(ISLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }
}
