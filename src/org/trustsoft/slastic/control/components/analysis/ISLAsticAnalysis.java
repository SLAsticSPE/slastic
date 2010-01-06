package org.trustsoft.slastic.control.components.analysis;

import org.trustsoft.slastic.control.components.ISLAsticComponent;

/**
 * This Interface represents the Analysis component of the SLAstic.CONTROL framework. It holds different componente that are responsible for different analysis'.
 * @author Lena Stoever
 *
 */
public interface ISLAsticAnalysis extends ISLAsticComponent {

    /**
     * method for delegating ISlAsticAnalysisEvents to the belonging analysis
     * objects
     *
     * @param evt
     */
    public void handleInternalEvent(ISLAsticAnalysisEvent evt);

    // TODO: remove
    public void setSLAs(slal.Model slas);
}
