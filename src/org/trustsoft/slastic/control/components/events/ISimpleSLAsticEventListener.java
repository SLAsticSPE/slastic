package org.trustsoft.slastic.control.components.events;

import org.trustsoft.slastic.control.components.analysis.*;

/**
 *
 * @author Andre van Hoorn
 */
public interface ISimpleSLAsticEventListener {
    public void handleSLAsticEvent (ISLAsticEvent ev);
}
