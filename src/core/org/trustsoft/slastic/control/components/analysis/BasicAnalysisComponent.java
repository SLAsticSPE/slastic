package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.events.IEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicAnalysisComponent extends AbstractAnalysisComponent {

    private static final Log log = LogFactory.getLog(BasicAnalysisComponent.class);

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    public void handleSLAsticEvent(IEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
