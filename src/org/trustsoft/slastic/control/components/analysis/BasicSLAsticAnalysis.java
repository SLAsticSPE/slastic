package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.events.ISLAsticEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicSLAsticAnalysis extends AbstractSLAsticAnalysis {

    private static final Log log = LogFactory.getLog(BasicSLAsticAnalysis.class);

    public void init(String initString) throws IllegalArgumentException {
        super.initVarsFromInitString(initString);
        // we don't expect init properties so far, so just return.
    }

    public void handleSLAsticEvent(ISLAsticEvent ev) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
