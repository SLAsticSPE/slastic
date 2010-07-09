package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.reconfiguration.AbstractReconfigurationManagerComponent;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractAdaptationPlannerComponent extends AbstractAnalysisSubComponent {
    public static final String PROP_PREFIX = "slastic.control.analysis.adaptationplanning";
    
    public final AbstractReconfigurationManagerComponent getReconfigurationManager(){
        return this.getParentAnalysisComponent().getReconfigurationManager();
    }
}
