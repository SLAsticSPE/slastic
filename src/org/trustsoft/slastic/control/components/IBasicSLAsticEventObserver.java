package org.trustsoft.slastic.control.components;

import org.trustsoft.slastic.control.components.analysis.*;

/**
 *
 * @author Andre van Hoorn
 */
public interface IBasicSLAsticEventObserver {
    public void handleSLAsticEvent (ISLAsticEvent ev);
}
