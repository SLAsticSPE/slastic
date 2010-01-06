package org.trustsoft.slastic.control.components.modelManager;

import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractSLAsticModelManager extends AbstractSLAsticComponent implements ISLAsticModelManager {
    public boolean execute() { return true; }

    public void terminate() { }
}
