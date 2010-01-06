package org.trustsoft.slastic.control.components.analysis;


import org.trustsoft.slastic.control.components.AbstractSLAsticComponent;

/**
 *
 * @author Andre van Hoorn
 */
public abstract class AbstractAnalysisSubComponent extends AbstractSLAsticComponent implements ISLAsticAnalysisSubComponent {
    private AbstractSLAsticAnalysis parentAnalysisComponent;

    public final AbstractSLAsticAnalysis getParentAnalysisComponent() {
        return parentAnalysisComponent;
    }

    public final void setParentAnalysisComponent(AbstractSLAsticAnalysis parentAnalysisComponent) {
        this.parentAnalysisComponent = parentAnalysisComponent;
    }
}
