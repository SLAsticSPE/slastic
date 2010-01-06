package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.reconfigurationManager.AbstractSLAsticReconfigurationManager;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractAdaptationPlanner extends AbstractAnalysisSubComponent {
    public final AbstractSLAsticReconfigurationManager getReconfigurationManager(){
        return this.getParentAnalysisComponent().getReconfigurationManager();
    }
}
