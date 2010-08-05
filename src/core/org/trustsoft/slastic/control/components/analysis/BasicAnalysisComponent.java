package org.trustsoft.slastic.control.components.analysis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author Andre van Hoorn
 */
public class BasicAnalysisComponent extends AbstractAnalysisComponent {

    private static final Log log = LogFactory.getLog(BasicAnalysisComponent.class);

    @Override
    public boolean init() {
        return true;
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void terminate(boolean error) {
        // do nothing
    }
}
