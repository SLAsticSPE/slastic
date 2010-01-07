package org.trustsoft.slastic.control.components.analysis;

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

    // TODO: implement basic event handling concept from Tpan
    public void handleInternalEvent(ISLAsticAnalysisEvent evt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
