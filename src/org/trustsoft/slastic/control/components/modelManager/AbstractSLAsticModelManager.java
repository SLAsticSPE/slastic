package org.trustsoft.slastic.control.components.modelManager;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;
import org.trustsoft.slastic.control.components.AbstractSLAsticControl;
import org.trustsoft.slastic.control.components.events.ISimpleSLAsticEventServiceClient;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelManager extends AbstractSLAsticComponent implements ISLAsticModelManager, ISimpleSLAsticEventServiceClient {

    private AbstractSLAsticControl parentControlComponent;

    public boolean execute() {
        return true;
    }

    public void terminate() {
    }

    public final AbstractSLAsticControl getParentControlComponent() {
        return parentControlComponent;
    }

    public final void setParentControlComponent(AbstractSLAsticControl parentControlComponent) {
        this.parentControlComponent = parentControlComponent;
    }
}
